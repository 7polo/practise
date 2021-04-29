package com.polo.mybatis.mybatis;

import lombok.Data;

import java.io.Serializable;

/**
 * @author baoqianyong
 * @date 2021/4/26
 */
@Data
public class User implements Serializable {

    private static final long serialVersionUID = 41232L;

    private String id;

    private String name;

}
