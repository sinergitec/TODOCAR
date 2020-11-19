package com.sienrgitec.todocar.modelos;

import java.io.Serializable;

public class ctInformacionArt implements Serializable {
    private Integer iArticulo;
    private Integer iProveedor;
    private Integer iPartida;
    private Integer iDomicilio;
    private String  cRuta;
    private String  cTipo;
    private String  cNombre;
    private Object id;

    public Integer getiArticulo() {
        return iArticulo;
    }

    public void setiArticulo(Integer iArticulo) {
        this.iArticulo = iArticulo;
    }

    public Integer getiProveedor() {
        return iProveedor;
    }

    public void setiProveedor(Integer iProveedor) {
        this.iProveedor = iProveedor;
    }

    public Integer getiPartida() {
        return iPartida;
    }

    public void setiPartida(Integer iPartida) {
        this.iPartida = iPartida;
    }

    public Integer getiDomicilio() {
        return iDomicilio;
    }

    public void setiDomicilio(Integer iDomicilio) {
        this.iDomicilio = iDomicilio;
    }

    public String getcRuta() {
        return cRuta;
    }

    public void setcRuta(String cRuta) {
        this.cRuta = cRuta;
    }

    public String getcTipo() {
        return cTipo;
    }

    public void setcTipo(String cTipo) {
        this.cTipo = cTipo;
    }


    public String getcNombre() {
        return cNombre;
    }

    public void setcNombre(String cNombre) {
        this.cNombre = cNombre;
    }

    public Object getId() {
        return id;
    }

    public void setId(Object id) {
        this.id = id;
    }
}
