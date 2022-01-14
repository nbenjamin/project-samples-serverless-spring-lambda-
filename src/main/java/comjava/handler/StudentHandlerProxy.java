package comjava.handler;

import org.springframework.cloud.function.adapter.aws.SpringBootRequestHandler;

import com.amazonaws.services.lambda.runtime.events.APIGatewayProxyRequestEvent;

public class StudentHandlerProxy extends SpringBootRequestHandler<APIGatewayProxyRequestEvent, Object> {
}
