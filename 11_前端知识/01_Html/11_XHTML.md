# XHtml简介

> 撰稿人：邹强  
> 基本简介：XHtml简介笔记  
> 作成时间：2019-7-28  
> 修改时间：2019-7-28

## 定义

1. XHTML 指的是可扩展超文本标记语言
2. XHTML 与 HTML 4.01 几乎是相同的
3. XHTML 是更严格更纯净的 HTML 版本
4. XHTML 是以 XML 应用的方式定义的 HTML
5. XHTML 是 2001 年 1 月发布的 W3C 推荐标准
6. XHTML 是大小写敏感的，标准的 XHTML 标签应该使用小写。
7. XHTML 得到所有主流浏览器的支持

## 与 HTML 相比最重要的区别

1. 文档结构
    > XHTML DOCTYPE 是强制性的
    > `<html>` 中的 XML namespace 属性是强制性的
    > `<html> , <head> , <title> , <body>` 也是强制性的

2. 元素语法
    > XHTML 元素必须正确嵌套
    > XHTML 元素必须始终关闭
    > XHTML 元素必须小写
    > XHTML 文档必须有一个根元素

3. 属性语法
    > XHTML 属性必须使用小写
    > XHTML 属性值必须用引号包围
    > XHTML 属性最小化也是禁止的

## 一个带最少必须标签的XHTML文档

```html

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">

<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <title>Title of document</title>
</head>

<body>
......
</body>

</html>

```

## 错误示范

1. XHTML 元素必须合理嵌套
    - 错误

    ```html
        <b><i>This text is bold and italic</b></i>
    ```

    - 正确

    ```html
        <b><i>This text is bold and italic</i></b>
    ```

2. XHTML 元素必须合理嵌套
    - 错误

    ```html
        <b><i>This text is bold and italic</b></i>
    ```

    - 正确

    ```html
        <b><i>This text is bold and italic</i></b>
    ```
