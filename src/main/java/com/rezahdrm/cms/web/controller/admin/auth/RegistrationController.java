package com.rezahdrm.cms.web.controller.admin.auth;

import com.rezahdrm.cms.exception.UserAlreadyExistException;
import com.rezahdrm.cms.model.User;
import com.rezahdrm.cms.model.dto.UserDTO;
import com.rezahdrm.cms.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import javax.validation.Valid;

@Controller
@RequestMapping("/register")
public class RegistrationController {
    UserService userService;

    public RegistrationController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping
    public String showRegisterForm(ModelMap modelMap) {
        modelMap.addAttribute("user", new UserDTO());
        return "/frontend/auth/register";
    }

    @PostMapping("/doRegister")
    public ModelAndView register(@ModelAttribute @Valid UserDTO userDTO, ModelAndView modelAndView){
        //Password: aaAa1!
        try {
            User registered = userService.registerNewUserAccount(userDTO);
        } catch (UserAlreadyExistException uaeEx) {
            modelAndView.addObject("message", "An account for that email already exists.");
            return modelAndView;
        }
        //TODO perfect Message

        return new ModelAndView("/frontend/auth/login","user",userDTO);
    }

}
