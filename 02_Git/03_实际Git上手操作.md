# Git 操作流程

## 创建版本库

 1. mkdir learngit
 2. cd learingit
 3. pwd
 4. git init
 5. 编辑一个readme.txt 内容如下
    > Git is a version control system.
    > Git is free software
 6. 一定放到learngit目录下
 7. git add readme.txt
 8. git commit -m "wrote a readme file"
 9. Q: 为什么添加文件需要 `add` ,`commit`两步呢？  
    A：因为`commit`可以一次提交很多文件
    > git add file1.txt
    > git add file2.txt file3.txt  
    > git commit  -m "add 3 files"

## 修改并提交

1. 我们已经成功添加并提交了一个readme.txt文件，现在对它进行修改
   > Git is a distributed version control system.  
   > Git is free software.
2. 运行 `git status`
   > On branch master  
   > Changes not staged for commit  
   > ...
3. 上面命令告诉我们，readme.txt被修改过了，但还没有准备提交修改
4. 输入 `git diff readme.txt` 命令，可以查看修改情况
5. 输入 `git add readme.txt` 同样没有任何输出，在执行commit之前
6. 试试运行 `git status`
   > On branch master  
   > Changes to be committed  
   > modified: readme.txt  
7. 可以放心的提交了 `git commit -m "add distributed"`
8. 再次试试 `git status`
   > On branch master  
   > nothing to commit, working tree clean

## 版本回退

1. 我们学会了修改并提交，现在再练习一次，修改后的readme.txt

   ```text
      Git is a distributed version control system.  
      Git is free software distributed under th GPL
   ```

2. git add readme.txt
3. git commit -m "append GPL"
4. 现在，我们回顾一次`readme.txt`文件一共有几个版本被提交到Git仓库

   ```text
      版本3 appedn GPL
      版本2 add distributed
      版本1 wrote a readme file
   ```

5. 实际开发中，我们可以用 `git log`命令查看历史记录
6. 在Git中 `HEAD` 表示当前版本， `HEAD^` 表示上一个版本
   `HEAD~100` 表示上100个版本
7. git reset --hard HEAD^ 表示回退到上一版本
8. 使用 `git log` 命令再次查看版本库的状态

   ```text
       版本2 add distributed  
       版本1 wrote a readme file
   ```

9. 方了！ 最新版3已经不见踪影，肿么办？
10. 只要上面的命令行没有被关闭，找到版本3的版本号

      ```text
         git reset --hard 1094a  
         1094a是版本3的部分版本号，不用输全，只要是唯一即可
      ```

11. 在Git中，使用 `git reset --hard HEAD^`即可回退到上一版本  
      想再次回到最新版本，必须找到当时的 commit id
12. `git reflog` 会记录你的每一次命令，可以找到历史记录

## 工作区和暂存区

  > Git中的暂存区（state或者index），默认会创建一个分支`master`  
  > 指向`master`的指针称为`HEAD`  

  ![Git示意图](https://cdn.liaoxuefeng.com/cdn/files/attachments/001384907702917346729e9afbf4127b6dfbae9207af016000/0)

1. git add 实际是把文件修改添加到暂存区
2. git commit 提交，实际上是把暂存区的内容提交到当前分支
3. 做个练习，先对`readme.txt`做个修改，再增加一个`LICENSE`  
     使用 git stats 查一上状态

   ```text
      On branch master
      Changes not staged for commit:
      modified: readme.txt  
      Untracked files:
      LICENSE
   ```

4. git 非常清楚的告诉我们，`readme.txt`被修改了，`LICENSE`从来没被添加过
5. 使用两次 `git add`命令，把两个文件都添加，用 `git status`试试

   ```text
      On branch master<br>
      Changes to be committed<br>
      new file: LICENSE <br>
      modified: readme.txt<br>
   ```

6. 暂存区就这样了  
  ![alt text](https://cdn.liaoxuefeng.com/cdn/files/attachments/001384907720458e56751df1c474485b697575073c40ae9000/0)

7. 执行`git commit`,暂存区就没有任何内容了
  ![alt text](https://cdn.liaoxuefeng.com/cdn/files/attachments/0013849077337835a877df2d26742b88dd7f56a6ace3ecf000/0)

[一个成功的Git分支模型](https://www.jianshu.com/p/b357df6794e3)  
[[转] 译：Git rebase VS. Git merge](https://www.jianshu.com/p/ddb3f412b579)
