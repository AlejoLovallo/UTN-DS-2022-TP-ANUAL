package Domain.Repositorios;

import Domain.Espacios.Direccion;
import Domain.Espacios.Espacio;
import Domain.Espacios.TipoDireccion;
import Domain.MediosDeTransporte.TipoTransportePublico;
import Domain.MediosDeTransporte.TransportePublico;
import Domain.MediosDeTransporte.VehiculoParticular;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.HashMap;

public class RepositorioDireccionDB extends Repositorio {

    public RepositorioDireccionDB() {
        super(new DBHibernate<Direccion>(Direccion.class));
    }

    public Direccion crearTransportePublico(String pais, String provincia, String municipio, String localidad, String calle, Integer altura, TipoDireccion tipoDireccion){
        Direccion direccion = new Direccion(pais, provincia, municipio, localidad, calle, altura, tipoDireccion);

        this.dbService.agregar(direccion);
        return direccion;
    }

    public Direccion buscarDireccion(String pais, String provincia, String municipio, String localidad, String calle, Integer altura, String tipoDireccion){
        return (Direccion) this.dbService.buscar(condicionDireccion(pais, provincia, municipio, localidad, calle, altura, tipoDireccion));
    }


    private BusquedaCondicional condicionDireccion(String pais, String provincia, String municipio, String localidad, String calle, Integer altura, String tipoDireccion){
        CriteriaBuilder criteriaBuilder = criteriaBuilder();
        CriteriaQuery<Direccion> direccionQuery = criteriaBuilder.createQuery(Direccion.class);

        Root<VehiculoParticular> condicionRaiz = direccionQuery.from(VehiculoParticular.class);

        Predicate condicionPais = criteriaBuilder.equal(condicionRaiz.get("pais"), pais);
        Predicate condicionCalle = criteriaBuilder.equal(condicionRaiz.get("calle"), calle);
        Predicate condicionAltura = criteriaBuilder.equal(condicionRaiz.get("altura"), altura);
        //TODO: revisar 'condicionRaiz.get("tipo_espacio")'
        Predicate condicionTipoDireccion = criteriaBuilder.equal(condicionRaiz.get("tipo_espacio"), tipoDireccion);
        Predicate condicionProvincia = criteriaBuilder.equal(condicionRaiz.get("provincia"), provincia);
        Predicate condicionMunicipio = criteriaBuilder.equal(condicionRaiz.get("municipio"), municipio);
        Predicate condicionLocalidad = criteriaBuilder.equal(condicionRaiz.get("localidad"), localidad);

        Predicate condicionExisteDireccion = criteriaBuilder.and(
                condicionPais,
                condicionCalle,
                condicionAltura,
                condicionTipoDireccion,
                condicionProvincia,
                condicionMunicipio,
                condicionLocalidad
        );

        direccionQuery.where(condicionExisteDireccion);

        return new BusquedaCondicional(null, direccionQuery);
    }
}
