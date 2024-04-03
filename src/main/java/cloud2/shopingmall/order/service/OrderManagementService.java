package cloud2.shopingmall.order.service;

import cloud2.shopingmall.common.exception.OrderException;
import cloud2.shopingmall.order.dto.OrderDTO;
import cloud2.shopingmall.order.dto.OrderProductDTO;
import cloud2.shopingmall.order.entity.OrderProduct;
import cloud2.shopingmall.order.entity.Orders;
import cloud2.shopingmall.order.mapper.OrderMainMapper;
import cloud2.shopingmall.order.repository.OrderProductRepository;
import cloud2.shopingmall.order.repository.OrderRepository;
import cloud2.shopingmall.user.repository.UserRepository;
import java.util.List;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Slf4j
public class OrderManagementService {
    /**
     * 주문 생성 주문 취소 주문 내용 변경 주문 상태 업데이트
     */
    private final OrderRepository orderRepository;
    private final UserRepository userRepository;
    private final OrderMainMapper.OrderProductMapper orderProductMapper;
    private final OrderMainMapper.OrderMapper orderMapper;
    private final OrderMainMapper.PaymentMapper paymentMapper;
    private final OrderMainMapper.DeliveryMapper deliveryMapper;
    private final OrderProductRepository orderProductRepository;

    @Transactional
    public OrderDTO createOrderForProduct(String userName) {
        //개별상품 주문
        Orders order = new Orders();
        order.setUser(userRepository.findByUsername(userName));
        order.setStatus(Orders.OrderStatus.PAYMENT_COMPLETED);
        Orders savedOrder = orderRepository.save(order);
        return orderMapper.toDto(savedOrder);
    }

    @Transactional
    public OrderDTO createOrderForCart(String userName) {
        //장바구니 상품 주문
        Orders order = new Orders();
        order.setUser(userRepository.findByUsername(userName));
        order.setStatus(Orders.OrderStatus.PAYMENT_COMPLETED);
        Orders savedOrder = orderRepository.save(order);
        return orderMapper.toDto(savedOrder);
    }

    @Transactional
    public OrderDTO canceledOrder(OrderDTO orderDTO) {
        //주문 상태가 결제완료 혹은 배송 준비일때만 주문 취소 가능
        Orders order = orderRepository.findById(orderDTO.getId())
                .orElseThrow(() -> new OrderException.OrderNotFoundException(orderDTO.getId()));
        if (!(order.getStatus() == Orders.OrderStatus.PAYMENT_COMPLETED
                || order.getStatus() == Orders.OrderStatus.PREPARING_FOR_DELIVERY)) {
            throw new OrderException.OrderCancellationNotAllowedException(order.getId());
        }

        order.setStatus(Orders.OrderStatus.ORDER_CANCELLED);
        return orderMapper.toDto(order);

    }

    public void createOrderProduct(List<OrderProductDTO> orderProductDTOS, Long orderId) {
        //장바구니 주문 결제시
        if (orderProductDTOS.isEmpty()) {
            throw new OrderException.OrderNotFoundOrderProductException();
        }
        orderProductDTOS.stream().forEach(orderProductDTO -> {
            OrderProduct orderProduct = new OrderProduct();
            orderProduct.setId(orderProductDTO.getProductId());
            orderProduct.setProductCount(orderProductDTO.getProductCount());
            orderProduct.setOrders(orderRepository.findById(orderId).orElseThrow(() ->
                    new OrderException.OrderNotFoundException(orderId)));
            orderProductRepository.save(orderProduct);
        });
    }

    public void createOrderProduct(OrderProductDTO orderProductDTO, Long orderId) {
        //상품 주문 결제시
        if (orderProductDTO == null) {
            throw new OrderException.OrderNotFoundOrderProductException();
        }
        OrderProduct orderProduct = new OrderProduct();
        orderProduct.setId(orderProductDTO.getProductId());
        orderProduct.setProductCount(orderProductDTO.getProductCount());
        orderProduct.setOrders(orderRepository.findById(orderId)
                .orElseThrow(() -> new OrderException.OrderNotFoundException(orderId)));
        orderProductRepository.save(orderProduct);
    }

    public void updateOrder(Long orderId) {
        Orders order = orderRepository.findById(orderId)
                .orElseThrow(() -> new OrderException.OrderNotFoundException(orderId));

    }

}
