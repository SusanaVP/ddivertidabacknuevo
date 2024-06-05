package com.iessotero.divertida.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ViewController {
	
	@GetMapping("/confirmation")
	public String confirmationPage() {
		return "confirmation";
	}
}
