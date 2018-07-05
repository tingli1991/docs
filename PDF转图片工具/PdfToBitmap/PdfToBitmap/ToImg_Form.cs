using PdfToBitmap.Utils;
using System;
using System.Collections.Generic;
using System.IO;
using System.Windows.Forms;

namespace PdfToBitmap
{
    /// <summary>
    /// 
    /// </summary>
    public partial class Form_PdfToImage : Form
    {
        /// <summary>
        /// 导出目录
        /// </summary>
        private static readonly string FileDir = Path.Combine(AppDomain.CurrentDomain.BaseDirectory, "Files", "Images");

        /// <summary>
        /// 构造函数
        /// </summary>
        public Form_PdfToImage()
        {
            InitializeComponent();
            InitMergerType();
            btn_exportDir.Enabled = false;
        }

        /// <summary>
        /// 初始化合并方式
        /// </summary>
        private void InitMergerType()
        {
            var mergerTypeList = new List<object>()
            {
                new { Name="垂直合并",Index=1},
                new { Name="水平合并",Index=2}
            };
            cmb_MergerType.ValueMember = "Index";
            cmb_MergerType.DisplayMember = "Name";
            cmb_MergerType.DataSource = mergerTypeList;
        }


        /// <summary>
        /// 浏览按钮的点击事件
        /// </summary>
        /// <param name="sender"></param>
        /// <param name="e"></param>
        private void Btn_InputFile_Click(object sender, EventArgs e)
        {
            var fileDialog = new OpenFileDialog
            {
                Multiselect = true,
                Title = "请选择文件",
                Filter = "所有PDF文件(*.pdf)|*.pdf"
            };

            DialogResult dr = fileDialog.ShowDialog();
            if (dr == DialogResult.OK)
            {
                string[] names = fileDialog.FileNames;
                txt_ResText.Text += $"已选择文件：\r\n";
                foreach (string filePath in names)
                {
                    txt_ResText.Text += $" {filePath}\r\n";
                }

                txt_ResText.Text += $"\r\n开始转换：\r\n";
                var mergerType = (int)cmb_MergerType.SelectedValue;
                foreach (string filePath in names)
                {
                    try
                    {
                        var fileName = $"{Path.GetFileNameWithoutExtension(filePath)}.jpg";
                        switch (mergerType)
                        {
                            case 1://水平转换
                                BitmapUtils.PdfToImage(filePath, FileDir, fileName);
                                break;
                            case 2://垂直转换
                                BitmapUtils.PdfToImage(filePath, FileDir, fileName, true);
                                break;
                        }
                        txt_ResText.Text += $" 成功文件：{filePath}\r\n";
                    }
                    catch (Exception ex)
                    {
                        txt_ResText.Text += $" 文件：{filePath}转换发生异常：\r\n";
                        txt_ResText.Text += $" {ex}\r\n";
                    }
                }
                btn_exportDir.Enabled = true;
                btn_exportDir.Click += Btn_exportDir_Click;
            }
        }

        /// <summary>
        /// 打开到处的文件目录
        /// </summary>
        /// <param name="sender"></param>
        /// <param name="e"></param>
        private void Btn_exportDir_Click(object sender, EventArgs e)
        {
            System.Diagnostics.Process.Start("explorer.exe", FileDir);
        }
    }
}