// This one for like and dislike
package com.parul.fakeNews.Polling;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.mail.Session;
import javax.security.auth.message.callback.PrivateKeyCallback.Request;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class NewsResult {
	@Autowired
	NamedParameterJdbcTemplate temp;

	@PostMapping(path = "newsrs")
	public String Result(@RequestParam String result, @RequestParam String id, HttpSession session) {
		Map<String, Object> param = new HashMap<String, Object>();
		param.put("Result", result);
		param.put("newsId", id);
		// int userId=(int) session.getAttribute("userId");
		param.put("userId", session.getAttribute("userId"));
		int res = temp.query("SELECT bsId FROM `be_social` WHERE News_id= :newsId AND User_id=:userId;", param,
				new ResultSetExtractor<Integer>() {
					@Override
					public Integer extractData(ResultSet rs) throws SQLException, DataAccessException {
						if (rs.next()) {
							return rs.getInt("bsId");
						}
						// System.out.println(result+"\n"+id);
						return 0;
					}
				});

		if (res > 0) {
			param.put("res", res);
			temp.update("UPDATE be_social SET result=:Result WHERE bsId= :res;", param);
			System.out.println(result + "\n" + id);
		} else {
			temp.update("INSERT INTO be_social SET News_id=:newsId, User_id=:userId, result=:Result;", param);
			System.out.println(result + "\n" + id);
		}

		// List<Map<String,Object>> = temp.query("", rse)
		return "Polling";
	}
}
