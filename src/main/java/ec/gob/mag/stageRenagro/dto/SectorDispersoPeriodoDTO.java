package ec.gob.mag.stageRenagro.dto;

import java.io.Serializable;
import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
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
@Setter
@Getter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Builder

@Entity
public class SectorDispersoPeriodoDTO implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "sedp_id")
	private Integer sedpId;

	@Column(name = "sedp_estado")
	private Integer sedpEstado;

	@Column(name = "sedp_estado_avance")
	private Integer sedpEstadoAvance;

	@Column(name = "sedp_insert")
	private Timestamp sedpInsert;

	@ManyToOne
	@JoinColumn(name = "secd_id")
	@JsonIgnore
	private SectorDispersoDTO sectorDisperso;

	@Transient
	private Long secdId;

	@Transient
	private Integer bolpId;

	@Transient
	private SectorDispersoDTO sectorDispersoT;

}