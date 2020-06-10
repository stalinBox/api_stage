package ec.gob.mag.stageRenagro.dto;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
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
public class PerfilTipoDTO implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "tpef_id", unique = true)
	@ApiModelProperty(value = "Id del perfilTipo", position = 1)
	@JsonProperty("tpefId")
	private Long id;

	@Column(name = "tpef_nombre")
	@ApiModelProperty(value = "Nombre de perfil tipo")
	@JsonProperty("tpefNombre")
	@JsonInclude(Include.NON_NULL)
	private String tpefNombre;

	@Column(name = "tpef_descripcion")
	@ApiModelProperty(value = "tpef Descripcion")
	@JsonProperty("tpefDescripcion")
	@JsonInclude(Include.NON_NULL)
	private String tpefDescripcion;

	@Column(name = "tpef_estado")
	@ApiModelProperty(value = "Estado de la tabla", example = "11")
	@JsonProperty("tpefEstado")
	@JsonInclude(Include.NON_NULL)
	private Integer tpefEstado;

	@Column(name = "tpef_eliminado")
	@ApiModelProperty(value = "Eliminado lógico de la tupla", example = "false")
	@JsonProperty("tpefEliminado")
	@JsonInclude(Include.NON_NULL)
	private Boolean tpefEliminado;

	@Column(name = "tpef_reg_usu")
	@ApiModelProperty(value = "Tpef registro de usuario", example = "none")
	@JsonProperty("tpefRegUsu")
	@JsonInclude(Include.NON_NULL)
	private Integer tpefRegUsu;

	@CreationTimestamp
	@Column(name = "tpef_reg_fecha", updatable = false)
	@Temporal(TemporalType.TIMESTAMP)
	@ApiModelProperty(value = " Fecha de registro de la tupla", notes = "***", position = 5)
	@JsonProperty("tpefRegFecha")
	@JsonInclude(Include.NON_NULL)
	private Date tpefRegFecha;

	@Column(name = "tpef_act_usu")
	@ApiModelProperty(value = "Tpef registro de usuario", example = "none")
	@JsonProperty("tpefActUsu")
	@JsonInclude(Include.NON_NULL)
	private Integer tpefActUsu;

	@CreationTimestamp
	@Column(name = "tpef_act_fecha", updatable = false)
	@Temporal(TemporalType.TIMESTAMP)
	@ApiModelProperty(value = " Fecha de registro de la tupla", notes = "***", position = 5)
	@JsonProperty("tpefActFecha")
	@JsonInclude(Include.NON_NULL)
	private Date tpefActFecha;
}
