package ott.j4jg_gateway.controller;

import ott.j4jg_gateway.model.dto.UserAuthDTO;
import ott.j4jg_gateway.model.dto.UserAddInfoDTO;
import ott.j4jg_gateway.model.entity.User;
import ott.j4jg_gateway.jwt.JWTUtil;
import ott.j4jg_gateway.repository.UserRepository;
import ott.j4jg_gateway.service.UserAddInfoService;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
public class UserController {

    private static final Logger logger = LoggerFactory.getLogger(UserController.class);
    private final UserRepository userRepository;
    private final JWTUtil jwtUtil;
    private final UserAddInfoService userAddInfoService;

    @Autowired
    public UserController(UserRepository userRepository, JWTUtil jwtUtil, UserAddInfoService userAddInfoService) {
        this.userRepository = userRepository;
        this.jwtUtil = jwtUtil;
        this.userAddInfoService = userAddInfoService;
    }

    @CrossOrigin(origins = "*", allowedHeaders = "*")
    @GetMapping("/userinfo")
    public ResponseEntity<?> getUserInfo(@RequestHeader("Authorization") String authorizationHeader) {
        String token = extractTokenFromHeader(authorizationHeader);

        if (token == null) {
            logger.warn("Authorization 헤더가 없거나 유효하지 않습니다.");
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Authorization 헤더가 없거나 유효하지 않습니다.");
        }

        try {
            Jws<Claims> claims = jwtUtil.parseClaims(token);
            String email = jwtUtil.getEmail(token);
            String provider = jwtUtil.getProvider(token);
            logger.info("토큰이 성공적으로 파싱되었습니다. 이메일: {}, 제공자: {}", email, provider);

            if (email == null || provider == null) {
                logger.warn("토큰에 유효한 이메일 또는 제공자가 포함되어 있지 않습니다.");
                return ResponseEntity.badRequest().body("토큰에 유효한 이메일 또는 제공자가 포함되어 있지 않습니다.");
            }

            Optional<User> optionalUser = userRepository.findByUserEmailAndProvider(email, provider);
            if (optionalUser.isPresent()) {
                User user = optionalUser.get();
                UserAuthDTO userDTO = UserAuthDTO.fromEntity(user);
                return ResponseEntity.ok(userDTO);
            } else {
                logger.info("데이터베이스에 사용자가 존재하지 않습니다. 이메일: {}, 제공자: {}", email, provider);
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("사용자 정보가 존재하지 않습니다.");
            }

        } catch (io.jsonwebtoken.ExpiredJwtException e) {
            logger.error("토큰이 만료되었습니다: {}", e.getMessage());
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("토큰이 만료되었습니다.");
        } catch (io.jsonwebtoken.SignatureException e) {
            logger.error("유효하지 않은 토큰 서명입니다: {}", e.getMessage());
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("유효하지 않은 토큰 서명입니다.");
        } catch (Exception e) {
            logger.error("토큰 파싱 중 오류가 발생했습니다: {}", e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("토큰 파싱 중 오류가 발생했습니다.");
        }
    }

    @GetMapping("/my")
    public ResponseEntity<?> getMyInfo(@RequestHeader("Authorization") String authorizationHeader) {
        String token = extractTokenFromHeader(authorizationHeader);

        if (token == null) {
            logger.warn("Authorization 헤더가 없거나 유효하지 않습니다.");
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Authorization 헤더가 없거나 유효하지 않습니다.");
        }

        try {
            Jws<Claims> claims = jwtUtil.parseClaims(token);
            String email = jwtUtil.getEmail(token);
            String provider = jwtUtil.getProvider(token);
            logger.info("토큰이 성공적으로 파싱되었습니다. 이메일: {}, 제공자: {}", email, provider);

            if (email == null || provider == null) {
                logger.warn("토큰에 유효한 이메일 또는 제공자가 포함되어 있지 않습니다.");
                return ResponseEntity.badRequest().body("토큰에 유효한 이메일 또는 제공자가 포함되어 있지 않습니다.");
            }

            Optional<User> optionalUser = userRepository.findByUserEmailAndProvider(email, provider);
            if (optionalUser.isPresent()) {
                User user = optionalUser.get();
                UserAddInfoDTO userAddInfoDTO = userAddInfoService.getUserAddInfoDTO(user.getUserId());
                return ResponseEntity.ok(userAddInfoDTO);
            } else {
                logger.info("데이터베이스에 사용자가 존재하지 않습니다. 이메일: {}, 제공자: {}", email, provider);
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("사용자 정보가 존재하지 않습니다.");
            }

        } catch (io.jsonwebtoken.ExpiredJwtException e) {
            logger.error("토큰이 만료되었습니다: {}", e.getMessage());
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("토큰이 만료되었습니다.");
        } catch (io.jsonwebtoken.SignatureException e) {
            logger.error("유효하지 않은 토큰 서명입니다: {}", e.getMessage());
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("유효하지 않은 토큰 서명입니다.");
        } catch (Exception e) {
            logger.error("토큰 파싱 중 오류가 발생했습니다: {}", e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("토큰 파싱 중 오류가 발생했습니다.");
        }
    }

    @PutMapping("/user/update-nickname")
    public ResponseEntity<?> updateNickname(@RequestHeader("Authorization") String authorizationHeader, @RequestBody String newNickname) {
        String token = extractTokenFromHeader(authorizationHeader);

        if (token == null) {
            logger.warn("Authorization 헤더가 없거나 유효하지 않습니다.");
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Authorization 헤더가 없거나 유효하지 않습니다.");
        }

        try {
            Jws<Claims> claims = jwtUtil.parseClaims(token);
            String email = jwtUtil.getEmail(token);
            String provider = jwtUtil.getProvider(token);
            logger.info("토큰이 성공적으로 파싱되었습니다. 이메일: {}, 제공자: {}", email, provider);

            if (email == null || provider == null) {
                logger.warn("토큰에 유효한 이메일 또는 제공자가 포함되어 있지 않습니다.");
                return ResponseEntity.badRequest().body("토큰에 유효한 이메일 또는 제공자가 포함되어 있지 않습니다.");
            }

            Optional<User> optionalUser = userRepository.findByUserEmailAndProvider(email, provider);
            if (optionalUser.isPresent()) {
                String userId = optionalUser.get().getUserId();
                boolean success = userAddInfoService.updateUserNickname(userId, newNickname);

                if (success) {
                    return ResponseEntity.ok("닉네임이 성공적으로 업데이트되었습니다.");
                } else {
                    return ResponseEntity.badRequest().body("닉네임 업데이트에 실패했습니다. 입력값을 확인해 주세요.");
                }
            } else {
                logger.info("데이터베이스에 사용자가 존재하지 않습니다. 이메일: {}, 제공자: {}", email, provider);
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("사용자 정보가 존재하지 않습니다.");
            }

        } catch (Exception e) {
            logger.error("닉네임 업데이트 중 오류가 발생했습니다: {}", e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("닉네임 업데이트 중 오류가 발생했습니다.");
        }
    }

    private String extractTokenFromHeader(String authorizationHeader) {
        if (authorizationHeader != null && authorizationHeader.startsWith("Bearer ")) {
            return authorizationHeader.substring(7);
        }
        return null;
    }
}
