package com.infinite.life.common.entity.rabbitmq;

import lombok.Data;

import java.io.Serializable;

/**
 * @author cuiwx
 * @version 1.0  2020/7/25
 * @description 实体类
 */
@Data
public class SingleDTO extends SinglePO implements Serializable {

    private String memo;
}
