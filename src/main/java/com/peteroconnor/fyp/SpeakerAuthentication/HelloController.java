package com.peteroconnor.fyp.SpeakerAuthentication;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloController {

    @RequestMapping("/")
    public String index() {
        return "This is the home page - Peter O'Connor";
    }

}
