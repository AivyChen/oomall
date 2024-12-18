package cn.edu.xmu.oomall.order.dao.bo;

import cn.edu.xmu.javaee.core.aop.CopyFrom;
import cn.edu.xmu.javaee.core.model.bo.OOMallObject;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.io.Serializable;
import java.time.LocalDateTime;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Slf4j
public class Activity extends OOMallObject implements Serializable {
    @Getter
    private Long id;
    private String name;
    private Byte type;

    @Override
    public void setGmtCreate(LocalDateTime gmtCreate) {

    }

    @Override
    public void setGmtModified(LocalDateTime gmtModified) {

    }
}
