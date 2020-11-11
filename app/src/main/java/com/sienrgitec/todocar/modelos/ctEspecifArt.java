package com.sienrgitec.todocar.modelos;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class ctEspecifArt implements Serializable {
    @SerializedName("iArticulo")
    @Expose
    private Integer iArticulo;

    @SerializedName("iPartida")
    @Expose
    private Integer iPartida;

    @SerializedName("cTipo")
    @Expose
    private String cTipo;

    @SerializedName("cArchivo")
    @Expose
    private String cArchivo;

}
