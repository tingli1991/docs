using Microsoft.AspNetCore.Mvc;
using System;

namespace Exceptionless.WebAPITest.Controllers
{
    /// <summary>
    /// 
    /// </summary>
    [Route("api/Values"), ApiController]
    public class ValuesController : ControllerBase
    {
        /// <summary>
        ///  GET api/values/error
        /// </summary>
        /// <returns></returns>
        [HttpGet("Error")]
        public ActionResult<string> Error()
        {
            try
            {
                throw new Exception($"Exceptionless异常测试：{DateTime.Now.ToString("yyyyMMddHHmmss")}");
            }
            catch (Exception ex)
            {
                //核心代码：向Exceptionless提交异常信息,下面三种写法相同
                //ex.ToExceptionless().Submit();//原生写法
                //Log4Less.Submit(ex, new { Id = 1, Name = "张三" }, "异常扩展方法测试");//扩展写法
                ex.Submit(new { Id = 1, Name = "张三" }, "异常扩展方法测试");//扩展写法(推荐使用)
            }
            return "";
        }

        /// <summary>
        ///  GET api/values/info
        /// </summary>
        /// <returns></returns>
        [HttpGet("Info")]
        public ActionResult<string> Info()
        {
            Log4Less.Info($"Exceptionless信息测试：{DateTime.Now.ToString("yyyyMMddHHmmss")}", new { Id = 1, Name = "张三" }, "信息扩展方法测试");
            return "";
        }
    }
}