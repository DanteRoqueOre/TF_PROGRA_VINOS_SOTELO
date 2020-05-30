<<<<<<< HEAD
package pe.edu.upc.spring.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;

@Entity
@Table(name="Vino")
public class Vino implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int idVino;
	
	@NotEmpty(message="No puede estar vacio")
	@NotBlank(message="No puede estar en blanco")
	@Column(name="nombreEvento",length=60,nullable=false)
	private String nameVino;

	public Vino() {
		super();
	}

	public Vino(int idVino,String nameVino) {
		super();
		this.idVino = idVino;
		this.nameVino = nameVino;
	}

	public int getIdVino() {
		return idVino;
	}

	public void setIdVino(int idVino) {
		this.idVino = idVino;
	}

	public String getNameVino() {
		return nameVino;
	}

	public void setNameVino(String nameVino) {
		this.nameVino = nameVino;
	}

}
=======
package pe.edu.upc.spring.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;

@Entity
@Table(name="Vino")
public class Vino implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int idVino;
	
	@NotEmpty(message="No puede estar vacio")
	@NotBlank(message="No puede estar en blanco")
	@Column(name="nombreEvento",length=60,nullable=false)
	private String nameVino;

	public Vino() {
		super();
	}

	public Vino(int idVino,String nameVino) {
		super();
		this.idVino = idVino;
		this.nameVino = nameVino;
	}

	public int getIdVino() {
		return idVino;
	}

	public void setIdVino(int idVino) {
		this.idVino = idVino;
	}

	public String getNameVino() {
		return nameVino;
	}

	public void setNameVino(String nameVino) {
		this.nameVino = nameVino;
	}

}
>>>>>>> branch 'master' of https://github.com/Thales-cod/TF_PROGRA_VINOS_SOTELO.git
