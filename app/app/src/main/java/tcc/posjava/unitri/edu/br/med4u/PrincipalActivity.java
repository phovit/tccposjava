package tcc.posjava.unitri.edu.br.med4u;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import com.google.firebase.iid.FirebaseInstanceId;


public class PrincipalActivity extends AppCompatActivity {

    private String autorizacao;
    private static String TAG = "PrincipalActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_principal);

        String[] dados = new String[] {"             Fármacias", "         Medicamentos", "              Médicos"};
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, dados);

        ListView listView = findViewById(R.id.listview);
        Button login = findViewById(R.id.btLoginPrincipal);
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            Intent telaLogin = new Intent(PrincipalActivity.this, LoginActivity.class);
            startActivity(telaLogin);
            }
        });

        listView.setAdapter(adapter);

        Log.d(TAG, "Token Firebase: " + FirebaseInstanceId.getInstance().getToken());
        Log.i(TAG, "Token Firebase: " + FirebaseInstanceId.getInstance().getToken());

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                switch (position){
                    case 0: searchPharm();
                        break;
                    case 1: searchMedicines();
                        break;
                    case 2: searchDoctors();
                        break;
                    case 3: finish();
                        break;
                }
            }
            });
        }

    private void searchPharm() {
        Intent it = new Intent(PrincipalActivity.this, ConsFarmaciaActivity.class);
        it.putExtra("autorizacao", autorizacao);
        startActivity(it);
    }

    private void searchMedicines() {
        Intent it = new Intent(PrincipalActivity.this, ConsMedicineActivity.class);
        it.putExtra("autorizacao", autorizacao);
        startActivity(it);
    }

    private void searchDoctors() {
        Intent it = new Intent(PrincipalActivity.this, ConsMedicoActivity.class);
        it.putExtra("autorizacao", autorizacao);
        startActivity(it);
    }


    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_principal, menu);
        return true;

    }

    /*@Override
    protected void onResume() {
        super.onResume();
        preencheAutorizacao();
    }

    public void preencheAutorizacao(){
        SharedPreferences preferences = getSharedPreferences("autorizacao", MODE_PRIVATE);
        autorizacao = preferences.getString("autorizacao", "autorizacao");
    }*/

}
