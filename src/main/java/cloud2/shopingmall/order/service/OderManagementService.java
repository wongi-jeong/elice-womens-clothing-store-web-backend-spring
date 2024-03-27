package cloud2.shopingmall.order.service;

import cloud2.shopingmall.order.dto.DeliveryDTO;
import cloud2.shopingmall.order.dto.OrderProductDTO;
import cloud2.shopingmall.order.dto.OrderUserDTO;
import cloud2.shopingmall.order.dto.PaymentDTO;
import cloud2.shopingmall.order.entity.Orders;
import cloud2.shopingmall.order.repository.DeliveryRepository;
import cloud2.shopingmall.order.repository.OrderProductRepository;
import cloud2.shopingmall.order.repository.OrderRepository;
import cloud2.shopingmall.order.repository.OrderUserRepository;
import cloud2.shopingmall.product.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class OderManagementService {
    private final OrderRepository orderRepository;
    private final OrderUserRepository orderUserRepository;
    private final OrderProductRepository orderProductRepository;
    private final ProductRepository productRepository;
    private final DeliveryRepository deliveryRepository;
    @Transactional
    public Orders createOrder(List<OrderProductDTO> orderProductDTOS, OrderUserDTO orderUserDTO, DeliveryDTO deliveryDTO, PaymentDTO paymentDTO){
        for(OrderProductDTO productDTO : orderProductDTOS){

        }
        return new Orders();
    }

}
