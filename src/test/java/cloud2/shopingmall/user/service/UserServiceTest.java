package cloud2.shopingmall.user.service;

import cloud2.shopingmall.common.exception.PasswordMismatchException;
import cloud2.shopingmall.user.dto.UserDTO;
import cloud2.shopingmall.user.dto.UserProfileDTO;
import cloud2.shopingmall.user.dto.UserProfileDTO.Join.Gender;

import cloud2.shopingmall.user.entity.User;
import cloud2.shopingmall.user.entity.UserProfile;
import cloud2.shopingmall.user.mapper.UserMainMapper.UserMapper;
import cloud2.shopingmall.user.mapper.UserMainMapper.UserProfileMapper;
import cloud2.shopingmall.user.repository.UserRepository;
import cloud2.shopingmall.user.repository.UserProfileRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class UserServiceTest {

    @Mock
    private UserRepository userRepository;
    @Mock
    private UserProfileRepository userProfileRepository;
    @Mock
    private UserMapper userMapper;
    @Mock
    private UserProfileMapper userProfileMapper;
    @Mock
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @InjectMocks
    private UserService userService;

    @Test
    @DisplayName("회원가입 테스트")
    void testJoinProcess() throws PasswordMismatchException {
        // 유저 DTO 및 유저 프로필 DTO 생성
        UserDTO.Join userDTO = new UserDTO.Join("testuser", "Test123!", "Test123!");
        UserProfileDTO.Join userProfileDTO = new UserProfileDTO.Join("John Doe", "john@example.com", "010-1234-5678",
                "123 Test St", Gender.MALE, "1990-01-01");

        // userMapper.toEntity() 메서드가 호출될 때 적절한 User 객체 반환하도록 설정
        when(userMapper.toEntity(userDTO)).thenReturn(new User());
        // userProfileMapper.toEntity() 메서드가 호출될 때 적절한 UserProfile 객체 반환하도록 설정
        when(userProfileMapper.toEntity(userProfileDTO)).thenReturn(new UserProfile());

        // 테스트 대상 메서드 호출
        boolean result = userService.joinProcess(userDTO, userProfileDTO);

        // userRepository.save() 및 userProfileRepository.save() 메서드가 호출되었는지 확인
        verify(userRepository, times(1)).save(any());
        verify(userProfileRepository, times(1)).save(any());

        // 결과 검증
        assertTrue(result);
    }
}
