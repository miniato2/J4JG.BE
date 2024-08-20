package ott.j4jg_gateway.model.converter;

import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import java.nio.charset.StandardCharsets;
import java.security.SecureRandom;
import java.util.Base64;

@Component
@Converter(autoApply = true)
public class PhoneNumberEncryptor implements AttributeConverter<String, String> {

    @Value("${aes.secret-key:defaultSecretKey}")
    private String secretKey;

    @Override
    public String convertToDatabaseColumn(String phoneNumber) {
        return encrypt(phoneNumber);
    }

    @Override
    public String convertToEntityAttribute(String encryptedPhoneNumber) {
        return decrypt(encryptedPhoneNumber);
    }

    public String encrypt(String value) {
        if (value == null) {
            return null; // null 입력 시 null 반환
        }

        try {
            byte[] iv = new byte[16];
            SecureRandom random = new SecureRandom();
            random.nextBytes(iv);
            IvParameterSpec ivSpec = new IvParameterSpec(iv);
            SecretKeySpec keySpec = new SecretKeySpec(secretKey.getBytes(StandardCharsets.UTF_8), "AES");
            Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5PADDING");
            cipher.init(Cipher.ENCRYPT_MODE, keySpec, ivSpec);
            byte[] encrypted = cipher.doFinal(value.getBytes(StandardCharsets.UTF_8));
            byte[] encryptedWithIv = new byte[iv.length + encrypted.length];
            System.arraycopy(iv, 0, encryptedWithIv, 0, iv.length);
            System.arraycopy(encrypted, 0, encryptedWithIv, iv.length, encrypted.length);
            return Base64.getEncoder().encodeToString(encryptedWithIv);
        } catch (Exception ex) {
            throw new RuntimeException("값을 암호화하는 중에 오류가 발생했습니다: " + ex.getMessage(), ex);
        }
    }

    public String decrypt(String encrypted) {
        if (encrypted == null) {
            return null; // null 입력 시 null 반환
        }

        try {
            byte[] encryptedWithIv = Base64.getDecoder().decode(encrypted);
            byte[] iv = new byte[16];
            byte[] cipherText = new byte[encryptedWithIv.length - iv.length];
            System.arraycopy(encryptedWithIv, 0, iv, 0, iv.length);
            System.arraycopy(encryptedWithIv, iv.length, cipherText, 0, cipherText.length);
            IvParameterSpec ivSpec = new IvParameterSpec(iv);
            SecretKeySpec keySpec = new SecretKeySpec(secretKey.getBytes(StandardCharsets.UTF_8), "AES");
            Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5PADDING");
            cipher.init(Cipher.DECRYPT_MODE, keySpec, ivSpec);
            byte[] original = cipher.doFinal(cipherText);
            return new String(original, StandardCharsets.UTF_8);
        } catch (Exception ex) {
            throw new RuntimeException("값을 해독하는 중 오류가 발생했습니다: " + ex.getMessage(), ex);
        }
    }
}
