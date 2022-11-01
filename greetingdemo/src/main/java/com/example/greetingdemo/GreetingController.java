package com.example.greetingdemo;

import java.util.concurrent.atomic.AtomicLong;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class GreetingController {
	
	private static final String template = "Hello, %s!";
	private final AtomicLong counter = new AtomicLong();
	
	//define a greeting component variable wt initialization
	private GreetingComponent gc;
	
	//define a constructor that will inject the greeting comp obj/intsance
	//GreetingComponent will be injected n initialize in the constructor
	@Autowired
	public GreetingController(GreetingComponent gc) {
		this.gc = gc;
	}
	
	@GetMapping("/greeting")  //handle get method
	public Greeting greeting(@RequestParam(value = "name", defaultValue = "World") String name) { 
		return new Greeting(counter.incrementAndGet(), String.format(template, name));
	}
	
	//define another method t invoke method defined in GreetingComponent
	@GetMapping("testgreeting")  //handle get method
	public ResponseEntity<String> getMessage() { 
		return ResponseEntity.ok(gc.getMessage());
	}
	

}
