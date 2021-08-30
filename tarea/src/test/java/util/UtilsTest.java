package util;


import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;


import java.util.Date;
import java.util.ArrayList;



public class UtilsTest {

    private Utils utilidades = new Utils();





    @Test
    public void compararListas_test(){
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
        ArrayList<Integer> listaObtenida = utilidades.compararListasSiguiendo(lista1,lista2);
        listaEsperada.add(1);listaEsperada.add(2);listaEsperada.add(10);listaEsperada.add(100);listaEsperada.add(2000);listaEsperada.add(20000);

        Assertions.assertEquals(listaObtenida,listaEsperada);

    }
    @Test
    public void fechaMasReciente_test(){
        Date fechaEsperada = new Date(121,3,1);
        Date fechaReciente = new Date(121,3,1);
        Date fechaAntigua = new Date(113,2,5);

        Date fechaObtenida = utilidades.fechaMasReciente(fechaAntigua,fechaReciente);

        Assertions.assertEquals(fechaEsperada,fechaObtenida);
    }
    @Test
    public void listaToString_test(){
        ArrayList<Integer> lista = new ArrayList<>();
        //lenando la lista
        for (int i = 0; i < 10; i++){
            lista.add(i);
        }
        String esperado = "0,1,2,3,4,5,6,7,8,9";
        String obtenido = utilidades.listaToString(lista);
        Assertions.assertEquals(esperado,obtenido);
    }
    @Test
    public void stringToDate_test(){
        String fecha = "2021-08-30";
        Date fechaEsperada = new Date(121,7,30);
        Date fechaObtenida = utilidades.stringtoDate(fecha);

        Assertions.assertEquals(fechaEsperada,fechaObtenida);
    }
    @Test
    public void dateToString_test(){
        Date fecha = new Date(121,1,1);
        String fechaEsperada = "2021-02-1";
        String fechaObtenida = utilidades.dateToString(fecha);

        Assertions.assertEquals(fechaEsperada,fechaObtenida);
    }
}
