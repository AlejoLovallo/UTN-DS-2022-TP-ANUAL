package Domain.Repositorios;

import Domain.MediosDeTransporte.TipoCombustible;
import Domain.MediosDeTransporte.TipoVehiculo;
import Domain.MediosDeTransporte.VehiculoParticular;
import Domain.Usuarios.Usuario;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

public class RepositorioVehiculoParticularDB extends Repositorio{

    public RepositorioVehiculoParticularDB() {
        super(new DBHibernate<VehiculoParticular>(VehiculoParticular.class));
    }

    public VehiculoParticular crearVehiculoParticular(TipoVehiculo tipoVehiculo, TipoCombustible tipoCombustible, int cantidadPasajeros, double consumoPorKM){
        VehiculoParticular vehiculoParticular = new VehiculoParticular(tipoVehiculo, tipoCombustible, cantidadPasajeros);

        this.dbService.agregar(vehiculoParticular);
        vehiculoParticular.setConsumoPorKm(consumoPorKM);
        this.dbService.modificar(vehiculoParticular);

        return vehiculoParticular;
    }

    public VehiculoParticular buscarVehiculoParticular(String tipoVehiculo, String tipoCombustible, Integer cantPersonas, Double consumoPorKm ){
        return (VehiculoParticular) this.dbService.buscar(condicionVehiculoParticular(
                tipoVehiculo,
                tipoCombustible,
                cantPersonas,
                consumoPorKm
        ));
    }

    private BusquedaCondicional condicionVehiculoParticular(String tipoVehiculo, String tipoCombustible, Integer cantidadPasajeros, Double consumoPorKm){
        CriteriaBuilder criteriaBuilder = criteriaBuilder();
        CriteriaQuery<VehiculoParticular> vehiculoQuery = criteriaBuilder.createQuery(VehiculoParticular.class);

        Root<VehiculoParticular> condicionRaiz = vehiculoQuery.from(VehiculoParticular.class);

        Predicate condicionTipoVehiculo = criteriaBuilder.equal(condicionRaiz.get("tipoVehiculo"), TipoVehiculo.valueOf(tipoVehiculo));
        Predicate condicionTipoCombustible = criteriaBuilder.equal(condicionRaiz.get("tipoCombustible"),TipoCombustible.valueOf(tipoCombustible));
        Predicate condicionCantPersonas = criteriaBuilder.equal(condicionRaiz.get("cantPasajeros"), cantidadPasajeros);
        Predicate condicionConsumoPorKm = criteriaBuilder.equal(condicionRaiz.get("consumoPorKm"), consumoPorKm);

        Predicate condicionExisteUsuario = criteriaBuilder.and(condicionTipoVehiculo, condicionTipoCombustible, condicionCantPersonas, condicionConsumoPorKm);

        vehiculoQuery.where(condicionExisteUsuario);

        return new BusquedaCondicional(null, vehiculoQuery);
    }

    private BusquedaCondicional condicionVehiculoPorID(Integer id){
        CriteriaBuilder criteriaBuilder = criteriaBuilder();
        CriteriaQuery<VehiculoParticular> vehiculoParticularCriteriaQuery = criteriaBuilder.createQuery(VehiculoParticular.class);

        Root<VehiculoParticular> root = vehiculoParticularCriteriaQuery.from(VehiculoParticular.class);

        Predicate condicionPorID = criteriaBuilder.like(root.get("id_transporte"), "%"+ id.toString() +"%");

        vehiculoParticularCriteriaQuery.where(condicionPorID);

        return new BusquedaCondicional(null, vehiculoParticularCriteriaQuery);
    }
}
