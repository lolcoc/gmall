package com.atguigu.gmall.manager.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.atguigu.gmall.bean.*;
import com.atguigu.gmall.service.ManagerService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
public class AttrManageController {

    @Reference
    private ManagerService managerService;

    @RequestMapping("ctg1ForAttrList")
    @ResponseBody
    public String getctg1ForAttrList(){
        return "attrListPage";
    }

    @RequestMapping("getCatalog1")
    @ResponseBody
    public List<BaseCatalog1> getCatalog1(){
        return managerService.getCatalog1();
    }


    @RequestMapping("getCatalog2")
    @ResponseBody
    public List<BaseCatalog2> getCatalog2(String catalog1Id){
        return managerService.getCatalog2(catalog1Id);
    }

    @RequestMapping("getCatalog3")
    @ResponseBody
    public List<BaseCatalog3> getCatalog3(String catalog2Id){
        return managerService.getCatalog3(catalog2Id);
    }

    @RequestMapping("attrInfoList")
    @ResponseBody
    public List<BaseAttrInfo> getAttrInfoList(String catalog3Id){
        return managerService.getAttrList(catalog3Id);
    }

    @RequestMapping(value = "saveAttrInfo",method = RequestMethod.POST)
    @ResponseBody
    public String saveAttrInfo(BaseAttrInfo baseAttrInfo){
        // 从页面传递过来
        managerService.saveAttrInfo(baseAttrInfo);
        return "success";
    }

    @RequestMapping("getAttrValueList")
    @ResponseBody
    public List<BaseAttrValue> getAttrValueList(String attrId){
        // 属性---属性值 是一一对应
        BaseAttrInfo baseAttrInfo =  managerService.getAttrInfo(attrId);
        return baseAttrInfo.getAttrValueList();
    }

    @RequestMapping("spuList")
    @ResponseBody
    public List<SpuInfo> getSpuList(String catalog3Id){
            SpuInfo spuInfo = new SpuInfo();
            spuInfo.setCatalog3Id(catalog3Id);
            return managerService.getSpuListPage(spuInfo);
    }

    @RequestMapping("spuSaleAttrList")
    @ResponseBody
    public List<SpuSaleAttr> getSpuSaleAttrList(String spuId){
        return managerService.getSpuSaleAttrList(spuId);
    }

    @RequestMapping("spuImageList")
    @ResponseBody
    public List<SpuImage> getSpuImageList(String spuId){
        List<SpuImage> spuImages = managerService.getSpuImageList(spuId);
        return spuImages;
    }

    @RequestMapping(value = "saveSku",method = RequestMethod.POST)
    @ResponseBody
    public String saveSku(SkuInfo skuInfo){
        managerService.saveSku(skuInfo);
        return "success";
    }
}
