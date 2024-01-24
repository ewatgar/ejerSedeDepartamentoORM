package org.portada.modelo;

import jakarta.persistence.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Objects;

@Entity
@Table(name = "proyecto", schema = "empresa_proyectos")
public class Proyecto {

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

    @ManyToMany
    @JoinColumn(name = "departamentos")
    private Collection<Departamento> departamentos = new ArrayList<>();

    //endregion

    //region Constructores

    public Proyecto(String nombre) {
        this.nombre = nombre;
    }

    public Proyecto() {
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
        if (!(o instanceof Proyecto proyecto)) return false;
        return Objects.equals(id, proyecto.id) && Objects.equals(nombre, proyecto.nombre) && Objects.equals(departamentos, proyecto.departamentos);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, nombre, departamentos);
    }

    @Override
    public String toString() {
        return "Proyecto{" +
                "id=" + id +
                ", nombre='" + nombre + '\'' +
                ", departamentos=" + departamentos +
                '}';
    }

    //endregion

}
