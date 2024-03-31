package cloud2.shopingmall.order.service;

import org.springframework.stereotype.Service;
import cloud2.shopingmall.order.entity.Delivery;
import cloud2.shopingmall.order.repository.DeliveryRepository;
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

}



