package cloud2.shopingmall.order.service;

import cloud2.shopingmall.order.dto.OrderInfoDTO;
import cloud2.shopingmall.order.dto.OrderProductDTO;
import cloud2.shopingmall.order.entity.OrderProduct;
import cloud2.shopingmall.order.entity.Orders;
import cloud2.shopingmall.order.repository.OrderProductRepository;
import cloud2.shopingmall.order.repository.OrderRepository;
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


    public List<OrderInfoDTO> findAllOrder(){
        //관리자 입장에서 전체 주문 목록 조회(주문번호, 주문 상태, 주문 날짜, 변경 날짜, 총 금액, 주문자 아이디)
        List<Orders> allorders = orderRepository.findAllWithOrderUserAndPayment();
        List<OrderInfoDTO> orderInfoDTOList = new ArrayList<>();
        for(Orders order : allorders){
            OrderInfoDTO orderInfoDTO = getOrderInfoDTO(order);
            orderInfoDTOList.add(orderInfoDTO);
        }
        return orderInfoDTOList;
    }
    public List<OrderInfoDTO.OrderDetailInfo> findByUser(String userName){
        //주문자로 주문 목록 검색(주문번호, 주문 상태, 주문 날짜, 변경 날짜, 총 금액, 상품, 수량 , 상품 금액)
        List<Orders> orderDetailsByUsername = orderRepository.findOrdersByUsername(userName);
        List<OrderInfoDTO.OrderDetailInfo> orderDetailDTOList = new ArrayList<>();
        for(Orders order: orderDetailsByUsername){
            OrderInfoDTO.OrderDetailInfo orderDetailInfo = getDetailInfo(order);
            orderDetailDTOList.add(orderDetailInfo);
        }
        return orderDetailDTOList;
    }
    public OrderInfoDTO.OrderDetailInfo findByOrderId(Long orderId){
        //주문번호로 검색(주문번호, 주문 상태, 주문 날짜, 변경 날짜, 총 금액, 상품, 수량 , 상품 금액)
        Orders order = orderRepository.findOrdersByOrderId(orderId);
        OrderInfoDTO.OrderDetailInfo orderDetailInfo = getDetailInfo(order);
        return orderDetailInfo;
    }

    private static OrderInfoDTO getOrderInfoDTO(Orders order) {
        OrderInfoDTO orderInfoDTO = new OrderInfoDTO();
        orderInfoDTO.setOrderStatus(order.getOrderStatus());
        orderInfoDTO.setOrderNumber(order.getId());
        orderInfoDTO.setOrderCreatedAt(order.getOrderCreatedAt());
        orderInfoDTO.setOrderModifiedAt(order.getOrderModifiedAt());
        orderInfoDTO.setOrderUser(order.getUser().getUsername());
        orderInfoDTO.setTotalPrice(order.getPayment().getPayTotalPrice());
        return orderInfoDTO;
    }
    private static OrderInfoDTO.OrderDetailInfo getDetailInfo(Orders order) {
        OrderInfoDTO.OrderDetailInfo orderDetailInfo = new OrderInfoDTO.OrderDetailInfo();
        orderDetailInfo.setOrderNumber(order.getId());
        orderDetailInfo.setOrderUser(order.getUser().getUsername());
        orderDetailInfo.setOrderCreatedAt(order.getOrderCreatedAt());
        orderDetailInfo.setOrderStatus(order.getOrderStatus());
        orderDetailInfo.setOrderModifiedAt(order.getOrderModifiedAt());
        orderDetailInfo.setTotalPrice(order.getPayment().getPayTotalPrice());
        Map<Long, Integer> products = new HashMap<>();
        List<OrderProduct> ops = order.getOrderProducts();
        for(OrderProduct op: ops){
            products.put(op.getId(),op.getProductCount());
        }
        orderDetailInfo.setProducts(products);
        return orderDetailInfo;
    }

}
