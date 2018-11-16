package tcc.posjava.unitri.edu.br.med4u;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

public class AdapterListView extends BaseAdapter {
    private LayoutInflater mInflater;
    private ArrayList<itemList> itens;
 
    public AdapterListView(Context context, ArrayList<itemList> itens)
    {
        //Itens que preencheram o listview
        this.itens = itens;
        //responsavel por pegar o Layout do item.
        mInflater = LayoutInflater.from(context);
    }
 
    /**
     * Retorna a quantidade de itens
     *
     * @return
     */
    public int getCount()
    {
        return itens.size();
    }
 
    /**
     * Retorna o item de acordo com a posicao dele na tela.
     *
     * @param position
     * @return
     */
    public itemList getItem(int position) {
        return itens.get(position);
    }
 
    /**
     * Sem implementação
     *
     * @param position
     * @return
     */
    public long getItemId(int position)
    {
        return position;
    }
 
    public View getView(int position, View view, ViewGroup parent)
    {
        //Pega o item de acordo com a posção.
        itemList item = itens.get(position);
        //infla o layout para podermos preencher os dados
        view = mInflater.inflate(R.layout.activity_principal, null);

        //atravez do layout pego pelo LayoutInflater, pegamos cada id relacionado
        //ao item e definimos as informações.
        TextView textView = view.findViewById(R.id.text);
        textView.setText(item.getTexto());
        ImageView imageView = view.findViewById(R.id.imagemview);
        imageView.setImageResource(item.getIconeRid());

        return view;
    }
}