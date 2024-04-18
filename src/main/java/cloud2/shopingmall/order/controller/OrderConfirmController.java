package cloud2.shopingmall.order.controller;

import cloud2.shopingmall.order.dto.OrderInfoDTO;
import cloud2.shopingmall.order.service.*;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping("/api/admin/order")
@RequiredArgsConstructor
public class OrderConfirmController {
    private final OrderQueryService orderQueryService;
    @GetMapping("")
    public ResponseEntity<Page<OrderInfoDTO>> findAllOrder(@PageableDefault(size = 20) Pageable pageable){
        Page<OrderInfoDTO> allOrder = orderQueryService.findAllOrder(pageable);
        return ResponseEntity.ok(allOrder);
    }
}
