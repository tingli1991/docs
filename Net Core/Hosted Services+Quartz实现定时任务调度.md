
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
![](https://github-1251498502.cos.ap-chongqing.myqcloud.com/Stack.Notepads/Notebook.201904260003.png)

##### 代码解析  
###### Host配置  
``` C#
.ConfigureHostConfiguration(configHost =>
{
　　//配置根目录
　　configHost.SetBasePath(Directory.GetCurrentDirectory()); 
　　//读取host的配置json，和appsetting类似，暂不需要先注释掉，可根据需要开启
　　//configHost.AddJsonFile("hostsettings.json", true, true); 
　　//读取环境变量，Asp.Net core默认的环境变量是以ASPNETCORE_作为前缀的，这里也采用此前缀以保持一致
　　configHost.AddEnvironmentVariables("ASPNETCORE_"); 
　　//可以在启动host的时候之前可传入参数，暂不需要先注释掉，可根据需要开启
　　//configHost.AddCommandLine(args);
})
```

###### App配置  
``` C#
.ConfigureAppConfiguration((hostContext, configApp) =>
{
　　//读取应用的配置json
　　configApp.AddJsonFile("appsettings.json", true); 
　　//读取应用特定环境下的配置json
　　configApp.AddJsonFile($"appsettings.{hostContext.HostingEnvironment.EnvironmentName}.json", true); 
　　//读取环境变量
　　configApp.AddEnvironmentVariables(); 
　　//可以在启动host的时候之前可传入参数，暂不需要先注释掉，可根据需要开启
　　//configApp.AddCommandLine(args);
})
```

###### 配置服务及依赖注入注册，注：没有Middleware的配置了
``` C#
.ConfigureServices((hostContext, services) =>
{
　　//添加日志Service
　　services.AddLogging();
　　//添加Timer Hosted Service
　　services.AddHostedService<TimedHostedService>();
})
```

###### 日志配置  
``` C#
.ConfigureLogging((hostContext, configLogging) =>
{
　　//输出控制台日志
　　configLogging.AddConsole();
　　//开发环境输出Debug日志
　　if (hostContext.HostingEnvironment.EnvironmentName == EnvironmentName.Development)
　　{
　　　　configLogging.AddDebug();
　　}
})
```

###### 使用控制台生命周期  
``` C#
.UseConsoleLifetime() //使用Ctrl + C退出
```

### 使用Quartz  
#### 添加Host Nuget包  
``` C#
Install-Package Quartz -Version 3.0.5
Install-Package Quartz.Plugins -Version 3.0.5
```  

#### Quartz配置  
之前Quartz的配置是放在quartz.config里面的，但我更喜欢使用appsettings.json，因此，把配置改成了从appsettings.json。  
先建一个QuartzOption的类：  
``` c# 
/// <summary>
/// 更多设置请参考：https://github.com/quartznet/quartznet/blob/master/src/Quartz/Impl/StdSchedulerFactory.cs
/// </summary>
public class QuartzOption
{
    public QuartzOption(IConfiguration config)
    {
        if (config == null)
        {
            throw new ArgumentNullException(nameof(config));
        }

        var section = config.GetSection("quartz");
        section.Bind(this);
    }

    public Scheduler Scheduler { get; set; }

    public ThreadPool ThreadPool { get; set; }

    public Plugin Plugin { get; set; }

    public NameValueCollection ToProperties()
    {
        var properties = new NameValueCollection
        {
            ["quartz.scheduler.instanceName"] = Scheduler?.InstanceName,
            ["quartz.threadPool.type"] = ThreadPool?.Type,
            ["quartz.threadPool.threadPriority"] = ThreadPool?.ThreadPriority,
            ["quartz.threadPool.threadCount"] = ThreadPool?.ThreadCount.ToString(),
            ["quartz.plugin.jobInitializer.type"] = Plugin?.JobInitializer?.Type,
            ["quartz.plugin.jobInitializer.fileNames"] = Plugin?.JobInitializer?.FileNames
        };

        return properties;
    }
}

public class Scheduler
{
    public string InstanceName { get; set; }
}

public class ThreadPool
{
    public string Type { get; set; }

    public string ThreadPriority { get; set; }

    public int ThreadCount { get; set; }
}

public class Plugin
{
    public JobInitializer JobInitializer { get; set; }
}

public class JobInitializer
{
    public string Type { get; set; }
    public string FileNames { get; set; }
}
```

#### 重写JobFactory  
``` C#
public class JobFactory : IJobFactory
{
    private readonly IServiceProvider _serviceProvider;

    public JobFactory(IServiceProvider serviceProvider)
    {
        _serviceProvider = serviceProvider;
    }

    public IJob NewJob(TriggerFiredBundle bundle, IScheduler scheduler)
    {
        var job = _serviceProvider.GetService(bundle.JobDetail.JobType) as IJob;
        return job;
    }
    public void ReturnJob(IJob job)
    {
    }
}
```

#### 编写Quartz Hosted Service  
``` c# 
public class QuartzService : IHostedService
{
    private readonly ILogger _logger;
    private readonly IScheduler _scheduler;

    public QuartzService(ILogger<QuartzService> logger, IScheduler scheduler)
    {
        _logger = logger;
        _scheduler = scheduler;
    }

    public async Task StartAsync(CancellationToken cancellationToken)
    {
        _logger.LogInformation("开始Quartz调度...");
        await _scheduler.Start(cancellationToken);
    }

    public async Task StopAsync(CancellationToken cancellationToken)
    {
        _logger.LogInformation("停止Quartz调度...");
        await _scheduler.Shutdown(cancellationToken);
    }
}
``` 

#### 准备appsettings.json  
``` C# 
{
  "quartz": {
    "scheduler": {
      "instanceName": "HostedService.Quartz"
    },
    "threadPool": {
      "type": "Quartz.Simpl.SimpleThreadPool, Quartz",
      "threadPriority": "Normal",
      "threadCount": 10
    },
    "plugin": {
      "jobInitializer": {
        "type": "Quartz.Plugin.Xml.XMLSchedulingDataProcessorPlugin, Quartz.Plugins",
        "fileNames": "quartz_jobs.xml"
      }
    }
  }
}
```  

#### 编写一个TestJob  
``` C# 
public class TestJob : IJob
{
    private readonly ILogger _logger;

    public TestJob(ILogger<TestJob> logger)
    {
        _logger = logger;
    }

    public Task Execute(IJobExecutionContext context)
    {
        _logger.LogInformation(string.Format("[{0:yyyy-MM-dd hh:mm:ss:ffffff}]任务执行！", DateTime.Now));
        return Task.CompletedTask;
    }
}
``` 

#### 准备Quartz的调度文件quartz_jobs.xml
``` xml 
<?xml version="1.0" encoding="UTF-8"?>
<job-scheduling-data xmlns="http://quartznet.sourceforge.net/JobSchedulingData"
        xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
                 version="2.0">

  <processing-directives>
    <overwrite-existing-data>true</overwrite-existing-data>
  </processing-directives>

  <schedule>
    <job>
      <name>TestJob</name>
      <group>TestGroup</group>
      <description>测试任务</description>
      <job-type>HostedService.Quartz.Jobs.TestJob, HostedService.Quartz</job-type>
      <durable>true</durable>
      <recover>false</recover>
    </job>
    <trigger>
      <simple>
        <name>TestTrigger</name>
        <group>TestGroup</group>
        <description>测试触发器</description>
        <job-name>TestJob</job-name>
        <job-group>TestGroup</job-group>
        <repeat-count>-1</repeat-count>
        <repeat-interval>2000</repeat-interval>
      </simple>
    </trigger>

    <!--<trigger>
      <cron>
        <name>TestTrigger</name>
        <group>TestGroup</group>
        <description>测试触发器</description>
        <job-name>TestJob</job-name>
        <job-group>TestGroup</job-group>
        <cron-expression>0/2 * * * * ?</cron-expression>
      </cron>
    </trigger>-->
  </schedule>
</job-scheduling-data>
```  

#### 注册Quartz Hosted Service和TestJob  
``` C# 
.ConfigureServices((hostContext, services) =>
{
    services.AddLogging();
    //services.AddHostedService<TimedHostedService>();

    services.AddSingleton<IJobFactory, JobFactory>();
    services.AddSingleton(provider =>
    {
        var option = new QuartzOption(hostContext.Configuration);
        var sf = new StdSchedulerFactory(option.ToProperties());
        var scheduler = sf.GetScheduler().Result;
        scheduler.JobFactory = provider.GetService<IJobFactory>();
        return scheduler;
    });
    services.AddHostedService<QuartzService>();
    services.AddSingleton<TestJob, TestJob>();
})
```

#### 查看运行结果
![](https://github-1251498502.cos.ap-chongqing.myqcloud.com/Stack.Notepads/Notebook.201904260005.png)  

#### 补充说明 
Generic Service默认的环境是Production，如果想使用Development环境，可以在项目属性的Debug页签中添加环境变量来实现。    
![](https://github-1251498502.cos.ap-chongqing.myqcloud.com/Stack.Notepads/Notebook.201904260007.png)    

#### 源码地址 ：
https://github.com/ErikXu/.NetCoreTips/tree/master/HostedService.Quartz  

#### 便捷使用：
https://www.nuget.org/packages/Quartz.HostedService   
https://github.com/ErikXu/Quartz.HostedService   

### 特别说明： 该文章收录于.NET Core跨平台公众号
### 原文地址：https://mp.weixin.qq.com/s/fmBPYZfN2u5a5DG85ooxkA  






