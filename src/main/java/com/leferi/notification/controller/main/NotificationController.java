package com.leferi.notification.controller.main;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
@RequestMapping(value = "/notification")
public class NotificationController {

    @GetMapping("list")
    public String list(Model model){

        return "/notification/list";
    }

}
