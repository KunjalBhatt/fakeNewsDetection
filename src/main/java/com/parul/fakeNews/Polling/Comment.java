//This one contains usercomment store logic

package com.parul.fakeNews.Polling;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class Comment {
	@Autowired
	NamedParameterJdbcTemplate temp;

	@PostMapping(path = "submmitescomment")
	public String storeComment(HttpSession session, @RequestParam String comment, @RequestParam int newsId) {
		Map<String, Object> param = new HashMap<String, Object>();
		param.put("Comment", comment);
		param.put("newsId", newsId);
		param.put("userId", session.getAttribute("userId"));
		int check = temp.update(
				"INSERT INTO `usercomments` SET News_id = :newsId, User_id=:userId, Comment= :Comment; ", param);
		if (check > 0) {
			System.out.println("Done");
		} else {
			System.out.println("fails");
		}
		return "Polling";

	}
}
