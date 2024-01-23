package org.portada.modelo;

import jakarta.persistence.*;

import java.util.Objects;

@Entity
@Table(name = "empleado", schema = "empresa_proyectos")
public class Empleado {

    //region Atributos

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id", nullable=false)
    private Long id;

    @Basic
    @Column(name="nombre", nullable=false)
    private String nombre;

    @Basic
    @Column(name="num_emp", nullable=false)
    private Integer numEmp;

    //endregion

    //region Relaciones

    @OneToOne
    @JoinColumn(name = "id_datos_profesionales", nullable = false)
    private DatosProfesionales datosProfesionales;

    @ManyToOne
    @JoinColumn(name = "id_departamento",nullable = false)
    private Departamento departamento;

    //endregion

    //region Constructores

    public Empleado(String nombre, Integer numEmp) {
        this.nombre = nombre;
        this.numEmp = numEmp;
    }
    public Empleado() {
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

    public Integer getNumEmp() {
        return numEmp;
    }

    public void setNumEmp(Integer numEmp) {
        this.numEmp = numEmp;
    }

    public DatosProfesionales getDatosProfesionales() {
        return datosProfesionales;
    }

    public void setDatosProfesionales(DatosProfesionales datosProfesionales) {
        this.datosProfesionales = datosProfesionales;
    }

    public Departamento getDepartamento() {
        return departamento;
    }

    public void setDepartamento(Departamento departamento) {
        this.departamento = departamento;
    }


    //endregion

    //region Equals Hash toString

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Empleado empleado)) return false;
        return Objects.equals(id, empleado.id) && Objects.equals(nombre, empleado.nombre) && Objects.equals(numEmp, empleado.numEmp) && Objects.equals(datosProfesionales, empleado.datosProfesionales) && Objects.equals(departamento, empleado.departamento);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, nombre, numEmp, datosProfesionales, departamento);
    }

    @Override
    public String toString() {
        return "Empleado{" +
                "id=" + id +
                ", nombre='" + nombre + '\'' +
                ", numEmp=" + numEmp +
                ", datosProfesionales=" + datosProfesionales +
                ", departamento=" + departamento +
                '}';
    }

    //endregion

}
