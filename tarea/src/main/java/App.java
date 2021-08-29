
import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvException;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

import controller.*;
import org.apache.commons.collections4.bag.CollectionSortedBag;

public class App {


    public static void main(String[] args) {

        AppController ap = new AppController();

        System.out.println(ap.getListaUsuario1().get(3).getUltima_conexion());

        Date fecha1 = ap.getListaUsuario1().get(3).getUltima_conexion();//11-10-2020
        Date fecha2 = ap.getListaUsuario1().get(4).getUltima_conexion();//14/01/2018

        if(fecha1.after(fecha2)){
            System.out.println("fecha1 es la mas reciente que fecha 2");
        }else{
            System.out.println("fecha2 es la mas reciente que la fecha 1");
        }

        ArrayList<Integer> lista = new ArrayList<>();
        lista.add(1231312);
        lista.add(12123);
        lista.add(213);
        lista.add(213);
        lista.add(213);
        lista.add(2314);

        Set<Integer> set = new LinkedHashSet<>(lista);
        lista.clear();
        lista.addAll(set);


        Collections.sort(lista);
        System.out.println(lista);

    }
}
