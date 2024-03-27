package cloud2.shopingmall.order.mapper;

import cloud2.shopingmall.common.mapper.EntityMapper;
import cloud2.shopingmall.order.dto.*;
import cloud2.shopingmall.order.entity.*;

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
