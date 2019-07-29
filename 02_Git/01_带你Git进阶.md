# 带你Git进阶

> 撰稿人：邹强  
> 基本简介：Git的进阶课程  
> 作成时间：2018-8-21  
> 修改时间：2019-7-4

## 课程目录

1. 撤回命令
2. 日志与对比命令
3. 分支管理
4. 删除与移动命令
5. 标签管理

### 1. 撤回命令

1. 放弃工作区修改
    > `git checkout nFile.txt`  
    >  1. 暂存区有此文件时，工作区恢复成暂存区版本
    >  2. 暂存区无此文件时，工作区恢复成仓库版本

2. 放弃暂存区的内容
    > - `git reset HEAD nFile.txt` （工作区保持原状）

3. 撤回仓库区内容：（实际调整的是仓库区的HEAD）
    > - `git reset --hard HEAD^` 将仓库退回到上一个版本（HEAD^^则表示退回到上两个版本，并同步到本地工作区，HEAD~5表示退回到上5个版本）
    > - `git reflog` 命令历史
    > - `git reset --hard 版本号` 将仓库的HEAD指向某版本（可前进，可后退）

### 2.日志与对比命令

1. 查看日志
    > - `git log` 显示所有提交记录（后面追加-3 参数表示显示最新3条记录）
    > - `git log -p` 显示所有提交记录，并显示每次提交的对比
    > - `git log --stat` 显示所有提交记录，并显示每闪提交`简略`对比
    > - `git log --pretty=oneline` 显示所有提交记录，可以自定义格式`oneline, format="%h - %an"`,如果不指定格式，则与`git log`显示效果一致
    > - `git log --graph --pretty="%h %cn %cd %s" --date=iso` 显示的日志格式：版本 用户名 日期 日志内容

2. 查看区别
    > - `git diff` 累积在unstaged(工作区)状态的改动
    >    1. 仓库区无此文件，工作区 <-对比-> 暂存区
    >    2. 仓库区有此文件，工作区 <-对比-> 仓库区
    > - `git diff --staged` 累积在staged状态的改动
    > - `git diff HEAD -- nFile.txt` 查看工作区与版本库最新版本区别（nFile.txt文件）

3. 改写提交
    > - `git commit --amend`

### 3. 分支管理

1. 创建&切换分支
    > - `git branch dev` 创建dev分支
    > - `git checkout dev` 切换到dev分支
    > - `git checkout -b dev` 创建并切换到dev分支
2. 查看分支
    > - `git branch` 查看所有分支，当前分支会标*号
3. 删除分支
    > - `git branck -d dev` 删除指定分支
4. 合并(merge)
    > - `git merge dev` 将 **dev** 分支merge到当前分支
    > - `git merge --no-ff dev` 禁用fast-forward模式
5. 变基(rebase)
    > - `git rebase dev` 将 **dev** 分支rebase到当前分支
    > - `git rebase -i f52c633` 编辑当前分支的从 **f52c633** 开始历史记录（修改commitlog或合并历史等）

Git鼓励大量使用分支

### 4. 删除与移动命令

1. 删除文件
    > - `git rm nFile.txt` 删除`nFile.txt`并提交到暂存区
    > - `git rm --cached nFile.txt` 删除暂存区但保留工作区的`nFile.txt`文件
    > - `git rm log/\*.log` 删除log目录下所有后缀为log的文件
    > - `git rm \*~` 删除所有以~结尾的文件

2. 移动文件（可以用来重命名）
    > - `git mv nFile.txt nFile2.txt` 将`nFile.txt` 重命名为`nFile2.txt`文件

### 5. 标签管理

1. 为分支打个标签
    > - `git tag v1.0`  
    >   为当前分支的最新commit上打 **tag v1.0** 标签
    > - `git tag v0.9 f52c633`  
    >   为当前分支的[f52c633]commit上打 **tag v0.9** 标签
    > - `git tag -a v0.1 -m "version 0.1 released" 1094adb`  
    >   为当前分支的[1094adb]commit上打 **v0.1** 标签并备注 **version 0.1 released**
2. 查看标签
    > - `git tag`  查看所有标签列表
    > - `git show v0.9` 查看 **v0.9** 标签的明细（包括备注）
3. 删除本地标签（默认标签均为本地）
    > - `git tag -d v0.1` 删除 **v0.1** 标签
4. 推送标签到远程
    > - `git push origin v0.1` 推送 **v0.1** 标签到远程
    > - `git push origin --tags` 推送全部尚未推送标签
5. 删除远程标签
    > - `git tag -d v0.1` 先删除本地 **v0.1** 标签
    > - `git push origin :refs/tags/v0.1` 删除远程 **v0.1** 标签

注：标签总是和某个commit挂钩。如果这个commit既出现在master分支，又出现在dev分支，那么在这两个分支上都可以看到这个标签。

### 6. 其它

1. 解决中文文件名无法正常显示的问题
    > - `git config --global core.quotepath false`
2. `.gitignore`文件忽略
    1. 基本规则
        > - `.gitignore`可以放在任何目录中,作用范围是其所处的目录及子目录
        > - `git add -f hello.java`强行添加被忽略的文件
        > - 忽略只对未跟踪的文件有效, 对于已加入版本库的文件无效
    2. Git忽略语法如下
        1. `#` 开始的行会被忽略  
        2. `*.a` 忽略所有以.a为扩展名的文件
        3. `??.a` 忽略所有文件名为两位英文且扩展名为.a的文件
        4. `[abc].java` 忽略所有 a.java, b.java, c.java
        5. `/Name` 忽略当前目录下的Name文件夹,但不包括Name下的子目录
        6. `Name/` 忽略所有叫[Name]的文件夹下所有文件
        7. `doc/*.txt` 忽略文件如doc/notes.txt,但不忽略doc/ser/arch.txt不被忽略

### 原汁原味的解释

> start a working area (see also: git help tutorial)

   1. `clone`      Clone a repository into a new directory
   2. `init`       Create an empty Git repository or reinitialize an existing one

> work on the current change (see also: git help everyday)

   1. `add`        Add file contents to the index
   2. `mv`         Move or rename a file, a directory, or a symlink
   3. `reset`      Reset current HEAD to the specified state
   4. `rm`         Remove files from the working tree and from the index

> examine the history and state (see also: git help revisions)

   1. `bisect`     Use binary search to find the commit that introduced a bug
   2. `grep`       Print lines matching a pattern
   3. `log`        Show commit logs
   4. `show`       Show various types of objects
   5. `status`     Show the working tree status

> grow, mark and tweak your common history

   1. `branch`     List, create, or delete branches
   2. `checkout`   Switch branches or restore working tree files
   3. `commit`     Record changes to the repository
   4. `diff`       Show changes between commits, commit and working tree, etc
   5. `merge`      Join two or more development histories together
   6. `rebase`     Reapply commits on top of another base tip
   7. `tag`        Create, list, delete or verify a tag object signed with GPG

> collaborate (see also: git help workflows)

   1. `fetch`      Download objects and refs from another repository
   2. `pull`       Fetch from and integrate with another repository or a local branch
   3. `push`       Update remote refs along with associated objects  

更多命令参考
[Git常用命令详解](https://www.jianshu.com/p/360bdda5157f "Git")
[Pro Git book](https://www.git-scm.com/book/en/v2 "Pro Git book")
