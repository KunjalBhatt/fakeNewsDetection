//this one for logging out (ending session comes here)
package com.parul.fakeNews.logoutController;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class LogoutUser {
	@RequestMapping(path = "Logout", method = RequestMethod.GET)
	public String logOut() {
		return "logout";
	}

	@GetMapping(path = "logginout")
	public String deletSession(HttpSession session) {

		session.invalidate();

		return "index";
	}

}
