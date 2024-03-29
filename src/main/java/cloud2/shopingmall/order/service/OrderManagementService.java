package cloud2.shopingmall.order.service;

import cloud2.shopingmall.order.dto.*;
import cloud2.shopingmall.order.entity.*;
import cloud2.shopingmall.order.mapper.OrderMainMapper;
import cloud2.shopingmall.order.repository.DeliveryRepository;
import cloud2.shopingmall.order.repository.OrderProductRepository;
import cloud2.shopingmall.order.repository.OrderRepository;
import cloud2.shopingmall.order.repository.PaymentRepository;
import cloud2.shopingmall.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class OrderManagementService {
    /**
     * 주문 생성
     * 주문 취소
     * 주문 내용 변경
     * 주문 상태 업데이트
     */
    private final OrderRepository orderRepository;
    private final OrderProductRepository orderProductRepository;
    private final PaymentRepository paymentRepository;
    private final DeliveryRepository deliveryRepository;;
    private final UserRepository userRepository;
    private final OrderMainMapper.OrderProductMapper orderProductMapper;
    private final OrderMainMapper.OrderMapper orderMapper;
    private final OrderMainMapper.PaymentMapper paymentMapper;
    private final OrderMainMapper.DeliveryMapper deliveryMapper;
    @Transactional
    public OrderDTO createOrderForProduct(String userName, OrderProductDTO productDTO,DeliveryDTO deliveryDTO, PaymentDTO paymentDTO){
        //개별상품 주문
        OrderProduct product = orderProductMapper.toEntity(productDTO);
        orderProductRepository.save(product);
        Payment payment = paymentMapper.toEntity(paymentDTO);
        paymentRepository.save(payment);
        Delivery delivery = deliveryMapper.toEntity(deliveryDTO);
        deliveryRepository.save(delivery);
        Orders order = new Orders();
       order.setUser(userRepository.findByUsername(userName));
       order.setPayment(payment);
       order.setDelivery(delivery);
       order.getOrderProducts().add(product);
       order.setOrderStatus(Orders.OrderStatus.PAYMENT_COMPLETED);
       Orders savedOrder = orderRepository.save(order);
       return  orderMapper.toDto(savedOrder);
    }
    @Transactional
    public OrderDTO createOrderForCart(String userName, List<OrderProductDTO> productDTOs,DeliveryDTO deliveryDTO, PaymentDTO paymentDTO){
        //장바구니 상품 주문
        Payment payment = paymentMapper.toEntity(paymentDTO);
        paymentRepository.save(payment);
        Delivery delivery = deliveryMapper.toEntity(deliveryDTO);
        deliveryRepository.save(delivery);
        Orders order = new Orders();
        order.setUser(userRepository.findByUsername(userName));
        order.setPayment(payment);
        order.setDelivery(delivery);
        for(OrderProductDTO productDTO : productDTOs){
            OrderProduct product = orderProductMapper.toEntity(productDTO);
            orderProductRepository.save(product);
            order.getOrderProducts().add(product);
        }
        Orders savedOrder = orderRepository.save(order);
        return  orderMapper.toDto(savedOrder);
    }

}
