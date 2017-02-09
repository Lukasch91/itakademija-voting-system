package vs;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class LinkController {

	@RequestMapping(value = "/admin")
	public String admin() {
		return "admin";
	}
	
	@RequestMapping(value = "/rep")
	public String representative() {
		return "rep";
	}
	
	@RequestMapping(value = "/login")
	public String login() {
		return "login";
	}
	
}
