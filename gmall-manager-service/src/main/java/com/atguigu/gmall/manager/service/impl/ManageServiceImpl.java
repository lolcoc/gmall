package com.atguigu.gmall.manager.service.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.atguigu.gmall.bean.*;
import com.atguigu.gmall.manager.mapper.*;
import com.atguigu.gmall.service.ManagerService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

@Service
public class ManageServiceImpl implements ManagerService {

    @Autowired
    private BaseAttrInfoMapper baseAttrInfoMapper;
    @Autowired
    private BaseAttrValueMapper baseAttrValueMapper;
    @Autowired
    private BaseCatalog1Mapper baseCatalog1Mapper;
    @Autowired
    private BaseCatalog2Mapper baseCatalog2Mapper;
    @Autowired
    private BaseCatalog3Mapper baseCatalog3Mapper;
    @Autowired
    private SpuInfoMapper spuInfoMapper;
    @Autowired
    private BaseSaleAttrMapper baseSaleAttrMapper;
    @Autowired
    private SpuImageMapper spuImageMapper;
    @Autowired
    private SpuSaleAttrMapper spuSaleAttrMapper;
    @Autowired
    private SpuSaleAttrValueMapper spuSaleAttrValueMapper;
    @Autowired
    private SkuInfoMapper skuInfoMapper;
    @Autowired
    private SkuImageMapper skuImageMapper;
    @Autowired
    private SkuAttrValueMapper skuAttrValueMapper;
    @Autowired
    private SkuSaleAttrValueMapper skuSaleAttrValueMapper;

    @Override
    public List<BaseCatalog1> getCatalog1() {
        return baseCatalog1Mapper.selectAll();
    }

    @Override
    public List<BaseCatalog2> getCatalog2(String catalog1Id) {
        BaseCatalog2 baseCatalog2 = new BaseCatalog2();
        baseCatalog2.setCatalog1Id(catalog1Id);
        return baseCatalog2Mapper.select(baseCatalog2);
    }

    @Override
    public List<BaseCatalog3> getCatalog3(String catalog2Id) {
        BaseCatalog3 baseCatalog3 = new BaseCatalog3();
        baseCatalog3.setCatalog2Id(catalog2Id);
        return baseCatalog3Mapper.select(baseCatalog3);
    }

    @Override
    public List<BaseAttrInfo> getAttrList(String catalog3Id) {

        return baseAttrInfoMapper.getBaseAttrInfoListByCatalog3Id(Long.parseLong(catalog3Id));
    }

    // ctrl+alt+b 添加跟编辑写在一起
    @Override
    public void saveAttrInfo(BaseAttrInfo baseAttrInfo) {
        // 添加数据
        if (baseAttrInfo.getId()!=null && baseAttrInfo.getId().length()>0){
            baseAttrInfoMapper.updateByPrimaryKey(baseAttrInfo);
        } else {
            if (baseAttrInfo.getId()==null || baseAttrInfo.getId().length()==0){
                baseAttrInfo.setId(null); // null "";
            }
            baseAttrInfoMapper.insertSelective(baseAttrInfo);
        }
        // 写属性值 ，先删除，在新增
        BaseAttrValue baseAttrValue = new BaseAttrValue();
        baseAttrValue.setAttrId(baseAttrInfo.getId());
        baseAttrValueMapper.delete(baseAttrValue);
        // 插入数据
        List<BaseAttrValue> attrValueList = baseAttrInfo.getAttrValueList();
        if (attrValueList!=null && attrValueList.size()>0){
            for (BaseAttrValue attrValue : attrValueList) {
                if (attrValue.getId()==null || attrValue.getId().length()==0){
                    attrValue.setId(null);
                }
                attrValue.setAttrId(baseAttrInfo.getId());
                baseAttrValueMapper.insertSelective(attrValue);
            }
        }
    }

    @Override
    public BaseAttrInfo getAttrInfo(String attrId) {
        BaseAttrValue baseAttrValue = new BaseAttrValue();
        baseAttrValue.setAttrId(attrId);
        List<BaseAttrValue> baseAttrValueList = baseAttrValueMapper.select(baseAttrValue);
        BaseAttrInfo baseAttrInfo = new BaseAttrInfo();
        baseAttrInfo.setAttrValueList(baseAttrValueList);
        return baseAttrInfo;
    }

    @Override
    public List<SpuInfo> getSpuListPage(SpuInfo spuInfo) {
        return spuInfoMapper.select(spuInfo);
    }

    @Override
    public List<BaseSaleAttr> gerBaseSaleAttrList() {
        return baseSaleAttrMapper.selectAll();
    }

