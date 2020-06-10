package ec.gob.mag.stageRenagro.dto;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;
import javax.validation.constraints.Email;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonProperty.Access;

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
public class PersonaDTO implements Serializable {

	private static final long serialVersionUID = 1L;

	@ApiModelProperty(value = "Este campo es  la clave primaria de la tabla Persona", required = false, readOnly = true)
	@Id
	@Column(name = "per_id", unique = true, nullable = false)
	@JsonProperty("perId")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@ApiModelProperty(value = "18=cedula 19=ruc 20=pasaporte  345=sin identificacion", required = true)
	@Column(name = "cat_tipo_identificacion")
	@JsonProperty("catTipoIdentificacion")
	@JsonInclude(Include.NON_NULL)
	private Integer catTipoIdentificacion;

	@ApiModelProperty(value = "21=masculino 22=femenino", required = true)
	@Column(name = "cat_genero")
	@JsonProperty("catGenero")
	@JsonInclude(Include.NON_NULL)
	private Integer catGenero;

	@ApiModelProperty(value = "23=soltero 24=casado 25=divorciado.", required = true)
	@Column(name = "cat_estado_civil")
	@JsonProperty("catEstadoCivil")
	@JsonInclude(Include.NON_NULL)
	private Integer catEstadoCivil;

	@ApiModelProperty(value = "Profesiones que tenga la persona", required = false)
	@Column(name = "cat_titulo")
	@JsonProperty("catTitulo")
	@JsonInclude(Include.NON_NULL)
	private Integer catTitulo;

	@ApiModelProperty(value = "Abreviaciones del titulo de la persona", required = false)
	@Column(name = "cat_abrev_titulo")
	@JsonProperty("catAbrevTitulo")
	@JsonInclude(Include.NON_NULL)
	private Integer catAbrevTitulo;

	@ApiModelProperty(value = "Titulo de Tercer Nivel, Cuarto Nivel,..", required = false)
	@Column(name = "cat_titulo_acad")
	@JsonProperty("catTituloAcad")
	@JsonInclude(Include.NON_NULL)
	private Integer catTituloAcad;

	@ApiModelProperty(value = "Abreviaciones del titulo academico de la persona", required = false)
	@Column(name = "cat_abrev_titulo_acad")
	@JsonProperty("catAbrevTituloAcad")
	@JsonInclude(Include.NON_NULL)
	private Integer catAbrevTituloAcad;

	@ApiModelProperty(value = "27=ecuatoriana 344=extranjero", required = false)
	@Column(name = "cat_id_tipo_nac")
	@JsonProperty("catIdTipoNac")
	@JsonInclude(Include.NON_NULL)
	private Integer catIdTipoNac;

	@ApiModelProperty(value = "Indígena, Blanco, Mestizo, Montubio, Afroecuatoriano", required = true)
	@Column(name = "cat_etnia")
	@JsonProperty("catEtnia")
	@JsonInclude(Include.NON_NULL)
	private Integer catEtnia;

	@ApiModelProperty(value = "Categoria de pueblo indigena", required = false)
	@Column(name = "cat_pueblo_indigena")
	@JsonProperty("catPuebloIndigena")
	@JsonInclude(Include.NON_NULL)
	private Integer catPuebloIndigena;

	@ApiModelProperty(value = "Campo que almacena el nombre de la persona", required = true, allowableValues = "")
	@Column(name = "per_nombre", length = 50)
	@JsonProperty("perNombre")
	@JsonInclude(Include.NON_NULL)
	private String perNombre;

	@ApiModelProperty(value = "Campo que almacena el apellido de la persona", required = true)
	@Column(name = "per_apellido", length = 50)
	@JsonProperty("perApellido")
	@JsonInclude(Include.NON_NULL)
	private String perApellido;

	@ApiModelProperty(value = "Campo que almacena los nombres de la persona", required = false)
	@Column(name = "per_nombres", length = 100)
	@JsonProperty("perNombres")
	@JsonInclude(Include.NON_NULL)
	private String perNombres;

	@ApiModelProperty(value = "Campo que almacena el numero de cedula de la persona", required = true)
	@Column(name = "per_identificacion", unique = true, length = 30)
	@JsonProperty("perIdentificacion")
	@JsonInclude(Include.NON_NULL)
	private String perIdentificacion;

	@ApiModelProperty(value = "Campo que almacena la fecha de nacimiento del persona", required = false)
	@Temporal(TemporalType.DATE)
	@Column(name = "per_fecha_nac", length = 13)
	@JsonProperty("perFechaNac")
	@JsonInclude(Include.NON_NULL)
	private Date perFechaNac;

