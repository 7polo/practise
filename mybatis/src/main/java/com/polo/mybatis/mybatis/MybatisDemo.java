package com.polo.mybatis.mybatis;

import com.polo.mybatis.mybatis.mapper.DemoMapper;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import java.io.InputStream;
import java.util.Properties;

/**
 * @author baoqianyong
 * @date 2021/4/19
 */
public class MybatisDemo {

    public static void main(String[] args) throws Exception {
        System.out.println(DemoMapper.class);
        String resource = "com/polo/mybatis/mybatis/mybatis-config.xml";
        InputStream inputStream = Resources.getResourceAsStream(resource);

        Properties properties = new Properties();
        properties.setProperty("driver", "com.mysql.cj.jdbc.Driver");
        properties.setProperty("url", "jdbc:mysql://[ip]:[port]/jnote_dev?useUnicode=true&characterEncoding=utf8&autoReconnect=true&failOverReadOnly=false&useSSL=false&serverTimezone=Asia/Shanghai");
        properties.setProperty("username", "***");
        properties.setProperty("password", "***");

        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream, properties);

        int v = sqlSessionFactory.openSession().selectOne("com.polo.mybatis.mybatis.mapper.DemoMapper.selectTest");
    }
}
