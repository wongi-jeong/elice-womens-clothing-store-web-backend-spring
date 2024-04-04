package cloud2.shopingmall.order.service;

import cloud2.shopingmall.common.exception.OrderException;
import cloud2.shopingmall.order.dto.*;
import cloud2.shopingmall.order.entity.*;
import cloud2.shopingmall.order.mapper.OrderMainMapper;
import cloud2.shopingmall.order.repository.OrderProductRepository;
import cloud2.shopingmall.order.repository.OrderRepository;
import cloud2.shopingmall.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

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
    private final UserRepository userRepository;
    private final OrderMainMapper.OrderProductMapper orderProductMapper;
    private final OrderMainMapper.OrderMapper orderMapper;
    private final OrderProductRepository orderProductRepository;
    private final PaymentService paymentService;
    @Transactional
    public OrderDTO createOrderForProduct(String userName,Integer totalPrice){
        //개별상품 주문
        boolean isPaymentSuccessful = paymentService.verifyPayment(userName,totalPrice);
        if (!isPaymentSuccessful) {
            throw new OrderException.CustomException();
        }
        Orders order = new Orders();
       order.setUser(userRepository.findByUsername(userName));
       order.setStatus(Orders.OrderStatus.PAYMENT_COMPLETED);
       Orders savedOrder = orderRepository.save(order);
       return  orderMapper.toDto(savedOrder);
    }
    @Transactional
    public OrderDTO createOrderForCart(String userName,Integer totalPrice){
        //장바구니 상품 주문
        boolean isPaymentSuccessful = paymentService.verifyPayment(userName,totalPrice);
        if (!isPaymentSuccessful) {
            throw new OrderException.CustomException();
        }
        Orders order = new Orders();
        order.setUser(userRepository.findByUsername(userName));
        order.setStatus(Orders.OrderStatus.PAYMENT_COMPLETED);
        Orders savedOrder = orderRepository.save(order);
        return  orderMapper.toDto(savedOrder);
    }
    @Transactional
    public OrderDTO canceledOrder(Long orderId){
        //주문 상태가 결제완료 혹은 배송 준비일때만 주문 취소 가능
        //추후 환불 로직 추가

        Orders order = orderRepository.findById(orderId).orElseThrow(()->new OrderException.OrderNotFoundException(orderId));
        if(!(order.getStatus() == Orders.OrderStatus.PAYMENT_COMPLETED || order.getStatus() == Orders.OrderStatus.PREPARING_FOR_DELIVERY)) {
            throw new OrderException.OrderCancellationNotAllowedException(order.getId());
        }

        order.setStatus(Orders.OrderStatus.ORDER_CANCELLED);
        Orders save = orderRepository.save(order);
        return orderMapper.toDto(save);

    }
    @Transactional
    public void createOrderProduct(List<OrderProductDTO> orderProductDTOS, Long orderId,String userName, Integer totalPrice){
        boolean isPaymentSuccessful = paymentService.verifyPayment(userName,totalPrice);
        if (!isPaymentSuccessful) {
            throw new OrderException.CustomException();
        }
        //장바구니 주문 결제시
        if(orderProductDTOS.isEmpty()){
            throw new OrderException.OrderNotFoundOrderProductException();
        }
        Orders orders = orderRepository.findById(orderId)
                .orElseThrow(() -> new OrderException.OrderNotFoundException(orderId));

        List<OrderProduct> orderProducts = orderProductDTOS.stream()
                .map(orderProductMapper::toEntity)
                .peek(orderProduct -> orderProduct.setOrders(orders))
                .collect(Collectors.toList());

        orderProductRepository.saveAll(orderProducts);
    }

    @Transactional
    public void createOrderProduct(OrderProductDTO orderProductDTO, Long orderId,String userName, Integer totalPrice) {
        boolean isPaymentSuccessful = paymentService.verifyPayment(userName,totalPrice);
        if (!isPaymentSuccessful) {
            throw new OrderException.CustomException();
        }
        //상품 주문 결제시
        if (orderProductDTO == null) {
            throw new OrderException.OrderNotFoundOrderProductException();
        }
        OrderProduct orderProduct = orderProductMapper.toEntity(orderProductDTO);
        Orders order = orderRepository.findById(orderId)
                .orElseThrow(() -> new OrderException.OrderNotFoundException(orderId));
        orderProduct.setOrders(order);
        if (order.getOrderProducts() == null) {
            order.setOrderProducts(new ArrayList<>());
        }
        order.getOrderProducts().add(orderProduct);

        orderProductRepository.save(orderProduct);
        orderRepository.save(order);
    }

    public void updateOrder(Long orderId){
        Orders order = orderRepository.findById(orderId).orElseThrow(()-> new OrderException.OrderNotFoundException(orderId));

    }

}
