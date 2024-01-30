package org.portada;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.portada.modelo.*;

import java.time.LocalDate;
import java.util.List;

public class _06_CreaProyectoRelaciones {
    public static void main(String[] args) {
        try (SessionFactory sessionFactory = new Configuration().configure().buildSessionFactory();
             Session s = sessionFactory.openSession()) {
            System.out.println("Sesión iniciada");

            Transaction t = null;
            try {
                t = s.beginTransaction();

                Sede s1 = new Sede("sede1");
                s.persist(s1);

                Departamento d1 = new Departamento("dept1");
                d1.setSede(s1);
                s.persist(d1);

                Departamento d2 = new Departamento("dept2");
                d2.setSede(s1);
                s.persist(d2);

                s1.getDepartamentos().addAll(List.of(d1,d2));

                Proyecto p1 = new Proyecto("proy1");
                p1.getDepartamentos().addAll(List.of(d1,d2));
                s.persist(p1);

                Proyecto p2 = new Proyecto("proy2");
                p2.getDepartamentos().addAll(List.of(d1,d2));
                s.persist(p2);

                t.commit();

                System.out.println("Se han añadido los proyectos a los departamentos");

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
