package test;

import java.io.IOException;
import java.nio.file.FileVisitResult;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.SimpleFileVisitor;
import java.nio.file.attribute.BasicFileAttributes;

import org.junit.Assert;
import org.junit.Ignore;
import org.junit.Test;

public class _DeleteDatabaseFiles {

	private static final Path directoryPath = Paths.get("db/h2");

	@Ignore
	@Test
	public void deleteH2Database() {

		Assert.assertTrue(deleteDirectoryWithFiles(directoryPath));
	}

	private boolean deleteDirectoryWithFiles(Path directoryPath) {

		try {
			Files.walkFileTree(directoryPath, new SimpleFileVisitor<Path>() {
				@Override
				public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) throws IOException {
					// System.out.println("delete file: " + file.toString());
					Files.delete(file);
					return FileVisitResult.CONTINUE;
				}

				@Override
				public FileVisitResult postVisitDirectory(Path dir, IOException exc) throws IOException {
					Files.delete(dir);
					// System.out.println("delete dir: " + dir.toString());
					return FileVisitResult.CONTINUE;
				}
			});
			return true;
		} catch (IOException e) {
			e.printStackTrace();
			return false;
		}
	}
}
