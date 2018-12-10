package com.dev.alyson.buscapedido.webservice;

import android.os.AsyncTask;

import org.ksoap2.SoapEnvelope;
import org.ksoap2.serialization.Marshal;
import org.ksoap2.serialization.MarshalFloat;
import org.ksoap2.serialization.PropertyInfo;
import org.ksoap2.serialization.SoapObject;
import org.ksoap2.serialization.SoapSerializationEnvelope;
import org.ksoap2.transport.AndroidHttpTransport;
import org.ksoap2.transport.HttpTransportSE;
import org.xmlpull.v1.XmlPullParserException;

import java.io.IOException;

public class WSpedido extends AsyncTask{

    String resp;


    @Override
    protected Object doInBackground(Object[] objects) {
        getPedido();
        return null;
    }

    @Override
    protected void onPostExecute(Object o) {
        super.onPostExecute(o);
    }

    public void getPedido (){


        String WSDL_URL = "http://SERVER:8080/g5-senior-services/sapiens_Synccom_senior_g5_co_mcm_ven_pedidos?wsdl";
        //String SOAP_ACTION = "http://services.senior.com.br/sapiens_Synccom_senior_g5_co_mcm_ven_pedidos/ConsultaPedidosGA";
        String SOAP_ACTION = "";
        String NAMESPACE = "http://services.senior.com.br";
        String METHOD_NAME = "ConsultaPedidosGA";


        SoapObject request = new SoapObject(NAMESPACE, METHOD_NAME);
        request.addProperty("user","suporte");
        request.addProperty("password","suporte");

        PropertyInfo pi = new PropertyInfo();
        pi.setName("encryption");
        pi.setValue(0);
        pi.setType(Integer.class);
        request.addProperty(pi);

        PedidosConsultaPedidosGAInFiltros L = new PedidosConsultaPedidosGAInFiltros(901783,2,1);

        PropertyInfo pi2 = new PropertyInfo();
        pi2.setName("filtros");
        pi2.setValue(L);
        pi2.setType(L.getClass());


        SoapObject parameters = new SoapObject(NAMESPACE, METHOD_NAME);

        //parameters.addProperty("","");
        parameters.addProperty(pi2);

        request.addProperty("parameters",parameters);



        SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(SoapEnvelope.VER11);
        envelope.setOutputSoapObject(request);
        envelope.addMapping(NAMESPACE , PedidosConsultaPedidosGAInFiltros.class.getSimpleName(), PedidosConsultaPedidosGAInFiltros .class);
        Marshal floatMarshal = new MarshalFloat();
        floatMarshal.register(envelope);

        HttpTransportSE htse = new HttpTransportSE(WSDL_URL);
        //AndroidHttpTransport htse = new AndroidHttpTransport(WSDL_URL);


        try {
            htse.debug = true;
            htse.call(SOAP_ACTION,envelope);

            //SoapObject result = (SoapObject)envelope.getResponse();
            //SoapObject obj = (SoapObject) envelope.bodyIn;
            //resp = obj.getProperty(0).toString();

            System.out.println("requestDump is :"+htse.requestDump);
            System.out.println("responseDump is :"+htse.responseDump);
            System.out.println("response"+envelope.getResponse());


        } catch (IOException e) {
            e.printStackTrace();
        } catch (XmlPullParserException e) {
            e.printStackTrace();
        }


    }






}
