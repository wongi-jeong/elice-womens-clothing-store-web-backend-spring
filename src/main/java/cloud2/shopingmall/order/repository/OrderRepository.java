package cloud2.shopingmall.order.repository;

import cloud2.shopingmall.order.entity.OrderEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<OrderEntity,Long> {
}
