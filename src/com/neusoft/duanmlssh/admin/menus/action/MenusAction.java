package com.neusoft.duanmlssh.admin.menus.action;

import java.io.IOException;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONObject;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import com.neusoft.duanmlssh.admin.menus.action.form.MenuForm;
import com.neusoft.duanmlssh.admin.menus.interaction.MenusInter;
import com.neusoft.duanmlssh.comment.DateUtils;
import com.neusoft.duanmlssh.comment.MutualObject;
import com.neusoft.duanmlssh.comment.json.impl.DataCenter;
import com.neusoft.duanmlssh.comment.json.impl.DataStore;
import com.neusoft.duanmlssh.comment.json.impl.Row;
import com.neusoft.duanmlssh.comment.json.io.DataCenterIOManager;
import com.neusoft.duanmlssh.comment.tool.CommontTool;
import com.neusoft.duanmlssh.pojo.SshSysMenus;

@Controller
@RequestMapping("/admin/menu/")
public class MenusAction {
	
	@Autowired
	public MenusInter menusInter;
	
	@RequestMapping(value = "/menuManage.do")
	public String mainMunus(HttpServletRequest request,
			HttpServletResponse response, ModelMap model) {
		return "admin/menus/mainMenus";
	}
	
	@RequestMapping(value = "/menuList.do")
	public void menuList(HttpServletRequest request,String menuId,HttpServletResponse response, ModelMap model) throws IOException,Exception {
		DataCenter dc = new DataCenter();
		if (StringUtils.isNotBlank(menuId)) {
			SshSysMenus menuObject = menusInter.getMenusById(menuId);
			Row row = new Row(JSONObject.fromObject(menuObject), 0);
			DataStore ds = new DataStore("meuninfo");
			ds.getRowSet().addRow(row);
			dc.addDataStore(ds);
			dc.setCode(1);
		}else{
			dc.setCode(-1);
			dc.setTitle("无法查询到相关的菜单信息！");
		}
		DataCenterIOManager.createWriter(response.getOutputStream()).write(dc);
	}
	
	@RequestMapping(value = "/menuadd.do")
	public String menuAdd(String menuid,HttpServletRequest request,HttpServletResponse response, ModelMap model) {
		model.put("menuid",CommontTool.getUUID());
		model.put("parmentid",menuid);
		model.put("authordate", DateUtils.dataFormate(new Date()));
		return "admin/menus/addMenu";
	}
	
	@RequestMapping(value = "/menuedit.do")
	public String menuEdit(String menuid,HttpServletRequest request,HttpServletResponse response, ModelMap model) {
		if (StringUtils.isNotBlank(menuid)) {
			SshSysMenus menuObject = menusInter.getMenusById(menuid);
			model.put("authordate", DateUtils.dataFormate(new Date()));
			model.put("menuinfo",menuObject);
			return "admin/menus/editMenu";
		}else{
			return null;
		}
	}
	
	@RequestMapping(value = "/menudelete.do")
	public void menuDelete(String menuid,HttpServletRequest request,HttpServletResponse response, ModelMap model) throws IOException,Exception{
		DataCenter dc = new DataCenter();
		if (StringUtils.isNotBlank(menuid)) {
			if (menuid.equals("8a84f69b3e87563c013e875aff210013")) {
				dc.setStatusCode(300);
				dc.setMessage("菜单管理的功能模块系统不允许删除，请联系管理员！");
			} else {
				MutualObject mutual = menusInter.deleteMenu(menuid);
				if (mutual.getResultFlag()) {
					dc.setStatusCode(200);
					dc.setNavTabId("admin_cdgl");
					dc.setMessage("菜单删除成功！");
				} else {
					dc.setStatusCode(300);
					dc.setMessage(mutual.get("errMesg").toString());
				}
			}
		}else{
			dc.setStatusCode(300);
			dc.setMessage("菜单删除失败，菜单ID值为空！");
		}
		DataCenterIOManager.createWriter(response.getOutputStream()).write(dc);
	}
	
	@RequestMapping(value = "/menuToAdd.do")
	public void menuToAdd(MenuForm menuForm, HttpServletRequest request,HttpServletResponse response, ModelMap model) 
			throws IOException,Exception {
		DataCenter dc = new DataCenter();
		MutualObject mutual = menusInter.saveMenu(menuForm);
		if (mutual.getResultFlag()) {
			dc.setStatusCode(200);
			dc.setCallbackType("closeCurrent");
			dc.setNavTabId("admin_cdgl");
			dc.setMessage("子菜单添加成功！");
		} else {
			dc.setStatusCode(300);
			dc.setMessage("子菜单添加失败，请联系管理员！"+mutual.get("errMesg"));
		}
		DataCenterIOManager.createWriter(response.getOutputStream()).write(dc);
	}
	
	@RequestMapping(value = "/menuToEdit.do")
	public void menuToEdit(MenuForm menuForm, HttpServletRequest request,HttpServletResponse response, ModelMap model) 
			throws IOException,Exception {
		DataCenter dc = new DataCenter();
		MutualObject mutual = menusInter.updateMenu(menuForm);
		if (mutual.getResultFlag()) {
			dc.setStatusCode(200);
			dc.setCallbackType("closeCurrent");
			dc.setNavTabId("admin_cdgl");
			dc.setMessage("菜单信息修改成功！");
		} else {
			dc.setStatusCode(300);
			dc.setMessage(mutual.get("errMesg").toString());
		}
		DataCenterIOManager.createWriter(response.getOutputStream()).write(dc);
	}
}
