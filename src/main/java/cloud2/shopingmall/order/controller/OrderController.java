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
        } catch (OrderException.OrderNotFoundExecption e) {
            ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }catch (OrderException.OrderCancellationNotAllowedException e){
            ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY).body(e.getMessage());
        }
        return ResponseEntity.ok(orderDTO);
    }
    @PostMapping("/create")
    public ResponseEntity<OrderDTO> createOrderByCart(@AuthenticationPrincipal CustomUserDetails customUserDetails,
                                                @RequestBody @Validated OrderCreateRequest.OrderByCart orderCreateRequest){

        OrderDTO orderDTO = orderManagementService.createOrderForCart(customUserDetails.getUsername(), orderCreateRequest.getOrderProductDTOS(), orderCreateRequest.getDeliveryDTO(), orderCreateRequest.getPaymentDTO());
        return ResponseEntity.ok(orderDTO);

    }
    @PostMapping("/create")
    public ResponseEntity<OrderDTO> createOrderByProduct(@AuthenticationPrincipal CustomUserDetails customUserDetails,
                                                      @RequestBody @Validated OrderCreateRequest.OrderByProduct orderByProduct){
        OrderDTO orderDTO = orderManagementService.createOrderForProduct(customUserDetails.getUsername(), orderByProduct.getOrderProductDTOS(), orderByProduct.getDeliveryDTO(), orderByProduct.getPaymentDTO());
        return ResponseEntity.ok(orderDTO);

    }
//    @PostMapping("/{id}/modify")
//    public ResponseEntity<OrderInfoDTO> modifyDelivery(@PathVariable("id") Long id){
//      //상품 주문지 변경
//    }

}
