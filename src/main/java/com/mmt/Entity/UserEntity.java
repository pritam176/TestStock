/**
 * 
 */
package com.mmt.Entity;

import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 * @author pkumar
 *
 */
@Entity
@Table(name = "User")
public class UserEntity {

	

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long user_id;

	private String username;
	private String password;
	private boolean enabled;

	@OneToMany(mappedBy = "userEntity", cascade = CascadeType.ALL,fetch = FetchType.EAGER)
	private Set<UserRoleEntity> userRoleEntity;

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public boolean isEnabled() {
		return enabled;
	}

	public void setEnabled(boolean enabled) {
		this.enabled = enabled;
	}

	public Set<UserRoleEntity> getUserRoleEntity() {
		return userRoleEntity;
	}

	public void setUserRoleEntity(Set<UserRoleEntity> userRoleEntity) {
		this.userRoleEntity = userRoleEntity;
	}

	public long getUser_id() {
		return user_id;
	}

	public void setUser_id(long user_id) {
		this.user_id = user_id;
	}

	

}
