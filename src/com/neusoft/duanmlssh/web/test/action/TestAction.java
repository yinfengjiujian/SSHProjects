package com.neusoft.duanmlssh.web.test.action;

import java.util.Date;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class TestAction {
	
	@RequestMapping(value="/test/{select}/cache1.html")
	 public String cache1(ModelMap model,@PathVariable(value="select") String select){  
		System.out.println(select);
        model.addAttribute("date1", new Date().toString());  
        model.addAttribute("date2", new Date().toString());  
        model.addAttribute("date3", new Date().toString());  
        return "test/jsp/cache1";  
	 }  
	
	@RequestMapping(value="/test/{select}/cache2.html")
	public String cache2(ModelMap model,@PathVariable(value="select") String select){  
		System.out.println(select);
		return "test/jsp/cache2";  
	}  
}
