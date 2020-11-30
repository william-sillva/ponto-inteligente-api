package com.nautilus.pontointeligente.api.dtos;

import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.br.CNPJ;
import org.hibernate.validator.constraints.br.CPF;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;

public class CadastroPJDto {

    private Long id;

    private String nome;

    private String email;

    private String senha;

    private String cpf;

    private String razaoSocial;

    private String cnpj;

    public CadastroPJDto() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    @NotEmpty(message = "Email não pode ser vazio.")
    @Length(min = 5, max = 200, message = "Email deve conter entre 5 e 200 caracteres.")
    @Email(message = "Email inválido.")
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @NotEmpty(message = "Senha não pode ser vazia.")
    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    @NotEmpty(message = "CPF não pode ser vazio.")
    @CPF(message = "CPF inválido.")
    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    @NotEmpty(message = "Razão Social não pode ser vazio.")
    @Length(min = 5, max = 200, message = "Razão Social deve conter entre 5 e 200 caracteres.")
    public String getRazaoSocial() {
        return razaoSocial;
    }

    public void setRazaoSocial(String razaoSocial) {
        this.razaoSocial = razaoSocial;
    }

    @NotEmpty(message = "CNPJ não pode ser vazio.")
    @CNPJ(message = "CNPJ inválido.")
    public String getCnpj() {
        return cnpj;
    }

    public void setCnpj(String cnpj) {
        this.cnpj = cnpj;
    }

    @Override
    public String toString() {
        return "CadastroPJDto ["
                + "id=" + this.id
                + ", nome=" + this.nome
                + ", email=" + this.email
                + ", senha=" + this.senha
                + ", cpf=" + this.cpf
                + ", razaoSocial=" + this.razaoSocial
                + ", cnpj=" + this.cnpj
                + "]";
    }
}
