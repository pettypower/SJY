# 下载安装
1. Windows安装
    > [下载地址](https://git-scm.com/downloads)\
    > 一路选择默认下一步安装即可
2. 其它操作系统安装

# 创建版本库
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
 9. Q: 为什么添加文件需要 `add` ,`commit`两步呢？<br>
    A：因为`commit`可以一次提交很多文件
    > git add file1.txt<br>
    > git add file2.txt file3.txt<br>
    > git commit  -m "add 3 files"