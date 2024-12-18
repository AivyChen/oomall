package cn.edu.xmu.oomall.customer.mapper;

import com.example.customer.mapper.po.CouponPo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CouponPoMapper extends JpaRepository<CouponPo,Long> {
}