	@ApiModelProperty(value = "Tipo de sangre de la persona", required = false)
	@Column(name = "per_tipo_sangre", length = 16)
	@JsonProperty("perTipoSangre")
	@JsonInclude(Include.NON_NULL)
	private String perTipoSangre;

	@ApiModelProperty(value = "De donde viene la migración de la información", required = false)
	@Column(name = "per_fuente", length = 16)
	@JsonProperty("perFuente")
	@JsonInclude(Include.NON_NULL)
	private String perFuente;

	@ApiModelProperty(value = "Identificación del origen de la migracion", required = false)
	@Column(name = "per_fuente_id")
	@JsonProperty("perFuenteId")
	@JsonInclude(Include.NON_NULL)
	private Integer perFuenteId;

	@ApiModelProperty(value = "Fecha de migracion", required = false)
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "per_fuente_fecha", length = 29)
	@JsonProperty("perFuenteFecha")
	@JsonInclude(Include.NON_NULL)
	private Date perFuenteFecha;

	@ApiModelProperty(value = "Desde Rcivil: CIUDADANO,FALLECIDO,EXTRANJERO,MENOR DE EDAD,POLICIA,MILITAR..", required = false, allowableValues = "CIUDADANO, FALLECIDO, EXTRANJERO, MENOR DE EDAD, POLICIA, MILITAR")
	@Column(name = "per_rccondicion", length = 56)
	@JsonProperty("perRccondicion")
	@JsonInclude(Include.NON_NULL)
	private String perRccondicion;

	@ApiModelProperty(value = "11=activo  12=inactivo", required = true, allowableValues = "11=>activo, 12=>inactivo ....")
	@Column(name = "per_estado")
	@JsonProperty("perEstado")
	@JsonInclude(Include.NON_NULL)
	private Integer perEstado;

	@ApiModelProperty(value = "Este campo almacena los valores f =false para eliminado logico  y t= true para indicar que está activo", required = true, allowableValues = "false=>no eliminado lógico, true=> eliminado lógico")
	@Column(name = "per_eliminado")
	@JsonProperty("perEliminado")
	@JsonInclude(Include.NON_NULL)
	private Boolean perEliminado;

	@ApiModelProperty(value = "Campo que almacena el usuario que registra al persona", required = false)
	@Column(name = "per_reg_usu")
	@JsonProperty("perRegUsu")
	@JsonInclude(Include.NON_NULL)
	private Integer perRegUsu;

	@ApiModelProperty(value = "Este campo almacena la fecha en la que el usuario realiza el ingreso de información", hidden = true)
	@CreationTimestamp
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "per_reg_fecha", length = 29)
	@JsonProperty("perRegFecha")
	@JsonInclude(Include.NON_NULL)
	private Date perRegFecha;

	@ApiModelProperty(value = "Este campo almacena el codigo del usuario que realiza la actualización de información", required = false)
	@Column(name = "per_act_usu")
	@JsonProperty("perActUsu")
	@JsonInclude(Include.NON_NULL)
	private Integer perActUsu;

	@ApiModelProperty(value = "Este campo almacena la fecha en la que hizo la actualización el usuario", readOnly = true)
	@UpdateTimestamp
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "per_act_fecha", length = 29)
	@JsonProperty("perActFecha")
	@JsonInclude(Include.NON_NULL)
	private Date perActFecha;

	@ApiModelProperty(value = "Id de ubicación del domicilio")
	@Column(name = "ubi_id_domicilio")
	@JsonProperty("ubiIdDomicilio")
	@JsonInclude(Include.NON_NULL)
	private Long ubiIdDomicilio;

	@ApiModelProperty(value = "Dirección del domicilio de la persona")
	@Column(name = "per_dir_domicilio", length = 500)
	@JsonProperty("perDirDomicilio")
	@JsonInclude(Include.NON_NULL)
	private String perDirDomicilio;

	@ApiModelProperty(value = "Número de Teléfono de la persona")
	@Column(name = "per_telefono", length = 64)
	@JsonProperty("perTelefono")
	@JsonInclude(Include.NON_NULL)
	private String perTelefono;

	@ApiModelProperty(value = "Número de Celular de la persona")
	@Column(name = "per_celular", length = 64)
	@JsonProperty("perCelular")
	@JsonInclude(Include.NON_NULL)
	private String perCelular;

	@ApiModelProperty(value = "Correo o email de la persona")
	@Column(name = "per_correo", length = 128)
	@JsonProperty("perCorreo")
	@JsonInclude(Include.NON_NULL)
	@Email(message = "_error.validation_valid_mail.message")
	private String perCorreo;

	@ApiModelProperty(value = "Fecha de defunción de la persona")
	@Temporal(TemporalType.DATE)
	@Column(name = "per_fecha_defuncion", length = 13)
	@JsonProperty("perFechaDefuncion")
	@JsonInclude(Include.NON_NULL)
	private Date perFechaDefuncion;

	@ApiModelProperty(value = "Tipo_Organización de la persona a la que pertence")
	@Column(name = "cat_tipo_org_per")
	@JsonProperty("catTipoOrgPer")
	@JsonInclude(Include.NON_NULL)
	private Integer catTipoOrgPer;

	@ApiModelProperty(value = "Ruc de la persona")
	@Column(name = "per_ruc", length = 13)
	@JsonProperty("perRuc")
	@JsonInclude(Include.NON_NULL)
	private String perRuc;

	@ApiModelProperty(value = "Tipo de ruc SRI Natural/ Jurídica de la  persona")
	@Column(name = "per_tipo_sri", length = 10)
	@JsonProperty("perTipoSri")
	private String perTipoSri;

	@ApiModelProperty(value = "Actividad Economica de la persona viene del SRI")
	@Column(name = "per_act_econ_sri", length = 1024)
	@JsonProperty("perActEconSri")
	@JsonInclude(Include.NON_NULL)
	private String perActEconSri;

	@ApiModelProperty(value = "Estado de la actividad económica de la persona")
	@Column(name = "per_estado_sri", length = 32)
	@JsonProperty("perEstadoSri")
	@JsonInclude(Include.NON_NULL)
	private String perEstadoSri;

	@ApiModelProperty(value = "Tipo de Contribuyente viene del SRI")
	@Column(name = "per_tipo_contr_sri", length = 128)
	@JsonProperty("perTipoContrSri")
	@JsonInclude(Include.NON_NULL)
	private String perTipoContrSri;

	@ApiModelProperty(value = "Pagina web de la persona")
	@Column(name = "per_pagina_web", length = 128)
	@JsonProperty("perPaginaWeb")
	@JsonInclude(Include.NON_NULL)
	private String perPaginaWeb;

	@ApiModelProperty(value = "Cédula de la persona", readOnly = true)
	@Column(name = "per_cedula", unique = true, length = 10)
	@JsonProperty(value = "perCedula", access = Access.READ_ONLY)
	@JsonInclude(Include.NON_NULL)
	private String perCedula;

	@ApiModelProperty(value = "Lugar Nacimiento de la persona")
	@Column(name = "per_lugar_nac_rc", length = 512)
	@JsonProperty("perLugarNacRc")
	@JsonInclude(Include.NON_NULL)
	private String perLugarNacRc;

	@ApiModelProperty(value = "Instruccion de formal de la persona")
	@Column(name = "cat_instruccion_formal")
	@JsonProperty("catInstruccionFormal")
	@JsonInclude(Include.NON_NULL)
	private Integer catInstruccionFormal;

	@ApiModelProperty(value = "Año de Instruccion de formal de la persona")
	@Column(name = "cat_instruccion_anio")
	@JsonProperty("catInstruccionAnio")
	@JsonInclude(Include.NON_NULL)
	private Integer catInstruccionAnio;

	@ApiModelProperty(value = "Categoria de la etnia de la persona (otra)")
	@Column(name = "cat_etnia_otra")
	@JsonProperty("catEtniaOtra")
	@JsonInclude(Include.NON_NULL)
	private String catEtniaOtra;

	@ApiModelProperty(value = "Variable transitoria de petiId", required = true)
	@Transient
	@JsonProperty("petiId")
	@JsonInclude(Include.NON_NULL)
	private Long petiId;

	@Transient
	@ApiModelProperty(value = "Tipo de productor")
	@JsonProperty("tipoProductor")
	@JsonInclude(Include.NON_NULL)
	private String tipoProductor;

//	/********* RELACIONES JPA ************/
//	@ApiModelProperty(value = "Tipos persona")
//	@OneToMany(fetch = FetchType.EAGER, mappedBy = "persona", cascade = { CascadeType.PERSIST, CascadeType.MERGE,
//			CascadeType.REFRESH }, orphanRemoval = true)
//	@JsonProperty("personaTipos")
//	@JsonInclude(Include.NON_NULL)
//	@JsonManagedReference(value = "persona-persona-tipos")
//	private List<PersonaTipoDTO> personaTipos;

}
