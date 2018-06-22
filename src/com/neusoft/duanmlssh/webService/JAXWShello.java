package com.neusoft.duanmlssh.webService;

import javax.jws.WebMethod;
import javax.jws.WebService;
import javax.jws.soap.SOAPBinding;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;

import com.neusoft.duanmlssh.webService.pojo.Cat;
import com.neusoft.duanmlssh.webService.pojo.Tagger;

@Service("jaxwshello")
@WebService(targetNamespace="com.neusoft.duanmlssh.webService")
@SOAPBinding(parameterStyle = SOAPBinding.ParameterStyle.BARE)
public class JAXWShello {
	
	private static Logger log = Logger.getLogger(JAXWShello.class); 
	
	@WebMethod
	public Cat sayHello(String name){
		log.info("JAXWShello WebService is start***************************");
		Cat cat = new Cat();
		cat.setName("段美林");
		cat.setAge(28);
		Tagger ta =  new Tagger();
		ta.setName("tagger");
		cat.setTagger(ta);
		log.info("JAXWShello WebService is end***************************");
		return cat;
	}
	
	
}
