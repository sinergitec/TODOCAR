package com.sienrgitec.todocar.modelos;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class ctCliente implements Serializable {
    @SerializedName("iCliente")
    @Expose
    private Integer iCliente;

    @SerializedName("cClave")
    @Expose
    private String cClave;

    @SerializedName("cNombre")
    @Expose
    private String cNombre;

    @SerializedName("cApellidos")
    @Expose
    private String cApellidos;

    @SerializedName("dtRegistro")
    @Expose
    private Object dtRegistro;

    @SerializedName("dtUltCompra")
    @Expose
    private Object dtUltCompra;

    @SerializedName("deUltCompra")
    @Expose
    private Double deUltCompra;

    @SerializedName("dePorcUltComision")
    @Expose
    private Double dePorcUltComision;

    @SerializedName("deUltComision")
    @Expose
    private Double deUltComision;

    @SerializedName("dePorcUltPropina")
    @Expose
    private Double dePorcUltPropina;

    @SerializedName("deUltPropina")
    @Expose
    private Double deUltPropina;

    @SerializedName("ICPCP")
    @Expose
    private Object ICPCP;

    @SerializedName("cEMail")
    @Expose
    private String cEmail;

    @SerializedName("dtCreado")
    @Expose
    private Object dtCreado;

    @SerializedName("dtModificado")
    @Expose
    private Object dtModificado;

    @SerializedName("cUsuCrea")
    @Expose
    private String cUsuCrea;

    @SerializedName("cUsuModifica")
    @Expose
    private String cUsuModifica;

    public Integer getiCliente() {
        return iCliente;
    }

    public void setiCliente(Integer iCliente) {
        this.iCliente = iCliente;
    }

    public String getcClave() {
        return cClave;
    }

    public void setcClave(String cClave) {
        this.cClave = cClave;
    }

    public String getcNombre() {
        return cNombre;
    }

    public void setcNombre(String cNombre) {
        this.cNombre = cNombre;
    }

    public String getcApellidos() {
        return cApellidos;
    }

    public void setcApellidos(String cApellidos) {
        this.cApellidos = cApellidos;
    }

    public Object getDtRegistro() {
        return dtRegistro;
    }

    public void setDtRegistro(Object dtRegistro) {
        this.dtRegistro = dtRegistro;
    }

    public Object getDtUltCompra() {
        return dtUltCompra;
    }

    public void setDtUltCompra(Object dtUltCompra) {
        this.dtUltCompra = dtUltCompra;
    }

    public Double getDeUltCompra() {
        return deUltCompra;
    }

    public void setDeUltCompra(Double deUltCompra) {
        this.deUltCompra = deUltCompra;
    }

    public Double getDePorcUltComision() {
        return dePorcUltComision;
    }

    public void setDePorcUltComision(Double dePorcUltComision) {
        this.dePorcUltComision = dePorcUltComision;
    }

    public Double getDeUltComision() {
        return deUltComision;
    }

    public void setDeUltComision(Double deUltComision) {
        this.deUltComision = deUltComision;
    }

    public Double getDePorcUltPropina() {
        return dePorcUltPropina;
    }

    public void setDePorcUltPropina(Double dePorcUltPropina) {
        this.dePorcUltPropina = dePorcUltPropina;
    }

    public Double getDeUltPropina() {
        return deUltPropina;
    }

    public void setDeUltPropina(Double deUltPropina) {
        this.deUltPropina = deUltPropina;
    }

    public Object getICPCP() {
        return ICPCP;
    }

    public void setICPCP(Object ICPCP) {
        this.ICPCP = ICPCP;
    }

    public String getcEmail() {
        return cEmail;
    }

    public void setcEmail(String cEmail) {
        this.cEmail = cEmail;
    }

    public Object getDtCreado() {
        return dtCreado;
    }

    public void setDtCreado(Object dtCreado) {
        this.dtCreado = dtCreado;
    }

    public Object getDtModificado() {
        return dtModificado;
    }

    public void setDtModificado(Object dtModificado) {
        this.dtModificado = dtModificado;
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
}
