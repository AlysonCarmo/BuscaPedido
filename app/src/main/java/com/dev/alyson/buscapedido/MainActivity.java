package com.dev.alyson.buscapedido;


import android.os.StrictMode;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.dev.alyson.buscapedido.dominio.Pedido;
import com.dev.alyson.buscapedido.webservice.WSpedido;


public class MainActivity extends AppCompatActivity {

    private EditText pedido;
    private Button btnBuscar;
    private TextView nomeCliente;
    private TextView nomeRep;
    private TextView cidade;
    private TextView uf;
    private TextView emissao;
    private TextView valor;
    WSpedido wb = new  WSpedido();



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if (android.os.Build.VERSION.SDK_INT > 9)
        {
            StrictMode.ThreadPolicy policy = new
                    StrictMode.ThreadPolicy.Builder().permitAll().build();
            StrictMode.setThreadPolicy(policy);
        }


        //wb.execute(); // VERIFICAR FUNCIONAMENTO DA WSpedido


        btnBuscar = (Button) findViewById(R.id.buttonBuscar);
        nomeCliente = (TextView) findViewById(R.id.nomeCliente);
        nomeRep = (TextView) findViewById(R.id.nomeRep);
        cidade = (TextView) findViewById(R.id.nomeCidade);
        uf = (TextView) findViewById(R.id.sigUfs);
        emissao = (TextView) findViewById(R.id.dataEmissao);
        valor = (TextView) findViewById(R.id.valorPedido);

        btnBuscar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                pedido = (EditText) findViewById(R.id.buscaPedido);

                String teste = pedido.getText().toString();

                if (pedido.getText().toString().equals("") || pedido.getText().toString().equals(" ")){
                    Toast.makeText(v.getContext(), "Pedido não informado!", Toast.LENGTH_SHORT).show();

                } else{
                    int ped = Integer.parseInt(pedido.getText().toString());
                    Pedido pedido = wb.getPedido(ped);

                    nomeCliente.setText(String.valueOf(pedido.getNomeCliente()));
                    valor.setText(String.valueOf(pedido.getValorPedido()));
                    nomeRep.setText(String.valueOf(pedido.getNomeRepresentante()));
                    cidade.setText(String.valueOf(pedido.getCidadeCliente()));
                    uf.setText(String.valueOf(pedido.getUfCliente()));
                    emissao.setText(String.valueOf(pedido.getDataEmissao()));



                }


            }
        });





    }
}
