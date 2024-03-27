package cloud2.shopingmall.order.mapper;

import cloud2.shopingmall.common.mapper.EntityMapper;
import cloud2.shopingmall.order.dto.*;
import cloud2.shopingmall.order.entity.*;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring", unmappedSourcePolicy = ReportingPolicy.IGNORE)
public interface OrderMainMapper {

    interface DeliveryMapper extends EntityMapper<DeliveryEntity, DeliveryDTO> {
    }

    interface OrderProductMapper extends EntityMapper<OrderProductEntity, OrderProductDTO> {
    }

    interface OrderMapper extends EntityMapper<OrderEntity, OrderDTO> {
    }

    interface OrderUserMapper extends EntityMapper<OrderUserEntity, OrderUserDTO> {
    }

    interface PaymentMapper extends EntityMapper<PaymentEntity, PaymentDTO> {
    }
}
