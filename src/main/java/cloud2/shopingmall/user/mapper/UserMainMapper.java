package cloud2.shopingmall.user.mapper;

import cloud2.shopingmall.common.mapper.EntityMapper;
import cloud2.shopingmall.user.dto.UserDTO;
import cloud2.shopingmall.user.dto.UserProfileDTO;
import cloud2.shopingmall.user.entity.User;
import cloud2.shopingmall.user.entity.UserProfile;
<<<<<<< HEAD
=======
import org.mapstruct.BeanMapping;
>>>>>>> dev
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

@Mapper(componentModel = "spring", unmappedSourcePolicy = ReportingPolicy.IGNORE)
public interface UserMainMapper {

<<<<<<< HEAD
=======
    @Mapper(componentModel = "spring")
>>>>>>> dev
    interface UserMapper extends EntityMapper<User, UserDTO> {

    }

<<<<<<< HEAD
=======
    @Mapper(componentModel = "spring")
>>>>>>> dev
    interface UserProfileMapper extends EntityMapper<UserProfile, UserProfileDTO> {

    }

}
