package com.Dual2024.ProjectCompetition.DataAccess.Model;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.Dual2024.ProjectCompetition.Utils.UserState;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.Lob;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@AllArgsConstructor
@RequiredArgsConstructor
@Builder
@Entity
@Table(name = "users")
public class User implements UserDetails {

	private static final long serialVersionUID = -3851309459476535286L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "user_id")
	private Long id;

	@NotBlank
	@Column(name = "user_nick", unique = true, nullable = false)
	private String nick;

	@Email
	@NotBlank
	@Column(name = "user_email", unique = true, nullable = false)
	private String email;

	@NotBlank
	@Size(min = 6)
	@Column(name = "user_password", nullable = false)
	private String password;

	@Lob
	@Column(name = "user_avatar")
	private String avatar;

	@Enumerated(EnumType.STRING)
	@Column(name = "user_state", nullable = false)
	private UserState state;

	@ManyToMany(fetch = FetchType.EAGER)
	@JoinTable(name = "users_roles", joinColumns = @JoinColumn(name = "users_roles_user_id"), inverseJoinColumns = @JoinColumn(name = "users_roles_role_id"))
	private List<Role> roles;

	@ManyToMany(mappedBy = "users")
	private List<Team> teams;

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		List<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>();
		roles.forEach((Role role) -> authorities.add(new SimpleGrantedAuthority("ROLE_" + role.getName())));
		return authorities;
	}

	@Override
	public boolean isAccountNonExpired() {
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}

	@Override
	public boolean isEnabled() {
		return true;
	}

	@Override
	public String getUsername() {
		return email;
	}

}