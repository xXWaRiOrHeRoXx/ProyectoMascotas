package com.developerluisfm.mdapplication;

import android.content.Intent;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

import com.developerluisfm.mdapplication.adapter.MascotaAdapter;
import com.developerluisfm.mdapplication.db.BaseDatos;
import com.developerluisfm.mdapplication.pojo.Mascota;

import java.util.ArrayList;

public class FavoritosActivity extends AppCompatActivity {

    ArrayList<Mascota> mascotas;
    RecyclerView listaMascotas;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_favoritos);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setTitle(R.string.favoritos);
        toolbar.setLogo(R.drawable.ic_marca);
        setSupportActionBar(toolbar);

        ActionBar ab = getSupportActionBar();
        ab.setDisplayHomeAsUpEnabled(true);


        listaMascotas = (RecyclerView) findViewById(R.id.rvMascotas);
        LinearLayoutManager lm = new LinearLayoutManager(this);
        lm.setOrientation(LinearLayoutManager.VERTICAL);
        listaMascotas.setLayoutManager(lm);

        inicializarMascotas();
        MascotaAdapter mascotaAdapter = new MascotaAdapter(mascotas);
        listaMascotas.setAdapter(mascotaAdapter);



    }

    public void inicializarMascotas(){

        mascotas = new ArrayList<Mascota>();

        /*Mascota mascota1 = new Mascota(1,"Tofi",1,R.drawable.p1,0);
        Mascota mascota2 = new Mascota(2,"Oreo",3,R.drawable.p2,2);
        Mascota mascota3 = new Mascota(3,"Black",2,R.drawable.p3,1);
        Mascota mascota4 = new Mascota(4,"Nii",6,R.drawable.p4,3);
        Mascota mascota5 = new Mascota(5,"Sisi",4,R.drawable.p5,4);



        mascotas.add(mascota1);
        mascotas.add(mascota2);
        mascotas.add(mascota3);
        mascotas.add(mascota4);
        mascotas.add(mascota5);*/

        BaseDatos db = new BaseDatos(getBaseContext());
        mascotas = db.consultarMascotasFavoritas();

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu, menu);

        MenuItem item = menu.findItem(R.id.action_favorite);
        item.setVisible(false);
        this.invalidateOptionsMenu();

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        if (id == R.id.contacto) {
            return true;
        }

        if (id == R.id.action_favorite) {
            Intent intent = new Intent(getBaseContext(), FavoritosActivity.class);
            startActivity(intent);
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
