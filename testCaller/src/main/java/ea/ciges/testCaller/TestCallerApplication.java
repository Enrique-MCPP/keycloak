package ea.ciges.testCaller;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
public class TestCallerApplication {

	public static void main(String[] args) {
		SpringApplication.run(TestCallerApplication.class, args);
	}

}
