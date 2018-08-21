# EF Core学习笔记
##### 1. 使用Nuget安装相应的包
``` C#
Microsoft.EntityFrameworkCore.SqlServer          SqlServer数据库提供程序
Microsoft.EntityFrameworkCore.Tools              数据库建模工具包
Microsoft.VisualStudio.Web.CodeGeneration.Design 生成MVC视图和控制器（可选工具）
```
##### 2. 使用包管理器控制台(PowerShell脚本)
``` C#
Scaffold-DbContext "Server=(localdb)\mssqllocaldb;Database=Blogging;Trusted_Connection=False;" Microsoft.EntityFrameworkCore.SqlServer -OutputDir Models
```

##### 3. 分布式事务使用  
.net 2.0后TransactionScope类的出现，大大的简化了事务的设计。示例代码如下：
**同步写法：**
``` C#
using (TransactionScope scope = new TransactionScope())
{
       _context.ThirdRtAgency.Add(agent);
       _context.ThirdRtReport.Add(report);
       _context.ThirdRtFollowReport.Add(thirdRtFollowReport);
       _context.SaveChanges();
       scope.Complete();//提交事务
}
```  
**异步写法：**  
在异步里面的使用(需要使用TransactionScopeAsyncFlowOption来启用异步)
``` C#
using (var scope = new TransactionScope(TransactionScopeAsyncFlowOption.Enabled))
{
      await _context.ThirdRtAgency.AddAsync(agent);
      await _context.ThirdRtReport.AddAsync(report);
      await _context.ThirdRtFollowReport.AddAsync(thirdRtFollowReport);
      await _context.SaveChangesAsync();
      scope.Complete();//提交事务
}
```
