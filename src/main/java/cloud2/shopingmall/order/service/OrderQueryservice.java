package cloud2.shopingmall.order.service;

import cloud2.shopingmall.order.dto.OrderInfoDTO;
import cloud2.shopingmall.order.entity.OrderProduct;
import cloud2.shopingmall.order.entity.Orders;
import cloud2.shopingmall.order.repository.OrderProductRepository;
import cloud2.shopingmall.order.repository.OrderRepository;
import cloud2.shopingmall.order.repository.OrderUserRepository;
import cloud2.shopingmall.product.entity.Product;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class OrderQueryservice {
    /**
     * 전체 주문 조회
     * 사용자별 주문 조회
     * 주문 상세 조회
     */
    private final OrderRepository orderRepository;
    private final OrderUserRepository orderUserRepository;
    private  final OrderProductRepository orderProductRepository;


    public List<OrderInfoDTO> findAllOrder(){
        //관리자 입장에서 전체 주문 목록 조회
        List<Orders> allorders = orderRepository.findAll();
        List<OrderInfoDTO> orderInfoDTOList = new ArrayList<>();
        for(Orders order : allorders){
            List<OrderProduct> orderProducts = orderProductRepository.findByOrders(order);
            Map<Product, Integer> productsMap = new HashMap<>();
            for(OrderProduct orderProduct: orderProducts){
                Product product = orderProduct.getProduct();
                productsMap.put(product,orderProduct.getProductCount());
            }
            OrderInfoDTO orderInfoDTO = new OrderInfoDTO();
            orderInfoDTO.setOrderId(order.getId());
            orderInfoDTO.setOrderStatus(order.getOrderStatus());
            orderInfoDTO.setOrderNumber(order.getOrderUser().getOrderNumber());
            orderInfoDTO.setOrderCreatedAt(order.getOrderCreatedAt());
            orderInfoDTO.setOrderModifiedAt(order.getOrderModifiedAt());
            orderInfoDTO.setProducts(productsMap);

            orderInfoDTOList.add(orderInfoDTO);
        }
        return orderInfoDTOList;
    }

//    public List<OrderInfoDTO> findByUser(){
//        //주문자로 주문 목록 점색
//
//    }
}
