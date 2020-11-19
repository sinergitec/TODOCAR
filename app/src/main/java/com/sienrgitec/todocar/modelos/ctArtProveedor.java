package com.sienrgitec.todocar.modelos;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;


public class ctArtProveedor implements Serializable {

    @SerializedName("iArticulo")
    @Expose
    private Integer iArticulo;

    @SerializedName("iProveedor")
    @Expose
    private Integer iProveedor;

    @SerializedName("iDomicilio")
    @Expose
    private Integer iDomicilio;

    @SerializedName("cArticulo")
    @Expose
    private String cArticulo;

    public Integer getiDomicilio() {
        return iDomicilio;
    }

    public void setiDomicilio(Integer iDomicilio) {
        this.iDomicilio = iDomicilio;
    }

    @SerializedName("cAplicaciones")
    @Expose
    private String cAplicaciones;

    @SerializedName("cPresentacion")
    @Expose
    private String cPresentacion;

    @SerializedName("cDescripcion")
    @Expose
    private String cDescripcion;

    @SerializedName("iCategoria")
    @Expose
    private Integer iCategoria;

    @SerializedName("iClasificacion")
    @Expose
    private Integer iClasificacion;

    @SerializedName("iSubClasificacion")
    @Expose
    private Integer iSubClasificacion;

    @SerializedName("iMarca")
    @Expose
    private Integer iMarca;

    @SerializedName("dePrecio")
    @Expose
    private Double dePrecio;


    @SerializedName("cMarca")
    @Expose
    private String cMarca;

    @SerializedName("dePeso")
    @Expose
    private Double dePeso;

    public String getcMarca() {
        return cMarca;
    }

    public Double getDePeso() {
        return dePeso;
    }

    public void setDePeso(Double dePeso) {
        this.dePeso = dePeso;
    }

    public void setcMarca(String cMarca) {
        this.cMarca = cMarca;
    }

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

    public String getcArticulo() {
        return cArticulo;
    }

    public void setcArticulo(String cArticulo) {
        this.cArticulo = cArticulo;
    }

    public String getcAplicaciones() {
        return cAplicaciones;
    }

    public void setcAplicaciones(String cAplicaciones) {
        this.cAplicaciones = cAplicaciones;
    }

    public String getcPresentacion() {
        return cPresentacion;
    }

    public void setcPresentacion(String cPresentacion) {
        this.cPresentacion = cPresentacion;
    }

    public Integer getiCategoria() {
        return iCategoria;
    }

    public void setiCategoria(Integer iCategoria) {
        this.iCategoria = iCategoria;
    }

    public Integer getiClasificacion() {
        return iClasificacion;
    }

    public void setiClasificacion(Integer iClasificacion) {
        this.iClasificacion = iClasificacion;
    }

    public Integer getiSubClasificacion() {
        return iSubClasificacion;
    }

    public void setiSubClasificacion(Integer iSubClasificacion) {
        this.iSubClasificacion = iSubClasificacion;
    }

    public Integer getiMarca() {
        return iMarca;
    }

    public void setiMarca(Integer iMarca) {
        this.iMarca = iMarca;
    }

    public Double getDePrecio() {
        return dePrecio;
    }

    public void setDePrecio(Double dePrecio) {
        this.dePrecio = dePrecio;
    }

    public String getcDescripcion() {
        return cDescripcion;
    }

    public void setcDescripcion(String cDescripcion) {
        this.cDescripcion = cDescripcion;
    }
}
