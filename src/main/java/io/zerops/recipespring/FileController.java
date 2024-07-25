package io.zerops.recipespring;

import com.amazonaws.services.s3.AmazonS3;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RestController
public class FileController {
    private final Logger logger = LoggerFactory.getLogger(FileController.class);
    private final FileRepository fileRepository;
    private final AmazonS3 s3Client;
    private final S3Config s3Config;

    public FileController(
            FileRepository fileRepository,
            AmazonS3 s3Client,
            S3Config s3Config
    ) {
        this.fileRepository = fileRepository;
        this.s3Client = s3Client;
        this.s3Config = s3Config;
    }

    @PostMapping("/file")
    public File uploadFile(@RequestParam("file") MultipartFile file) throws IOException {
        var fileEntity = new File();
        fileEntity.name = file.getOriginalFilename();
        fileEntity.size = (int) file.getSize();
        fileEntity.path = "/to-update";
        fileEntity.type = file.getContentType();

        this.fileRepository.save(fileEntity);
        var id = fileEntity.id;
        this.logger.info(id.toString());

        var key = id + "-" + fileEntity.name;
        fileEntity.path = this.s3Config.createUrl(key);
        this.fileRepository.save(fileEntity);

        var response = this.s3Client.putObject(this.s3Config.bucketName, key, file.getInputStream(), null);
        this.logger.info(response.getMetadata().getETag());

        return fileEntity;
    }

    @GetMapping("/file")
    public Iterable<File> getFiles() {
        return this.fileRepository.findAll();
    }
}
