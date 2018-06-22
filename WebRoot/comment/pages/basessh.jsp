<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<link href="<%=path%>/comment/dwz/themes/default/style.css" rel="stylesheet" type="text/css" media="screen"/>
<link href="<%=path%>/comment/dwz/themes/css/core.css" rel="stylesheet" type="text/css" media="screen"/>
<link href="<%=path%>/comment/dwz/themes/css/print.css" rel="stylesheet" type="text/css" media="print"/>
<link href="<%=path%>/comment/dwz/uploadify/css/uploadify.css" rel="stylesheet" type="text/css" media="screen"/>

<script src="<%=path%>/comment/dwz/js/jquery-2.1.4.min.js" type="text/javascript"></script>
<script src="<%=path%>/comment/dwz/js/jquery.cookie.js" type="text/javascript"></script>
<script src="<%=path%>/comment/dwz/js/jquery.validate.js" type="text/javascript"></script>
<script src="<%=path%>/comment/dwz/js/jquery.bgiframe.js" type="text/javascript"></script>
<script src="<%=path%>/comment/dwz/xheditor/xheditor-1.2.2.min.js" type="text/javascript"></script>
<script src="<%=path%>/comment/dwz/xheditor/xheditor_lang/zh-cn.js" type="text/javascript"></script>
<script src="<%=path%>/comment/dwz/uploadify/scripts/jquery.uploadify.js" type="text/javascript"></script>

<script src="<%=path%>/comment/dwz/chart/raphael.js" type="text/javascript"></script>
<script src="<%=path%>/comment/dwz/chart/g.raphael.js" type="text/javascript" ></script>
<script src="<%=path%>/comment/dwz/chart/g.bar.js" type="text/javascript" ></script>
<script src="<%=path%>/comment/dwz/chart/g.line.js" type="text/javascript" ></script>
<script src="<%=path%>/comment/dwz/chart/g.pie.js" type="text/javascript" ></script>
<script src="<%=path%>/comment/dwz/chart/g.dot.js" type="text/javascript" ></script>
<script src="<%=path%>/comment/dwz/js/dwz.DataCenter.js" type="text/javascript" ></script>
<script src="<%=path%>/comment/dwz/js/dwz.DataStore.js" type="text/javascript" ></script>
<script src="<%=path%>/comment/dwz/js/dwz.RowSet.js" type="text/javascript" ></script>
<script src="<%=path%>/comment/dwz/js/dwz.Row.js" type="text/javascript" ></script>

<script src="http://api.map.baidu.com/api?v=2.0&ak=6PYkS1eDz5pMnyfO0jvBNE0F" type="text/javascript" ></script>
<script src="http://api.map.baidu.com/library/Heatmap/2.0/src/Heatmap_min.js" type="text/javascript" ></script>

<script src="<%=path%>/comment/dwz/js/dwz.core.js" type="text/javascript"></script>
<script src="<%=path%>/comment/dwz/js/dwz.util.date.js" type="text/javascript"></script>
<script src="<%=path%>/comment/dwz/js/dwz.validate.method.js" type="text/javascript"></script>
<script src="<%=path%>/comment/dwz/js/dwz.barDrag.js" type="text/javascript"></script>
<script src="<%=path%>/comment/dwz/js/dwz.drag.js" type="text/javascript"></script>
<script src="<%=path%>/comment/dwz/js/dwz.tree.js" type="text/javascript"></script>
<script src="<%=path%>/comment/dwz/js/dwz.accordion.js" type="text/javascript"></script>
<script src="<%=path%>/comment/dwz/js/dwz.ui.js" type="text/javascript"></script>
<script src="<%=path%>/comment/dwz/js/dwz.theme.js" type="text/javascript"></script>
<script src="<%=path%>/comment/dwz/js/dwz.switchEnv.js" type="text/javascript"></script>
<script src="<%=path%>/comment/dwz/js/dwz.alertMsg.js" type="text/javascript"></script>
<script src="<%=path%>/comment/dwz/js/dwz.contextmenu.js" type="text/javascript"></script>
<script src="<%=path%>/comment/dwz/js/dwz.navTab.js" type="text/javascript"></script>
<script src="<%=path%>/comment/dwz/js/dwz.tab.js" type="text/javascript"></script>
<script src="<%=path%>/comment/dwz/js/dwz.resize.js" type="text/javascript"></script>
<script src="<%=path%>/comment/dwz/js/dwz.dialog.js" type="text/javascript"></script>
<script src="<%=path%>/comment/dwz/js/dwz.dialogDrag.js" type="text/javascript"></script>
<script src="<%=path%>/comment/dwz/js/dwz.sortDrag.js" type="text/javascript"></script>
<script src="<%=path%>/comment/dwz/js/dwz.cssTable.js" type="text/javascript"></script>
<script src="<%=path%>/comment/dwz/js/dwz.stable.js" type="text/javascript"></script>
<script src="<%=path%>/comment/dwz/js/dwz.taskBar.js" type="text/javascript"></script>
<script src="<%=path%>/comment/dwz/js/dwz.ajax.js" type="text/javascript"></script>
<script src="<%=path%>/comment/dwz/js/dwz.pagination.js" type="text/javascript"></script>
<script src="<%=path%>/comment/dwz/js/dwz.database.js" type="text/javascript"></script>
<script src="<%=path%>/comment/dwz/js/dwz.datepicker.js" type="text/javascript"></script>
<script src="<%=path%>/comment/dwz/js/dwz.effects.js" type="text/javascript"></script>
<script src="<%=path%>/comment/dwz/js/dwz.panel.js" type="text/javascript"></script>
<script src="<%=path%>/comment/dwz/js/dwz.checkbox.js" type="text/javascript"></script>
<script src="<%=path%>/comment/dwz/js/dwz.history.js" type="text/javascript"></script>
<script src="<%=path%>/comment/dwz/js/dwz.combox.js" type="text/javascript"></script>
<script src="<%=path%>/comment/dwz/js/dwz.file.js" type="text/javascript"></script>
<script src="<%=path%>/comment/dwz/js/dwz.print.js" type="text/javascript"></script>

<script src="<%=path%>/comment/dwz/js/dwz.regional.zh.js" type="text/javascript"></script>
<style type="text/css">
	#header{height:85px}
	#leftside, #container, #splitBar, #splitBarProxy{top:90px}
</style>

<script type="text/javascript">
$(function(){
	DWZ.init("<%=path%>/comment/dwz/dwz.frag.xml", {
		loginUrl:"<%=path%>/comment/dwz/login_dialog.html", loginTitle:"登录窗口",	// 弹出登录对话框
		statusCode:{ok:200, error:300, timeout:301}, //【可选】
		pageInfo:{pageNum:"pageNum", numPerPage:"numPerPage", orderField:"orderField", orderDirection:"orderDirection"}, //【可选】
		keys: {statusCode:"statusCode", message:"message"}, //【可选】
		ui:{hideMode:'offsets'}, //【可选】hideMode:navTab组件切换的隐藏方式，支持的值有’display’，’offsets’负数偏移位置的值，默认值为’display’
		debug:false,	// 调试模式 【true|false】
		callback:function(){
			initEnv();
			$("#themeList").theme({themeBase:"<%=path%>/comment/dwz/themes"}); // themeBase 相对于index页面的主题base路径
//			setTimeout(function() {$("#sidebar .toggleCollapse div").trigger("click");}, 10);//设置成左边的菜单栏是否隐藏
		}
	});
});
</script>