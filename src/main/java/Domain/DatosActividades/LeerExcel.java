package Domain.DatosActividades;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.text.ParseException;
import java.util.Date;
import java.text.SimpleDateFormat;

public class LeerExcel {

    private String actividad;
    private String tipoConsumo;
    private float valor;
    private String periodicidad;
    private Date periodoDeImputacion;

    public String getActividad() {
        return actividad;
    }

    public void setActividad(String actividad) {
        this.actividad = actividad;
    }

    public String getTipoConsumo() {
        return tipoConsumo;
    }

    public void setTipoConsumo(String tipoConsumo) {
        this.tipoConsumo = tipoConsumo;
    }

    public float getValor() {
        return valor;
    }

    public void setValor(float valor) {
        this.valor = valor;
    }

    public String getPeriodicidad() {
        return periodicidad;
    }

    public void setPeriodicidad(String periodicidad) {
        this.periodicidad = periodicidad;
    }

    public Date getPeriodoDeImputacion() {
        return periodoDeImputacion;
    }

    public void setPeriodoDeImputacion(Date periodoDeImputacion) {
        this.periodoDeImputacion = periodoDeImputacion;
    }

    public Date leerPeriodoDeImputacion(String fecha, String formato)
    {
    //    try{
    //        Date ret = new SimpleDateFormat(formato).parse(fecha);
    //        return ret;
    //    }
    //    catch(ParseException e) {
    //        return null;
    //    }
        return null;
    }

    //ARCHIVO CSV
    public void cargarExcel(String path) {
        try
        {
            FileReader fr = new FileReader(path);
            BufferedReader br = new BufferedReader(fr);
            //PRIMER LINEA SON TITULOS DE COLUMNA, NO DATOS
            br.readLine();

            String row = br.readLine();
            String[] datos = row.split(";");

            this.actividad = datos[0];
            this.tipoConsumo = datos[1];
            this.valor = Float.parseFloat(datos[2]);
            this.periodicidad = datos[3];
            if(periodicidad.equals("Mensual"))
                this.periodoDeImputacion = this.leerPeriodoDeImputacion(datos[4],"MM/YYYY");
            else
                this.periodoDeImputacion = this.leerPeriodoDeImputacion(datos[4], "YYYY");

            br.close();
        }
        catch (FileNotFoundException e)
        {
            System.out.print("Archivo no valido");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}