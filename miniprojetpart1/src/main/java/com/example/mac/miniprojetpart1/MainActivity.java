package com.example.mac.miniprojetpart1;

import android.app.FragmentTransaction;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.example.msia.julina.AproposFragment;
import com.example.msia.julina.HelpFragment;
import com.example.msia.julina.LoginActivity;

import Metier.Product;
import Services.GetDisponibiliteTask;
import Services.UpdateDispoTask;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    NavigationView navigationView =null;
    Toolbar toolbar = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // set the fragment
        /*MainFragment fragment = new MainFragment();
        android.support.v4.app.FragmentTransaction fragmentTransaction = getSupportFragmentManager()
                .beginTransaction();
        fragmentTransaction.replace(R.id.fragment_contenairer,fragment);
        fragmentTransaction.commit();*/

        ActivityFragment fragment = new ActivityFragment();
        Intent intent = getIntent();
        Bundle bundle = new Bundle();

        fragment.setArguments(bundle);
        android.support.v4.app.FragmentTransaction fragmentTransaction = getSupportFragmentManager()
                .beginTransaction();
        fragmentTransaction.replace(R.id.fragment_contenairer, fragment);
        fragmentTransaction.commit();

        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement

            MainFragment fragment = new MainFragment();
            android.support.v4.app.FragmentTransaction fragmentTransaction = getSupportFragmentManager()
                    .beginTransaction();
            fragmentTransaction.replace(R.id.fragment_contenairer,fragment);
            fragmentTransaction.commit();
            Toast.makeText(MainActivity.this, "Card", Toast.LENGTH_SHORT).show();


        return super.onOptionsItemSelected(item);
    }

    public void AjouterPanier (View view)
    {
       // new UpdateDispoTask(this).execute(1,"bleu","XS", 2 );
        Intent intent = new Intent(this, LoginActivity.class);
        startActivity(intent);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_homme) {
            Product.categorie_current="Hommes";
            Intent intent = new Intent(this,ProductActivity.class);
            startActivity(intent);

        } else if (id == R.id.nav_femme) {
            Product.categorie_current="Femmes";
            Intent intent = new Intent(this,ProductActivity.class);
            startActivity(intent);

        } else if (id == R.id.nav_enfant) {

            Product.categorie_current="Enfants";
            Intent intent = new Intent(this,ProductActivity.class);
            startActivity(intent);

        } else if (id == R.id.nav_monpanier) {

            MainFragment fragment = new MainFragment();
            android.support.v4.app.FragmentTransaction fragmentTransaction = getSupportFragmentManager()
                    .beginTransaction();
            fragmentTransaction.replace(R.id.fragment_contenairer,fragment);
            fragmentTransaction.commit();

        } else if (id == R.id.nav_cmd) {
            CmdFragment fragment = new CmdFragment();
            android.support.v4.app.FragmentTransaction fragmentTransaction = getSupportFragmentManager()
                    .beginTransaction();
            fragmentTransaction.replace(R.id.fragment_contenairer,fragment);
            fragmentTransaction.commit();

        } else if (id == R.id.nav_notification) {
            NotificationFragment fragment = new NotificationFragment();
            android.support.v4.app.FragmentTransaction fragmentTransaction = getSupportFragmentManager()
                    .beginTransaction();
            fragmentTransaction.replace(R.id.fragment_contenairer,fragment);
            fragmentTransaction.commit();

        } else if (id == R.id.nav_aide) {
            HelpFragment fragment = new HelpFragment();
            android.support.v4.app.FragmentTransaction fragmentTransaction = getSupportFragmentManager()
                    .beginTransaction();
            fragmentTransaction.replace(R.id.fragment_contenairer, fragment);
            fragmentTransaction.commit();

        } else if (id == R.id.nav_apropos) {
            AproposFragment fragment = new AproposFragment();
            android.support.v4.app.FragmentTransaction fragmentTransaction = getSupportFragmentManager()
                    .beginTransaction();
            fragmentTransaction.replace(R.id.fragment_contenairer, fragment);
            fragmentTransaction.commit();

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
    public void femmes(View view)
    {
        Product.categorie_current="Femmes";
        Intent intent = new Intent(this,ProductActivity.class);
        startActivity(intent);
    }
    public void hommes(View view)
    {
        Product.categorie_current="Hommes";
        Intent intent = new Intent(this,ProductActivity.class);
        startActivity(intent);
    }
    public void enfants(View view)
    {
        Product.categorie_current="Enfants";
        Intent intent = new Intent(this,ProductActivity.class);
        startActivity(intent);
    }
}
