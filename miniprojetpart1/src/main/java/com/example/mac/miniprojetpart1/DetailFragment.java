package com.example.mac.miniprojetpart1;

import android.app.FragmentTransaction;

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
import Metier.Product;


public  class DetailFragment extends Fragment implements BaseSliderView.OnSliderClickListener, ViewPagerEx.OnPageChangeListener {
    ArrayAdapter arrayAdapter;
    private SliderLayout mDemoSlider;





    @Nullable
    @Override

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_detail, null);
        Bundle bundle = getArguments();


        if (bundle != null) {

            Product product = (Product) bundle.getSerializable("product");
            TextView nameprod= (TextView)view.findViewById(R.id.name);
            TextView ref= (TextView)view.findViewById(R.id.ref);
            Spinner spinnersize= (Spinner) view.findViewById(R.id.taillespiner);
            Spinner spinnercolor= (Spinner) view.findViewById(R.id.couleurspinner);

            nameprod.setText(product.getName());
            ref.setText(product.getRef());

            arrayAdapter= new ArrayAdapter(view.getContext(), android.R.layout.simple_spinner_item, product.getColors());
            spinnercolor.setAdapter(arrayAdapter);

            arrayAdapter= new ArrayAdapter(view.getContext(), android.R.layout.simple_spinner_item, product.getSizes());
            spinnersize.setAdapter(arrayAdapter);




            mDemoSlider = (SliderLayout)view.findViewById(R.id.slider);

            HashMap<String,Integer> file_maps = new HashMap<String, Integer>();
            if (product.getTab_img()!= null) {
                for (int i = 0; i < product.getTab_img().size(); i++) {
                    file_maps.put(product.getName()+i, product.getTab_img(i));


                }
            }


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
