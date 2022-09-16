package Domain.Reportes;

import Domain.CalculadorHC.CalculadorHC;
import Domain.Organizacion.Organizacion;
import Domain.Organizacion.RepositorioAgentes;
import Domain.Organizacion.RepositorioOrganizaciones;
import Domain.Organizacion.TipoOrganizacion;

public class GeneradorReportes {

    private RepositorioOrganizaciones repositorioOrganizaciones = RepositorioOrganizaciones.GetInstance();
    private RepositorioAgentes repositorioAgentes = RepositorioAgentes.GetInstance();
    private CalculadorHC calculadorHC = CalculadorHC.getInstance();


    public void reporteHC_Org(TipoOrganizacion tipo){

        Double cantidadHC = 0.0;

        for (Organizacion organizacion : repositorioOrganizaciones.getOrganizaciones()){
            if (organizacion.getTipo() == tipo){
                cantidadHC += organizacion.calcularHcTotal();
            }
        }
    }

    public void reporteHC_SecTer(){

    }

    public void reporteCompHC_Org(){

    }

    public void reporteCompHC_SecTer(){

    }

    public void reporteCompHC_Pais(){

    }

    public void reporteEvolucionHC_Org(){

    }

    public void reporteEvolucionHC_SecTer(){

    }
    
}
