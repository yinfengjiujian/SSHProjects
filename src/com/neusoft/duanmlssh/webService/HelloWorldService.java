package com.neusoft.duanmlssh.webService;

import javax.jws.WebMethod;
import javax.jws.WebService;
import javax.jws.soap.SOAPBinding;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;

import com.neusoft.duanmlssh.webService.pojo.Cat;

@Service("helloWordService")
@WebService(targetNamespace="com.neusoft.duanmlssh.webService")
@SOAPBinding(parameterStyle = SOAPBinding.ParameterStyle.BARE)
public class HelloWorldService {
	
	private static Logger log = Logger.getLogger(HelloWorldService.class); 
	
	@WebMethod
	public String sayHello(Cat cat){
		log.info("HelloWorldService WebService  *******************");
		return "Hello: WebService 开始调用----> "  + cat.getName();
	}
	
	
}
