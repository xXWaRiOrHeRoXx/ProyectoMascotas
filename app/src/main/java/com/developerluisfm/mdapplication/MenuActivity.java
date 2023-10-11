package com.developerluisfm.mdapplication;

import android.content.Intent;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

import com.developerluisfm.mdapplication.adapter.PageAdapter;
import com.developerluisfm.mdapplication.fragment.PerfilFragment;
import com.developerluisfm.mdapplication.fragment.ListaFragment;
import com.developerluisfm.mdapplication.pojo.Mascota;

import java.util.ArrayList;

public class MenuActivity extends AppCompatActivity {

    private RecyclerView rvContactos;
    private ArrayList<Mascota> mascotas;
    private Toolbar toolbar;
    private TabLayout tabLayout;
    private ViewPager viewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);

        toolbar = (Toolbar) findViewById(R.id.toolbar);
        tabLayout = (TabLayout) findViewById(R.id.tabLayout);
        viewPager = (ViewPager) findViewById(R.id.viewPger);

        rvContactos = (RecyclerView) findViewById(R.id.rvMascotas);

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        linearLayoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
        //rvContactos.setLayoutManager(linearLayoutManager);

        setUpViewPager();

        if(toolbar != null){
            Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
            toolbar.setTitle(R.string.mascotas);
            toolbar.setLogo(R.drawable.ic_marca);
            setSupportActionBar(toolbar);
        }

    }

    private ArrayList<Fragment> agregarFragment(){

        ArrayList<Fragment> fragments = new ArrayList<>();
        fragments.add(new ListaFragment());
        fragments.add(new PerfilFragment());

        return  fragments;
    }

    public void setUpViewPager(){
        viewPager.setAdapter(new PageAdapter(getSupportFragmentManager(), agregarFragment()));
        tabLayout.setupWithViewPager(viewPager);
        tabLayout.getTabAt(0).setIcon(R.drawable.dog);
        tabLayout.getTabAt(1).setIcon(R.drawable.ic_camera_alt);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.contacto) {
            Intent intent = new Intent(getBaseContext(), ContactoActivity.class);
            startActivity(intent);
            return true;
        }

        if (id == R.id.acerca) {
            Intent intent = new Intent(getBaseContext(), AcercaActivity.class);
            startActivity(intent);
            return true;
        }

        if (id == R.id.action_favorite) {
            Intent intent = new Intent(getBaseContext(), FavoritosActivity.class);
            startActivity(intent);
            return true;
        }

        if (id == R.id.action_config) {
            Intent intent = new Intent(getBaseContext(), CuentaActivity.class);
            startActivity(intent);
            return true;
        }

        return super.onOptionsItemSelected(item);
    }


}
