package CalculadorDistanciaTest.endPointsTest;

import Domain.CalculadorDistancia.Endpoints.ListadoLocalidades;
import Domain.CalculadorDistancia.Endpoints.Localidad;
import Domain.Organizacion.RepositorioOrganizaciones;
import Utils.Common;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class ListadoLocalidadesTest {
    protected ListadoLocalidades localidades;

    private void initializeLocalidades(){
        this.localidades=new ListadoLocalidades();    }

    @BeforeEach
    public void initialize(){this.initializeLocalidades();}

    @AfterEach
    public void clean(){}

    @Test
    public void getInstance(){
        ListadoLocalidades lista = null;

        Assertions.assertNull(lista);
        Assertions.assertNotNull(lista.getInstance());
    }

    @Test
    public void getsetLocalidades(){
        Localidad localidad1= new Localidad("1","Recoleta");
        Localidad localidad2= new Localidad("2","Caballito");
        List<Localidad> lista=new ArrayList<>();
        lista.add(localidad1);
        lista.add(localidad2);

        this.localidades.setLocalidades(lista);

        Assertions.assertEquals(lista,this.localidades.getLocalidades());
    }

    @Test
    public void getsetLocalidadById(){
        Localidad localidad1= new Localidad("1","Recoleta");
        Localidad localidad2= new Localidad("2","Caballito");
        List<Localidad> lista=new ArrayList<>();
        String id1="1";
        String id2="2";

        lista.add(localidad1);
        lista.add(localidad2);

        this.localidades.setLocalidades(lista);

        Assertions.assertEquals(Optional.of(localidad1), this.localidades.getLocalidadById(id1));
        Assertions.assertEquals(Optional.of(localidad2), this.localidades.getLocalidadById(id2));

    }
    @Test
    public void getsetLocalidadByName(){
        Localidad localidad1= new Localidad("1","Recoleta");
        Localidad localidad2= new Localidad("2","Caballito");
        List<Localidad> lista=new ArrayList<>();

        String name1="Recoleta";
        String name2="Caballito";

        lista.add(localidad1);
        lista.add(localidad2);

        this.localidades.setLocalidades(lista);

        Assertions.assertEquals(Optional.of(localidad1), this.localidades.getLocalidadByName(name1));
        Assertions.assertEquals(Optional.of(localidad2), this.localidades.getLocalidadByName(name2));

   }
}
