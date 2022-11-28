package com.example.apiexamen;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.google.gson.Gson;

import org.json.JSONException;
import org.json.JSONObject;

import clases.Singleton;
import clases.numeros;

public class MainActivity extends AppCompatActivity {


    RecyclerView recyclerView;
    //BOTONES
    Button botonpedirnumero;
    Button botonenviardatos;
    Button botonirarecyclewview;
    Button btnlimpiar;
    TextView textnum,txtmarcador;
    int numero=0,sumador=0,turno=0;

   private   RequestQueue requestQueue;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        botonpedirnumero=(Button) findViewById(R.id.pedirnumero);
        requestQueue = Singleton.getInstance(MainActivity.this).getRequestQueue();

         txtmarcador=(TextView) findViewById(R.id.sumatoria);
        //PEDIR NUMEROS
         botonpedirnumero.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                textnum=(TextView) findViewById(R.id.setnumeros);

                String url ="https://ramiro.uttics.com/api/numero";
                JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET, url, null, new Response.Listener<JSONObject>()
                {
                    @Override
                    public void onResponse(JSONObject response)
                    {
                        Gson gson=new Gson();
                       numeros respuesta=gson.fromJson(response.toString(),numeros.class);
                       numero=Integer.parseInt(respuesta.getNumero());
                       textnum.setText(respuesta.getNumero());

                        if(turno==3)
                        {
                            if(sumador==21)
                            {
                                Toast.makeText(MainActivity.this, "GANASTE....", Toast.LENGTH_SHORT).show();
                                sumador=0;
                                turno=0;
                                textnum.setText(""); // txtmarcador.setText("");
                            }else
                            {
                                Toast.makeText(MainActivity.this, "NO GANASTE....", Toast.LENGTH_SHORT).show();
                                sumador=0;
                                turno=0;
                                textnum.setText("");
                            }
                        }else
                        {
                            turno++;
                            sumador=numero+sumador;
                            Toast.makeText(MainActivity.this, "Tu Suma"+sumador, Toast.LENGTH_SHORT).show();
                            txtmarcador.setText(""+sumador);
                        }
                    }
                },
                        new Response.ErrorListener()
                        {
                    @Override
                    public void onErrorResponse(VolleyError error) {

                    }
                });
                requestQueue.add(request);

            }
        });
        //IR AL RESULTADOS
        botonirarecyclewview=(Button) findViewById(R.id.verresultados);
        botonirarecyclewview.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view) {
                Intent i= new Intent(getApplicationContext(),RecyclewView.class);
                startActivity(i);
            }
        });

        //ENVIAR DATOS
        botonenviardatos=(Button) findViewById(R.id.enviarresultados);
        botonenviardatos.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view) {
                String url="https://ramiro.uttics.com/api/enviarnumero";
               // String limpiar="https://ramiro.uttics.com/api/limpiar";
                JSONObject jsonBody = new JSONObject();
                JSONObject array =new JSONObject();
                try
                {
                    jsonBody.put("nombre","Pablo");
                    jsonBody.put("numero",""+sumador);
                 } catch (JSONException e)
                {
                    e.printStackTrace();
                }
                try {
                    array.put("persona", jsonBody);
                }catch (JSONException e)
                {
                    e.printStackTrace();
                }

                JsonObjectRequest peticion = new JsonObjectRequest(Request.Method.POST, url, array, new Response.Listener<JSONObject>()
                {
                    @Override
                    public void onResponse(JSONObject response)
                    {
                        Toast.makeText(MainActivity.this,"Su Puntuacion ha sido enviada ",Toast.LENGTH_SHORT).show();
                    }
                }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(MainActivity.this,"HUBO ERROR",Toast.LENGTH_SHORT).show();


                    }
                });
                requestQueue.add(peticion);

            }
        });

        btnlimpiar=(Button) findViewById(R.id.btnlimpiar);
        btnlimpiar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view)
            {
                String url3="https://ramiro.uttics.com/api/limpiar";
                JsonObjectRequest limpiar = new JsonObjectRequest(Request.Method.POST, url3, null, new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response)
                    {
                        Toast.makeText(MainActivity.this,"Se limpio",Toast.LENGTH_SHORT).show();
                    }
                }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {

                    }
                });   requestQueue.add(limpiar);
            }
        });


    }



}