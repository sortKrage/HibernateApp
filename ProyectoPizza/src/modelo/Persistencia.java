/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo;

import controlador.*;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Vector;

/**
 *
 * @author sortKrage
 */
public interface Persistencia {

    Vector cargarEmp(int cod) throws SQLException;

    ArrayList<Empleado> consultarEmp(String cod) throws SQLException;

    Vector cargarArt() throws SQLException;

    ArrayList<Cliente> consultarCli(int cod) throws SQLException;

    public void guardarModCli(Cliente c) throws SQLException;

    public void guardarPedido(Pedido p) throws SQLException;

    public void guardarModEmp(Empleado emp) throws SQLException;

    public Vector cargarPuesto() throws SQLException;

    public ArrayList<Articulo> consultarArt(int cod) throws SQLException;

    public void guarModArt(Articulo a) throws SQLException;

    public Vector estadisticaPorFecha(String text, String text0) throws SQLException;

    public Vector estadisticaPorCod(String cliente, String text) throws SQLException;

    public void guardarVenta(Venta v) throws SQLException;

    public void actualizarHoras(Vector emp) throws SQLException;

    public ArrayList<String> cargarContabilidad(String cod) throws SQLException;

}
