package com.atguigu.gmall.manager.mapper;


import com.atguigu.gmall.bean.BaseAttrInfo;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

public interface BaseAttrInfoMapper extends Mapper<BaseAttrInfo> {
    List<BaseAttrInfo> getBaseAttrInfoListByCatalog3Id(Long catalog3Id);
}
