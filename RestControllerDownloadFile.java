import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

@Controller
public class FileController {

    @GetMapping("/download")
    @ResponseBody
    public ResponseEntity<StreamingResponseBody> downloadFile() throws IOException {
        // Get your file from the database or any other source
        File file = new File("example.xlsx");

        // Set the headers for the response
        HttpHeaders headers = new HttpHeaders();
        headers.add(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=example.xlsx");

        // Set the content length to allow for proper progress reporting
        headers.setContentLength(file.length());

        // Return the file as a ResponseEntity with a StreamingResponseBody
        return ResponseEntity.ok()
                .headers(headers)
                .contentType(MediaType.parseMediaType("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet"))
                .body(outputStream -> {
                    try (InputStream inputStream = new FileInputStream(file)) {
                        // Set the buffer size to control the size of each chunk of data
                        byte[] buffer = new byte[4096];
                        int bytesRead = -1;
                        while ((bytesRead = inputStream.read(buffer)) != -1) {
                            outputStream.write(buffer, 0, bytesRead);
                        }
                    }
                });
    }
}
