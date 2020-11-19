package com.sienrgitec.todocar.configuracion;

import com.sienrgitec.todocar.modelos.ctArtProveedor;
import com.sienrgitec.todocar.modelos.ctCliente;
import com.sienrgitec.todocar.modelos.ctDomicilio;
import com.sienrgitec.todocar.modelos.ctFormasPago;
import com.sienrgitec.todocar.modelos.ctUsuario;
import com.sienrgitec.todocar.modelos.opPedido;
import com.sienrgitec.todocar.modelos.opPedidoDet;
import com.sienrgitec.todocar.modelos.opPedidoDomicilio;
import com.sienrgitec.todocar.modelos.opPedidoProveedor;

import java.util.ArrayList;

public class Globales  {
    public static String URL          = "http://192.168.1.102:8083/painal/rest/painalService/";
    //public static  String  URL = "http://sinergitecdemo.ddns.net:8083/painal/rest/painalService/";

    public static Integer vg_iArticulo = 0;
    public static Integer vg_iPartida = 0;
    public static String  vg_cDescripcion = "";
    public static Boolean vg_lPideCant = true;

    public static ctArtProveedor g_ctArtProveedor = null;
    public static ctFormasPago   g_ctFormasPago   = null;
    public static ctUsuario      g_ctUsuario      = null;
    public static ctDomicilio    g_ctDomicilio    = null;
    public static ctCliente      g_ctCliente      = null;

    public static ArrayList<opPedido>    opPedidoList    = new ArrayList<>();
    public static ArrayList<opPedidoDet> opPedidoDetList = new ArrayList<>();
    public static ArrayList<opPedidoProveedor> opPedidoProvList = new ArrayList<>();
    public static ArrayList<opPedidoDomicilio> opPedidDomList = new ArrayList<>();
}
