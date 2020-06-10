package ec.gob.mag.stageRenagro.dto;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
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
public class UsuarioDTO implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "usu_id", unique = true)
	@ApiModelProperty(value = "Id del usuario", position = 1)
	@JsonProperty("usuId")
	private Long id;

	@Column(name = "peti_id")
	@ApiModelProperty(value = "Peti Id", notes = "***", position = 3)
	@JsonProperty("petiId")
	@JsonInclude(Include.NON_NULL)
	private Long petiId;

	@CreationTimestamp
	@Column(name = "usu_expira", updatable = false)
	@Temporal(TemporalType.TIMESTAMP)
	@ApiModelProperty(value = "Usu Expira", notes = "***", position = 4)
	@JsonProperty("usuExpira")
	@JsonInclude(Include.NON_NULL)
	private Date usuExpira;

	@Column(name = "usu_estado")
	@ApiModelProperty(value = "Usu Estado", notes = "***", position = 5)
	@JsonProperty("usuEstado")
	@JsonInclude(Include.NON_NULL)
	private Long usuEstado;

	@Column(name = "usu_eliminado")
	@ApiModelProperty(value = "Usu eliminado", notes = "***", position = 6)
	@JsonProperty("usuEliminado")
	@JsonInclude(Include.NON_NULL)
	private Boolean usuEliminado;

	@Column(name = "usu_reg_usu")
	@ApiModelProperty(value = "Usu Reg Usu", notes = "***", position = 7)
	@JsonProperty("usuRegUsu")
	@JsonInclude(Include.NON_NULL)
	private Long usuRegUsu;

	@Column(name = "usu_reg_fecha", updatable = false)
	@Temporal(TemporalType.TIMESTAMP)
	@ApiModelProperty(value = "Usu Reg Fecha", notes = "***", position = 8)
	@JsonProperty("usuRegFecha")
	@JsonInclude(Include.NON_NULL)
	private Date usuRegFecha;

	@Column(name = "usu_act_usu")
	@ApiModelProperty(value = "Usu Act Usu", notes = "***", position = 9)
	@JsonProperty("usuActUsu")
	@JsonInclude(Include.NON_NULL)
	private Long usuActUsu;

	@Column(name = "usu_act_fecha", updatable = false)
	@Temporal(TemporalType.TIMESTAMP)
	@ApiModelProperty(value = "Usu Act Fecha", notes = "***", position = 10)
	@JsonProperty("usuActFecha")
	@JsonInclude(Include.NON_NULL)
	private Date usuActFecha;

	@Column(name = "usu_fecha_activacion", updatable = false)
	@Temporal(TemporalType.TIMESTAMP)
	@ApiModelProperty(value = "Usu Fecha Activacion", notes = "***", position = 11)
	@JsonProperty("usuFechaActivacion")
	@JsonInclude(Include.NON_NULL)
	private Date usuFechaActivacion;

	@Column(name = "usu_acepta_term")
	@ApiModelProperty(value = "Usu Acepta Terminos", notes = "***", position = 12)
	@JsonProperty("usuAceptaTerm")
	@JsonInclude(Include.NON_NULL)
	private Boolean usuAceptaTerm;

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "usuario")
	@ApiModelProperty(value = "Usuarios Perfil", notes = "***", position = 14)
	@JsonProperty("usuariosperfil")
	@JsonInclude(Include.NON_NULL)
	private List<UsuarioPerfilDTO> usuariosperfil;

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "usup_id")
	@ApiModelProperty(value = "Usuario Persona", notes = "***", position = 13)
	@JsonProperty("usupId")
	@JsonInclude(Include.NON_NULL)
	private UsuarioPersonaDTO usuariopersona;
}
