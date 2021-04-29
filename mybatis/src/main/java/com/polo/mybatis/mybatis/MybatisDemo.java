package com.polo.mybatis.mybatis;

import com.polo.mybatis.mybatis.mapper.DemoMapper;
import com.polo.mybatis.mybatis.mapper.UserMapper;
import org.apache.ibatis.cache.TransactionalCacheManager;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.apache.ibatis.transaction.Transaction;
import org.apache.ibatis.transaction.jdbc.JdbcTransactionFactory;

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


//        int v = sqlSession.selectOne("com.polo.mybatis.mybatis.mapper.DemoMapper.selectTest");
//
//        sqlSession.getConfiguration().addMapper(DemoMapper.class);
//
//        DemoMapper mapper = sqlSession.getMapper(DemoMapper.class);
//        System.out.println(mapper.selectTest2());
//        System.out.println(mapper.selectTest2());


        // 一级缓存
//        localCache(sqlSessionFactory);

        // 二级缓存
        namespaceCache(sqlSessionFactory);

    }

    private static void localCache(SqlSessionFactory sqlSessionFactory) {
        SqlSession sqlSession = sqlSessionFactory.openSession();
        sqlSession.getConfiguration().addMapper(UserMapper.class);
        UserMapper mapper = sqlSession.getMapper(UserMapper.class);
        System.out.println("--------------- 查询1");
        User u1 = mapper.getById(1);
        System.out.println("--------------- 查询1 结束");

        System.out.println("\n--------------- 查询2");
        User u2 = mapper.getById(1);
        System.out.println("--------------- 查询2 结束");
    }

    private static void namespaceCache(SqlSessionFactory factory) {

        SqlSession sqlSession1 = factory.openSession();
        sqlSession1.getConfiguration().addMapper(UserMapper.class);
        UserMapper mapper = sqlSession1.getMapper(UserMapper.class);
        System.out.println("--------------- 查询1");
        User u1 = mapper.getById(1);
        sqlSession1.close();
        System.out.println("--------------- 查询1 结束");

        SqlSession sqlSession2 = factory.openSession();
        mapper = sqlSession2.getMapper(UserMapper.class);
        System.out.println("\n--------------- 查询2");
        User u2 = mapper.getById(1);
        sqlSession2.close();
        System.out.println("--------------- 查询2 结束");

        System.out.println();
    }
}
