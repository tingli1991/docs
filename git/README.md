## 【Git使用小结】

#### 1.安装Git
##### 1.1 安装地址：(https://git-scm.com/)
##### 1.1.1 安装成功后包含如下三个软件：
        Git Bash   常用的命令行工具
        Git CMD    命令行窗口（不常用）
        Git GUI    带UI界面的工具

#### 2.配置全局变量
        git config --global user.name "tingli1991"              设置的用户名
        git config --global user.email "litingxian@live.cn"     设置邮件地址
        
#### 3.常用的Git命令
        git status                查看当前版本的状态  
        git clone [项目地址]       克隆对应的项目  
        git vim [文件名]           编辑或者创建文件(与linux的命令相同)  
        git add [文件名]           将工作去代码添加到暂存区  
        git commit [文件名]        将暂存区代码添加到本地maser分支  
        git push [文件名]          将本地master分支添加到远程maser分支  
        git rm [文件名]            删除指定文件  
        git mkdir [目录名]         新建文件夹
        git                        命令行翻页退出
        ctrl + c                   强制退出
        pwd                        查看当前工作目录
        git pull                   更新远程服务器代码
        git log                    查看提交日志(当前版本之前的日志)
        git reset --hard [版本号]  版本回退（更新到指定的版本）
        git reflog                查看完整的版本日志
        
#### 4.git 提交代码的过程
        [工作区]             该区域主要是我们本机的代码工作区域
        [暂存区]             将代码由工作去添加到缓存区使用git add 命令完成
        [本地master分支]     将缓存区代码添加到master分支使用 git commit 命令完成
        [远程master分支]     将本地master分支添加到远程master分支则使用 git push 命令完成
