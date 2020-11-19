package com.sienrgitec.todocar.modelos;

import java.io.Serializable;

public class opPedidoDet implements Serializable {
    private Integer iPedido;
    private Integer iPedidoProv;
    private Integer iPartida;
    private String dtFecha;
    private Integer iArticulo;
    private String cArticulo;
    private String cDescripcion;
    private String cObs;
    private Boolean lPesado;
    private Integer iUMedida;
    private Double dePrecio;
    private Double dePorcImp;
    private Double deImpuesto;
    private Double dePrecioVta;
    private Double dePorcDescto;
    private Double deDescuento;
    private Double dePrecioUnit;
    private Double deCantidad;
    private Double deImporte;
    private Double deCantCancela;
    private String dtCancela;
    private String dtCreado;
    private Integer iRazon;
    private String dtModificado;
    private String cTipoRazon;
    private String cUsuCrea;
    private String cUsuModificado;
    private Object id;

    public Integer getiPedido() {
        return iPedido;
    }

    public void setiPedido(Integer iPedido) {
        this.iPedido = iPedido;
    }

    public Integer getiPedidoProv() {
        return iPedidoProv;
    }

    public void setiPedidoProv(Integer iPedidoProv) {
        this.iPedidoProv = iPedidoProv;
    }

    public Integer getiPartida() {
        return iPartida;
    }

    public void setiPartida(Integer iPartida) {
        this.iPartida = iPartida;
    }

    public String getDtFecha() {
        return dtFecha;
    }

    public void setDtFecha(String dtFecha) {
        this.dtFecha = dtFecha;
    }

    public Integer getiArticulo() {
        return iArticulo;
    }

    public void setiArticulo(Integer iArticulo) {
        this.iArticulo = iArticulo;
    }

    public String getcArticulo() {
        return cArticulo;
    }

    public void setcArticulo(String cArticulo) {
        this.cArticulo = cArticulo;
    }

    public String getcDescripcion() {
        return cDescripcion;
    }

    public void setcDescripcion(String cDescripcion) {
        this.cDescripcion = cDescripcion;
    }

    public String getcObs() {
        return cObs;
    }

    public void setcObs(String cObs) {
        this.cObs = cObs;
    }

    public Boolean getlPesado() {
        return lPesado;
    }

    public void setlPesado(Boolean lPesado) {
        this.lPesado = lPesado;
    }

    public Integer getiUMedida() {
        return iUMedida;
    }

    public void setiUMedida(Integer iUMedida) {
        this.iUMedida = iUMedida;
    }

    public Double getDePrecio() {
        return dePrecio;
    }

    public void setDePrecio(Double dePrecio) {
        this.dePrecio = dePrecio;
    }

    public Double getDePorcImp() {
        return dePorcImp;
    }

    public void setDePorcImp(Double dePorcImp) {
        this.dePorcImp = dePorcImp;
    }

    public Double getDeImpuesto() {
        return deImpuesto;
    }

    public void setDeImpuesto(Double deImpuesto) {
        this.deImpuesto = deImpuesto;
    }

    public Double getDePrecioVta() {
        return dePrecioVta;
    }

    public void setDePrecioVta(Double dePrecioVta) {
        this.dePrecioVta = dePrecioVta;
    }

    public Double getDePorcDescto() {
        return dePorcDescto;
    }

    public void setDePorcDescto(Double dePorcDescto) {
        this.dePorcDescto = dePorcDescto;
    }

    public Double getDeDescuento() {
        return deDescuento;
    }

    public void setDeDescuento(Double deDescuento) {
        this.deDescuento = deDescuento;
    }

    public Double getDePrecioUnit() {
        return dePrecioUnit;
    }

    public void setDePrecioUnit(Double dePrecioUnit) {
        this.dePrecioUnit = dePrecioUnit;
    }

    public Double getDeCantidad() {
        return deCantidad;
    }

    public void setDeCantidad(Double deCantidad) {
        this.deCantidad = deCantidad;
    }

    public Double getDeImporte() {
        return deImporte;
    }

    public void setDeImporte(Double deImporte) {
        this.deImporte = deImporte;
    }

    public Double getDeCantCancela() {
        return deCantCancela;
    }

    public void setDeCantCancela(Double deCantCancela) {
        this.deCantCancela = deCantCancela;
    }

    public String getDtCancela() {
        return dtCancela;
    }

    public void setDtCancela(String dtCancela) {
        this.dtCancela = dtCancela;
    }

    public String getDtCreado() {
        return dtCreado;
    }

    public void setDtCreado(String dtCreado) {
        this.dtCreado = dtCreado;
    }

    public Integer getiRazon() {
        return iRazon;
    }

    public void setiRazon(Integer iRazon) {
        this.iRazon = iRazon;
    }

    public String getDtModificado() {
        return dtModificado;
    }

    public void setDtModificado(String dtModificado) {
        this.dtModificado = dtModificado;
    }

    public String getcTipoRazon() {
        return cTipoRazon;
    }

    public void setcTipoRazon(String cTipoRazon) {
        this.cTipoRazon = cTipoRazon;
    }

    public String getcUsuCrea() {
        return cUsuCrea;
    }

    public void setcUsuCrea(String cUsuCrea) {
        this.cUsuCrea = cUsuCrea;
    }

    public String getcUsuModificado() {
        return cUsuModificado;
    }

    public void setcUsuModificado(String cUsuModificado) {
        this.cUsuModificado = cUsuModificado;
    }

    public Object getId() {
        return id;
    }

    public void setId(Object id) {
        this.id = id;
    }
}
