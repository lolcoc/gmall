package com.atguigu.gmall.manager.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class ManagerController {

    @RequestMapping("index")
    public String index(){
        return "index";
    }

    @RequestMapping("attrListPage")
    public String getAttrListPage(){
        return "attrListPage";
    }

    @RequestMapping("spuListPage")
    public String getSpuListPage(){
        return "spuListPage";
    }
}
