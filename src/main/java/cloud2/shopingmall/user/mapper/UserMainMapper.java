package cloud2.shopingmall.user.mapper;

import cloud2.shopingmall.common.mapper.EntityMapper;
import cloud2.shopingmall.user.dto.UserDTO;
import cloud2.shopingmall.user.dto.UserProfileDTO;
import cloud2.shopingmall.user.entity.UserEntity;
import cloud2.shopingmall.user.entity.UserProfileEntity;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring", unmappedSourcePolicy = ReportingPolicy.IGNORE)
public interface UserMainMapper {

    interface UserMapper extends EntityMapper<UserEntity, UserDTO> {

    }

    interface UserProfileMapper extends EntityMapper<UserProfileEntity, UserProfileDTO> {

    }

}
