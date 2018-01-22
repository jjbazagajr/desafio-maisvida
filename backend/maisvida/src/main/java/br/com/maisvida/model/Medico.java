package br.com.maisvida.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotBlank;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

/**
 * Created by jjbaz on 19/01/2018.
 */
@Entity
public class Medico {
    private int id;
    @NotBlank(message = "Primeiro nome obrigatório!")
    private String primeiroNome;
    @NotBlank(message = "Ultimo nome obrigatório!")
    private String ultimoNome;
    @NotBlank(message = "Email obrigatório!")
    @Email
    private String email;
    private boolean ativo;
    private boolean ocupado;
    @NotNull(message = "Selecione uma especialidade!")
    private Especialidade especialidade;
    @NotNull(message = "Endereço não está completo!")
    @JsonProperty("address")
    private Endereco endereco;


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Basic
    @Column(name = "primeiro_nome")
    public String getPrimeiroNome() {
        return primeiroNome;
    }

    public void setPrimeiroNome(String primeiroNome) {
        this.primeiroNome = primeiroNome;
    }

    @Basic
    @Column(name = "ultimo_nome")
    public String getUltimoNome() {
        return ultimoNome;
    }

    public void setUltimoNome(String ultimoNome) {
        this.ultimoNome = ultimoNome;
    }

    @Basic
    @Column(name = "email")
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Basic
    @Column(name = "ativo")
    public boolean isAtivo() {
        return ativo;
    }

    public void setAtivo(boolean ativo) {
        this.ativo = ativo;
    }

    @Basic
    @Column(name = "ocupado")
    public boolean isOcupado() {
        return ocupado;
    }

    public void setOcupado(boolean ocupado) {
        this.ocupado = ocupado;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Medico medico = (Medico) o;

        if (id != medico.id) return false;
        if (ativo != medico.ativo) return false;
        if (ocupado != medico.ocupado) return false;
        if (primeiroNome != null ? !primeiroNome.equals(medico.primeiroNome) : medico.primeiroNome != null)
            return false;
        if (ultimoNome != null ? !ultimoNome.equals(medico.ultimoNome) : medico.ultimoNome != null) return false;
        if (email != null ? !email.equals(medico.email) : medico.email != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (primeiroNome != null ? primeiroNome.hashCode() : 0);
        result = 31 * result + (ultimoNome != null ? ultimoNome.hashCode() : 0);
        result = 31 * result + (email != null ? email.hashCode() : 0);
        result = 31 * result + (ativo ? 1 : 0);
        result = 31 * result + (ocupado ? 1 : 0);
        return result;
    }

    @OneToOne
    @JoinColumn(name = "especialidade_id", referencedColumnName = "id")
    public Especialidade getEspecialidade() {
        return especialidade;
    }

    public void setEspecialidade(Especialidade especialidade) {
        this.especialidade = especialidade;
    }

    @OneToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "endereco_id", referencedColumnName = "id")
    public Endereco getEndereco() {
        return endereco;
    }

    public void setEndereco(Endereco endereco) {
        this.endereco = endereco;
    }
}
