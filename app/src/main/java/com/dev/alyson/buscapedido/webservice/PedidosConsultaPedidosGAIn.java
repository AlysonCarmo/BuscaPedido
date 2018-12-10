package com.dev.alyson.buscapedido.webservice;

import org.ksoap2.serialization.KvmSerializable;
import org.ksoap2.serialization.PropertyInfo;

import java.util.Hashtable;

public class PedidosConsultaPedidosGAIn implements KvmSerializable {


    PedidosConsultaPedidosGAInFiltros filtros;
    PropertyInfo pi = new PropertyInfo();

    public PedidosConsultaPedidosGAIn(){

    }

    public PedidosConsultaPedidosGAIn(Integer pedido, Integer codemp, Integer codfil ){

        filtros = new PedidosConsultaPedidosGAInFiltros(pedido,codemp,codfil);

        pi.setName("filtros");
        pi.setValue(filtros);
        pi.setType(filtros.getClass());
    }

    @Override
    public Object getProperty(int arg0) {
        switch (arg0) {
            case 0:
                return pi;
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
                arg2.type = PropertyInfo.class;
                arg2.name = "filtros";
                break;
            default:
                break;
        }

    }

    @Override
    public void setProperty(int index, Object value) {
        switch (index) {
            case 0:
                pi = (PropertyInfo) value;
                break;
            default:
                break;
        }

    }
}
