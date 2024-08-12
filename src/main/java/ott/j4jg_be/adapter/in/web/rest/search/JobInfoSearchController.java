package ott.j4jg_be.adapter.in.web.rest.search;

import lombok.RequiredArgsConstructor;
import org.hibernate.mapping.Any;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import ott.j4jg_be.application.port.in.search.SearchJobInfoQuery;

@RestController
@RequiredArgsConstructor
public class JobInfoSearchController {

    private final SearchJobInfoQuery searchJobInfoQuery;

    @GetMapping("/jobInfo/search")
    public ResponseEntity<Object> searchJobInfo(@RequestParam String keyword,
                                                @RequestParam(defaultValue = "0") int categoryCode,
                                                @RequestParam(defaultValue = "0") int skillTag,
                                                @RequestParam(defaultValue = "0") int page){

        return ResponseEntity.ok().body(searchJobInfoQuery.searchJobInfo(keyword, categoryCode, skillTag, page));
    }

    @GetMapping("/jobInfo/{jobInfoId}")
    public ResponseEntity<Object> jobInfoDetail(@PathVariable int jobInfoId){
        return ResponseEntity.ok().body(searchJobInfoQuery.jobInfoDetail(jobInfoId));
    }
}
