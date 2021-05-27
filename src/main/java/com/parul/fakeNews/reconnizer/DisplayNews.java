package com.parul.fakeNews.reconnizer;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.json.JSONArray;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class DisplayNews {

	public List<NewsData> findAll() {
		return null;
	}

	@Autowired
	NamedParameterJdbcTemplate temp;

	@GetMapping(path = "Setdata")
	@ResponseBody
	public ModelAndView setData(NewsData nd1) {
		Map param = new HashMap<>();
		List<Map<String, Object>> data = temp
				.queryForList("SELECT News_id,News,Accuracy FROM `storednews` ORDER BY Search_count DESC;", param);
		// System.out.println(data);
		ModelAndView mv = new ModelAndView();
		mv.addObject("data", data);
		mv.setViewName("Trending");
		return mv;

	}
}
