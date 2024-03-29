package cloud2.shopingmall.order.service;

import cloud2.shopingmall.order.dto.DeliveryDTO;
import cloud2.shopingmall.order.dto.OrderProductDTO;
import cloud2.shopingmall.order.dto.OrderUserDTO;
import cloud2.shopingmall.order.dto.PaymentDTO;
import cloud2.shopingmall.order.entity.Orders;
import cloud2.shopingmall.order.repository.DeliveryRepository;
import cloud2.shopingmall.order.repository.OrderProductRepository;
import cloud2.shopingmall.order.repository.OrderRepository;
import cloud2.shopingmall.product.repository.ProductDetailsRepository;
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
    private final ProductDetailsRepository productDetailsRepository;
    private final DeliveryRepository deliveryRepository;

    @Transactional
    public Orders createOrder(List<OrderProductDTO> orderProductDTOS, OrderUserDTO orderUserDTO, DeliveryDTO deliveryDTO, PaymentDTO paymentDTO) {
        for (OrderProductDTO productDTO : orderProductDTOS) {

        }
        return new Orders();
    }

}
