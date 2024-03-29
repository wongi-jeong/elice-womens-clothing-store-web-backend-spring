package cloud2.shopingmall.order.entity;

import cloud2.shopingmall.order.dto.OrderProductDTO;
import cloud2.shopingmall.order.mapper.OrderMainMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import static org.assertj.core.api.Assertions.*;

@SpringBootTest
@Transactional
class OrderProductTest {
    @Autowired
    OrderMainMapper.OrderProductMapper mapper;
    @Test
    void EntityToDTOMapping(){
        OrderProduct orderProduct = new OrderProduct();
        orderProduct.setId(1L);
        orderProduct.setProductCount(3);

        OrderProductDTO dto = mapper.toDto(orderProduct);
        assertThat(dto).isNotNull();
        assertThat(dto.getProductCount()).isEqualTo(orderProduct.getProductCount());

    }
    @Test
    void DTOToEntity(){
        OrderProductDTO orderProductDTO = new OrderProductDTO();
        orderProductDTO.setProductCount(2);

        OrderProduct orderProduct = mapper.toEntity(orderProductDTO);

        assertThat(orderProduct.getProductCount()).isEqualTo(orderProductDTO.getProductCount());
    }


}