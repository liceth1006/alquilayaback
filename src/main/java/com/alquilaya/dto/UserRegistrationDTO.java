package com.alquilaya.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;

public class UserRegistrationDTO {

    //@NotBlank
    @Email(message = "El correo debe ser válido")
    private String email;

    //@NotBlank
    @Pattern(regexp = "^(?=.*\\d)[A-Za-z\\d@$!%*?&]{8,255}$", message = "La contraseña debe tener al menos 8 caracteres con un número")
    private String password;

    @NotBlank
    @Pattern(regexp = "^[a-zA-Z0-9 ]{1,255}$", message = "El nombre no es válido")
    private String firstName;

    @NotBlank
    @Pattern(regexp = "^[a-zA-Z0-9 ]{1,255}$", message = "El apellido no es válido")
    private String lastName;

    private int typeDocumentId;

    @NotBlank
    @Pattern(regexp = "^[a-zA-Z0-9 ]{1,255}$", message = "El número de documento no es válido")
    private String documentNumber;

	 private String role;
	 
    public UserRegistrationDTO(String email, String password, String firstName, String lastName, int typeDocumentId, String documentNumber,String role) {
        this.email = email;
        this.password = password;
        this.firstName = firstName;
        this.lastName = lastName;
        this.typeDocumentId = typeDocumentId;
        this.documentNumber = documentNumber;
    }

    
    public UserRegistrationDTO() {}
    
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

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public int getTypeDocumentId() {
		return typeDocumentId;
	}

	public void setTypeDocumentId(int typeDocumentId) {
		this.typeDocumentId = typeDocumentId;
	}

	public String getDocumentNumber() {
		return documentNumber;
	}

	public void setDocumentNumber(String documentNumber) {
		this.documentNumber = documentNumber;
	}


	public String getRole() {
		return role;
	}


	public void setRole(String role) {
		this.role = role;
	}

	
   
    
}
