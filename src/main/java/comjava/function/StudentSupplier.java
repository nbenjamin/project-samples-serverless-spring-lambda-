package comjava.function;

import comjava.service.Student;
import comjava.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.Message;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.function.Function;
import java.util.function.Supplier;

@Component
public class StudentSupplier {}
      /*  implements Supplier<List<Student>>{

    @Autowired
    private StudentService studentService;


 *//*   @Override
    public List<Student> apply() {
        System.out.println(apiGatewayProxyRequestEvent);
        return studentService.getList();
    }*//*
}
*/