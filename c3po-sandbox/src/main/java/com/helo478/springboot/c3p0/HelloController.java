package com.helo478.springboot.c3p0;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;

@RestController
public class HelloController {
	
	@Autowired
	private HelloRepository helloRepository;

    @RequestMapping("/")
    public String index() {
        return helloRepository.getHello();
    }

}