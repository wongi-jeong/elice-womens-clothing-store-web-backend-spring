package cloud2.shopingmall.order.repository;


import cloud2.shopingmall.order.entity.CartItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Transactional
@Repository
public interface CartItemRepository extends JpaRepository<CartItem,Long> {

}
