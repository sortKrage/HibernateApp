/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo;

import controlador.Articulo;
import controlador.Cliente;
import controlador.Empleado;
import controlador.Pedido;
import controlador.Puesto;
import controlador.Venta;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Properties;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import org.hibernate.*;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;
import org.hibernate.service.ServiceRegistryBuilder;

/**
 *
 * @author sortkrage
 */
public class PersistenciaHibernate implements Persistencia {

    Session sesion;

    public PersistenciaHibernate() {

        Properties prop = new Properties();
        try {
            prop.load(new InputStreamReader(PersistenciaJDBC.class.getResourceAsStream("CFG.INI")));
        } catch (IOException ex) {
            JOptionPane.showMessageDialog(null, "Compruebe los permisos del CFG.INI", "ERROR FATAL",
                    JOptionPane.ERROR_MESSAGE);
        }

        String link = prop.getProperty("hibernate.archivoCFG");

        SessionFactory sessionFactory;
        Configuration configuration = new Configuration();
        configuration.configure(link);

        configuration.setProperty("hibernate.show_sql", "true");

        ServiceRegistry serviceRegistry = new ServiceRegistryBuilder().applySettings(configuration.getProperties())
                .buildServiceRegistry();
        sessionFactory = configuration.buildSessionFactory(serviceRegistry);
        sesion = sessionFactory.openSession();

    }

    @Override
    public Vector cargarEmp(int cod) throws SQLException {
        Vector fila = new Vector();

        Query q = sesion.createQuery("SELECT e FROM Empleado e WHERE idEmpleado = ?");
        q.setInteger(0, cod);

        List list = q.list();

        for (int i = 0; i < list.size(); i++) {

            Empleado emp = (Empleado) list.get(i);

            fila.add(emp.getIdEmpleado());
            fila.add(emp.getApe1());
            fila.add(emp.getApe2());
            fila.add(emp.getNombre());

        }

        return fila;
    }

    @Override
    public ArrayList<Empleado> consultarEmp(String cod) throws SQLException {
        ArrayList<Empleado> resul = new ArrayList<>();

        Query q = sesion.createQuery("SELECT e FROM Empleado e WHERE idEmpleado = ?");
        q.setString(0, cod);

        List list = q.list();

        for (int i = 0; i < list.size(); i++) {

            Empleado e = (Empleado) list.get(i);

            resul.add(e);

        }

        return resul;
    }

    @Override
    public Vector cargarArt() throws SQLException {
        Vector listArt = new Vector();

        Query q = sesion.createQuery("FROM Articulo");

        List list = q.list();

        for (int i = 0; i < list.size(); i++) {

            Vector fila = new Vector();
            Articulo art = (Articulo) list.get(i);

            fila.add(art.getIdArticulo());
            fila.add(art.getNombre());
            fila.add(String.valueOf(art.getPrecio()));

            listArt.add(fila);

        }

        return listArt;
    }

    @Override
    public ArrayList<Cliente> consultarCli(int cod) throws SQLException {
        ArrayList<Cliente> resul = new ArrayList<>();

        Query q = sesion.createQuery("SELECT c FROM Cliente c WHERE idCliente = ?");
        q.setInteger(0, cod);

        List list = q.list();

        for (int i = 0; i < list.size(); i++) {
            Cliente c = (Cliente) list.get(i);

            resul.add(c);
        }

        return resul;
    }

    @Override
    public void guardarModCli(Cliente c) throws SQLException {

        sesion.beginTransaction();
        sesion.saveOrUpdate(c);
        sesion.getTransaction().commit();

    }

    @Override
    public void guardarPedido(Pedido p) throws SQLException {

        sesion.beginTransaction();
        sesion.saveOrUpdate(p);
        sesion.getTransaction().commit();

    }

    @Override
    public void guardarModEmp(Empleado emp) throws SQLException {

        sesion.beginTransaction();
        sesion.merge(emp);
        sesion.getTransaction().commit();

    }

