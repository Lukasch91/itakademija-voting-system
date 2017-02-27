package vs.utils_.storage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;
import org.springframework.util.FileSystemUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.file.DirectoryNotEmptyException;
import java.nio.file.Files;
import java.nio.file.NoSuchFileException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.stream.Stream;

@Service
public class StorageServiceImpl implements StorageService {

	private final Path rootLocation;
	private String content;

	@Autowired
	public StorageServiceImpl(StorageProperties properties) {
		this.rootLocation = Paths.get(properties.getLocation());
	}

	@Override
	public void store(MultipartFile file) {
		try {
			if (file.isEmpty()) {
				throw new StorageException("Failed to store empty file " + file.getOriginalFilename());
			}
			Files.copy(file.getInputStream(), this.rootLocation.resolve(file.getOriginalFilename()));
		} catch (IOException e) {
			throw new StorageException("Failed to store file " + file.getOriginalFilename(), e);
		}
	}

	@Override
	public String returnStoredFile(MultipartFile file) {

		try (InputStream in = Files.newInputStream(this.rootLocation.resolve(file.getOriginalFilename()));
				BufferedReader reader = new BufferedReader(new InputStreamReader(in))) {

			String tempContent = null;
			content = "";

			while ((tempContent = reader.readLine()) != null) {
				this.content += (tempContent + "\n");
			}
		} catch (IOException x) {
			System.err.println("sagg-----------------------" + x);
		}

		return content;

	}

	@Override
	public void deleteFile(MultipartFile file) {
		try {
			Files.delete(this.rootLocation.resolve(file.getOriginalFilename()));
		} catch (NoSuchFileException x) {
			System.err.format("%s: no such" + " file or directory%n",
					this.rootLocation.resolve(file.getOriginalFilename()));
		} catch (DirectoryNotEmptyException x) {
			System.err.format("%s not empty%n", this.rootLocation.resolve(file.getOriginalFilename()));
		} catch (IOException x) {
			// File permission problems are caught here.
			System.err.println(x);
		}

	}

	@Override
	public Stream<Path> loadAll() {
		try {
			return Files.walk(this.rootLocation, 1).filter(path -> !path.equals(this.rootLocation))
					.map(path -> this.rootLocation.relativize(path));
		} catch (IOException e) {
			throw new StorageException("Failed to read stored files", e);
		}

	}

	@Override
	public Path load(String filename) {
		return rootLocation.resolve(filename);
	}

	@Override
	public Resource loadAsResource(String filename) {
		return null;
	}

	@Override
	public void deleteAll() {
		FileSystemUtils.deleteRecursively(rootLocation.toFile());
	}

	@Override
	public void init() {
		try {
			Files.createDirectory(rootLocation);
		} catch (IOException e) {
			throw new StorageException("Could not initialize storage", e);
		}
	}

}