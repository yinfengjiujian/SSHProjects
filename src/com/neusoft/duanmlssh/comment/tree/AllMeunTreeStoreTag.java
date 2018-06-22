package com.neusoft.duanmlssh.comment.tree;

import java.io.IOException;
import java.util.List;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.TagSupport;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import com.neusoft.duanmlssh.admin.menus.applogic.MenusApplogicInterImpl;
import com.neusoft.duanmlssh.pojo.SshSysMenus;

public class AllMeunTreeStoreTag extends TagSupport{

	protected final transient Log log = LogFactory.getLog(AllMeunTreeStoreTag.class);
	
	/**
	*
	* <p>Title: 河源人社局</p>
	*
	* <p>Description: </p>
	*
	* <p>Copyright: Copyright (c) 2014</p>
	*
	* <p>Company: neusoft</p>
	*
	* @author 段美林
	* @date  2016-11-22
	* @version 1.0
	*/
	private static final long serialVersionUID = -3098718887674014770L;
	
	private String treeHead;
	private String treeContent;
	private String treeStyle;
	private String treeTarget;
	private String treeMenuId;
	
	@SuppressWarnings("rawtypes")
	@Override
	public int doStartTag() throws JspException {
		ApplicationContext ctx = WebApplicationContextUtils.getWebApplicationContext(pageContext.getServletContext());
		MenusApplogicInterImpl menusApplogic = (MenusApplogicInterImpl) ctx.getBean("menusApplogic");
		StringBuffer sbStr = new StringBuffer();
		//获取根节点菜单对象
		List fistMenu = menusApplogic.getRootMenu();
		//获取所有的孩子节点菜单List对象
		List allChildrenMeunList = menusApplogic.getAllChildrenMenus();
		sbStr.append("<div class='").append(treeHead).append("'>");
		sbStr.append("<h2><span>Folder</span>");
		sbStr.append("菜单列表");
		sbStr.append("</h2></div>");
		sbStr.append("<div class='").append(treeContent).append("' style='height: 500px' >");
		sbStr.append("<ul class='").append(treeStyle).append(" collapse'>");
		//如果前台没有传入树形菜单的ID，则返回整棵树
		for (int i = 0; i < fistMenu.size(); i++) {
			SshSysMenus sshSysMenus = (SshSysMenus) fistMenu.get(i);
			sbStr.append("<li><a id='").append(sshSysMenus.getMenuId()).append("' href='javascript:;' onclick='tree_method(this.id)'>").append(sshSysMenus.getMenuName());
			sbStr.append("</a>");
			//迭代拼接此一级菜单的树形结构
			sbStr.append(this.iterationMenus(allChildrenMeunList, sshSysMenus.getMenuId()));
		}
		sbStr.append("</ul></div>");
		try {
			pageContext.getOut().write(sbStr.toString().replace("<ul></ul>", ""));
		} catch (IOException e) {
			log.error("后台生成树形结构出错，请联系管理员！");
			e.printStackTrace();
		}
		return super.doStartTag();
	}
	
	/**
	 * 
	* <p>@Description:迭代生成树形结构</p>
	* 
	* @author 段美林
	* @date  2016-11-23
	* @version 1.0
	 */
	@SuppressWarnings("rawtypes")
	public StringBuffer  iterationMenus(List allChildrenMenus,String firstMenuId){
		StringBuffer sbStr = new StringBuffer("");
		for (int i = 0; i < allChildrenMenus.size(); i++) {
			SshSysMenus sshSysMenus = (SshSysMenus) allChildrenMenus.get(i);
			sbStr.append("<ul>");
			if (firstMenuId.equals(sshSysMenus.getParentId())) {
				//如果链接地址为空，则为父菜单，不提供链接处理
				sbStr.append("<li><a id='").append(sshSysMenus.getMenuId()).append("' href='javascript:;' onclick='tree_method(this.id)'>");
				sbStr.append(sshSysMenus.getMenuName());
				sbStr.append("</a>");
				sbStr.append(this.iterationMenus(allChildrenMenus, sshSysMenus.getMenuId()));
				sbStr.append("</li>");
			}
			sbStr.append("</ul>");
		}
		return sbStr;
	}
	
	public String getTreeHead() {
		return treeHead;
	}

	public void setTreeHead(String treeHead) {
		this.treeHead = treeHead;
	}

	public String getTreeContent() {
		return treeContent;
	}

	public void setTreeContent(String treeContent) {
		this.treeContent = treeContent;
	}

	public String getTreeStyle() {
		return treeStyle;
	}

	public void setTreeStyle(String treeStyle) {
		this.treeStyle = treeStyle;
	}

	public String getTreeTarget() {
		return treeTarget;
	}

	public void setTreeTarget(String treeTarget) {
		this.treeTarget = treeTarget;
	}
	
	public String getTreeMenuId() {
		return treeMenuId;
	}

	public void setTreeMenuId(String treeMenuId) {
		this.treeMenuId = treeMenuId;
	}
}
