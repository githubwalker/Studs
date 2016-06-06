package com.alprojects.mvc_pages;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;


// http://stackoverflow.com/questions/15479213/how-to-serve-html-files-with-spring
// draft for now

@Controller
@RequestMapping
public class PagesController {

    @RequestMapping("/edit_studs")
    public String edit_list(Model model)
    {
        return "edit";
    }

    @RequestMapping("/index")
    public String initial(Model model)
    {
        return "redirect:/edit_studs";
    }

    @RequestMapping("/")
    public String initial2(Model model)
    {
        return "redirect:/edit_studs";
    }

    @RequestMapping("/login")
    public String login(Model model, String error, String logout)
    {
        if (error != null)
            model.addAttribute("error", "Your username and password is invalid.");

        if (logout != null)
            model.addAttribute("message", "You have been logged out successfully.");

        return "login";
    }

}

