package reactspring_login.reactspring_login;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.convert.threeten.Jsr310JpaConverters;

import javax.annotation.PostConstruct;
import java.util.TimeZone;


@EntityScan(basePackageClasses = {
		Jsr310JpaConverters.class,
		ReactSpringLoginApplication.class
})
@SpringBootApplication
public class ReactSpringLoginApplication {

	public static void main(String[] args) {
		SpringApplication.run(ReactSpringLoginApplication.class, args);
	}


	@PostConstruct
	void init() {
		TimeZone.setDefault(TimeZone.getTimeZone("UTC"));
	}
}
