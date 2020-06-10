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
import javax.persistence.Transient;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Data
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Setter
@Getter
@Entity

public class BrigadaDTO implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "bri_id")
	private Long briId;

	@Column(name = "bri_codigo")
	private String briCodigo;

	@Column(name = "bri_descripcion")
	private String briDescripcion;

	@Column(name = "bri_estado")
	private Integer briEstado;

	@Column(name = "bri_insert")
	private Timestamp briInsert;

	@Column(name = "usup_id_insert")
	private Integer usupIdInsert;

	@Column(name = "bri_update")
	private Timestamp briUpdate;

	@Column(name = "usup_id_update")
	private Integer usupIdUpdate;

	@Column(name = "bri_vehiculo_asignado")
	private Integer briVehiculoAsignado;

	@Column(name = "bri_vehiculo_estado")
	private Integer briVehiculoEstado;

	@Column(name = "bri_completa")
	private Integer briCompleta;

	// bi-directional many-to-one association to IntegranteBrigada
	@OneToMany(mappedBy = "brigada")
	@JsonIgnore
	private List<IntegranteBrigadaDTO> integranteBrigadas;

	@Transient
	private Integer bolpId;

}