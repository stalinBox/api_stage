package ec.gob.mag.stageRenagro.controller;

import java.io.IOException;
import java.io.Serializable;
import java.util.List;
import java.util.stream.Collectors;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;

import ec.gob.mag.stageRenagro.dto.BrigadaDTO;
import ec.gob.mag.stageRenagro.dto.BrigadaSectorDTO;
import ec.gob.mag.stageRenagro.dto.IntegranteBrigadaDTO;
import ec.gob.mag.stageRenagro.dto.PerfilDTO;
import ec.gob.mag.stageRenagro.dto.PersonaDTO;
import ec.gob.mag.stageRenagro.dto.ResponseDTO;
import ec.gob.mag.stageRenagro.dto.ResponseSaveRenagroDTO;
import ec.gob.mag.stageRenagro.dto.SectorDispersoDTO;
import ec.gob.mag.stageRenagro.dto.SectorDispersoPeriodoDTO;
import ec.gob.mag.stageRenagro.dto.UsuarioDTO;
import ec.gob.mag.stageRenagro.dto.UsuarioPerfilDTO;
import ec.gob.mag.stageRenagro.dto.UsuarioPersonaDTO;
import ec.gob.mag.stageRenagro.util.Consumer;
import ec.gob.mag.stageRenagro.util.ConvertEntityUtil;
import ec.gob.mag.stageRenagro.util.GestionarJsonData;
import ec.gob.mag.stageRenagro.util.GetValueKeyJsonObject;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponses;

import org.json.JSONObject;
import io.swagger.annotations.ApiResponse;

@RestController
@RequestMapping("/api/stageRenagro")
@Api(value = "STAGE RENAGRO", description = "API REST STAGE RENAGRO", tags = "STAGE RENAGRO MOVIL")
@ApiResponses(value = { @ApiResponse(code = 200, message = "true"),
		@ApiResponse(code = 404, message = "The resource not found") })
public class StageMovilController implements Serializable, ErrorController {

	private static final long serialVersionUID = 1L;
	private static final String PATH = "/error";
	/**
	 * INSTANCIACION DE CLASES
	 */
	@Autowired
	@Qualifier("consumer")
	private Consumer consumer;

	@Autowired
	@Qualifier("getValueKeyJsonObject")
	private GetValueKeyJsonObject getValueKeyJsonObject;

	@Autowired
	@Qualifier("gestionarJsonData")
	private GestionarJsonData gestionarJsonData;

	@Autowired
	@Qualifier("convertEntityUtil")
	private ConvertEntityUtil convertEntityUtil;

	/**
	 * REFERENCIAS A LOS MICROSERVICIOS
	 */
	@Value("${url.servidor_micro}")
	private String urlServidor;

	@Value("${url.renagroProcesamiento}")
	private String urlProcesamiento;

	@Value("${url.renagro}")
	private String urlMicroRenagro;

	@Value("${url.stageRenagro}")
	private String urlMicroStageRenagro;

	@Value("${url.persona}")
	private String urlMicroPersona;

	@Value("${url.seguridades}")
	private String urlMicroSeguridades;

	@Value("${url.ubicacion}")
	private String urlMicroUbicacion;

	/**
	 * VARIABLE PRIVADA PARA SETTEAR EL PATH
	 */
	private String pathMicro;

	@RequestMapping(value = "/findAllUbicacionesEcuador/", method = RequestMethod.GET)
	@ApiOperation(value = "Guardar los datos del movil de renagro", response = String.class)
	public Object getUbicacion(@RequestHeader(name = "Authorization") String token) {
		pathMicro = urlServidor + urlMicroUbicacion + "api/ubicacion/findAllUbicacionesEcuador";
		System.out.println("--> " + pathMicro);
		Object responseUbicacion = consumer.doGet(pathMicro, token);
		return responseUbicacion;
	}

