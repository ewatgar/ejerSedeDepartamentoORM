package org.portada.modelo;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Objects;

@Entity
@Table(name = "sede", schema = "empresa_proyectos")
public class Sede {

    //region Atributos

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id", nullable=false)
    private Long id;

    @Basic
    @Column(name="nombre", nullable=false)
    private String nombre;

    //endregion

    //region Relaciones

    @OneToMany(mappedBy = "sede")
    private Collection<Departamento> departamentos = new ArrayList<>();

    //endregion

    //region Constructores

    public Sede(String nombre) {
        this.nombre = nombre;
    }

    public Sede() {
    }

    //endregion

    //region Getters Setters

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

    public Collection<Departamento> getDepartamentos() {
        return departamentos;
    }

    public void setDepartamentos(Collection<Departamento> departamentos) {
        this.departamentos = departamentos;
    }

    //endregion

    //region Equals Hash toString

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Sede sede)) return false;
        return Objects.equals(id, sede.id) && Objects.equals(nombre, sede.nombre) && Objects.equals(departamentos, sede.departamentos);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, nombre, departamentos);
    }

    @Override
    public String toString() {
        return "Sede{" +
                "id=" + id +
                ", nombre='" + nombre + '\'' +
                ", departamentos=" + departamentos +
                '}';
    }

    //endregion

}
