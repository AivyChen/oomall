//School of Informatics Xiamen University, GPL-3.0 license

package cn.edu.xmu.oomall.order.controller.vo;

import lombok.Data;
import lombok.NoArgsConstructor;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;

@Data
@NoArgsConstructor
public class OrderItemVo {

    @NotNull(message = "商品销售不能为空")
    private Long onsaleId;

    @Min(value = 1, message = "至少购买一个")
    private Integer quantity;

    private Long actId;

    private Long couponId;
}

