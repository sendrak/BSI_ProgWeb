
package br.com.cadastroEmbarcacoes.model;

import br.com.cadastroEmbarcacoes.annotation.EmailValidation;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.*;
import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.br.CPF;

@Entity
@JsonIgnoreProperties(value = "senha", allowGetters = false, allowSetters = true)
public class Cliente extends Usuario {
    
    public Cliente(String cpf, String nome, String email, String login, String senha) {
        super(login, senha);
        this.cpf = cpf;
        this.nome = nome;
        this.email = email;
    }
    
    public Cliente() {

    }
    
    @Column(length = 14, nullable = false)
    @NotBlank(message = "Insira um CPF válido.")
    @CPF(message = "CPF não validado")
    private String cpf;
    
    @Column(length = 100, nullable = false, updatable = true)
    @NotBlank(message = "Insira um nome válido.")
    @Length(min = 5, max = 100, message = "O nome deve ter no mínimo 5 caracteres e no máximo 100.")
    private String nome;
    
    
    @Column(length = 100, nullable = false, unique = true)
    @NotBlank(message = "Insira um nome válido.")
    @Length(min = 5, max = 100, message = "O email deve ter no mínimo 5 caracteres e no máximo 100.")
    @EmailValidation
    private String email;
    
    @JsonIgnore
    @Valid
    @OneToMany(mappedBy = "cliente", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Embarcacao> embarcacoes = new ArrayList<>();

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }
    
    public void cadastrarCliente(){
        
    }
    
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
    
    
    
}
