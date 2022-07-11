package CalculadorDistanciaTest.endPointsTest;

import Domain.CalculadorDistancia.Endpoints.ListadoMunicipios;
import Domain.CalculadorDistancia.Endpoints.Municipio;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class ListadoMunicipiosTest {
    protected ListadoMunicipios municip;

    private void initializeMunicipios(){
        this.municip=new ListadoMunicipios();    }

    @BeforeEach
    public void initialize(){this.initializeMunicipios();}

    @AfterEach
    public void clean(){}

    @Test
    public void getInstance(){
        ListadoMunicipios lista = null;

        Assertions.assertNull(lista);
        Assertions.assertNotNull(lista.getInstance());
    }

    @Test
    public void getsetMunicipios(){
        Municipio muni1= new Municipio("1","Recoleta");
        Municipio muni2= new Municipio("2","Caballito");
        List<Municipio> lista=new ArrayList<>();
        lista.add(muni1);
        lista.add(muni2);

        this.municip.setMunicipios(lista);

        Assertions.assertEquals(lista,this.municip.getMunicipios());
    }

    @Test
    public void getsetMunicipiosById(){
        Municipio muni1= new Municipio("1","Recoleta");
        Municipio muni2= new Municipio("2","Caballito");
        List<Municipio> lista=new ArrayList<>();
        String id1="1";
        String id2="2";

        lista.add(muni1);
        lista.add(muni2);

        this.municip.setMunicipios(lista);

        Assertions.assertEquals(Optional.of(muni1), this.municip.getMunicipioById(id1));
        Assertions.assertEquals(Optional.of(muni2), this.municip.getMunicipioById(id2));

    }
    @Test
    public void getsetMunicipiosByName(){
        Municipio muni1= new Municipio("1","Recoleta");
        Municipio muni2= new Municipio("2","Caballito");
        List<Municipio> lista=new ArrayList<>();

        String name1="Recoleta";
        String name2="Caballito";

        lista.add(muni1);
        lista.add(muni2);

        this.municip.setMunicipios(lista);

        Assertions.assertEquals(Optional.of(muni1), this.municip.getMunicipioByName(name1));
        Assertions.assertEquals(Optional.of(muni2), this.municip.getMunicipioByName(name2));

    }
}
