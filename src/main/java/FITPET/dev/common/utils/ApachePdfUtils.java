package FITPET.dev.common.utils;

import FITPET.dev.common.enums.PetType;
import FITPET.dev.common.exception.GeneralException;
import FITPET.dev.common.status.ErrorStatus;
import FITPET.dev.entity.Comparison;
import FITPET.dev.entity.Pet;
import FITPET.dev.entity.PetInfo;
import jakarta.servlet.ServletOutputStream;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.apache.pdfbox.Loader;
import org.apache.pdfbox.multipdf.PDFMergerUtility;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.PDPageContentStream;
import org.apache.pdfbox.pdmodel.font.PDType1Font;
import org.springframework.stereotype.Component;

import java.io.File;
import java.io.IOException;
import java.net.URLEncoder;
import java.time.format.DateTimeFormatter;

import static org.apache.pdfbox.pdmodel.font.Standard14Fonts.FontName.HELVETICA_BOLD;

@Slf4j
@Component
public class ApachePdfUtils {

    /*
     * 견적서 PDF 파일을 추출함
     * @param response
     * @param comparison
     */
    public void downloadComparisonPdf(HttpServletResponse response, Comparison comparison) {

        String backgroundPath = getBackgroundPath(comparison.getPetInfo().getPet());

        // 배경용 PDF 파일 로드
        PDDocument doc = loadPDDocument(backgroundPath);

        // 보험지급건 PDF 파일 로드
        PDDocument doc2 = loadPDDocument("src/main/resources/assets/보험지급건.pdf");

        // 배경용 PDF 편집
        drawComparisonPdf(doc, comparison);

        // 파일 이름 생성
        String fileName = getPdfFileName(comparison);

        // 두 개의 PDF 병합 및 다운로드
        mergeAndDownloadPdf(response, fileName, doc, doc2);
    }

    private String getBackgroundPath(Pet pet) {
        String path = "src/main/resources/assets/";

        return pet.getPetType() == PetType.DOG ? path + "dog_background.pdf" : path + "cat_background.pdf";
    }


    private PDDocument loadPDDocument(String path){
        try{
            return Loader.loadPDF(new File(path));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }


    private void drawComparisonPdf(PDDocument doc, Comparison comparison) {
        PDPage page = doc.getPage(0);

        try {
            // 컨텐츠 스트림 열기
            PDPageContentStream contentStream = new PDPageContentStream(doc, page, PDPageContentStream.AppendMode.APPEND, true, true);

            // TODO: 보험료 정보 수정
            // 폰트 설정 및 텍스트 추가
            PDType1Font font = new PDType1Font(HELVETICA_BOLD);
            contentStream.setFont(font, 12);
            contentStream.beginText();
            contentStream.newLineAtOffset(100, 700); // 텍스트 시작 위치
            contentStream.endText();

            contentStream.close();

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }


    private String getPdfFileName(Comparison comparison) {
        PetInfo petInfo = comparison.getPetInfo();

        // 요청 일시 포맷 변경
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyMMdd");
        String formattedDate = comparison.getCreatedAt().format(formatter);

        return petInfo.getPhoneNum().substring(9, 13) + "_" +
                petInfo.getName() + "_" +
                petInfo.getPet().getPetSpecies() + "_" +
                formattedDate;
    }


    private void mergeAndDownloadPdf(HttpServletResponse response, String fileName, PDDocument doc, PDDocument doc2) {
        try {
            // 임시 파일 생성
            File tempFile1 = File.createTempFile("temp1", ".pdf");
            File tempFile2 = File.createTempFile("temp2", ".pdf");

            // PDF 문서를 임시 파일에 저장
            doc.save(tempFile1);
            doc2.save(tempFile2);

            // PDF 병합을 위한 PDFMergerUtility 생성
            PDFMergerUtility merger = new PDFMergerUtility();
            merger.addSource(tempFile1);
            merger.addSource(tempFile2);

            // 병합된 PDF를 outputStream으로 저장
            ServletOutputStream outputStream = setServletOutputStream(response, fileName);
            merger.setDestinationStream(outputStream);
            merger.mergeDocuments(null);

            // 입출력 스트림을 닫음
            doc.close();
            doc2.close();
            outputStream.close();

            // 임시 파일 삭제
            tempFile1.delete();
            tempFile2.delete();

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }


    private ServletOutputStream setServletOutputStream(HttpServletResponse response, String fileName){
        try {
            // 파일 다운로드를 위해 파일 형식 지정
            response.setContentType("application/pdf");
            response.setCharacterEncoding("UTF-8");

            // 파일 이름 지정
            String encodedFileName = encodePdfFileName(fileName);
            response.setHeader("Content-Disposition", "inline; filename=\"" + encodedFileName + ".pdf\"; filename*=UTF-8''" + encodedFileName + ".pdf");

            return response.getOutputStream();
        } catch (IOException e){
            throw new RuntimeException(e);
        }
    }


    private String encodePdfFileName(String fileName) {
        try{
            return URLEncoder.encode(fileName, "UTF-8").replaceAll("\\+", "%20");
        } catch (Exception e){
            throw new GeneralException(ErrorStatus.FAILURE_ENCODE_PDF_FILE_NAME);
        }
    }

}
