package com.parul.fakeNews.loginController;

import java.io.IOException;
import java.sql.*;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.jdbc.core.SqlParameter;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.fasterxml.jackson.databind.util.JSONPObject;

@Controller
public class loginController {
	@Autowired
	NamedParameterJdbcTemplate templateDB;

	@RequestMapping(method = RequestMethod.GET, path = "loginPage")
	public String checkData() {

		return "reg";
	}

	@RequestMapping(method = RequestMethod.GET, path = "checking")
	@ResponseBody
	public String checkData(@RequestParam String email, @RequestParam String pass, HttpServletResponse response,
			HttpSession session) throws IOException {
		MapSqlParameterSource data = new MapSqlParameterSource();
		final String username;
		data.addValue("email", email);
		data.addValue("pass", pass);

		int count = templateDB.query("SELECT * FROM user_information WHERE E_mail = :email AND Password = :pass  ",
				data, new ResultSetExtractor<Integer>() {

					@Override
					public Integer extractData(ResultSet rs) throws SQLException, DataAccessException {
						int s = 0;

						if (rs.next()) {
							s = 1;
							session.setAttribute("userName", rs.getString("UserName"));
							session.setAttribute("Email_id", rs.getString("E_mail"));
							session.setAttribute("userId", rs.getString("User_id"));
						} else {
							s = 0;
						}
						return s;
					}
				});
		String rsp = "";
		if (count > 0) {
			rsp = "Success";

		} else {
			rsp = "fail";
		}

		return rsp;
	}

	@RequestMapping(path = "homePage")
	public String homePage() {

		return "index";
	}

}
