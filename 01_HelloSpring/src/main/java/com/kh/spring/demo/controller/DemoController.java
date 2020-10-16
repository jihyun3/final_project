package com.kh.spring.demo.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.kh.spring.demo.model.service.DemoService;
import com.kh.spring.demo.model.vo.Demo;

//web mvc 프로젝트에서 controller 객체를 spring-bean으로 등록할때는
//@Controller를 사용함.


@Controller
public class DemoController {
	
	@Autowired
	@Qualifier("service")
	private DemoService service;
	
	//클라이언트가 요청한 주소랑 매핑되는 메소드를 생성해서 처리
	//주소와 매핑을 하기위해서는 @ReqeustMapping을 사용
	
	@RequestMapping("/demo/demo.do")
	public String demo() {
		System.out.println("mapping매소드 호출");
		return "demo/demo";//ReqeustDispatcher.forward()
	}
	
	
	//클라이언트가 전송한 데이터나 요청내용을 mapping매소드에서 처리하려면 
	//HttpServletRequest.get
	//각정보를 매개변수로 선언해서 처리할 수가 있음
	//매개변수를 선언하면 자료형에 맞는 값을 spring이 대입해줌
	//스프링이 받을수잇는 타입  mapping매소드의 매개변수로 선언할 수 있는 타입
	// HttpServletRqeust / HttpServletResponse 객체 
	// HttpSession <- 서블릿에선 request.getSession()으로 불러와야 했음 
	// java.util.Locale <- 서버가 실행되는 지역을 가져옴 ko-kr ,us ,ja-jp이런거 
	// InputStream, Reader 
	// OutputStream, Writer
	
	//파라미터값, 서버의 값을 보관하는 객체 선언가능 
	//Map, Model, ModelAndView
	//파라미터값을 받는 VO객체 (ex:Person Member Product)
	
	//파라미터에 어노테이션을 통해서 특정값을 받아올 수 있음
	//매개변수 선언 위채에 사용 (@RequestParam("abc") String alpa)
	//@RequestParam(value="name") String name
	//@ReqeustHeader(value="해더키값") 헤더의 정보들
	//@CookieValue(value="쿠키키값") String name
	
	//@RequestMapping(value=/demo/demo/{값})
	//@PathValiable("값") String name -> Rest방식으로 주소를 작성했을 때
	
	//@RequestMapping밑에 
	//@ResponseBody -> 클라이언트에게 json방식으로 값을 전송할때  * springBoot에서 default로 선언됨.
	//@RequestBody -> 클라이언트에게 json방식으로 값을 받을때
	//* 객체 매핑 spring bean을 등록을 해줘야함. -> jackson
	
	
	
	
	
	@RequestMapping("demo/demo")
	public void demo1(HttpSession session ,HttpServletRequest request, HttpServletResponse response)throws ServletException,IOException {
		//기본 서블릿과 동일하게 mapping매소드를 이용해보자 
		
		String devName = request.getParameter("devName");
		int devAge = Integer.parseInt(request.getParameter("devAge"));
		String devEmail = request.getParameter("devEmail");
		String devGender = request.getParameter("devGender");
		String[] devLang = request.getParameterValues("devLang");
		
		
		System.out.println(devName+devAge+devEmail+devGender);
		for(String l : devLang) {
			System.out.println(l+" ");
			System.out.println("");
			
		}
		System.out.println(devLang.length);
		
		//=세션 생성
		//HttpSession session=request.getSession();
		Demo demo = Demo.builder().devName(devName).devAge(devAge).devEmail(devEmail).devGender(devGender).devLang(devLang).build();
//		Demo demo= new Demo(0,devName,devAge,devEmail,devGender,devLang);
		request.setAttribute("demo", demo);
		session.setAttribute("userId", devName);
		
//		response.setContentType("text/html;charset=utf-8");
//		response.getWriter().write("파라미터 완료");
		
		request.getRequestDispatcher("/WEB-INF/views/demo/demoResult.jsp").forward(request, response);
		
		
		
	}
	
	//@RequestParam으로 클라이언트 데이터 받기
	@RequestMapping("/demo/demo2")
	//@ReqeustParam("파라미터 key값")으로 값을 보내면   null일때 400에러가 발생  이럴때
	//@RequestParam(value="devAge", defaultValue="0")이런형식으로  defaultValue를 넣어주면 된다 
//	public String demo2(
//			@RequestParam(value="devName")String devName,
//			@RequestParam(value="devAge", defaultValue="0")int devAge, 
//			@RequestParam(value="devEmail", required = false)String devEmail , 
//			@RequestParam(value="devGender")String devGender, 
//			@RequestParam(value="devLang", required = false)String[] devLang) {
	public String demo2(String devName, int devAge, String devEmail, String devGender, String[] devLang, Model model) {
		//스프링에서 서버에서 작성핰 데이트를 보관하려면,
		//HttpServletRequest, HttpSession도 가능하지만 !@#!@#
		//Model객체를 이용하여 데이터를 보관할 수 있음. -> scope영역은 request와 동일 
		//Model객체를 이용하는 방법은 request와 비슷
		//key, value형으로 값을 넣으면 addAttribute매소드를 이용함
		//addAttribute(String, Object);
		model.addAttribute("demo", Demo.builder().devName(devName).devAge(devAge).devEmail(devEmail).devGender(devGender).devLang(devLang).build());
		System.out.println(devName+" "+devAge+" "+devEmail+" "+devGender+" "+devLang+" "+model);
		
		return "demo/demoResult";
	}
	
	//Command로 데이터 처리하기 -> 바로 vo객체로 데이터
	@RequestMapping("demo/demo3")
	public String demo3(Demo demo, Model m) {
		System.out.println(demo);
		return "demo/demoResult";
	}

}
