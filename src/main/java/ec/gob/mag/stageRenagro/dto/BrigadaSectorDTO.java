package ec.gob.mag.stageRenagro.dto;

import java.io.Serializable;
import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Transient;

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
public class BrigadaSectorDTO implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "bris_id")
	private Long brisId;

	@Column(name = "bri_id")
	private Integer briId;

	@Column(name = "bris_insert")
	private Timestamp brisInsert;

	@Column(name = "bris_update")
	private Timestamp brisUpdate;

	@Column(name = "usup_id_insert")
	private Integer usupIdInsert;

	@Column(name = "usup_id_update")
	private Integer usupIdUpdate;

	@Column(name = "bris_estado")
	private Integer brisEstado;

	@Transient
	private Integer sedpId;

	@Transient
	private SectorDispersoPeriodoDTO sectorDispersoP;

	public BrigadaSectorDTO(Long brisId, Integer briId, Timestamp brisInsert, Timestamp brisUpdate,
			Integer usupIdInsert, Integer usupIdUpdate, Integer brisEstado) {
		super();
		this.brisId = brisId;
		this.briId = briId;
		this.brisInsert = brisInsert;
		this.brisUpdate = brisUpdate;
		this.usupIdInsert = usupIdInsert;
		this.usupIdUpdate = usupIdUpdate;
		this.brisEstado = brisEstado;
	}

}