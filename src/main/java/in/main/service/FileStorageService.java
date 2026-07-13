package in.main.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.*;
import java.util.UUID;

@Service
public class FileStorageService implements FileStorageServiceImpl {


	    @Value("${file.upload-dir}")
	    private String uploadDir;

	    @Override
	    public String uploadFile(MultipartFile file) {

	        try {

	            Path path = Paths.get(uploadDir);

	            if (!Files.exists(path)) {
	                Files.createDirectories(path);
	            }

	            String fileName =
	                    UUID.randomUUID() + "_" + file.getOriginalFilename();

	            Files.copy(
	                    file.getInputStream(),
	                    path.resolve(fileName),
	                    StandardCopyOption.REPLACE_EXISTING
	            );

	            return fileName;

	        } catch (IOException e) {
	            throw new RuntimeException("File upload failed");
	        }
	    }
	}
