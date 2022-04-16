package Spring_react_01.Spring_react_01;

import Spring_react_01.Spring_react_01.model.User;
import Spring_react_01.Spring_react_01.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class SpringReact01Application implements CommandLineRunner {

	public static void main(String[] args) {
		SpringApplication.run(SpringReact01Application.class, args);
	}

	@Autowired
	private UserRepository userRepository;

	@Override
	public void run(String... args) throws Exception {
		this.userRepository.save(new User("LEE", "Sin", "LeeSin@gmail.com"));
		this.userRepository.save(new User("LEE", "Sin", "LeeSin@gmail.com"));
		this.userRepository.save(new User("LEE", "Sin", "LeeSin@gmail.com"));
	}



}
