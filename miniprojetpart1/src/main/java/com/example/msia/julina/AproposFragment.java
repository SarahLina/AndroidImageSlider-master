package com.example.msia.julina;
import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.support.v4.app.Fragment;

import com.example.mac.miniprojetpart1.R;


public class AproposFragment extends Fragment {
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_apropos, null);
        ImageView imageView= (ImageView) view.findViewById(R.id.logoapropos);
        imageView.setImageResource(R.drawable.ic_logo);
        return view;
    }
}