package com.account_service.models;

import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
// import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Type;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

@Entity
@Table(name = "account_service")
@JsonInclude(Include.NON_NULL)
public class AccountServiceModel {
	@Id
//  @GeneratedValue(strategy = GenerationType.AUTO)
    @GeneratedValue(generator = "uuid2")
	@GenericGenerator(name = "uuid2", strategy = "uuid2")
//	@Column(name = "id", columnDefinition = "INT", insertable = false, updatable = false)
	@Column(name = "id", columnDefinition = "VARCHAR(36)", insertable = false, updatable = false)
	@Type(type = "uuid-char")
    private UUID id;
	@Column(name = "name", columnDefinition = "VARCHAR(50)",
			nullable = false)
    private String name;
	@Column(name = "email", columnDefinition = "VARCHAR(50)",
			nullable = false)
    private String email;
	@Column(name = "password", columnDefinition = "VARCHAR(50)",
			nullable = false)
    private String password;
	@Column(name = "locked", columnDefinition = "CHAR(1) default 'F' NOT NULL")
	@Type(type = "org.hibernate.type.TrueFalseType")
    private Boolean locked;

    public AccountServiceModel() {  }

    public AccountServiceModel(UUID id, String name, String email, String password, Boolean locked) {
		super();
		this.id = id;
		this.name = name;
		this.email = email;
		this.password = password;
		this.locked = locked;
	}

	public AccountServiceModel(String name, String email, String password) {
        this.setName(name);
        this.setEmail(email);
        this.setPassword(password);
        this.setLocked(locked);
    }

    public AccountServiceModel(Boolean locked) {
        this.setLocked(locked);
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Boolean getLocked() {
		return locked;
	}

	public void setLocked(Boolean locked) {
		if (locked == null) {
			this.locked = false;
		} else {
			this.locked = locked;
		}
	}

	@Override
	public String toString() {
		return "AccountServiceModel [id=" + id + ", name=" + name + ", email=" + email + ", password=" + password
				+ ", locked=" + locked + "]";
	}
}
