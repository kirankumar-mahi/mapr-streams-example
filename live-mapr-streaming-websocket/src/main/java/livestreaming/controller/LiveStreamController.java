package livestreaming.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class LiveStreamController {
	
	@RequestMapping("/")
	public String index() {
		return "index.html";
	}

}
