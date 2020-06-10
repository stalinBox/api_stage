package ec.gob.mag.stageRenagro.dto;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ResponseDTO {

	private Long perId;
	private String perIdentificacion;
	private String perNombres;
	private String perCelular;
	private String tpefNombre;
	private Long usuId;
	private Long upefId;

	private Long brisId;

	// Boleta Periodo
	private Long bolpId;

	private List<SectorDispersoDTO> sectorDisperso;
}
