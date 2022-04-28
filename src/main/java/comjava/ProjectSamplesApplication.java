package comjava;

import com.amazonaws.services.lambda.runtime.events.APIGatewayProxyRequestEvent;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.function.context.FunctionRegistry;
import org.springframework.context.annotation.Bean;

import java.util.function.Function;

@SpringBootApplication
public class ProjectSamplesApplication {

	@Bean
	public CommandLineRunner runner(FunctionRegistry registry) {
		return args -> {
			System.err.println(registry.getNames(Function.class));
		};
	}

	@Bean public Function<APIGatewayProxyRequestEvent, String> reverseString1() {
		return value1 -> {
			System.out.println("headers..." + value1.getHeaders());
			System.out.println("paylaod..." + value1.getPathParameters());
			return "sample"; };
	}

	public static void main(String[] args) {
		SpringApplication.run(ProjectSamplesApplication.class, args);
	}
}
