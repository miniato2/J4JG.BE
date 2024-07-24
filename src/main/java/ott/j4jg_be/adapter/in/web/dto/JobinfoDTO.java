package ott.j4jg_be.adapter.in.web.dto;

import jakarta.validation.constraints.NotNull;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class JobinfoDTO {

    private int id; //채용정보 id

    // 기업은 묶자
    private int companyId;  //기업 id
    private String companyName; //기업이름
    private String companyImg; //기업 이미지

    private String companyAvgRate; //기업 평가
    private String companyLvl; //레벨
    //

    //주소 묶기
    private String location; //지역 ex)서울
    private String district; //지역 ex)중구, 성남시, 강남구
    //

    private String position; //직무
    private int categoryCode; //카테고리 코드
    private int[] attractionTags; // 인기 태그
    private int[] skillTag; //스킬태그
    private int annualFrom; //연차 최소
    private int annualTo; //연차
    private boolean isNewbie; //신입가능

}
