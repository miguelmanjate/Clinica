package mz.ciuem.uclinica.auth;

import java.util.List;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.springframework.security.core.GrantedAuthority;

import mz.ciuem.uclinica.entity.GenericEntity;

@Entity
@Table(name = "permission")
@Access(AccessType.FIELD)
public class Permission extends GenericEntity implements GrantedAuthority {

	@Column(name = "autorizacao")
	@Enumerated(EnumType.STRING)
	private Authorization authorization;

	@ManyToMany(fetch = FetchType.LAZY, mappedBy = "permissions")
	private List<Role> roles;;

	@Override
	public String getAuthority() {
		return getAuthorization().toString();
	}

	public Authorization getAuthorization() {
		return authorization;
	}

	public void setAuthorization(Authorization authorization) {
		this.authorization = authorization;
	}

	public List<Role> getRoles() {
		return roles;
	}

	public void setRoles(List<Role> roles) {
		this.roles = roles;
	}
	
	

}
