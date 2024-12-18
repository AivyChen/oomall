package cn.edu.xmu.oomall.order.adaptor;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

@Service
@Component
@RequiredArgsConstructor
public class RegionServiceAdapter implements FactoryAdapter{

    private final RegionService regionService;

    @Override
    public Object fetchData(Long id) {
        // 调用区域服务的接口
        return regionService.getRegionById(id);
    }
}
