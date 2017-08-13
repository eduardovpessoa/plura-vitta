package model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "tipoLocal", schema="penaestrada")
public class TipoLocal {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)	  
	private Integer idTipoLocal;
	
	private String descricao;

	public Integer getIdTipoLocal() {
		return idTipoLocal;
	}

	public void setIdTipoLocal(Integer idTipoLocal) {
		this.idTipoLocal = idTipoLocal;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((idTipoLocal == null) ? 0 : idTipoLocal.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		TipoLocal other = (TipoLocal) obj;
		if (idTipoLocal == null) {
			if (other.idTipoLocal != null)
				return false;
		} else if (!idTipoLocal.equals(other.idTipoLocal))
			return false;
		return true;
	}
}
