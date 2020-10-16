package com.kh.spring;

import java.util.Locale;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * Handles requests for the application home page.
 */
@Controller //spring bean으로 등록, 역활을 지정 -> controller(servlet의 역활)
public class HomeController {
	
	private static final Logger logger = LoggerFactory.getLogger(HomeController.class);
	
	@Autowired
	@Qualifier("test1")
	private BeanTest test;
	
	@Autowired
	@Qualifier("test3")
	private BeanTest test2;
	
	/**
	 * Simply selects the home view to render by returning its name.
	 */
	@RequestMapping(value = "/", method = RequestMethod.GET)//doGet, doPost역활
	public String home(Locale locale, Model model) {
		System.out.println("BeanTest 출력 :"+test.getName());
		System.out.println("BeanTest 출력2 :"+test2.getName());
		return "index";
	}
	
}
