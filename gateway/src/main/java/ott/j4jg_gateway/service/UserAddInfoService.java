package ott.j4jg_gateway.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ott.j4jg_gateway.model.dto.UserAddInfoDTO;
import ott.j4jg_gateway.model.entity.User;
import ott.j4jg_gateway.model.entity.UserAddInfo;
import ott.j4jg_gateway.repository.UserAddInfoRepository;
import ott.j4jg_gateway.repository.UserRepository;

import java.util.Optional;

@Service
public class UserAddInfoService {

    private final UserRepository userRepository;
    private final UserAddInfoRepository userAddInfoRepository;

    @Autowired
    public UserAddInfoService(UserRepository userRepository, UserAddInfoRepository userAddInfoRepository) {
        this.userRepository = userRepository;
        this.userAddInfoRepository = userAddInfoRepository;
    }

    public UserAddInfoDTO getUserAddInfoDTO(String userId) {
        Optional<User> userOptional = userRepository.findById(userId);
        if (userOptional.isPresent()) {
            User user = userOptional.get();
            Optional<UserAddInfo> userAddInfoOptional = userAddInfoRepository.findByUser(user);
            return userAddInfoOptional
                    .map(userAddInfo -> UserAddInfoDTO.fromEntity(user, userAddInfo))
                    .orElseGet(() -> UserAddInfoDTO.fromEntity(user, null));
        }
        return null;
    }

    public boolean updateUserNickname(String userId, String newNickname) {
        if (newNickname == null || newNickname.length() > 9) {
            return false; // 닉네임이 null이거나 길이가 9자를 초과함
        }

        Optional<User> userOptional = userRepository.findById(userId);
        if (!userOptional.isPresent()) {
            return false; // 사용자 존재하지 않음
        }

        // 닉네임 중복 체크
        Optional<UserAddInfo> existingUserAddInfo = userAddInfoRepository.findByUserNickname(newNickname);
        if (existingUserAddInfo.isPresent() && !existingUserAddInfo.get().getUser().getUserId().equals(userId)) {
            return false; // 중복된 닉네임 존재
        }

        User user = userOptional.get();
        Optional<UserAddInfo> userAddInfoOptional = userAddInfoRepository.findByUser(user);
        UserAddInfo userAddInfo = userAddInfoOptional.orElse(new UserAddInfo());

        userAddInfo.setUser(user);
        userAddInfo.setUserNickname(newNickname);
        userAddInfoRepository.save(userAddInfo);

        return true;
    }
}
