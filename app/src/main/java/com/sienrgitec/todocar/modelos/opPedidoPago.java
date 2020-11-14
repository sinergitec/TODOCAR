package com.sienrgitec.todocar.modelos;

import java.io.Serializable;

public class opPedidoPago implements Serializable {
    private Integer iPedido;
    private Integer iPartida;
    private Integer iFormaPago;
    private Double  deMonto;
    private Double deProcComision;
    private Double deComision;
    private Double dePorcPropina;
    private Double dePropina;
    private String dtCreado;
    private String dtModificado;
    private String cUsuCrea;
    private Integer iCliente;
    private Integer iOrigenFP;
    private String cCuenta;

    public Integer getiPedido() {
        return iPedido;
    }

    public void setiPedido(Integer iPedido) {
        this.iPedido = iPedido;
    }

    public Integer getiPartida() {
        return iPartida;
    }

    public void setiPartida(Integer iPartida) {
        this.iPartida = iPartida;
    }

    public Integer getiFormaPago() {
        return iFormaPago;
    }

    public void setiFormaPago(Integer iFormaPago) {
        this.iFormaPago = iFormaPago;
    }

    public Double getDeMonto() {
        return deMonto;
    }

    public void setDeMonto(Double deMonto) {
        this.deMonto = deMonto;
    }

    public Double getDeProcComision() {
        return deProcComision;
    }

    public void setDeProcComision(Double deProcComision) {
        this.deProcComision = deProcComision;
    }

    public Double getDeComision() {
        return deComision;
    }

    public void setDeComision(Double deComision) {
        this.deComision = deComision;
    }

    public Double getDePorcPropina() {
        return dePorcPropina;
    }

    public void setDePorcPropina(Double dePorcPropina) {
        this.dePorcPropina = dePorcPropina;
    }

    public Double getDePropina() {
        return dePropina;
    }

    public void setDePropina(Double dePropina) {
        this.dePropina = dePropina;
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

    public String getcUsuCrea() {
        return cUsuCrea;
    }

    public void setcUsuCrea(String cUsuCrea) {
        this.cUsuCrea = cUsuCrea;
    }

    public Integer getiCliente() {
        return iCliente;
    }

    public void setiCliente(Integer iCliente) {
        this.iCliente = iCliente;
    }

    public Integer getiOrigenFP() {
        return iOrigenFP;
    }

    public void setiOrigenFP(Integer iOrigenFP) {
        this.iOrigenFP = iOrigenFP;
    }

    public String getcCuenta() {
        return cCuenta;
    }

    public void setcCuenta(String cCuenta) {
        this.cCuenta = cCuenta;
    }
}
