package com.example.apiexamen;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.google.gson.Gson;

import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import adapter.AdaptadorGanadores;
import clases.Ganadores;
import clases.Nombres;
import clases.Singleton;

public class RecyclewView extends AppCompatActivity {


    AdaptadorGanadores adapter;
    List<Ganadores> ganadores;
    List<Nombres>  nombres;
    RecyclerView recyclewvieww;

    private RequestQueue mQueue;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recyclew_view);

        mQueue = Singleton.getInstance(RecyclewView.this).getRequestQueue();
        ganadores = new ArrayList<>();
        nombres= new ArrayList<>();

        getListResults();

    }

    public void getListResults()
    {
     String url2="https://ramiro.uttics.com/api/ganadores";

        JsonObjectRequest request2= new JsonObjectRequest(Request.Method.GET, url2, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {

                recyclewvieww=findViewById(R.id.recyclewviev);
               recyclewvieww.setHasFixedSize(true);
                LinearLayoutManager layoutManager = new LinearLayoutManager(getApplicationContext());
                //recyclewvieww.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
                recyclewvieww.setLayoutManager(layoutManager);

                Gson gson= new Gson();
                Ganadores winner = gson.fromJson(response.toString(), Ganadores.class);
                adapter = new AdaptadorGanadores(winner.getData());



                recyclewvieww.setAdapter(adapter);

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                error.printStackTrace();
            }
        });

        mQueue.add(request2);

    }





    private void setAdapter()
    {
        //  numerosAdaptador adaptador = new  numerosAdaptador(numeroslist);
        //RecyclerView.LayoutManager LayoutManager= new LinearLayoutManager(getApplicationContext());
     /*   recyclerView.setLayoutManager(LayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(adaptador);
        recyclerView.setHasFixedSize(true);*/
    }

}