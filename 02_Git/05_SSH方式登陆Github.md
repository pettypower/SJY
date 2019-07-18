# SSH方式登陆Github

> 撰稿人：邹强  
> 基本简介：Git的入门课程  
> 作成时间：2019-7-18  
> 修改时间：2019-7-18

1. 生成密钥对
    > `ssh-keygen -t rsa -C "your_email@youremail.com"`
    > 连续三次Enter键
2. 查看生成的公钥
    > `cat "c:/Users/Administrator/.ssh/id_rsa.pub"`
    > 也可以用notepad打开查看
3. 登陆github
    > 点击头像： Settings -> 左栏点击 SSH and GPG keys -> 点击 New SSH key
    > 粘进key文本域内，title随便取一个
4. 测试key是否正常工作
    > `ssh -T git@github.com`
    > 看到 You've successfully authenticated, 说明正常
5. 修改remote url
    > `git remote -v`
    > 查看当前远程仓库
    > `git remote set-url origin git@github.com:pettypower/SJY.git`
    > 更新远程仓库
6. 远程拉取（只拉最新20个版本）
    > `git clone --progress -v --depth 20 "git@github.com:pettypower/SJY.git" "D:\MyWorkSpace\SJY_SSH\SJY"`
