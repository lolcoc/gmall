package com.atguigu.gmall.order.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.atguigu.gmall.bean.UserAddress;
import com.atguigu.gmall.service.UserAddressService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Controller
public class UserAddressController {

    @Reference
    UserAddressService userAddressService;

    @RequestMapping("selectord")
    @ResponseBody
    public List<UserAddress> selectord(HttpServletRequest request){
        String userId = request.getParameter("userId");
        return userAddressService.getUserAddressList(userId);
    }
}
