<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@	taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib uri="http://www.duanmlssh.duanmltld/tld" prefix="duanml"%>
<%@ include file="/comment/pages/common.jsp"%>
<script src="<%=path%>/jsResources/mainUsers.js" type="text/javascript"></script>

<form id="pagerForm" method="post" action="<%=path%>/admin/user/userManager.do">
	<input type="hidden" name="pageNum" value="1" />
	<input type="hidden" name="numPerPage" value="${numPerPage}" />
</form>

<div class="pageContent">
	<div class="panelBar">
		<ul class="toolBar">
			<li><a class="add" href="demo_page4.html" target="navTab"><span>添加用户</span></a></li>
			<li><a class="delete" href="demo/common/ajaxDone.html?userid={userinfo}" target="ajaxTodo" title="确实要删除这些记录吗?" warn="请选择一个用户"><span>删除用户</span></a></li>
			<li><a class="edit" href="demo_page4.html?userid={userinfo}" target="navTab" warn="请选择一个用户"><span>修改用户</span></a></li>
			<li class="line">line</li>
			<li><a class="icon" href="demo_page4.html" target="navTab"><span>查询用户</span></a></li>
		</ul>
	</div>
	<table class="table" width="1200px" layoutH="280"  onclickrow="doseltablerow_menulist()" ondblclickrow="dblclick()" >
		<thead>
			<tr>
				<th width="22px"><input type="radio" group="ids" class="checkboxCtrl"></th>
				<th width="60px" align="center">用户账号</th>
				<th width="80px" align="center">用户名称</th>
				<th width="80px" align="center">账号是否启用</th>
				<th width="80px" align="center">账号是否锁定</th>
				<th width="60px" align="center">性别</th>
				<th width="60px" align="center">出生日期</th>
				<th width="60px" align="center">电子邮件</th>
				<th width="50px" align="center">移动电话</th>
				<th width="50px" align="center">家庭电话</th>
				<th width="50px" align="center">地址</th>
				<th width="150px" align="center">描述信息</th>
			</tr>
		</thead>
		<tbody>
			<c:forEach var="users" items="${ListUsers}">
				<tr target="userinfo" rel="${users.userId}">
					<td><input name="ids" value="${users.userId}"  type="radio"></td>
					<td>${users.userAccount}</td>
					<td>${users.userFullname}</td>
					<td>${users.accountEnabled}</td>
					<td>${users.accountLocked}</td>
					<td>${users.sex}</td>
					<td>${users.birthdate}</td>
					<td>${users.email}</td>
					<td>${users.mobileTelephone}</td>
					<td>${users.homeTelephone}</td>
					<td>${users.homeAddress}</td>
					<td>${users.description}</td>
				</tr>
			</c:forEach>
		</tbody>
	</table>
	<div class="panelBar">
		<div class="pages">
			<span>每页显示</span>
			   <c:set var="pageSizeList" value="${fn:split('5|10|20|50|100', '|')}"/>
			   <select class="combox" name="numPerPage" onchange="navTabPageBreak({numPerPage:this.value})">
			    <c:forEach var="pageSize" items="${pageSizeList}">
			        <option value="${pageSize}" ${pageSize eq numPerPage ? 'selected="selected"' : ''}>${pageSize}</option>
			    </c:forEach>
			   </select>
			<span>条，共${totalCount}条</span>
		</div>
		<div class="pagination" targetType="navTab" totalCount="${totalCount}" numPerPage="${numPerPage}" pageNumShown="10" currentPage="${pageNum}"></div>
	</div>
</div>

