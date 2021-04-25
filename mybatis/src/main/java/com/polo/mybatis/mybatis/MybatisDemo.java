package com.polo.mybatis.mybatis;

import com.polo.mybatis.mybatis.mapper.DemoMapper;
import com.polo.mybatis.mybatis.mapper.UserMapper;
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
        properties.setProperty("url", "jdbc:mysql://172.22.5.100:3306/Test?useUnicode=true&characterEncoding=utf8&autoReconnect=true&failOverReadOnly=false&useSSL=false&serverTimezone=Asia/Shanghai");
        properties.setProperty("username", "root");
        properties.setProperty("password", "123456");

        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream, properties);
        SqlSession sqlSession = sqlSessionFactory.openSession();


//        int v = sqlSession.selectOne("com.polo.mybatis.mybatis.mapper.DemoMapper.selectTest");
//
//        sqlSession.getConfiguration().addMapper(DemoMapper.class);
//
//        DemoMapper mapper = sqlSession.getMapper(DemoMapper.class);
//        System.out.println(mapper.selectTest2());
//        System.out.println(mapper.selectTest2());

        sqlSession.getConfiguration().addMapper(UserMapper.class);
        UserMapper mapper = sqlSession.getMapper(UserMapper.class);
        User u1 = mapper.getById(1);
        User u2 = mapper.getById(1);
        System.out.println();
    }
}
