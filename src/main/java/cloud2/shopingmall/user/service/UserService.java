package cloud2.shopingmall.user.service;

import cloud2.shopingmall.user.dto.UserDTO;
import cloud2.shopingmall.user.entity.User;
import cloud2.shopingmall.user.mapper.UserMainMapper.UserMapper;
import cloud2.shopingmall.user.repository.UserRepository;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    private final UserRepository userRepository;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;
    private final UserMapper userMapper;

    public UserService(UserRepository userRepository, BCryptPasswordEncoder bCryptPasswordEncoder, UserMapper userMapper) {
        this.userRepository = userRepository;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
        this.userMapper = userMapper;
    }

    public Boolean UserIsExist(String username) {
        // repository에 유저 정보가 존재하는지 체크 존재하는 경우 true 없으면 false
        return userRepository.existsByUsername(username);
    }


    public User create(User user) {

        String password = user.getPassword();

        user.setPassword(bCryptPasswordEncoder.encode(password)); // 비밀번호를 암호화하여 저장
        user.setUserRole(User.UserRole.ADMIN); // Role 부여
        return userRepository.save(user); // DB에 저장
    }

}
