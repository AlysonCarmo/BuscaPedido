package com.dev.alyson.buscapedido.webservice;

import org.ksoap2.serialization.KvmSerializable;
import org.ksoap2.serialization.PropertyInfo;

import java.util.Hashtable;

public class PedidosConsultaPedidosGAInFiltros implements KvmSerializable {


    Integer pedido;
    Integer codemp;
    Integer codfil;

    public PedidosConsultaPedidosGAInFiltros(){

    }

    public PedidosConsultaPedidosGAInFiltros(Integer pedido, Integer codemp, Integer codfil ){
        this.pedido = pedido;
        this.codemp = codemp;
        this.codfil = codfil;
    }

    @Override
    public Object getProperty(int arg0) {
        switch (arg0) {
            case 0:
                return pedido;
            case 1:
                return codemp;
            case 2:
                return codfil;
            default:
                return null;
        }
    }

    @Override
    public int getPropertyCount() {
        return 3;
    }

    @Override
    public void getPropertyInfo(int index, Hashtable arg1, PropertyInfo arg2) {
        switch (index) {

            case 0:
                arg2.type = PropertyInfo.INTEGER_CLASS;
                arg2.name = "numPed";
                break;
            case 1:
                arg2.type = PropertyInfo.INTEGER_CLASS;
                arg2.name = "codEmp";
                break;
            case 2:
                arg2.type = PropertyInfo.INTEGER_CLASS;
                arg2.name = "codFil";
                break;
            default:
                break;
        }

    }

    @Override
    public void setProperty(int index, Object value) {
        switch (index) {
            case 0:
                pedido = (Integer) value;
                break;
            case 1:
                codemp = (Integer) value;
                break;
            case 2:
                codfil = (Integer) value;
                break;
            default:
                break;
        }

    }
}
