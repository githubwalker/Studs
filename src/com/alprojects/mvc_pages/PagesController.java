package com.alprojects.mvc_pages;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;


// http://stackoverflow.com/questions/15479213/how-to-serve-html-files-with-spring
// draft for now

@Controller
@RequestMapping
public class PagesController {

    @RequestMapping( value = "/edit", method = RequestMethod.GET)
    public String PageWithGrid(ModelMap model)
    {
        return "index";
    }
}
