package com.sydney.idlecherry.controller;

import com.sydney.idlecherry.model.Commodity;
import com.sydney.idlecherry.model.User;
import com.sydney.idlecherry.service.CommodityService;
import com.sydney.idlecherry.service.UserService;
import com.sydney.idlecherry.util.StatusCode;
import com.sydney.idlecherry.vo.ResultVo;
import org.apache.commons.io.FilenameUtils;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.crypto.hash.Md5Hash;
import org.apache.shiro.subject.Subject;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.UUID;

@Controller
@RequestMapping(value = "/user")
public class UserController {

    @Autowired
    UserService userService;

    @ResponseBody
    @RequestMapping("/login")
    public ResultVo userLogin(@RequestParam String username, @RequestParam String password){
        List<User> userList = userService.selectUsers();
        Integer userID;
        for(User user:userList) {
            if(user.getUsername().equals(username) && user.getPassword().equals(password)){
                userID = user.getUserid();
                return new ResultVo(true, StatusCode.OK,"登录成功", userID);
            }
        }
        return new ResultVo(true,StatusCode.LOGINERROR,"登录信息错误");
    }

    @ResponseBody
    @RequestMapping("/register")
    public ResultVo userRegister(@RequestParam String username, @RequestParam String password,
                                 @RequestParam String mobilephone, @RequestParam String email){
        List<User> userList = userService.selectUsers();
        for(User user:userList) {
            if(user.getUsername().equals(username)){
                return new ResultVo(false, StatusCode.ERROR,"用户名已存在，请换一个吧");
            }
        }
        Integer userID = userList.get(userList.size() - 1).getUserid() + 1;
        User newUser = new User();
        newUser.setUserid(userID);
        newUser.setUsername(username);
        newUser.setPassword(password);
        newUser.setMobilephone(mobilephone);
        newUser.setEmail(email);
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String currentTime = df.format(new Date());
        newUser.setCreatetime(currentTime);

        userService.insertSelective(newUser);
        return new ResultVo(true,StatusCode.OK,"注册成功", userID);
    }

    @RequestMapping(value = "/perfectInfo")
    public String perfectInfo(Model model, Integer userID) {
        model.addAttribute("userID", userID);
        return "perfectInfo";
    }

    @ResponseBody
    @RequestMapping("/updateInfo")
    public ResultVo updateInfo(@RequestParam String userID, @RequestParam String school, @RequestParam String faculty,
                               @RequestParam String startYear, @RequestParam String gender,
                               @RequestParam String signature){


        User newUser = new User();
        Integer ID = Integer.valueOf(userID);
        newUser.setUserid(ID);
        newUser.setSchool(school);
        newUser.setFaculty(faculty);
        Integer year = Integer.valueOf(startYear);
        newUser.setStartyear(year);
        newUser.setGender(gender);
        newUser.setSignature(signature);

        userService.updateByPrimaryKeySelective(newUser);
        return new ResultVo(true,StatusCode.OK,"添加信息成功");
    }

    @RequestMapping(value = "updateImg")
    @ResponseBody
    public JSONObject updateImg(@RequestParam(value = "file", required = false) MultipartFile file, HttpSession session, @RequestParam String userID) throws IOException, JSONException {
        JSONObject res = new JSONObject();
        JSONObject resUrl = new JSONObject();
        String filename = UUID.randomUUID().toString().replaceAll("-", "");
        String ext = FilenameUtils.getExtension(file.getOriginalFilename());//获得文件扩展名
        String filenames = filename + "." + ext;//文件全名
        String pathname = "D:/idlecherryImg/" + filenames;
        file.transferTo(new File(pathname));
        resUrl.put("src", "D:/idlecherryImg/"+filenames);
        res.put("msg", "");
        res.put("code", 0);
        res.put("data", resUrl);
        String uimgUrl = "D:/idlecherryImg/" + filenames;

        User newUser = new User();
        Integer ID = Integer.valueOf(userID);
        newUser.setUserid(ID);
        newUser.setProfile(uimgUrl);
        userService.updateByPrimaryKeySelective(newUser);

        return res;
    }



}
