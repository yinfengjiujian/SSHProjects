<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@	taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib uri="http://www.duanmlssh.duanmltld/tld" prefix="duanml"%>
<%@ include file="/comment/pages/common.jsp"%>

<script type="text/javascript">
function tree_method(meunId){
	$.post(ProjectSSH.WEB_APP_NAME+"/admin/menu/menuList.do",{"menuId":meunId},function(data) {
		data = eval("("+data+")");
		var dataDC = new DataCenter(data);
		if (dataDC.getCode() == 1) {
			$("#menuList").css("display","block");
			var Store_ = dataDC.getDataStore("meuninfo");
			var rowData = Store_.getRowSet().getRow(0);
			//分别给对应的赋值
			$("#menuid").val(rowData.getItemValue("menuId"));
			$("#nameid").val(rowData.getItemValue("menuName"));
			$("#menukey").val(rowData.getItemValue("menuKey"));
			$("#parentid").val(rowData.getItemValue("parentId"));
			$("#orderid").val(rowData.getItemValue("menuOrder"));
			$("#addressid").val(rowData.getItemValue("menuUrl"));
			$("#authorid").val(rowData.getItemValue("aae013"));
			//返回的日期格式要单独处理
			var timeajax = rowData.getItemValue("aae036");
			if(timeajax != null && timeajax != ""){
		          varmytime= new Date(parseInt(timeajax.time)); // 取毫秒数
		          $("#dateid").val(getDate(varmytime));
		    }else{
		    	$("#dateid").val("");
		    }
		}else{
			alertMsg.error("系统发生错误，"+dataDC.getTitle());
		}
	});
	$("#buttonadd").attr("href","<%=path%>/admin/menu/menuadd.do?menuid="+meunId);
	$("#buttonedit").attr("href","<%=path%>/admin/menu/menuedit.do?menuid="+meunId);
	$("#buttondelete").attr("href","<%=path%>/admin/menu/menudelete.do?menuid="+meunId);
}

function getDate(date) {
    var year = date.getFullYear();
    var month = date.getMonth() + 1;
    var day = date.getDate();
    return year + "-" + month + "-" + day ; 
}
</script>

<div class="pageContent">
	<div class="accordion" style="float:left;width: 300px;">
		<duanml:AllMenustree treeContent="accordionContent" treeStyle="tree treeFolder" treeHead="accordionHeader" treeTarget="navTab"></duanml:AllMenustree>
	</div>
	<div id="menuList" class="accordion" style="display: none;">
		<div class="pageFormContent nowrap" layoutH="260">
			<dl>
				<dt>菜单ID：</dt>
				<dd>
					<input type="text" id="menuid" name="menuidname" maxlength="32" size="40" readonly="readonly" class="required" />
				</dd>
			</dl>
			<dl>
				<dt>菜单名称：</dt>
				<dd>
					<input type="text" id="nameid" name="menuname" maxlength="25" size="60" class="required" />
				</dd>
			</dl>
			<dl>
				<dt>菜单Key值：</dt>
				<dd>
					<input type="text" id="menukey" name="menukey" maxlength="30" readonly="readonly" size="40"  class="required" />
				</dd>
			</dl>
			<dl>
				<dt>父类菜单ID：</dt>
				<dd>
					<input type="text" id="parentid" name="parentidname" maxlength="32" size="40" readonly="readonly" />
				</dd>
			</dl>
			<dl>
				<dt>菜单次序：</dt>
				<dd>
					<input type="text" id="orderid" name="ordername" maxlength="6"  class="required"/>
				</dd>
			</dl>
			<dl>
				<dt>菜单地址：</dt>
				<dd>
					<input type="text" id="addressid" name="addressname"  maxlength="80" size="60"  />
				</dd>
			</dl>
			<dl>
				<dt>操作人：</dt>
				<dd>
					<input type="text" id="authorid" name="authorname" readonly="readonly"  maxlength="20"  />
				</dd>
			</dl>
			<dl>
				<dt>操作时间：</dt>
				<dd>
					<input type="text" id="dateid" name="datename" disabled="disabled"  readonly="readonly" class="date" />
					<a class="inputDateButton" href="javascript:;">选择</a>
				</dd>
			</dl>
		</div>
		<div class="panelBar">
			<ul class="toolBar">
				<li><a id="buttonadd" class="add" href="javascript:;" target="dialog" title="添加子菜单" width="800" height="400"><span>增加子菜单</span></a></li>
				<li><a id="buttonedit" class="edit" href="javascript:;" target="dialog" title="修改菜单" width="800" height="480"><span>修改菜单</span></a></li>
				<li><a id="buttondelete" class="delete" href="javascript:;" target="ajaxTodo" title="确定要删除此菜单吗?"><span>删除菜单</span></a></li>
			</ul>
		</div>
	</div>
</div>

