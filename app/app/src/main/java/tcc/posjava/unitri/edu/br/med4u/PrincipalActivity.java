package tcc.posjava.unitri.edu.br.med4u;

import android.app.Activity;
import android.app.VoiceInteractor;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.media.Image;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

public class PrincipalActivity extends AppCompatActivity {

    private ListView listView;

    private static String TAG = "PrincipalActivity";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_principal);

        createListView();

        /*String[] dados = new String[] {"     Fármacias", "   Medicamentos", "    Médicos"};
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, dados);*/

        ListView listView = findViewById(R.id.list);
        Button login = findViewById(R.id.btLoginPrincipal);
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            Intent telaLogin = new Intent(PrincipalActivity.this, LoginActivity.class);
            startActivity(telaLogin);
            }
        });

        /*listView.setAdapter(adapter);*/


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
        startActivity(it);
    }

    private void searchMedicines() {
        Intent it = new Intent(PrincipalActivity.this, ConsMedicoActivity.class);
        startActivity(it);
    }

    private void searchDoctors() {
        Intent it = new Intent(PrincipalActivity.this, ConsMedicoActivity.class);
        startActivity(it);
    }


    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_principal, menu);
        return true;

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.cadCidade:
                Intent cadastroCidade = new Intent(this, CadCidadeActivity.class);
                startActivity(cadastroCidade);
                break;
            case R.id.cadEndereco:
                Intent cadastroEndereco = new Intent(this, CadEnderecoActivity.class);
                startActivity(cadastroEndereco);
                break;
            case R.id.cadEstado:
                Intent cadastroEstado = new Intent(this, CadEstadoActivity.class);
                startActivity(cadastroEstado);
                break;
            case R.id.cadFabricante:
                Intent cadastroFabricante = new Intent(this, CadFabricanteActivity.class);
                startActivity(cadastroFabricante);
                break;
            case R.id.cadFarmacia:
                Intent cadastroFarmacia = new Intent(this, CadFarmaciaActivity.class);
                startActivity(cadastroFarmacia);
                break;
            case R.id.cadMedico:
                Intent cadastroMedico = new Intent(this, CadMedicoActivity.class);
                startActivity(cadastroMedico);
                break;
            case R.id.cadReceita:
                Intent cadastroReceita = new Intent(this, CadReceitaActivity.class);
                startActivity(cadastroReceita);
                break;
            case R.id.consEndereco:
                Intent consultaEndereco = new Intent(this, ConsEnderecoActivity.class);
                startActivity(consultaEndereco);
            case R.id.consFabricante:
                Intent consultaFabricante = new Intent(this, ConsFabricanteActivity.class);
                startActivity(consultaFabricante);
            case R.id.consFarmacia:
                Intent consultaFermacia = new Intent(this, ConsFarmaciaActivity.class);
                startActivity(consultaFermacia);
            case R.id.consMedico:
                Intent consultaMedico = new Intent(this, ConsMedicoActivity.class);
                startActivity(consultaMedico);
            case R.id.consReceita:
                Intent consultaReceita = new Intent(this, ConsReceitaActivity.class);
                startActivity(consultaReceita);

            default:
                return false;
        }

        return super.onOptionsItemSelected(item);
    }

    private void createListView()
    {
        //Criamos nossa lista que preenchera o ListView
        ArrayList itens = new ArrayList<itemList>();
        itemList item1 = new itemList("Fármacias", R.drawable.buscamedicamento);
        itemList item2 = new itemList("Medicamentos", R.drawable.buscamedicamento);
        itemList item3 = new itemList("Médicos", R.drawable.buscamedicamento);


        itens.add(item1);
        itens.add(item2);
        itens.add(item3);


        //Cria o adapter
        AdapterListView adapterListView = new AdapterListView(this, itens);

        //Define o Adapte

        listView.setAdapter(adapterListView);
        //Cor quando a lista é selecionada para ralagem.
        listView.setCacheColorHint(Color.TRANSPARENT);
    }


}
