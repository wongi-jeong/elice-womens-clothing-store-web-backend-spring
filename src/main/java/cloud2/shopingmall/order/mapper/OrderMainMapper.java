package cloud2.shopingmall.order.mapper;

import cloud2.shopingmall.common.mapper.EntityMapper;
import cloud2.shopingmall.order.dto.*;
import cloud2.shopingmall.order.entity.*;
import org.mapstruct.Mapper;
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
    @Mapper(componentModel = "spring", unmappedSourcePolicy = ReportingPolicy.IGNORE)
    interface RefundMapper extends EntityMapper<Refund, RefundDTO> {
    }

}
