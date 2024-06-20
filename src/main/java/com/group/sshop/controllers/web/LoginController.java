package com.group.sshop.controllers.web;

import com.group.sshop.models.dto.RegisterFrom;
import com.group.sshop.service.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequiredArgsConstructor
public class LoginController {

    private final UserService userService;

    @GetMapping("/login")
    public ModelAndView login() {
        return new ModelAndView("web/login");
    }

    @GetMapping("/register")
    public ModelAndView register(RegisterFrom registerFrom){
        return new ModelAndView("web/register");
    }


    @PostMapping("/register")
    public ModelAndView handleRegister(@ModelAttribute @Valid RegisterFrom registerFrom, BindingResult bindingResult){
        System.out.println(registerFrom);
        if(bindingResult.hasErrors()){
            System.out.println(bindingResult.getModel());
            return new ModelAndView("web/register");
        } else {
            userService.registerNewUser(registerFrom);
        }

        return new ModelAndView("redirect:/");
    }
}
