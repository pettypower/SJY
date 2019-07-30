# HTML 媒体播放简介

> 撰稿人：邹强  
> 基本简介：HTML 媒体(Media)笔记  
> 作成时间：2019-7-30  
> 修改时间：2019-7-30

## 定义

辅助应用程序（helper application）是可由浏览器启动的程序。辅助应用程序也称为插件。
辅助程序可用于播放音频和视频（以及其他）。辅助程序是使用 `<object>` 标签来加载的。
使用辅助程序播放视频和音频的一个优势是，您能够允许用户来控制部分或全部播放设置。
插件可以通过 `<object>` 标签或者 `<embed>` 标签添加在页面中。object 和 embed 元素都通过添加对浏览器不直接支持的插件的支持来扩展浏览器的功能。大多数辅助应用程序允许对音量设置和播放功能（比如后退、暂停、停止和播放）的手工（或程序的）控制

## `<object>`

1. 局部属性
    > data, type, height, width, usemap, name, form

2. 实例

    ```html
    <!DOCTYPE html>
    <html>
        <body>
            <object width="400" data="bookmark.swf"></object>
            <object width="100%" height="500px" data="snippet.html"></object>
            <object data="logo.png"></object>

            <!-- embed 没有关闭标签 -->
            <embed width="400" height="50" src="bookmark.swf">
            <embed width="100%" height="500px" src="snippet.html">
            <embed src="logo.png">
            <embed height="50" width="100" src="https://www.w3cschool.cn/statics/demosource/horse.mp3">
        </body>
    </html>

    ```
