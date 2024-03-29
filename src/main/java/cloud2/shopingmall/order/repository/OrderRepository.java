package cloud2.shopingmall.order.repository;

import cloud2.shopingmall.order.dto.OrderInfoDTO;
import cloud2.shopingmall.order.entity.Orders;
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
//    @Query("SELECT new cloud2.shopingmall.order.dto.OrderInfoDTO.OrderDetailInfo(o.id, u.username ,o.orderCreatedAt, o.orderModifiedAt, o.orderStatus, p.payTotalPrice) "+
//            "FROM Orders o "+
//            "JOIN o.user u " +
//            "JOIN o.payment p " +
//            "WHERE u.username = :username")
//    List<OrderInfoDTO.OrderDetailInfo> findOrderDetailsByUsername(@Param("username") String username);

//    @Query("SELECT new cloud2.shopingmall.order.dto.OrderInfoDTO.OrderDetailInfo(o.id, u.username ,o.orderCreatedAt, o.orderModifiedAt, o.orderStatus, p.payTotalPrice) "+
//            "FROM Orders o "+
//            "JOIN o.user u " +
//            "JOIN o.payment p " +
//            "WHERE o.id = :orderId")
//    OrderInfoDTO.OrderDetailInfo findOrderDetailsByOrderId(@Param("orderId") Long orderId);
}
