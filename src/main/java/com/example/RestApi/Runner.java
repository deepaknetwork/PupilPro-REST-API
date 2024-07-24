package com.example.RestApi;

import java.time.LocalDate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import com.example.RestApi.Database.DatabaseService;
import com.example.RestApi.Database.StudentRepository;
import com.example.RestApi.Database.Students;
import com.example.RestApi.Database.TeacherRepository;
import com.example.RestApi.Database.Teachers;

@Component
public class Runner implements CommandLineRunner {

	@Autowired
	TeacherRepository teacherrepository;
	
	@Autowired
	StudentRepository studentrepository;
	
	@Autowired
	DatabaseService db;
	
	@Override
	public void run(String... args) throws Exception {
		// TODO Auto-generated method stub
		teacherrepository.save(new Teachers(1,"deepak","INFORMATION TECHNOLOGY","M.TECT-IT",LocalDate.now().minusYears(30),9843270038L));
		teacherrepository.save(new Teachers(2,"dhivya","INFORMATION TECHNOLOGY","M.TECT-IT",LocalDate.now().minusYears(30),9843270038L));
		
		db.addstudents(new Students(1,"Mahesh","INFORMATION TECHNOLOGY",2,LocalDate.now().minusYears(20),9843270038L,new Teachers(2,"dhivya","INFORMATION TECHNOLOGY","M.TECT-IT",LocalDate.now().minusYears(30),9843270038L)));
		db.addstudents(new Students(2,"Kowshik","INFORMATION TECHNOLOGY",2,LocalDate.now().minusYears(19),9843270038L,new Teachers(2,"dhivya","INFORMATION TECHNOLOGY","M.TECT-IT",LocalDate.now().minusYears(30),9843270038L)));
		db.addstudents(new Students(3,"Naren","INFORMATION TECHNOLOGY",2,LocalDate.now().minusYears(19),9843270038L,new Teachers(1,"deepak","INFORMATION TECHNOLOGY","M.TECT-IT",LocalDate.now().minusYears(30),9843270038L)));
	}

}
