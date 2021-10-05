package com.sydney.idlecherry.controller;

import com.sydney.idlecherry.model.Commodity;
import com.sydney.idlecherry.model.User;
import com.sydney.idlecherry.service.CommodityService;
import com.sydney.idlecherry.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
public class IndexController {

    @Autowired
    CommodityService commodityService;

    @Autowired
    UserService userService;

    @RequestMapping(value = "/index")
    public String index(Model model) {

        List<Commodity> commodities = commodityService.selectCommodities();
        model.addAttribute("commodityList", commodities);
        return "indexWithoutUser";
    }

    @RequestMapping(value = "/login")
    public String turnLogin() {
        return "logreg";
    }

    @RequestMapping(value = "/message")
    public String userDetail(Model model) {
        model.addAttribute("data", "哈哈ghhh");
        return "messageResult";
    }

    @RequestMapping(value = "/indexForUser")
    public String indexForUser(Model model, Integer userID) {
        List<Commodity> commodities = commodityService.selectCommodities();
        User user = userService.queryUserByID(userID);
        String userName = user.getUsername();
        model.addAttribute("commodityList", commodities);
        model.addAttribute("userID", userID);
        model.addAttribute("userName", userName);
        return "indexForUser";
    }
}
