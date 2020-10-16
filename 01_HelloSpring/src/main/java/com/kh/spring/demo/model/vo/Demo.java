package com.kh.spring.demo.model.vo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

//vo객체를 만들때 중복코드가 많아~
//vo객체의 생성자, setter,getter,toString, equals, hashcode 자동으로 생성해주는 
//라이브러리가 있음 .=> lombock

/*@Setter
@Getter*/


@Data
@AllArgsConstructor//매개변수잇는 생성자//
@Builder//newDemo().Build().devName("유병승").age(19).build();
//@NoArgsConstructor//기본 생성자*/
public class Demo{
	
	//private int devNo;
	private String devName;
	private int devAge;
	private String devEmail;
	private String devGender;
	private String[] devLang;

}
