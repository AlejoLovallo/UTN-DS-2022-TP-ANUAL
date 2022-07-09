package CalculadorDistanciaTest.endPointsTest;

import Domain.CalculadorDistancia.Endpoints.ListadoProvincias;
import Domain.CalculadorDistancia.Endpoints.Provincia;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class ListadoProvinciasTest {
    protected ListadoProvincias listProv;

    private void initializeProvincias(){
        this.listProv=new ListadoProvincias();    }

    @BeforeEach
    public void initialize(){this.initializeProvincias();}

    @AfterEach
    public void clean(){}

    @Test
    public void getInstance(){
        ListadoProvincias lista = null;

        Assertions.assertNull(lista);
        Assertions.assertNotNull(lista.getInstance());
    }

    @Test
    public void getsetProvincias(){
        Provincia prov1= new Provincia("1","BsAs");
        Provincia prov2= new Provincia("2","Salta");
        List<Provincia> lista=new ArrayList<>();
        lista.add(prov1);
        lista.add(prov2);

        this.listProv.setProvincias(lista);

        Assertions.assertEquals(lista,this.listProv.getProvincias());
    }

    @Test
    public void getsetProvinciasById(){
        Provincia prov1= new Provincia("1","BsAs");
        Provincia prov2= new Provincia("2","Salta");
        List<Provincia> lista=new ArrayList<>();
        lista.add(prov1);
        lista.add(prov2);
        String id1="1";
        String id2="2";

        this.listProv.setProvincias(lista);

        Assertions.assertEquals(Optional.of(prov1), this.listProv.getProvinciaById(id1));
        Assertions.assertEquals(Optional.of(prov2), this.listProv.getProvinciaById(id2));

    }
    @Test
    public void getsetProvinciasByName(){
        Provincia prov1= new Provincia("1","BsAs");
        Provincia prov2= new Provincia("2","Salta");
        List<Provincia> lista=new ArrayList<>();
        lista.add(prov1);
        lista.add(prov2);
        String name1="BsAs";
        String name2="Salta";

        this.listProv.setProvincias(lista);

        Assertions.assertEquals(Optional.of(prov1), this.listProv.getProvinciaByName(name1));
        Assertions.assertEquals(Optional.of(prov2), this.listProv.getProvinciaByName(name2));

    }

}
