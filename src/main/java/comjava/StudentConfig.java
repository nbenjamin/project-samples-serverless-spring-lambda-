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
public class StudentConfig {

	@Autowired
	private StudentService studentService;

	@Bean
	public Function<APIGatewayProxyRequestEvent, List<Student>> getListProxy() {

		return (proxyRequestEvent) -> {
			System.out.println(proxyRequestEvent.getHeaders());
			System.out.println(proxyRequestEvent.getQueryStringParameters());
			System.out.println(proxyRequestEvent.getPathParameters());
			return  studentService.getList();
		};
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
}
