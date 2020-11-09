package ec.gob.mag.stageRenagro.dto;

import java.util.List;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ResponseDTO {

	@ApiModelProperty(value = "Id de la tabla persona")
	private Long perId;

	@ApiModelProperty(value = "La cedula de identificacion de la tabla persona")
	private String perIdentificacion;

	@ApiModelProperty(value = "El nombre de la persona")
	private String perNombres;

	@ApiModelProperty(value = "El celular de la tabla persona")
	private String perCelular;

	@ApiModelProperty(value = "Nombre del perfil del usuario")
	private String tpefNombre;

	@ApiModelProperty(value = "Id del usuario")
	private Long usuId;

	@ApiModelProperty(value = "Id de la tabla usuario_persona")
	private Long upefId;

//	@ApiModelProperty(value = "Id de la tabla brigada_sector")
//	private Long brisId;

	@ApiModelProperty(value = "Id de la tabla brigada")
	private Long briId;

	@ApiModelProperty(value = "Id de la tabla boleta_periodo")
	private Long bolpId;

	@ApiModelProperty(value = "Entidad Sector Disperso")
	private List<SectorDispersoDTO> sectorDisperso;
}
