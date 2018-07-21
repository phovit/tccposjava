package tcc.posjava.unitri.edu.br.med4u;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

public class PrincipalActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_principal);
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
            case R.id.btCadastroFab:
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

            default:
                return false;
        }

        return super.onOptionsItemSelected(item);
    }
}
