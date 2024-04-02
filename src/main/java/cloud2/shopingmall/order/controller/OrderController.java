package cloud2.shopingmall.order.controller;

import cloud2.shopingmall.common.exception.OrderException;
import cloud2.shopingmall.jwt.CustomUserDetails;
import cloud2.shopingmall.jwt.JWTUtil;
import cloud2.shopingmall.order.dto.*;
import cloud2.shopingmall.order.service.*;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/order")
@RequiredArgsConstructor
public class OrderController {
    private final OrderQueryService orderQueryService;
    private final OrderManagementService orderManagementService;
    private final PaymentService paymentService;
    private final DeliveryService deliveryService;

    @GetMapping("")
    public ResponseEntity<Page<OrderInfoDTO>> findAllOrder(@PageableDefault(size = 20) Pageable pageable){
        Page<OrderInfoDTO> allOrder = orderQueryService.findAllOrder(pageable);
        return ResponseEntity.ok(allOrder);
    }
    @GetMapping("/user")
    public ResponseEntity<List<OrderInfoDTO.OrderDetailInfo>> findOrderByUser(@AuthenticationPrincipal CustomUserDetails customUserDetails){
        List<OrderInfoDTO.OrderDetailInfo> orderDetailList = orderQueryService.findByUser(customUserDetails.getUsername());
        return ResponseEntity.ok(orderDetailList);
    }
    @GetMapping("/{id}")
    public ResponseEntity<OrderInfoDTO.OrderDetailInfo> findOrderById(@PathVariable Long id){
        OrderInfoDTO.OrderDetailInfo orderDetail = orderQueryService.findByOrderId(id);
        return ResponseEntity.ok(orderDetail);
    }

    @PostMapping("/cancel")
    public ResponseEntity<OrderDTO> cancelOrder(@RequestBody OrderDTO orderDTO){
        try {
            OrderDTO orderDTO1 = orderManagementService.canceledOrder(orderDTO);
        } catch (OrderException.OrderNotFoundException e) {
            ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }catch (OrderException.OrderCancellationNotAllowedException e){
            ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY).body(e.getMessage());
        }
        return ResponseEntity.ok(orderDTO);
    }
    @PostMapping("/creat/cart")
    public ResponseEntity<OrderDTO> createOrderByCart(@AuthenticationPrincipal CustomUserDetails customUserDetails,
                                                @RequestBody @Validated OrderCreateRequest.OrderByCart orderCreateRequest){
        //장바구니 주문
//json으로 여러 객체 받아와야할때 복합 객체를 사용하게 되면 언제 검증을 하게 되는지 그리고 검증에 관한 로직은 어디서 구현하면 좋을 지
        OrderDTO orderDTO = orderManagementService.createOrderForCart(customUserDetails.getUsername());
        orderManagementService.createOrderProduct(orderCreateRequest.getOrderProductDTOS(),orderDTO.getId());
        deliveryService.createDelivery(orderCreateRequest.getDeliveryDTO(),orderDTO.getId());
        paymentService.createPayment(customUserDetails.getUsername(), orderCreateRequest.getTotalPrice(),orderDTO.getId());
        return ResponseEntity.ok(orderDTO);

    }
    @PostMapping("/create/product")
    public ResponseEntity<OrderDTO> createOrderByProduct(@AuthenticationPrincipal CustomUserDetails customUserDetails,
                                                      @RequestBody @Validated OrderCreateRequest.OrderByProduct orderByProduct){
        //상품 주문
        OrderDTO orderDTO = orderManagementService.createOrderForProduct(customUserDetails.getUsername());
        orderManagementService.createOrderProduct(orderByProduct.getOrderProductDTOS(),orderDTO.getId());
        deliveryService.createDelivery(orderByProduct.getDeliveryDTO(),orderDTO.getId());
        paymentService.createPayment(customUserDetails.getUsername(), orderByProduct.getTotalPrice(),orderDTO.getId());
        return ResponseEntity.ok(orderDTO);

    }
//    @PostMapping("/{id}/modify")
//    public ResponseEntity<OrderInfoDTO> modifyDelivery(@PathVariable("id") Long id){
//      //상품 주문지 변경
//    }

}
