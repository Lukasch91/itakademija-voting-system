package vs.admin.features.candidate.model.storage;

import org.springframework.core.io.Resource;
import org.springframework.web.multipart.MultipartFile;

import java.nio.file.Path;
import java.util.stream.Stream;

public interface StorageService {
	void init();

	void store(MultipartFile file);
	
	String returnStoredFile(MultipartFile file);
	
	void deleteFile(MultipartFile file);

	Stream<Path> loadAll();

	Path load(String filename);

	Resource loadAsResource(String filename);

	void deleteAll();

}
