package cn.edu.xmu.oomall.customer.mapper.po;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "customer_coupon")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CouponPo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;  // 主键，自增

    @Column(length = 128)
    private String couponSn;  // 优惠券编号

    @Column(length = 128)
    private String name;  // 优惠券名称

    private Long customerId;  // 客户 ID

    private Long activityId;  // 活动 ID

    private LocalDateTime beginTime;  // 开始时间

    private LocalDateTime endTime;  // 结束时间

    private Integer used;  // 是否已使用，0: 否，1: 是

    private Long creatorId;  // 创建者 ID

    @Column(length = 128)
    private String creatorName;  // 创建者姓名

    private Long modifierId;  // 修改者 ID

    @Column(length = 128)
    private String modifierName;  // 修改者姓名

    private LocalDateTime gmtCreate;  // 创建时间

    private LocalDateTime gmtModified;  // 修改时间
}

