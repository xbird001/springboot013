package springboot010;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class UserController {
	
	@GetMapping("/createUser")
	@ResponseBody
	public String createUser() {
		for(int i=0; i<10; i++) {
			System.out.println("==============================");
		}
		return "create user";
	}

}
