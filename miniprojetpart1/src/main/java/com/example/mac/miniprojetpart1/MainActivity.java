package com.example.mac.miniprojetpart1;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.DisplayMetrics;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.example.msia.julina.AproposFragment;
import com.example.msia.julina.HelpFragment;
import com.example.msia.julina.LoginActivity;

import java.util.ArrayList;
import java.util.List;

import Helpers.DataBaseHelper;
import Metier.ArticlePannier;
import Metier.FullProduct;
import Metier.Product;
import Repository.CardLineRepo;
import Repository.FullProductRepo;
import Repository.ProductRepo;
import Services.GetDisponibiliteTask;
import Services.GetFullProductTask;
import util.UtilService;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    NavigationView navigationView =null;
    Toolbar toolbar = null;
    public static int idMainActivity;
    private CardLineRepo cardLineRepo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //initBase();
        // set the fragment
        /*MainFragment fragment = new MainFragment();
        android.support.v4.app.FragmentTransaction fragmentTransaction = getSupportFragmentManager()
                .beginTransaction();
        fragmentTransaction.replace(R.id.fragment_contenairer,fragment);
        fragmentTransaction.commit();*/




            if (idMainActivity == R.id.nav_monpanier) {

                idMainActivity= -1;
                MainFragment fragment = new MainFragment();
                android.support.v4.app.FragmentTransaction fragmentTransaction = getSupportFragmentManager()
                        .beginTransaction();
                fragmentTransaction.replace(R.id.fragment_contenairer, fragment);
                fragmentTransaction.commit();
            }
        else
            if (idMainActivity == R.id.nav_cmd) {

                idMainActivity= -1;
                CmdFragment fragment = new CmdFragment();
                android.support.v4.app.FragmentTransaction fragmentTransaction = getSupportFragmentManager()
                        .beginTransaction();
                fragmentTransaction.replace(R.id.fragment_contenairer,fragment);
                fragmentTransaction.commit();
            }
        else
            {
                ActivityFragment fragment = new ActivityFragment();
                Intent intent = getIntent();
                Bundle bundle = new Bundle();

                fragment.setArguments(bundle);
                android.support.v4.app.FragmentTransaction fragmentTransaction = getSupportFragmentManager()
                        .beginTransaction();
                fragmentTransaction.replace(R.id.fragment_contenairer, fragment);
                fragmentTransaction.commit();
            }


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
    protected void onStart() {
        super.onStart();
        initBase();
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
        idMainActivity = item.getItemId();

        //noinspection SimplifiableIfStatement

            MainFragment fragment = new MainFragment();
            android.support.v4.app.FragmentTransaction fragmentTransaction = getSupportFragmentManager()
                    .beginTransaction();
            fragmentTransaction.replace(R.id.fragment_contenairer,fragment);
            fragmentTransaction.commit();


        return super.onOptionsItemSelected(item);
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

    public void initBase(){
        cardLineRepo=new CardLineRepo(getApplicationContext());
        List<ArticlePannier> articlePannierList = cardLineRepo.getCardLine();
        DataBaseHelper bdd = new DataBaseHelper(this.getApplicationContext());


        if (new UtilService().checkNetwork(this)) {
            new GetFullProductTask(this).execute(getScreenDensity());
        } else {
            Toast.makeText(this, "Aucune connexion", Toast.LENGTH_SHORT).show();
        }




    }



    public String getScreenDensity() {
        DisplayMetrics metrics = new DisplayMetrics();
        this.getWindowManager().getDefaultDisplay().getMetrics(metrics);
        String density ="";
        switch(metrics.densityDpi){
            case DisplayMetrics.DENSITY_LOW:
                density="ldpi";
                break;
            case DisplayMetrics.DENSITY_MEDIUM:
                density= "mdpi";
                break;
            case DisplayMetrics.DENSITY_HIGH:
                density="hdpi";
                break;
            case DisplayMetrics.DENSITY_XHIGH:
                density= "xhdpi";
                break;
            case DisplayMetrics.DENSITY_XXHIGH:
                density= "xxhdpi";
            case DisplayMetrics.DENSITY_XXXHIGH:
                density= "xxxhdpi";
                break;
        }

        return density;

    }



}
