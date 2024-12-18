package cn.edu.xmu.oomall.order.dao.bo;

import cn.edu.xmu.javaee.core.model.bo.OOMallObject;
import lombok.Getter;

import java.io.Serializable;
import java.time.LocalDateTime;

public class Coupon extends OOMallObject implements Serializable {
    @Getter
    private Long id;
    private CouponActivity activity;
    private String name;
    private String couponSn;

    @Override
    public void setGmtCreate(LocalDateTime gmtCreate) {

    }

    @Override
    public void setGmtModified(LocalDateTime gmtModified) {

    }
}
