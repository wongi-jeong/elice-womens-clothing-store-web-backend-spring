package cloud2.shopingmall.user.mapper;

import cloud2.shopingmall.common.mapper.EntityMapper;
import cloud2.shopingmall.user.dto.UserDTO;
import cloud2.shopingmall.user.dto.UserProfileDTO;
import cloud2.shopingmall.user.entity.User;
import cloud2.shopingmall.user.entity.UserProfile;
<<<<<<< HEAD
=======
import org.mapstruct.BeanMapping;
>>>>>>> 9619cc3794305c6215aa009a2cf377e9d4a36312
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

@Mapper(componentModel = "spring", unmappedSourcePolicy = ReportingPolicy.IGNORE)
public interface UserMainMapper {

<<<<<<< HEAD
=======
    @Mapper(componentModel = "spring")
>>>>>>> 9619cc3794305c6215aa009a2cf377e9d4a36312
    interface UserMapper extends EntityMapper<User, UserDTO> {

    }

<<<<<<< HEAD
    interface UserProfileMapper extends EntityMapper<UserProfile, UserProfileDTO> {
=======
    @Mapper(componentModel = "spring")
    interface UserProfileMapper extends EntityMapper<UserProfile, UserProfileDTO.Create> {
>>>>>>> 9619cc3794305c6215aa009a2cf377e9d4a36312

    }

}
