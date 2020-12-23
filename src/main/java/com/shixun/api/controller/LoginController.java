package com.shixun.api.controller;

import com.shixun.api.service.ISourcesService;
import com.shixun.api.utils.SecurityUtils;
import com.shixun.api.vo.MenuVO;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

@RestController
@RequiredArgsConstructor
public class LoginController {

    private final ISourcesService sourcesServiceources;

    @PostMapping("/index")
    public ModelAndView loginSuccess(){
        ModelAndView modelAndView = new ModelAndView();
        MenuVO userMenu = sourcesServiceources.findUserMenu(SecurityUtils.getUsername());
        modelAndView.addObject("userMenu", userMenu);
        modelAndView.addObject("username", SecurityUtils.getUsername());
        modelAndView.setViewName("index");
        return modelAndView;
    }



}
