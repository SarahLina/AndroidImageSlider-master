package com.example.mac.miniprojetpart1;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.Toast;

import com.example.mac.miniprojetpart1.R;
import com.example.msia.julina.LoginActivity;

import java.util.ArrayList;
import java.util.List;

import Adapters.ArticlePannierCutomAdapter;
import Metier.ArticlePannier;
import Metier.Cmd;
import Adapters.CmdCustomAdapter;
import Services.GetListCmdTask;
import util.UtilService;

public class CmdFragment extends Fragment {
    CmdCustomAdapter cutomAdapter ;
    ListView listView;



    public CmdFragment() {
        // Required empty public constructor

    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        View v = inflater.inflate(R.layout.fragment_cmd,null);
      //  ListView listView = (ListView) v.findViewById(R.id.listView);
      //  cutomAdapter = new CmdCustomAdapter(getActivity(),getCmdList());
      //  listView.setAdapter(cutomAdapter);

        if (new UtilService().checkNetwork(getActivity())) {
            new GetListCmdTask(getActivity()).execute(LoginActivity.username_current);
        } else {
            Toast.makeText(getActivity(), "Aucune connexion", Toast.LENGTH_SHORT).show();
        }

        return v;
    }

    public List<Cmd> getCmdList() {
        String[] listSummary = {"Article1","Article2"};
        //String[] listSummary = getResources().getStringArray(R.array.summary);
        List<Cmd> cmdList = new ArrayList<Cmd>();
        // le 1er livre
        Cmd cmd = new Cmd();
        cmd.setNameCmd("001/16");
        //List authors = new ArrayList();

        cmd.setDate("12/10/2016");
        cmd.setEtat("En cours");
        //articlePannier.setIconCover(R.drawable.ic_menu_camera);

        cmdList.add(cmd);

        cmd = new Cmd();

        cmd.setNameCmd("002/16");
        //List authors = new ArrayList();

        cmd.setDate("14/11/2016");
        cmd.setEtat("En livraison");
        //articlePannier.setIconCover(R.drawable.ic_menu_camera);

        cmdList.add(cmd);

        cmd = new Cmd();
        cmd.setNameCmd("004/16");
        //List authors = new ArrayList();

        cmd.setDate("02/01/2016");
        cmd.setEtat("Livrée");
        //articlePannier.setIconCover(R.drawable.ic_menu_camera);

        cmdList.add(cmd);

        return cmdList;
    }

}
