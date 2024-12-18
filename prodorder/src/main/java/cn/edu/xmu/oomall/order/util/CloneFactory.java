package cn.edu.xmu.oomall.order.util;

import cn.edu.xmu.oomall.order.controller.vo.OrderVo;
import cn.edu.xmu.oomall.order.dao.bo.Order;
import cn.edu.xmu.oomall.order.dao.bo.OrderItem;
import cn.edu.xmu.oomall.order.mapper.po.OrderItemPo;
import cn.edu.xmu.oomall.order.mapper.po.OrderPo;

public final class CloneFactory {
    /**
     * Copy all fields from source to target
     * @param target the target object
     * @param source the source object
     * @return the copied target object
     */
    public static Order copy(Order target, OrderPo source) {
        return target;
    }

    /**
     * Copy all fields from source to target
     * @param target the target object
     * @param source the source object
     * @return the copied target object
     */
    public static OrderPo copy(OrderPo target, Order source) {
        return target;
    }

    /**
     * Copy all fields from source to target
     * @param target the target object
     * @param source the source object
     * @return the copied target object
     */
    public static OrderItem copy(OrderItem target, OrderItemPo source) {
        return target;
    }

    /**
     * Copy all fields from source to target
     * @param target the target object
     * @param source the source object
     * @return the copied target object
     */
    public static OrderVo copy(OrderVo target, Order source) {
        return target;
    }
}
