package tcc.posjava.unitri.edu.br.med4u;

import android.Manifest;
import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v4.content.FileProvider;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class CadReceitaActivity extends AppCompatActivity {

    private Button takePictureButton;
    private ImageView imageView;
    private Uri file;

    private static String TAG = "CadReceitaActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cad_receita);

        takePictureButton = findViewById(R.id.button_image);
        imageView = findViewById(R.id.ivCadRec);

        if (ContextCompat.checkSelfPermission(this, Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED) {
            takePictureButton.setEnabled(false);
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.CAMERA, Manifest.permission.WRITE_EXTERNAL_STORAGE}, 0);
        }

        /*ImageView campoFoto = findViewById(R.id.ivCadRec);
        Bitmap bitmap = CarregadorDeFoto.carrega("foto_supimpa.jpg");
        campoFoto.setImageBitmap(bitmap);*/

        Button cadRec = findViewById(R.id.btCadaRec);
        cadRec.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // primeiro cria a intenção
                Intent it = new Intent("EXECUTAR_ALARME");
                PendingIntent p = PendingIntent.getBroadcast(CadReceitaActivity.this, 0, it, 0);
                // precisamos pegar agora + 10segundos
                Calendar c = Calendar.getInstance();
                c.setTimeInMillis(System.currentTimeMillis());
                c.add(Calendar.SECOND, 10); // +10 segundos
                // agendar o alarme
                AlarmManager alarme = (AlarmManager) getSystemService(ALARM_SERVICE);
                long time = c.getTimeInMillis();
                alarme.set(AlarmManager.RTC_WAKEUP, time, p);
                // debug:
                Log.i("Alarme", "Alarme agendado!");
            }
        });

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
                "unitri.edu.br.camera",
                getOutputMediaFile());


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

