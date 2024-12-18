package cn.edu.xmu.oomall.order.dao;

import cn.edu.xmu.javaee.core.exception.BusinessException;
import cn.edu.xmu.javaee.core.model.ReturnNo;
import cn.edu.xmu.javaee.core.model.dto.UserDto;
import cn.edu.xmu.javaee.core.util.JacksonUtil;
import cn.edu.xmu.oomall.order.dao.bo.Order;
import cn.edu.xmu.oomall.order.mapper.OrderItemPoMapper;
import cn.edu.xmu.oomall.order.mapper.OrderPoMapper;
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
import java.util.Optional;

import static cn.edu.xmu.javaee.core.model.Constants.IDNOTEXIST;

@Repository
@Slf4j
public class OrderDao {

    private OrderPoMapper orderPoMapper;


    @Autowired
    @Lazy
    public OrderDao(OrderPoMapper orderPoMapper) {
        this.orderPoMapper = orderPoMapper;
    }

    public List<Order> findOrder(String orderSn, Long status, LocalDate beginTime, LocalDate endTime, Long customerId){
        log.debug("findOrder: orderSn = {}, status = {}, beginTime = {}, endTime = {}, customerId = {}", orderSn, status, beginTime, endTime, customerId);
        List<OrderPo> poList = orderPoMapper.findOrder(orderSn, status, beginTime, endTime, customerId);
        List<Order> orderList = new ArrayList<>();
        for (OrderPo po : poList) {
            log.debug("findOrder: retrieve from database order = {}", po);
            Order bo = CloneFactory.copy(new Order(), po);
            build(bo);
            orderList.add(bo);
        }
        return orderList;
    }

    public Order insert(Order bo, UserDto user){
        bo.setId(null);
        bo.setCreator(user);
        bo.setGmtCreate(LocalDateTime.now());
        OrderPo po = CloneFactory.copy(new OrderPo(), bo);
        log.debug("save: po = {}", po);
        po = orderPoMapper.save(po);
        bo.setId(po.getId());
        return bo;
    }

    public void save(Order bo, UserDto user){
        bo.setModifier(user);
        bo.setGmtModified(LocalDateTime.now());
        OrderPo po = CloneFactory.copy(new OrderPo(), bo);
        log.debug("save: po = {}", po);
        OrderPo updatePo = orderPoMapper.save(po);
        if (IDNOTEXIST.equals(updatePo.getId())) {
            throw new BusinessException(ReturnNo.RESOURCE_ID_NOTEXIST, String.format(ReturnNo.RESOURCE_ID_NOTEXIST.getMessage(), "订单", bo.getId()));
        }
    }

    public Order findById(Long id){
        log.debug("findById: id = {}", id);
        Optional<OrderPo> ret = orderPoMapper.findById(id);
        if (ret.isPresent()) {
            OrderPo po = ret.get();
            log.debug("findById: retrieve from database order = {}", po);
            Order bo = CloneFactory.copy(new Order(), po);
            build(bo);
            return bo;
        } else {
            throw new BusinessException(ReturnNo.RESOURCE_ID_NOTEXIST, String.format(ReturnNo.RESOURCE_ID_NOTEXIST.getMessage(), "订单", id));
        }
    }

    public void delete(Long id){
        log.debug("delete: id = {}", id);
        orderPoMapper.deleteOrderById(id);
    }

    private void build(Order bo) throws RuntimeException{
        bo.setOrderDao(this);
    }
}
