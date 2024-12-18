package cn.edu.xmu.oomall.order.dao;

import cn.edu.xmu.javaee.core.exception.BusinessException;
import cn.edu.xmu.javaee.core.model.ReturnNo;
import cn.edu.xmu.javaee.core.model.dto.UserDto;
import cn.edu.xmu.javaee.core.util.JacksonUtil;
import cn.edu.xmu.oomall.order.dao.bo.Order;
import cn.edu.xmu.oomall.order.dao.bo.OrderItem;
import cn.edu.xmu.oomall.order.mapper.OrderItemPoMapper;
import cn.edu.xmu.oomall.order.mapper.po.OrderItemPo;
import cn.edu.xmu.oomall.order.mapper.po.OrderPo;
import cn.edu.xmu.oomall.order.util.CloneFactory;
import lombok.extern.slf4j.Slf4j;
import org.apache.rocketmq.spring.core.RocketMQTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.messaging.Message;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Repository
@Slf4j
public class OrderItemDao {

    private OrderItemPoMapper orderItemPoMapper;


    @Autowired
    @Lazy
    public OrderItemDao(OrderItemPoMapper orderItemPoMapper) {
        this.orderItemPoMapper = orderItemPoMapper;
    }

    public List<OrderItem> findByOrderId(Long orderId){
        log.debug("findByOrderId: orderId = {}", orderId);
        List<OrderItemPo> poList = orderItemPoMapper.findByOrderId(orderId);
        List<OrderItem> orderItemList = new ArrayList<>();
        for (OrderItemPo po : poList) {
            log.debug("findByOrderId: retrieve from database orderItem = {}", po);
            OrderItem bo = CloneFactory.copy(new OrderItem(), po);
            build(bo);
            orderItemList.add(bo);
        }
        return orderItemList;
    }

    private void build(OrderItem bo) throws RuntimeException{
        bo.setOrderItemDao(this);
    }
}
