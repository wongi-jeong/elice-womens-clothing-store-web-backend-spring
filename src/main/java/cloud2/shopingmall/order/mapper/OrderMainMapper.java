package cloud2.shopingmall.order.mapper;

import cloud2.shopingmall.common.mapper.EntityMapper;
import cloud2.shopingmall.order.dto.DeliveryDTO;
import cloud2.shopingmall.order.dto.OrderDTO;
import cloud2.shopingmall.order.dto.OrderInfoDTO;
import cloud2.shopingmall.order.dto.OrderProductDTO;
import cloud2.shopingmall.order.dto.PaymentDTO;
import cloud2.shopingmall.order.entity.Delivery;
import cloud2.shopingmall.order.entity.OrderProduct;
import cloud2.shopingmall.order.entity.Orders;
import cloud2.shopingmall.order.entity.Payment;
import java.util.HashMap;
import java.util.Map;
import org.mapstruct.AfterMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring", unmappedSourcePolicy = ReportingPolicy.IGNORE)
public interface OrderMainMapper {

    @Mapper(componentModel = "spring", unmappedSourcePolicy = ReportingPolicy.IGNORE)
    interface DeliveryMapper extends EntityMapper<Delivery, DeliveryDTO> {
    }

    @Mapper(componentModel = "spring", unmappedSourcePolicy = ReportingPolicy.IGNORE)
    interface OrderProductMapper extends EntityMapper<OrderProduct, OrderProductDTO> {
    }

    @Mapper(componentModel = "spring", unmappedSourcePolicy = ReportingPolicy.IGNORE)
    interface OrderMapper extends EntityMapper<Orders, OrderDTO> {
    }

    @Mapper(componentModel = "spring", unmappedSourcePolicy = ReportingPolicy.IGNORE)
    interface PaymentMapper extends EntityMapper<Payment, PaymentDTO> {
    }

    //    @Mapper(componentModel = "spring", unmappedSourcePolicy = ReportingPolicy.IGNORE)
//    interface RefundMapper extends EntityMapper<Refund, RefundDTO> {
//    }
    @Mapper(componentModel = "spring", unmappedSourcePolicy = ReportingPolicy.IGNORE)
    interface OrderInfoMapper extends EntityMapper<Orders, OrderInfoDTO> {
        @Mapping(source = "user.username", target = "userName")
        @Mapping(source = "payment.payTotalPrice", target = "totalPrice")
        OrderInfoDTO toDto(Orders order);
    }

    @Mapper(componentModel = "spring")
    interface OrderDetailMapper extends EntityMapper<Orders, OrderInfoDTO.OrderDetailInfo> {
        @Mapping(source = "user.username", target = "userName")
        @Mapping(source = "payment.payTotalPrice", target = "totalPrice")
        @Mapping(target = "products", ignore = true)
        OrderInfoDTO.OrderDetailInfo toDto(Orders order);

        @AfterMapping
        default void customMapping(@MappingTarget OrderInfoDTO.OrderDetailInfo target, Orders source) {
            // 상품 맵핑 로직
            Map<Long, Integer> productsMap = new HashMap<>();
            for (OrderProduct op : source.getOrderProducts()) {
                productsMap.put(op.getId(), op.getProductCount());
            }
            target.setProducts(productsMap);
        }
    }

}
