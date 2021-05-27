package com.parul.fakeNews.loginController;

import java.util.Base64;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class passwordController {
	@Autowired
	NamedParameterJdbcTemplate templateDB;

	@RequestMapping(path = "passControl")
	public String password() {
		return "codeCheck";
	}

	@RequestMapping(path = "passUpdate")
	@ResponseBody
	public String storePass(@RequestParam String pass, @RequestParam String Email) {

		String emailPlain = new String(Base64.getDecoder().decode(Email));
		MapSqlParameterSource parameterSource = new MapSqlParameterSource();
		parameterSource.addValue("Password", pass);
		parameterSource.addValue("Email", emailPlain);
		templateDB.update("UPDATE user_information SET PASSWORD = :Password WHERE E_mail = :Email", parameterSource);
		System.out.println("Done !!!!!!!!!");
		return "codeCheck";
	}
}
