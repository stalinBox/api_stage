package ec.gob.mag.stageRenagro.dto;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@ToString
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
@Setter
@Entity
public class SectorDispersoDTO implements Serializable {
	private static final long serialVersionUID = 1L;

	@ApiModelProperty(value = "Id de la tabla sector_disperso")
	@Column(name = "secd_id")
	private Long secdId;

	@ApiModelProperty(value = "Id de la tabla brigada_sector")
	private Long brisId;

	@ApiModelProperty(value = "Id de la tabla sector_disperso_periodo")
	@Column(name = "sedp_id")
	private Long secdpId;

	@ApiModelProperty(value = "codigo INEC de la zona")
	@Column(name = "cod_zon_inec")
	private String codZonInec;

	@ApiModelProperty(value = "id del canton")
	@Column(name = "ubi_id_canton")
	private Integer ubiIdCanton;

	@ApiModelProperty(value = "Id de la parroquia")
	@Column(name = "ubi_id_parroquia")
	private Integer ubiIdParroquia;

	@ApiModelProperty(value = "Id de la provincia")
	@Column(name = "ubi_id_provincia")
	private Integer ubiIdProvincia;

	@ApiModelProperty(value = "Codigo INEC del canton")
	@Column(name = "ubi_inec_canton")
	private String ubiInecCanton;

	@ApiModelProperty(value = "Codigo INEC de la parroquia")
	@Column(name = "ubi_inec_parroquia")
	private String ubiInecParroquia;

	@ApiModelProperty(value = "Codigo INEC de la provincia")
	@Column(name = "ubi_inec_provincia")
	private String ubiInecProvincia;

	@ApiModelProperty(value = "Codigo INEC del sector disperso")
	@Column(name = "ubi_inec_sector_disperso")
	private String ubiInecSectorDisperso;

	@ApiModelProperty(value = "Nombre del canton")
	@Column(name = "ubi_nombre_canton")
	private String ubiNombreCanton;

	@ApiModelProperty(value = "Nombre de la parroquia")
	@Column(name = "ubi_nombre_parroquia")
	private String ubiNombreParroquia;

	@ApiModelProperty(value = "Nombre de la provincia")
	@Column(name = "ubi_nombre_provincia")
	private String ubiNombreProvincia;

	@ApiModelProperty(value = "Fecha timestamp")
	@Column(name = "ubi_time_stamp")
	private Timestamp ubiTimeStamp;

	@OneToMany(mappedBy = "Entidad Sector Disperso")
	@JsonInclude(Include.NON_NULL)
	private List<SectorDispersoPeriodoDTO> sectorDispersoPeriodos;
}