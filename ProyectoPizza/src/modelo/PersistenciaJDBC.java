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
import java.sql.Connection;
import java.util.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Properties;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

/**
 *
 * @author sortKrage
 */
public class PersistenciaJDBC implements Persistencia {

    Connection cn;

    public PersistenciaJDBC() throws SQLException {

        Properties prop = new Properties();
        try {
            prop.load(new InputStreamReader(PersistenciaJDBC.class.getResourceAsStream("CFG.INI")));
        } catch (IOException ex) {
            JOptionPane.showMessageDialog(null, "Compruebe los permisos del CFG.INI",
                    "ERROR FATAL", JOptionPane.ERROR_MESSAGE);
        }
        String server = prop.getProperty("mysqlJDBC.servidor").toLowerCase().trim();
        String bbdd = prop.getProperty("mysqlJDBC.basedatos").trim();
        String usu = prop.getProperty("mysqlJDBC.usuario").toLowerCase().trim();
        String cont = prop.getProperty("mysqlJDBC.password").toLowerCase().trim();
        this.cn = DriverManager.getConnection("jdbc:mysql://" + server + "/" + bbdd,
                usu, cont);

    }

    @Override
    public Vector cargarEmp(int cod) throws SQLException {

        Vector fila = new Vector();

        PreparedStatement pst = cn.prepareStatement("SELECT * FROM Empleado"
                + " WHERE idEmpleado = ?");
        pst.setInt(1, cod);
        ResultSet rs = pst.executeQuery();

        if (rs.next()) {

            fila.add(rs.getString("idEmpleado"));
            fila.add(rs.getString("Ape1"));
            fila.add(rs.getString("Ape2"));
            fila.add(rs.getString("Nombre"));

        }

        return fila;
    }

    /**
     *
     * Este método carga desde la BBDD todos los artículos y lo devuelve como un
     * Vector que contiene Vectores con los datos de cada artículo (Necesario
     * para el contructor del "DefaultTableModel")
     *
     * @return Vector
     * @throws SQLException
     */
    @Override
    public Vector cargarArt() throws SQLException {

        Vector listArt = new Vector();

        Statement s = cn.createStatement();

        ResultSet rs = s.executeQuery("SELECT * FROM Articulo");

        while (rs.next()) {

            Vector fila = new Vector();

            fila.add(rs.getString("idArticulo"));
            fila.add(rs.getString("Nombre"));
            fila.add(rs.getString("Precio"));

            listArt.add(fila);

        }

        return listArt;
    }

    @Override
    public ArrayList<Cliente> consultarCli(int cod) throws SQLException {
        ArrayList<Cliente> cliente = new ArrayList<>();

        PreparedStatement ps = cn.prepareStatement("SELECT * FROM Cliente WHERE idCliente = ?");
        ps.setInt(1, cod);

        ResultSet rs = ps.executeQuery();

        while (rs.next()) {

            cliente.add(new Cliente(rs.getInt("idCliente"), rs.getString("Nombre"),
                    rs.getString("Ape1"), rs.getString("Ape2"), rs.getString("Direccion")));

        }

        return cliente;
    }

    @Override
    public void guardarModCli(Cliente c) throws SQLException {

        PreparedStatement ps;

        ArrayList<Cliente> cs = consultarCli(c.getIdCliente());

        if (cs.isEmpty()) {

            ps = cn.prepareStatement("INSERT INTO Cliente VALUES(?,?,?,?,?)");

            ps.setInt(1, c.getIdCliente());
            ps.setString(2, c.getNombre());
            ps.setString(3, c.getApe1());
            ps.setString(4, c.getApe2());
            ps.setString(5, c.getDireccion());

        } else {

            ps = cn.prepareStatement("UPDATE Cliente SET Nombre = ?, Ape1 = ?, Ape2 = ?,"
                    + "Direccion = ? WHERE idCliente = ?");

            ps.setString(1, c.getNombre());
            ps.setString(2, c.getApe1());
            ps.setString(3, c.getApe2());
            ps.setString(4, c.getDireccion());
            ps.setInt(5, c.getIdCliente());
        }

        ps.executeUpdate();

    }

