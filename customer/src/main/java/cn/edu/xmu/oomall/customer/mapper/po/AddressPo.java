package cn.edu.xmu.oomall.customer.mapper.po;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "customer_address")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class AddressPo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;  // 主键，自增

    private Long customerId;  // 客户 ID

    private Long regionId;  // 区域 ID

    @Column(length = 500)
    private String address;  // 地址

    @Column(length = 128)
    private String consignee;  // 收货人姓名

    @Column(length = 128)
    private String mobile;  // 手机号

    private Integer beDefault;  // 是否为默认地址，0: 否，1: 是

    private Long creatorId;  // 创建者 ID

    @Column(length = 128)
    private String creatorName;  // 创建者姓名

    private Long modifierId;  // 修改者 ID

    @Column(length = 128)
    private String modifierName;  // 修改者姓名

    private LocalDateTime gmtCreate;  // 创建时间

    private LocalDateTime gmtModified;  // 修改时间
}
