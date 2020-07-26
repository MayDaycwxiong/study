package com.infinite.life.common.entity.rabbitmq;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

@Data
public class SinglePO implements Serializable {

    private String urid;

    private String entNum;

    private String transNo;

    private String state;

    private Date inTime;

    private Double amount;

    private Integer version;
}
