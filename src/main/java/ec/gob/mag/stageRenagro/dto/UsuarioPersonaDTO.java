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
import javax.persistence.OneToMany;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
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

//============== LOMBOK =============
@Getter
@Setter
@ToString(of = "id")
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
public class UsuarioPersonaDTO implements Serializable {

	private static final long serialVersionUID = 1L;

	@ApiModelProperty(position = 1, value = "Este campo es  la clave primaria de la tabla Usuario_Persona", required = true, readOnly = true)
	@Id
	@Column(name = "usup_id", unique = true)
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@JsonProperty("usupId")
	private Long id;

	@ApiModelProperty(position = 2, value = "Este campo es  la clave primaria de la tabla Persona", required = true)
	@Column(name = "per_id")
	@JsonProperty("perId")
	@JsonInclude(Include.NON_NULL)
	private Long perId;

	@ApiModelProperty(position = 3, value = "Si la persona no esta en SIGAP no puede continuar", required = true)
	@Column(name = "usup_per_id_sigap")
	@JsonProperty("usupPerIdSigap")
	@JsonInclude(Include.NON_NULL)
	private Long usupPerIdSigap;

	@ApiModelProperty(position = 4, value = "Este campo almacena la informacion del Login Acceso o nombre de usuario", required = true)
	@Column(name = "usup_login", unique = true)
	@JsonProperty("usupLogin")
	@JsonInclude(Include.NON_NULL)
	private String usupLogin;

	@JsonIgnoreProperties
	@ApiModelProperty(position = 5, value = "Este campo almacena la informacion del Clave de Acceso o password", required = true)
	@Column(name = "usup_clave")
	@JsonProperty("usupClave")
	@JsonInclude(Include.NON_NULL)
	private String usupClave;

	@JsonIgnoreProperties
	@ApiModelProperty(position = 5, value = "Este campo almacena la informacion del Clave de Acceso o password", required = true)
	@Column(name = "usup_clave_sicpas")
	@JsonProperty("usupClaveSicpas")
	@JsonInclude(Include.NON_NULL)
	private String usupClaveSicpas;

	@ApiModelProperty(position = 6, value = "El correo del funcionario para validarse en el active directory", required = true)
	@Column(name = "usup_ad_correo")
	@JsonProperty("usupAdCorreo")
	@JsonInclude(Include.NON_NULL)
	private String usupAdCorreo;

	@ApiModelProperty(position = 7, value = "11=activo  12=inactivo", required = true)
	@Column(name = "usup_estado")
	@JsonProperty("usupEstado")
	@JsonInclude(Include.NON_NULL)
	private Integer usupEstado;

	@ApiModelProperty(position = 8, value = "Este campo almacena los valores f =false para eliminado logico  y t= true para indicar que esta¡ activo", required = true)
	@Column(name = "usup_eliminado")
	@JsonProperty("usupEliminado")
	@JsonInclude(Include.NON_NULL)
	private Boolean usupEliminado;

	@ApiModelProperty(position = 9, value = "Este campo almacena el código del usuario realiza el ingreso de informacion", required = true)
	@Column(name = "usup_reg_usu")
	@JsonProperty("usupRegUsu")
	@JsonInclude(Include.NON_NULL)
	private Integer usupRegUsu;

	@ApiModelProperty(position = 10, value = "Este campo almacena la fecha en la que el usuario realiza el ingreso de informacion", required = true)
	@CreationTimestamp
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "usup_reg_fecha")
	@JsonProperty("usupRegFecha")
	@JsonInclude(Include.NON_NULL)
	private Date usupRegFecha;

	@ApiModelProperty(position = 11, value = "Este campo almacena el codigo que hizo la actualizacion del usuario	", required = true)
	@Column(name = "usup_act_usu")
	@JsonProperty("usupActUsu")
	@JsonInclude(Include.NON_NULL)
	private Integer usupActUsu;

	@ApiModelProperty(position = 12, value = "Este campo almacena la fecha en la que se hizo la actualizacion del usuario	", required = true)
	@UpdateTimestamp
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "usup_act_fecha")
	@JsonProperty("usupActFecha")
	@JsonInclude(Include.NON_NULL)
	private Date usupActFecha;

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "usuariopersona")
	@ApiModelProperty(value = "Usuarios", notes = "***", position = 13)
	@JsonProperty("usuarios")
	@JsonInclude(Include.NON_NULL)
	private List<UsuarioDTO> usuarios;

	@Transient
	@JsonIgnore
	private transient UsuarioPersonaDTO savedState;

}
