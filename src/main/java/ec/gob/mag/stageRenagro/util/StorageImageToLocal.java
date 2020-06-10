package ec.gob.mag.stageRenagro.util;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Date;

import javax.xml.bind.DatatypeConverter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import ec.gob.mag.stageRenagro.dto.ImagesLocalProperties;

@Service("storageImageToLocal")
public class StorageImageToLocal {
	@Autowired
	@Qualifier("getResourcesPath")
	private GetResourcesPath getResourcesPath;

	@Value("${param.tmpFolder}")
	private String pathFolderImage;

	public ImagesLocalProperties saveImagesToLocal(String base64String, String nameImage, String variable)
			throws IOException, NoSuchAlgorithmException {
		ImagesLocalProperties imagesLocalProperties = new ImagesLocalProperties();
		String[] strings = base64String.split(",");
		String extension;

		boolean contieneImage = base64String.contains("data:image");
		if (contieneImage) {
			switch (strings[0]) {
			case "data:image/jpeg;base64":
				extension = "jpeg";
				break;
			case "data:image/png;base64":
				extension = "png";
				break;
			case "data:image/bmp;base64":
				extension = "bmp";
				break;
			default:
				extension = "jpg";
				break;
			}
			byte[] data = DatatypeConverter.parseBase64Binary(strings[1]);
			String realNameImage = nameImage + "_" + generateRestoNameImage() + "." + extension;
			System.out.println("PASO 1-------------");
			String path = getResourcesPath.createTempFile(pathFolderImage) + realNameImage;
			System.out.println("--------------->PATH " + path);
			imagesLocalProperties.setPathLocalImage(path);
			imagesLocalProperties.setRealNameImage(realNameImage);
			imagesLocalProperties.setVariable(variable);
			File file = new File(path);
			try (OutputStream outputStream = new BufferedOutputStream(new FileOutputStream(file))) {
				outputStream.write(data);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return imagesLocalProperties;
	}

	// GENERAR RESTO DEL NOMBRE PARA LA IMAGEN
	public String generateRestoNameImage() throws NoSuchAlgorithmException {
		System.out.println("PASO GENERATERESTONAME-------------");
		String random = new Date() + "" + System.currentTimeMillis();
		String randomName = getMd5(random);
		return randomName;
	}

	public String getMd5(String input) {
		try {
			System.out.println("PASO GET MD5-------------");
			MessageDigest md = MessageDigest.getInstance("MD5");
			byte[] messageDigest = md.digest(input.getBytes());
			BigInteger no = new BigInteger(1, messageDigest);
			String hashtext = no.toString(16);
			while (hashtext.length() < 32) {
				hashtext = "0" + hashtext;
			}
			return hashtext;
		} catch (NoSuchAlgorithmException e) {
			throw new RuntimeException(e);
		}
	}
}
