package tcc.posjava.unitri.edu.br.med4u;

import android.Manifest;
import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.ContentResolver;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.Matrix;
import android.graphics.Point;
import android.graphics.drawable.BitmapDrawable;
import android.hardware.Camera;
import android.media.ExifInterface;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v4.content.FileProvider;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Display;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class CadReceitaActivity extends AppCompatActivity {

    //variaveis para criação da imagem
    private Button takePictureButton;
    private ImageView imageView;
    private Uri file;

   private static String TAG = "CadReceitaActivity";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cad_receita);
        ActionBar actionbar = getSupportActionBar();
        actionbar.setTitle("Med4U");

        /*takePictureButton = findViewById(R.id.button_image);
        imageView = findViewById(R.id.ivCadRec);*/

        /*if (ContextCompat.checkSelfPermission(this, Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED) {
            takePictureButton.setEnabled(false);
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.CAMERA, Manifest.permission.WRITE_EXTERNAL_STORAGE}, 0);
        }*/
        /*ImageView campoFoto = findViewById(R.id.ivCadRec);*/

        /*String caminho = returnPath();*/
        /*Bitmap bitmap = CarregadorDeFoto.carrega(returnPath());*/
        /*campoFoto.setImageBitmap(bitmap);*/

    }

    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_principal, menu);
        return true;

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            /*case R.id.menuCadMedicines:
                Intent cadMedicines = new Intent(this, );
                startActivity(cadMedicines);
                break;*/
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
                break;
            case R.id.menuPerfilVisualizar:
                Intent visPerfil = new Intent(this, visPerfil.class);
                startActivity(visPerfil);
                break;*/
            default:
                return false;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        if (requestCode == 0) {
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED
                    && grantResults[1] == PackageManager.PERMISSION_GRANTED) {
                takePictureButton.setEnabled(true);
            }
        }
    }

    public void takePicture(View view) {

        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);

        file = FileProvider.getUriForFile(
                CadReceitaActivity.this,
                "tcc.posjava.unitri.edu.br.med4u",
                getOutputMediaFile());

        Log.d(TAG, "Tirou foto");




        intent.putExtra(MediaStore.EXTRA_OUTPUT, file);


        startActivityForResult(intent, 100);
    }

    private static File getOutputMediaFile() {

        File mediaStorageDir = new File(Environment.getExternalStoragePublicDirectory(
                Environment.DIRECTORY_PICTURES), "Med4U");

        if (!mediaStorageDir.exists()) {

            if (!mediaStorageDir.mkdirs()) {
                Log.d(TAG, "Ocorreu um erro ao criar o diretório de imagens");
                return null;
            }
        }

        String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
        /*file = ajustaFoto(file);*/
        return new File(mediaStorageDir.getPath() + File.separator + "IMG_" + timeStamp + ".jpg");
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {

        if (requestCode == 100) {
            if (resultCode == RESULT_OK) {
                imageView.setImageURI(file);
            }
        }
    }
}

