package ott.j4jg_be.adapter.out.crawler.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class NewsDTO {
    private String action_index;
    private String action;
    private String id;
    private String companyName;
    private String title;
    private String url;
}
