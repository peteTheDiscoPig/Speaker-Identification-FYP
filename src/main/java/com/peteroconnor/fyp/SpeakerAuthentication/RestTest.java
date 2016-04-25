package com.peteroconnor.fyp.SpeakerAuthentication;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class RestTest {
	
	@RequestMapping("/ASV/test")
	public String sendMessage(){
		
		return "This is proof the application is hosted - Peter O'Connor";
	}
}
