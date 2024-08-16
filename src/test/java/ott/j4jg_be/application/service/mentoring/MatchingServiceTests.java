package ott.j4jg_be.application.service.mentoring;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import ott.j4jg_be.adapter.in.web.dto.mentoring.MatchingRequestDTO;
import ott.j4jg_be.application.port.in.point.PointUseCase;
import ott.j4jg_be.application.port.out.mentoring.GetMentoringPort;
import ott.j4jg_be.application.port.out.mentoring.MatchingPort;
import ott.j4jg_be.application.port.out.mentoring.UpdateMentoringApplicationPort;
import ott.j4jg_be.application.port.out.mentoring.UpdateMentoringPort;
import ott.j4jg_be.domain.mentoring.Mentoring;

import java.time.LocalDateTime;

import static org.mockito.Mockito.*;


public class MatchingServiceTests {

    @Mock
    private GetMentoringPort getMentoringPort;
    @Mock
    private MatchingPort matchingPort;
    @Mock
    private UpdateMentoringPort updateMentoringPort;
    @Mock
    private UpdateMentoringApplicationPort updateMentoringApplicationPort;
    @Mock
    private PointUseCase pointUseCase;

    @InjectMocks
    private MatchingService matchingService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void 매칭_테스트_인원_notfull(){
        //given
        Mentoring mentoring = mock(Mentoring.class);

        when(mentoring.isNotFull(mentoring.getMaxPerson(), mentoring.getCurrentPerson())).thenReturn(true);

        MatchingRequestDTO matchingRequestDTO = new MatchingRequestDTO(1, 1);
        when(getMentoringPort.getMentoring(1)).thenReturn(mentoring);

        //when
        matchingService.matching(matchingRequestDTO,"2");

        //then
        verify(matchingPort).matching(anyString(), anyInt());
        verify(updateMentoringPort).updateCurrentPerson(anyInt());
        verify(updateMentoringApplicationPort).updateStatus(anyInt());
        verify(pointUseCase).usePoints("2", 100, "멘토링 신청");
    }

    @Test
    void 매칭_테스트_인원_full(){
        //given
        Mentoring mentoring = mock(Mentoring.class);

        when(mentoring.isNotFull(mentoring.getMaxPerson(), mentoring.getCurrentPerson())).thenReturn(false);

        MatchingRequestDTO matchingRequestDTO = new MatchingRequestDTO(1, 1);
        when(getMentoringPort.getMentoring(1)).thenReturn(mentoring);

        //when
        matchingService.matching(matchingRequestDTO,"2");

        // Then
        verify(matchingPort, never()).matching(anyString(), anyInt());
        verify(updateMentoringPort, never()).updateCurrentPerson(anyInt());
        verify(updateMentoringApplicationPort, never()).updateStatus(anyInt());
        verify(pointUseCase, never()).usePoints(anyString(), anyInt(), anyString());
    }
}
