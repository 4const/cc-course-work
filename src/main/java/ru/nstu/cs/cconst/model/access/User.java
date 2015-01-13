package ru.nstu.cs.cconst.model.access;

import javax.persistence.*;

@Entity
@Table(name = "users")
public class User {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	@Column(length = 20, nullable = false)
	private String login;

	private String password;

	@Column(columnDefinition = "smallint default 0", nullable = false)
	private short role;

	public User() {
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public short getRole() {
		return role;
	}

	public void setRole(short role) {
		this.role = role;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) {
			return true;
		}
		if (!(o instanceof User)) {
			return false;
		}

		User user = (User)o;

		if (id != user.id) {
			return false;
		}
		if (role != user.role) {
			return false;
		}
		if (login != null ? !login.equals(user.login) : user.login != null) {
			return false;
		}
		if (password != null ? !password.equals(user.password) : user.password != null) {
			return false;
		}

		return true;
	}

	@Override
	public int hashCode() {
		int result = id;
		result = 31 * result + (login != null ? login.hashCode() : 0);
		result = 31 * result + (password != null ? password.hashCode() : 0);
		result = 31 * result + (int)role;
		return result;
	}

	@Override
	public String toString() {
		return "User{" +
			"id=" + id +
			", login='" + login + '\'' +
			", password='" + password + '\'' +
			", role=" + role +
			'}';
	}
}
