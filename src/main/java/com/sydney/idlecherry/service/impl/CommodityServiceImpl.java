package com.sydney.idlecherry.service.impl;

import com.sydney.idlecherry.mapper.CommodityMapper;
import com.sydney.idlecherry.model.Commodity;
import com.sydney.idlecherry.service.CommodityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class CommodityServiceImpl implements CommodityService {

    @Autowired
    private CommodityMapper commodityMapper;

    @Override
    public Commodity queryCommodityByID(Integer id) {
        return commodityMapper.selectByPrimaryKey(id);
    }

    @Override
    public List<Commodity> selectCommodities() {
        return  commodityMapper.selectCommodities();
    }
}