    @Override
    public void guardarPedido(Pedido p) throws SQLException {

        PreparedStatement ps = cn.prepareStatement("INSERT INTO Pedido (idCliente,idEmpleado, fecha, importe)"
                + " VALUES(?,?,?,?)");
        ps.setInt(1, p.getCliente());
        ps.setInt(2, p.getEmpleado());
        ps.setDate(3, p.getFecha());
        ps.setDouble(4, p.getImporte());

        ps.executeUpdate();

    }

    @Override
    public ArrayList<Empleado> consultarEmp(String cod) throws SQLException {

        ArrayList<Empleado> resul = new ArrayList<>();

        PreparedStatement ps = cn.prepareStatement("SELECT * FROM Empleado WHERE idEmpleado = ?");
        ps.setString(1, cod);

        ResultSet rs = ps.executeQuery();

        if (rs.next()) {

            resul.add(new Empleado(rs.getInt("idEmpleado"), (Puesto) new Puesto(rs.getInt("idPuesto")),
                    rs.getString("Nombre"), rs.getString("Ape1"), rs.getString("Ape2"),
                    rs.getLong("Minutos")));

        }

        return resul;
    }

    @Override
    public void guardarModEmp(Empleado emp) throws SQLException {

        ArrayList<Empleado> resul = null;
        PreparedStatement ps;

        resul = consultarEmp(String.valueOf(emp.getIdEmpleado()));

        if (resul.isEmpty()) {

            ps = cn.prepareStatement("INSERT INTO Empleado VALUES (?,?,?,?,?)");
            ps.setInt(1, emp.getIdEmpleado());
            ps.setString(2, emp.getNombre());
            ps.setString(3, emp.getApe1());
            ps.setString(4, emp.getApe2());
            ps.setInt(5, emp.getPuesto().getIdPuesto());

        } else {

            ps = cn.prepareStatement("UPDATE Empleado SET Nombre = ?, Ape1 = ?,"
                    + " Ape2 = ?, idPuesto = ? WHERE idEmpleado = ?");
            ps.setString(1, emp.getNombre());
            ps.setString(2, emp.getApe1());
            ps.setString(3, emp.getApe2());
            ps.setInt(4, emp.getPuesto().getIdPuesto());
            ps.setInt(5, emp.getIdEmpleado());

        }

        ps.executeUpdate();

    }

    @Override
    public Vector cargarPuesto() throws SQLException {

        Vector data = new Vector();

        Statement s = cn.createStatement();

        ResultSet rs = s.executeQuery("SELECT * FROM Puesto");

        while (rs.next()) {

            data.add(rs.getString("Nombre"));

        }

        return data;
    }

    @Override
    public ArrayList<Articulo> consultarArt(int cod) throws SQLException {
        ArrayList<Articulo> a = new ArrayList<>();

        PreparedStatement ps = cn.prepareStatement("SELECT * FROM Articulo WHERE idArticulo = ?");
        ps.setInt(1, cod);

        ResultSet rs = ps.executeQuery();

        if (rs.next()) {

            a.add(new Articulo(rs.getInt("idArticulo"), rs.getString("Nombre"), rs.getString("Descripcion"),
                    rs.getDouble("Precio")));

        }

        return a;
    }