<div class="pageContent">
	<div class="tabs" currentIndex="0" eventType="click">
		<div class="tabsHeader">
			<div class="tabsHeaderContent">
				<ul>
					<li><a href="javascript:;"><span>所属组织单元</span></a></li>
					<li><a href="javascript:;"><span>所属业务角色</span></a></li>
				</ul>
			</div>
		</div>
		<div class="tabsContent">
			<div>
				<div class="panelBar">
					<ul class="toolBar">
						<li><a class="add" href="demo_page4.html" target="navTab"><span>新增</span></a></li>
						<li><a class="delete" title="确实要删除这些记录吗?" target="selectedTodo" rel="ids" postType="string" href="demo/common/ajaxDone.html"><span>删除</span></a></li>
					</ul>
				</div>
				<table class="table" width="800" layoutH="380">
					<thead>
						<tr>
							<th width="22"><input type="radio" group="ids" class="checkboxCtrl"></th>
							<th width="80" orderField="accountNo" class="asc">客户号</th>
							<th orderField="accountName">客户名称</th>
							<th width="80" orderField="accountType">客户类型</th>
						</tr>
					</thead>
					<tbody>
						<tr target="sid_user" rel="1">
							<td><input name="ids" value="xxx" type="radio"></td>
							<td>A120113196309052434</td>
							<td>天津市华建装饰工程有限公司</td>
							<td>联社营业部</td>
						</tr>
					</tbody>
				</table>
			</div>
			<div>
				<div class="panelBar">
					<ul class="toolBar">
						<li><a class="add" href="demo_page4.html" target="navTab"><span>新增</span></a></li>
						<li><a class="delete" title="确实要删除这些记录吗?" target="selectedTodo" rel="ids" postType="string" href="demo/common/ajaxDone.html"><span>删除</span></a></li>
					</ul>
				</div>
			    <table class="table" width="800" layoutH="380">
					<thead>
						<tr>
							<th width="22"><input type="radio" group="ids" class="checkboxCtrl"></th>
							<th width="80" orderField="accountNo" class="asc">客户号</th>
							<th orderField="accountName">客户名称</th>
							<th width="80" orderField="accountType">客户类型</th>
							<th width="130" orderField="accountCert">证件号码</th>
							<th width="60" align="center" orderField="accountLevel">信用等级</th>
							<th width="70">所属行业</th>
							<th width="70">建档日期</th>
						</tr>
					</thead>
					<tbody>
						<tr target="sid_user" rel="1">
							<td><input name="ids" value="xxx" type="radio"></td>
							<td>A120113196309052434</td>
							<td>天津市华建装饰工程有限公司</td>
							<td>联社营业部</td>
							<td>29385739203816293</td>
							<td>5级</td>
							<td>政府机构</td>
							<td>2009-05-21</td>
						</tr>
						<tr target="sid_user" rel="1">
							<td><input name="ids" value="xxx" type="radio"></td>
							<td>A120113196309052434</td>
							<td>天津市华建装饰工程有限公司</td>
							<td>联社营业部</td>
							<td>29385739203816293</td>
							<td>5级</td>
							<td>政府机构</td>
							<td>2009-05-21</td>
						</tr>
						<tr target="sid_user" rel="1">
							<td><input name="ids" value="xxx" type="radio"></td>
							<td>A120113196309052434</td>
							<td>天津市华建装饰工程有限公司</td>
							<td>联社营业部</td>
							<td>29385739203816293</td>
							<td>5级</td>
							<td>政府机构</td>
							<td>2009-05-21</td>
						</tr>
						<tr target="sid_user" rel="1">
							<td><input name="ids" value="xxx" type="radio"></td>
							<td>A120113196309052434</td>
							<td>天津市华建装饰工程有限公司</td>
							<td>联社营业部</td>
							<td>29385739203816293</td>
							<td>5级</td>
							<td>政府机构</td>
							<td>2009-05-21</td>
						</tr>
						<tr target="sid_user" rel="1">
							<td><input name="ids" value="xxx" type="radio"></td>
							<td>A120113196309052434</td>
							<td>天津市华建装饰工程有限公司</td>
							<td>联社营业部</td>
							<td>29385739203816293</td>
							<td>5级</td>
							<td>政府机构</td>
							<td>2009-05-21</td>
						</tr>
						<tr target="sid_user" rel="1">
							<td><input name="ids" value="xxx" type="radio"></td>
							<td>A120113196309052434</td>
							<td>天津市华建装饰工程有限公司</td>
							<td>联社营业部</td>
							<td>29385739203816293</td>
							<td>5级</td>
							<td>政府机构</td>
							<td>2009-05-21</td>
						</tr>
						<tr target="sid_user" rel="1">
							<td><input name="ids" value="xxx" type="radio"></td>
							<td>A120113196309052434</td>
							<td>天津市华建装饰工程有限公司</td>
							<td>联社营业部</td>
							<td>29385739203816293</td>
							<td>5级</td>
							<td>政府机构</td>
							<td>2009-05-21</td>
						</tr>
						<tr target="sid_user" rel="1">
							<td><input name="ids" value="xxx" type="radio"></td>
							<td>A120113196309052434</td>
							<td>天津市华建装饰工程有限公司</td>
							<td>联社营业部</td>
							<td>29385739203816293</td>
							<td>5级</td>
							<td>政府机构</td>
							<td>2009-05-21</td>
						</tr>
						<tr target="sid_user" rel="1">
							<td><input name="ids" value="xxx" type="radio"></td>
							<td>A120113196309052888</td>
							<td>天津市华建装饰工程有限公司</td>
							<td>联社营业部</td>
							<td>29385739203816293</td>
							<td>5级</td>
							<td>政府机构</td>
							<td>2009-05-21</td>
						</tr>
					</tbody>
				</table>
			</div>
		</div>
	</div>
</div>