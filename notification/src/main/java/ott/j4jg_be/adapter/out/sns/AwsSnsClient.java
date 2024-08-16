package ott.j4jg_be.adapter.out.sns;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import ott.j4jg_be.application.port.out.sns.SnsPort;
import software.amazon.awssdk.services.sns.SnsClient;
import software.amazon.awssdk.services.sns.model.PublishRequest;

@Component
@RequiredArgsConstructor
public class AwsSnsClient implements SnsPort {

    private final SnsClient snsClient;

    @Override
    public void publish(String phoneNumber, String message) {
        PublishRequest request = PublishRequest.builder()
                .message(message)
                .phoneNumber(phoneNumber)
                .build();
        snsClient.publish(request);
    }
}
