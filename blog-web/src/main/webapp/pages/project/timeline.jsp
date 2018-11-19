<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<script type="text/javascript" charset="utf-8" src="./../../static/ue/ueditor.config.js"></script>
<script type="text/javascript" charset="utf-8" src="./../../static/ue/ueditor.all.min.js"> </script>
<!--建议手动加在语言，避免在ie下有时因为加载语言失败导致编辑器加载失败-->
<!--这里加载的语言文件会覆盖你在配置项目里添加的语言类型，比如你在配置项目里配置的是英文，这里加载的中文，那最后就是中文-->
<script type="text/javascript" charset="utf-8" src="./../../static/ue/lang/zh-cn/zh-cn.js"></script>
<script type="text/javascript" src="./../../static/ue/third-party/SyntaxHighlighter/shCore.js"></script>
<link rel="stylesheet" href="./../../static/ue/third-party/SyntaxHighlighter/shCoreDefault.css">
<script type="text/javascript">
    SyntaxHighlighter.all();
</script>
<div>
    <h1>完整demo</h1>
    <script id="editor" type="text/plain" style="width:1024px;height:500px;"></script>
</div>
<div id="btns">
    <div>
        <button onclick="getAllHtml()">获得整个html的内容</button>
        <button onclick="getContent()">获得内容</button>
        <button onclick="setContent()">写入内容</button>
        <button onclick="setContent(true)">追加内容</button>
        <button onclick="getContentTxt()">获得纯文本</button>
        <button onclick="getPlainTxt()">获得带格式的纯文本</button>
        <button onclick="hasContent()">判断是否有内容</button>
        <button onclick="setFocus()">使编辑器获得焦点</button>
        <button onmousedown="isFocus(event)">编辑器是否获得焦点</button>
        <button onmousedown="setblur(event)" >编辑器失去焦点</button>

    </div>
    <div>
        <button onclick="getText()">获得当前选中的文本</button>
        <button onclick="insertHtml()">插入给定的内容</button>
        <button id="enable" onclick="setEnabled()">可以编辑</button>
        <button onclick="setDisabled()">不可编辑</button>
        <button onclick=" UE.getEditor('editor').setHide()">隐藏编辑器</button>
        <button onclick=" UE.getEditor('editor').setShow()">显示编辑器</button>
        <button onclick=" UE.getEditor('editor').setHeight(300)">设置高度为300默认关闭了自动长高</button>
    </div>

    <div>
        <button onclick="getLocalData()" >获取草稿箱内容</button>
        <button onclick="clearLocalData()" >清空草稿箱</button>
    </div>

</div>
<div>
    <button onclick="createEditor()">
    创建编辑器</button>
    <button onclick="deleteEditor()">
    删除编辑器</button>
</div>

