package com.example.RestApi.Database;

import java.time.LocalDate;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Transient;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Past;
import jakarta.validation.constraints.Size;

@Entity
public class Students {

	@Id
	@GeneratedValue
	private int id;
	@Size(min=5,message="name mininum 5 characters")
	private String name;
	@Size(min=2,message="dept mininum 2 characters")
	private String department;
	@Min(value=1,message="year is min 1")
	@Max(value=4,message="year is max 4")
	private int acadamic_year;
	@Past(message="enter valid dob")
	private LocalDate date_of_birth;
	private long contact_number;
	
	@ManyToOne
	//@JsonIgnore
	private Teachers teachers;
	
	@Transient
	@NotNull
	@JsonIgnore
	private int teacher_id;
	
	
	
	public Students(int id,String name,String department,int year,LocalDate date_of_birth,long contact_number,Teachers teachers) {
		super();
		this.id = id;
		this.name = name;
		this.department = department;
		this.acadamic_year = year;
		this.date_of_birth = date_of_birth;
		this.contact_number = contact_number;
		this.teachers = teachers;
	}
	public Students() {
		
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getDepartment() {
		return department;
	}
	public void setDepartment(String department) {
		this.department = department;
	}
	public int getYear() {
		return acadamic_year;
	}
	public void setYear(int year) {
		this.acadamic_year = year;
	}
	public LocalDate getDate_of_birth() {
		return date_of_birth;
	}
	public void setDate_of_birth(LocalDate date_of_birth) {
		this.date_of_birth = date_of_birth;
	}
	public long getContact_number() {
		return contact_number;
	}
	public void setContact_number(long contact_number) {
		this.contact_number = contact_number;
	}
	public Teachers getTeachers() {
		return teachers;
	}
	public void setTeachers(Teachers teachers) {
		this.teachers = teachers;
	}
	public int getTeacher_id() {
		return teacher_id;
	}
	public void setTeacher_id(int teacher_id) {
		this.teacher_id = teacher_id;
	}
	
	

}
