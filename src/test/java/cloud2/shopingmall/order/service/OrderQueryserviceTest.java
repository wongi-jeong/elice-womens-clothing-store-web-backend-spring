package cloud2.shopingmall.order.service;

import cloud2.shopingmall.order.dto.OrderInfoDTO;
import cloud2.shopingmall.order.entity.Orders;
import cloud2.shopingmall.order.repository.OrderProductRepository;
import cloud2.shopingmall.order.repository.OrderRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@SpringBootTest
class OrderQueryserviceTest {
    @Mock
    private OrderRepository orderRepository;
    @Mock
    private OrderProductRepository orderProductRepository;
    @InjectMocks
    private OrderQueryservice orderQueryservice;

    @BeforeEach
    void setUp(){
        MockitoAnnotations.openMocks(this);
    }
    @Test
    void findAllOrder() {
        Orders mockOrder = mock(Orders.class);
        when(orderRepository.findAllWithOrderUserAndPayment()).thenReturn(Arrays.asList(mockOrder));

        // When
        List<OrderInfoDTO> result = orderQueryservice.findAllOrder();

        // Then
        assertThat(result).isNotEmpty();
        verify(orderRepository).findAllWithOrderUserAndPayment();
    }

    @Test
    void findByUser() {
    }

    @Test
    void findByOrderId() {
    }
}