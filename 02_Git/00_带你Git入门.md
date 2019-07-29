# 带你Git入门

> 撰稿人：邹强  
> 基本简介：Git的入门课程  
> 作成时间：2018-8-21  
> 修改时间：2019-7-3  

## 课程目录

1. Git下载与安装启动
2. Git生命周期
3. Git基本名词
4. 第一次使用Git
5. 工作中基本命令
6. 附：Linux最基本命令

### 1.Git下载与安装启动

> 1. 下载
> [官方下载地址](https://git-scm.com/downloads)
> （也可以在**readme.md**文件里找到百度云的共享链接）
> 2. 安装
> 一路选择默认下一步安装即可
> 3. 启动
> 在需要使用Git的目录下，点击右键选择`Git Bash Here`

### 2.Git生命周期

![文件生存周期](./img/01-1.jpg)

### 3.Git基本名词

1. 名词解释
    * 工作区：工作目录内容，用户做的所有改动默认处于工作区
    * 暂存区：提交仓库前的临时区域,`git add a.txt` 可以将文件(a.txt)提交到暂存区
    * 仓库区：最终本地仓库区域
    * 分支：代码的平行世界管理利器
        > 假设项目开发过程中，需要临时并行开发一个feature, 我们就可以使用分支功能。  
        > 专门为该feature开辟一个分支，该feature的开发都在新分支上，  
        > 这样中途开发的半成品，不会影响主线的开发，在该feature完成的时候，可以将分支合并到主线。
    * 拉取(pull): 从远程仓库获取最新数据，并且与本地自动合并
    * 获取(fetch):从远程仓库获取最新数据，但并不与本地全并
    * 变基(rebase):改变一条分支的「基点」,使原分支从指定的地方（commit）重新长出来。

2. Git状态定义
    * unstaged：未进入暂存区（处于工作区）的内容
        * untracked : 新增文件
        * modified ：被修改的内容
    * staged：已经进入暂存区的改动
    * commited：已经进入仓库的内容

### 4.第一次使用命令

1. 本地创建仓库
    > `git init`
2. 从服务器上Clone一个仓库
    > `git clone ssh://user@domain.com/repo.git`
    > `git clone --depth 20 "git@github.com:pettypower/SJY.git"` 克隆默认分支最新20个版本（忽略更早历史版本）
    > `git clone --depth 20 -b zouq_html "git@github.com:pettypower/SJY.git"` 克隆指定分支[zouq_html]最新20个版本（忽略更早历史版本）
3. 设置全局用户名
    > `git config --global user.name "zq"`
4. 设置全局邮箱
    > `git config --global user.email "zq@chiquan-jx.com"`
5. 创建SSH公私匙对
    > `ssh-keygen -t rsa -C "zq@chiquan-jx.com"`
6. 设置远程仓库简称为`origin`
    > `git remote add origin git@github.com:zq/SJY.git`
    > `git remote set-url origin git@github.com:zq/SJY.git` 更新远程仓库地址
    > `git remote -v` 查看远程仓库

### 5.Git基本命令

1. 查看最新状态
    > `git status`

2. 提交到暂存区：将改动从 unsta ged --> staged
    > `git add nFile.txt`  提交指定文件
    > `git add .`  提交所有文件

3. 提交到仓库区
    > * `git commit -m "some comment"` 此操作会将暂存区的改动提交
    > * `git commit -am "some comment2"`此操作会将工作区，暂存区的改动一并提交

4. 推送到远程仓库
    > `git push -u origin master` 推送到远程仓库`origin`  
    > 注：初始化时曾设置好的远程地址略称`origin`的`master`分支

### 6.附：Linux 最基本命令

> 1. 进入指定全路径
> `cd /c/user/my_project`
> 2. 进入某一下级路径(当前路径下存在的文件夹)
> `cd test`
> 3. 进入上一级路径
> `cd ..`
> 4. 查看当前所在路径
> `pwd`
