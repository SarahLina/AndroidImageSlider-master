package com.example.mac.miniprojetpart1;

import android.app.FragmentTransaction;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.app.Fragment;
import android.support.annotation.StringDef;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.daimajia.slider.library.Animations.DescriptionAnimation;
import com.daimajia.slider.library.SliderLayout;
import com.daimajia.slider.library.SliderTypes.BaseSliderView;
import com.daimajia.slider.library.SliderTypes.TextSliderView;
import com.daimajia.slider.library.Tricks.ViewPagerEx;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import Adapters.TransformerAdapter;
import Metier.FullProduct;
import Metier.Product;
import Repository.CardLineRepo;
import Repository.FullProductRepo;
import Repository.ProductRepo;
import Services.UpdateDispoTask;


public  class DetailFragment extends Fragment implements BaseSliderView.OnSliderClickListener, ViewPagerEx.OnPageChangeListener {
    ArrayAdapter arrayAdapter;
    private SliderLayout mDemoSlider;

    private Product product ;
    private TextView nameprod;
    private TextView ref;
    private Spinner spinnersize;
    private Spinner spinnercolor;
    private EditText quantite;




    @Nullable
    @Override

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        final View view = inflater.inflate(R.layout.fragment_detail, null);
        Bundle bundle = getArguments();


        if (bundle != null) {

            this.product = (Product) bundle.getSerializable("product");
            this.nameprod= (TextView)view.findViewById(R.id.name);
             this.ref= (TextView)view.findViewById(R.id.ref);
             this.spinnersize= (Spinner) view.findViewById(R.id.taillespiner);
             this.spinnercolor= (Spinner) view.findViewById(R.id.couleurspinner);
            this.quantite = (EditText) view.findViewById(R.id.quantite);

            final Button ajouter = (Button) view.findViewById(R.id.ajouter);
           // Intent intent = new Intent(null,MainActivity.class);
            ajouter.setOnClickListener(new View.OnClickListener()
            {
                @Override
                public void onClick(View v)
                {
                    System.out.println("------ajouter  dagui----");
                    CardLineRepo cardLineRepo = new CardLineRepo(v.getContext());
                    FullProduct fullProduct = new FullProduct();
                    ProductRepo productRepo = new ProductRepo(v.getContext());
                    //FullProductRepo fullProductRepo = new FullProductRepo(v.getContext());
                    fullProduct.setProduct(productRepo.getProductsByRef(product.getRef()));
                    fullProduct.setSize(spinnersize.getSelectedItem().toString());//
                    fullProduct.setColor(spinnercolor.getSelectedItem().toString());//spin-------
                    //fullProduct.setQuantity(quantite.getText().toString().);////parse in int

                    new UpdateDispoTask(getActivity()).execute(fullProduct.getProduct().getId_product(), fullProduct.getColor(), fullProduct.getSize(),Integer.parseInt(quantite.getText().toString()));
                  //  cardLineRepo.addCardLineRepo(fullProduct,Integer.parseInt(quantite.getText().toString()));

                   // Intent intent = new Intent(null,MainActivity.class);
                    //startActivity(intent);

                }
            });
            nameprod.setText(product.getName());
            ref.setText(product.getRef());

            arrayAdapter= new ArrayAdapter(view.getContext(), android.R.layout.simple_spinner_item, product.getColors());
            spinnercolor.setAdapter(arrayAdapter);

            arrayAdapter= new ArrayAdapter(view.getContext(), android.R.layout.simple_spinner_item, product.getSizes());
            spinnersize.setAdapter(arrayAdapter);




            mDemoSlider = (SliderLayout)view.findViewById(R.id.slider);

            HashMap<String,Integer> file_maps = new HashMap<String, Integer>();
            /*if (product.getTab_img()!= null) {
                for (int i = 0; i < product.getTab_img().size(); i++) {
                    file_maps.put(product.getName()+i, product.getTab_img(i));


                }
            }*/


            for(String name : file_maps.keySet()){
                TextSliderView textSliderView = new TextSliderView(view.getContext());
                // initialize a SliderLayout
                textSliderView
                        .description(name)
                        .image(file_maps.get(name))
                        .setScaleType(BaseSliderView.ScaleType.Fit)
                        .setOnSliderClickListener((BaseSliderView.OnSliderClickListener) this);

                //add your extra information
                textSliderView.bundle(new Bundle());
                textSliderView.getBundle()
                        .putString("extra",name);

                mDemoSlider.addSlider(textSliderView);
            }
            mDemoSlider.setPresetTransformer(SliderLayout.Transformer.Accordion);
            mDemoSlider.setPresetIndicator(SliderLayout.PresetIndicators.Center_Bottom);
            mDemoSlider.setCustomAnimation(new DescriptionAnimation());
            mDemoSlider.setDuration(4000);
            mDemoSlider.addOnPageChangeListener((ViewPagerEx.OnPageChangeListener) this);



        }

        return view;
    }
   /* public void ajouter(){
       // FullProductRepo fullProductRepo =new FullProductRepo(this.view.getContext());
        CardLineRepo cardLineRepo = new CardLineRepo(this.view.getContext());
        FullProduct fullProduct = new FullProduct();
        ProductRepo productRepo = new ProductRepo(this.view.getContext());
        FullProductRepo fullProductRepo = new FullProductRepo(this.view.getContext());
        fullProduct.setProduct(productRepo.getProductsByRef(ref.toString()));
        fullProduct.setSize(spinnersize.toString());//
        fullProduct.setColor(spinnercolor.toString());//spin-------
        //fullProduct.setQuantity(quantite.getText().toString().);////parse in int
        cardLineRepo.addCardLineRepo(fullProduct,Integer.parseInt(quantite.getText().toString()));

    }*/
    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

    }

    @Override
    public void onPageSelected(int position) {

    }

    @Override
    public void onPageScrollStateChanged(int state) {

    }

    @Override
    public void onSliderClick(BaseSliderView slider) {

    }

}
