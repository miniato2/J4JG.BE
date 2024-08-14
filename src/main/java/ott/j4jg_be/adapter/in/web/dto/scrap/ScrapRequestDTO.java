package ott.j4jg_be.adapter.in.web.dto.scrap;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ScrapRequestDTO {
    private Long userId;
    private int jobInfoId;
}
