package org.portada.modelo;

import jakarta.persistence.*;

import java.util.Objects;

@Entity
@Table(name = "datos_profesionales", schema = "empresa_proyectos")
public class DatosProfesionales {

    //region Atributos

    @Id
    @Column(name="id", nullable=false)
    private Long id;

    @Basic
    @Column(name="categ", nullable=false)
    private String categ;

    @Basic
    @Column(name="sal_bruto_anual", nullable=false)
    private Double salBrutoAnual;

    //endregion

    //region Constructores

    public DatosProfesionales(Long id, String categ, Double salBrutoAnual) {
        this.id = id;
        this.categ = categ;
        this.salBrutoAnual = salBrutoAnual;
    }

    public DatosProfesionales() {
    }

    //endregion

    //region Getters Setters

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCateg() {
        return categ;
    }

    public void setCateg(String categ) {
        this.categ = categ;
    }

    public Double getSalBrutoAnual() {
        return salBrutoAnual;
    }

    public void setSalBrutoAnual(Double salBrutoAnual) {
        this.salBrutoAnual = salBrutoAnual;
    }


    //endregion

    //region Equals Hash toString

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof DatosProfesionales that)) return false;
        return Objects.equals(id, that.id) && Objects.equals(categ, that.categ) && Objects.equals(salBrutoAnual, that.salBrutoAnual);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, categ, salBrutoAnual);
    }

    @Override
    public String toString() {
        return "DatosProfesionales{" +
                "id=" + id +
                ", categ='" + categ + '\'' +
                ", salBrutoAnual=" + salBrutoAnual +
                '}';
    }

    //endregion

}
