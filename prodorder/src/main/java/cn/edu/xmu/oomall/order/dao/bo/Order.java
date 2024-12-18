//School of Informatics Xiamen University, GPL-3.0 license

package cn.edu.xmu.oomall.order.dao.bo;

import cn.edu.xmu.javaee.core.aop.CopyFrom;
import cn.edu.xmu.javaee.core.model.bo.OOMallObject;
import cn.edu.xmu.oomall.order.dao.OrderDao;
import cn.edu.xmu.oomall.order.dao.OrderItemDao;
import cn.edu.xmu.oomall.order.mapper.po.OrderPo;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import lombok.extern.slf4j.Slf4j;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.*;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@CopyFrom(OrderPo.class)
@Slf4j
public class Order extends OOMallObject implements Serializable {

    @Builder
    public Order(Long id, Long creatorId, String creatorName, Long modifierId, String modifierName, LocalDateTime gmtCreate, LocalDateTime gmtModified, Long customerId, Long shopId, String orderSn, Long pid, String consignee, Long regionId, String address, String mobile, String message, Long activityId, Long packageId, List<OrderItem> orderItems) {
        super(id, creatorId, creatorName, modifierId, modifierName, gmtCreate, gmtModified);
        this.customerId = customerId;
        this.shopId = shopId;
        this.orderSn = orderSn;
        this.pid = pid;
        this.consignee = consignee;
        this.regionId = regionId;
        this.address = address;
        this.mobile = mobile;
        this.message = message;
        this.activityId = activityId;
        this.packageId = packageId;
        this.orderItems = orderItems;
    }
    @Setter
    @Getter
    private Long id;

    @Setter
    @Getter
    private Long customerId;

    @Setter
    @Getter
    private Long shopId;

    @Setter
    @Getter
    private String orderSn;

    @Setter
    @Getter
    private Long pid;

    @Setter
    @Getter
    private String consignee;

    @Setter
    @Getter
    private Long regionId;

    @Setter
    @Getter
    private String address;

    @Setter
    @Getter
    private String mobile;

    @Setter
    @Getter
    private String message;

    @Setter
    @Getter
    private Long status;

    @Setter
    @Getter
    private Long activityId;

    @Setter
    @Getter
    private Activity activity;

    @Setter
    @Getter
    private Long packageId;

    @Setter
    @Getter
    private List<OrderItem> orderItems;

    @ToString.Exclude
    @JsonIgnore
    @Setter
    private OrderDao orderDao;;

    @ToString.Exclude
    @JsonIgnore
    @Setter
    private OrderItemDao orderItemDao;


    /**
     * 待付款
     */
    @ToString.Exclude
    @JsonIgnore
    public static Long WAITPAY = 100L;
    /**
     * 未付款
     */
    @ToString.Exclude
    @JsonIgnore
    public static Long UNPAY = 101L;
    /**
     * 待支付消费
     */
    @ToString.Exclude
    @JsonIgnore
    public static Long UNCONSUME = 102L;
    /**
     * 已付款
     */
    @ToString.Exclude
    @JsonIgnore
    public static Long PAY = 200L;
    /**
     * 待成团
     */
    @ToString.Exclude
    @JsonIgnore
    public static Long UNGROUP = 201L;
    /**
     * 已付费
     */
    @ToString.Exclude
    @JsonIgnore
    public static Long CONSUME = 202L;
    /**
     * 待发货
     */
    @ToString.Exclude
    @JsonIgnore
    public static Long UNSEND = 203L;
    /**
     * 待收货
     */
    @ToString.Exclude
    @JsonIgnore
    public static Long UNRECEIVE = 204L;
    /**
     * 完成
     */
    @ToString.Exclude
    @JsonIgnore
    public static Long FINISH = 300L;
    /**
     * 已取消
     */
    @ToString.Exclude
    @JsonIgnore
    public static Long CANCEL = 400L;
    /**
     * 待退款
     */
    @ToString.Exclude
    @JsonIgnore
    public static Long UNREFUND = 401L;
    /**
     * 已取消
     */
    @ToString.Exclude
    @JsonIgnore
    public static Long CANCELED = 402L;

    /**
     * 状态和名称的对应
     */
    public static final Map<Byte, String> STATUSNAMES = new HashMap() {
//        {
//            put(UNSHIPPED, "未发货");
//            put(SHIPPED, "在途");
//            put(SIGNED, "签收");
//            put(CANCELED, "取消");
//            put(REJECTED, "拒收");
//            put(RETURNED, "已退回");
//            put(LOST, "丢失");
//            put(RECEIVED, "回收");
//            put(BROKEN, "破损");
//        }
    };

    /**
     * 允许的状态迁移
     */
    private static final Map<Byte, Set<Byte>> toStatus = new HashMap<>() {
        {
//            put(UNSHIPPED, new HashSet<>() {
//                {
//                    add(SHIPPED);
//                    add(CANCELED);
//                    add(SIGNED);
//                    add(REJECTED);
//                }
//            });
//            put(SHIPPED, new HashSet<>() {
//                {
//                    add(SIGNED);
//                    add(REJECTED);
//                    add(LOST);
//                }
//            });
//            put(REJECTED, new HashSet<>() {
//                {
//                    add(LOST);
//                    add(RETURNED);
//                }
//            });
//            put(RETURNED, new HashSet<>() {
//                {
//                    add(BROKEN);
//                    add(RECEIVED);
//                }
//            });
        }
    };


    @Override
    public void setGmtCreate(LocalDateTime gmtCreate) {

    }

    @Override
    public void setGmtModified(LocalDateTime gmtModified) {

    }

    public List<OrderItem> updateOrderItems(){
        orderItems = orderItemDao.findByOrderId(id);
        for (OrderItem orderItem : orderItems) {
            orderItem.getMessage();
        }
        return orderItems;
    }
}
