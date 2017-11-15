package com.epam.spring.controller;

import com.epam.spring.object.User;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

@Controller
public class TestController {
    @RequestMapping(value = "/", method = RequestMethod.GET)
    public ModelAndView main(HttpSession session) {
//        WebApplicationContext context = WebApplicationContextUtils.getWebApplicationContext(session.getServletContext());
        return new ModelAndView("login", "user", new User());
    }

    @RequestMapping(value = "/check-user", method = RequestMethod.POST)
    public String checkUser(@Valid @ModelAttribute("user") User user, BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()){
            return "login";
        }

        model.addAttribute("user", user);

        return "main";
//        return new ModelAndView("main", "user", user);
    }

    @RequestMapping(value = "/failed", method = RequestMethod.GET)
    public ModelAndView failed(){
        return new ModelAndView("login-failed", "message", "Login failed!");
    }

    @RequestMapping(value = "/get-json-user/{name}", method = RequestMethod.GET, produces = "application/json")
    @ResponseBody
    public User getJsonUser(@PathVariable("name") String name) {
        User user = new User();
        user.setName(name);
        return user;
    }

    @RequestMapping(value = "/put-json-user", method = RequestMethod.POST, consumes = "application/json")
    public ResponseEntity<String> setJsonUser(@RequestBody User user) {
        System.out.println(user);
        return new ResponseEntity<String>(HttpStatus.OK);
    }
}
