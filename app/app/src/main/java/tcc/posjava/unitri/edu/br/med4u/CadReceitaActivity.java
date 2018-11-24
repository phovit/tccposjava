package tcc.posjava.unitri.edu.br.med4u;


import android.content.Intent;

import android.os.Bundle;

import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

public class CadReceitaActivity extends AppCompatActivity {

    private String url = "http://med4u.herokuapp.com/reminders";
    private String urlMed = "http://med4u.herokuapp.com/medicine";
    private String autorizacao;
    private TextView timeView;

    private static String TAG = "CadReceitaActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cad_receita);
        ActionBar actionbar = getSupportActionBar();
        actionbar.setTitle("Med4U");
        Intent it = getIntent();
        autorizacao = it.getStringExtra("autorizacao");

    }
}
