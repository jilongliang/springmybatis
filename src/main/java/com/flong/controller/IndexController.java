package com.flong.controller;



import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.flong.BaseController;



@Controller
@RequestMapping("/index")
public class IndexController extends BaseController{
 
	
 
	@RequestMapping
    public String getIndexPage() {
        return "index";
    }
	
	
	
}
