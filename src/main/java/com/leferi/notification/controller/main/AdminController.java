package com.leferi.notification.controller.main;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
@RequestMapping(value = "/admin")
public class AdminController {

    @GetMapping("list")
    public String list(Model model){

        return "/admin/list";
    }

    @GetMapping("regist")
    public String regist(Model model){

        return "/admin/regist";
    }


}
