package ec.gob.mag.stageRenagro.util;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.HttpStatusCodeException;
import org.springframework.web.client.RestTemplate;

import ec.gob.mag.stageRenagro.dto.ImagesProperties;

@Service("sendImageToAlfresco")
public class SendImageToAlfresco {
	@Autowired
	@Qualifier("accesoAlfresco")
	private AccesoAlfresco accesoAlfresco;

	@Autowired
	@Qualifier("getValueKeyJsonObject")
	private GetValueKeyJsonObject getValueKeyJsonObject;

	@Autowired
	@Qualifier("getResourcesPath")
	private GetResourcesPath getResourcesPath;

	@Value("${url.alfresco}")
	private String urlAlfresco;

	@Value("${param.email}")
	private String email;

	@Value("${param.passwd}")
	private String passwd;

	@Value("${param.nameFolder}")
	private String nameFolder;

	@Value("${param.alfUser}")
	private String alfUser;

	@Value("${param.alfPass}")
	private String alfPass;

	@Autowired
	RestTemplate restTemplate;

	@Async
	public ImagesProperties saveImageToAlfresco(String pathLocalImage, String nameFile, String variable) {
		String responseLogin = accesoAlfresco.loginAlfresco(email, passwd, urlAlfresco + "api/login");
		ImagesProperties imgProperties = new ImagesProperties();

		if (responseLogin != null) {
			JSONObject JsonResponseLogin = new JSONObject(responseLogin);
			getValueKeyJsonObject.setFinalresult(null);
			Object tk = getValueKeyJsonObject.searchingJson(JsonResponseLogin, "token");
			getValueKeyJsonObject.setFinalresult(null);
			MultiValueMap<String, Object> bodyMap = new LinkedMultiValueMap<>();
			bodyMap.add("pathFile", getResourcesPath.getUserFileResource(pathLocalImage));
			bodyMap.add("token", tk.toString());
			bodyMap.add("nameFile", nameFile);
			bodyMap.add("nameFolder", nameFolder);
			bodyMap.add("alfUser", alfUser);
			bodyMap.add("alfPass", alfPass);

			HttpHeaders headers = new HttpHeaders();
			headers.setContentType(MediaType.MULTIPART_FORM_DATA);
			HttpEntity<MultiValueMap<String, Object>> requestEntity = new HttpEntity<>(bodyMap, headers);

//			assertTrue(bucket.tryConsume( ));
//			Executors.newScheduledThreadPool(1).schedule(() -> assertTrue(bucket.tryConsume(1)), 2, TimeUnit.SECONDS);

			ResponseEntity<String> response = null;
			try {
				response = restTemplate.exchange(urlAlfresco + "api/Pupload", HttpMethod.POST, requestEntity,
						String.class);
				System.out.println("=== CODIGO ===>: " + response.getStatusCode());
				if (response.getStatusCode() == HttpStatus.CREATED) {
					imgProperties.setNameFile(nameFile);
					imgProperties.setNameFolder(nameFolder);
					imgProperties.setVariable(variable);
					return imgProperties;
				}
			} catch (HttpStatusCodeException e) {
				imgProperties.setStatus(e.getStatusText());
				System.out.println("MESSAGE: " + e.getStatusText());
				System.out.println("=== CODIGO ERROR ===>: " + e.getStatusCode().value());
			}
			return imgProperties;

		} else {
			return null;
		}

	}
}
