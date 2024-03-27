package cloud2.shopingmall.order.repository;

import cloud2.shopingmall.order.entity.OrderProductEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderProductRepository extends JpaRepository<OrderProductEntity,Long> {
}
