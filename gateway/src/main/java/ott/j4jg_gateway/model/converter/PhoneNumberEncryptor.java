package ott.j4jg_gateway.model.converter;

import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Converter
public class PhoneNumberEncryptor implements AttributeConverter<String, String> {

    private final PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    @Override
    public String convertToDatabaseColumn(String attribute) {
        // 암호화
        return attribute != null ? passwordEncoder.encode(attribute) : null;
    }

    @Override
    public String convertToEntityAttribute(String dbData) {
        // 단일키 불가능하므로, 단순히 복원된 문자열이 없다는 것을 나타내는 빈 문자열 또는 null을 반환함
        return null;
    }
}
