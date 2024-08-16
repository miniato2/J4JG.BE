package ott.j4jg_be.application.port.out.sns;

public interface SnsPort {

    void publish(String phoneNumber, String message);
}
