package cloud2.shopingmall.order.repository;

import cloud2.shopingmall.order.entity.PaymentEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PaymentRepository extends JpaRepository<PaymentEntity,Long> {
}
