package com.polo.mybatis.spring;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author baoqianyong
 * @date 2021/5/27
 */
@Service
public class DemoService {

    @Autowired
    private DemoMapper demoMapper;

    @Transactional(rollbackFor = Exception.class, propagation= Propagation.SUPPORTS)
    public void doQuery() {
        this.getClass().getMethods();
        demoMapper.selectTest();
    }
}
