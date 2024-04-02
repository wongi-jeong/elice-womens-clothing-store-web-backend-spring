package cloud2.shopingmall.order.service;

import cloud2.shopingmall.order.dto.DeliveryDTO;
import cloud2.shopingmall.order.dto.OrderDTO;
import org.springframework.stereotype.Service;
import cloud2.shopingmall.order.entity.Delivery;
import cloud2.shopingmall.order.repository.DeliveryRepository;
import org.springframework.transaction.annotation.Transactional;
//import cloud2.shopingmall.order.exception.ResourceNotFoundException;
import java.util.List;

@Service
public class DeliveryService {
    /**
     * 배송 시작
     * 배송상태업데이트
     */
    private DeliveryRepository deliveryRepository;

    public List<Delivery> getAllDeliveries() {
        return deliveryRepository.findAll();
    }

    /*public Delivery getDeliveryById(Long deliveryId) {
        return deliveryRepository.findById(deliveryId)
                .orElseThrow(() -> new ResourceNotFoundException("Delivery", "id", deliveryId));
    }*/

    public Delivery createDelivery(Delivery delivery) {
        return deliveryRepository.save(delivery);
    }

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



