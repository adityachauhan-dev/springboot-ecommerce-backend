package in.main.service;

import org.springframework.web.multipart.MultipartFile;

public interface FileStorageServiceImpl {

	String uploadFile(MultipartFile file);
	
}