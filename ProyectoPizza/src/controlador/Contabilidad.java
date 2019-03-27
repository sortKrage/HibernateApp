/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlador;

import java.io.Serializable;

/**
 *
 * @author sortkrage
 */
public class Contabilidad implements Serializable {

    private String nombreEmp, ape1Emp, ape2Emp, horas, salario;

    public Contabilidad() {
    }

    public Contabilidad(String nombreEmp, String ape1Emp, String ape2Emp, String horas, String salario) {
        this.nombreEmp = nombreEmp;
        this.ape1Emp = ape1Emp;
        this.ape2Emp = ape2Emp;
        this.horas = horas;
        this.salario = salario;
    }

    public String getNombreEmp() {
        return nombreEmp;
    }

    public void setNombreEmp(String nombreEmp) {
        this.nombreEmp = nombreEmp;
    }

    public String getApe1Emp() {
        return ape1Emp;
    }

    public void setApe1Emp(String ape1Emp) {
        this.ape1Emp = ape1Emp;
    }

    public String getApe2Emp() {
        return ape2Emp;
    }

    public void setApe2Emp(String ape2Emp) {
        this.ape2Emp = ape2Emp;
    }

    public String getHoras() {
        return horas;
    }

    public void setHoras(String horas) {
        this.horas = horas;
    }

    public String getSalario() {
        return salario;
    }

    public void setSalario(String salario) {
        this.salario = salario;
    }

    @Override
    public String toString() {
        return "Contabilidad{" + "nombreEmp=" + nombreEmp + ", ape1Emp=" + ape1Emp + ", ape2Emp=" + ape2Emp + ", horas=" + horas + ", salario=" + salario + '}';
    }

}
