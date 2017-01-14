package com.graduate.zhang.controller;

import com.graduate.zhang.model.User;
import com.graduate.zhang.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * Created by 张晓磊 on 2016/3/4.
 */
@Controller
public class UserController {
    private UserService userService;

    @Autowired
    public UserController(UserService userService){
        this.userService = userService;
    }

    @RequestMapping(value="mongodb/test",method = RequestMethod.GET)
    public String mongoTest(Model model){
        User user = userService.findOne("张晓磊");
        model.addAttribute("user", user);
        return "mongo/test";
    }
}