    @Override
    public Vector cargarPuesto() throws SQLException {
        Vector resul = new Vector();

        Query q = sesion.createQuery("FROM Puesto");
        List list = q.list();

        for (int i = 0; i < list.size(); i++) {
            Puesto p = (Puesto) list.get(i);

            resul.add(p.getNombre());
        }

        return resul;
    }

    @Override
    public ArrayList<Articulo> consultarArt(int cod) throws SQLException {
        ArrayList<Articulo> resul = new ArrayList<>();
        Query q = sesion.createQuery("SELECT a FROM Articulo a WHERE idArticulo = ?");
        q.setInteger(0, cod);
        List list = q.list();

        for (int i = 0; i < list.size(); i++) {

            Articulo a = (Articulo) list.get(i);

            resul.add(a);

        }

        return resul;
    }

    @Override
    public void guarModArt(Articulo a) throws SQLException {

        sesion.beginTransaction();
        sesion.merge(a);
        sesion.getTransaction().commit();

    }

    @Override
    public Vector estadisticaPorFecha(String fecha, String fecha2) throws SQLException {
        Vector resul = new Vector();

        Query q = sesion.createQuery("SELECT p FROM Pedido p WHERE Fecha BETWEEN ? AND ?");
        q.setString(0, fecha);
        q.setString(1, fecha2);

        List list = q.list();

        for (int i = 0; i < list.size(); i++) {

            Pedido p = (Pedido) list.get(i);

            Vector fila = new Vector();

            fila.add(p.getIdPedido());
            fila.add(p.getImporte());

            resul.add(fila);

        }

        return resul;
    }

    @Override
    public Vector estadisticaPorCod(String tabla, String cod) throws SQLException {
        Vector resul = new Vector();

        String id = "id" + tabla;

        Query q = sesion.createQuery("SELECT p FROM Pedido p WHERE " + id + " = ?");
        q.setString(0, cod);

        List list = q.list();

        for (int i = 0; i < list.size(); i++) {

            Vector fila = new Vector();

            Pedido p = (Pedido) list.get(i);

            fila.add(p.getIdPedido());
            fila.add(p.getImporte());

            resul.add(fila);

        }

        return resul;
    }

    @Override
    public void guardarVenta(Venta v) throws SQLException {

        Query q = sesion.createQuery("FROM Pedido");
        List list = q.list();

        int cod = 0;

        for (int i = 0; i < list.size(); i++) {
            Pedido p = (Pedido) list.get(i);

            cod = p.getIdPedido();

        }

        v.setIdPedido(cod);

        sesion.beginTransaction();
        sesion.saveOrUpdate(v);
        sesion.getTransaction().commit();

    }

    @Override
    public void actualizarHoras(Vector emp) throws SQLException {

        try {

            SimpleDateFormat sdf = new SimpleDateFormat("HH:mm");

            Date horaEntrada = sdf.parse(String.valueOf(emp.get(4)));

            Date horaSalida = new Date(System.currentTimeMillis());

            String s = sdf.format(horaSalida);

            horaSalida = sdf.parse(s);

            long minutosTrabajo = (horaSalida.getTime() - horaEntrada.getTime()) / (1000 * 60);

            int cod = Integer.parseInt(String.valueOf(emp.get(0)));

            Empleado e = (Empleado) sesion.get(Empleado.class, cod);

            e.setMinutos(minutosTrabajo);

            sesion.beginTransaction();
            sesion.update(e);
            sesion.getTransaction().commit();

        } catch (ParseException ex) {
            Logger.getLogger(PersistenciaHibernate.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    @Override
    public ArrayList<String> cargarContabilidad(String cod) throws SQLException {

        ArrayList<String> data = new ArrayList<>();

        Query q = sesion.createQuery("SELECT e FROM Empleado e WHERE idEmpleado = " + cod);

        Empleado e = (Empleado) q.uniqueResult();

        System.out.println(e);

        data.add(String.valueOf(e.getNombre()));
        data.add(String.valueOf(e.getApe1()));
        data.add(String.valueOf(e.getApe2()));
        data.add(String.valueOf(e.getMinutos()));
        data.add(String.valueOf(e.getPuesto().getSalarioHora()));

        return data;
    }

}
