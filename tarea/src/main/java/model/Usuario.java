package model;

import java.util.ArrayList;

public class Usuario {

    private int id;
    private String correo;
    private String ultima_conexion;
    private ArrayList<Integer> siguiendo = new ArrayList<Integer>();

    public Usuario(){

    }

    public Usuario(int id,String correo,String ultima_conexion){

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public String getUltima_conexion() {
        return ultima_conexion;
    }

    public void setUltima_conexion(String ultima_conexion) {
        this.ultima_conexion = ultima_conexion;
    }

    public ArrayList<Integer> getSiguiendo() {
        return siguiendo;
    }

    public void setSiguiendo(ArrayList<Integer> siguiendo) {
        this.siguiendo = siguiendo;
    }



}