<script type="text/javascript">
    //实例化编辑器
    //建议使用工厂方法getEditor创建和引用编辑器实例，如果在某个闭包下引用该编辑器，直接调用UE.getEditor('editor')就能拿到相关的实例
    var ue = UE.getEditor('editor',{
		toolbars: [
		    [
		        'anchor', //锚点
		        'undo', //撤销
		        'redo', //重做
		        'bold', //加粗
		        'indent', //首行缩进
		        'snapscreen', //截图
		        'italic', //斜体
		        'underline', //下划线
		        'strikethrough', //删除线
		        'subscript', //下标
		        'fontborder', //字符边框
		        'superscript', //上标
		        'formatmatch', //格式刷
		        'source', //源代码
		        'blockquote', //引用
		        'pasteplain', //纯文本粘贴模式
		        'selectall', //全选
		        'print', //打印
		        'preview', //预览
		        'horizontal', //分隔线
		        'removeformat', //清除格式
		        'time', //时间
		        'date', //日期
		        'unlink', //取消链接
		        'insertrow', //前插入行
		        'insertcol', //前插入列
		        'mergeright', //右合并单元格
		        'mergedown', //下合并单元格
		        'deleterow', //删除行
		        'deletecol', //删除列
		        'splittorows', //拆分成行
		        'splittocols', //拆分成列
		        'splittocells', //完全拆分单元格
		        'deletecaption', //删除表格标题
		        'inserttitle', //插入标题
		        'mergecells', //合并多个单元格
		        'deletetable', //删除表格
		        'cleardoc', //清空文档
		        'insertparagraphbeforetable', //"表格前插入行"
		        'insertcode', //代码语言
		        'fontfamily', //字体
		        'fontsize', //字号
		        'paragraph', //段落格式
		        'simpleupload', //单图上传
		        'insertimage', //多图上传
		        'edittable', //表格属性
		        'edittd', //单元格属性
		        'link', //超链接
		        'emotion', //表情
		        'spechars', //特殊字符
		        'searchreplace', //查询替换
		        'map', //Baidu地图
		        'insertvideo', //视频
		        'help', //帮助
		        'justifyleft', //居左对齐
		        'justifyright', //居右对齐
		        'justifycenter', //居中对齐
		        'justifyjustify', //两端对齐
		        'forecolor', //字体颜色
		        'backcolor', //背景色
		        'insertorderedlist', //有序列表
		        'insertunorderedlist', //无序列表
		        'fullscreen', //全屏
		        'directionalityltr', //从左向右输入
		        'directionalityrtl', //从右向左输入
		        'rowspacingtop', //段前距
		        'rowspacingbottom', //段后距
		        'pagebreak', //分页
		        'insertframe', //插入Iframe
		        'imagenone', //默认
		        'imageleft', //左浮动
		        'imageright', //右浮动
		        'attachment', //附件
		        'imagecenter', //居中
		        'wordimage', //图片转存
		        'lineheight', //行间距
		        'edittip ', //编辑提示
		        'customstyle', //自定义标题
		        'autotypeset', //自动排版
		        'webapp', //百度应用
		        'touppercase', //字母大写
		        'tolowercase', //字母小写
		        'background', //背景
		        'template', //模板
		        'scrawl', //涂鸦
		        'music', //音乐
		        'inserttable', //插入表格
		        'drafts', // 从草稿箱加载
		        'charts', // 图表
		    ]
		]
    });


    function isFocus(e){
        alert(UE.getEditor('editor').isFocus());
        UE.dom.domUtils.preventDefault(e)
    }
    function setblur(e){
        UE.getEditor('editor').blur();
        UE.dom.domUtils.preventDefault(e)
    }
    function insertHtml() {
        var value = prompt('插入html代码', '');
        UE.getEditor('editor').execCommand('insertHtml', value)
    }
    function createEditor() {
        enableBtn();
        UE.getEditor('editor');
    }
    function getAllHtml() {
        alert(UE.getEditor('editor').getAllHtml())
    }
    function getContent() {
        var arr = [];
        arr.push("使用editor.getContent()方法可以获得编辑器的内容");
        arr.push("内容为：");
        arr.push(UE.getEditor('editor').getContent());
        alert(arr.join("\n"));
    }
    function getPlainTxt() {
        var arr = [];
        arr.push("使用editor.getPlainTxt()方法可以获得编辑器的带格式的纯文本内容");
        arr.push("内容为：");
        arr.push(UE.getEditor('editor').getPlainTxt());
        alert(arr.join('\n'))
    }
    function setContent(isAppendTo) {
        var arr = [];
        arr.push("使用editor.setContent('欢迎使用ueditor')方法可以设置编辑器的内容");
        UE.getEditor('editor').setContent('欢迎使用ueditor', isAppendTo);
        alert(arr.join("\n"));
    }
    function setDisabled() {
        UE.getEditor('editor').setDisabled('fullscreen');
        disableBtn("enable");
    }

    function setEnabled() {
        UE.getEditor('editor').setEnabled();
        enableBtn();
    }

    function getText() {
        //当你点击按钮时编辑区域已经失去了焦点，如果直接用getText将不会得到内容，所以要在选回来，然后取得内容
        var range = UE.getEditor('editor').selection.getRange();
        range.select();
        var txt = UE.getEditor('editor').selection.getText();
        alert(txt)
    }

    function getContentTxt() {
        var arr = [];
        arr.push("使用editor.getContentTxt()方法可以获得编辑器的纯文本内容");
        arr.push("编辑器的纯文本内容为：");
        arr.push(UE.getEditor('editor').getContentTxt());
        alert(arr.join("\n"));
    }
    function hasContent() {
        var arr = [];
        arr.push("使用editor.hasContents()方法判断编辑器里是否有内容");
        arr.push("判断结果为：");
        arr.push(UE.getEditor('editor').hasContents());
        alert(arr.join("\n"));
    }
    function setFocus() {
        UE.getEditor('editor').focus();
    }
    function deleteEditor() {
        disableBtn();
        UE.getEditor('editor').destroy();
    }
    function disableBtn(str) {
        var div = document.getElementById('btns');
        var btns = UE.dom.domUtils.getElementsByTagName(div, "button");
        for (var i = 0, btn; btn = btns[i++];) {
            if (btn.id == str) {
                UE.dom.domUtils.removeAttributes(btn, ["disabled"]);
            } else {
                btn.setAttribute("disabled", "true");
            }
        }
    }
    function enableBtn() {
        var div = document.getElementById('btns');
        var btns = UE.dom.domUtils.getElementsByTagName(div, "button");
        for (var i = 0, btn; btn = btns[i++];) {
            UE.dom.domUtils.removeAttributes(btn, ["disabled"]);
        }
    }

    function getLocalData () {
        alert(UE.getEditor('editor').execCommand( "getlocaldata" ));
    }

    function clearLocalData () {
        UE.getEditor('editor').execCommand( "clearlocaldata" );
        alert("已清空草稿箱")
    }
</script>