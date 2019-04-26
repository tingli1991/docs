
### 背景：  
之前一直有朋友问，.NET Core + Linux环境有没有类似Windows服务的东西。其实是有的，我了解的方法有两种：

1、创建一个ASP.Net Core的Web项目（如Web API），然后通过添加中间件（Middleware）的方式来启动任务；

2 、创建一个.Net Core的项目，添加Host，Dependency Injection，Configuration等组件，然后通过Main方法或中间件的方式启动服务。

但是，上述两种方法都有点不足，如：

1、会把Web的生命周期引进来，但实际上，我们并不需要Web的功能，如Controller；

2、本身是没有问题的，但是对开发者的要求相对高一点点，需要对.Net Core的各个组成部分都有一定的认识，简而言之，门槛有一丢丢高。

.NETCore 2.1推出了一个Generic Host的概念，可以很好的解决上面两种方法的不足：  
至于为什么选择Quartz来做调度，我想可能是因为情怀吧，因为之前是用的TopShelf+Quartz，其实Hangfire也不错。  

### 使用Hosted Service  
##### 创建一个控制台程序  
![](https://github-1251498502.cos.ap-chongqing.myqcloud.com/Stack.Notepads/Notebook.201904260001.png)

##### 添加Host Nuget包  
``` C#
Install-Package Microsoft.Extensions.Hosting -Version 2.1.0
Install-Package Microsoft.Extensions.Configuration.FileExtensions -Version 2.1.0
Install-Package Microsoft.Extensions.Configuration.Json -Version 2.1.0
Install-Package Microsoft.Extensions.Configuration.EnvironmentVariables -Version 2.1.0
Install-Package Microsoft.Extensions.Configuration.CommandLine -Version 2.1.0
Install-Package Microsoft.Extensions.Logging.Console -Version 2.1.0
Install-Package Microsoft.Extensions.Logging.Debug -Version 2.1.0
```  

##### 添加一个基于Timer的简单Hosted Service（用于简单演示），直接继承IHostedService接口即可  
``` C#
internal class TimedHostedService : IHostedService, IDisposable
{
    private readonly ILogger _logger;
    private Timer _timer;

    public TimedHostedService(ILogger<TimedHostedService> logger)
    {
        _logger = logger;
    }

    public Task StartAsync(CancellationToken cancellationToken)
    {
        _logger.LogInformation("Timed Background Service is starting.");

        _timer = new Timer(DoWork, null, TimeSpan.Zero, TimeSpan.FromSeconds(5));

        return Task.CompletedTask;
    }

    private void DoWork(object state)
    {
        _logger.LogInformation(string.Format("[{0:yyyy-MM-dd hh:mm:ss}]Timed Background Service is working.", DateTime.Now));
    }

    public Task StopAsync(CancellationToken cancellationToken)
    {
        _logger.LogInformation("Timed Background Service is stopping.");

        _timer?.Change(Timeout.Infinite, 0);

        return Task.CompletedTask;
    }

    public void Dispose()
    {
        _timer?.Dispose();
    }
}
```
##### Main函数中添加Host的相关代码  
``` C#
var host = new HostBuilder()
    .ConfigureHostConfiguration(configHost =>
    {
        configHost.SetBasePath(Directory.GetCurrentDirectory());
        //configHost.AddJsonFile("hostsettings.json", true, true);
        configHost.AddEnvironmentVariables("ASPNETCORE_");
        //configHost.AddCommandLine(args);
    })
    .ConfigureAppConfiguration((hostContext, configApp) =>
    {
        configApp.AddJsonFile("appsettings.json", true);
        configApp.AddJsonFile($"appsettings.{hostContext.HostingEnvironment.EnvironmentName}.json", true);
        configApp.AddEnvironmentVariables();
        //configApp.AddCommandLine(args);
    })
    .ConfigureServices((hostContext, services) =>
    {
        services.AddLogging();
        services.AddHostedService<TimedHostedService>();
    })
    .ConfigureLogging((hostContext, configLogging) =>
    {
        configLogging.AddConsole();
        if (hostContext.HostingEnvironment.EnvironmentName == EnvironmentName.Development)
        {
            configLogging.AddDebug();
        }
    })
    .UseConsoleLifetime()
    .Build();
host.Run();
```
##### 运行结果  
![](https://github-1251498502.cos.ap-chongqing.myqcloud.com/Stack.Notepads/Notebook.201904260002.png)




