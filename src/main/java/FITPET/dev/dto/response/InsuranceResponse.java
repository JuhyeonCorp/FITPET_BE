package FITPET.dev.dto.response;


import FITPET.dev.common.annotation.ExcelColumn;
import FITPET.dev.common.annotation.ExcelFile;
import FITPET.dev.common.enums.*;
import jakarta.persistence.Column;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.*;

import java.util.List;

public class InsuranceResponse {
    @Data @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class InsuranceListDto {
        private List<InsuranceDto> insuranceDtoList;
    }

    @Data @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class AllInsuranceListDto {
        private List<InsuranceDto> seventyinsuranceDtoList;
        private List<InsuranceDto> eightyinsuranceDtoList;
        private List<InsuranceDto> ninetyinsuranceDtoList;
    }

    @Data
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class InsuranceDto {
        private Long insuranceId;
        private String company;
        private int premium;
        private int discountedPremium;
    }

    @Data
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class InsuranceDetailPageDto {
        private List<InsuranceDetailDto> content;
        private int currentPage; // 현재 페이지 번호
        private int pageSize; // 페이지 크기
        private int numberOfElement; // 현재 페이지의 element 개수
        private int totalElements; // 전체 element 개수
        private int totalPage; // 전체 페이지 개수
    }

    @Data
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class InsuranceDetailDto {
        private Long insuranceId;
        private String company;
        private PetType petType;
        private int age;
        private String dogBreedRank; // 견종등급
        private String renewalCycle; // 갱신주기
        private String coverageRatio; // 보상비율
        private String deductible; // 자부담금
        private String compensation; // 1일 보상
        private int premium;
    }

    @Data
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    @ExcelFile
    public static class InsuranceExcelDto {
        private String company;
        @ExcelColumn(headerName = "보험Id")
        private Long insuranceId;
        @ExcelColumn(headerName = "품종")
        private PetType petType;
        @ExcelColumn(headerName = "나이")
        private int age;
        @ExcelColumn(headerName = "견종등급")
        private String dogBreedRank; // 견종등급
        @ExcelColumn(headerName = "갱신주기")
        private String renewalCycle; // 갱신주기
        @ExcelColumn(headerName = "보상비율")
        private String coverageRatio; // 보상비율
        @ExcelColumn(headerName = "자부담")
        private String deductible; // 자부담금
        @ExcelColumn(headerName = "1일 보상")
        private String compensation; // 1일 보상
        @ExcelColumn(headerName = "보험료")
        private int premium;
    }

}
