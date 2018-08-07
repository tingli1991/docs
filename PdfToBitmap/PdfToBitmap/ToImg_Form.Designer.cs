namespace PdfToBitmap
{
    partial class Form_PdfToImage
    {
        /// <summary>
        /// 必需的设计器变量。
        /// </summary>
        private System.ComponentModel.IContainer components = null;

        /// <summary>
        /// 清理所有正在使用的资源。
        /// </summary>
        /// <param name="disposing">如果应释放托管资源，为 true；否则为 false。</param>
        protected override void Dispose(bool disposing)
        {
            if (disposing && (components != null))
            {
                components.Dispose();
            }
            base.Dispose(disposing);
        }

        #region Windows 窗体设计器生成的代码

        /// <summary>
        /// 设计器支持所需的方法 - 不要修改
        /// 使用代码编辑器修改此方法的内容。
        /// </summary>
        private void InitializeComponent()
        {
            this.btn_InputFile = new System.Windows.Forms.Button();
            this.groupBox1 = new System.Windows.Forms.GroupBox();
            this.btn_exportDir = new System.Windows.Forms.Button();
            this.label1 = new System.Windows.Forms.Label();
            this.cmb_MergerType = new System.Windows.Forms.ComboBox();
            this.grp_ResText = new System.Windows.Forms.GroupBox();
            this.txt_ResText = new System.Windows.Forms.TextBox();
            this.groupBox1.SuspendLayout();
            this.grp_ResText.SuspendLayout();
            this.SuspendLayout();
            // 
            // btn_InputFile
            // 
            this.btn_InputFile.Font = new System.Drawing.Font("宋体", 9F, System.Drawing.FontStyle.Bold, System.Drawing.GraphicsUnit.Point, ((byte)(134)));
            this.btn_InputFile.Location = new System.Drawing.Point(235, 22);
            this.btn_InputFile.Name = "btn_InputFile";
            this.btn_InputFile.Size = new System.Drawing.Size(144, 23);
            this.btn_InputFile.TabIndex = 0;
            this.btn_InputFile.Text = "浏览并转换";
            this.btn_InputFile.UseVisualStyleBackColor = true;
            this.btn_InputFile.Click += new System.EventHandler(this.Btn_InputFile_Click);
            // 
            // groupBox1
            // 
            this.groupBox1.Controls.Add(this.btn_exportDir);
            this.groupBox1.Controls.Add(this.label1);
            this.groupBox1.Controls.Add(this.cmb_MergerType);
            this.groupBox1.Controls.Add(this.btn_InputFile);
            this.groupBox1.Location = new System.Drawing.Point(12, 12);
            this.groupBox1.Name = "groupBox1";
            this.groupBox1.Size = new System.Drawing.Size(559, 70);
            this.groupBox1.TabIndex = 1;
            this.groupBox1.TabStop = false;
            this.groupBox1.Text = "操作";
            // 
            // btn_exportDir
            // 
            this.btn_exportDir.Font = new System.Drawing.Font("宋体", 9F, System.Drawing.FontStyle.Bold, System.Drawing.GraphicsUnit.Point, ((byte)(134)));
            this.btn_exportDir.Location = new System.Drawing.Point(401, 22);
            this.btn_exportDir.Name = "btn_exportDir";
            this.btn_exportDir.Size = new System.Drawing.Size(144, 23);
            this.btn_exportDir.TabIndex = 3;
            this.btn_exportDir.Text = "打开导出目录";
            this.btn_exportDir.UseVisualStyleBackColor = true;
            // 
            // label1
            // 
            this.label1.AutoSize = true;
            this.label1.Font = new System.Drawing.Font("宋体", 9F, System.Drawing.FontStyle.Bold, System.Drawing.GraphicsUnit.Point, ((byte)(134)));
            this.label1.Location = new System.Drawing.Point(13, 27);
            this.label1.Name = "label1";
            this.label1.Size = new System.Drawing.Size(70, 12);
            this.label1.TabIndex = 2;
            this.label1.Text = "合并方式：";
            // 
            // cmb_MergerType
            // 
            this.cmb_MergerType.FormattingEnabled = true;
            this.cmb_MergerType.Location = new System.Drawing.Point(84, 24);
            this.cmb_MergerType.Name = "cmb_MergerType";
            this.cmb_MergerType.Size = new System.Drawing.Size(121, 20);
            this.cmb_MergerType.TabIndex = 1;
            // 
            // grp_ResText
            // 
            this.grp_ResText.Controls.Add(this.txt_ResText);
            this.grp_ResText.Location = new System.Drawing.Point(12, 83);
            this.grp_ResText.Name = "grp_ResText";
            this.grp_ResText.Size = new System.Drawing.Size(559, 299);
            this.grp_ResText.TabIndex = 2;
            this.grp_ResText.TabStop = false;
            this.grp_ResText.Text = "输出";
            // 
            // txt_ResText
            // 
            this.txt_ResText.Location = new System.Drawing.Point(15, 20);
            this.txt_ResText.Multiline = true;
            this.txt_ResText.Name = "txt_ResText";
            this.txt_ResText.Size = new System.Drawing.Size(530, 259);
            this.txt_ResText.TabIndex = 3;
            // 
            // Form_PdfToImage
            // 
            this.AutoScaleDimensions = new System.Drawing.SizeF(6F, 12F);
            this.AutoScaleMode = System.Windows.Forms.AutoScaleMode.Font;
            this.ClientSize = new System.Drawing.Size(583, 396);
            this.Controls.Add(this.grp_ResText);
            this.Controls.Add(this.groupBox1);
            this.MaximizeBox = false;
            this.MinimizeBox = false;
            this.Name = "Form_PdfToImage";
            this.ShowInTaskbar = false;
            this.StartPosition = System.Windows.Forms.FormStartPosition.CenterScreen;
            this.Text = "PDF转图片";
            this.groupBox1.ResumeLayout(false);
            this.groupBox1.PerformLayout();
            this.grp_ResText.ResumeLayout(false);
            this.grp_ResText.PerformLayout();
            this.ResumeLayout(false);

        }

        #endregion
        private System.Windows.Forms.Button btn_InputFile;
        private System.Windows.Forms.GroupBox groupBox1;
        private System.Windows.Forms.ComboBox cmb_MergerType;
        private System.Windows.Forms.Label label1;
        private System.Windows.Forms.GroupBox grp_ResText;
        private System.Windows.Forms.TextBox txt_ResText;
        private System.Windows.Forms.Button btn_exportDir;
    }
}

