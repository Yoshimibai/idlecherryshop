package com.sydney.idlecherry.mapper;

import com.sydney.idlecherry.model.Commodity;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface CommodityMapper {
    int deleteByPrimaryKey(Integer commid);

    int insert(Commodity record);

    int insertSelective(Commodity record);

    Commodity selectByPrimaryKey(Integer commid);

    int updateByPrimaryKeySelective(Commodity record);

    int updateByPrimaryKey(Commodity record);

    List<Commodity> selectCommodities();
}