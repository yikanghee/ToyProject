package MOBEE.MOBEE;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.List;

@RestController
public class HelloWorld {

    @GetMapping("hello")
    public List<String> hello() {
        return Arrays.asList("안녕하세요", "Hello");
    }
}
