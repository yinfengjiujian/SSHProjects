package com.neusoft.duanmlssh.comment.tree;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.TagSupport;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import com.neusoft.duanmlssh.admin.menus.applogic.MenusApplogicInterImpl;
import com.neusoft.duanmlssh.pojo.SshSysMenus;

/**
 * 
*
* <p>Title: 河源人社局</p>
*
* <p>Description: </p>
*
* <p>Copyright: Copyright (c) 2017</p>
*
* <p>Company: neusoft</p>
*
* @author 段美林
*
* @date  2017-3-24
*
* @version 1.0
 */
public class MeunTreeStoreTag extends TagSupport{

	protected final transient Log log = LogFactory.getLog(MeunTreeStoreTag.class);
	
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
		//获取一级菜单List对象
		List fistMenu = menusApplogic.getFistMenu();
		//获取所有的孩子节点菜单List对象
		List allChildrenMeunList = menusApplogic.getAllChildrenMenus();
		//如果前台没有传入树形菜单的ID，则返回整棵树
		if (StringUtils.isEmpty(treeMenuId)) {
			for (int i = 0; i < fistMenu.size(); i++) {
				SshSysMenus sshSysMenus = (SshSysMenus) fistMenu.get(i);
				sbStr.append("<div class='").append(treeHead).append("'>");
				sbStr.append("<h2><span>Folder</span>");
				sbStr.append(sshSysMenus.getMenuName());
				sbStr.append("</h2></div>");
				sbStr.append("<div class='").append(treeContent).append("'>");
				sbStr.append("<ul class='").append(treeStyle).append("'>");
				//迭代拼接此一级菜单的树形结构
				sbStr.append(this.iterationMenus(allChildrenMeunList, sshSysMenus.getMenuId()));
				sbStr.append("</ul></div>");
			}
	    //如果前台有传入树形一级菜单的ID，则返回这个一级菜单的树	
		}else{
			SshSysMenus sshSysMenus = menusApplogic.findByField("menuId", treeMenuId);
			sbStr.append("<div class='").append(treeHead).append("'>");
			sbStr.append("<h2><span>Folder</span>");
			sbStr.append(sshSysMenus.getMenuName());
			sbStr.append("</h2></div>");
			sbStr.append("<div class='").append(treeContent).append("'>");
			sbStr.append("<ul class='").append(treeStyle).append("'>");
			//迭代拼接此一级菜单的树形结构
			sbStr.append(this.iterationMenus(allChildrenMeunList, sshSysMenus.getMenuId()));
			sbStr.append("</ul></div>");
		}
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
		HttpServletRequest request = (HttpServletRequest) pageContext.getRequest();
		StringBuffer sbStr = new StringBuffer("");
		for (int i = 0; i < allChildrenMenus.size(); i++) {
			SshSysMenus sshSysMenus = (SshSysMenus) allChildrenMenus.get(i);
			if (firstMenuId.equals(sshSysMenus.getParentId())) {
				//如果链接地址为空，则为父菜单，不提供链接处理
				if (StringUtils.isEmpty(sshSysMenus.getMenuUrl())) {
					sbStr.append("<li><a href='javascript:;'>");
				}else{
					sbStr.append("<li><a href='").append(request.getContextPath()).append(sshSysMenus.getMenuUrl()).append("' target=\"navTab\"");
					sbStr.append(" rel=\"").append(sshSysMenus.getMenuKey()).append("\">");
				}
				sbStr.append(sshSysMenus.getMenuName());
				sbStr.append("</a>");
				sbStr.append("<ul>");
				sbStr.append(this.iterationMenus(allChildrenMenus, sshSysMenus.getMenuId()));
				sbStr.append("</ul>");
				sbStr.append("</li>");
			}
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
