package ec.gob.mag.stageRenagro.util;

import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import ec.gob.mag.stageRenagro.dto.ImagesJson;
import ec.gob.mag.stageRenagro.dto.ImagesLocalProperties;
import ec.gob.mag.stageRenagro.dto.ImagesProperties;

@Service("gestionarJsonData")
public class GestionarJsonData {

	@Autowired
	@Qualifier("getValueKeyJsonObject")
	private GetValueKeyJsonObject getValueKeyJsonObject;

	@Autowired
	@Qualifier("storageImageToLocal")
	private StorageImageToLocal storageImageToLocal;

	@Autowired
	@Qualifier("sendImageToAlfresco")
	private SendImageToAlfresco sendImageToAlfresco;

	@Autowired
	@Qualifier("deleteImagesTmp")
	private DeleteImagesTmp deleteImagesTmp;

	@Async
	public JsonDataWithStatus gestionarJsonData(JSONObject JsonData, String paramImage1, String paramImage2,
			String paramImage3, String ciEncuestador) {
		List<ImagesJson> imagesJson = new ArrayList<ImagesJson>();
		List<ImagesLocalProperties> imgProperties = new ArrayList<ImagesLocalProperties>();

		imagesJson.add(new ImagesJson(JsonData.getJSONObject("formA").get(paramImage1).toString(), paramImage1));
		imagesJson.add(new ImagesJson(JsonData.getJSONObject("formA").get(paramImage2).toString(), paramImage2));
		imagesJson.add(new ImagesJson(JsonData.getJSONObject("formC").get(paramImage3).toString(), paramImage3));

		imagesJson.stream().filter(mprImg -> mprImg.getBase64().contains("data:image")).map(mprImgJson -> {
			ImagesLocalProperties imagesLocalProperties = new ImagesLocalProperties();
			try {
				imagesLocalProperties = storageImageToLocal.saveImagesToLocal(mprImgJson.getBase64(), ciEncuestador,
						mprImgJson.getVariable());
			} catch (IOException | NoSuchAlgorithmException e) {
				e.printStackTrace();
			}

			imgProperties.add(imagesLocalProperties);
			return mprImgJson;
		}).collect(Collectors.toList());

		if (!imgProperties.isEmpty()) {
			for (ImagesLocalProperties i : imgProperties) {

				ImagesProperties imgProp = sendImageToAlfresco.saveImageToAlfresco(i.getPathLocalImage(),
						i.getRealNameImage(), i.getVariable());

				if (imgProp.getStatus() == null) {
					if (imgProp.getVariable().equals(paramImage1) || imgProp.getVariable().equals(paramImage2)) {
						JsonData.getJSONObject("formA").remove(i.getVariable());
						JsonData.getJSONObject("formA").put(imgProp.getVariable(),
								imgProp.getNameFolder() + "/" + imgProp.getNameFile());
					} else {
						JsonData.getJSONObject("formC").remove(i.getVariable());
						JsonData.getJSONObject("formC").put(imgProp.getVariable(),
								imgProp.getNameFolder() + "/" + imgProp.getNameFile());
					}
					deleteImagesTmp.deleteImagesLocalTmp(i.getPathLocalImage());

				} else {
					JsonDataWithStatus jdStatus = new JsonDataWithStatus();
					jdStatus.setJsonData(JsonData);
					jdStatus.setStatus(imgProp.getStatus());
					return jdStatus;
				}

			}
		}
		JsonDataWithStatus jdStatus = new JsonDataWithStatus();
		jdStatus.setJsonData(JsonData);
		jdStatus.setStatus(null);
		return jdStatus;
	}

}
