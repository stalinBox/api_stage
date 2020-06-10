package ec.gob.mag.stageRenagro.dto;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.CreationTimestamp;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString(of = "id")
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
public class UsuarioPerfilDTO implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "upef_id", unique = true)
	@ApiModelProperty(value = "Id del usuario perfil", position = 1)
	@JsonProperty("upefId")
	private Long id;

	@Column(name = "pef_id")
	@ApiModelProperty(value = "Id del perfil", notes = "***", position = 2)
	@JsonProperty("pefId")
	@JsonInclude(Include.NON_NULL)
	private Long pefId;

	@Column(name = "apli_id")
	@ApiModelProperty(value = "Id de la Aplicacion", notes = "***", position = 3)
	@JsonProperty("apliId")
	@JsonInclude(Include.NON_NULL)
	private Long apliId;

	@Column(name = "upef_por_defecto")
	@ApiModelProperty(value = "Upef por Defecto", notes = "***", position = 5)
	@JsonProperty("upefPorDefecto")
	@JsonInclude(Include.NON_NULL)
	private Long upefPorDefecto;

	@Column(name = "upef_estado")
	@ApiModelProperty(value = "Upef estado", notes = "***", position = 6)
	@JsonProperty("upefEstado")
	@JsonInclude(Include.NON_NULL)
	private Long upefEstado;

	@Column(name = "upef_eliminado")
	@ApiModelProperty(value = "Upef eliminado", notes = "***", position = 8)
	@JsonProperty("upefEliminado")
	@JsonInclude(Include.NON_NULL)
	private Boolean upefEliminado;

	@Column(name = "upef_reg_usu")
	@ApiModelProperty(value = "Upef Registro Usuario", notes = "***", position = 9)
	@JsonProperty("upefRegUsu")
	@JsonInclude(Include.NON_NULL)
	private Long upefRegUsu;

	@CreationTimestamp
	@Column(name = "upef_reg_fecha", updatable = false)
	@Temporal(TemporalType.TIMESTAMP)
	@ApiModelProperty(value = "Upef Reg Fecha", notes = "***", position = 10)
	@JsonProperty("upefRegFecha")
	@JsonInclude(Include.NON_NULL)
	private Date upefRegFecha;

	@Column(name = "upef_act_usu")
	@ApiModelProperty(value = "Upef Act Usu", notes = "***", position = 11)
	@JsonProperty("upefActUsu")
	@JsonInclude(Include.NON_NULL)
	private Long upefActUsu;

	@CreationTimestamp
	@Column(name = "upef_act_fecha", updatable = false)
	@Temporal(TemporalType.TIMESTAMP)
	@ApiModelProperty(value = "Fecha upef act", notes = "***", position = 12)
	@JsonProperty("upefActFecha")
	@JsonInclude(Include.NON_NULL)
	private Date upefActFecha;

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "usu_id")
	@ApiModelProperty(value = "Usuario", notes = "***", position = 13)
	@JsonProperty("usuId")
	@JsonInclude(Include.NON_NULL)
	private UsuarioDTO usuario;

}
