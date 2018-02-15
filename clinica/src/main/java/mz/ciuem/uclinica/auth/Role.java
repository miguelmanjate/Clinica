package mz.ciuem.uclinica.auth;

import java.util.List;
import java.util.Set;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.springframework.security.core.GrantedAuthority;

import mz.ciuem.uclinica.entity.GenericEntity;

@Entity
@Table(name = "ROLE")
@Access(AccessType.FIELD)

public class Role extends GenericEntity implements GrantedAuthority {

	@Column(name = "rolename", length = 50, updatable = false)
	private String rolename;

	@ManyToMany(fetch = FetchType.LAZY, mappedBy = "roles")
	private Set<User> users;

	@ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	@JoinTable(name = "role_permission", joinColumns = @JoinColumn(name = "role_id", nullable = false, updatable = false), inverseJoinColumns = @JoinColumn(name = "permission_id", nullable = false, updatable = false))
	private List<Permission> permissions;

	@Override
	public String getAuthority() {
		return getRolename();
	}

	public String getRolename() {
		return rolename;
	}

	public void setRolename(String rolename) {
		this.rolename = rolename;
	}

	public Set<User> getUsers() {
		return users;
	}

	public void setUsers(Set<User> users) {
		this.users = users;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + ((rolename == null) ? 0 : rolename.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!super.equals(obj))
			return false;
		if (getClass() != obj.getClass())
			return false;
		Role other = (Role) obj;
		if (rolename == null) {
			if (other.rolename != null)
				return false;
		} else if (!rolename.equals(other.rolename))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Role [rolename=" + rolename + "]";
	}

	public List<Permission> getPermissions() {
		return permissions;
	}

	public void setPermissions(List<Permission> permissions) {
		this.permissions = permissions;
	}

}
