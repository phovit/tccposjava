package tcc.posjava.unitri.edu.br.med4u;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

public class PrincipalActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_principal);
        ImageView imageView = findViewById(R.id.ivMedicament);
        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent buscaMedicamento = new Intent(PrincipalActivity.this, ConsMedicoActivity.class);
                startActivity(buscaMedicamento);
            }
        });
        Button login = findViewById(R.id.btLoginPrincipal);
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent telaLogin = new Intent(PrincipalActivity.this, LoginActivity.class);
                startActivity(telaLogin);
            }
        });
        ImageView findPharm = findViewById(R.id.ivPharm);
        findPharm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent fPharm = new Intent(PrincipalActivity.this, ConsFarmaciaActivity.class);
                startActivity(fPharm);
            }
        });
        ImageView findMedicament = findViewById(R.id.ivMedicament);
        findMedicament.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent fMedicament = new Intent(PrincipalActivity.this, ConsFabricanteActivity.class);
                startActivity(fMedicament);
            }
        });
        ImageView findMedic = findViewById(R.id.ivMedic);
        findMedic.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent fMedic = new Intent(PrincipalActivity.this, ConsMedicoActivity.class);
                startActivity(fMedic);
            }
        });
        TextView testCamera = findViewById(R.id.tvTestCamera);
        testCamera.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent tCamera = new Intent(PrincipalActivity.this, CadReceitaActivity.class);
                startActivity(tCamera);
            }
        });

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

    @Override
    protected void onStart() {
        super.onStart();
        handleNotification();
    }

    private void handleNotification() {

        String title = "", body = "";

        Bundle bundle = getIntent().getExtras();

        if (getIntent().hasExtra("EXTRA_TITLE")) {

            title = bundle.getString("EXTRA_TITLE");
        }
        if (getIntent().hasExtra("EXTRA_BODY")) {

            body = bundle.getString("EXTRA_BODY");
        }

        if (!title.equals("") || !body.equals("")) {

            showDialog(title, body);
        }
    }

    private void showDialog(String title, String message) {
        AlertDialog alertDialog = new AlertDialog.Builder(PrincipalActivity.this).create();
        alertDialog.setTitle(title);
        alertDialog.setMessage(message);
        alertDialog.setButton(AlertDialog.BUTTON_POSITIVE, "OK",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                });
        alertDialog.show();
    }
}
