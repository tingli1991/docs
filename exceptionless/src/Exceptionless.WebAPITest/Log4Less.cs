using Exceptionless.Logging;
using System;

namespace Exceptionless.WebAPITest
{
    /// <summary>
    /// ExceptionLess帮助类
    /// </summary>
    public static class Log4Less
    {
        /// <summary>
        /// 输出跟踪
        /// </summary>
        /// <param name="message">日志信息</param>
        /// <param name="tags">标签列表</param>
        public static void Trace(string message, params string[] tags)
        {
            Trace(message, null, tags);
        }

        /// <summary>
        /// 输出跟踪
        /// </summary>
        /// <param name="message">日志信息</param>
        /// <param name="tags">标签列表</param>
        public static void Trace(string message, object data, params string[] tags)
        {
            Submit(message, data, LogLevel.Trace, tags);
        }

        /// <summary>
        /// 输出调试
        /// </summary>
        /// <param name="message">日志信息</param>
        /// <param name="tags">标签列表</param>
        public static void Debug(string message, params string[] tags)
        {
            Debug(message, null, tags);
        }

        /// <summary>
        /// 输出调试
        /// </summary>
        /// <param name="message">日志信息</param>
        /// <param name="data"></param>
        /// <param name="tags">标签列表</param>
        public static void Debug(string message, object data, params string[] tags)
        {
            Submit(message, data, LogLevel.Debug, tags);
        }

        /// <summary>
        /// 输出信息
        /// </summary>
        /// <param name="message">日志信息</param>
        /// <param name="tags">标签列表</param>
        public static void Info(string message, params string[] tags)
        {
            Info(message, null, tags);
        }

        /// <summary>
        /// 输出信息
        /// </summary>
        /// <param name="message">日志信息</param>
        /// <param name="data"></param>
        /// <param name="tags">标签列表</param>
        public static void Info(string message, object data, params string[] tags)
        {
            Submit(message, data, LogLevel.Info, tags);
        }

        /// <summary>
        /// 输出警告
        /// </summary>
        /// <param name="message">日志信息</param>
        /// <param name="tags">标签列表</param>
        public static void Warn(string message, params string[] tags)
        {
            Warn(message, null, tags);
        }

        /// <summary>
        /// 输出警告
        /// </summary>
        /// <param name="message">日志信息</param>
        /// <param name="data"></param>
        /// <param name="tags">标签列表</param>
        public static void Warn(string message, object data, params string[] tags)
        {
            Submit(message, data, LogLevel.Warn, tags);
        }

        /// <summary>
        /// 输出警告
        /// </summary>
        /// <param name="message">日志信息</param>
        /// <param name="obj"></param>
        /// <param name="tags">标签列表</param>
        public static void Error(string message, params string[] tags)
        {
            Error(message, null, tags);
        }

        /// <summary>
        /// 输出警告
        /// </summary>
        /// <param name="message">日志信息</param>
        /// <param name="data"></param>
        /// <param name="tags">标签列表</param>
        public static void Error(string message, object data, params string[] tags)
        {
            Submit(message, data, LogLevel.Error, tags);
        }

        /// <summary>
        /// 提交异常
        /// </summary>
        /// <param name="message">日志信息</param>
        /// <param name="obj"></param>
        /// <param name="tags">标签列表</param>
        public static void Submit(this Exception ex, params string[] tags)
        {
            Submit(ex, null, tags);
        }

        /// <summary>
        /// 提交异常
        /// </summary>
        /// <param name="message">日志信息</param>
        /// <param name="data">需要提交的日志数据，例如：请求参数等关键数据</param>
        /// <param name="tags">标签列表</param>
        public static void Submit(this Exception ex, object data, params string[] tags)
        {
            ex.ToExceptionless().AddObject(data).AddTags(tags).Submit();
        }

        /// <summary>
        /// 提交日志
        /// </summary>
        /// <param name="message"></param>
        /// <param name="data"></param>
        /// <param name="level"></param>
        /// <param name="tags"></param>
        public static void Submit(string message, object data, LogLevel level, params string[] tags)
        {
            if (message == null)
                return;

            var eventBuilder = ExceptionlessClient.Default.CreateLog(message, level);
            if (data != null)
            {
                eventBuilder.AddObject(data);
            }
            if (tags?.Length > 0)
            {
                eventBuilder.AddTags(tags);
            }
            eventBuilder.Submit();
        }
    }
}