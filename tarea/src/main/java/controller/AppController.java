package controller;

import model.Usuario;
import util.Utils;

import java.util.ArrayList;

public class AppController {

    ArrayList<Usuario> listaUsuarios1;
    ArrayList<Usuario> listaUsuarios2;
    ArrayList<Usuario> usuariosDefinitivos;


    Utils utilidades = new Utils();
    String rutaData1 = "datasets\\dataset1.csv";
    String rutaData2 = "datasets\\dataset2.csv";

    public AppController() {
        leerCSV();
        usuariosDefinitivos = utilidades.generarListaDefinitiva(listaUsuarios1,listaUsuarios2);
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
        // Genera el txt solicitado
        ArrayList<Usuario> inactivos = this.usuariosInactivos();
        ArrayList<Usuario> sigueMasInactivos = this.usuariosSiguendoMasInactivos();
        ArrayList<Usuario> mayorSeguidores = this.usuariosMasSeguidores();

        utilidades.escribirDatosTXT(inactivos,sigueMasInactivos,mayorSeguidores);

    }
    public ArrayList<Usuario> usuariosInactivos(){
        // Retorna todos los usuarios inactivos
        ArrayList<Usuario> usuariosInactivos = new ArrayList<>();
        for(Usuario usuario : this.usuariosDefinitivos){
            if(!usuario.isActivo()){
                usuariosInactivos.add(usuario);
            }
        }
        utilidades.escribirDatosCSV(usuariosInactivos);
        return usuariosInactivos;
    }
    public ArrayList<Usuario> usuariosSiguendoMasInactivos(){
        ArrayList<Usuario> usuarios = new ArrayList<>();
        for(Usuario usuario : this.usuariosDefinitivos){
            int mitadSeguidos = usuario.getSiguiendo().size()/2;
            if(usuario.getSiguendoInactivo().size() > mitadSeguidos){
                usuarios.add(usuario);
            }
        }
        return usuarios;
    }
    public ArrayList<Usuario> usuariosMasSeguidores(){
        ArrayList<Usuario> usuarios = new ArrayList<>();

        int usuarioId;
        int mayorSeguidores = 0;
        //Busca la mayor cantidad de seguidores que puede tener un usuario
        for(Usuario usuario : usuariosDefinitivos){
            if(usuario.getSeguidores()>=mayorSeguidores){
                mayorSeguidores = usuario.getSeguidores();
            }
        }
        //Busca todos los usuarios que coincidan con la cantidad de seguidores anterior
        for(Usuario usuario : usuariosDefinitivos){
            if(usuario.getSeguidores()==mayorSeguidores){
                usuarios.add(usuario);
            }
        }

        return usuarios;
    }


}
