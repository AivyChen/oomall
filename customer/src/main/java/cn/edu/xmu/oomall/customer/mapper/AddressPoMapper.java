package cn.edu.xmu.oomall.customer.mapper;

import com.example.customer.mapper.po.AddressPo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AddressPoMapper extends JpaRepository<AddressPo,Long> {
}
