package SpringReact.SpringReact;

import SpringReact.SpringReact.domain.Currency;
import SpringReact.SpringReact.domain.User;
import SpringReact.SpringReact.repository.CurrencyRepository;
import SpringReact.SpringReact.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.math.BigDecimal;

@SpringBootApplication
public class SpringReactApplication {

	@Autowired
	private CurrencyRepository currencyRepository;

	@Autowired
	private UserRepository userRepository;

	public static void main(String[] args) {
		SpringApplication.run(SpringReactApplication.class, args);
	}

	@Bean
	CommandLineRunner runner() {
		return args -> {

			userRepository.save(new User("admin", "$2a$04$KNLUwOWHVQZVpXyMBNc7JOzbLiBjb9Tk9bP7KNcPI12ICuvzXQQKG"));

			// save demo data after start
			currencyRepository.save(new Currency("US Dolar", "USD", new BigDecimal(100), new BigDecimal(3.92)));
			currencyRepository.save(new Currency("Euro", "EUR", new BigDecimal(300), new BigDecimal(4.52)));

		};

	}

}
