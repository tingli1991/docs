using Microsoft.AspNetCore.Mvc;
using System;

namespace Exceptionless.WebAPITest.Controllers
{
    /// <summary>
    /// 
    /// </summary>
    [Route("api/[controller]"), ApiController]
    public class ValuesController : ControllerBase
    {
        // GET api/values
        [HttpGet]
        public ActionResult<string> Get()
        {
            try
            {
                throw new Exception("ExceptionDemo 的异常");
            }
            catch (Exception ex)
            {
                //核心代码：向Exceptionless提交异常信息
                ex.ToExceptionless().Submit();
            }
            return "";
        }
    }
}