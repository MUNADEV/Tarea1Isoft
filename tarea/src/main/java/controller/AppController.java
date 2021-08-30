package controller;

import model.Usuario;
import util.Utils;

import java.util.ArrayList;

public class AppController {

    ArrayList<Usuario> listaUsuarios1;
    ArrayList<Usuario> listaUsuarios2;
    ArrayList<Usuario> usuariosDefinitivos;

    Utils utilidades = new Utils();
    String rutaData1 = "\\C:\\Users\\javie\\Proyectos\\tarea_2s2021\\enunciado\\datasets\\dataset1.csv";
    String rutaData2 = "\\C:\\Users\\javie\\Proyectos\\tarea_2s2021\\enunciado\\datasets\\dataset2.csv";

    public AppController() {
        listaUsuarios1 = utilidades.leerDatos(rutaData1);
        listaUsuarios2 = utilidades.leerDatos(rutaData2);
    }

    public void generarCSV(){
        usuariosDefinitivos = utilidades.generarListaDefinitiva(listaUsuarios1,listaUsuarios2);
        utilidades.escribirDatos(usuariosDefinitivos);
    }
    public ArrayList<Usuario> getListaUsuarios1() {
        return listaUsuarios1;
    }
    public ArrayList<Usuario> getListaUsuarios2(){
        return listaUsuarios2;
    }
}
