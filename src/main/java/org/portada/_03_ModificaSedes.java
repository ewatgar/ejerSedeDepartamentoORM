package org.portada;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.portada.modelo.Sede;

public class _03_ModificaSedes {
    public static void main(String[] args) {
        try (SessionFactory sessionFactory = new Configuration().configure().buildSessionFactory();
             Session s = sessionFactory.openSession()) {
            System.out.println("Sesión iniciada");

            Transaction t = null;
            try {
                Sede s1 = s.find(Sede.class,1);
                Sede s2 = s.find(Sede.class,2);
                if (s1 == null || s2 == null) {
                    System.out.println("ERROR: Una de las sedes no existen");
                    return;
                }

                t = s.beginTransaction();
                s.remove(s1);
                s2.setNombre("nuevoNombre");
                s.persist(s2);
                t.commit();

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
