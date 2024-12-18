package cn.edu.xmu.oomall.customer.mapper.po;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "customer_customer")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CustomerPo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;  // 主键，自增

    @Column(length = 128)
    private String userName;  // 用户名

    @Column(length = 255)
    private String password;  // 密码

    @Column(length = 128)
    private String name;  // 客户姓名

    private Long point;  // 积分

    private Integer invalid;  // 是否无效，0: 否，1: 是

    private Integer beDeleted;  // 是否已删除，0: 否，1: 是

    private Long creatorId;  // 创建者 ID

    @Column(length = 128)
    private String creatorName;  // 创建者姓名

    private Long modifierId;  // 修改者 ID

    @Column(length = 128)
    private String modifierName;  // 修改者姓名

    private LocalDateTime gmtCreate;  // 创建时间

    private LocalDateTime gmtModified;  // 修改时间

    @Column(length = 32, unique = true)
    private String mobile;  // 手机号，唯一
}
