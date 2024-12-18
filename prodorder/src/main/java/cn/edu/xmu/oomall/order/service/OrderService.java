//School of Informatics Xiamen University, GPL-3.0 license

package cn.edu.xmu.oomall.order.service;

import cn.edu.xmu.javaee.core.exception.BusinessException;
import cn.edu.xmu.javaee.core.model.ReturnNo;
import cn.edu.xmu.javaee.core.model.dto.UserDto;
import cn.edu.xmu.oomall.order.OrderFactory;
import cn.edu.xmu.oomall.order.adaptor.FactoryAdapter;
import cn.edu.xmu.oomall.order.dao.OrderDao;
import cn.edu.xmu.oomall.order.dao.OrderItemDao;
import cn.edu.xmu.oomall.order.dao.bo.Activity;
import cn.edu.xmu.oomall.order.dao.bo.Order;
import cn.edu.xmu.oomall.order.dao.bo.OrderItem;
import cn.edu.xmu.oomall.order.dao.bo.Product;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.swing.plaf.synth.Region;
import java.time.LocalDate;
import java.util.List;
import java.util.Objects;

@Service
@Transactional(propagation = Propagation.REQUIRED)
@Slf4j
@RequiredArgsConstructor
public class OrderService {

    private final OrderDao orderDao;

    private final OrderItemDao orderItemDao;

    public Order createOrder(Order order, UserDto user) {
        FactoryAdapter productAdapter = OrderFactory.createAdapter("product");
        Product product = (Product) productAdapter.getData(order.getOrderItems().get(0).getOnsaleId());
        FactoryAdapter activityAdapter = OrderFactory.createAdapter("activity");
        Activity activity = (Activity) activityAdapter.getData(order.getActivityId());
        FactoryAdapter regionAdapter = OrderFactory.createAdapter("region");
        Region region = (Region) regionAdapter.getData(order.getRegionId());
        Order newOrder = orderDao.insert(order, user);
        log.debug("createOrder: order = {}", newOrder);
        return newOrder;
    }

    public Order getOrderById(Long id, UserDto user) {
        Order order = orderDao.findById(id);
        Long customerId = order.getCustomerId();
        if (Objects.equals(customerId, user.getId())) {
            order.updateOrderItems();
            return order;
        }
        else{
            throw new BusinessException(ReturnNo.RESOURCE_ID_OUTSCOPE, String.format(ReturnNo.RESOURCE_ID_OUTSCOPE.getMessage(), "订单", user.getId()));
        }
    }

    public List<Order> getOrder(String orderSn, Long status, LocalDate beginTime, LocalDate endTime, UserDto user){
        Long customerId = user.getId();
        if(customerId != null){
            if(Objects.equals(status, null) || Objects.equals(status, Order.WAITPAY) || Objects.equals(status, Order.PAY) || Objects.equals(status, Order.FINISH) || Objects.equals(status, Order.CANCEL)) {
                List<Order> orderList = this.orderDao.findOrder(orderSn, status, beginTime, endTime, customerId);
                return orderList;
            }
            else {
                throw new BusinessException(ReturnNo.RESOURCE_ID_NOTEXIST, String.format(ReturnNo.RESOURCE_ID_NOTEXIST.getMessage(), "订单", orderSn));
            }
        }
        else{
            throw new BusinessException(ReturnNo.RESOURCE_ID_NOTEXIST, String.format(ReturnNo.RESOURCE_ID_NOTEXIST.getMessage(), "订单", orderSn));
        }
    }

    public void modifyOrder(Order oldOrder, UserDto user) {
        log.debug("modifyOrder: oldOrder = {}", oldOrder);
        Long customerId = user.getId();
        if(customerId != null){
            Order order = this.orderDao.findById(oldOrder.getId());
            if(!Objects.equals(order, null)) {
                if (order.getCustomerId() != customerId) {
                    throw new BusinessException(ReturnNo.RESOURCE_ID_OUTSCOPE, String.format(ReturnNo.RESOURCE_ID_OUTSCOPE.getMessage(), "订单", customerId));
                }
                Long status = order.getStatus();
                if (status != Order.UNSEND) {
                    throw new BusinessException(ReturnNo.ORDERSTATUS_CHANGENOTALLOW, String.format(ReturnNo.ORDERSTATUS_CHANGENOTALLOW.getMessage(), "订单", order.getStatus()));
                } else {
                    orderDao.save(order, user);
                }
            }
            else{
                throw new BusinessException(ReturnNo.RESOURSE_NOT_EXISTS, String.format(ReturnNo.RESOURSE_NOT_EXISTS.getMessage(), "订单", customerId));
            }
        }
        else{
            throw new BusinessException(ReturnNo.RESOURCE_ID_NOTEXIST, String.format(ReturnNo.RESOURCE_ID_NOTEXIST.getMessage(), "订单", null));
        }
    }

    public void deleteOrder(Long id, UserDto user) {
        log.debug("deleteOrder: id = {}", id);
        Long customerId = user.getId();
        if(customerId != null){
            Order order = this.orderDao.findById(id);
            if(!Objects.equals(order, null)) {
                if (order.getCustomerId() != customerId) {
                    throw new BusinessException(ReturnNo.RESOURCE_ID_OUTSCOPE, String.format(ReturnNo.RESOURCE_ID_OUTSCOPE.getMessage(), "订单", customerId));
                }
                orderDao.delete(id);
            }
            else{
                throw new BusinessException(ReturnNo.RESOURSE_NOT_EXISTS, String.format(ReturnNo.RESOURSE_NOT_EXISTS.getMessage(), "订单", customerId));
            }
        }
        else{
            throw new BusinessException(ReturnNo.RESOURCE_ID_NOTEXIST, String.format(ReturnNo.RESOURCE_ID_NOTEXIST.getMessage(), "订单", null));
        }
    }
}
