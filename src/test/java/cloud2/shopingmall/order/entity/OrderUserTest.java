package cloud2.shopingmall.order.entity;

import cloud2.shopingmall.order.dto.OrderDTO;
import cloud2.shopingmall.order.dto.OrderUserDTO;
import cloud2.shopingmall.order.mapper.OrderMainMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;


import static org.assertj.core.api.Assertions.*;
@SpringBootTest
class OrderUserTest {
    @Autowired
    OrderMainMapper.OrderUserMapper mapper;

    @Test
    void EntityToDTO(){
        OrderUser orderUser = new OrderUser();
        orderUser.setOrderNumber(1333333);
        orderUser.setOrderUserName("주문자");
        orderUser.setOrderPassword("111111");

        OrderUserDTO orderUserDTO = mapper.toDto(orderUser);

        assertThat(orderUserDTO.getOrderUserName()).isEqualTo(orderUser.getOrderUserName());
        assertThat(orderUserDTO.getOrderNumber()).isEqualTo(orderUser.getOrderNumber());
        assertThat(orderUserDTO.getOrderPassword()).isEqualTo(orderUser.getOrderPassword());


    }
    @Test
    void DTOToEntity(){
        OrderUserDTO orderUserDTO = new OrderUserDTO();
        orderUserDTO.setOrderNumber(222222);
        orderUserDTO.setOrderPassword("121212");
        orderUserDTO.setOrderUserName("주문자2");

        OrderUser orderUser = mapper.toEntity(orderUserDTO);

        assertThat(orderUserDTO.getOrderNumber()).isEqualTo(orderUser.getOrderNumber());
        assertThat(orderUserDTO.getOrderUserName()).isEqualTo(orderUser.getOrderUserName());
        assertThat(orderUserDTO.getOrderPassword()).isEqualTo(orderUser.getOrderPassword());
    }

}