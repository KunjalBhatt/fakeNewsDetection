//This controller show polling data
package com.parul.fakeNews.Polling;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.parul.fakeNews.reconnizer.NewsData;

@Controller
public class Setdata {
	public List<NewsData> findAll() {
		return null;
	}

	@Autowired
	NamedParameterJdbcTemplate temp;

	@GetMapping(path = "polling")

	public ModelAndView GetData(NewsData nd1) {
		Map<String, Object> param = new HashMap<String, Object>();
		List<Map<String, Object>> newsList = temp.queryForList("SELECT sn.news_id,sn.news, sn.accuracy,\r\n" + "\r\n"
				+ "COUNT(IF(bs.result = 'true',bs.bsid,NULL)) like_count,\r\n"
				+ "COUNT(IF(bs.result = 'false',bs.bsid,NULL)) dislike_count\r\n" + " FROM storednews sn\r\n"
				+ "LEFT JOIN be_social bs ON bs.News_id = sn.News_id\r\n" + "GROUP BY sn.news_id;", param);
		// System.out.println(newsList);
		ModelAndView mv = new ModelAndView();
		mv.addObject("News", newsList);
		
		mv.setViewName("Polling");
		return mv;

	}

}
