package mz.ciuem.uclinica.auth;

import java.util.Collection;
import java.util.HashSet;
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
import javax.persistence.PrePersist;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotBlank;
import org.hibernate.validator.constraints.NotEmpty;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import mz.ciuem.uclinica.entity.GenericEntity;

@Entity
@Table(name = "user")
@Access(AccessType.FIELD)
public class User extends GenericEntity implements UserDetails {

	
	@NotNull(message = "Mandatorio informar o nome do utilizador")
	@NotBlank(message = "Mandatorio informar o nome do utilizador")
	@Column(name = "username", length = 50, unique = true)
	private String username;

	
	@NotNull(message = "Mandatorio informar a senha")
	@NotBlank(message = "Mandatorio informar a senha")
	@Column(name = "password")
	private String password;

	@Column(name = "enabled")
	private boolean enabled;

	@ManyToMany(fetch = FetchType.EAGER, cascade  = CascadeType.ALL)
	@JoinTable(name = "user_role", joinColumns = @JoinColumn(name = "user_id", nullable = false, updatable = false),
	inverseJoinColumns = @JoinColumn(name = "role_id", nullable = false, updatable = false))
	private Set<Role> roles;

	@Override
	public Collection<GrantedAuthority> getAuthorities() {
		Set<GrantedAuthority> authorities = new HashSet<GrantedAuthority>();
		authorities.addAll(getRoles());
		
		return authorities;
	}
	
	@PrePersist
	public void encryptPassword(){
		
		setPasswordUsingBCrypt(password);
	}

	@Override
	public String getPassword() {
		return password;
	}

	@Override
	public String getUsername() {
		return username;
	}

	@Override
	public boolean isAccountNonExpired() {
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isEnabled() {
		return true;
	}

	public Set<Role> getRoles() {
		return roles;
	}

	public void setRoles(Set<Role> roles) {
		this.roles = roles;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public void setEnabled(boolean enabled) {
		this.enabled = enabled;
	}
	
	public String setPasswordUsingBCrypt(String password){
		
		BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
		this.password = passwordEncoder.encode(password);
		return this.password;
		
	}
	
	
	

}
