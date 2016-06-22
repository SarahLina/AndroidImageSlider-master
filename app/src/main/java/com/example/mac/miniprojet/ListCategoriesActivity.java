package com.example.mac.miniprojet;


import android.app.FragmentTransaction;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import Metier.Categorie;

public class ListCategoriesActivity extends AppCompatActivity {
    ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_categories);


        ListCategoriesFragment listCategoriesFragment = new ListCategoriesFragment();
        Bundle bundle = new Bundle();
        listCategoriesFragment.setArguments(bundle);

        FragmentTransaction ft = getFragmentManager().beginTransaction();
        ft.replace(R.id.frameLayoutCategories, listCategoriesFragment);
        ft.commit();


        listView = (ListView) findViewById(R.id.listView);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Log.v("message","message0");

                showView((Categorie) listView.getAdapter().getItem(position));
                Log.v("message","message1");

            }
        });



    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }





    public void showView (Categorie categorie) {
        Log.v("message","message3");

        Intent intent = new Intent(this,ListProductsActivity.class);
        intent.putExtra("categorie",categorie);
        startActivity(intent);

    }
}
