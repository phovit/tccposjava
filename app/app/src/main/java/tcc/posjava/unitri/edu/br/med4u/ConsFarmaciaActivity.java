package tcc.posjava.unitri.edu.br.med4u;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.Toast;

public class ConsFarmaciaActivity extends AppCompatActivity {

    private String autorizacao;
    private String TAG = "ConsFarmaciaActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cons_farmacia);
        ActionBar actionbar = getSupportActionBar();
        actionbar.setTitle("Med4U");
        Intent it = getIntent();
        autorizacao = it.getStringExtra("autorizacao");
    }

    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_principal, menu);
        return true;

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.menuCadLembretes:
                if (autorizacao != null) {
                    Intent cadLembretes = new Intent(this, CadRememberActivity.class);
                    cadLembretes.putExtra("autorizacao", autorizacao);
                    startActivity(cadLembretes);
                } else {
                    Toast.makeText(ConsFarmaciaActivity.this, "Necessita autenticação", Toast.LENGTH_LONG).show();
                }
                break;
            case R.id.menuCadMedicines:
                if (autorizacao != null) {
                    Intent cadMedicines = new Intent(this, CadMedicinesActivity.class);
                    cadMedicines.putExtra("autorizacao", autorizacao);
                    startActivity(cadMedicines);
                } else {
                    Toast.makeText(ConsFarmaciaActivity.this, "Necessita autenticação", Toast.LENGTH_LONG).show();
                }
                break;
            case R.id.menuCadReceita:
                if (autorizacao != null) {
                    Intent cadastroReceita = new Intent(this, CadReceitaActivity.class);
                    cadastroReceita.putExtra("autorizacao", autorizacao);
                    startActivity(cadastroReceita);
                } else {
                    Toast.makeText(ConsFarmaciaActivity.this, "Necessita autenticação", Toast.LENGTH_LONG).show();
                }
                break;
            case R.id.menuCadUsuario:
                Intent cadastroUsuario = new Intent(this, NewUserActivity.class);
                cadastroUsuario.putExtra("autorizacao", autorizacao);
                startActivity(cadastroUsuario);
                break;
            case R.id.menuConsFarmacia:
                Intent consultaFarm = new Intent(this, ConsFarmaciaActivity.class);
                consultaFarm.putExtra("autorizacao", autorizacao);
                startActivity(consultaFarm);
                break;
            case R.id.menuConsLembretes:
                if (autorizacao != null) {
                    Intent consLembretes = new Intent(this, ConsRememberActivity.class);
                    consLembretes.putExtra("autorizacao", autorizacao);
                    startActivity(consLembretes);
                } else {
                    Toast.makeText(ConsFarmaciaActivity.this, "Necessita autenticação", Toast.LENGTH_LONG).show();
                }
                break;
            case R.id.menuConsMedicamentos:
                Intent consultaMedicines = new Intent(this, ConsMedicineActivity.class);
                startActivity(consultaMedicines);
                break;
            case R.id.menuConsMedico:
                Intent contultaMedicos = new Intent(this, ConsMedicoActivity.class);
                contultaMedicos.putExtra("autorizacao", autorizacao);
                startActivity(contultaMedicos);
                break;
            case R.id.menuConsReceita:
                if (autorizacao != null) {
                    Intent consultaReceita = new Intent(this, ConsReceitaActivity.class);
                    consultaReceita.putExtra("autorizacao", autorizacao);
                    startActivity(consultaReceita);
                } else {
                    Toast.makeText(ConsFarmaciaActivity.this, "Necessita autenticação", Toast.LENGTH_LONG).show();
                }
                break;
            /*case R.id.menuPerfilEditar:
                Intent editPerfil = new Intent(this, EditPerfil.class);
                startActivity(editPerfil);
                break;
            case R.id.menuPerfilVisualizar:
                Intent visPerfil = new Intent(this, visPerfil.class);
                startActivity(visPerfil);
                break;*/
            case R.id.menuSair:
                SharedPreferences myPrefs = getSharedPreferences("Logout",
                        MODE_PRIVATE);
                SharedPreferences.Editor editor = myPrefs.edit();
                editor.clear();
                editor.commit();
                /*AppState.getSingleInstance().setLoggingOut(true);*/
                Log.d(TAG, "Now log out and start the activity login");
                Intent intent = new Intent(ConsFarmaciaActivity.this,
                        PrincipalActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(intent);
            default:
                return false;
        }

        return super.onOptionsItemSelected(item);
    }
}