    @Override
    public void saveSpuInfo(SpuInfo spuInfo) {
        if (spuInfo.getId()==null || spuInfo.getId().length() == 0){
            spuInfo.setId(null);
            spuInfoMapper.insertSelective(spuInfo);
        }else {
            spuInfoMapper.updateByPrimaryKey(spuInfo);
        }

        SpuImage spuImage = new SpuImage();
        spuImage.setSpuId(spuInfo.getId());
        spuImageMapper.delete(spuImage);

        List<SpuImage> spuImageList = spuInfo.getSpuImageList();
        for (SpuImage image : spuImageList) {
            if (image.getId()!= null && image.getId().length()==0){
                image.setId(null);
            }
            image.setSpuId(spuInfo.getId());
            spuImageMapper.insertSelective(image);
        }

        SpuSaleAttr spuSaleAttr = new SpuSaleAttr();
        spuSaleAttr.setSpuId(spuInfo.getId());
        spuSaleAttrMapper.delete(spuSaleAttr);

        SpuSaleAttrValue spuSaleAttrValue = new SpuSaleAttrValue();
        spuSaleAttrValue.setSpuId(spuInfo.getId());
        spuSaleAttrValueMapper.delete(spuSaleAttrValue);

        List<SpuSaleAttr> spuSaleAttrList = spuInfo.getSpuSaleAttrList();
        for (SpuSaleAttr saleAttr : spuSaleAttrList) {
            if (saleAttr.getId()!=null && saleAttr.getId().length()==0){
                saleAttr.setId(null);
            }
            saleAttr.setSpuId(spuInfo.getId());
            spuSaleAttrMapper.insertSelective(saleAttr);

            List<SpuSaleAttrValue> spuSaleAttrValueList = saleAttr.getSpuSaleAttrValueList();
            for (SpuSaleAttrValue saleAttrValue : spuSaleAttrValueList) {
                if (saleAttrValue.getId() != null && saleAttrValue.getId().length()==0){
                    saleAttrValue.setId(null);
                }
                saleAttrValue.setSpuId(spuInfo.getId());
                spuSaleAttrValueMapper.insert(saleAttrValue);
            }
        }
    }

    @Override
    public List<SpuSaleAttr> getSpuSaleAttrList(String spuId) {
        return spuSaleAttrMapper.selectSpuSaleAttrList(Long.parseLong(spuId));
    }

    @Override
    public List<SpuImage> getSpuImageList(String spuId) {
        SpuImage spuImage = new SpuImage();
        spuImage.setSpuId(spuId);
        return spuImageMapper.select(spuImage);
    }

    @Override
    public void saveSku(SkuInfo skuInfo) {

        if(skuInfo.getId()==null || skuInfo.getId().length() == 0){
            skuInfo.setId(null);
            skuInfoMapper.insertSelective(skuInfo);
        }else{
            skuInfoMapper.updateByPrimaryKeySelective(skuInfo);
        }
        SkuImage skuImage = new SkuImage();
        skuImage.setSkuId(skuInfo.getId());
        skuImageMapper.delete(skuImage);

        List<SkuImage> imageList =skuInfo.getSkuImageList();
        for (SkuImage image : imageList) {
            if (image.getId() != null && image.getId().length()==0){
                image.setId(null);
            }
            image.setSkuId(skuInfo.getId());
            skuImageMapper.insertSelective(image);
        }

        SkuAttrValue skuAttrValue = new SkuAttrValue();
        skuAttrValue.setSkuId(skuInfo.getId());
        skuAttrValueMapper.delete(skuAttrValue);

        List<SkuAttrValue> skuAttrValueList =skuInfo.getSkuAttrValueList();
        for (SkuAttrValue attrValue : skuAttrValueList) {
            if (attrValue.getId() != null && attrValue.getId().length() == 0){
                attrValue.setId(null);
            }
            attrValue.setSkuId(skuInfo.getId());
            skuAttrValueMapper.insertSelective(attrValue);
        }

        SkuSaleAttrValue skuSaleAttrValue = new SkuSaleAttrValue();
        skuSaleAttrValue.setSkuId(skuInfo.getId());
        skuSaleAttrValueMapper.delete(skuSaleAttrValue);

        List<SkuSaleAttrValue> skuSaleAttrValueList = skuInfo.getSkuSaleAttrValueList();
        for (SkuSaleAttrValue saleAttrValue : skuSaleAttrValueList) {
           if (saleAttrValue.getId() != null && saleAttrValue.getId().length() == 0){
               saleAttrValue.setId(null);
           }
           saleAttrValue.setSkuId(skuInfo.getId());
           skuSaleAttrValueMapper.insertSelective(saleAttrValue);
        }
    }
}
