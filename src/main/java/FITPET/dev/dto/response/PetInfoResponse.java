package FITPET.dev.dto.response;

import FITPET.dev.common.annotation.ExcelColumn;
import FITPET.dev.common.annotation.ExcelFile;
import java.util.List;
import FITPET.dev.common.enums.ComparisonStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

public class PetInfoResponse {

    @Data
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    @ExcelFile
    public static class PetInfoExcelDto {
        @ExcelColumn(headerName = "견적서Id")
        private Long petInfoId;
        @ExcelColumn(headerName = "견적 요청 일시")
        private String createdAt;
        @ExcelColumn(headerName = "품종")
        private String petType;
        @ExcelColumn(headerName = "상세 품종")
        private String detailType;
        @ExcelColumn(headerName = "이름")
        private String name;
        @ExcelColumn(headerName = "나이")
        private int age;
        @ExcelColumn(headerName = "보호자 연락처")
        private String phoneNum;
        @ExcelColumn(headerName = "비고")
        private String comment;
        @ExcelColumn(headerName = "상태")
        private ComparisonStatus comparisonStatus;
    }

    @Data
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class PetInfoDetailDto {
        private Long petInfoId;
        private String createdAt;
        private String petType;
        private String detailType;
        private String name;
        private int age;
        private String phoneNum;
        private String comment;
        private String status;
    }

    @Data
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class PetInfoDetailPageDto {
        private List<PetInfoDetailDto> content;
        private int currentPage; // 현재 페이지 번호
        private int pageSize; // 페이지 크기
        private int totalNumber; // 전체 content 개수
        private int totalPage; // 전체 페이지 개수
    }

}
