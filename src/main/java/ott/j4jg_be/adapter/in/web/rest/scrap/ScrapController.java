package ott.j4jg_be.adapter.in.web.rest.scrap;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ott.j4jg_be.adapter.in.web.dto.scrap.ScrapDTO;
import ott.j4jg_be.adapter.in.web.dto.scrap.ScrapRequestDTO;
import ott.j4jg_be.adapter.in.web.mapper.scrap.ScrapMapper;
import ott.j4jg_be.application.port.in.scrap.CancelScrapUsecase;
import ott.j4jg_be.application.port.in.scrap.GetScrapQuery;
import ott.j4jg_be.application.port.in.scrap.ScrapUsecase;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class ScrapController {

    private final ScrapUsecase scrapUsecase;
    private final CancelScrapUsecase cancelScrapUsecase;
    private final GetScrapQuery getScrapQuery;

    @PostMapping("/scrap")
    public void scrapJobInfo(@ModelAttribute ScrapRequestDTO scrapRequestDTO) {
        scrapUsecase.scrapJobInfo(scrapRequestDTO);
    }

    @PutMapping("/scrap")
    public void cancelScrap(int scrapId) {
        cancelScrapUsecase.cancelScrap(scrapId);
    }

    @GetMapping("/scrap")
    public ResponseEntity<List<ScrapDTO>> getScrapList(Long userId){

        return ResponseEntity.ok().body(getScrapQuery.getScrapList(userId));
    }

}
