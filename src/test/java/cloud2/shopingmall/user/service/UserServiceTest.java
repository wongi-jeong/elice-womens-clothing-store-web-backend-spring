package cloud2.shopingmall.user.service;

import cloud2.shopingmall.user.dto.UserDTO;
import cloud2.shopingmall.user.dto.UserProfileDTO;
import cloud2.shopingmall.user.entity.User;
import cloud2.shopingmall.user.entity.UserProfile;
import cloud2.shopingmall.user.mapper.UserMainMapper.UserMapper;
import cloud2.shopingmall.user.mapper.UserMainMapper.UserProfileMapper;
import cloud2.shopingmall.user.repository.UserProfileRepository;
import cloud2.shopingmall.user.repository.UserRepository;
import cloud2.shopingmall.user.service.UserService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class UserServiceTest {

    @InjectMocks
    private UserService userService;

    @Mock
    private UserRepository userRepository;

    @Mock
    private UserProfileRepository userProfileRepository;

    @Mock
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Mock
    private UserMapper userMapper;

    @Mock
    private UserProfileMapper userProfileMapper;

    @Test
    @DisplayName("사용자 계정이 DB에 존재하는 경우")
    public void testJoinProcess_UserAlreadyExists() {

        // 데이터 생성 및 초기화
        UserDTO userDTO = new UserDTO("test_name","test_password");
        UserProfileDTO.Create userProfileDTO = new UserProfileDTO.Create();


        // 이미 존재하는 사용자라고 가정
        when(userRepository.existsByUsername(any(String.class))).thenReturn(true);


        // 테스트 실행
        userService.joinProcess(userDTO, userProfileDTO);

        // 이미 존재하는 사용자일 경우 다음 로직(save)를 실행하지 않아야 한다 !!

        // userRepository.save()가 호출되지 않았는지 확인 -> never()메서드를 사용하여 호출이 안되는지 확인 호출이 안되면 테스트 통과!!
        verify(userRepository, never()).save(any());
        // userProfileRepository.save()가 호출되지 않았는지 확인
        verify(userProfileRepository, never()).save(any());
    }

//    @Test
//    @DisplayName("사용자 계정이 DB에 존재하지 않는 경우")
//    public void testJoinProcess_UserDoesNotExist() {
//        // 가짜 데이터 생성
//        UserDTO userDTO = new UserDTO("test_name","test_password");
//        User user = new User();
//        UserProfileDTO.Create userProfileDTO = new UserProfileDTO.Create("test_name","abcd@naver.com","010-1234-5678","test_address", UserProfileDTO.Create.Gender.MALE,"1998-12-12");
//        UserProfile userProfile = new UserProfile();
//
//
//
//        // 사용자가 존재하지 않는다고 가정
//        when(userRepository.existsByUsername(any(String.class))).thenReturn(false);
//        // userMapper.toEntity() 메서드의 리턴값 설정 -> user 객체를 반환
//        when(userMapper.toEntity(userDTO)).thenReturn(user);
//        // userProfileMapper.toEntity() 메서드의 리턴값 설정
//        when(userProfileMapper.toEntity(userProfileDTO)).thenReturn(userProfile);
//        // bCryptPasswordEncoder.encode() 메서드의 리턴값 설정
//        when(bCryptPasswordEncoder.encode(anyString())).thenReturn("encodedPassword");
//        // userRepository.save() 메서드의 리턴값 설정
//        when(userRepository.save(user)).thenReturn(user);
//
//        // 테스트 실행
//        userService.joinProcess(userDTO, userProfileDTO);
//
//        // userRepository.save()가 호출되었는지 확인
//        verify(userRepository, times(1)).save(user);
//        // userProfileRepository.save()가 호출되었는지 확인
//        verify(userProfileRepository, times(1)).save(userProfile);
//
//        // user.setPassword()가 올바르게 호출되었는지 확인
//        verify(user).setPassword("encodedPassword");
//        // user.setStatus()가 올바르게 호출되었는지 확인
//        verify(user).setStatus(User.Status.ACTIVE);
//        // user.setUserRole()가 올바르게 호출되었는지 확인
//        verify(user).setUserRole("ROLE_ADMIN");
//        // userProfile.setUser()가 올바르게 호출되었는지 확인
//        verify(userProfile).setUser(user);
//        // userProfile.setGender()가 올바르게 호출되었는지 확인
//        verify(userProfile).setGender(anyString());
//    }
}
