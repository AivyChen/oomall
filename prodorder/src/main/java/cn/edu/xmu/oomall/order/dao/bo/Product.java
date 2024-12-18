package cn.edu.xmu.oomall.order.dao.bo;

import cn.edu.xmu.javaee.core.model.bo.OOMallObject;
import lombok.Getter;

import java.io.Serializable;
import java.time.LocalDateTime;

public class Product extends OOMallObject implements Serializable {
    @Getter
    private Long id;
    private String name;
    private Double price;
    private int quantity;
    private Byte status;

    @Override
    public void setGmtCreate(LocalDateTime gmtCreate) {

    }

    @Override
    public void setGmtModified(LocalDateTime gmtModified) {

    }
}
