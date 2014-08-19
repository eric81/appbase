package com.eudemon.taurus.app.action;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class WelcomeAction {
	@RequestMapping(value = "index")
	public String welcome(){
		return "index";
	}
}
