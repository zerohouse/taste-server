package at.begin.web.upload;

import at.begin.infra.response.JsonResponse;
import at.begin.web.user.User;
import at.begin.web.user.inject.Logged;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.Objects;
import java.util.UUID;

import static at.begin.infra.response.JsonResponseFactory.errorResponse;
import static at.begin.infra.response.JsonResponseFactory.successResponse;

@RestController
@RequestMapping("/api/v1/upload")
@PropertySource("classpath:application.properties")
public class UploadFileController {

    @Value("${FILE_UPLOAD_PATH}")
    private String FILE_STORAGE_DIRECTORY;

    @Value("${RELATIVE_PATH}")
    private String RELATIVE_PATH;

    @RequestMapping
    public JsonResponse uploadContentFile(MultipartFile file, @Logged User user) {
        if (file.isEmpty())
            return errorResponse("파일이 없습니다.");

        Objects.requireNonNull(FILE_STORAGE_DIRECTORY);
        ensureFileSaveDirectoryExist(FILE_STORAGE_DIRECTORY);

        String uglifiedFileName = uglifyFileName(file);

        File fileStorePath = new File(FILE_STORAGE_DIRECTORY + uglifiedFileName);

        try {
            file.transferTo(fileStorePath);
        } catch (IllegalStateException | IOException e) {
            return errorResponse("파일 업로드 중 문제가 발생했습니다.");
        }

        return successResponse(RELATIVE_PATH + uglifiedFileName);
    }

    private String uglifyFileName(MultipartFile file) {
        return System.currentTimeMillis() + UUID.randomUUID().toString().replace("-", "") + extractFileExtention(file.getOriginalFilename());
    }

    private String extractFileExtention(String fileName) {
        int lastPeriod = fileName.lastIndexOf(".");
        return fileName.substring(lastPeriod, fileName.length());
    }

    private void ensureFileSaveDirectoryExist(String fileSaveDirectoryPath) {
        File fileSaveDirectory = new File(fileSaveDirectoryPath);

        if (!fileSaveDirectory.exists()) {
            fileSaveDirectory.mkdirs();
        }
    }

}
