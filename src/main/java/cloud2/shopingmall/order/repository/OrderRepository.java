package cloud2.shopingmall.order.repository;

import cloud2.shopingmall.order.entity.Orders;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
@Transactional
public interface OrderRepository extends JpaRepository<Orders,Long> {
    @Query("SELECT o FROM Orders o JOIN FETCH o.user JOIN FETCH o.payment")
    List<Orders> findAllWithOrderUserAndPayment();

    @Query("SELECT o FROM Orders o JOIN FETCH o.user JOIN FETCH o.payment JOIN FETCH o.orderProducts WHERE o.user.username = :username")
    List<Orders> findOrdersByUsername(@Param("username") String username);

    @Query("SELECT o FROM Orders o JOIN FETCH o.user JOIN FETCH o.payment JOIN FETCH o.orderProducts WHERE o.id = :orderId")
    Orders findOrdersByOrderId(@Param("orderId") Long orderId);
}
