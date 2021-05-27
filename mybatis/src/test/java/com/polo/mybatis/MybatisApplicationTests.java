package com.polo.mybatis;

import com.polo.mybatis.spring.DemoService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class MybatisApplicationTests {

    @Autowired
    private DemoService demoService;

    @Test
    void contextLoads() {
        demoService.doQuery();
    }

}
