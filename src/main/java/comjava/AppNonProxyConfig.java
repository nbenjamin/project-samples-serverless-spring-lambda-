package comjava;

import java.util.List;
import java.util.Map;
import java.util.function.Function;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import comjava.service.Student;
import comjava.service.StudentService;

@Configuration
public class AppNonProxyConfig {

	@Autowired
	private StudentService studentService;

	@Bean
	public Function<Map<Object, Object>, List<Student>> getList() {

		return (proxyRequestEvent) -> studentService.getList();
	}

	@Bean
	public Function<Map<Object, Object>, List<Student>> addStudent() {

		return (mapValues) -> {

			Student student = new Student(Long.valueOf((String) mapValues.get("id")),
					String.valueOf(mapValues.get("name")));

			studentService.addStudent(student);
			return studentService.getList();
		};
	}

	@Bean
	public Function<Map<Object, Object>, List<Student>> deleteStudent() {

		return (mapValues) -> {

			long id = (long) mapValues.get("id");
			studentService.deleteStudent(id);

			return studentService.getList();
		};
	}
}
