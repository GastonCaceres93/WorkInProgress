package com.gcaceres.mvc;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class Greeting {

    @RequestMapping("/greeting")
    public String greeting(@RequestParam(value="name", required=false, defaultValue="World") String name, Model model) {
        
    	//adding 
    	model.addAttribute("name", name);

        //return must be name of the template to render
        return "saluteNew";
    }
    

    @RequestMapping("/goodbye")
    public String goodbye(@RequestParam(value="name", required=false, defaultValue="World") String name, Model model) {
        
    	//adding 
    	model.addAttribute("name", name);

        //return must be name of the template to render
        return "goodbye";
    }

}