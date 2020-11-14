package com.sienrgitec.todocar.modelos;

import java.io.Serializable;

public class opPedidoDomicilio implements Serializable {
    private Integer iPedido;
    private Integer iDomicilio;
    private Integer iCliente;
    private Boolean lHabitual;
    private String dtCreado;
    private String dtModificado;
    private String cUsuModifica;
    private String cUsuCrea;

    public String getcUsuCrea() {
        return cUsuCrea;
    }

    public void setcUsuCrea(String cUsuCrea) {
        this.cUsuCrea = cUsuCrea;
    }

    public Integer getiPedido() {
        return iPedido;
    }

    public void setiPedido(Integer iPedido) {
        this.iPedido = iPedido;
    }

    public Integer getiDomicilio() {
        return iDomicilio;
    }

    public void setiDomicilio(Integer iDomicilio) {
        this.iDomicilio = iDomicilio;
    }

    public Integer getiCliente() {
        return iCliente;
    }

    public void setiCliente(Integer iCliente) {
        this.iCliente = iCliente;
    }

    public Boolean getlHabitual() {
        return lHabitual;
    }

    public void setlHabitual(Boolean lHabitual) {
        this.lHabitual = lHabitual;
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

    public String getcUsuModifica() {
        return cUsuModifica;
    }

    public void setcUsuModifica(String cUsuModifica) {
        this.cUsuModifica = cUsuModifica;
    }

    public String getUsuModifica() {
        return UsuModifica;
    }

    public void setUsuModifica(String usuModifica) {
        UsuModifica = usuModifica;
    }

    private String UsuModifica;




}
