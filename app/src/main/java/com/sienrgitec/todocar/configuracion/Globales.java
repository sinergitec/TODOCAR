package com.sienrgitec.todocar.configuracion;

import com.sienrgitec.todocar.modelos.ctArtProveedor;
import com.sienrgitec.todocar.modelos.opPedido;
import com.sienrgitec.todocar.modelos.opPedidoDet;
import com.sienrgitec.todocar.modelos.opPedidoProveedor;

import java.util.ArrayList;

public class Globales  {
    public static String URL          = "http://192.168.1.102:8083/painal/rest/painalService/";
    //public static  String  URL = "http://sinergitecdemo.ddns.net:8083/painal/rest/painalService/";

    public static Integer vg_iArticulo = 0;
    public static ctArtProveedor g_ctArtProveedor =  null;

    public static ArrayList<opPedido>    opPedidoList    = new ArrayList<>();
    public static ArrayList<opPedidoDet> opPedidoDetList = new ArrayList<>();
    public static ArrayList<opPedidoProveedor> opPedidoProvList = new ArrayList<>();
}
