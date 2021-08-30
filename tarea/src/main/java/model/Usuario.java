package model;

import java.util.ArrayList;
import java.util.Date;

public class Usuario {

    private int id;
    private String correo;
    private Date ultima_conexion;
    private ArrayList<Integer> siguiendo = new ArrayList<Integer>();
    private int seguidores;

    private Boolean isActivo;

    public Usuario(){
    }

    public int getSeguidores() {
        return seguidores;
    }

    public void setSeguidores(int seguidores) {
        this.seguidores = seguidores;
    }

    public Boolean isActivo() {
        return isActivo;
    }

    public void setActivo(Boolean activo) {
        isActivo = activo;
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

    public Date getUltima_conexion() {
        return ultima_conexion;
    }

    public void setUltima_conexion(Date ultima_conexion) {
        this.ultima_conexion = ultima_conexion;
    }

    public ArrayList<Integer> getSiguiendo() {
        return siguiendo;
    }

    public void setSiguiendo(ArrayList<Integer> siguiendo) {
        this.siguiendo = siguiendo;
    }


    public Boolean getActivo() {
        return isActivo;
    }

    @Override
    public String toString() {


        return "Usuario{" +
                "id=" + id +
                ", correo='" + correo + '\'' +
                ", ultima_conexion='" + ultima_conexion + '\'' +
                ", siguiendo=" + siguiendo +
                '}';
    }

}
