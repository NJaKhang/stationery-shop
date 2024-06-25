package com.group.sshop.controllers.web;

import com.group.sshop.models.entities.User;
import com.group.sshop.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Profile;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequiredArgsConstructor
public class ProfileController {
    private final UserDetailsService userDetailsService;

    @GetMapping("/profile")
    public ModelAndView profile() {
        ModelAndView modelAndView = new ModelAndView("web/profile");
        modelAndView.addObject("title", "Hồ sơ");
        return modelAndView;
    }
}
