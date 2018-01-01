【Git使用小结】
===
## 1.安装Git  
### 1.1 安装地址：(https://git-scm.com/)  
### 1.1.1 安装成功后包含如下三个软件：  
  Git Bash   常用的命令行工具  
  Git CMD  
  Git GUI    带UI界面的工具  
## 2.配置全局变量
### 2.1 git config --global user.name "tingli1991"
### 2.2 git config --global user.email "litingxian@live.cn"
#3.常用的Git命令
3.1 git status              查看当前版本的状态
3.2 git clone [项目地址]    克隆对应的项目
3.3 git vim [文件名]        编辑或者创建文件(与linux的命令相同)
3.4 git add [文件名]        将工作去代码添加到暂存区
3.5 git commit [文件名]     将暂存区代码添加到本地maser分支
3.6 git push [文件名]       将本地master分支添加到远程maser分支
3.7 git rm [文件名]         删除指定文件
3.8 git mkdir [目录名]      新建文件夹
#4.git 提交代码的过程
4.1 工作区             该区域主要是我们本机的代码工作区域
4.2 暂存区             将代码由工作去添加到缓存区使用git add 命令完成
4.3 本地master分支     将缓存区代码添加到master分支使用 git commit 命令完成
4.4 远程master分支     将本地master分支添加到远程master分支则使用 git push 命令完成
