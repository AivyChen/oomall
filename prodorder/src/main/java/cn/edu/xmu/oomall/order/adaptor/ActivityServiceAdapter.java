package cn.edu.xmu.oomall.order.adaptor;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.jaxb.OrderAdapter;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class ActivityServiceAdapter implements FactoryAdapter {

    private final ActivityService activityService;

    @Override
    public Object fetchData(Long id) {
        // 调用活动服务的接口
        return activityService.getActivityById(id);
    }

    @Override
    public Object getData(Long id) {
        return null;
    }
}
