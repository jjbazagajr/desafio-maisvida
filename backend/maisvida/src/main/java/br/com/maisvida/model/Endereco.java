package br.com.maisvida.model;

import javax.persistence.*;

/**
 * Created by jjbaz on 19/01/2018.
 */
@Entity
public class Endereco {
    private int id;
    private String complemento;
    private Cidade Cidade;

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
    @Column(name = "complemento")
    public String getComplemento() {
        return complemento;
    }

    public void setComplemento(String complemento) {
        this.complemento = complemento;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Endereco endereco = (Endereco) o;

        if (id != endereco.id) return false;
        if (complemento != null ? !complemento.equals(endereco.complemento) : endereco.complemento != null)
            return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (complemento != null ? complemento.hashCode() : 0);
        return result;
    }

    @OneToOne
    @JoinColumn(name = "cidade_id", referencedColumnName = "id")
    public Cidade getCidade() {
        return Cidade;
    }

    public void setCidade(Cidade cidade) {
        Cidade = cidade;
    }
}
