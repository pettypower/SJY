# Git常用命令讲解

> 撰稿人：邹强  
> 基本简介：Git常用命令讲解  
> 作成时间：2018-8-21  
> 修改时间：2019-8-1

## 1. 本地初始化Git仓库

1. 本地创建仓库
    > `git init`
2. 设置全局用户名
    > `git config --global user.name "你自己的名字，最好是英文"`
3. 设置全局邮箱
    > `git config --global user.email "你自己的邮箱"`
4. 设置远程仓库简称为`origin`
    - 添加一个远程仓库，并命令为origin  
        > `git remote add origin git@github.com:zq/SJY.git`
    - 将远程仓库`origin` 地址更新为 git@github.com:zq/SJY.git  
        > `git remote set-url origin git@github.com:zq/SJY.git`
    - 查看远程仓库地址
        > `git remote -v`  
    
        
5. 创建SSH公私匙对
    > `ssh-keygen -t rsa -C "zq@chiquan-jx.com"`
    - [详细操作参考](05_SSH方式登陆Github.md)
6. 拉取远程最新
    - `git pull --rebase`

## 2. 从服务器上Clone一个仓库

1. 最常用的方式，将与服务器完全同步（包括历史记录）
    > `git clone ssh://user@domain.com/repo.git`
2. 仅克隆最新20个版本（忽略更早历史记录）
    > `git clone --depth 20 "git@github.com:pettypower/SJY.git"`
3. 仅克隆指定分支**zouq_html**最近20个版本
    > `git clone --depth 20 -b zouq_html "git@github.com:pettypower/SJY.git"` 

## 3. 常用命令

1. 查看本地仓库最新状态
    > `git status`
2. 将改动提交到暂存区
    -  提交指定文件
        > `git add nFile.txt`
    -  提交所有文件（不包含新增文件）
        > `git add .`
3. 提交到仓库区
    -  将暂存区的内容提交到仓库区
        > `git commit -m "注释内容"`
    -  将工作区，暂存区的改动一并提交
        > `git commit -am "注释内容"`
3. 拉取远程最新
    - `git pull --rebase`
4. 推送到远程仓库
    - `git push`

## 4. 撤回命令

1. 放弃本地所有修改
    > `git reset --hard HEAD`
2. 放弃工作区修改
    > `git checkout nFile.txt`  
        暂存区有此文件时，工作区恢复成暂存区版本  
        暂存区无此文件时，工作区恢复成仓库版本
3. 放弃暂存区的内容
    > `git reset HEAD`  
    （工作区保持原状）
4. 仓库区版本调整：（实际调整的是仓库区的HEAD）
    - 仓库回退到上一版本（--hard表示工作区与仓库同步，暂存区清空）
        > `git reset --hard HEAD^`
    - 仓库回退到上两版本
        > `git reset --hard HEAD^^`
    - 仓库回退到上5个版本
        > `git reset --hard HEAD~5`
    - 仓库回退到指定版本（可前进，可后退）
        > `git reset --hard 版本号`
    - 仓库回退到指定版本，但工作区，暂存区不受影响
        > `git reset --soft 版本号` 

## 5. 分支管理

1. 创建&切换分支
    - 创建dev分支
        > `git branch dev`
    - 切换到dev分支
        > `git checkout dev`
    - 创建并切换到dev分支
        > `git checkout -b dev` 
2. 查看分支
    - 查看本地分支
        > `git branch` 
    - 查看远程分支
        > `git branch -r`
    - 查看所有分支
        > `git branch -a`
3. 删除分支
    > `git branck -d dev` 
4. 合并(merge)
    - 将 **dev** 分支merge到当前分支
        > `git merge dev` 
    - 将 **dev** 分支merge到当前分支禁用fast-forward模式
        > `git merge --no-ff dev` 
5. 变基(rebase)
    - 将 **dev** 分支rebase到当前分支
        > `git rebase dev`
    - 编辑当前分支的从 **f52c633** 开始历史记录（修改commitlog或合并历史等）
        > - `git rebase -i f52c633` 

> 1. Git鼓励大量使用分支
> 2. 不允许在公共分支（如master）上rebase个人分支

## 6. 日志与对比命令

1. 查看日志
    - 查看log日志(默认格式)
        > `git log --oneline`
    - 查看log日志，指定格式(版本 用户名 日期 日志内容)
        > `git log --graph --pretty="%h %cn %cd %s"`
    - 显示所有提交记录，并显示每闪提交`简略`对比
        > `git log --stat` 
    - 显示所有提交记录（后面追加-3 参数表示显示最新3条记录）
        > `git log` 
    - 显示所有提交记录，并显示每次提交的对比
        > `git log -p` 
2. 查看区别
    - 查看工作区的改动
        > `git diff`  
        仓库区无此文件，工作区 <-对比-> 暂存区  
        仓库区有此文件，工作区 <-对比-> 仓库区
    - 查看暂存区的改动内容
        > `git diff --staged`
    - 查看工作区与版本库最新版本区别
        > `git diff HEAD -- nFile.txt` 
3. 改写最后一次提交注释
    > - `git commit --amend`

## 7. 标签管理

1. 为分支打个标签
    - 为当前分支的最新commit上打 v1.0 标签
        > `git tag v1.0`  
    - 为当前分支的[f52c633]commit上打 v0.9 标签
        > `git tag v0.9 f52c633`
    - 为当前分支的[1094adb]commit上打 v1.0 标签并备注 version 0.1 released
        > `git tag -a v0.1 -m "version 0.1 released" 1094adb`  
2. 查看标签
    - 查看所有标签列表
        > `git tag`
    - 查看 v0.9 标签的明细（包括备注）
        > `git show v0.9` 
3. 删除本地标签（默认标签均为本地）
    > `git tag -d v0.1`
4. 推送标签到远程
    - 推送 v0.1 标签到远程
        > `git push origin v0.1`
    - 推送全部尚未推送标签
        > `git push origin --tags` 
5. 删除远程标签
    - 先删除本地 v0.1 标签
        > `git tag -d v0.1` 
    - 删除远程 v0.1 标签
        > `git push origin :refs/tags/v0.1` 

> 注：标签总是和某个commit挂钩。如果这个commit既出现在master分支，又出现在dev分支，那么在这两个分支上都可以看到这个标签。

## 9. `.gitignore`文件忽略

1. 基本规则
    - `.gitignore`可以放在任何目录中,作用范围是其所处的目录及子目录
    - `git add -f hello.java`强行添加被忽略的文件
    - 忽略只对未跟踪的文件有效, 对于已加入版本库的文件无效
2. Git忽略语法如下
    1. `#` 开始的行会被忽略  
    2. `*.a` 忽略所有以.a为扩展名的文件
    3. `??.a` 忽略所有文件名为两位英文且扩展名为.a的文件
    4. `[abc].java` 忽略所有 a.java, b.java, c.java
    5. `/Name` 忽略当前目录下的Name文件夹,但不包括Name下的子目录
    6. `Name/` 忽略所有叫[Name]的文件夹下所有文件
    7. `doc/*.txt` 忽略文件如doc/notes.txt,但不忽略doc/ser/arch.txt

## 10. 其它

1. 解决中文文件名无法正常显示的问题
    > - `git config --global core.quotepath false`