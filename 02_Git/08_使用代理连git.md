# 使用代理连Git

> 撰稿人：邹强  
> 基本简介：使用代理连Git  
> 作成时间：2019-8-9  
> 修改时间：2019-8-9  

1. 使用命令设置

    > 全局:`git config --global https.proxy yusg:808`
    > 当前项目: `git config --local https.proxy yusg:808`

2. 直接修改配置文件

    > 全局: `C:\Users\Administrator\.gitconfig`  添加如下内容

        ```txt
        [http]
            proxy = yusg:808
        ```
    > 当前项目: `项目目录\.git\config` 添加如下内容

        ```txt
        [http]
            proxy = yusg:808
        ```
