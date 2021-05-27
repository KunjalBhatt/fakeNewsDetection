package com.parul.fakeNews.reconnizer;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class factDetector {
	@GetMapping(path="SetNewsData")
	public String SetNewsData() {
		return "Factdetector";
	}
	
}
