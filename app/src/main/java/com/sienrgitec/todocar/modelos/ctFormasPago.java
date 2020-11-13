package com.sienrgitec.todocar.modelos;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class ctFormasPago implements Serializable {
    @SerializedName("cCvePago")
    @Expose
    private String cCvePago;

    @SerializedName("cFormaPago")
    @Expose
    private String cFormaPago;

    @SerializedName("cUsuCrea")
    @Expose
    private String cUsuCrea;

    @SerializedName("cUsuModifica")
    @Expose
    private String cUsuModifica;

    @SerializedName("dtCreado")
    @Expose
    private String dtCreado;

    @SerializedName("dtModificado")
    @Expose
    private String dtModificado;

    @SerializedName("iFormaPago")
    @Expose
    private Integer iFormaPago;

    @SerializedName("lActivo")
    @Expose
    private Boolean lActivo;

    private Object  id;


    public String getCvePago() {
        return cCvePago;
    }

    public void setCvePago(String cvePago) {
        cCvePago = cvePago;
    }

    public String getcFormaPago() {
        return cFormaPago;
    }

    public void setcFormaPago(String cFormaPago) {
        this.cFormaPago = cFormaPago;
    }

    public String getcUsuCrea() {
        return cUsuCrea;
    }

    public void setcUsuCrea(String cUsuCrea) {
        this.cUsuCrea = cUsuCrea;
    }

    public String getcUsuModifica() {
        return cUsuModifica;
    }

    public void setcUsuModifica(String cUsuModifica) {
        this.cUsuModifica = cUsuModifica;
    }

    public String getDtCreado() {
        return dtCreado;
    }

    public void setDtCreado(String dtCreado) {
        this.dtCreado = dtCreado;
    }

    public String getDtModificado() {
        return dtModificado;
    }

    public void setDtModificado(String dtModificado) {
        this.dtModificado = dtModificado;
    }

    public Integer getiFormaPago() {
        return iFormaPago;
    }

    public void setiFormaPago(Integer iFormaPago) {
        this.iFormaPago = iFormaPago;
    }

    public Boolean getlActivo() {
        return lActivo;
    }

    public void setlActivo(Boolean lActivo) {
        this.lActivo = lActivo;
    }

    public Object getId() {
        return id;
    }

    public void setId(Object id) {
        this.id = id;
    }
}
