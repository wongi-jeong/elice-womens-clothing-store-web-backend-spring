package cloud2.shopingmall.order.service;

import cloud2.shopingmall.common.exception.OrderException;
import cloud2.shopingmall.order.dto.DeliveryDTO;
import cloud2.shopingmall.order.entity.Delivery;
import cloud2.shopingmall.order.entity.Orders;
import cloud2.shopingmall.order.mapper.OrderMainMapper;
import cloud2.shopingmall.order.repository.DeliveryRepository;
import cloud2.shopingmall.order.repository.OrderRepository;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class DeliveryService {
    /**
     * 배송 시작 배송상태업데이트
     */
    private final DeliveryRepository deliveryRepository;
    private final OrderMainMapper.DeliveryMapper deliveryMapper;
    private final OrderRepository orderRepository;

    public List<Delivery> getAllDeliveries() {
        return deliveryRepository.findAll();
    }


    public DeliveryDTO createDelivery(DeliveryDTO deliveryDTO, Long orderId) {
        Delivery delivery = deliveryMapper.toEntity(deliveryDTO);
        delivery.setSenderStatus(Delivery.SenderStatus.PREPARING_FOR_DELIVERY);
        delivery.setOrder(orderRepository.findById(orderId)
                .orElseThrow(() -> new OrderException.OrderNotFoundException(orderId)));
        Delivery save = deliveryRepository.save(delivery);
        return deliveryMapper.toDto(save);
    }

    public void verifyModify(Long orderId) {
        Orders order = orderRepository.findById(orderId)
                .orElseThrow(() -> new OrderException.OrderNotFoundException(orderId));
        if (!(order.getStatus() == Orders.OrderStatus.PAYMENT_COMPLETED
                || order.getStatus() == Orders.OrderStatus.PREPARING_FOR_DELIVERY)) {
            throw new OrderException.OrderCancellationNotAllowedException(order.getId());
        }
    }

    public DeliveryDTO modifyDelivery(DeliveryDTO deliveryDTO) {
        //배송지 변경(기존 배송지 어떻게 찾을 것인가 무엇을 받을 것인가)
        return deliveryDTO;
    }

 /*public Delivery getDeliveryById(Long deliveryId) {
        return deliveryRepository.findById(deliveryId)
                .orElseThrow(() -> new ResourceNotFoundException("Delivery", "id", deliveryId));
    }*/
    /*public Delivery updateDeliveryStatus(Long deliveryId, Delivery.SenderStatus newStatus) {
        Delivery delivery = deliveryRepository.findById(deliveryId)
                .orElseThrow(() -> new ResourceNotFoundException("Delivery", "id", deliveryId));

        delivery.setSenderStatus(newStatus);
        return deliveryRepository.save(delivery);
    }*/

//    @Transactional
//    public OrderDTO modifyDelivery(DeliveryDTO deliveryDTO){
//        //상품이 결제 완료 혹은 배송 준비일때 만 배송지 및 수령인 변경가능
//
//    }
}



