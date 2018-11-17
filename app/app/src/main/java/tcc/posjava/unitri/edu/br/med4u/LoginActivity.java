package tcc.posjava.unitri.edu.br.med4u;

import android.app.Activity;
import android.app.DialogFragment;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.app.AlertDialog;
import android.content.DialogInterface;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class LoginActivity extends Activity {

    private static String TAG = "LoginActivity";
    private String nomeUsuario = "";
    private String senhaUsuario = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        TextView newUser = findViewById(R.id.tvNewUserLogin);
        newUser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent iNewUser = new Intent(LoginActivity.this, NewUserActivity.class);
                startActivity(iNewUser);
            }
        });

        Button login = findViewById(R.id.btLogin);

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EditText editTextUsr = findViewById(R.id.etUserLogin);
                nomeUsuario = editTextUsr.getText().toString();
                EditText editTextPsw = findViewById(R.id.etPassLogin);
                senhaUsuario = editTextPsw.getText().toString();
                String url = "http://med4u.herokuapp.com/loginApi";
                RequestQueue queue = Volley.newRequestQueue(LoginActivity.this);
                JSONObject postRequest = new JSONObject();
                try {
                    postRequest.put("username", nomeUsuario) ;
                    postRequest.put("password", senhaUsuario);

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
                                    if ((response.toString().contains(("Authorization\":\"Bearer eyJhbGciOiJIUzUxMiJ9.")))) {
                                        Intent cadRec = new Intent(LoginActivity.this, CadReceitaActivity.class);
                                        startActivity(cadRec);
                                    } else {
                                        showDialog("Informação", "Nome de usuário ou senha inválidos.");
                                    }
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
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_principal, menu);

        return true;

    }

    private void showDialog(String title, String message) {
        AlertDialog alertDialog = new AlertDialog.Builder(LoginActivity.this).create();
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

    private String toString(Object palavra){
        return palavra + "";
    }
}
