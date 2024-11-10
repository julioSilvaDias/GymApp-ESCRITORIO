package gymApp.bbdd.pojos;

import java.io.Serializable;
import java.util.Objects;

public class Usuario implements Serializable{

	private static final long serialVersionUID = 1L;
	
	String id;
	String name;
	String password;
	String surname;
	String email;
	String brithdate;
	
	
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public String getPassword() {
		return password;
	}
	
	public void setPassword(String password) {
		this.password = password;
	}
	
	public String getSurname() {
		return surname;
	}
	
	public void setSurname(String surname) {
		this.surname = surname;
	}
	
	public String getEmail() {
		return email;
	}
	
	public void setEmail(String email) {
		this.email = email;
	}

	public String getBrithdate() {
		return brithdate;
	}

	public void setBrithdate(String brithdate) {
		this.brithdate = brithdate;
	}

	@Override
	public int hashCode() {
		return Objects.hash(brithdate, email, id, name, password, surname);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Usuario other = (Usuario) obj;
		return Objects.equals(brithdate, other.brithdate) && Objects.equals(email, other.email) && id == other.id
				&& Objects.equals(name, other.name) && Objects.equals(password, other.password)
				&& Objects.equals(surname, other.surname);
	}

	@Override
	public String toString() {
		return "Usuario [id=" + id + ", name=" + name + ", password=" + password + ", surname=" + surname + ", email="
				+ email + ", brithdate=" + brithdate + "]";
	}

	
	
}
