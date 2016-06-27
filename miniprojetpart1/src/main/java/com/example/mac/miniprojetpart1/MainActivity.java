package com.example.mac.miniprojetpart1;

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

import java.util.ArrayList;

import Metier.FullProduct;
import Metier.Product;
import Repository.CardLineRepo;
import Repository.FullProductRepo;
import Repository.ProductRepo;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    NavigationView navigationView =null;
    Toolbar toolbar = null;

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
            Product product= new Product();
            product.setName("T shirt 1");
            product.setCategorie("Top & T-shirt");
        // product.setImg(R.drawable.ft81);
            product.setPrice(39734);


            product.setRef("zjhefj342P9d8");
            product.setTypeClient("femme");

            /*Liizes(size);
            List <String> color= new ArrayList<>();
            color.add("Noir");
            color.add("Blanc");
            color.add("Rose");
            color.add("Gris");*/
            /* product.setColors(color);
            List <Integer> im= new  ArrayList<>();
            im.add(R.drawable.ft81);
            im.add(R.drawable.ft82);
            im.add(R.drawable.ft83);


            product.setTab_img(im);*/
            ProductRepo productRepo = new ProductRepo(this);


            productRepo.addProduct(product);



            FullProductRepo fullProductRepo = new FullProductRepo(this);
            if (fullProductRepo.getIdFullProd(productRepo.getProductsByRef("zjhefj342P9d8").getId_product(),"vert","XS")==-1){
                System.out.println("Hi--------------------------------");
                fullProductRepo.addFullProduct(productRepo.getProductsByRef("zjhefj342P9d8").getId_product(),"vert","XS");
            }
            //Toast.makeText(this,productRepo.getProductsByRef("zjhefj342P98").getId_product(),Toast.LENGTH_LONG).show();
           //fullProductRepo.addFullProduct(productRepo.getProductsByRef("zjhefj342P9d8").getId_product(),"vert","XS");
           //fullProductRepo.addFullProduct(productRepo.getProductsByRef("zjhefj342P9d8").getId_product(),"bleu","XS");
           //fullProductRepo.addFullProduct(productRepo.getProductsByRef("zjhefj342P9d8").getId_product(),"bleu","S");

            product= new Product();
            product.setName("T shirt 2");
            product.setCategorie("Top & T-shirt");
            //product.setImg(R.drawable.ft1);
            product.setPrice(1200);
            product.setRef("hefj3sasP9s8");
            product.setTypeClient("homme");
       // if (productRepo.getProductsByRef("hefj3sasP9s8").getId_product()==-1){

            productRepo.addProduct(product);
        //}
        if (fullProductRepo.getIdFullProd(productRepo.getProductsByRef("hefj3sasP9s8").getId_product(),"marron","XS")!=0){
            fullProductRepo.addFullProduct(productRepo.getProductsByRef("hefj3sasP9s8").getId_product(),"marron","XS");
        }
            //fullProductRepo.addFullProduct(productRepo.getProductsByRef("hefj3sasP9s8").getId_product(),"marron","XS");
           // fullProductRepo.addFullProduct(productRepo.getProductsByRef("hefj3sasP9s8").getId_product(),"noir","XS");
           // fullProductRepo.addFullProduct(productRepo.getProductsByRef("hefj3sasP9s8").getId_product(),"noir","XS");

         /*   size= new ArrayList<>();
            size.add("S");
            size.add("M");
            size.add("L");
            product.setSizes(size);
            color= new ArrayList<>();
            color.add("bleu");
            color.add("Blanc");
            product.setColors(color);
            im= new  ArrayList<>();
            im.add(R.drawable.ft1);
            im.add(R.drawable.ft2);
            product.setTab_img(im);
            productList.add(product);*/ /*
        product= new Product();
        product.setName("T shirt 3");
        product.setCategorie("Top & T-shirt");
        product.setImg(R.drawable.ft2);
        product.setPrice(1300);
        product.setRef("hefj3sasP98");
        product.setTypeClient("homme");
           size= new ArrayList<>();
            size.add("S");
            size.add("M");
            size.add("L");
            product.setSizes(size);
            color= new ArrayList<>();
            color.add("bleu");
            color.add("Blanc");
            color.add("jaune");
            product.setColors(color);
            im= new  ArrayList<>();
            im.add(R.drawable.ft2);
            im.add(R.drawable.ft3);
            product.setTab_img(im);
            productList.add(product);

            product= new Product();
            product.setName("T shirt 4");
            product.setCategorie("Top & T-shirt");
            product.setImg(R.drawable.ft3);
            product.setPrice(1400);
            product.setRef("hefj3sasP98");
            product.setTypeClient("Femmes");
            size= new ArrayList<>();
            size.add("S");
            size.add("M");
            size.add("L");
            product.setSizes(size);
            color= new ArrayList<>();
            color.add("bleu");
            color.add("Blanc");
            product.setColors(color);
            im= new  ArrayList<>();
            im.add(R.drawable.ft3);
            im.add(R.drawable.ft4);
            im.add(R.drawable.ft5);
            product.setTab_img(im);
            productList.add(product);*/


    }


}
