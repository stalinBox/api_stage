package ec.gob.mag.stageRenagro.util;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;

@Service("getResourcesPath")
public class GetResourcesPath {
	public String getPathResources(String pathFolderImages) {
		File file = new File(getClass().getClassLoader().getResource(pathFolderImages).getFile());
		return file.getParent() + "//";
	}

	public Resource getUserFileResource(String pathLocalImage) throws IOException {
		File tempFile = new File(pathLocalImage);
		return new FileSystemResource(tempFile);
	}

	public String createTempFile(String pathFolderImages) throws IOException {
		Path path = Files.createTempDirectory("renagro-stage");
		File file = path.toFile();
		return file.getAbsolutePath() + "/";
	}
}
