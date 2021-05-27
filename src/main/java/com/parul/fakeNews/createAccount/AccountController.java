//Storing user data into db for creating account purpose
package com.parul.fakeNews.createAccount;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Random;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.jdbc.core.RowCallbackHandler;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class AccountController {

	@Autowired
	NamedParameterJdbcTemplate templateDB;
	@Autowired
	JavaMailSender mailSender;
	@Autowired
	HttpServletRequest request;

	@RequestMapping(method = RequestMethod.GET, path = "signinPage")
	public String getSignin() {

		return "reg";
	}

	@RequestMapping(method = RequestMethod.POST, path = "saveData")
	public String userSignin(CreateAccount c1, HttpSession session) throws MessagingException, SQLException {
		System.out.println(c1);

		MapSqlParameterSource paramSource = new MapSqlParameterSource();

		paramSource.addValue("username", c1.getUserName());
		String name = c1.getUserName();

		paramSource.addValue("email", c1.getEmail());
		String E_mail = c1.getEmail();

		paramSource.addValue("password", c1.getPassword());
		Random random = new Random();
		int max = 10000;
		int min = 99999;
		int a = (int) (Math.random() * (max - min + 1) + min);
		// int b.fo=("%06",a);
		System.out.println("called");

		System.out.println("Sending");
		// SimpleMailMessage message = new SimpleMailMessage();
		MimeMessage message = mailSender.createMimeMessage();
		// String mailcontent="<a href=\"http://localhost:8080/Verification\"
		// targe=\"_blank\">Verify Your Resume</a> ";

		// message.setFrom("FakeNewsDetector036@gmail.com");
		MimeMessageHelper helper = new MimeMessageHelper(message);
		helper.setTo(E_mail);
		helper.setSubject("Performing security procedure");
		helper.setText("Dear " + name + ",<br />" + "<br />"
				+ "We have send an verification code which help your to verify yourself<br />" + "<br />" + "Code is: "
				+ a + "<br />" + "<br />"

				+ "<br />" + "<br />" + "Thank you,<br />" + "<b>Team FakeNewsDetector</b><br />"

				, true);

		mailSender.send(message);
		System.out.println("Send...");
		paramSource.addValue("code", a);
		HttpSession session2 = request.getSession();

		session2.setAttribute("email", c1.getEmail());
		templateDB.update("Insert into Verification SET code = :code , Email = :email", paramSource);

		templateDB.update(
				"INSERT INTO user_information SET `UserName` = :username, `E_mail` = :email, `Password` = :password ",
				paramSource);

		return "verification";

	}

}
