package com.group.sshop.controllers.web;

import com.group.sshop.models.domain.Principal;
import com.group.sshop.models.dto.CartForm;
import com.group.sshop.models.dto.ProfileForm;
import com.group.sshop.models.entities.User;
import com.group.sshop.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Profile;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequiredArgsConstructor
public class ProfileController {
    private final UserService userService;

    @GetMapping("/profile")
    public ModelAndView profile(Authentication authentication) {
        ModelAndView userView = new ModelAndView("web/profile");
        Principal principal = (Principal) authentication.getPrincipal();
        User user = userService.findById(principal.getId());
        userView.addObject("user", user);
        userView.addObject("title", "Hồ sơ");
        return userView;
    }

    @PostMapping("/profile")
    public ModelAndView cart(ProfileForm profileForm, Authentication authentication) {
        ModelAndView modelAndView = new ModelAndView("redirect:/profile");
        Principal principal = (Principal) authentication.getPrincipal();
        Long id = principal.getId();
        userService.update(profileForm, id);
        return modelAndView;
    }
}
