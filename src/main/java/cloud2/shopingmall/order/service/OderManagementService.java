package cloud2.shopingmall.order.service;

import cloud2.shopingmall.order.dto.*;
import cloud2.shopingmall.order.entity.OrderProduct;
import cloud2.shopingmall.order.entity.Orders;
import cloud2.shopingmall.order.mapper.OrderMainMapper;
import cloud2.shopingmall.order.repository.DeliveryRepository;
import cloud2.shopingmall.order.repository.OrderProductRepository;
import cloud2.shopingmall.order.repository.OrderRepository;
import cloud2.shopingmall.order.repository.OrderUserRepository;
import cloud2.shopingmall.product.repository.ProductRepository;
import cloud2.shopingmall.user.mapper.UserMainMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class OderManagementService {
    /**
     * 주문 생성
     * 주문 취소
     * 주문 내용 변경
     * 주문 상태 업데이트
     */
    private final OrderRepository orderRepository;
    private final OrderUserRepository orderUserRepository;
    private final OrderProductRepository orderProductRepository;
    private final ProductRepository productRepository;
    private final DeliveryRepository deliveryRepository;
    private final OrderMainMapper.OrderUserMapper orderUserMapper;
    private final OrderMainMapper.OrderProductMapper orderProductMapper;
    private final OrderMainMapper.OrderMapper orderMapper;
    @Transactional
    public OrderDTO createOrderForProduct(OrderUserDTO userDTO, OrderProductDTO productDTO,DeliveryDTO deliveryDTO, PaymentDTO paymentDTO){
        //개별상품 주문
       Orders order = new Orders();
       order.setOrderUser(orderUserMapper.toEntity(userDTO));
       order.getOrderProducts().add(orderProductMapper.toEntity(productDTO));
       Orders savedOrder = orderRepository.save(order);
       return  orderMapper.toDto(savedOrder);
    }
    @Transactional
    public OrderDTO createOrderForCart(OrderUserDTO userDTO, List<OrderProductDTO> productDTOS,DeliveryDTO deliveryDTO, PaymentDTO paymentDTO){
        //장바구니 상품 주문
        Orders order = new Orders();
        order.setOrderUser(orderUserMapper.toEntity(userDTO));
        for(OrderProductDTO productDTO : productDTOS){
            order.getOrderProducts().add(orderProductMapper.toEntity(productDTO));
        }
        Orders savedOrder = orderRepository.save(order);
        return  orderMapper.toDto(savedOrder);
    }

}
