package tcc.posjava.unitri.edu.br.med4u;

import android.Manifest;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Matrix;
import android.media.ExifInterface;
import android.net.Uri;
import android.nfc.Tag;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v4.content.FileProvider;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class NewUserContinueActivity extends AppCompatActivity {

    private Button takePictureButton;
    private ImageView imageView;
    private Uri file;
    private static String TAG = "NewUserContinueActivity";
    String url = "http://med4u.herokuapp.com/users";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_user_continue);
        ActionBar actionbar = getSupportActionBar();
        actionbar.setTitle("Med4U");

        takePictureButton = findViewById(R.id.btPhotoNW);
        imageView = findViewById(R.id.imageview);

        if (ContextCompat.checkSelfPermission(this, Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED) {
            takePictureButton.setEnabled(false);
            ActivityCompat.requestPermissions(this, new String[] { Manifest.permission.CAMERA, Manifest.permission.WRITE_EXTERNAL_STORAGE }, 0);
        }

        Button btCadNU = findViewById(R.id.buttonCad);
        btCadNU.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                EditText etNameNU = findViewById(R.id.etNameNewUser);
                String name = etNameNU.getText().toString();
                EditText etUserNU = findViewById(R.id.etUserNewUser);
                String userName = etUserNU.getText().toString();
                EditText etCpf = findViewById(R.id.etCPFNewUserCad);
                String cpf = etCpf.getText().toString();
                EditText etEmail = findViewById(R.id.etUserEmailCad);
                String email = etEmail.getText().toString();
                /*EditText etPhone = findViewById(R.id.etPhoneNewUser);
                String phone = etPhone.getText().toString();*/
                /*EditText etCell = findViewById(R.id.etCellNewUser);
                String cell = etCell.getText().toString();*/
                EditText etPass = findViewById(R.id.etPassNewUser);
                String pass = etPass.getText().toString();
                /*EditText etPass2 = findViewById(R.id.etPassNewUser2);
                String pass2 = etPass2.getText().toString();*/


                RequestQueue queue = Volley.newRequestQueue(NewUserContinueActivity.this);

                JSONObject postRequest = new JSONObject();

                try {
                    postRequest.put("name", name);
                    postRequest.put("username", userName) ;
                    postRequest.put("cpf", cpf) ;
                    postRequest.put("email", email) ;
                    postRequest.put("password", pass) ;

                } catch (JSONException e) {
                    e.printStackTrace();
                }
                JsonObjectRequest jsonObjReq = new JsonObjectRequest(

                        Request.Method.POST,
                        url,
                        postRequest,
                        new Response.Listener<JSONObject>() {
                            @Override
                            public void onResponse(JSONObject response) {
                                if (response == null) {
                                    Log.d(TAG, "API Response: null");
                                } else {

                                    Log.d(TAG, "API Response: " + response.toString());

                                    showDialog("Resposta", response.toString());

                                    /*if ((response.toString().contains(("Authorization\":\"Bearer eyJhbGciOiJIUzUxMiJ9.")))) {
                                        Intent cadRec = new Intent(NewUserContinueActivity.this, CadReceitaActivity.class);
                                        startActivity(cadRec);*/
                                }

                            }

                        },

                        /* Callback chamado em caso de erro */

                        new Response.ErrorListener() {
                            @Override
                            public void onErrorResponse(VolleyError error) {

                                Log.d(TAG, "Ocorreu um erro ao chamar a API " + error);
                            }
                        }) {
                };
                queue.add(jsonObjReq);

                etCpf.setText("");
                etEmail.setText("");
                etNameNU.setText("");
                etPass.setText("");
                etUserNU.setText("");

                Intent login = new Intent(NewUserContinueActivity.this, LoginActivity.class);
                startActivity(login);
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
                NewUserContinueActivity.this,
                "tcc.posjava.unitri.edu.br.med4u",
                getOutputMediaFile());
        intent.putExtra(MediaStore.EXTRA_OUTPUT, file);

        startActivityForResult(intent, 100);
    }

    private static File getOutputMediaFile(){

        File mediaStorageDir = new File(Environment.getExternalStoragePublicDirectory(
                Environment.DIRECTORY_PICTURES), "MED4U");

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
    protected void onActivityResult(int requestCode, int resultCode, Intent data){
        if (requestCode == 100) {
            if (resultCode == RESULT_OK) {
                Bitmap bitmap = CarregadorDeFoto.carrega(file.getPath());
                imageView.setImageURI(file);
                imageView.setImageBitmap(bitmap);
            }
        }
    }

    private void showDialog(String title, String message) {
        android.support.v7.app.AlertDialog alertDialog = new android.support.v7.app.AlertDialog.Builder(NewUserContinueActivity.this).create();
        alertDialog.setTitle(title);
        alertDialog.setMessage(message);
        alertDialog.setButton(android.support.v7.app.AlertDialog.BUTTON_POSITIVE, "OK",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                });
        alertDialog.show();
    }
}