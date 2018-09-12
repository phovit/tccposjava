package tcc.posjava.unitri.edu.br.med4u;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;

public class NewUserActivity extends AppCompatActivity {

    ImageView campoFoto = findViewById(R.id.formulario_foto);
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_user_continue);
    }

    public ImageView getCampoFoto(){
        return campoFoto;
    }


}
