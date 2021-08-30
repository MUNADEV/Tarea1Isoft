package controller;

import model.Usuario;
import util.Utils;

import java.util.ArrayList;

public class AppController {

    ArrayList<Usuario> listaUsuarios1;
    ArrayList<Usuario> listaUsuarios2;
    ArrayList<Usuario> usuariosDefinitivos;

    ArrayList<Usuario> usuariosInactivos;

    Utils utilidades = new Utils();
    String rutaData1 = "\\C:\\Users\\javie\\Proyectos\\tarea_2s2021\\enunciado\\datasets\\dataset1.csv";
    String rutaData2 = "\\C:\\Users\\javie\\Proyectos\\tarea_2s2021\\enunciado\\datasets\\dataset2.csv";

    public AppController() {
        leerCSV();
        usuariosDefinitivos = utilidades.generarListaDefinitiva(listaUsuarios1,listaUsuarios2);
        usuariosInactivos = this.usuariosInactivos();
    }

    public void leerCSV(){
        listaUsuarios1 = utilidades.leerDatos(rutaData1);
        listaUsuarios2 = utilidades.leerDatos(rutaData2);
    }
    public void generarCSV(){
        // Genera el csv definitivo, uniendo los dos datasets con las condiciones requeridas
        utilidades.escribirDatosCSV(usuariosDefinitivos);
    }
    public void generarTXT(){

    }
    public ArrayList<Usuario> usuariosInactivos(){
        // Retorna todos los usuarios inactivos
        for(Usuario usuario : this.usuariosDefinitivos){
            if(!usuario.isActivo()){
                usuariosInactivos.add(usuario);
            }
        }
        utilidades.escribirDatosCSV(usuariosInactivos);
        return usuariosInactivos;
    }
    public ArrayList<Usuario> usuariosSiguenMitadInactivos(){
        ArrayList<Usuario> usuarios = new ArrayList<>();
        for(Usuario usuario : this.usuariosDefinitivos){

        }
        return usuarios;
    }

    public ArrayList<Usuario> getUsuariosDefinitivos() {
        return usuariosDefinitivos;
    }



}
