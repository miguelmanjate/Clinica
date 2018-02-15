package mz.ciuem.uclinica.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@MappedSuperclass
public class GenericEntity implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	protected Long id;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "created", nullable = false)
	protected Date created;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "updated", nullable = false)
	protected Date updated;

	public GenericEntity() {
		super();
		
		created = updated = new Date();
	}

	@PrePersist
	public void onCreate() {
		updated = created = new Date();

	}

	@PreUpdate
	public void onUpdate() {
		updated = new Date();
	}

	public Date getCreated() {
		return created;
	}

	public Date getUpdated() {
		return updated;
	}

	public Long getId() {
		return id;
	}

	public void setUpdated(Date updated) {
		this.updated = updated;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public void setCreated(Date created) {
		this.created = created;
	}
	

	

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
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
		GenericEntity other = (GenericEntity) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return String.format("%s(id=%d)", this.getClass().getSimpleName(), this.getId());
	}

}
