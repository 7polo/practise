package com.polo.mybatis.spring.demo;

import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.context.annotation.Role;
import org.springframework.stereotype.Service;

/**
 * @author baoqianyong
 * @date 2021/5/27
 */
public  class TracingInterceptor implements MethodInterceptor {

    public TracingInterceptor() {

    }

    @Override
    public Object invoke(MethodInvocation i) throws Throwable {
        System.out.println("method "+i.getMethod()+" is called on "+
                i.getThis()+" with args "+i.getArguments());
        Object ret=i.proceed();
        System.out.println("method "+i.getMethod()+" returns "+ret);
        return ret;
    }
}
