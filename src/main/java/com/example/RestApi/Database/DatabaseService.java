package com.example.RestApi.Database;

import java.util.List;
import org.springframework.stereotype.Repository;

import com.example.RestApi.Exceptions.noTeacher;

import jakarta.transaction.Transactional;

@Repository
@Transactional
public class DatabaseService {

	
	TeacherRepository teacherrepository;
	StudentRepository studentrepository;
	
	public DatabaseService(TeacherRepository teacherrepository,StudentRepository studentrepository) {
		this.teacherrepository=teacherrepository;
		this.studentrepository=studentrepository;
	}
	
	//Teachers table
	public List<Teachers> showallteachers() {
		return teacherrepository.findAll();
	}
	
	public Teachers showoneteachers(int id) {
		return teacherrepository.findById(id);
	}
	
	public Teachers addteachers(Teachers teachers) {
		return teacherrepository.save(teachers);
	}
	
	public List<Students> showstudentsofteachers(int id) {
		return teacherrepository.findById(id).getStudents();
	}
	
	public void deleteteachers(int id) {
		teacherrepository.deleteById(id);
	}
	
	public List<Students> showallstudents() {
		return studentrepository.findAll();
	}
	
	public void updateteacher(int id,Teachers teachers) {
		Teachers foundteacher=teacherrepository.findById(id);
		if(foundteacher==null) {
			throw new noTeacher("no teacher found with id:"+id+"to update ");
		}
		foundteacher.setName(teachers.getName());
		foundteacher.setDepartment(teachers.getDepartment());
		foundteacher.setDate_of_birth(teachers.getDate_of_birth());
		foundteacher.setQualification(teachers.getQualification());
		foundteacher.setContact_number(teachers.getContact_number());
		
	}
	
	public void patchupdateteacher(int id,Teachers teachers) {
		Teachers foundteacher=teacherrepository.findById(id);
		if(foundteacher==null) {
			throw new noTeacher("no teacher found with id:"+id+" to update");
		}
		if(teachers.getName()!=null) {
			foundteacher.setName(teachers.getName());
		}
		if(teachers.getDepartment()!=null) {
			foundteacher.setDepartment(teachers.getDepartment());
		}
		if(teachers.getQualification()!=null) {
			foundteacher.setQualification(teachers.getQualification());
		}
		if(teachers.getDate_of_birth()!=null) {
			foundteacher.setDate_of_birth(teachers.getDate_of_birth());
		}
		long contact_number=teachers.getContact_number();
		if(contact_number>100000000) {
			foundteacher.setContact_number(teachers.getContact_number());
		}
	
	}
	
	//Students table
	public Students showonestudents(int id) {
		return studentrepository.findById(id);
	}
	
	public Students addstudents(Students students) {
		return studentrepository.save(students);
	}
	
	public void deletestudent(int id) {
		studentrepository.deleteById(id);
	}
	
	public void updatestudent(int id,Students students) {
		Students foundstudent=studentrepository.findById(id);
		if(foundstudent==null) {
			throw new noTeacher("no student found with id:"+id+" to update");
		}
		Teachers foundteacher=teacherrepository.findById(students.getTeacher_id());
		if(foundteacher==null) {
			throw new noTeacher("no teacher found with id:"+students.getTeacher_id()+"to update student id:"+students.getId());
		}
		foundstudent.setName(students.getName());
		foundstudent.setDepartment(students.getDepartment());
		foundstudent.setYear(students.getYear());
		foundstudent.setDate_of_birth(students.getDate_of_birth());
		foundstudent.setTeachers(foundteacher);
		foundstudent.setContact_number(students.getContact_number());
		
	}
	
	public void patchupdatestudent(int id,Students students) {
		Students foundstudent=studentrepository.findById(id);
		if(foundstudent==null) {
			throw new noTeacher("no student found with id:"+id+" to update");
		}
		if(students.getName()!=null) {
			foundstudent.setName(students.getName());
		}
		if(students.getDepartment()!=null) {
			foundstudent.setDepartment(students.getDepartment());
		}
		int year=students.getYear();
		if(year>=1&&year<=4) {
			foundstudent.setYear(students.getYear());
		}
		if(students.getDate_of_birth()!=null) {
			foundstudent.setDate_of_birth(students.getDate_of_birth());
		}
		int teacher_id=students.getTeacher_id();
		if(teacher_id!=0) {
			Teachers foundteacher=teacherrepository.findById(students.getTeacher_id());
			if(foundteacher==null) {
				throw new noTeacher("no teacher found with id:"+students.getTeacher_id()+"to update student id:"+students.getId());
			}
			foundstudent.setTeachers(foundteacher);
		}
		long contact_number=students.getContact_number();
		if(contact_number>100000000) {
			foundstudent.setContact_number(students.getContact_number());
		}
	}
	
}
