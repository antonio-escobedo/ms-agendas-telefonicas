package com.agenda.model;

import java.io.Serializable;
import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "AGENDA_TELEFONICA")
public class AgendaTelefonicaEntity implements Serializable {

	/**
	 * @param id
	 * @param nombreContacto
	 * @param telefono
	 * @param fechaHoraRegistro
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	
	@Column(name="NOMBRE_CONTACTO")
	private String nombreContacto;
	
	@Column(name="TELEFONO")
	private String telefono;
	
	public AgendaTelefonicaEntity() {
		
	}
	
	/**
	 * @param id
	 * @param nombreContacto
	 * @param telefono
	 * @param fechaHoraRegistro
	 */
	public AgendaTelefonicaEntity(Long id, String nombreContacto, String telefono, Timestamp fechaHoraRegistro) {
		super();
		this.id = id;
		this.nombreContacto = nombreContacto;
		this.telefono = telefono;
		this.fechaHoraRegistro = fechaHoraRegistro;
	}

	/**
	 * @return the id
	 */
	public Long getId() {
		return id;
	}

	/**
	 * @param id the id to set
	 */
	public void setId(Long id) {
		this.id = id;
	}

	/**
	 * @return the nombreContacto
	 */
	public String getNombreContacto() {
		return nombreContacto;
	}

	/**
	 * @param nombreContacto the nombreContacto to set
	 */
	public void setNombreContacto(String nombreContacto) {
		this.nombreContacto = nombreContacto;
	}

	/**
	 * @return the telefono
	 */
	public String getTelefono() {
		return telefono;
	}

	/**
	 * @param telefono the telefono to set
	 */
	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}
}