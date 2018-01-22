package br.com.maisvida.model;

import javax.persistence.*;
import java.io.Serializable;

/**
 * Created by jjbaz on 19/01/2018.
 */
@Entity
public class Estado implements Serializable {
    private int id;
    private String nome;
    private String sigla;

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
    @Column(name = "nome")
    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    @Column(name = "sigla")
    public String getSigla() {
        return sigla;
    }

    public void setSigla(String sigla) {
        this.sigla = sigla;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Estado estado = (Estado) o;

        if (id != estado.id) return false;
        if (nome != null ? !nome.equals(estado.nome) : estado.nome != null) return false;
        if (sigla != null ? !sigla.equals(estado.sigla) : estado.sigla != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (nome != null ? nome.hashCode() : 0);
        result = 31 * result + (sigla != null ? sigla.hashCode() : 0);
        return result;
    }
}
