package com.polo.mybatis.mybatis.mapper;

import com.polo.mybatis.mybatis.User;
import org.apache.ibatis.annotations.Select;

import java.io.Serializable;

/**
 * @author baoqianyong
 * @date 2021/4/19
 */
public interface UserMapper {

    @Select("select * from User where id = #{id}")
    User getById(Serializable id);
}
