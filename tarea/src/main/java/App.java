import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.*;

import controller.*;
import util.Utils;

public class App {


    public static void main(String[] args) {

        AppController ap = new AppController();





        Utils ut = new Utils();
        System.out.println(ap.getUsuariosDefinitivos().get(1).getCorreo() + "" + ap.usuariosInactivos().get(1).getId());
        ap.generarCSV();
        //ap.usuariosInactivos();
        //System.out.println(ap.getUsuariosDefinitivos().get(2).getCorreo() + " "+ap.getUsuariosDefinitivos().get(2).isActivo());
    }
}
