package comjava;

import java.util.List;
import java.util.function.Function;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.amazonaws.services.lambda.runtime.events.APIGatewayProxyRequestEvent;

import comjava.service.Student;
import comjava.service.StudentService;

@Configuration
public class AppProxyConfig {

	@Autowired
	private StudentService studentService;

	@Bean
	public Function<APIGatewayProxyRequestEvent, List<Student>> getListProxy() {

		return (proxyRequestEvent) -> studentService.getList();
	}

	@Bean
	public Function<APIGatewayProxyRequestEvent, List<Student>> addStudentProxy() {

		return (mapValues) -> {
			String body = mapValues.getBody();
			Student student = new Student(1, body);

			studentService.addStudent(student);
			return studentService.getList();
		};
	}

	@Bean
	public Function<APIGatewayProxyRequestEvent, List<Student>> deleteStudentProxy() {

		return (mapValues) -> {

			long id = Long.valueOf(String.valueOf(mapValues.getPathParameters().get("id")));
			studentService.deleteStudent(id);

			return studentService.getList();
		};
	}
}
