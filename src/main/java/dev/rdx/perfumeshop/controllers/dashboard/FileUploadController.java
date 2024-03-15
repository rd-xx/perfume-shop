package dev.rdx.perfumeshop.controllers.dashboard;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.nio.file.Files;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

@RestController
public class FileUploadController {
    @Value("${upload.path}")
    private String localPath;

    @PostMapping("/uploadFile")
    public List<String> uploadFile(@RequestParam MultipartFile file) {
        File uploadDir = new File(localPath);
        SimpleDateFormat dateFormat = new SimpleDateFormat("/yyyy/MM/dd/");

        uploadDir = new File(uploadDir + dateFormat.format(new Date()));

        if (!uploadDir.exists()) {
            uploadDir.mkdirs();
        }

        String fileName = file.getOriginalFilename();
        String[] listP = fileName.split("\\.");

        fileName = UUID.randomUUID() + "." + listP[listP.length - 1];

        try {
            file.transferTo(new File(uploadDir, fileName));
        } catch (Exception ex) {
            ex.printStackTrace();
        }

        List<String> list = new ArrayList<>();

        list.add(dateFormat.format(new Date()) + fileName);

        return list;
    }

    @GetMapping("/file/display/{year}/{month}/{day}/{fileName}")
    public ResponseEntity<?> displayFile(@PathVariable String fileName, @PathVariable String year, @PathVariable String month, @PathVariable String day) {
        File file = new File(localPath + year + "/" + month + "/" + day + "/", fileName);

        if (!file.exists()) {
            return null;
        }

        try {
            byte[] imageData = Files.readAllBytes(file.toPath());
            String meta = "image/png";
            return ResponseEntity.ok().contentType(MediaType.valueOf(meta)).body(imageData);
        } catch (Exception ex) {
            ex.printStackTrace();

            return null;
        }

    }
}
