package com.sydney.idlecherry.controller;

import com.sydney.idlecherry.model.Commodity;
import com.sydney.idlecherry.service.CommodityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
public class CommodityController {

    @Autowired
    CommodityService commodityService;

    @RequestMapping(value = "/CommodityDetail")
    public String showCommodityDetail(Model model, Integer id) {

        Commodity commodity = commodityService.queryCommodityByID(id);
        model.addAttribute("commodity", commodity);
        return "CommodityDetail";
    }


}
