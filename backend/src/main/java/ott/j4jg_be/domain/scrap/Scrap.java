package ott.j4jg_be.domain.scrap;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class Scrap {

    private int scrapId;
    private String userId;
    private int jobInfoId;
    private boolean status;

    public Scrap(String userId, int jobInfoId, boolean status){
        this.userId = userId;
        this.jobInfoId = jobInfoId;
        this.status = status;
    }
}
