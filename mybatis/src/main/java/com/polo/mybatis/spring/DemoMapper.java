package com.polo.mybatis.spring;

import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

/**
 * @author baoqianyong
 * @date 2021/5/27
 */
@Repository
public interface DemoMapper {

    @Select("select 2")
    int selectTest();
}
