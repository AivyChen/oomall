package cn.edu.xmu.oomall.customer.mapper;

import com.example.customer.mapper.po.CustomerPo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CustomerPoMapper extends JpaRepository<CustomerPo, Long> {
}
