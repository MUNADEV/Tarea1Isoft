import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.*;

import controller.*;
import util.Utils;

public class App {


    public static void main(String[] args) {

        AppController ap = new AppController();

        System.out.println(ap.getListaUsuarios1().get(3).getUltima_conexion());

        Date fecha1 = ap.getListaUsuarios1().get(3).getUltima_conexion();//11-10-2020
        Date fecha2 = ap.getListaUsuarios1().get(4).getUltima_conexion();//14/01/2018


        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        String strDate = dateFormat.format(fecha1);
        System.out.println("Converted String: " + strDate);
        Utils ut = new Utils();
        ap.generarCSV();
    }
}
