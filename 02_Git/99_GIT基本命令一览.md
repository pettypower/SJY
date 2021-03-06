# GIT基本命令一览

## Linux 基本命令

  1. `ls -a` 查看当前目录文档
  2. `ls -ah` 查看全部文档（含隐藏）
  3. `pwd` 查看当前目录
  4. `mkdir` 创建文件夹
  5. `cd ..` 返回上一级
  6. `cd <子目录名>` 进入指定子目录

## 创建

  1. `git init` 创建新的Git本地
  2. `git clone ssh://user@domain.com/repo.git`
  3. `git config --global user.name "zouqiang"`
  4. `git config --global user.email "qiang.zou@chiquan-jx.com"`
  5. `ssh-keygen -t rsa -C "qiang.zou@chiquan-jx.com"`

## 本地修改

  1. `git status` 显示工作路径下已修改的文件
  2. `git diff` 显示与上次提交版本的不同
  3. `git add` 把当前所有修改添加到下次提交中
  4. `git add -p<file>` 把某个文件的修改添加到下次提交中
  5. `git commit -a` 提交本地所有修改
  6. `git commit` 提交之前已标记的变化
  7. `git commit -m 'message here'` 附加消息提交
  8. `git commit --ament` 修改上次提交
  9. `git stash`
  10. `git checkout branch2` 切换到branch2
  11. `git stash pop`

## 搜索

  1. `git grep "Hello"` 从当前目录的所有文件中查找文本内容
  2. `git grep "Hello" v2.5` 在某一版本中搜索文本

## 提交历史

  1. `git log` 显示所有提交记录(hash,作者，message,时间)
  2. `git log --oneline` 显示所有提交记录(hash,message)
  3. `git log --author="username"` 显示某个用户的所有提交
  4. `git log -p<file>` 显示某个文件的所有修改
  5. `git blame<file>` 谁，什么时间，修改了指定文件什么内容

## 分支与标签

  1. `git branch` 列出所有的分支
  2. `git checkout <branch>` 切换分支
  3. `git checkout -b <branch>` 创建并切换到新分支
  4. `git branch <new-branck>`  基于当前分支创建分支
  5. `git branch --track<new-branch><remote-branch>` 基于远程分支创建新的可追溯的分支
  6. `git branch -d <branch>` 删除本地分支
  7. `git tag<tag-name>` 给当前版本打标签

## 更新与发布

  1. `git remote -v` 列出当前配置的远程端
  2. `git remote show<remote>` 显示远程端的信息
  3. `git remote add<remote><url>` 添加新的远程端
  4. `git fetch <remote>` 下载远程端版本，但不合并到HEAD中
  5. `git remote pull <remote><url>` 下载远程端版本，并自动与HEAD版本

## 合并

  1. `git pull origin master` 将远程端版本合并到本地版本中
  2. `git push remote <remote><branch>` 将本地版本发布到远程端
  3. `git push <remote> --delete <branch>` 删除远程端分支
  4. `git push --tags` 发布标签

## 合并与重置

  1. `git merge <branch>` 并分支合并到HEAD中
  2. `git rebase <branch>` 将当前HEAD版本重置到分支中
  3. `git rebase --abort` 退出重置
  4. `git rebase --continue` 解决冲突后继续重置
  5. `git mergetool` 使用配置好的merge tool 解决冲突
  6. `git add <resolved-file>`
  7. `git rm <resolved-file>` 在编辑器中手动解决冲突后，标记文件为已解决冲突

## 撤销

  1. `git reset -hard HEAD` 放弃工作目录下的所有修改
  2. `git reset HEAD` 移动缓存区的所有文件
  3. `git checkout HEAD <file>` 放弃某个文件的所有本地修改
  4. `git revert <commit>` 重置一个提交（通过创建一个截然不同的新提交）
  5. `git reset --hard<commit>` 将HEAD重置到指定的版本，并抛弃该版本之后的所有修改
  6. `git reset <commit>` 将HEAD重置到上一次提交的版本，并将之后的修改标准为未添加到缓存区的修改
  7. `git reset --keep <commit>` 将HEAD重置到上一次提交的版本，并保留未提交的本地修改
