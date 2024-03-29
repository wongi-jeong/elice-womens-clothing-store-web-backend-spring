package cloud2.shopingmall.order.service;

import cloud2.shopingmall.order.dto.OrderInfoDTO;
import cloud2.shopingmall.order.entity.Orders;
import cloud2.shopingmall.order.repository.OrderProductRepository;
import cloud2.shopingmall.order.repository.OrderRepository;
import jakarta.persistence.criteria.CriteriaBuilder;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
@RequiredArgsConstructor
public class OrderQueryservice {
    /**
     * 전체 주문 조회
     * 사용자별 주문 조회
     * 주문 상세 조회
     */
    private final OrderRepository orderRepository;
    private  final OrderProductRepository orderProductRepository;


    public List<OrderInfoDTO> findAllOrder(){
        //관리자 입장에서 전체 주문 목록 조회(주문번호, 주문 상태, 주문 날짜, 변경 날짜, 총 금액, 주문자 아이디)
        List<Orders> allorders = orderRepository.findAllWithOrderUserAndPayment();
        List<OrderInfoDTO> orderInfoDTOList = new ArrayList<>();
        for(Orders order : allorders){
            OrderInfoDTO orderInfoDTO = new OrderInfoDTO();
            orderInfoDTO.setOrderStatus(order.getOrderStatus());
            orderInfoDTO.setOrderNumber(order.getId());
            orderInfoDTO.setOrderCreatedAt(order.getOrderCreatedAt());
            orderInfoDTO.setOrderModifiedAt(order.getOrderModifiedAt());
            orderInfoDTO.setOrderUser(order.getUser().getUsername());
            orderInfoDTO.setTotalPrice(order.getPayment().getPayTotalPrice());
        }
        return orderInfoDTOList;
    }

//    public List<OrderInfoDTO.OrderDetailInfo> findByUser(String userName){
//        //주문자로 주문 목록 점색(주문번호, 주문 상태, 주문 날짜, 변경 날짜, 총 금액, 상품, 수량 , 상품 금액)
//        List<OrderInfoDTO.OrderDetailInfo> orderDetailsByUsername = orderRepository.findOrderDetailsByUsername(userName);
//        for(OrderInfoDTO.OrderDetailInfo orderDetailInfo: orderDetailsByUsername){
//            Map<Long, Integer> products = new HashMap<>();
//            List<OrderInfoDTO.productQuantityDTO> productQuantityDTOS = orderProductRepository.prouctQUantityByOrders(orderDetailInfo.getOrderNumber());
//            for(OrderInfoDTO.productQuantityDTO pqDTO: productQuantityDTOS){
//                products.put(pqDTO.getProductId(),pqDTO.getAmount());
//            }
//            orderDetailInfo.setProducts(products);
//        }
//        return orderDetailsByUsername;
//    }
//    public OrderInfoDTO.OrderDetailInfo findByOrderId(Long orderId){
//        //주문번호로 검색(주문번호, 주문 상태, 주문 날짜, 변경 날짜, 총 금액, 상품, 수량 , 상품 금액)
//        OrderInfoDTO.OrderDetailInfo orderDetailsByOrderId = orderRepository.findOrderDetailsByOrderId(orderId);
//        List<OrderInfoDTO.productQuantityDTO> productQuantityDTOS = orderProductRepository.prouctQUantityByOrders(orderId);
//        Map<Long, Integer> products = new HashMap<>();
//        for(OrderInfoDTO.productQuantityDTO pqDTO: productQuantityDTOS){
//            products.put(pqDTO.getProductId(),pqDTO.getAmount());
//        }
//        orderDetailsByOrderId.setProducts(products);
//        return orderDetailsByOrderId;
//    }
}
