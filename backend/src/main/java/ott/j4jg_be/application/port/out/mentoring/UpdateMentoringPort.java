package ott.j4jg_be.application.port.out.mentoring;


public interface UpdateMentoringPort {
    void updateCurrentPerson(int mentoringId);

    void updateStatus(int mentoringId);
}
