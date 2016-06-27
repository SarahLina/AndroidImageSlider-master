package com.example.mac.miniprojetpart1;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import Adapters.CustomAdapter;
import Metier.FullProduct;
import Metier.Product;
import Repository.FullProductRepo;
import Repository.ProductRepo;


public class ProductFragment extends Fragment {
    CustomAdapter cutomAdapter ;
    ListView listView;
    ArrayAdapter arrayAdapter;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_product,null);
        listView = (ListView) view.findViewById(R.id.listView);
        cutomAdapter = new CustomAdapter(view.getContext(), getProductList(view.getContext()));
        listView.setAdapter(cutomAdapter);

        //Implémenter le Spinner

        Spinner spinner= (Spinner) view.findViewById(R.id.spinner);          //on cast ce qu on a récupérer en spinner
        String [] values= new String[]{};
        if (Product.categorie_current.compareTo("Femmes")==0)
        {
            values= new String[] {"Tous","Top & T-shirt", "Robes & Jupes","Pantalons & Jeans", "Accessoires", "Chaussures"};
        }
        if (Product.categorie_current.compareTo("Hommes")==0)
        {
            values= new String[]{"Tous","T-shirt","Shorts & Pantalons","Costumes","Vetements de sport", "Chaussures"};
        }
        if (Product.categorie_current.compareTo("Enfants")==0)
        {
            values= new String[]{"Tous","T-shirt","Shorts & Pantalons","Pulls et gilets","Vetements de sport", "Chaussures"};
        }

        String [] names= values;
        arrayAdapter= new ArrayAdapter(getContext(), android.R.layout.simple_spinner_item, names);
        spinner.setAdapter(arrayAdapter);



        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                cutomAdapter.getFilter().filter(arrayAdapter.getItem(position).toString());
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });



        return view;
    }

    public void ajouter (View view)
    {
        Toast.makeText(view.getContext(), "produit ajouté", Toast.LENGTH_LONG);
    }

    public List<Product> getProductList(Context context) {
        List<Product> productList = new ArrayList<Product>();
        ProductRepo productRepo = new ProductRepo(context);
        FullProductRepo fullProductRepo = new FullProductRepo(context);
        // Type Client =  Femmes
        List<String> tabColor = new ArrayList<>();
        List<String> tabSize = new ArrayList<>();
        if (Product.categorie_current.compareTo("Femmes")==0)
        {
            productList=productRepo.getProductsType("femme");

        }
        if (Product.categorie_current.compareTo("Hommes")==0){
            productList=productRepo.getProductsType("homme");
        }
        if (Product.categorie_current.compareTo("Enfants")==0) {
            productList=productRepo.getProductsType("enfant");
        }
        if (!productList.isEmpty()){
        for(Product iProd:productList) {
            tabColor = new ArrayList<>();
            tabSize = new ArrayList<>();
            if (fullProductRepo.existColorProd("bleu", iProd.getId_product())) {
                tabColor.add("bleu");
            }
            if (fullProductRepo.existColorProd("noir", iProd.getId_product())) {
                tabColor.add("noir");
            }
            if (fullProductRepo.existColorProd("blanc", iProd.getId_product())) {
                tabColor.add("blanc");
            }
            if (fullProductRepo.existColorProd("rouge", iProd.getId_product())) {
                tabColor.add("rouge");
            }
            if (fullProductRepo.existColorProd("marron", iProd.getId_product())) {
                tabColor.add("marron");
            }
            if (fullProductRepo.existColorProd("rose", iProd.getId_product())) {
                tabColor.add("rose");
            }
            if (fullProductRepo.existColorProd("vert", iProd.getId_product())) {
                tabColor.add("vert");
            }
            if (fullProductRepo.existSizesProd("XS", iProd.getId_product())) {
                tabSize.add("XS");
            }
            if (fullProductRepo.existSizesProd("S", iProd.getId_product())) {
                tabSize.add("S");
            }
            if (fullProductRepo.existSizesProd("M", iProd.getId_product())) {
                tabSize.add("M");
            }
            if (fullProductRepo.existSizesProd("L", iProd.getId_product())) {
                tabSize.add("L");
            }
            if (fullProductRepo.existSizesProd("Xl", iProd.getId_product())) {
                tabSize.add("XL");
            }
            if (fullProductRepo.existSizesProd("36", iProd.getId_product())) {
                tabSize.add("36");
            }
            if (fullProductRepo.existSizesProd("37", iProd.getId_product())) {
                tabSize.add("37");
            }
            if (fullProductRepo.existSizesProd("38", iProd.getId_product())) {
                tabSize.add("38");
            }
            if (fullProductRepo.existSizesProd("39", iProd.getId_product())) {
                tabSize.add("39");
            }
            if (fullProductRepo.existSizesProd("40", iProd.getId_product())) {
                tabSize.add("40");
            }
            if (fullProductRepo.existSizesProd("41", iProd.getId_product())) {
                tabSize.add("42");
            }
            if (fullProductRepo.existSizesProd("43", iProd.getId_product())) {
                tabSize.add("43");
            }
            if (fullProductRepo.existSizesProd("28", iProd.getId_product())) {
                tabSize.add("28");
            }
            if (fullProductRepo.existSizesProd("28", iProd.getId_product())) {
                tabSize.add("28");
            }
            if (fullProductRepo.existSizesProd("29", iProd.getId_product())) {
                tabSize.add("29");
            }
            if (fullProductRepo.existSizesProd("26", iProd.getId_product())) {
                tabSize.add("26");
            }
            if (fullProductRepo.existSizesProd("25", iProd.getId_product())) {
                tabSize.add("25");
            }
            iProd.setColors(tabColor);
            iProd.setSizes(tabSize);
        }
        }
        //Top & T-shirt
        /*

            Product product= new Product();
            product.setName("T shirt 1");
            product.setCategorie("Top & T-shirt");
            product.setImg(R.drawable.ft81);
            product.setPrice(39734);
            product.setRef("zjhefj342P98");
            product.setTypeClient("Femmes");
            List <String> size= new ArrayList<>();
            size.add("S");
            size.add("M");
            size.add("L");
            size.add("XL");
            product.setSizes(size);
            List <String> color= new ArrayList<>();
            color.add("Noir");
            color.add("Blanc");
            color.add("Rose");
            color.add("Gris");
            product.setColors(color);
            List <Integer> im= new  ArrayList<>();
            im.add(R.drawable.ft81);
            im.add(R.drawable.ft82);
            im.add(R.drawable.ft83);


            product.setTab_img(im);
            productList.add(product);

            product= new Product();
            product.setName("T shirt 2");
            product.setCategorie("Top & T-shirt");
            product.setImg(R.drawable.ft1);
            product.setPrice(1200);
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
            im.add(R.drawable.ft1);
            im.add(R.drawable.ft2);
            product.setTab_img(im);
            productList.add(product);
/*
            product= new Product();
            product.setName("T shirt 3");
            product.setCategorie("Top & T-shirt");
            product.setImg(R.drawable.ft2);
            product.setPrice(1300);
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
            productList.add(product);

            product= new Product();
            product.setName("T shirt 5");
            product.setCategorie("Top & T-shirt");
            product.setImg(R.drawable.ic_et92);
            product.setPrice(1200);
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
            im.add(R.drawable.ft4);
            im.add(R.drawable.ft1);
            product.setTab_img(im);
            productList.add(product);

            product= new Product();
            product.setName("T shirt 6");
            product.setCategorie("Top & T-shirt");
            product.setImg(R.drawable.ic_tshirt1);
            product.setPrice(1500);
            product.setRef("hefj3sasP98");
            product.setTypeClient("Femmes");
            size= new ArrayList<>();
            size.add("S");
            size.add("L");
            product.setSizes(size);
            color= new ArrayList<>();
            color.add("Blanc");
            product.setColors(color);
            im= new  ArrayList<>();
            im.add(R.drawable.ft5);
            im.add(R.drawable.ft1);
            product.setTab_img(im);
            productList.add(product);

            product= new Product();
            product.setName("T shirt 7");
            product.setCategorie("Top & T-shirt");
            product.setImg(R.drawable.ic_tshirt1);
            product.setPrice(3200);
            product.setRef("hefj3sasP98");
            product.setTypeClient("Femmes");
            size= new ArrayList<>();
            size.add("M");
            product.setSizes(size);
            color= new ArrayList<>();
            color.add("noir");
            color.add("rouge");
            product.setColors(color);
            im= new  ArrayList<>();
            im.add(R.drawable.ft6);
            im.add(R.drawable.ft7);
            product.setTab_img(im);
            productList.add(product);

            product= new Product();
            product.setName("T shirt 8");
            product.setCategorie("Top & T-shirt");
            product.setImg(R.drawable.ic_tshirt1);
            product.setPrice(1200);
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
            im.add(R.drawable.ft7);
            im.add(R.drawable.ft7);
            product.setTab_img(im);
            productList.add(product);

            product= new Product();
            product.setName("T shirt 9");
            product.setCategorie("Top & T-shirt");
            product.setImg(R.drawable.ic_tshirt1);
            product.setPrice(9200);
            product.setRef("hefj3sasP98");
            product.setTypeClient("Femmes");
            size= new ArrayList<>();
            size.add("S");
            product.setSizes(size);
            color= new ArrayList<>();
            color.add("Blanc");
            product.setColors(color);
            im= new  ArrayList<>();
            im.add(R.drawable.ft9);
            im.add(R.drawable.ft9);
            product.setTab_img(im);
            productList.add(product);

            product= new Product();
            product.setName("T shirt 10");
            product.setCategorie("Top & T-shirt");
            product.setImg(R.drawable.ic_tshirt1);
            product.setPrice(1000);
            product.setRef("hefj3sasP98");
            product.setTypeClient("Femmes");
            size= new ArrayList<>();
            size.add("S");
            size.add("M");
            size.add("L");
            product.setSizes(size);
            color= new ArrayList<>();
            color.add("noir");
            color.add("rouge");
            color.add("jaune");
            product.setColors(color);
            im= new  ArrayList<>();
            im.add(R.drawable.ft10);
            im.add(R.drawable.ft81);
            im.add(R.drawable.ft82);
            product.setTab_img(im);
            productList.add(product);



            //Robes & Jupes

            product= new Product();
            product.setName("Robe 1");
            product.setCategorie("Robes & Jupes");
            product.setImg(R.drawable.ic_fr3);
            product.setPrice(39734);
            product.setRef("zjhefj342P98");
            product.setTypeClient("Femmes");
            size= new ArrayList<>();
            size.add("S");
            size.add("M");
            size.add("L");
            size.add("XL");
            product.setSizes(size);
            color= new ArrayList<>();
            color.add("Noir");
            color.add("Blanc");
            color.add("Rose");
            color.add("Gris");
            product.setColors(color);
            im= new  ArrayList<>();
            im.add(R.drawable.fr3);
            im.add(R.drawable.fr31);
            product.setTab_img(im);
            productList.add(product);

            product= new Product();
            product.setName("Robe 2");
            product.setCategorie("Robes & Jupes");
            product.setImg(R.drawable.ic_fr7);
            product.setPrice(39734);
            product.setRef("hefj3dsP98");
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
            im.add(R.drawable.fr1);
            im.add(R.drawable.fr2);
            product.setTab_img(im);
            productList.add(product);

            product= new Product();
            product.setName("Robe 3");
            product.setCategorie("Robes & Jupes");
            product.setImg(R.drawable.ic_fr3);
            product.setPrice(39734);
            product.setRef("hefj3dsP98");
            product.setTypeClient("Femmes");
            size= new ArrayList<>();
            size.add("S");
            size.add("M");
            size.add("L");
            size.add("XL");
            product.setSizes(size);
            color= new ArrayList<>();
            color.add("bleu");
            color.add("Blanc");
            color.add("jaune");
            product.setColors(color);
            im= new  ArrayList<>();
            im.add(R.drawable.fr2);
            im.add(R.drawable.fr4);
            product.setTab_img(im);
            productList.add(product);

            product= new Product();
            product.setName("Robe 4");
            product.setCategorie("Robes & Jupes");
            product.setImg(R.drawable.ic_fr7);
            product.setPrice(39734);
            product.setRef("hefj3dsP98");
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
            im.add(R.drawable.fr4);
            im.add(R.drawable.fr3);
            im.add(R.drawable.fr5);
            product.setTab_img(im);
            productList.add(product);

            product= new Product();
            product.setName("Robe 4");
            product.setCategorie("Robes & Jupes");
            product.setImg(R.drawable.ic_fr3);
            product.setPrice(39734);
            product.setRef("hefj3dsP98");
            product.setTypeClient("Femmes");
            size= new ArrayList<>();
            size.add("S");
            size.add("M");
            size.add("L");
            product.setSizes(size);
            color= new ArrayList<>();
            color.add("bleu");
            color.add("jaune");
            product.setColors(color);
            im= new  ArrayList<>();
            im.add(R.drawable.fr5);
            im.add(R.drawable.fr1);
            product.setTab_img(im);
            productList.add(product);





            //Pantalons & Jeans

            product= new Product();
            product.setName("Pantalon 1");
            product.setCategorie("Pantalons & Jeans");
            product.setImg(R.drawable.ic_fp2);
            product.setPrice(39734);
            product.setRef("zjhefj342P98");
            product.setTypeClient("Femmes");
            size= new ArrayList<>();
            size.add("XS");
            size.add("S");
            size.add("M");
            size.add("L");
            size.add("XL");
            product.setSizes(size);
            color= new ArrayList<>();
            color.add("Noir");
            color.add("Blanc");
            color.add("Rouge");
            color.add("Gris");
            product.setColors(color);
            im= new  ArrayList<>();
            im.add(R.drawable.fp1);
            im.add(R.drawable.fp2);
            im.add(R.drawable.fp3);
            product.setTab_img(im);
            productList.add(product);

            product= new Product();
            product.setName("Pantalon 2");
            product.setCategorie("Pantalons & Jeans");
            product.setImg(R.drawable.ic_fp2);
            product.setPrice(39734);
            product.setRef("hefj3dsP98");
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
            im.add(R.drawable.fp2);
            im.add(R.drawable.fp1);
            im.add(R.drawable.fp3);
            im.add(R.drawable.fp4);

            product.setTab_img(im);
            productList.add(product);

            product= new Product();
            product.setName("Pantalon 4");
            product.setCategorie("Pantalons & Jeans");
            product.setImg(R.drawable.ic_fp2);
            product.setPrice(39734);
            product.setRef("hefj3dsP98");
            product.setTypeClient("Femmes");
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
            im.add(R.drawable.fp4);
            im.add(R.drawable.fp5);

            product.setTab_img(im);
            productList.add(product);

            product= new Product();
            product.setName("Pantalon 5");
            product.setCategorie("Pantalons & Jeans");
            product.setImg(R.drawable.ic_fp2);
            product.setPrice(39734);
            product.setRef("hefj3dsP98");
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
            im.add(R.drawable.fp5);
            im.add(R.drawable.fp4);
            product.setTab_img(im);
            productList.add(product);







            //Accessoires



            product= new Product();
            product.setName("Accessoire 1");
            product.setCategorie("Accessoires");
            product.setImg(R.drawable.fa1);
            product.setPrice(39734);
            product.setRef("hefj3dsP98");
            product.setTypeClient("Femmes");
            size= new ArrayList<>();

            product.setSizes(size);
            color= new ArrayList<>();
            color.add("bleu");
            color.add("Blanc");
            product.setColors(color);
            im= new  ArrayList<>();
            im.add(R.drawable.fa1);
            im.add(R.drawable.fa2);
            im.add(R.drawable.fa3);
            product.setTab_img(im);
            productList.add(product);

            product= new Product();
            product.setName("Accessoire 2");
            product.setCategorie("Accessoires");
            product.setImg(R.drawable.fa4);
            product.setPrice(39734);
            product.setRef("hefj3dsP98");
            product.setTypeClient("Femmes");
            size= new ArrayList<>();

            product.setSizes(size);
            color= new ArrayList<>();
            color.add("bleu");
            color.add("Blanc");
            color.add("jaune");
            color.add("vert");
            product.setColors(color);
            im= new  ArrayList<>();
            im.add(R.drawable.fa4);
            im.add(R.drawable.fa5);
            product.setTab_img(im);
            productList.add(product);


            product= new Product();
            product.setName("Accessoire 3");
            product.setCategorie("Accessoires");
            product.setImg(R.drawable.fa4);
            product.setPrice(39734);
            product.setRef("hefj3dsP98");
            product.setTypeClient("Femmes");
            size= new ArrayList<>();

            product.setSizes(size);
            color= new ArrayList<>();
            color.add("bleu");
            color.add("Blanc");
            product.setColors(color);
            im= new  ArrayList<>();
            im.add(R.drawable.fa4);
            im.add(R.drawable.fa5);
            im.add(R.drawable.fa6);
            product.setTab_img(im);
            productList.add(product);

            product= new Product();
            product.setName("Accessoire 4");
            product.setCategorie("Accessoires");
            product.setImg(R.drawable.fa7);
            product.setPrice(39734);
            product.setRef("hefj3dsP98");
            product.setTypeClient("Femmes");
            size= new ArrayList<>();
            size.add("S");
            size.add("M");
            size.add("L");
            size.add("XL");
            product.setSizes(size);
            color= new ArrayList<>();
            color.add("bleu");
            color.add("Blanc");
            color.add("jaune");
            product.setColors(color);
            im= new  ArrayList<>();
            im.add(R.drawable.fa7);
            im.add(R.drawable.fa8);
            product.setTab_img(im);
            productList.add(product);

            product= new Product();
            product.setName("Accessoire 5");
            product.setCategorie("Accessoires");
            product.setImg(R.drawable.fa9);
            product.setPrice(39734);
            product.setRef("hefj3dsP98");
            product.setTypeClient("Femmes");
            size= new ArrayList<>();

            product.setSizes(size);
            color= new ArrayList<>();
            color.add("rose");
            color.add("Blanc");
            product.setColors(color);
            im= new  ArrayList<>();
            im.add(R.drawable.fa9);
            im.add(R.drawable.fa10);
            product.setTab_img(im);
            productList.add(product);



            //Chaussures


            product= new Product();
            product.setName("Chaussure 1");
            product.setCategorie("Chaussures");
            product.setImg(R.drawable.ic_fc2);
            product.setPrice(39734);
            product.setRef("hefj3dsP98");
            product.setTypeClient("Femmes");
            size= new ArrayList<>();
            size.add("36");
            size.add("37");
            size.add("38");
            product.setSizes(size);
            color= new ArrayList<>();
            color.add("bleu");
            color.add("Blanc");
            product.setColors(color);
            im= new  ArrayList<>();
            im.add(R.drawable.fc1);
            im.add(R.drawable.fc1);
            im.add(R.drawable.fc3);
            product.setTab_img(im);
            productList.add(product);

            product= new Product();
            product.setName("Chaussure 2");
            product.setCategorie("Chaussures");
            product.setImg(R.drawable.ic_fc2);
            product.setPrice(39734);
            product.setRef("hefj3dsP98");
            product.setTypeClient("Femmes");
            size= new ArrayList<>();
            size.add("37");
            size.add("38");

            product.setSizes(size);
            color= new ArrayList<>();
            color.add("bleu");
            color.add("Blanc");
            color.add("noir");
            product.setColors(color);
            im= new  ArrayList<>();
            im.add(R.drawable.fc2);
            im.add(R.drawable.fc3);

            product.setTab_img(im);
            productList.add(product);


            product= new Product();
            product.setName("Chaussure 3");
            product.setCategorie("Chaussures");
            product.setImg(R.drawable.ic_fc2);
            product.setPrice(39734);
            product.setRef("hefj3dsP98");
            product.setTypeClient("Femmes");
            size= new ArrayList<>();
            size.add("37");
            size.add("38");

            product.setSizes(size);
            color= new ArrayList<>();
            color.add("bleu");
            color.add("Blanc");
            color.add("noir");
            product.setColors(color);
            im= new  ArrayList<>();
            im.add(R.drawable.fc3);
            im.add(R.drawable.fc3);

            product.setTab_img(im);
            productList.add(product);


            product= new Product();
            product.setName("Chaussure 4");
            product.setCategorie("Chaussures");
            product.setImg(R.drawable.ic_fc2);
            product.setPrice(39734);
            product.setRef("hefj3dsP98");
            product.setTypeClient("Femmes");
            size= new ArrayList<>();
            size.add("37");
            size.add("38");

            product.setSizes(size);
            color= new ArrayList<>();
            color.add("bleu");
            color.add("Blanc");
            color.add("noir");
            product.setColors(color);
            im= new  ArrayList<>();
            im.add(R.drawable.fc2);
            im.add(R.drawable.fc3);

            product.setTab_img(im);
            productList.add(product);

            product= new Product();
            product.setName("Chaussure 5");
            product.setCategorie("Chaussures");
            product.setImg(R.drawable.ic_fc2);
            product.setPrice(39734);
            product.setRef("hefj3dsP98");
            product.setTypeClient("Femmes");
            size= new ArrayList<>();
            size.add("37");
            size.add("38");

            product.setSizes(size);
            color= new ArrayList<>();
            color.add("bleu");
            color.add("Blanc");
            color.add("noir");
            product.setColors(color);
            im= new  ArrayList<>();
            im.add(R.drawable.fc2);
            im.add(R.drawable.fc3);

            product.setTab_img(im);
            productList.add(product);
        }

        // Type Client =  Hommes
        */

        //T-shirt
            /*
            Product product= new Product();
            product.setName("T shirt 1");
            product.setCategorie("Top & T-shirt");
            product.setImg(R.drawable.ic_et92);
            product.setPrice(39734);
            product.setRef("zjhefj342P98");
            product.setTypeClient("Hommes");
            List <String> size= new ArrayList<>();
            size.add("S");
            size.add("M");
            size.add("L");
            size.add("XL");
            product.setSizes(size);
            List <String> color= new ArrayList<>();
            color.add("Noir");
            color.add("Blanc");
            color.add("Rose");
            color.add("Gris");
            product.setColors(color);
            List <Integer> im= new  ArrayList<>();
            im.add(R.drawable.ht1);
            im.add(R.drawable.ht11);


            product.setTab_img(im);
            productList.add(product);

            product= new Product();
            product.setName("T shirt 2");
            product.setCategorie("Top & T-shirt");
            product.setImg(R.drawable.ic_et92);
            product.setPrice(1200);
            product.setRef("hefj3sasP98");
            product.setTypeClient("Hommes");
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
            im.add(R.drawable.ht1);
            im.add(R.drawable.ht2);
            product.setTab_img(im);
            productList.add(product);

            product= new Product();
            product.setName("T shirt 3");
            product.setCategorie("Top & T-shirt");
            product.setImg(R.drawable.ic_et92);
            product.setPrice(1300);
            product.setRef("hefj3sasP98");
            product.setTypeClient("Hommes");
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
            im.add(R.drawable.ht2);
            im.add(R.drawable.ht3);
            product.setTab_img(im);
            productList.add(product);

            product= new Product();
            product.setName("T shirt 4");
            product.setCategorie("Top & T-shirt");
            product.setImg(R.drawable.ic_et92);
            product.setPrice(1400);
            product.setRef("hefj3sasP98");
            product.setTypeClient("Hommes");
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
            im.add(R.drawable.ht3);
            im.add(R.drawable.ht4);
            im.add(R.drawable.ht5);
            product.setTab_img(im);
            productList.add(product);

            product= new Product();
            product.setName("T shirt 5");
            product.setCategorie("Top & T-shirt");
            product.setImg(R.drawable.ic_et92);
            product.setPrice(1200);
            product.setRef("hefj3sasP98");
            product.setTypeClient("Hommes");
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
            im.add(R.drawable.ht4);
            im.add(R.drawable.ht1);
            product.setTab_img(im);
            productList.add(product);

            product= new Product();
            product.setName("T shirt 6");
            product.setCategorie("Top & T-shirt");
            product.setImg(R.drawable.ic_tshirt2);
            product.setPrice(1500);
            product.setRef("hefj3sasP98");
            product.setTypeClient("Hommes");
            size= new ArrayList<>();
            size.add("S");
            size.add("L");
            product.setSizes(size);
            color= new ArrayList<>();
            color.add("Blanc");
            product.setColors(color);
            im= new  ArrayList<>();
            im.add(R.drawable.ht5);
            im.add(R.drawable.ht1);
            product.setTab_img(im);
            productList.add(product);

            product= new Product();
            product.setName("T shirt 7");
            product.setCategorie("Top & T-shirt");
            product.setImg(R.drawable.ic_tshirt2);
            product.setPrice(3200);
            product.setRef("hefj3sasP98");
            product.setTypeClient("Hommes");
            size= new ArrayList<>();
            size.add("M");
            product.setSizes(size);
            color= new ArrayList<>();
            color.add("noir");
            color.add("rouge");
            product.setColors(color);
            im= new  ArrayList<>();
            im.add(R.drawable.ht6);
            im.add(R.drawable.ht7);
            product.setTab_img(im);
            productList.add(product);

            product= new Product();
            product.setName("T shirt 8");
            product.setCategorie("Top & T-shirt");
            product.setImg(R.drawable.ic_tshirt1);
            product.setPrice(1200);
            product.setRef("hefj3sasP98");
            product.setTypeClient("Hommes");
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
            im.add(R.drawable.ht7);
            im.add(R.drawable.ht7);
            product.setTab_img(im);
            productList.add(product);

            product= new Product();
            product.setName("T shirt 9");
            product.setCategorie("Top & T-shirt");
            product.setImg(R.drawable.ic_et92);

            product.setPrice(9200);
            product.setRef("hefj3sasP98");
            product.setTypeClient("Hommes");
            size= new ArrayList<>();
            size.add("S");
            product.setSizes(size);
            color= new ArrayList<>();
            color.add("Blanc");
            product.setColors(color);
            im= new  ArrayList<>();
            im.add(R.drawable.ht9);
            im.add(R.drawable.ht9);
            product.setTab_img(im);
            productList.add(product);

            product= new Product();
            product.setName("T shirt 10");
            product.setCategorie("Top & T-shirt");
            product.setImg(R.drawable.ic_et92);
            product.setPrice(1000);
            product.setRef("hefj3sasP98");
            product.setTypeClient("Hommes");
            size= new ArrayList<>();
            size.add("S");
            size.add("M");
            size.add("L");
            product.setSizes(size);
            color= new ArrayList<>();
            color.add("noir");
            color.add("rouge");
            color.add("jaune");
            product.setColors(color);
            im= new  ArrayList<>();
            im.add(R.drawable.ht10);
            im.add(R.drawable.ht9);
            im.add(R.drawable.ht8);
            product.setTab_img(im);
            productList.add(product);



            //Pantalon et short

            product= new Product();
            product.setName("Pantalon 1");
            product.setCategorie("Shorts & Pantalons");
            product.setImg(R.drawable.ic_fp2);
            product.setPrice(39734);
            product.setRef("zjhefj342P98");
            product.setTypeClient("Hommes");
            size= new ArrayList<>();
            size.add("XS");
            size.add("S");
            size.add("M");
            size.add("L");
            size.add("XL");
            product.setSizes(size);
            color= new ArrayList<>();
            color.add("Noir");
            color.add("Blanc");
            color.add("Rouge");
            color.add("Gris");
            product.setColors(color);
            im= new  ArrayList<>();
            im.add(R.drawable.hp1);
            im.add(R.drawable.hp2);
            im.add(R.drawable.hp3);
            product.setTab_img(im);
            productList.add(product);

            product= new Product();
            product.setName("Pantalon 2");
            product.setCategorie("Shorts & Pantalons");
            product.setImg(R.drawable.ic_fp2);
            product.setPrice(39734);
            product.setRef("hefj3dsP98");
            product.setTypeClient("Hommes");
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
            im.add(R.drawable.hp2);
            im.add(R.drawable.hp1);
            im.add(R.drawable.hp3);
            im.add(R.drawable.hp4);

            product.setTab_img(im);
            productList.add(product);

            product= new Product();
            product.setName("Pantalon 4");
            product.setCategorie("Shorts & Pantalons");
            product.setImg(R.drawable.ic_fp2);
            product.setPrice(39734);
            product.setRef("hefj3dsP98");
            product.setTypeClient("Hommes");
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
            im.add(R.drawable.hp4);
            im.add(R.drawable.hp5);

            product.setTab_img(im);
            productList.add(product);

            product= new Product();
            product.setName("Pantalon 5");
            product.setCategorie("Shorts & Pantalons");
            product.setImg(R.drawable.ic_fp2);
            product.setPrice(39734);
            product.setRef("hefj3dsP98");
            product.setTypeClient("Hommes");
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
            im.add(R.drawable.hp5);
            im.add(R.drawable.hp4);
            product.setTab_img(im);
            productList.add(product);




            //Costumes

            product= new Product();
            product.setName("Costume 1");
            product.setCategorie("Costume");
            product.setImg(R.drawable.ic_fp2);
            product.setPrice(39734);
            product.setRef("zjhefj342P98");
            product.setTypeClient("Hommes");
            size= new ArrayList<>();
            size.add("XS");
            size.add("S");
            size.add("M");
            size.add("L");
            size.add("XL");
            product.setSizes(size);
            color= new ArrayList<>();
            color.add("Noir");
            color.add("Blanc");
            color.add("Rouge");
            color.add("Gris");
            product.setColors(color);
            im= new  ArrayList<>();
            im.add(R.drawable.hco1);
            im.add(R.drawable.hco2);
            im.add(R.drawable.hco3);
            product.setTab_img(im);
            productList.add(product);

            product= new Product();
            product.setName("Costume 2");
            product.setCategorie("Costume");
            product.setImg(R.drawable.ic_fp2);
            product.setPrice(39734);
            product.setRef("hefj3dsP98");
            product.setTypeClient("Hommes");
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
            im.add(R.drawable.hco2);
            im.add(R.drawable.hco1);
            im.add(R.drawable.hco3);
            im.add(R.drawable.hco4);

            product.setTab_img(im);
            productList.add(product);

            product= new Product();
            product.setName("Costume 4");
            product.setCategorie("Costume");
            product.setImg(R.drawable.ic_fp2);
            product.setPrice(39734);
            product.setRef("hefj3dsP98");
            product.setTypeClient("Hommes");
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
            im.add(R.drawable.hco4);
            im.add(R.drawable.hco5);

            product.setTab_img(im);
            productList.add(product);

            product= new Product();
            product.setName("Costume 5");
            product.setCategorie("Costume");
            product.setImg(R.drawable.ic_fp2);
            product.setPrice(39734);
            product.setRef("hefj3dsP98");
            product.setTypeClient("Hommes");
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
            im.add(R.drawable.hco5);
            im.add(R.drawable.hco4);
            product.setTab_img(im);
            productList.add(product);

            product= new Product();
            product.setName("Costume 6");
            product.setCategorie("Costume");
            product.setImg(R.drawable.ic_fp2);
            product.setPrice(39734);
            product.setRef("hefj3dsP98");
            product.setTypeClient("Hommes");
            size= new ArrayList<>();
            size.add("S");
            size.add("M");
            size.add("L");
            product.setSizes(size);
            color= new ArrayList<>();
            color.add("Noir");
            color.add("Beige");
            product.setColors(color);
            im= new  ArrayList<>();
            im.add(R.drawable.hco6);
            im.add(R.drawable.fr7);
            product.setTab_img(im);
            productList.add(product);




            //Vetement Sport


            product= new Product();
            product.setName("Vetements de sport 1");
            product.setCategorie("Vetements de sport");
            product.setImg(R.drawable.ic_fc2);
            product.setPrice(39734);
            product.setRef("zjhefj342P98");
            product.setTypeClient("Hommes");
            size= new ArrayList<>();
            size.add("XS");
            size.add("S");
            size.add("M");
            size.add("L");
            size.add("XL");
            product.setSizes(size);
            color= new ArrayList<>();
            color.add("Noir");
            color.add("Blanc");
            color.add("Rouge");
            color.add("Gris");
            product.setColors(color);
            im= new  ArrayList<>();
            im.add(R.drawable.hsv1);
            im.add(R.drawable.hsv2);
            im.add(R.drawable.hsv3);
            product.setTab_img(im);
            productList.add(product);

            product= new Product();
            product.setName("Vetements de sport 2");
            product.setCategorie("Vetements de sport");
            product.setImg(R.drawable.ic_fc2);
            product.setPrice(39734);
            product.setRef("hefj3dsP98");
            product.setTypeClient("Hommes");
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
            im.add(R.drawable.hsv2);
            im.add(R.drawable.hsv1);
            im.add(R.drawable.hsv3);
            im.add(R.drawable.hsv4);

            product.setTab_img(im);
            productList.add(product);

            product= new Product();
            product.setName("Vetements de sport 4");
            product.setCategorie("Vetements de sport");
            product.setImg(R.drawable.ic_fc2);
            product.setPrice(39734);
            product.setRef("hefj3dsP98");
            product.setTypeClient("Hommes");
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
            im.add(R.drawable.hsv4);
            im.add(R.drawable.hsv5);

            product.setTab_img(im);
            productList.add(product);

            product= new Product();
            product.setName("Vetements de sport 5");
            product.setCategorie("Vetements de sport");
            product.setImg(R.drawable.ic_fc2);
            product.setPrice(39734);
            product.setRef("hefj3dsP98");
            product.setTypeClient("Hommes");
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
            im.add(R.drawable.hsv5);
            im.add(R.drawable.hsv4);
            product.setTab_img(im);
            productList.add(product);

            product= new Product();
            product.setName("Vetements de sport 6");
            product.setCategorie("Vetements de sport");
            product.setImg(R.drawable.ic_fc2);
            product.setPrice(39734);
            product.setRef("hefj3dsP98");
            product.setTypeClient("Hommes");
            size= new ArrayList<>();
            size.add("S");
            size.add("M");
            size.add("L");
            product.setSizes(size);
            color= new ArrayList<>();
            color.add("Noir");
            color.add("Beige");
            product.setColors(color);
            im= new  ArrayList<>();
            im.add(R.drawable.hsv6);
            im.add(R.drawable.fr7);
            product.setTab_img(im);
            productList.add(product);





            //Chaussures


            product= new Product();
            product.setName("Chaussure 1");
            product.setCategorie("Chaussures");
            product.setImg(R.drawable.ic_fc2);
            product.setPrice(39734);
            product.setRef("hefj3dsP98");
            product.setTypeClient("Hommes");
            size= new ArrayList<>();
            size.add("36");
            size.add("37");
            size.add("38");
            product.setSizes(size);
            color= new ArrayList<>();
            color.add("bleu");
            color.add("Blanc");
            product.setColors(color);
            im= new  ArrayList<>();
            im.add(R.drawable.hc1);
            im.add(R.drawable.hc1);
            im.add(R.drawable.hc3);
            product.setTab_img(im);
            productList.add(product);

            product= new Product();
            product.setName("Chaussure 2");
            product.setCategorie("Chaussures");
            product.setImg(R.drawable.ic_fc2);
            product.setPrice(39734);
            product.setRef("hefj3dsP98");
            product.setTypeClient("Hommes");
            size= new ArrayList<>();
            size.add("37");
            size.add("38");

            product.setSizes(size);
            color= new ArrayList<>();
            color.add("bleu");
            color.add("Blanc");
            color.add("noir");
            product.setColors(color);
            im= new  ArrayList<>();
            im.add(R.drawable.hc2);
            im.add(R.drawable.hc3);

            product.setTab_img(im);
            productList.add(product);


            product= new Product();
            product.setName("Chaussure 3");
            product.setCategorie("Chaussures");
            product.setImg(R.drawable.ic_fc2);
            product.setPrice(39734);
            product.setRef("hefj3dsP98");
            product.setTypeClient("Hommes");
            size= new ArrayList<>();
            size.add("37");
            size.add("38");

            product.setSizes(size);
            color= new ArrayList<>();
            color.add("bleu");
            color.add("Blanc");
            color.add("noir");
            product.setColors(color);
            im= new  ArrayList<>();
            im.add(R.drawable.hc3);
            im.add(R.drawable.hc3);

            product.setTab_img(im);
            productList.add(product);


            product= new Product();
            product.setName("Chaussure 4");
            product.setCategorie("Chaussures");
            product.setImg(R.drawable.ic_fc2);
            product.setPrice(39734);
            product.setRef("hefj3dsP98");
            product.setTypeClient("Hommes");
            size= new ArrayList<>();
            size.add("37");
            size.add("38");

            product.setSizes(size);
            color= new ArrayList<>();
            color.add("bleu");
            color.add("Blanc");
            color.add("noir");
            product.setColors(color);
            im= new  ArrayList<>();
            im.add(R.drawable.hc2);
            im.add(R.drawable.hc3);

            product.setTab_img(im);
            productList.add(product);

            product= new Product();
            product.setName("Chaussure 5");
            product.setCategorie("Chaussures");
            product.setImg(R.drawable.ic_fc2);
            product.setPrice(39734);
            product.setRef("hefj3dsP98");
            product.setTypeClient("Hommes");
            size= new ArrayList<>();
            size.add("37");
            size.add("38");

            product.setSizes(size);
            color= new ArrayList<>();
            color.add("bleu");
            color.add("Blanc");
            color.add("noir");
            product.setColors(color);
            im= new  ArrayList<>();
            im.add(R.drawable.hc2);
            im.add(R.drawable.hc3);

            product.setTab_img(im);
            productList.add(product);
        }

        // Type Client =  Enfants

        if (Product.categorie_current.compareTo("Enfants")==0)
        {

        }*/
        //T-shirt
             /*
            Product product= new Product();
            product.setName("T shirt 1");
            product.setCategorie("Top & T-shirt");
            product.setImg(R.drawable.ic_et92);
            product.setPrice(39734);
            product.setRef("zjhefj342P98");
            product.setTypeClient("Enfants");
            List <String> size= new ArrayList<>();
            size.add("S");
            size.add("M");
            size.add("L");
            size.add("XL");
            product.setSizes(size);
            List <String> color= new ArrayList<>();
            color.add("Noir");
            color.add("Blanc");
            color.add("Rose");
            color.add("Gris");
            product.setColors(color);
            List <Integer> im= new  ArrayList<>();
            im.add(R.drawable.et91);
            im.add(R.drawable.et92);


            product.setTab_img(im);
            productList.add(product);

            product= new Product();
            product.setName("T shirt 2");
            product.setCategorie("Top & T-shirt");
            product.setImg(R.drawable.ic_et1);
            product.setPrice(1200);
            product.setRef("hefj3sasP98");
            product.setTypeClient("Enfants");
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
            im.add(R.drawable.et1);
            im.add(R.drawable.et2);
            product.setTab_img(im);
            productList.add(product);

            product= new Product();
            product.setName("T shirt 3");
            product.setCategorie("Top & T-shirt");
            product.setImg(R.drawable.ic_et1);
            product.setPrice(1300);
            product.setRef("hefj3sasP98");
            product.setTypeClient("Enfants");
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
            im.add(R.drawable.et2);
            im.add(R.drawable.et3);
            product.setTab_img(im);
            productList.add(product);

            product= new Product();
            product.setName("T shirt 4");
            product.setCategorie("Top & T-shirt");
            product.setImg(R.drawable.ic_et1);
            product.setPrice(1400);
            product.setRef("hefj3sasP98");
            product.setTypeClient("Enfants");
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
            im.add(R.drawable.et3);
            im.add(R.drawable.et4);
            im.add(R.drawable.et5);
            product.setTab_img(im);
            productList.add(product);

            product= new Product();
            product.setName("T shirt 5");
            product.setCategorie("Top & T-shirt");
            product.setImg(R.drawable.ic_et1);
            product.setPrice(1200);
            product.setRef("hefj3sasP98");
            product.setTypeClient("Enfants");
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
            im.add(R.drawable.et4);
            im.add(R.drawable.et1);
            product.setTab_img(im);
            productList.add(product);

            product= new Product();
            product.setName("T shirt 6");
            product.setCategorie("Top & T-shirt");
            product.setImg(R.drawable.ic_et1);
            product.setPrice(1500);
            product.setRef("hefj3sasP98");
            product.setTypeClient("Enfants");
            size= new ArrayList<>();
            size.add("S");
            size.add("L");
            product.setSizes(size);
            color= new ArrayList<>();
            color.add("Blanc");
            product.setColors(color);
            im= new  ArrayList<>();
            im.add(R.drawable.et5);
            im.add(R.drawable.et1);
            product.setTab_img(im);
            productList.add(product);


            //Pantolons
            product= new Product();
            product.setName("Pantalon 1");
            product.setCategorie("Shorts & Pantalons");
            product.setImg(R.drawable.ic_fp2);
            product.setPrice(39734);
            product.setRef("zjhefj342P98");
            product.setTypeClient("Enfants");
            size= new ArrayList<>();
            size.add("XS");
            size.add("S");
            size.add("M");
            size.add("L");
            size.add("XL");
            product.setSizes(size);
            color= new ArrayList<>();
            color.add("Noir");
            color.add("Blanc");
            color.add("Rouge");
            color.add("Gris");
            product.setColors(color);
            im= new  ArrayList<>();
            im.add(R.drawable.ep1);
            im.add(R.drawable.ep2);
            im.add(R.drawable.ep3);
            product.setTab_img(im);
            productList.add(product);

            product= new Product();
            product.setName("Pantalon 2");
            product.setCategorie("Shorts & Pantalons");
            product.setImg(R.drawable.ic_fp2);
            product.setPrice(39734);
            product.setRef("hefj3dsP98");
            product.setTypeClient("Enfants");
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
            im.add(R.drawable.ep2);
            im.add(R.drawable.ep1);
            im.add(R.drawable.ep3);
            im.add(R.drawable.ep4);

            product.setTab_img(im);
            productList.add(product);

            product= new Product();
            product.setName("Pantalon 4");
            product.setCategorie("Shorts & Pantalons");
            product.setImg(R.drawable.ic_fp2);
            product.setPrice(39734);
            product.setRef("hefj3dsP98");
            product.setTypeClient("Enfants");
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
            im.add(R.drawable.ep4);
            im.add(R.drawable.ep5);

            product.setTab_img(im);
            productList.add(product);

            product= new Product();
            product.setName("Pantalon 5");
            product.setCategorie("Shorts & Pantalons");
            product.setImg(R.drawable.ic_fp2);
            product.setPrice(39734);
            product.setRef("hefj3dsP98");
            product.setTypeClient("Enfants");
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
            im.add(R.drawable.ep5);
            im.add(R.drawable.ep4);
            product.setTab_img(im);
            productList.add(product);

            product= new Product();
            product.setName("Pantalon 6");
            product.setCategorie("Shorts & Pantalons");
            product.setImg(R.drawable.ic_fp2);
            product.setPrice(39734);
            product.setRef("hefj3dsP98");
            product.setTypeClient("Enfants");
            size= new ArrayList<>();
            size.add("S");
            size.add("M");
            size.add("L");
            product.setSizes(size);
            color= new ArrayList<>();
            color.add("Noir");
            color.add("Beige");
            product.setColors(color);
            im= new  ArrayList<>();
            im.add(R.drawable.ep6);
            im.add(R.drawable.fr7);
            product.setTab_img(im);
            productList.add(product);



            //Pulls et gilets


            product= new Product();
            product.setName("Pulls et gilets 1");
            product.setCategorie("Pulls et gilets");
            product.setImg(R.drawable.ic_et4);
            product.setPrice(39734);
            product.setRef("zjhefj342P98");
            product.setTypeClient("Enfants");
            size= new ArrayList<>();
            size.add("XS");
            size.add("S");
            size.add("M");
            size.add("L");
            size.add("XL");
            product.setSizes(size);
            color= new ArrayList<>();
            color.add("Noir");
            color.add("Blanc");
            color.add("Rouge");
            color.add("Gris");
            product.setColors(color);
            im= new  ArrayList<>();
            im.add(R.drawable.eg1);
            im.add(R.drawable.eg2);
            im.add(R.drawable.eg3);
            product.setTab_img(im);
            productList.add(product);

            product= new Product();
            product.setName("Pulls et gilets 2");
            product.setCategorie("Pulls et gilets");
            product.setImg(R.drawable.ic_et4);
            product.setPrice(39734);
            product.setRef("hefj3dsP98");
            product.setTypeClient("Enfants");
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
            im.add(R.drawable.eg2);
            im.add(R.drawable.eg1);
            im.add(R.drawable.eg3);
            im.add(R.drawable.eg4);

            product.setTab_img(im);
            productList.add(product);

            product= new Product();
            product.setName("Pulls et gilets 4");
            product.setCategorie("Pulls et gilets");
            product.setImg(R.drawable.ic_et4);
            product.setPrice(39734);
            product.setRef("hefj3dsP98");
            product.setTypeClient("Enfants");
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
            im.add(R.drawable.eg4);
            im.add(R.drawable.eg5);

            product.setTab_img(im);
            productList.add(product);

            product= new Product();
            product.setName("Pulls et gilets 5");
            product.setCategorie("Pulls et gilets");
            product.setImg(R.drawable.ic_et4);
            product.setPrice(39734);
            product.setRef("hefj3dsP98");
            product.setTypeClient("Enfants");
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
            im.add(R.drawable.eg5);
            im.add(R.drawable.eg4);
            product.setTab_img(im);
            productList.add(product);

            product= new Product();
            product.setName("Pulls et gilets 6");
            product.setCategorie("Pulls et gilets");
            product.setImg(R.drawable.ic_et4);
            product.setPrice(39734);
            product.setRef("hefj3dsP98");
            product.setTypeClient("Enfants");
            size= new ArrayList<>();
            size.add("S");
            size.add("M");
            size.add("L");
            product.setSizes(size);
            color= new ArrayList<>();
            color.add("Noir");
            color.add("Beige");
            product.setColors(color);
            im= new  ArrayList<>();
            im.add(R.drawable.eg6);
            im.add(R.drawable.fr7);
            product.setTab_img(im);
            productList.add(product);


            //Spoort


            product= new Product();
            product.setName("Vetements de sport 1");
            product.setCategorie("Vetements de sport");
            product.setImg(R.drawable.ic_hc9);
            product.setPrice(39734);
            product.setRef("zjhefj342P98");
            product.setTypeClient("Enfants");
            size= new ArrayList<>();
            size.add("XS");
            size.add("S");
            size.add("M");
            size.add("L");
            size.add("XL");
            product.setSizes(size);
            color= new ArrayList<>();
            color.add("Noir");
            color.add("Blanc");
            color.add("Rouge");
            color.add("Gris");
            product.setColors(color);
            im= new  ArrayList<>();
            im.add(R.drawable.es1);
            im.add(R.drawable.es2);
            im.add(R.drawable.es3);
            product.setTab_img(im);
            productList.add(product);

            product= new Product();
            product.setName("Vetements de sport 2");
            product.setCategorie("Vetements de sport");
            product.setImg(R.drawable.ic_hc9);
            product.setPrice(39734);
            product.setRef("hefj3dsP98");
            product.setTypeClient("Enfants");
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
            im.add(R.drawable.es2);
            im.add(R.drawable.es1);
            im.add(R.drawable.es3);
            im.add(R.drawable.es4);

            product.setTab_img(im);
            productList.add(product);

            product= new Product();
            product.setName("Vetements de sport 4");
            product.setCategorie("Vetements de sport");
            product.setImg(R.drawable.ic_hc9);
            product.setPrice(39734);
            product.setRef("hefj3dsP98");
            product.setTypeClient("Enfants");
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
            im.add(R.drawable.es4);
            im.add(R.drawable.es5);

            product.setTab_img(im);
            productList.add(product);

            product= new Product();
            product.setName("Vetements de sport 5");
            product.setCategorie("Vetements de sport");
            product.setImg(R.drawable.ic_hc9);
            product.setPrice(39734);
            product.setRef("hefj3dsP98");
            product.setTypeClient("Enfants");
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
            im.add(R.drawable.es5);
            im.add(R.drawable.es4);
            product.setTab_img(im);
            productList.add(product);

            product= new Product();
            product.setName("Vetements de sport 6");
            product.setCategorie("Vetements de sport");
            product.setImg(R.drawable.ic_hc9);
            product.setPrice(39734);
            product.setRef("hefj3dsP98");
            product.setTypeClient("Enfants");
            size= new ArrayList<>();
            size.add("S");
            size.add("M");
            size.add("L");
            product.setSizes(size);
            color= new ArrayList<>();
            color.add("Noir");
            color.add("Beige");
            product.setColors(color);
            im= new  ArrayList<>();
            im.add(R.drawable.es6);
            im.add(R.drawable.fr7);
            product.setTab_img(im);
            productList.add(product);



            //Chaussures

            product= new Product();
            product.setName("Chaussure 1");
            product.setCategorie("Chaussures");
            product.setImg(R.drawable.ic_ec6);
            product.setPrice(39734);
            product.setRef("hefj3dsP98");
            product.setTypeClient("Enfants");
            size= new ArrayList<>();
            size.add("36");
            size.add("37");
            size.add("38");
            product.setSizes(size);
            color= new ArrayList<>();
            color.add("bleu");
            color.add("Blanc");
            product.setColors(color);
            im= new  ArrayList<>();
            im.add(R.drawable.ec1);
            im.add(R.drawable.ec1);
            im.add(R.drawable.ec3);
            product.setTab_img(im);
            productList.add(product);

            product= new Product();
            product.setName("Chaussure 2");
            product.setCategorie("Chaussures");
            product.setImg(R.drawable.ic_ec6);
            product.setPrice(39734);
            product.setRef("hefj3dsP98");
            product.setTypeClient("Enfants");
            size= new ArrayList<>();
            size.add("37");
            size.add("38");

            product.setSizes(size);
            color= new ArrayList<>();
            color.add("bleu");
            color.add("Blanc");
            color.add("noir");
            product.setColors(color);
            im= new  ArrayList<>();
            im.add(R.drawable.ec2);
            im.add(R.drawable.ec3);

            product.setTab_img(im);
            productList.add(product);


            product= new Product();
            product.setName("Chaussure 3");
            product.setCategorie("Chaussures");
            product.setImg(R.drawable.ic_ec6);
            product.setPrice(39734);
            product.setRef("hefj3dsP98");
            product.setTypeClient("Enfants");
            size= new ArrayList<>();
            size.add("37");
            size.add("38");

            product.setSizes(size);
            color= new ArrayList<>();
            color.add("bleu");
            color.add("Blanc");
            color.add("noir");
            product.setColors(color);
            im= new  ArrayList<>();
            im.add(R.drawable.ec3);
            im.add(R.drawable.ec3);

            product.setTab_img(im);
            productList.add(product);


            product= new Product();
            product.setName("Chaussure 4");
            product.setCategorie("Chaussures");
            product.setImg(R.drawable.ic_ec6);
            product.setPrice(39734);
            product.setRef("hefj3dsP98");
            product.setTypeClient("Enfants");
            size= new ArrayList<>();
            size.add("37");
            size.add("38");

            product.setSizes(size);
            color= new ArrayList<>();
            color.add("bleu");
            color.add("Blanc");
            color.add("noir");
            product.setColors(color);
            im= new  ArrayList<>();
            im.add(R.drawable.ec2);
            im.add(R.drawable.ec3);

            product.setTab_img(im);
            productList.add(product);

            product= new Product();
            product.setName("Chaussure 5");
            product.setCategorie("Chaussures");
            product.setImg(R.drawable.ic_ec6);
            product.setPrice(39734);
            product.setRef("hefj3dsP98");
            product.setTypeClient("Enfants");
            size= new ArrayList<>();
            size.add("37");
            size.add("38");

            product.setSizes(size);
            color= new ArrayList<>();
            color.add("bleu");
            color.add("Blanc");
            color.add("noir");
            product.setColors(color);
            im= new  ArrayList<>();
            im.add(R.drawable.ec2);
            im.add(R.drawable.ec3);

            product.setTab_img(im);
            productList.add(product);

        }*/





        return productList;
    }
}
