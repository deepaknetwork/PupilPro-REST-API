package com.example.RestApi.Controller;

import java.net.URI;
import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.example.RestApi.Database.DatabaseService;
import com.example.RestApi.Database.Students;
import com.example.RestApi.Database.Teachers;
import com.example.RestApi.Exceptions.noTeacher;

import jakarta.validation.Valid;

@RestController
public class StudentsController {

	DatabaseService databaseservice;
	
	public StudentsController(DatabaseService databaseservice) {
		this.databaseservice=databaseservice;
	}
	
	@GetMapping("/students")
	public List<Students> showallstudents() {
		return databaseservice.showallstudents();
	}
	
	@GetMapping("/students/{id}")
	public Students showonestudents(@PathVariable int id) {
		Students students=databaseservice.showonestudents(id);
		
		if(students==null) {
			throw new noTeacher("No student with id:"+id);
		}
		return students;
	}
	
	@PostMapping("/students")
	public ResponseEntity<Object> addstudents(@Valid @RequestBody Students student) {
		Teachers teachers=databaseservice.showoneteachers(student.getTeacher_id());
		
		if(teachers==null) {
			throw new noTeacher("teacher not found with id:"+student.getTeacher_id());
		}
		student.setTeachers(teachers);
		Students savedstudent=databaseservice.addstudents(student);
		URI location=ServletUriComponentsBuilder.
				fromCurrentRequest()
				.path("/{id}")
				.buildAndExpand(savedstudent.getId())
				.toUri();
		return ResponseEntity.created(location).build();
	}
	
	@DeleteMapping("/students/{id}")
	public void deletestudent(@PathVariable int id) {
		databaseservice.deletestudent(id);
	}
	
	@PutMapping("/students/{id}")
	public void updatestudent(@PathVariable int id,@Valid @RequestBody Students students) {
		databaseservice.updatestudent(id, students);
	}
	
	@PatchMapping("/students/{id}")
	public void patchupdatestudent(@PathVariable int id,@RequestBody Students students) {
		databaseservice.patchupdatestudent(id, students);
	}
	
	
	
	
}
