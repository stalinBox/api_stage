package ec.gob.mag.stageRenagro.dto;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

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

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "secd_id")
	private Long secdId;

//	@Transient
//	@Column(name = "sedp_id")
	private Long secdpId;

	@Column(name = "cod_zon_inec")
	private String codZonInec;

	@Column(name = "ubi_id_canton")
	private Integer ubiIdCanton;

	@Column(name = "ubi_id_parroquia")
	private Integer ubiIdParroquia;

	@Column(name = "ubi_id_provincia")
	private Integer ubiIdProvincia;

	@Column(name = "ubi_inec_canton")
	private String ubiInecCanton;

	@Column(name = "ubi_inec_parroquia")
	private String ubiInecParroquia;

	@Column(name = "ubi_inec_provincia")
	private String ubiInecProvincia;

	@Column(name = "ubi_inec_sector_disperso")
	private String ubiInecSectorDisperso;

	@Column(name = "ubi_nombre_canton")
	private String ubiNombreCanton;

	@Column(name = "ubi_nombre_parroquia")
	private String ubiNombreParroquia;

	@Column(name = "ubi_nombre_provincia")
	private String ubiNombreProvincia;

	@Column(name = "ubi_time_stamp")
	private Timestamp ubiTimeStamp;

	// bi-directional many-to-one association to SectorDispersoPeriodo
	@OneToMany(mappedBy = "sectorDisperso")
	@JsonInclude(Include.NON_NULL)
	private List<SectorDispersoPeriodoDTO> sectorDispersoPeriodos;
}