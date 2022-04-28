package comjava.service;

import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class StudentService {

	List<Student> students = List.of(new Student(1, "Ryan"), new Student(2, "Adam"));

	public List<Student> getList() {

		return students;
	}

	public void addStudent(Student student) {
		students.add(student);
	}

	public void deleteStudent(long studentId) {
		students.remove(studentId);
	}
}
