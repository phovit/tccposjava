package tcc.posjava.unitri.edu.br.med4u;

import android.widget.ImageView;

public class itemList {

    private String texto;
    private int iconeRid;

    public itemList()
    {
    }

    public itemList(String texto, int iconeRid)
    {
        this.texto = texto;
        this.iconeRid = iconeRid;
    }

    public int getIconeRid()
    {
        return iconeRid;
    }

    public void setIconeRid(int iconeRid)
    {
        this.iconeRid = iconeRid;
    }

    public String getTexto()
    {
        return texto;
    }

    public void setTexto(String texto)
    {
        this.texto = texto;
    }


}
