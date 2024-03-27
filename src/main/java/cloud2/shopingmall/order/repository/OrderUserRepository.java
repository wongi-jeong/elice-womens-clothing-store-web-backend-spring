package cloud2.shopingmall.order.repository;

import cloud2.shopingmall.order.entity.OrderUserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderUserRepository extends JpaRepository<OrderUserEntity,Long> {
}
