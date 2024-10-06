package com.liverpool.gestion_pedidos.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;

public class CustomerDTO {
    @Schema(hidden = true)
    private Long id;
    @NotNull(message = "El nombre no puede ser nulo")
    private String nombre;

    @NotNull(message = "El apellido no puede ser nulo")
    private String apellidoPaterno;

    private String apellidoMaterno;

    private String mail;

    @NotNull(message = "La direccion de envio no puede ser nula")
    private String direccionEnvio;

    public CustomerDTO() {
    }

    public CustomerDTO(Long id, String nombre, String apellidoPaterno, String apellidoMaterno, String mail, String direccionEnvio) {
        this.id = id;
        this.nombre = nombre;
        this.apellidoPaterno = apellidoPaterno;
        this.apellidoMaterno = apellidoMaterno;
        this.mail = mail;
        this.direccionEnvio = direccionEnvio;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellidoMaterno() {
        return apellidoMaterno;
    }

    public void setApellidoMaterno(String apellidoMaterno) {
        this.apellidoMaterno = apellidoMaterno;
    }

    public String getApellidoPaterno() {
        return apellidoPaterno;
    }

    public void setApellidoPaterno(String apellidoPaterno) {
        this.apellidoPaterno = apellidoPaterno;
    }

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public String getDireccionEnvio() {
        return direccionEnvio;
    }

    public void setDireccionEnvio(String direccionEnvio) {
        this.direccionEnvio = direccionEnvio;
    }
}
