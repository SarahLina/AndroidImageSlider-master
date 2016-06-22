package Adapters;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.NumberPicker;
import android.widget.TextView;

import com.example.mac.miniprojetpart1.R;

import java.util.List;

import Metier.ArticlePannier;
import Metier.Cmd;

/**
 * Created by msia on 27/04/2016.
 */
public class CmdCustomAdapter extends BaseAdapter {
    private Context context;
    private List<Cmd> cmdList;


    public CmdCustomAdapter(Context context, List<Cmd> cmdList) {
        this.context = context;
        this.cmdList = cmdList;
    }

    @Override
    public int getCount() {
        return cmdList.size();
    }

    @Override
    public Object getItem(int position) {
        return cmdList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // mettre le layout une seule fois lorsque convertView est null
        if (convertView == null) {
            convertView = parent.inflate(context, R.layout.cmd_item, null);
        }

        TextView textNum = (TextView) convertView.findViewById(R.id.cmdnum);
        TextView textDate = (TextView) convertView.findViewById(R.id.date);
        TextView textEtat = (TextView) convertView.findViewById(R.id.state);

        //coverIcon.setImageResource(articleList.get(position).getIconCover());

        textNum.setText(cmdList.get(position).getNameCmd());
        textDate.setText(cmdList.get(position).getDate());
        textEtat.setText(cmdList.get(position).getEtat());

        //Spinner spinnerQuant = (Spinner) convertView.findViewById(R.id.s)
        //textNom.setText(articleList.get(position).getTitle());
        // Récupérer la liste des auteurs
        //List<String> bookAuthors = articleList.get(position).getAuthors();
        // Séparer la liste des auteurs par une virgule
        //String authors = bookAuthors.get(0);
       /* int listSize = bookAuthors.size();
        if (listSize > 1) {
            for (int i = 1; i < listSize; i++) {
                authors = authors + ", " + bookAuthors.get(i);
            }
            textAuthors.setText("Auteur(s): "+ authors);
        }
        textEditor.setText("Editeur: " + bookList.get(position).getEditor());*/

        return convertView;
    }
}
