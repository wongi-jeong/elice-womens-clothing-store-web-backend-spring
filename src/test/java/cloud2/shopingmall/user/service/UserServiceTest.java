package cloud2.shopingmall.user.service;

import cloud2.shopingmall.common.exception.PasswordMismatchException;
import cloud2.shopingmall.user.dto.UserDTO;
import cloud2.shopingmall.user.dto.UserProfileDTO;
import cloud2.shopingmall.user.mapper.UserMainMapper.UserMapper;
import cloud2.shopingmall.user.mapper.UserMainMapper.UserProfileMapper;
import cloud2.shopingmall.user.repository.UserProfileRepository;
import cloud2.shopingmall.user.repository.UserRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import static org.mockito.ArgumentMatchers.any;
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
    public void testJoinProcess_UserAlreadyExists() throws PasswordMismatchException {

        // 데이터 생성 및 초기화
        UserDTO.Join userDTO = new UserDTO.Join("test_name", "test_password", "test_password");
        UserProfileDTO.Join userProfileDTO = new UserProfileDTO.Join();


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

}
