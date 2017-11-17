package com.epam.spring.controller;

import com.epam.spring.object.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.support.RequestContextUtils;
import org.springframework.web.servlet.view.RedirectView;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.Locale;
import java.util.Map;

@Controller
@SessionAttributes("user")
public class TestController {

    @Autowired
    private MessageSource messageSource;

    @ModelAttribute
    public User createNewUser() {
        return new User("usernamevalue");
    }

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public ModelAndView main(Locale locale) {
//        WebApplicationContext context = WebApplicationContextUtils.getWebApplicationContext(session.getServletContext());
        System.out.println(locale.getDisplayLanguage());
        System.out.println(messageSource.getMessage("locale", new String[] { locale.getDisplayName(locale) }, locale));
        return new ModelAndView("login", "user", new User());
    }

    @RequestMapping(value = "/check-user", method = RequestMethod.POST)
    public ModelAndView checkUser(@Valid @ModelAttribute("user") User user, BindingResult bindingResult, Locale locale, RedirectAttributes redirectAttributes) {
        ModelAndView modelAndView = new ModelAndView();
        if (!bindingResult.hasErrors()) {
            RedirectView redirectView = new RedirectView("mainpage");
            redirectView.setStatusCode(HttpStatus.MOVED_PERMANENTLY);
            modelAndView.setView(redirectView);

            redirectAttributes.addFlashAttribute("locale", messageSource.getMessage("locale", new String[] { locale.getDisplayName(locale) }, locale));
        } else {
            modelAndView.setViewName("login");
        }

        return modelAndView;
//        return new ModelAndView("main", "user", user);
    }

    @RequestMapping(value = "/mainpage", method = RequestMethod.GET)
    public String goMainPage(HttpServletRequest request) {

        Map<String, ?> map = RequestContextUtils.getInputFlashMap(request);
        if (map != null) {
            System.out.println("redirect!");
        } else {
            System.out.println("update!");
        }

        return "main";
    }

    @RequestMapping(value = "/failed", method = RequestMethod.GET)
    public ModelAndView failed(){
        return new ModelAndView("login-failed", "message", "Login failed!");
    }

    @RequestMapping(value = "/get-json-user/{name}/{admin}", method = RequestMethod.GET, produces = "application/json")
    @ResponseBody
    public User getJsonUser(@PathVariable("name") String name, @PathVariable("admin") Boolean admin) {
        User user = new User();
        user.setName(name);
        user.setAdmin(admin);
        return user;
    }

    @RequestMapping(value = "/get-xml-user/{name}/{admin}", method = RequestMethod.GET, produces = "application/xml")
    @ResponseBody
    public User getXMLUser(@PathVariable("name") String name, @PathVariable("admin") Boolean admin) {
        User user = new User();
        user.setName(name);
        user.setAdmin(admin);
        return user;
    }

    @RequestMapping(value = "/put-json-user", method = RequestMethod.POST, consumes = "application/json")
    public ResponseEntity<String> setJsonUser(@RequestBody User user) {
        System.out.println(user);
        return new ResponseEntity<String>(HttpStatus.OK);
    }
}
