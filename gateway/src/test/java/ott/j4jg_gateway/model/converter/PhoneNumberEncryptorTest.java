//package ott.j4jg_gateway.model.converter;
//
//import org.junit.jupiter.api.DisplayName;
//import org.junit.jupiter.api.Test;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.test.context.ActiveProfiles;
//
//import static org.junit.jupiter.api.Assertions.*;
//
//@SpringBootTest
//@ActiveProfiles("test")
//public class PhoneNumberEncryptorTest {
//
//    @Autowired
//    private PhoneNumberEncryptor phoneNumberEncryptor;
//
//    @Test
//    @DisplayName("전화번호 암호화 및 복호화 테스트")
//    public void 전화번호_암호화_및_복호화_테스트() {
//        // Given
//        String 원래전화번호 = "010-1234-5678";
//
//        // When
//        String 암호화된전화번호 = phoneNumberEncryptor.encrypt(원래전화번호);
//        String 결과_복호화 = phoneNumberEncryptor.decrypt(암호화된전화번호);
//
//        // Then
//        assertNotNull(암호화된전화번호, "암호화 결과는 null이 아니어야 합니다."); // 암호화 결과가 null이 아님을 확인
//        assertEquals(원래전화번호, 결과_복호화, "복호화된 결과는 원래 전화번호와 같아야 합니다."); // 원래 전화번호와 일치하는지 확인
//    }
//
//    @Test
//    @DisplayName("null 입력 시 암호화 테스트")
//    public void testEncryptWithNull() {
//        // Given
//        String nullPhoneNumber = null;
//
//        // When
//        String encryptedPhoneNumber = phoneNumberEncryptor.encrypt(nullPhoneNumber);
//
//        // Then
//        assertNull(encryptedPhoneNumber, "null 입력 시 암호화 결과는 null이어야 합니다."); // null을 입력했을 때 null이 반환되는지 확인
//    }
//
//    @Test
//    @DisplayName("null 입력 시 복호화 테스트")
//    public void testDecryptWithNull() {
//        // Given
//        String nullEncryptedPhoneNumber = null;
//
//        // When
//        String decryptedPhoneNumber = phoneNumberEncryptor.decrypt(nullEncryptedPhoneNumber);
//
//        // Then
//        assertNull(decryptedPhoneNumber, "null 입력 시 복호화 결과는 null이어야 합니다."); // null을 입력했을 때 null이 반환되는지 확인
//    }
//
//    @Test
//    @DisplayName("빈 문자열 암호화 및 복호화 테스트")
//    public void testEncryptDecryptEmptyString() {
//        // Given
//        String emptyPhoneNumber = "";
//
//        // When
//        String 암호화된전화번호 = phoneNumberEncryptor.encrypt(emptyPhoneNumber);
//        String decryptedPhoneNumber = phoneNumberEncryptor.decrypt(암호화된전화번호);
//
//        // Then
//        assertNotNull(암호화된전화번호, "빈 문자열 암호화 결과는 null이 아니어야 합니다."); // 암호화 결과가 null이 아님을 확인
//        assertEquals(emptyPhoneNumber, decryptedPhoneNumber, "빈 문자열이 복원되어야 합니다."); // 빈 문자열이 복원되는지 확인
//    }
//
//    @Test
//    @DisplayName("특수 문자 포함 전화번호 암호화 및 복호화 테스트")
//    public void testEncryptDecryptSpecialCharacters() {
//        // Given
//        String specialPhoneNumber = "!@#$%^&*()";
//
//        // When
//        String 암호화된전화번호 = phoneNumberEncryptor.encrypt(specialPhoneNumber);
//        String decryptedPhoneNumber = phoneNumberEncryptor.decrypt(암호화된전화번호);
//
//        // Then
//        assertNotNull(암호화된전화번호, "특수 문자가 포함된 전화번호 암호화 결과는 null이 아니어야 합니다."); // 암호화 결과가 null이 아님을 확인
//        assertEquals(specialPhoneNumber, decryptedPhoneNumber, "원래 특수문자 전화번호가 복원되어야 합니다."); // 원래 특수문자 전화번호와 일치하는지 확인
//    }
//}
