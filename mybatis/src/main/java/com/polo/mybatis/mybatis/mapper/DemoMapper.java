package com.polo.mybatis.mybatis.mapper;

import org.apache.ibatis.annotations.CacheNamespace;
import org.apache.ibatis.annotations.Flush;
import org.apache.ibatis.annotations.Select;

/**
 * @author baoqianyong
 * @date 2021/4/19
 */
public interface DemoMapper {

    int selectTest();

    @Select("select 2")
    int selectTest2();


}
