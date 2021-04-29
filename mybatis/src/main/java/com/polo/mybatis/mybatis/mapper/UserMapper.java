package com.polo.mybatis.mybatis.mapper;

import com.polo.mybatis.mybatis.User;
import org.apache.ibatis.annotations.*;
import org.apache.ibatis.cache.decorators.LruCache;

import java.io.Serializable;

/**
 * @author baoqianyong
 * @date 2021/4/19
 */
@CacheNamespace
public interface UserMapper {

    @Select("select * from User where id = #{id}")
    User getById(Serializable id);

    @Delete("delete from User where id = #{id}")
    void deletById(int i);
}
