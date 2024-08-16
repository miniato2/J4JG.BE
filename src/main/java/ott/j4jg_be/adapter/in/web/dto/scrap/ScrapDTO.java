package ott.j4jg_be.adapter.in.web.dto.scrap;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
public class ScrapDTO {
    private int scrapId;
    private String userId;
    private int jobInfoId;
    private boolean status;
}
