package FITPET.dev.common.basecode;


import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum ErrorStatus {
    /**
     *  Error Code
     *  400 : 잘못된 요청
     *  401 : JWT에 대한 오류
     *  403 : 요청한 정보에 대한 권한 없음.
     *  404 : 존재하지 않는 정보에 대한 요청.
     */

    /**
     * 400
     */
    FAILURE_READ_EXCEL_FILE(400, "엑셀 파일을 읽는 과정에서 오류가 발생했습니다."),
    FAILURE_READ_EXCEL_SHEET(400, "엑셀 시트를 읽는 과정에서 오류가 발생했습니다."),
    INVALID_PET_TYPE(400, "잘못된 품종 정보입니다."),
    INVALID_COMPANY(400, "잘못된 회사명입니다."),

    INVALID_PHONE_NUMBER(400,"유효하지 않은 전화번호 형식입니다."),
    INVALID_AGE(400,"유효하지 않은 나이 값입니다."),

    /**
     * 404
     * NOT_FOUND
     */
    NOT_EXIST_PET(404, "존재하지 않는 품종입니다."),
    NOT_EXIST_PET_INFO(404, "존재하지 않는 펫 정보입니다."),

    /**
     * 500
     */
    FAILURE_RENDER_EXCEL_BODY(500, "엑셀 바디를 그리는 중 오류가 발생했습니다."),
    FAILURE_ENCODE_EXCEL_FILE_NAME(500, "엑셀 파일 이름을 인코딩 하는 중 오류가 발생했습니다.");
    


    private final int code;
    private final String message;
}