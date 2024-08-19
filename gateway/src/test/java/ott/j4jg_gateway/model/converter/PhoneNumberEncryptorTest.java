package ott.j4jg_gateway.model.converter;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class PhoneNumberEncryptorTest {

    private PhoneNumberEncryptor phoneNumberEncryptor;
    private PasswordEncoder passwordEncoder;

    @BeforeEach
    public void setUp() {
        phoneNumberEncryptor = new PhoneNumberEncryptor();
        passwordEncoder = new BCryptPasswordEncoder();
    }

    @Test
    public void 전화번호를데이터베이스열로변환하기() {
        // 주어진 전화번호
        // given
        String phoneNumber = "010-1234-5678";

        // 전화번호를 해시하여 데이터베이스에 저장할 형식으로 변환
        // when
        String hashedPhoneNumber = phoneNumberEncryptor.convertToDatabaseColumn(phoneNumber);

        // 해시된 전화번호가 null이 아니어야 하며,
        // 해시된 값이 원래 전화번호와 일치하는지 확인
        // then
        assertNotNull(hashedPhoneNumber, "해시된 전화번호는 null이 아니어야 합니다.");
        assertTrue(passwordEncoder.matches(phoneNumber, hashedPhoneNumber), "해시된 전화번호는 원래 전화번호와 일치해야 합니다.");
    }

    @Test
    public void 복호화시해시된전화번호는null이어야한다() {
        // 전화번호를 해시하여 데이터베이스 열 형식으로 변환
        // given
        String hashedPhoneNumber = phoneNumberEncryptor.convertToDatabaseColumn("1234567890");

        // 해시된 전화번호를 복원하려고 시도
        // when
        String decryptedPhoneNumber = phoneNumberEncryptor.convertToEntityAttribute(hashedPhoneNumber);

        // 복호화가 불가능하므로 결과가 null이어야 함
        // then
        assertTrue(decryptedPhoneNumber == null, "복호화된 전화번호는 null이어야 합니다.");
    }
}
