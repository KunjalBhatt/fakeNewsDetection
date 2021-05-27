package com.parul.fakeNews.reconnizer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class recongnizer {
	@Autowired
	NamedParameterJdbcTemplate nameTemplet;

	@RequestMapping(method = RequestMethod.GET, path = "Recognizer")
	public String Recongnize() {

		return "recognizer";

	}

	@PostMapping(path = "storeNews")
	public ModelAndView StoreNews(@RequestParam String News) {
		MapSqlParameterSource data = new MapSqlParameterSource();
		data.addValue("News", News);
		int count = nameTemplet.update("insert into storedNews SET News =:News,Search_count=1,last_search_on=NOW(); ",
				data);
		if (count > 0) {
			System.out.println("Done");
		} else {
			System.out.println("error");
		}
		ModelAndView mv = new ModelAndView();
		mv.addObject("newsVal", News);
		mv.addObject("detactorVal", "Real");
		mv.setViewName("News");
		return mv;
	}

}
