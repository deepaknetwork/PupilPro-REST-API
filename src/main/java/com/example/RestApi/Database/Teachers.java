package com.example.RestApi.Database;

import java.time.LocalDate;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Past;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;

@Entity
public class Teachers {

@Id
@GeneratedValue
private int id;
@Size(min=5,message="name mininum 5 characters")
@NotNull
private String name;
@Size(min=2,message="dept mininum 2 characters")
@NotNull
private String department;
@Size(min=5,message="qualify mininum 5 characters")
@NotNull
private String qualification;
@Past(message="enter valid dob")
@NotNull
private LocalDate date_of_birth;
@Positive(message="provide valid phone no")
@NotNull
private long contact_number;
@OneToMany(mappedBy="teachers")
@JsonIgnore
private List<Students> students;


public Teachers(int id, String name, String department, String qualification, LocalDate date_of_birth,
		long contact_number) {
	super();
	this.id = id;
	this.name = name;
	this.department = department;
	this.qualification = qualification;
	this.date_of_birth = date_of_birth;
	this.contact_number = contact_number;
}

public Teachers() {
	
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
public String getQualification() {
	return qualification;
}
public void setQualification(String qualification) {
	this.qualification = qualification;
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
public List<Students> getStudents() {
	return students;
}
public void setStudents(List<Students> students) {
	this.students = students;
}


}