	@SuppressWarnings("finally")
	@RequestMapping(value = "/saveData/", produces = { "application/json; charset=utf-8" }, method = RequestMethod.POST)
	@ApiOperation(value = "Guardar los datos del movil de la app de renagro", response = String.class)
	@Transactional
	public Object SaveDataMovil(@RequestBody String data, @RequestHeader(name = "Authorization") String token)
			throws Throwable {
		String staExcepcion = null;
		Long staEstadoProcesamiento = null;
		JSONObject JsonData = new JSONObject(data);
		String jsonData = getValueKeyJsonObject.checkKey(JsonData, "staBoleta").toString();

		JSONObject paramJsonData = new JSONObject(jsonData);
		String paramImg1 = getValueKeyJsonObject.checkKey(JsonData, "varImage1").toString();
		String paramImg2 = getValueKeyJsonObject.checkKey(JsonData, "varImage2").toString();
		String paramImg3 = getValueKeyJsonObject.checkKey(JsonData, "varImage3").toString();
		String paramDNI = getValueKeyJsonObject.checkKey(JsonData, "dni").toString();
		String id = getValueKeyJsonObject.checkKey(JsonData, "id").toString();

		JSONObject dataJson = null;
		try {
			dataJson = gestionarJsonData.gestionarJsonData(paramJsonData, paramImg1, paramImg2, paramImg3, paramDNI);
		} catch (Exception e) {
			dataJson = JsonData;
			staExcepcion = e.getMessage();
			staEstadoProcesamiento = (long) 4;
			e.printStackTrace();
		}

		JsonData.remove("staBoleta");
		JsonData.put("staBoleta", dataJson.toString());
		JsonData.put("staFechInicio", getValueKeyJsonObject.checkKey(JsonData, "fechaInicio"));
		JsonData.put("staFechFin", getValueKeyJsonObject.checkKey(JsonData, "fechaFin"));
		JsonData.put("staIdMovil", id);
		JsonData.put("staExcepcion", staExcepcion);
		JsonData.put("staEstadoProcesamiento", staEstadoProcesamiento);

		pathMicro = null;
		pathMicro = urlServidor + urlMicroStageRenagro + "stage/create/";
		ResponseSaveRenagroDTO responseDTO = convertEntityUtil.ConvertSingleEntityPOST(pathMicro, JsonData.toString(),
				token, ResponseSaveRenagroDTO.class);

		pathMicro = null;
		pathMicro = urlProcesamiento + "renagroprocesadatosmovil/procesaDatosMovil/" + responseDTO.getId();
		System.out.println("====> staEstadoProcesamiento<==== :" + staEstadoProcesamiento);

		if (staEstadoProcesamiento != null) {
			return responseDTO;
		} else {
			// ENVIAR AL BACKGROUND PHP
			try {
				consumer.doGet(pathMicro, "");
			} finally {
				return responseDTO;
			}
		}

	}

