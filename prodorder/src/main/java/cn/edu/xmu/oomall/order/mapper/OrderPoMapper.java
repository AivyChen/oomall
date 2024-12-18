//School of Informatics Xiamen University, GPL-3.0 license

package cn.edu.xmu.oomall.order.mapper;

import cn.edu.xmu.oomall.order.dao.bo.Order;
import cn.edu.xmu.oomall.order.mapper.po.OrderPo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Repository
public interface OrderPoMapper extends JpaRepository<OrderPo, Long> {
    List<OrderPo> findOrder(String orderSn, Long status, LocalDate beginTime, LocalDate endTime, Long customerId);
    OrderPo save(OrderPo orderPo);
    Optional<OrderPo> findById(Long id);
    void deleteOrderById(Long id);
}
