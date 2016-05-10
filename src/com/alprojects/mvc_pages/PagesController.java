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

    public PagesController() {
        int iii = 0;
    }

    @RequestMapping("/edit_studs")
    public String edit_list(ModelMap model)
    {
        return "edit";
    }

    @RequestMapping("/")
    public String initial(ModelMap model)
    {
        return "redirect:/edit_studs";
    }

}
