package org.portada.modelo;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Objects;

@Entity
@Table(name = "departamento", schema = "empresa_proyectos")
public class Departamento {

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

    @OneToMany(mappedBy = "departamento")
    private Collection<Empleado> empleados = new ArrayList<>();


    @ManyToOne
    @JoinColumn(name = "id_sede", nullable = false)
    private Sede sede;

    //endregion

    //region Constructores

    public Departamento(String nombre) {
        this.nombre = nombre;
    }

    public Departamento() {
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

    public Collection<Empleado> getEmpleados() {
        return empleados;
    }

    public void setEmpleados(Collection<Empleado> empleados) {
        this.empleados = empleados;
    }

    public Sede getSede() {
        return sede;
    }

    public void setSede(Sede sede) {
        this.sede = sede;
    }

    //endregion

    //region Equals Hash toString

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Departamento that)) return false;
        return Objects.equals(id, that.id) && Objects.equals(nombre, that.nombre) && Objects.equals(empleados, that.empleados) && Objects.equals(sede, that.sede);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, nombre, empleados, sede);
    }

    @Override
    public String toString() {
        return "Departamento{" +
                "id=" + id +
                ", nombre='" + nombre + '\'' +
                ", empleados=" + empleados +
                ", sede=" + sede +
                '}';
    }

    //endregion

}
