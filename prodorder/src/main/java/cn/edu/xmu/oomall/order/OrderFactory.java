package cn.edu.xmu.oomall.order;

import cn.edu.xmu.oomall.order.adaptor.ActivityServiceAdapter;
import cn.edu.xmu.oomall.order.adaptor.FactoryAdapter;
import cn.edu.xmu.oomall.order.adaptor.ProductServiceAdapter;
import cn.edu.xmu.oomall.order.adaptor.RegionServiceAdapter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.jaxb.OrderAdapter;
import org.springframework.stereotype.Component;

@Component
public class OrderFactory {
    private static final Logger logger = LoggerFactory.getLogger(OrderFactory.class);
    @Autowired
    private static ApplicationContext context;
    public static FactoryAdapter createAdapter(String serviceType) {
        logger.debug("createAdapter: serviceType = {}", serviceType);
        switch (serviceType) {
            case "activity":
                return context.getBean(ActivityServiceAdapter.class);
            case "product":
                return context.getBean(ProductServiceAdapter.class);
            case "region":
                return context.getBean(RegionServiceAdapter.class);
            default:
                throw new IllegalArgumentException("Unknown service type: " + serviceType);
        }
    }
}
