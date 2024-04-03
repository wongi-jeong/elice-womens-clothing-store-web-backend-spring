package cloud2.shopingmall.user.service;


import cloud2.shopingmall.common.exception.PasswordMismatchException;
import cloud2.shopingmall.jwt.CustomUserDetails;
import cloud2.shopingmall.user.dto.CommonDTO;
import cloud2.shopingmall.user.dto.UserDTO;
import cloud2.shopingmall.user.dto.UserProfileDTO;
import cloud2.shopingmall.user.entity.User;
import cloud2.shopingmall.user.entity.UserProfile;
import cloud2.shopingmall.user.mapper.UserMainMapper;
import cloud2.shopingmall.user.mapper.UserMainMapper.UserJoinMapper;
import cloud2.shopingmall.user.mapper.UserMainMapper.UserProfileChangeMapper;
import cloud2.shopingmall.user.mapper.UserMainMapper.UserProfileJoinMapper;
import cloud2.shopingmall.user.mapper.UserMainMapper.UserProfileShowMapper;
import cloud2.shopingmall.user.mapper.UserMainMapper.UserShowMapper;
import cloud2.shopingmall.user.repository.UserProfileRepository;
import cloud2.shopingmall.user.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class UserService {

    private final UserShowMapper userShowMapper;
    private final UserProfileShowMapper userProfileShowMapper;
    private final UserMainMapper.UserJoinMapper userJoinMapper;
    private final UserProfileJoinMapper userProfileJoinMapper;
    private final UserProfileChangeMapper userProfileChangeMapper;
    private final UserRepository userRepository;
    private final UserProfileRepository userProfileRepository;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;


    @Autowired
    public UserService(UserJoinMapper userJoinMapper, UserProfileJoinMapper userProfileJoinMapper,
                       UserRepository userRepository, UserProfileRepository userProfileRepository,
                       BCryptPasswordEncoder bCryptPasswordEncoder, UserShowMapper userShowMapper,
                       UserProfileShowMapper userProfileShowMapper, UserProfileChangeMapper userProfileChangeMapper) {

        this.userProfileJoinMapper = userProfileJoinMapper;
        this.userJoinMapper = userJoinMapper;
        this.userProfileShowMapper = userProfileShowMapper;
        this.userProfileChangeMapper = userProfileChangeMapper;
        this.userShowMapper = userShowMapper;
        this.userRepository = userRepository;
        this.userProfileRepository = userProfileRepository;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;

    }


    // 회원 가입 기능
    @Transactional
    public boolean joinProcess(UserDTO.Join userDTO, UserProfileDTO.Join userProfileDTO)
            throws PasswordMismatchException {

        String username = userDTO.getUsername();
        String password = userDTO.getPassword();
        String secondPassword = userDTO.getSecondPassword();
        String email = userProfileDTO.getEmail();
        String phoneNumber = userProfileDTO.getPhoneNumber();

        // 비밀번호를 제대로 두 번 입력했는지 확인
        if (!password.equals(secondPassword)) {
            throw new PasswordMismatchException("입력한 비밀번호가 일치하지 않습니다.");
        }

        // repository에 유저 정보가 존재하는지 체크 존재하는 경우 true 없으면 false
        if (userRepository.existsByUsername(username)) {
            // 현재 존재하는 경우 예외 처리
            throw new IllegalArgumentException("이미 존재하는 아이디입니다.");
        }

        // repository에 유저 이메일이 존재하는지 체크 존재하는 경우 true 없으면 false
        if (userProfileRepository.existsByEmail(email)) {
            // 현재 존재하는 경우 예외 처리
            throw new IllegalArgumentException("이미 존재하는 이메일입니다.");
        }

        // repository에 유저 핸드폰 번호가 존재하는지 체크 존재하는 경우 true 없으면 false
        if (userProfileRepository.existsByPhoneNumber(phoneNumber)) {
            // 현재 존재하는 경우 예외 처리
            throw new IllegalArgumentException("이미 존재하는 전화번호입니다.");
        }

        // 모두 통과한 경우 다음 로직 실행
        // DTO -> Entity 변환
        User user = userJoinMapper.toEntity(userDTO);
        UserProfile userProfile = userProfileJoinMapper.toEntity(userProfileDTO);

        // User DB에 생성
        user.setStatus(User.Status.ACTIVE); // 처음 가입시 계정상태 활성화 상태
        user.setPassword(bCryptPasswordEncoder.encode(password)); // 비밀번호를 암호화하여 저장
        user.setUserRole("ROLE_ADMIN"); // Role 부여
        User savedUser = userRepository.save(user); // DB에 저장

        // UserProfile DB에 생성
        userProfile.setUser(savedUser);
        userProfile.setGender(userProfileDTO.getGender().getKey());
        userProfile.setPoint(100000); // 처음 가입 시 10만 포인트 증정
        userProfileRepository.save(userProfile);

        return true;
    }

    // 아이디 찾기 기능
    public String findUserId(UserProfileDTO.FindUser findIdUserDTO) {

        String email = findIdUserDTO.getEmail();
        String phoneNumber = findIdUserDTO.getPhoneNumber();
        String source = findIdUserDTO.getSource().getKey();

        UserProfile userProfile = new UserProfile();

        if (source.equals("phone")) {
            if (phoneNumber == null) {
                throw new IllegalArgumentException("전화번호를 입력해 주세요.");
            }
            if (!userProfileRepository.existsByPhoneNumber(phoneNumber)) {
                throw new IllegalArgumentException("존재하는 전화번호가 없습니다.");
            }
            userProfile = userProfileRepository.findByPhoneNumber(findIdUserDTO.getPhoneNumber());
        }

        if (source.equals("email")) {
            if (email == null) {
                throw new IllegalArgumentException("이메일을 입력해 주세요.");

            }
            if (!userProfileRepository.existsByEmail(email)) {
                throw new IllegalArgumentException("존재하는 이메일이 없습니다.");
            }
            userProfile = userProfileRepository.findByEmail(findIdUserDTO.getEmail());
        }

        String findID = userProfile.getUser().getUsername();

        return findID;
    }

    // 비밀번호 찾기 기능
    public Boolean findPasswordFilter(UserProfileDTO.FindPassword findPasswordDTO) {

        String username = findPasswordDTO.getUsername();
        String email = findPasswordDTO.getEmail();
        String phoneNumber = findPasswordDTO.getPhoneNumber();
        String source = findPasswordDTO.getSource().getKey();

        // 아이디가 있는지 확인
        if (!userRepository.existsByUsername(username)) {
            // 존재하지 않을 경우 예외처리
            throw new IllegalArgumentException("존재하는 아이디가 없습니다.");
        }

        User target = userRepository.findByUsername(username);

        // 아이디에 해당하는 이메일을 입력했는지 확인
        if (source.equals("email")) {
            if (email == null) {
                throw new IllegalArgumentException("이메일을 입력해 주세요.");
            }
            if (!userProfileRepository.existsByEmailAndUser(email, target)) {
                throw new IllegalArgumentException("아이디에 해당하는 이메일이 없습니다.");
            }
        }

        // 아이디에 해당하는 전화번호를 입력했는지 확인
        if (source.equals("phone")) {
            if (phoneNumber == null) {
                throw new IllegalArgumentException("전화번호를 입력해 주세요.");
            }
            if (!userProfileRepository.existsByPhoneNumberAndUser(phoneNumber, target)) {
                throw new IllegalArgumentException("아이디에 해당하는 전화번호가 없습니다.");
            }
        }

        // -> 비밀번호 변경 페이지로 이동하기
        return true;
    }

    @Transactional
    // 비밀번호 변경 기능 (비 로그인 상태에서 진행 / 비밀번호 찾기 기능과 관련됨)
    public Boolean changePassword(UserDTO.ChangePassword changePasswordDTO) throws PasswordMismatchException {

        User target = userRepository.findByUsername(changePasswordDTO.getUsername());
        String password = changePasswordDTO.getPassword();
        String secondPassword = changePasswordDTO.getSecondPassword();

        // 비밀번호를 제대로 두 번 입력했는지 확인
        if (!password.equals(secondPassword)) {
            throw new PasswordMismatchException("입력한 비밀번호가 일치하지 않습니다.");
        }

        // 입력한 비밀번호가 DB에 저장된 비밀번호와 같은지 확인하기
        if (bCryptPasswordEncoder.matches(password, target.getPassword())) {
            throw new PasswordMismatchException("입력한 비밀번호가 현재 비밀번호와 같습니다");
        }

        target.setPassword(bCryptPasswordEncoder.encode(password)); // 비밀번호를 암호화하여 저장

        userRepository.save(target);

        return true;
    }

    // 로그인한 사용자 정보 조회 기능
    public CommonDTO.ShowResponse showUser(CustomUserDetails userInfo) {

        // 현재 인증된 사용자의 정보 가져오기 (클라이언트쪽에서 JWT 토큰을 넣어줘야 인증이 됨)
        String username = userInfo.getUsername();

        // 현재 인증된 사용자의 정보 담기
        User user = userRepository.findByUsername(username);

        // user Entity -> Dto 변환
        UserDTO.Show userDTO = userShowMapper.toDto(user);
        UserProfileDTO.Show userProfileDTO = userProfileShowMapper.toDto(user.getUserProfile());

        CommonDTO.ShowResponse dto = new CommonDTO.ShowResponse(userDTO, userProfileDTO);

        return dto;
    }

    // 회원정보 변경 기능
    public Boolean changeInfo(CustomUserDetails userInfo, CommonDTO.ChangeInfoRequest changeInfoRequest)
            throws PasswordMismatchException {
        // 현재 인증된 사용자의 정보 가져오기 (클라이언트쪽에서 JWT 토큰을 넣어줘야 인증이 됨)
        String username = userInfo.getUsername();

        // 현재 인증된 사용자의 정보 담기
        User targetUser = userRepository.findByUsername(username);
        UserProfile targetUserProfile = targetUser.getUserProfile();

        // 클라이언트에서 입력한 데이터 담기
        UserDTO.ChangeInfo userDTO = changeInfoRequest.getUserDTO();
        UserProfileDTO.ChangeInfo userProfileDTO = changeInfoRequest.getUserProfileDTO();

        // 등록되어 있는 비밀번호가 입력한 비밀번호와 맞는지 비교하기
        String currentPassword = userDTO.getCurrentPassword();
        if (!bCryptPasswordEncoder.matches(currentPassword, targetUser.getPassword())) {
            throw new PasswordMismatchException("정확한 비밀번호를 입력해 주세요.");
        }

        // 비밀번호를 바꿀 경우 == 입력한 비밀번호가 null 값이 아닌 경우
        if (userDTO.getPassword() != null) {
            // 비밀번호를 두 번 제대로 입력했는지 확인하기
            if (!userDTO.getPassword().equals(userDTO.getSecondPassword())) {
                throw new PasswordMismatchException("입력한 비밀번호가 일치하지 않습니다.");
            }
            // 입력한 비밀번호를 암호화하여 Entity에 저장
            targetUser.setPassword(bCryptPasswordEncoder.encode(userDTO.getPassword()));
        }

        targetUserProfile = userProfileChangeMapper.toEntity(userProfileDTO);

        targetUser.setUserProfile(targetUserProfile);
        // 사용자의 정보 변경하기
        userRepository.save(targetUser);

        return true;
    }

}
