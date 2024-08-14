package ott.j4jg_be.application.service.mentoring;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import ott.j4jg_be.application.port.in.point.PointUseCase;
import ott.j4jg_be.application.port.out.scrap.CreateScrapPort;
import ott.j4jg_be.application.port.out.scrap.GetScrapPort;
import ott.j4jg_be.application.port.out.scrap.UpdateScrapPort;
import ott.j4jg_be.application.service.scrap.ScrapService;

import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.when;


public class ScrapServiceTests {
    @Mock
    private CreateScrapPort createScrapPort;
    @Mock
    private GetScrapPort getScrapPort;
    @Mock
    private UpdateScrapPort updateScrapPort;
    @Mock
    private PointUseCase pointUseCase;
    @InjectMocks
    private ScrapService scrapService;

    @Test
    void 스크랩_테스트(){

        //given

        //when

        //then


//        when(getScrapPort.getScrapByUserAndJobInfo()).andReturn();
    }




}