	@SuppressWarnings("unchecked")
	@RequestMapping(value = "/getData/{perId}/{apliId}", method = RequestMethod.GET)
	@ApiOperation(value = "Busca todas los registros activos de la tabla appmovil", response = Object.class)
	@ResponseStatus(HttpStatus.OK)
	public ResponseEntity<?> getDataMovil(@Validated @PathVariable Long perId, @PathVariable Long apliId,
			@RequestHeader(name = "Authorization") String auth) throws JsonParseException, JsonMappingException,
			IOException, NoSuchFieldException, SecurityException, IllegalArgumentException, IllegalAccessException {
		ResponseDTO responseDTO = new ResponseDTO();
		/**
		 * **********************************************************************
		 * ************************** SC_BDC ************************************
		 */
		// ----------------
		pathMicro = null;
		pathMicro = urlServidor + urlMicroPersona + "persona/findById/" + perId;
		PersonaDTO personaDTO = convertEntityUtil.ConvertSingleEntityGET(pathMicro, auth, PersonaDTO.class);
		// ----------------
		pathMicro = null;
		pathMicro = urlServidor + urlMicroSeguridades + "api/usuarioPersona/findByPerId/" + perId;
		UsuarioPersonaDTO usuarioPersonaDTO = convertEntityUtil.ConvertSingleEntityGET(pathMicro, auth,
				UsuarioPersonaDTO.class);

		// ----------------
		pathMicro = null;
		pathMicro = urlServidor + urlMicroSeguridades + "api/usuario/findByUsupIdAndApliId/" + usuarioPersonaDTO.getId()
				+ "/" + apliId;
		List<UsuarioDTO> usuarioDTO = (List<UsuarioDTO>) convertEntityUtil.ConvertListEntity(pathMicro, auth,
				UsuarioDTO.class);

		// ----------------
		pathMicro = null;
		pathMicro = urlServidor + urlMicroSeguridades + "usuarioperfil/findByUsupId/" + usuarioDTO.get(0).getId() + "/"
				+ apliId;
		List<UsuarioPerfilDTO> usuarioPerfilDTO = (List<UsuarioPerfilDTO>) convertEntityUtil
				.ConvertListEntity(pathMicro, auth, UsuarioPerfilDTO.class);

		PerfilDTO perfilDTO = null;
		for (UsuarioPerfilDTO i : usuarioPerfilDTO) {
			try {
				pathMicro = null;
				pathMicro = urlServidor + urlMicroSeguridades + "perfil/findById/" + i.getPefId();
				perfilDTO = convertEntityUtil.ConvertSingleEntityGET(pathMicro, auth, PerfilDTO.class);
				if (perfilDTO.getPerfilTipo().getId() == 29) {
					break;
				}
			} catch (NoSuchFieldException | SecurityException | IllegalArgumentException | IllegalAccessException
					| IOException e) {
				e.printStackTrace();
			}
		}

		/**
		 * **********************************************************************
		 * **************************SC_RENAGRO *********************************
		 */
		pathMicro = null;
		pathMicro = urlServidor + urlMicroRenagro + "integranteBrigada/findByUsupId/" + usuarioPersonaDTO.getId();
		List<IntegranteBrigadaDTO> integranteBrigadaDTO = (List<IntegranteBrigadaDTO>) convertEntityUtil
				.ConvertListEntity(pathMicro, auth, IntegranteBrigadaDTO.class);
		// ----------------
		pathMicro = null;
		pathMicro = urlServidor + urlMicroRenagro + "brigada/findById/" + integranteBrigadaDTO.get(0).getBriId();
		BrigadaDTO brigadaDTO = convertEntityUtil.ConvertSingleEntityGET(pathMicro, auth, BrigadaDTO.class);
		// ---------------
		pathMicro = null;
		pathMicro = urlServidor + urlMicroRenagro + "brigadaSector/findByBriId/"
				+ integranteBrigadaDTO.get(0).getBriId();
		List<BrigadaSectorDTO> brigadaSectorDTO = (List<BrigadaSectorDTO>) convertEntityUtil
				.ConvertListEntity(pathMicro, auth, BrigadaSectorDTO.class);
		List<SectorDispersoDTO> sectorDispersoDTOResponse = brigadaSectorDTO.stream().map(mpr -> {
			SectorDispersoDTO sectorDispersoDTO = new SectorDispersoDTO();
			try {
				// ---------------
				pathMicro = null;
				pathMicro = urlServidor + urlMicroRenagro + "sectorDispersoPeriodo/findById/" + mpr.getSedpId();
				SectorDispersoPeriodoDTO sectorDispersoPeriodoDTO = convertEntityUtil.ConvertSingleEntityGET(pathMicro,
						auth, SectorDispersoPeriodoDTO.class);
				// ---------------
				pathMicro = null;
				pathMicro = urlServidor + urlMicroRenagro + "sectordisperso/findBySecdId/"
						+ sectorDispersoPeriodoDTO.getSecdId();
				sectorDispersoDTO = convertEntityUtil.ConvertSingleEntityGET(pathMicro, auth, SectorDispersoDTO.class);
				sectorDispersoDTO.setSecdpId(Long.parseLong(mpr.getSedpId().toString()));
				sectorDispersoDTO.setBrisId(mpr.getBrisId());
			} catch (IOException | NoSuchFieldException | SecurityException | IllegalArgumentException
					| IllegalAccessException e) {
				e.printStackTrace();
			}

			return sectorDispersoDTO;
		}).collect(Collectors.toList());

		// FINAL RESPONSE
		responseDTO.setPerId(personaDTO.getId());
		responseDTO.setPerIdentificacion(personaDTO.getPerIdentificacion());
		responseDTO.setPerNombres(personaDTO.getPerNombres());
		responseDTO.setPerCelular(personaDTO.getPerCelular());
		responseDTO.setUsuId(usuarioPerfilDTO.get(0).getUsuario().getId());
		responseDTO.setUpefId(usuarioPerfilDTO.get(0).getId());
		responseDTO.setTpefNombre(perfilDTO.getPerfilTipo().getTpefNombre());
		responseDTO.setSectorDisperso(sectorDispersoDTOResponse);
		responseDTO.setBriId(Long.parseLong(integranteBrigadaDTO.get(0).getBriId().toString()));
		responseDTO.setBolpId(Long.parseLong(brigadaDTO.getBolpId().toString()));
		return ResponseEntity.ok(responseDTO);
	}

	@RequestMapping(value = "/verificarConexion", method = RequestMethod.GET)
	@ApiOperation(value = "Servicion de test", response = Boolean.class)
	public boolean testConnetion(@RequestHeader(name = "Authorization") String token) {
		return true;
	}

	@Override
	public String getErrorPath() {
		return PATH;
	}
}
