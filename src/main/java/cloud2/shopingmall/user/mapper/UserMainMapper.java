package cloud2.shopingmall.user.mapper;

import cloud2.shopingmall.common.mapper.EntityMapper;
import cloud2.shopingmall.user.dto.UserDTO;
import cloud2.shopingmall.user.dto.UserProfileDTO;
import cloud2.shopingmall.user.entity.User;
import cloud2.shopingmall.user.entity.UserProfile;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring", unmappedSourcePolicy = ReportingPolicy.IGNORE)
public interface UserMainMapper {

    @Mapper(componentModel = "spring")
    interface UserMapper extends EntityMapper<User, UserDTO.Join> {

    }

    @Mapper(componentModel = "spring")
    interface UserProfileMapper extends EntityMapper<UserProfile, UserProfileDTO.Join> {

    }


}
