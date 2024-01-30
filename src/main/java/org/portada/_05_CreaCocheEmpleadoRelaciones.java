package org.portada;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.portada.modelo.*;

import java.time.LocalDate;
import java.util.List;

public class _05_CreaCocheEmpleadoRelaciones {
    public static void main(String[] args) {
        try (SessionFactory sessionFactory = new Configuration().configure().buildSessionFactory();
             Session s = sessionFactory.openSession()) {
            System.out.println("Sesión iniciada");

            Transaction t = null;
            try {
                t = s.beginTransaction();

                Sede s1 = new Sede("sede1");
                s.persist(s1);

                Departamento d1_1 = new Departamento("dept1_1");
                d1_1.setSede(s1);
                s.persist(d1_1);

                s1.getDepartamentos().addAll(List.of(d1_1));
                //s.refresh(s1);

                // -----------------------------------------------------

                //region Creacion Empleados
                Empleado e1 = new Empleado("Eustaquio",100);
                e1.setDepartamento(d1_1);
                s.persist(e1);

                Empleado e2 = new Empleado("María",114);
                e2.setDepartamento(d1_1);
                s.persist(e2);
                //endregion

                //region Creacion DatosProfesionales
                DatosProfesionales dp1 = new DatosProfesionales(1L,"categoria",15000.0);
                s.persist(dp1);

                DatosProfesionales dp2 = new DatosProfesionales(2L,"categoria",15000.0);
                s.persist(dp2);
                //endregion

                CocheEmpresa ce1 = new CocheEmpresa("ABCD",null);
                s.persist(ce1);

                //region Relaciones
                e1.setDatosProfesionales(dp1);
                e2.setDatosProfesionales(dp2);
                ce1.setEmpleado(e1);
                ce1.setEmpleado(e2);
                s.persist(ce1);
                //endregion

                t.commit();

                System.out.println("Se han añadido");

            } catch (Exception e) {
                e.printStackTrace();
                if (t != null) {
                    System.out.println("ERROR: ROLLBACK");
                    t.rollback();
                }
            }
        }
    }
}
