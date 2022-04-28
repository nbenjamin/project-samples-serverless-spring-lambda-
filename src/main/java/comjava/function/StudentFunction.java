package comjava.function;

import com.amazonaws.services.lambda.runtime.events.APIGatewayProxyRequestEvent;
import comjava.service.Student;
import comjava.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.Message;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.function.Function;

@Component
public class StudentFunction implements Function<Message<String>, List<Student>> {

    @Autowired
    private StudentService studentService;


    @Override
    public List<Student> apply(Message<String> apiGatewayProxyRequestEvent) {
        System.out.println(apiGatewayProxyRequestEvent.getHeaders());
        return studentService.getList();
    }
}
