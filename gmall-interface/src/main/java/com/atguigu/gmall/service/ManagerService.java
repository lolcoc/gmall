package com.atguigu.gmall.service;

import com.atguigu.gmall.bean.*;

import java.util.List;

public interface ManagerService {

    // 查询所有一级分类属性
    List<BaseCatalog1> getCatalog1();
    // 查询二级分类，根据一级分类的id
    List<BaseCatalog2> getCatalog2(String catalog1Id);
    // 查询三级分类，根据二级分类的id
    List<BaseCatalog3> getCatalog3(String catalog2Id);
    // 根据三级分类id查询所有平台属性名称
    List<BaseAttrInfo> getAttrList(String catalog3Id);
    // 大保存平台属性，平台属性值
    void saveAttrInfo(BaseAttrInfo baseAttrInfo);
    // 查询属性信息
    BaseAttrInfo getAttrInfo(String attrId);

    List<SpuInfo> getSpuListPage(SpuInfo spuInfo);

    List<BaseSaleAttr> gerBaseSaleAttrList();

    void saveSpuInfo(SpuInfo spuInfo);
}
