package com.liftkarade.app.controller;


import java.util.Date;
import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.atomic.AtomicLong;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.liftkarade.app.model.Address;
import com.liftkarade.app.model.User;
import com.liftkarade.app.model.User.Gender;
import com.liftkarade.app.service.IUserService;

@RestController
public class HelloApplication {
	
	private static final Logger logger = LoggerFactory.getLogger(HelloApplication.class);
	
	@Autowired
	IUserService service;
	
	private static final String template = "Hello, %s!";
    private final AtomicLong counter = new AtomicLong();

    @RequestMapping("/greeting")
    public Greeting greeting(@RequestParam(value="name", defaultValue="World") String name) {
    	logger.error("dededededededededed");
        return new Greeting(counter.incrementAndGet(),
                            String.format(template, name));
    }
    
    @RequestMapping("/adduser")
    public User addUser(@RequestParam(value="name", defaultValue="World") String name,
    		@RequestParam(value="address", defaultValue="World") String address,
    		@RequestParam(value="email", defaultValue="sushil106768@gmail.com") String email,
    		@RequestParam(value="fname", defaultValue="Sushil") String firstname,
    		@RequestParam(value="lname", defaultValue="Gupta") String lastname,
    		@RequestParam(value="gender", defaultValue="male") String gender,
    		@RequestParam(value="password", defaultValue="sushil") String password,
    		@RequestParam(value="phone", defaultValue="9999999999") String phone) {
    	logger.error("adding User....");
    	List<Address> address1= new LinkedList<>();
    	address1.add(new Address());
    	User user = new User(firstname, lastname, email, password, phone, address1, "AWSNPGH", new Date(), Gender.male);
    	try {
			return service.addUser(user);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	return null;
    }

}
