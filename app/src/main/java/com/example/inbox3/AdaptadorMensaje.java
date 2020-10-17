package com.example.inbox3;


import android.graphics.Typeface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.inbox3.models.Mensaje;
import com.squareup.picasso.Picasso;

import java.util.List;

public class AdaptadorMensaje extends BaseAdapter {

    private List<Mensaje> listaMensajes;
    View view = null;
    Typeface boldTypeface = Typeface.defaultFromStyle(Typeface.BOLD);



    public AdaptadorMensaje(List<Mensaje> listaMensajes) {
        this.listaMensajes = listaMensajes;
    }

    @Override
    public int getCount() {
        return listaMensajes.size();
    }

    @Override
    public Mensaje getItem(int i) {
        return listaMensajes.get(i);
    }

    @Override
    public long getItemId(int i) {
        return getItem(i).getId();
    }

    @Override
    public View getView(int i, View convertView, ViewGroup parent) {

        if (convertView == null) {
             view = LayoutInflater.from(parent.getContext()).inflate(R.layout.elemento_lista, parent, false);
        } else  view = convertView;
        Mensaje mensaje = getItem(i);

        ImageView avatar = view.findViewById(R.id.avatar);
        TextView asunto = view.findViewById(R.id.asunto);
        TextView preview = view.findViewById(R.id.preview);
        TextView remitente = view.findViewById(R.id.remitente);
        String previewR = mensaje.getContenido().substring(0,50);
        String letraInicio = mensaje.getRemitente();



        asunto.setText(mensaje.getAsunto());
        preview.setText(previewR+"...");
        remitente.setText(mensaje.getRemitente() + " ("+mensaje.getCorreo()+")");
        asunto.setTypeface(boldTypeface);
        Picasso.get().load(mensaje.getColor()).into(avatar);
       /* switch(letraInicio){
            case "Mani por kilo":
                Picasso.get().load("https://http2.mlstatic.com/D_NQ_NP_877277-MLA31116283067_062019-O.jpg").into(avatar);
                break;
            case "B":
                Picasso.get().load("https://lettergenerator.net/domain/lettergenerator/assets/generators/bold-letter-generator/asapbold/printable-letter-asapbold-b.jpg?v=2").into(avatar);
                break;
            case "C":
                Picasso.get().load("https://lettergenerator.net/domain/lettergenerator/assets/generators/bold-letter-generator/asapbold/printable-letter-asapbold-c.jpg?v=2").into(avatar);
                break;
            case "D":
                Picasso.get().load("https://lettergenerator.net/domain/lettergenerator/assets/generators/bold-letter-generator/asapbold/printable-letter-asapbold-d.jpg?v=2").into(avatar);
                break;
            case "E":
                Picasso.get().load("https://lettergenerator.net/domain/lettergenerator/assets/generators/bold-letter-generator/asapbold/printable-letter-asapbold-e.jpg?v=2").into(avatar);
                break;
            case "F":
                Picasso.get().load("https://lettergenerator.net/domain/lettergenerator/assets/generators/bold-letter-generator/asapbold/printable-letter-asapbold-f.jpg?v=2").into(avatar);
                break;
            case "G":
                Picasso.get().load("https://lettergenerator.net/domain/lettergenerator/assets/generators/bold-letter-generator/asapbold/printable-letter-asapbold-g.jpg?v=2").into(avatar);
                break;
            case "H":
                Picasso.get().load("https://lettergenerator.net/domain/lettergenerator/assets/generators/bold-letter-generator/asapbold/printable-letter-asapbold-h.jpg?v=2").into(avatar);
                break;
            case "I":
                Picasso.get().load("https://lettergenerator.net/domain/lettergenerator/assets/generators/bold-letter-generator/asapbold/printable-letter-asapbold-i.jpg?v=2").into(avatar);
                break;
            case "J":
                Picasso.get().load("https://lettergenerator.net/domain/lettergenerator/assets/generators/bold-letter-generator/asapbold/printable-letter-asapbold-j.jpg?v=2").into(avatar);
                break;
            case "K":
                Picasso.get().load("https://lettergenerator.net/domain/lettergenerator/assets/generators/bold-letter-generator/asapbold/printable-letter-asapbold-k.jpg?v=2").into(avatar);
                break;
            case "L":
                Picasso.get().load("https://lettergenerator.net/domain/lettergenerator/assets/generators/bold-letter-generator/asapbold/printable-letter-asapbold-l.jpg?v=2").into(avatar);
                break;
            case "M":
                Picasso.get().load("https://lettergenerator.net/domain/lettergenerator/assets/generators/bold-letter-generator/asapbold/printable-letter-asapbold-m.jpg?v=2").into(avatar);
                break;
            case "N":
                Picasso.get().load("https://lettergenerator.net/domain/lettergenerator/assets/generators/bold-letter-generator/asapbold/printable-letter-asapbold-n.jpg?v=2").into(avatar);
                break;
            case "O":
                Picasso.get().load("https://lettergenerator.net/domain/lettergenerator/assets/generators/bold-letter-generator/asapbold/printable-letter-asapbold-o.jpg?v=2").into(avatar);
                break;
            case "P":
                Picasso.get().load("https://lettergenerator.net/domain/lettergenerator/assets/generators/bold-letter-generator/asapbold/printable-letter-asapbold-p.jpg?v=2").into(avatar);
                break;
            case "Q":
                Picasso.get().load("https://lettergenerator.net/domain/lettergenerator/assets/generators/bold-letter-generator/asapbold/printable-letter-asapbold-q.jpg?v=2").into(avatar);
                break;
            case "R":
                Picasso.get().load("https://lettergenerator.net/domain/lettergenerator/assets/generators/bold-letter-generator/asapbold/printable-letter-asapbold-r.jpg?v=2").into(avatar);
                break;
            case "S":
                Picasso.get().load("https://lettergenerator.net/domain/lettergenerator/assets/generators/bold-letter-generator/asapbold/printable-letter-asapbold-s.jpg?v=2").into(avatar);
                break;
            case "T":
                Picasso.get().load("https://lettergenerator.net/domain/lettergenerator/assets/generators/bold-letter-generator/asapbold/printable-letter-asapbold-t.jpg?v=2").into(avatar);
                break;
            case "U":
                Picasso.get().load("https://lettergenerator.net/domain/lettergenerator/assets/generators/bold-letter-generator/asapbold/printable-letter-asapbold-u.jpg?v=2").into(avatar);
                break;
            case "V":
                Picasso.get().load("https://lettergenerator.net/domain/lettergenerator/assets/generators/bold-letter-generator/asapbold/printable-letter-asapbold-v.jpg?v=2").into(avatar);
                break;
            case "W":
                Picasso.get().load("https://lettergenerator.net/domain/lettergenerator/assets/generators/bold-letter-generator/asapbold/printable-letter-asapbold-w.jpg?v=2").into(avatar);
                break;
            case "X":
                Picasso.get().load("https://lettergenerator.net/domain/lettergenerator/assets/generators/bold-letter-generator/asapbold/printable-letter-asapbold-x.jpg?v=2").into(avatar);
                break;
            case "Y":
                Picasso.get().load("https://lettergenerator.net/domain/lettergenerator/assets/generators/bold-letter-generator/asapbold/printable-letter-asapbold-y.jpg?v=2").into(avatar);
                break;
            case "Z":
                Picasso.get().load("https://lettergenerator.net/domain/lettergenerator/assets/generators/bold-letter-generator/asapbold/printable-letter-asapbold-z.jpg?v=2").into(avatar);
                break;
            default:
                Picasso.get().load("http://lorempixel.com/400/200/people/").into(avatar);
                */





        //avatar.setImageResource(R.drawable.aaa);




        return view;
    }

}
