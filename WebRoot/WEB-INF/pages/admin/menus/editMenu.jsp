<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@	taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib uri="http://www.duanmlssh.duanmltld/tld" prefix="duanml"%>
<%@ include file="/comment/pages/common.jsp"%>

<div class="pageContent">
	<form method="post" action="<%=path%>/admin/menu/menuToEdit.do" class="pageForm required-validate"
		onsubmit="return validateCallback(this,dialogAjaxDone,'是否确认修改数据！');">
		<div class="pageFormContent nowrap" layoutH="56">
			<dl>
				<dt>菜单ID：</dt>
				<dd>
					<input type="text" id="menuid" name="menuidname" value="${menuinfo.menuId}" maxlength="32" size="40" readonly="readonly" class="required" />
				</dd>
			</dl>
			<dl>
				<dt>菜单名称：</dt>
				<dd>
					<input type="text" id="nameid" name="menuname" maxlength="25" value="${menuinfo.menuName}" size="60" class="required" />
				</dd>
			</dl>
			<dl>
				<dt>菜单Key值：</dt>
				<dd>
					<input type="text" id="menukey" name="menukey" maxlength="30" value="${menuinfo.menuKey}" size="40" readonly="readonly"  class="required" />
				</dd>
			</dl>
			<dl>
				<dt>父类菜单ID：</dt>
				<dd>
					<input type="text" id="parentid" name="parentidname" value="${menuinfo.parentId}" maxlength="32" size="40" readonly="readonly" />
				</dd>
			</dl>
			<dl>
				<dt>菜单次序：</dt>
				<dd>
					<input type="text" id="orderid" name="ordername" maxlength="6" value="${menuinfo.menuOrder}"  class="required"/>
				</dd>
			</dl>
			<dl>
				<dt>菜单地址：</dt>
				<dd>
					<input type="text" id="addressid" name="addressname"  maxlength="80" value="${menuinfo.menuUrl}" size="60"  />
				</dd>
			</dl>
			<dl>
				<dt>操作人：</dt>
				<dd>
					<input type="text" id="authorid" name="authorname" readonly="readonly" value="${menuinfo.aae013}"  maxlength="20"  />
				</dd>
			</dl>
			<dl>
				<dt>操作时间：</dt>
				<dd>
					<input type="text" id="dateid" name="datename" value="${authordate}" dateFmt="yyyy-MM-dd HH:mm:ss" readonly="readonly" class="date" />
					<a class="inputDateButton" href="javascript:;">选择</a>
				</dd>
			</dl>
		</div>
		<div class="formBar">
			<ul>
				<li><div class="buttonActive">
						<div class="buttonContent">
							<button type="submit" class="save">保存</button>
						</div>
					</div>
				</li>
				<li>
					<div class="buttonActive">
						<div class="buttonContent">
							<button type="button" class="close">取消</button>
						</div>
					</div></li>
			</ul>
		</div>
	</form>
</div>