    @Override
    public void guarModArt(Articulo art) throws SQLException {

        ArrayList<Articulo> a = consultarArt(art.getIdArticulo());

        PreparedStatement ps;

        if (a.isEmpty()) {

            ps = cn.prepareStatement("INSERT INTO Articulo VALUES (?,?,?,?)");
            ps.setInt(1, art.getIdArticulo());
            ps.setString(2, art.getNombre());
            ps.setString(3, art.getDescripcion());
            ps.setDouble(4, art.getPrecio());

        } else {

            ps = cn.prepareStatement("UPDATE Articulo SET Nombre = ?, Descripcion = ?, Precio = ? WHERE idArticulo = ?");
            ps.setString(1, art.getNombre());
            ps.setString(2, art.getDescripcion());
            ps.setDouble(3, art.getPrecio());
            ps.setInt(4, art.getIdArticulo());

        }

        ps.executeUpdate();

    }

    @Override
    public Vector estadisticaPorFecha(String date, String date2) throws SQLException {
        Vector resul = new Vector();

        PreparedStatement ps = cn.prepareStatement("SELECT * FROM Pedido WHERE Fecha BETWEEN ? AND ?");
        ps.setString(1, date);
        ps.setString(2, date2);

        ResultSet rs = ps.executeQuery();

        while (rs.next()) {
            Vector fila = new Vector();

            fila.add(rs.getString("idPedido"));
            fila.add(rs.getString("importe"));

            resul.add(fila);
        }

        return resul;
    }

    @Override
    public Vector estadisticaPorCod(String tabla, String cod) throws SQLException {
        String id = "id" + tabla;
        Vector resul = new Vector();

        PreparedStatement ps = cn.prepareStatement("SELECT * FROM Pedido WHERE " + id + " = ?");
        ps.setString(1, cod);

        ResultSet rs = ps.executeQuery();

        while (rs.next()) {

            Vector fila = new Vector();
            fila.add(rs.getString("idPedido"));
            fila.add(rs.getShort("importe"));

            resul.add(fila);

        }

        return resul;
    }

    @Override
    public void guardarVenta(Venta v) throws SQLException {

        Statement st = cn.createStatement();
        ResultSet rs = st.executeQuery("SELECT * FROM Pedido");

        int cod = 0;

        while (rs.next()) {

            cod = rs.getInt("idPedido");

        }

        PreparedStatement ps = cn.prepareStatement("INSERT INTO Venta VALUES (?,?,?)");
        ps.setInt(1, v.getIdArticulo());
        ps.setInt(2, cod);
        ps.setInt(3, v.getCantidad());

        ps.executeUpdate();

    }

    @Override
    public void actualizarHoras(Vector emp) throws SQLException {

        SimpleDateFormat sdf = new SimpleDateFormat("HH:mm");

        try {

            Date horaEntrada = sdf.parse(String.valueOf(emp.get(4)));

            Date horaSalida = new Date(System.currentTimeMillis());

            String s = sdf.format(horaSalida);

            horaSalida = sdf.parse(s);

            long minutosTrabajo = (horaSalida.getTime() - horaEntrada.getTime()) / (1000 * 60);

            PreparedStatement ps = cn.prepareStatement("UPDATE Empleado SET Minutos = Minutos + ? WHERE idEmpleado = ?");
            ps.setLong(1, minutosTrabajo);
            ps.setString(2, String.valueOf(emp.get(0)));

            ps.executeUpdate();

        } catch (ParseException ex) {
            Logger.getLogger(PersistenciaJDBC.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    @Override
    public ArrayList<String> cargarContabilidad(String cod) throws SQLException {
        ArrayList<String> data = new ArrayList<>();

        Statement s = cn.createStatement();
        ResultSet rs = s.executeQuery("SELECT e.Nombre, e.Ape1, e.Ape2, e.Minutos, p.Salario "
                + "FROM Empleado e JOIN Puesto p WHERE e.idEmpleado = " + cod + " AND e.idPuesto = p.idPuesto");

        if (rs.next()) {

            data.add(rs.getString("e.Nombre"));
            data.add(rs.getString("e.Ape1"));
            data.add(rs.getString("e.Ape2"));
            data.add(rs.getString("e.Minutos"));
            data.add(rs.getString("p.Salario"));

        }

        return data;
    }

}
