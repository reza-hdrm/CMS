package com.rezahdrm.cms.web.controller.admin.auth;

import com.rezahdrm.cms.event.OnRegistrationCompleteEvent;
import com.rezahdrm.cms.exception.UserAlreadyExistException;
import com.rezahdrm.cms.model.User;
import com.rezahdrm.cms.model.dto.UserDTO;
import com.rezahdrm.cms.service.UserService;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

@Controller
@RequestMapping("register")
public class RegistrationController {
    UserService userService;
    ApplicationEventPublisher eventPublisher;

    public RegistrationController(UserService userService,ApplicationEventPublisher eventPublisher) {
        this.userService = userService;
        this.eventPublisher=eventPublisher;
    }

    @GetMapping
    public String showRegisterForm(ModelMap modelMap) {
        modelMap.addAttribute("user", new UserDTO());
        return "/frontend/auth/register";
    }

    @PostMapping("doRegister")
    public ModelAndView doRegister(
            @ModelAttribute("user") @Valid UserDTO userDTO,
            ModelAndView modelAndView,
            HttpServletRequest httpServletRequest){
        //Password: aaAa1!
        try {
            User registered = userService.registerNewUserAccount(userDTO);
            String appUrl=httpServletRequest.getContextPath();
            eventPublisher.publishEvent(new OnRegistrationCompleteEvent(registered,httpServletRequest.getLocale(),appUrl));
        } catch (UserAlreadyExistException uaeEx) {
            modelAndView.addObject("message", "An account for that email already exists.");
            return modelAndView;
        } catch (Exception e) {
            e.printStackTrace();
        }
        //TODO perfect Message

        return new ModelAndView("/frontend/auth/login","user",userDTO);
    }

    @GetMapping("registrationConfirm")
    public String registrationConfirm(@RequestParam String email,@RequestParam String token){
        userService.registrationConfirm(email,token);
        return "redirect:/login";
    }
}