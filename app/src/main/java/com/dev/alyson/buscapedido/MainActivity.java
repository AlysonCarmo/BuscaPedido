package com.dev.alyson.buscapedido;


import android.os.StrictMode;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.dev.alyson.buscapedido.dominio.Pedido;
import com.dev.alyson.buscapedido.webservice.WSpedido;

import java.util.concurrent.ExecutionException;



public class MainActivity extends AppCompatActivity implements WSpedido.OnSearchPedidoCompletedListener {

    private EditText pedido;
    private Button btnBuscar;
    private TextView nomeCliente;
    private TextView nomeRep;
    private TextView cidade;
    private TextView uf;
    private TextView emissao;
    private TextView valor;
    private ProgressBar progressBar;



    @Override
    public void onSearchPedidoCompleted(Pedido result) {
        progressBar.setVisibility(View.INVISIBLE);
        retornoPedido(result);

    }

    private void retornoPedido(Pedido p){

        if(p.getNumeroPedido() != null) {
            nomeCliente.setText(String.valueOf(p.getNomeCliente()));
            valor.setText(String.valueOf(p.getValorPedido()));
            nomeRep.setText(String.valueOf(p.getNomeRepresentante()));
            cidade.setText(String.valueOf(p.getCidadeCliente()));
            uf.setText(String.valueOf(p.getUfCliente()));
            emissao.setText(String.valueOf(p.getDataEmissao()));
        } else {
            Toast.makeText(this, "Pedido não encontrado!", Toast.LENGTH_SHORT).show();
        }

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button botao = (Button) findViewById(R.id.progressButton);
        progressBar = (ProgressBar)findViewById(R.id.progressBar);

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
                    String[] pedidos = new String[10];
                    pedidos[0] = pedido.getText().toString();
                    progressBar.setVisibility(View.VISIBLE);
                    WSpedido wb = new  WSpedido();
                    wb.setOnSearchPedidoCompletedListener(MainActivity.this);
                    wb.execute(pedidos[0]);

                }


            }
        });





    }


}
