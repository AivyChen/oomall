package cn.edu.xmu.oomall.order.adaptor;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class ProductServiceAdapter implements FactoryAdapter{

    private final ProductService productService;

    @Override
    public Object fetchData(Long id) {
        // 调用商品服务的接口
        return productService.getProductById(id);
    }
}
