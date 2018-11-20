package tcc.posjava.unitri.edu.br.med4u;

import android.app.Activity;
import android.app.VoiceInteractor;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.Signature;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.media.Image;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.util.Base64;
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

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.List;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.google.firebase.iid.FirebaseInstanceId;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

public class PrincipalActivity extends AppCompatActivity {

    private ListView listView;
    private String autorizacao;
    private static String TAG = "PrincipalActivity";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_principal);
        Intent it = getIntent();
        autorizacao = it.getStringExtra("autorizacao");

        /*createListView();*/

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
        startActivity(it);
    }

    private void searchMedicines() {
        Intent it = new Intent(PrincipalActivity.this, ConsMedicineActivity.class);
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
            case R.id.menuCadMedicines:
                Intent cadMedicines = new Intent(this, CadMedicinesActivity.class);
                startActivity(cadMedicines);
                break;
            case R.id.menuCadReceita:
                Intent cadastroReceita = new Intent(this, CadReceitaActivity.class);
                startActivity(cadastroReceita);
                break;
            case R.id.menuCadUsuario:
                Intent cadastroUsuario = new Intent(this, NewUserActivity.class);
                startActivity(cadastroUsuario);
                break;
            case R.id.menuConsFarmacia:
                Intent consultaFarm = new Intent(this, ConsFarmaciaActivity.class);
                startActivity(consultaFarm);
                break;
            case R.id.menuConsMedicamentos:
                Intent consultaMedicines = new Intent(this, ConsMedicineActivity.class);
                startActivity(consultaMedicines);
                break;
            case R.id.menuConsMedico:
                Intent contultaMedicos = new Intent(this, ConsMedicoActivity.class);
                startActivity(contultaMedicos);
                break;
            case R.id.menuConsReceita:
                Intent consultaReceita = new Intent(this, ConsReceitaActivity.class);
                startActivity(consultaReceita);
            /*case R.id.menuPerfilEditar:
                Intent editPerfil = new Intent(this, EditPerfil.class);
                startActivity(editPerfil);
                break;*/
            default:
                return false;
        }

        return super.onOptionsItemSelected(item);
    }
}
