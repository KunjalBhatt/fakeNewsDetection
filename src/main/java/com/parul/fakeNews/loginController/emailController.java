// This one is sending email to users account
package com.parul.fakeNews.loginController;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Base64;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCallback;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.jdbc.core.ResultSetSupportingSqlParameter;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class emailController {
	@Autowired
	NamedParameterJdbcTemplate templateDB;
	@Autowired
	JavaMailSender mailSender;

	@RequestMapping(method = RequestMethod.GET, path = "Mail")
	public String Mailaddress() {
		return "reset";

	}

//check enter email 
	@RequestMapping(path = "mailCheck")
	public String checkMail(@RequestParam String userEmail) throws MessagingException {
		MapSqlParameterSource paramSource = new MapSqlParameterSource();
		paramSource.addValue("Email", userEmail);

		int count = templateDB.query("SELECT count(*) as count FROM user_information WHERE E_mail = :Email  ",
				paramSource, new ResultSetExtractor<Integer>() {

					@Override
					public Integer extractData(ResultSet rs) throws SQLException, DataAccessException {
						int s = 0;
						while (rs.next()) {
							s = rs.getInt("count");
						}
						return s;
					}
				});

		if (count > 0) {
			String emailEncode = Base64.getEncoder().encodeToString(userEmail.getBytes());
			MimeMessage message = mailSender.createMimeMessage();
			MimeMessageHelper helper = new MimeMessageHelper(message);
			helper.setTo(userEmail);
			helper.setSubject("Performing security procedure");
			helper.setText("Dear User,<br />" + "<br />"
					+ "A request has been received to change the password for your FakeNewsDetector account.<br />"
					+ "<br />"

					+ "<a href=\"http://localhost:8080/passControl?q=" + emailEncode
					+ "\" targe=\"_blank\">Reset your password</a>" + "<br />" + "<br />"

					+ "Thank you,<br />" + "<b>Team FakeNewsDetector</b><br />"

					, true);

			mailSender.send(message);
			System.out.println("Send...");
		}
		return "message";

	}

}
