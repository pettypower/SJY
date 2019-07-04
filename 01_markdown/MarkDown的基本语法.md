# MarkDown的基本语法

> 撰稿人：邹强  
> 基本简介：本文档主要讲解了MarkDown的基本用法清单  
> 作成时间：2018-8-1  
> 修改时间：2019-7-13

## 1. Headers 标题

```
#  H1标题
##  H2标题
###  H3标题
####  H4标题
#####  H5标题
######  H6标题
```

> #  H1标题
> ##  H2标题
> ###  H3标题
> ####  H4标题
> #####  H5标题
> ######  H6标题

## 2. Emphasis 文本强调

```
*斜体* or _强调_
**加粗** or __加粗__
```

> *斜体* or _强调_ \
> **加粗** or __加粗__

```
注：如果 * 和 _ 两边都有空白的话，它们就只会被当成普通的符号
如果要在文字前后直接插入普通的星号或底线,可以用反斜线（转义符）：
\*this text is surrounded by literal asterisks\*
```

## 3.Lists 列表

**无序列表**

```
- 无序列表
- 子项
- 子项
```

> - 无序列表
> - 子项
> - 子项
  
**有序列表**
```
1. 第一行
2. 第二行
3. 第三行
```

> 1. 第一行
> 2. 第二行
> 3. 第三行
**组合**

```
- 产品介绍（子项无项目符号）
    此时子项，要以一个制表符或者4个空格缩进
- 产品特点
    1. 特点1
    2. 特点2
    1. 特点3
- 产品功能
    - 功能1
    - 功能2
    - 功能3
```

> - 产品介绍（子项无项目符号）
>     此时子项，要以一个制表符或者4个空格缩进
>  
> - 产品特点
>     1. 特点1
>     2. 特点2
>     3. 特点3
> - 产品功能
>     - 功能1
>     - 功能2
>     - 功能3


 ```
 可有时我们会出现这样的情况，首行内容是以日期或数字开头：2013. 公司年度目标。
为了避免也被转化成有序列表，我们可以在"."前加上反斜杠（转义符）：2013\. 公司年度目标。
 ```

## 4.Links 链接

```
[显示文字](https://www.baidu.com "鼠标悬停文字")
```

> [显示文字](https://www.baidu.com "鼠标悬停文字")

## 5.Images 图片

```
![图片加载不出来时显示的文字](http://dwz.win/cFA "鼠标悬停文字")
```

> ![图片加载不出来时显示的文字](http://dwz.win/cFA "鼠标悬停文字")

## 6.代码和语法高亮

\`\`\`html  
此处填写需要语法高亮的代码  
\`\`\`

```html
    <!-- html -->
    <div>Syntax Highlighting</div>
```

```css
    /* html */ 
    body{font-size:12px}
```

```javascript
    // javascript
    var s = "JavaScript syntax highlighting";
    alert(s);
```

```php
    // php
    <?php
      echo "hello, world!";
    ?>
```

```python
    # python
    s = "Python syntax highlighting"
    print s
```

## 7. Block Code 代码区块

```
> Email-style angle brackets  
> are used for blockquotes.
> > And, they can be nested.  
> > abc
> #### Headers in blockquotes
> * You can quote a list.
> * Etc.
```

> Email-style angle brackets  
> are used for blockquotes.
> > And, they can be nested.  
> > abc
> #### Headers in blockquotes
> * You can quote a list.
> * Etc.

## 8. Table 表格

```
险种 | 保额 | 保费
---|---|---
第三者责任保险|￥1000000|￥1380.76
机动车损失保险|￥94240.6|￥1199.8
车上人员责任险（司机）|￥20000|￥44.44
车上人员责任险（乘客）|￥80000|￥112.72
玻璃单独破碎险（国产）|￥0|￥103.89
```

> 险种 | 保额 | 保费
> ---|---|---
> 第三者责任保险|￥1000000|￥1380.76
> 机动车损失保险|￥94240.6|￥1199.8
> 车上人员责任险（司机）|￥20000|￥44.44
> 车上人员责任险（乘客）|￥80000|￥112.72
> 玻璃单独破碎险（国产）|￥0|￥103.89

## 9. 小技巧

### 换行

```
1. 结尾处加上2个或2个以上的空格
2. </br>
3. /
```

### 水平分割线

```
***
* * *
- - -
```

### 转义符

```
\*literal asterisks\*
\\  
\_
```

> \*literal asterisks\*  
> \\  
> \_

dev add zouqiang line1
master add zouqiang line1
