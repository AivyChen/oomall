package cn.edu.xmu.oomall.customer.mapper;

import com.example.customer.mapper.po.CartitemPo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CartitemPoMapper extends JpaRepository<CartitemPo,Long> {

}
