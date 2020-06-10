package ec.gob.mag.stageRenagro.dto;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.CreationTimestamp;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString(of = "id")
@EqualsAndHashCode(of = "id")
@Entity
public class PerfilDTO implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "pef_id", unique = true)
	@ApiModelProperty(value = "Id del perfil", position = 1)
	@JsonProperty("pefId")
	private Long id;

	@Column(name = "apli_id")
	@ApiModelProperty(value = "FK de aplicacion", example = "11")
	@JsonProperty("apliId")
	@JsonInclude(Include.NON_NULL)
	private Long apliId;

	@Column(name = "pef_estado")
	@ApiModelProperty(value = "Estado de la tabla", example = "11")
	@JsonProperty("pefEstado")
	@JsonInclude(Include.NON_NULL)
	private Integer pefEstado;

	@Column(name = "pef_eliminado")
	@ApiModelProperty(value = "Eliminado lógico de la tupla", example = "false")
	@JsonProperty("pefEliminado")
	@JsonInclude(Include.NON_NULL)
	private Boolean pefEliminado;

	@Column(name = "pef_reg_usu")
	@ApiModelProperty(value = "Pef registro de usuario", example = "none")
	@JsonProperty("pefRegUsu")
	@JsonInclude(Include.NON_NULL)
	private Integer pefRegUsu;

	@CreationTimestamp
	@Column(name = "pef_reg_fecha", updatable = false)
	@Temporal(TemporalType.TIMESTAMP)
	@ApiModelProperty(value = " Fecha de registro de la tupla", notes = "***", position = 5)
	@JsonProperty("pefRegFecha")
	@JsonInclude(Include.NON_NULL)
	private Date pefRegFecha;

	@Column(name = "pef_act_usu")
	@ApiModelProperty(value = "Tpef registro de usuario", example = "none")
	@JsonProperty("pefActUsu")
	@JsonInclude(Include.NON_NULL)
	private Integer pefActUsu;

	@CreationTimestamp
	@Column(name = "pef_act_fecha", updatable = false)
	@Temporal(TemporalType.TIMESTAMP)
	@ApiModelProperty(value = " Fecha de registro de la tupla", notes = "***", position = 5)
	@JsonProperty("pefActFecha")
	@JsonInclude(Include.NON_NULL)
	private Date pefActFecha;

	/**
	 * RELACIONES UNIDIRECCIONAL JPA
	 */
	@ApiModelProperty(value = "Campo perfil Tipo")
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "tpef_id", referencedColumnName = "tpef_id")
	@JsonProperty("perfilTipo")
	@JsonInclude(Include.NON_NULL)
	private PerfilTipoDTO perfilTipo;
}
