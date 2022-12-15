package Domain.Repositorios;

import Domain.Espacios.Estacion;
import Domain.MediosDeTransporte.*;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.HashMap;

public class RepositorioTransportePublicoDB extends Repositorio {

    public RepositorioTransportePublicoDB() {
        super(new DBHibernate<TransportePublico>(TransportePublico.class));
    }

    public TransportePublico crearTransportePublico(TipoTransportePublico tipoTransportePublico, String linea){
        TransportePublico transportePublico = new TransportePublico(tipoTransportePublico, linea, new HashMap<>());

        this.dbService.agregar(transportePublico);
        return transportePublico;
    }

    public TransportePublico buscarTransportePublico(Integer id){
        return (TransportePublico) this.dbService.buscar(condicionTransportePorID(id));
    }


    private BusquedaCondicional condicionTransportePorID(Integer id){
        CriteriaBuilder criteriaBuilder = criteriaBuilder();
        CriteriaQuery<TransportePublico> transportePublicoCriteriaQuery = criteriaBuilder.createQuery(TransportePublico.class);

        Root<TransportePublico> root = transportePublicoCriteriaQuery.from(TransportePublico.class);

        Predicate condicionPorID = criteriaBuilder.like(root.get("id_transporte"), "%"+ id.toString() +"%");

        transportePublicoCriteriaQuery.where(condicionPorID);

        return new BusquedaCondicional(null, transportePublicoCriteriaQuery);
    }

    public TransportePublico buscarTransportePublicoPorLinea(String linea){

        return (TransportePublico) this.dbService.buscar(condicionTransportePublicoPorLinea(linea));

    }

    private BusquedaCondicional condicionTransportePublicoPorLinea(String linea) {
        CriteriaBuilder criteriaBuilder = criteriaBuilder();
        CriteriaQuery<TransportePublico> transportePublicoCriteriaQuery = criteriaBuilder.createQuery(TransportePublico.class);

        Root<TransportePublico> condicionRaiz = transportePublicoCriteriaQuery.from(TransportePublico.class);

        //condicionRaiz.join("usuario");

        Predicate condicionLinea = criteriaBuilder.equal(condicionRaiz.get("linea"),  linea );
        //Predicate condicionContrasenia = criteriaBuilder.equal(condicionRaiz.get("password"), contrasenia);

        //Predicate condicionExisteUsuario = criteriaBuilder.and(condicionNombreDeUsuario, condicionContrasenia);

        transportePublicoCriteriaQuery.where(condicionLinea);

        return new BusquedaCondicional(null, transportePublicoCriteriaQuery);
    }
}
