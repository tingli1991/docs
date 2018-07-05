using O2S.Components.PDFRender4NET;
using System;
using System.Collections.Generic;
using System.Drawing;
using System.Drawing.Imaging;
using System.IO;
using System.Linq;

namespace PdfToBitmap.Utils
{
    /// <summary>
    /// 图片处理工具
    /// </summary>
    public class BitmapUtils
    {
        /// <summary>
        /// 将PDF文档转换为图片的方法
        /// </summary>
        /// <param name="sourceFileName">PDF文件路径(全路径)</param>
        /// <param name="outputPath">图片输出路径</param>
        /// <param name="fileName">生成图片的名字（格式：xxx.jpg）</param>
        /// <param name="isHorizontalMerger">是否是水品合并</param>
        public static void PdfToImage(string sourceFileName, string outputPath, string fileName, bool isHorizontalMerger = false)
        {
            //打开pdf
            PDFFile pdfFile = PDFFile.Open(sourceFileName);

            //检查是否存在输出目录，不存在则添加
            if (!Directory.Exists(outputPath))
                Directory.CreateDirectory(outputPath);

            Bitmap newBitmap = null;
            var bitmapList = new List<Bitmap>();
            for (int i = 1; i <= pdfFile.PageCount; i++)
            {
                var index = i - 1;
                Bitmap pageImage = pdfFile.GetPageImage(index, 100);
                bitmapList.Add(pageImage);
            }

            var path = Path.Combine(outputPath, fileName);
            newBitmap = isHorizontalMerger ? MergerHorizontal(bitmapList) : MergerVertical(bitmapList);
            newBitmap.Save(path, ImageFormat.Jpeg);
            newBitmap.Dispose();
            pdfFile.Dispose();
        }

        /// <summary>
        /// 垂直合成图片
        /// </summary>
        /// <param name="maps">需要合成的图片列表</param>
        /// <returns></returns>
        public static Bitmap MergerVertical(List<Bitmap> maps)
        {
            if (maps == null || !maps.Any())
                throw new ArgumentNullException("参数异常，请传入需要合成的图片");

            //创建要显示的图片对象,根据参数的个数设置宽度
            var maxWidth = maps.Max(e => e.Width);//图片列表中最大宽度
            var maxHeight = maps.Max(e => e.Height);//图片列表中最大高度

            var backgroudImg = new Bitmap(maxWidth, maxHeight * maps.Count);
            Graphics g = Graphics.FromImage(backgroudImg);
            g.Clear(Color.White);//清除画布,背景设置为白色
            for (int i = 0; i < maps.Count; i++)
            {
                var img = maps[i];
                var with = img.Width;
                var sourceX = 0;//原点x坐标
                var sourceY = img.Height * i;//原点Y坐标
                var destinationX = img.Width;//终点X坐标
                var destinationY = img.Height;//终点Y坐标

                //设置画布显示的分倍率
                g.CopyFromScreen(sourceX, sourceY, destinationX, destinationY, img.Size);

                //更具坐标填充画布
                g.DrawImage(img, sourceX, sourceY, destinationX, destinationY);
            }
            g.Dispose();
            return backgroudImg;
        }

        /// <summary>
        /// 水平合并图片
        /// </summary>
        /// <param name="maps">需要合成的图片列表</param>
        /// <returns></returns>
        private static Bitmap MergerHorizontal(List<Bitmap> maps)
        {
            if (maps == null || !maps.Any())
                throw new ArgumentNullException("参数异常，请传入需要合成的图片");

            //创建要显示的图片对象,根据参数的个数设置宽度
            var maxWidth = maps.Max(e => e.Width);//图片列表中最大宽度
            var maxHeight = maps.Max(e => e.Height);//图片列表中最大高度

            var backgroudImg = new Bitmap(maxWidth * maps.Count, maxHeight);
            Graphics g = Graphics.FromImage(backgroudImg);

            g.Clear(Color.White);//清除画布,背景设置为白色
            for (int i = 0; i < maps.Count; i++)
            {
                var img = maps[i];
                var with = img.Width;
                var sourceX = img.Width * i;//原点x坐标
                var sourceY = 0;//原点Y坐标
                var destinationX = img.Width;//终点X坐标
                var destinationY = img.Height;//终点Y坐标

                //设置画布显示的分倍率
                g.CopyFromScreen(sourceX, sourceY, destinationX, destinationY, img.Size);

                //更具坐标填充画布
                g.DrawImage(img, sourceX, sourceY, destinationX, destinationY);
            }
            g.Dispose();
            return backgroudImg;
        }
    }
}
