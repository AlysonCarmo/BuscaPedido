package com.dev.alyson.buscapedido.webservice;

import android.os.AsyncTask;

import com.dev.alyson.buscapedido.dominio.Pedido;

import org.ksoap2.SoapEnvelope;
import org.ksoap2.serialization.Marshal;
import org.ksoap2.serialization.MarshalFloat;
import org.ksoap2.serialization.PropertyInfo;
import org.ksoap2.serialization.SoapObject;
import org.ksoap2.serialization.SoapPrimitive;
import org.ksoap2.serialization.SoapSerializationEnvelope;
import org.ksoap2.transport.AndroidHttpTransport;
import org.ksoap2.transport.HttpTransportSE;
import org.xmlpull.v1.XmlPullParserException;

import java.io.IOException;

public class WSpedido extends AsyncTask{

    String resp;


    @Override
    protected Object doInBackground(Object[] objects) {
        //getPedido();
        return null;
    }

    @Override
    protected void onPostExecute(Object o) {
        super.onPostExecute(o);
    }

    public Pedido getPedido (int ped){


        Pedido pedido = new Pedido();

        String WSDL_URL = "http://SERVER/g5-senior-services/sapiens_Synccom_senior_g5_co_mcm_ven_pedidos?wsdl";
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

        PedidosConsultaPedidosGAInFiltros L = new PedidosConsultaPedidosGAInFiltros(ped,2,1);

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
            //htse.debug = true;
            htse.call(SOAP_ACTION,envelope);

            //SoapObject result = (SoapObject)envelope.getResponse();
            SoapObject obj = (SoapObject) envelope.bodyIn;

            SoapObject response = (SoapObject) obj.getProperty(0);
            SoapObject dadosResultado = (SoapObject) response.getProperty("dadosResultado");
            String erroExecucao = (String) response.getProperty("erroExecucao");
            SoapPrimitive mensagemRetorno = (SoapPrimitive) response.getProperty("mensagemRetorno");

            if(erroExecucao == null ){ //&& mensagemRetorno.toString() == "Consulta Finalizada"

                String NOME = dadosResultado.getProperty("nomCli").toString();
                //String NR_PEDIDO = dadosResultado.getProperty("nomCli").toString();
                Double VALOR =  Double.parseDouble(dadosResultado.getProperty("vlrLiq").toString());
                String NOME_REP = dadosResultado.getProperty("nomRep").toString();
                String DT_EMISSAO = dadosResultado.getProperty("datEmi").toString();
                String CIDADE = dadosResultado.getProperty("cidCli").toString();
                String UF = dadosResultado.getProperty("sigUfs").toString();

                pedido.setNomeCliente(NOME);
                pedido.setNumeroPedido("901108");
                pedido.setValorPedido(VALOR);
                pedido.setCidadeCliente(CIDADE);
                pedido.setDataEmissao(DT_EMISSAO);
                pedido.setUfCliente(UF);
                pedido.setNomeRepresentante(NOME_REP);

            } else {


            }





            //resp = obj.getProperty(0).toString();

            //System.out.println("requestDump is :"+htse.requestDump);
            //System.out.println("responseDump is :"+htse.responseDump);
            //System.out.println("response"+envelope.getResponse());



        } catch (IOException e) {
            e.printStackTrace();
        } catch (XmlPullParserException e) {
            e.printStackTrace();
        }

        return pedido;
    }






}
