package com.parul.fakeNews.controllers;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.mail.MessagingException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.jdbc.core.RowCallbackHandler;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.parul.fakeNews.createAccount.CreateAccount;

@Controller
public class HomeController {
	@Autowired
	NamedParameterJdbcTemplate templateDB1;

	@Autowired
	DataSource dataSource;

	@Autowired
	HttpServletRequest request;

	@RequestMapping(method = RequestMethod.GET, path = "Verification")
	public String userVerification() {
		return "verification";
	}

	@RequestMapping(method = RequestMethod.GET, path = "home")
	public ModelAndView getHomepage(@RequestParam String code) throws MessagingException {
		HttpSession session = request.getSession();

		ModelAndView mv = new ModelAndView();

		String email = String.valueOf(session.getAttribute("email"));
		session.setAttribute("Email", email);
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("email", email);
		String dbCode = templateDB1.query(
				"SELECT CODE FROM Verification WHERE Email = :email ORDER BY id  DESC LIMIT 1 ", params,
				new ResultSetExtractor<String>() {

					@Override
					public String extractData(ResultSet rs) throws SQLException, DataAccessException {
						String s = "";
						while (rs.next()) {
							s = rs.getString("code");
						}
						return s;
					}

				});
		if (dbCode.equalsIgnoreCase(code)) {
			mv.addObject("matched");

			templateDB1.query("SELECT User_id,UserName FROM `user_information`;", params, new RowCallbackHandler() {

				@Override
				public void processRow(ResultSet rs) throws SQLException {
					if (rs.next()) {
						session.setAttribute("userName", rs.getString("UserName"));
						session.setAttribute("userId", rs.getInt("User_id"));
					}
				}
			});

			// mv.setViewName("verification");
			mv.setViewName("index");
		} else {
			// mv.setViewName("homepage");
			mv.setViewName("verification");
		}

		return mv;
	}

}
