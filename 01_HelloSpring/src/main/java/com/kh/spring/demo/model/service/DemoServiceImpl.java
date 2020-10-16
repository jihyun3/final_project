package com.kh.spring.demo.model.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kh.spring.demo.model.dao.DemoDao;

//web mvc 프로젝트에서 service역활을 하는 객체를 spring-bean으로 등록하려면
//@Service를 이용함

@Service("service")
public class DemoServiceImpl implements DemoService{
	
	@Autowired
	private DemoDao dao;
}
