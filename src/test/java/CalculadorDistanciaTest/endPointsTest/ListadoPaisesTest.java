package CalculadorDistanciaTest.endPointsTest;


import Domain.CalculadorDistancia.Endpoints.ListadoPaises;
import Domain.CalculadorDistancia.Endpoints.Pais;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class ListadoPaisesTest {
    protected ListadoPaises listpais;

    private void initializePaises(){
        this.listpais=new ListadoPaises();    }

    @BeforeEach
    public void initialize(){this.initializePaises();}

    @AfterEach
    public void clean(){}

    @Test
    public void getInstance(){
        ListadoPaises lista = null;

        Assertions.assertNull(lista);
        Assertions.assertNotNull(lista.getInstance());
    }

    @Test
    public void getsetPaises(){
        Pais pais1= new Pais("1","Argentina");
        Pais pais2= new Pais("2","Brasil");
        List<Pais> lista=new ArrayList<>();
        lista.add(pais1);
        lista.add(pais2);

        this.listpais.setPaises(lista);

        Assertions.assertEquals(lista,this.listpais.getPaises());
    }

    @Test
    public void getsetPaisesById(){
        Pais pais1= new Pais("1","Argentina");
        Pais pais2= new Pais("2","Brasil");
        List<Pais> lista=new ArrayList<>();
        String id1="1";
        String id2="2";

        lista.add(pais1);
        lista.add(pais2);

        this.listpais.setPaises(lista);

        Assertions.assertEquals(Optional.of(pais1), this.listpais.getPaisById(id1));
        Assertions.assertEquals(Optional.of(pais2), this.listpais.getPaisById(id2));

    }
    @Test
    public void getsetPaisesByName(){
        Pais pais1= new Pais("1","Argentina");
        Pais pais2= new Pais("2","Brasil");
        List<Pais> lista=new ArrayList<>();

        String name1="Argentina";
        String name2="Brasil";

        lista.add(pais1);
        lista.add(pais2);

        this.listpais.setPaises(lista);

        Assertions.assertEquals(Optional.of(pais1), this.listpais.getPaisByName(name1));
        Assertions.assertEquals(Optional.of(pais2), this.listpais.getPaisByName(name2));

    }

}
