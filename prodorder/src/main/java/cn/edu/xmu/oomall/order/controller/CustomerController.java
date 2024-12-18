//School of Informatics Xiamen University, GPL-3.0 license

package cn.edu.xmu.oomall.order.controller;

import cn.edu.xmu.javaee.core.aop.LoginUser;
import cn.edu.xmu.javaee.core.model.ReturnNo;
import cn.edu.xmu.javaee.core.model.ReturnObject;
import cn.edu.xmu.javaee.core.model.dto.UserDto;
import cn.edu.xmu.oomall.order.controller.dto.OrderDto;
import cn.edu.xmu.oomall.order.controller.vo.OrderVo;
import cn.edu.xmu.oomall.order.dao.bo.Order;
import cn.edu.xmu.oomall.order.service.OrderService;
import cn.edu.xmu.oomall.order.util.CloneFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@RestController /*Restful的Controller对象*/
@RequiredArgsConstructor
@RequestMapping(produces = "application/json;charset=UTF-8")
public class CustomerController {

    private final OrderService orderService;

    @GetMapping("/orders")
    public ReturnObject getOrder(@RequestParam(required = false) String orderSn,
                                  @RequestParam(required = false) Long status,
                                  @RequestParam(required = false) LocalDate beginTime,
                                  @RequestParam(required = false) LocalDate endTime,
                                  @LoginUser UserDto user) {
        List<Order> orderList = this.orderService.getOrder(orderSn, status, beginTime, endTime, user);
        if (Objects.nonNull(orderList)){
            List<OrderVo> orderVoList = new ArrayList<>();
            for (Order order : orderList) {
                OrderVo orderVo = new OrderVo(order);
                orderVoList.add(orderVo);
            }
            return new ReturnObject(orderVoList);
        }
        else return new ReturnObject();
    }

    @GetMapping("/orders/{id}")
    public ReturnObject getOrder(@PathVariable Long id,
                                 @LoginUser UserDto user) {
        Order order = orderService.getOrderById(id, user);
        return new ReturnObject();
    }

    @PostMapping("/orders")
    public ReturnObject createOrder(@Validated @RequestBody OrderDto orderDto,
                                    @LoginUser UserDto user) {
        Order order = Order.builder().build();
        Order newOrder = this.orderService.createOrder(order, user);
        return new ReturnObject(ReturnNo.CREATED, CloneFactory.copy(new OrderVo(), newOrder));
    }


}
