package util;


import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;


import java.sql.DatabaseMetaData;
import java.sql.Date;
import java.util.ArrayList;



public class UtilsTest {

    private Utils utils = new Utils();



    @Test
    public void fechaMasReciente(){
        Date fechaReciente = new Date(121,7,29); //29-08-2021
        Date fechaAntigua = new Date(116,2,1);// 1-3-2016

        Assertions.assertTrue(fechaReciente.after(fechaAntigua));
    }

    @Test
    public void compararListas(){
        ArrayList<Integer> lista1;
        ArrayList<Integer> lista2;
        lista1 = new ArrayList<>();
        lista2 = new ArrayList<>();
        //Lista 1: Elementos desordenados
        lista1.add(10);lista1.add(2);lista1.add(1);lista1.add(100);
        //Lista 2: Elementos desordenados y repetidos con la lista1
        lista2.add(10); lista2.add(2000);lista2.add(20000);lista2.add(1);lista2.add(100);

        //orden correcto: 1,2,10,100,200
        ArrayList<Integer> listaEsperada = new ArrayList<>();
        ArrayList<Integer> listaObtenida = utils.compararListasSiguiendo(lista1,lista2);
        listaEsperada.add(1);listaEsperada.add(2);listaEsperada.add(10);listaEsperada.add(100);listaEsperada.add(2000);listaEsperada.add(20000);

        Assertions.assertEquals(listaObtenida,listaEsperada);

    }

}
