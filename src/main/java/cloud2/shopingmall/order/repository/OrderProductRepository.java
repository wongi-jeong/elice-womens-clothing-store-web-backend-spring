package cloud2.shopingmall.order.repository;

import cloud2.shopingmall.order.entity.OrderProduct;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
@Transactional
public interface OrderProductRepository extends JpaRepository<OrderProduct, Long> {

}
