package com.example.RestApi.Controller;
import java.net.URI;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
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
public class TeachersController {

	DatabaseService databaseservice;
	
	public TeachersController(DatabaseService databaseservice) {
		this.databaseservice=databaseservice;
	}
	
	@GetMapping("/teachers")
	public List<Teachers> showallteachers() {
		return databaseservice.showallteachers();
	}
	
	@GetMapping("/teachers/{id}")
	public Teachers showoneteachers(@PathVariable int id) {
		Teachers teachers=databaseservice.showoneteachers(id);
		
		if(teachers==null) {
			throw new noTeacher("teacher not found with id:"+id);
		}
		return teachers;
	}
	
	@PostMapping("/teachers")
	public ResponseEntity<Object> addteachers(@Valid @RequestBody Teachers teachers) {
		Teachers savedteachers=databaseservice.addteachers(teachers);
		URI location=ServletUriComponentsBuilder.fromCurrentRequest()
				.path("/{id}")
				.buildAndExpand(savedteachers.getId())
				.toUri();
		return ResponseEntity.created(location).build();
	}
	
	@DeleteMapping("/teachers/{id}")
	public void deleteteacher(@PathVariable int id) {
		databaseservice.deleteteachers(id);
		
	}
	
	@GetMapping("/teachers/{id}/students")
	public List<Students> showstudentsodteacher(@PathVariable int id) {
	
		Teachers teachers=databaseservice.showoneteachers(id);
		
		if(teachers==null) {
			throw new noTeacher("teacher not found with id:"+id);
		}
		return databaseservice.showstudentsofteachers(id);
	}
	
	@PutMapping("/teachers/{id}")
	public void updatestudent(@PathVariable int id,@Valid @RequestBody Teachers teachers) {
		databaseservice.updateteacher(id, teachers);
	}
	
	@PatchMapping("/teachers/{id}")
	public void patchupdatestudent(@PathVariable int id,@RequestBody Teachers teachers) {
		databaseservice.patchupdateteacher(id, teachers);
	}
	
}
