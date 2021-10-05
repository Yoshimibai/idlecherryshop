package com.sydney.idlecherry.service;

import com.sydney.idlecherry.model.Commodity;
import com.sydney.idlecherry.model.User;

import java.util.List;

public interface UserService {

    User queryUserByID(Integer id);

    List<User> selectUsers();

    int insertSelective(User record);

    int updateByPrimaryKeySelective(User record);
}
