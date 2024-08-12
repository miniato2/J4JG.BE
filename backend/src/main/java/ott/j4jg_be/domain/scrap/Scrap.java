package ott.j4jg_be.domain.scrap;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class Scrap {

    private int scrapId;
    private Long userId;
    private int jobInfoId;
    private boolean status;

    public Scrap(Long userId, int jobInfoId, boolean status){
        this.userId = userId;
        this.jobInfoId = jobInfoId;
        this.status = status;
    }
}
