package cloud2.shopingmall.order.repository;

import cloud2.shopingmall.order.entity.Cart;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Transactional
@Repository
public interface CartRepository extends JpaRepository<Cart,Long> {
    Cart findCartByUserId(Long userId);
}
