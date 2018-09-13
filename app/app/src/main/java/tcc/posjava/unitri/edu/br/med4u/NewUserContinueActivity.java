package tcc.posjava.unitri.edu.br.med4u;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.nfc.Tag;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.v4.content.FileProvider;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;

public class NewUserContinueActivity extends AppCompatActivity {

    public static final int CODIGO_CAMERA = 567;
    private ImageView campoFoto = findViewById(R.id.formulario_foto);
    private String caminhoFoto;
    public AlertDialog alerta;
    private Uri file;
    private static String TAG = "NewUserContinueActivity";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_user_continue);

        Button photoButton = findViewById(R.id.btPhotoNW);
        photoButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                /*alerta.setTitle("Teste");
                alerta.setMessage("Clique ok");
                alerta.show();*/
                Intent intentCamera = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                caminhoFoto = getExternalFilesDir(null) + "/" + System.currentTimeMillis() + ".jpg";
                File arquivoFoto = new File(caminhoFoto);
                file = FileProvider.getUriForFile(NewUserContinueActivity.this, "tcc.posjava.unitri.edu.br.med4u", getOutputMediaFile());
                intentCamera.putExtra(MediaStore.EXTRA_OUTPUT, file);
                startActivityForResult(intentCamera, 100);
            }
        });
    }

    private static File getOutputMediaFile(){

        File mediaStorageDir = new File(Environment.getExternalStoragePublicDirectory(
                Environment.DIRECTORY_PICTURES), "med4u");

        if (!mediaStorageDir.exists()){

            if (!mediaStorageDir.mkdirs()){
                Log.d(TAG, "Ocorreu um erro ao criar o diretório de imagens");
                return null;
            }
        }

        String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
        return new File(mediaStorageDir.getPath() + File.separator + "IMG_"+ timeStamp + ".jpg");
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (resultCode == Activity.RESULT_OK) {
            if (requestCode == CODIGO_CAMERA) {
                carregaImagem(caminhoFoto);
            }
        }

    }

    public void carregaImagem(String caminhoFoto) {
        if (caminhoFoto != null) {
            Bitmap bitmap = BitmapFactory.decodeFile(caminhoFoto);
            Bitmap bitmapReduzido = Bitmap.createScaledBitmap(bitmap, 300, 300, true);
            campoFoto.setImageBitmap(bitmapReduzido);
            campoFoto.setScaleType(ImageView.ScaleType.FIT_XY);
            campoFoto.setTag(caminhoFoto);
    }
    }
}
