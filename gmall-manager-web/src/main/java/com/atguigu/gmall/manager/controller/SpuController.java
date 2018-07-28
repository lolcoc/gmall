package com.atguigu.gmall.manager.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.atguigu.gmall.bean.BaseSaleAttr;
import com.atguigu.gmall.bean.SpuInfo;
import com.atguigu.gmall.service.ManagerService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
public class SpuController {

    @Reference
    private ManagerService managerService;

    @RequestMapping("baseSaleAttrList")
    @ResponseBody
    public List<BaseSaleAttr> gerBaseSaleAttrList(){
       return managerService.gerBaseSaleAttrList();
    }

    @RequestMapping(value = "saveSpuInfo", method = RequestMethod.POST)
    @ResponseBody
    public String saveSpuInfo(SpuInfo spuInfo){
        managerService.saveSpuInfo(spuInfo);
        return "success";
    }
}
