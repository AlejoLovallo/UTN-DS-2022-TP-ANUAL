package CalculadorDistanciaTest.endPointsTest;

import Domain.CalculadorDistancia.Endpoints.User;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class UserTest {
    protected User user;

    private void initializeUser(){
        this.user=new User("tpdds@gmail.com");    }

    @BeforeEach
    public void initialize(){this.initializeUser();}

    @AfterEach
    public void clean(){}

    @Test
    public void getUser(){
        String actualemail=this.user.getEmail();
        String newemail="otramateria@gmail.com";

        this.user.setEmail(newemail);

        Assertions.assertEquals("tpdds@gmail.com",actualemail);
        Assertions.assertEquals(newemail,this.user.getEmail());
    }
}
