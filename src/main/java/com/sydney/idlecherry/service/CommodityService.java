package com.sydney.idlecherry.service;

import com.sydney.idlecherry.model.Commodity;

import java.util.List;

public interface CommodityService {

    Commodity queryCommodityByID(Integer id);

    List<Commodity> selectCommodities();
}
