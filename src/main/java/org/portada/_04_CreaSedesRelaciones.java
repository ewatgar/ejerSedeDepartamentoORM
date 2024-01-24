package org.portada;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.portada.modelo.DatosProfesionales;
import org.portada.modelo.Departamento;
import org.portada.modelo.Empleado;
import org.portada.modelo.Sede;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class _04_CreaSedesRelaciones {
    public static void main(String[] args) {
        try (SessionFactory sessionFactory = new Configuration().configure().buildSessionFactory();
             Session s = sessionFactory.openSession()) {
            System.out.println("Sesión iniciada");

            Transaction t = null;
            try {
                t = s.beginTransaction();

                //region Creacion Sedes
                Sede s1 = new Sede("sede1");
                s.persist(s1);

                Sede s2 = new Sede("sede2");
                s.persist(s2);
                //endregion

                //region Creacion Departamentos
                Departamento d1_1 = new Departamento("dept1_1");
                d1_1.setSede(s1);
                s.persist(d1_1);

                Departamento d1_2 = new Departamento("dept1_2");
                d1_2.setSede(s1);
                s.persist(d1_2);

                Departamento d2_1 = new Departamento("dept2_1");
                d2_1.setSede(s2);
                s.persist(d2_1);

                Departamento d2_2 = new Departamento("dept2_2");
                d2_2.setSede(s2);
                s.persist(d2_2);
                //endregion

                //region Bidireccional Sede-Departamento
                //s1.setDepartamentos(List.of(d1_1,d1_2));
                //s2.setDepartamentos(List.of(d2_1,d2_2));

                s1.getDepartamentos().addAll(List.of(d1_1,d1_2));
                s2.getDepartamentos().addAll(List.of(d2_1,d2_2));
                //endregion

                //region Creacion Empleados
                Empleado e1 = new Empleado("Eustaquio",100);
                DatosProfesionales dp1 = new DatosProfesionales(1L,"categoria",15000.0);
                s.persist(dp1);
                e1.setDatosProfesionales(dp1);
                e1.setDepartamento(d1_1);
                s.persist(e1);

                Empleado e2 = new Empleado("María",114);
                DatosProfesionales dp2 = new DatosProfesionales(2L,"categoria",15000.0);
                s.persist(dp2);
                e2.setDatosProfesionales(dp2);
                e2.setDepartamento(d2_1);
                s.persist(e2);
                //endregion

                t.commit();

                Sede s1Updated = s.find(Sede.class,s1.getId());
                Sede s2Updated = s.find(Sede.class,s2.getId());
                //System.out.println(s1Updated.toString());
                //System.out.println(s2Updated.toString());

                System.out.println("Se han añadido las sedes");

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
