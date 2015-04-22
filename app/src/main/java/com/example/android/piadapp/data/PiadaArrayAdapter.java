package com.example.android.piadapp.data;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.android.piadapp.R;

import java.util.List;


public class PiadaArrayAdapter extends ArrayAdapter<Piada> {

    private Context context;
    private List<Piada> objects;

    public PiadaArrayAdapter(Context context, int resource, List<Piada> objects) {
        super(context, resource, objects);
        this.context = context;
        this.objects = objects;
    }


    @Override
    public View getView(int position, View convertView, ViewGroup parent) {


        Piada piada = objects.get(position);

        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Activity.LAYOUT_INFLATER_SERVICE);
        View view = inflater.inflate(R.layout.piada_listitem, null);

        ImageView image = (ImageView) view.findViewById(R.id.PiadaImage);
        image.setImageResource(piada.getRisorseImmagini());

        TextView tv = (TextView) view.findViewById(R.id.PiadaName);
        tv.setText(piada.getNome());

        TextView tIn = (TextView) view.findViewById(R.id.ListaIngredienti);
        tIn.setText(piada.getiP());

       /* if(tIn.getText().toString() == "placeholderIn") {
            tIn.setVisibility(View.GONE);
        }
*/
        return view;

    }
}
