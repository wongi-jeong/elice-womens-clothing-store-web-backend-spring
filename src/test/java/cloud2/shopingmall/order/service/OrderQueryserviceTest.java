package cloud2.shopingmall.order.service;

import cloud2.shopingmall.order.dto.OrderInfoDTO;
import cloud2.shopingmall.order.entity.OrderProduct;
import cloud2.shopingmall.order.entity.Orders;
import cloud2.shopingmall.order.entity.Payment;
import cloud2.shopingmall.order.repository.OrderProductRepository;
import cloud2.shopingmall.order.repository.OrderRepository;
import cloud2.shopingmall.user.entity.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

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
        // Given
        Orders mockOrder = Mockito.mock(Orders.class);
        User mockUser = Mockito.mock(User.class);
        Payment mockPayment = Mockito.mock(Payment.class);

        when(mockOrder.getId()).thenReturn(1L);
        when(mockOrder.getOrderStatus()).thenReturn(Orders.OrderStatus.PAYMENT_COMPLETED);
        when(mockOrder.getOrderCreatedAt()).thenReturn(LocalDateTime.now());
        when(mockOrder.getOrderModifiedAt()).thenReturn(LocalDateTime.now());
        when(mockOrder.getUser()).thenReturn(mockUser);
        when(mockOrder.getPayment()).thenReturn(mockPayment);
        when(mockUser.getUsername()).thenReturn("user123");
        when(mockPayment.getPayTotalPrice()).thenReturn(1000);

        when(orderRepository.findAllWithOrderUserAndPayment()).thenReturn(Arrays.asList(mockOrder));

        // When
        List<OrderInfoDTO> result = orderQueryservice.findAllOrder();

        // Then
        assertThat(result).isNotEmpty();
        assertThat(result.get(0).getOrderUser()).isEqualTo("user123");
    }

    @Test
    void findByUser() {
        // Given
        Orders mockOrder = Mockito.mock(Orders.class);
        User mockUser = Mockito.mock(User.class);
        Payment mockPayment = Mockito.mock(Payment.class);
        OrderProduct mockorderProduct = Mockito.mock(OrderProduct.class);

        when(mockOrder.getId()).thenReturn(1L);
        when(mockOrder.getOrderStatus()).thenReturn(Orders.OrderStatus.PAYMENT_COMPLETED);
        when(mockOrder.getOrderCreatedAt()).thenReturn(LocalDateTime.now());
        when(mockOrder.getOrderModifiedAt()).thenReturn(LocalDateTime.now());
        when(mockOrder.getUser()).thenReturn(mockUser);
        when(mockOrder.getPayment()).thenReturn(mockPayment);
        when(mockOrder.getOrderProducts()).thenReturn(Arrays.asList(mockorderProduct));
        when(mockUser.getUsername()).thenReturn("user123");
        when(mockPayment.getPayTotalPrice()).thenReturn(1000);

        when(orderRepository.findOrdersByUsername("user123")).thenReturn(Arrays.asList(mockOrder));

        // When
        List<OrderInfoDTO.OrderDetailInfo> result = orderQueryservice.findByUser("user123");

        // Then
        assertThat(result).isNotEmpty();
        assertThat(result.get(0).getTotalPrice()).isEqualTo(1000);
    }

    @Test
    void findByOrderId() { // Given
        Orders mockOrder = Mockito.mock(Orders.class);
        User mockUser = Mockito.mock(User.class);
        Payment mockPayment = Mockito.mock(Payment.class);
        OrderProduct mockorderProduct = Mockito.mock(OrderProduct.class);

        when(mockOrder.getId()).thenReturn(1L);
        when(mockOrder.getOrderStatus()).thenReturn(Orders.OrderStatus.PAYMENT_COMPLETED);
        when(mockOrder.getOrderCreatedAt()).thenReturn(LocalDateTime.now());
        when(mockOrder.getOrderModifiedAt()).thenReturn(LocalDateTime.now());
        when(mockOrder.getUser()).thenReturn(mockUser);
        when(mockOrder.getPayment()).thenReturn(mockPayment);
        when(mockOrder.getOrderProducts()).thenReturn(Arrays.asList(mockorderProduct));
        when(mockUser.getUsername()).thenReturn("user123");
        when(mockPayment.getPayTotalPrice()).thenReturn(1000);

        when(orderRepository.findOrdersByOrderId(1L)).thenReturn(mockOrder);

        // When
        OrderInfoDTO.OrderDetailInfo result = orderQueryservice.findByOrderId(1L);

        // Then
        assertThat(result).isNotNull();
        assertThat(result.getTotalPrice()).isEqualTo(1000);
    }
}