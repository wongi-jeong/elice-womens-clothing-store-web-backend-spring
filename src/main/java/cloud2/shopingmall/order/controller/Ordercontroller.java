package cloud2.shopingmall.order.controller;

import cloud2.shopingmall.order.dto.OrderProductDTO;
import cloud2.shopingmall.order.dto.OrderUserDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/order")
@RestController
public class Ordercontroller {

    @PostMapping()
    public ResponseEntity<String> CreateOrderFromCart(@RequestBody List<OrderProductDTO> productDTO, OrderUserDTO orderUserDTO){

    }
}
