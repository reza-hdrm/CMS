package com.rezahdrm.cms.web.controller.admin;

import com.ibm.icu.text.DateFormat;
import com.rezahdrm.cms.service.UserService;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("admin/user")
@PreAuthorize("hasRole('ROLE_ADMIN')")
public class UserController {
    private final UserService userService;
    private final DateFormat persianCalendar;

    public UserController(UserService userService, DateFormat persianCalendar) {
        this.userService = userService;
        this.persianCalendar = persianCalendar;
    }

    @GetMapping
    public String index(ModelMap modelMap){
        modelMap.addAttribute("users",userService.findAll()).
                addAttribute("persianCalendar",persianCalendar);
        return "admin/user/index";
    }
}
