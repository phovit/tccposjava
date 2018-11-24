package tcc.posjava.unitri.edu.br.med4u;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.content.DialogInterface;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.GraphRequest;
import com.facebook.GraphResponse;
import com.facebook.login.LoginManager;
import com.facebook.login.LoginResult;
import com.facebook.login.widget.LoginButton;
import com.google.firebase.iid.FirebaseInstanceId;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class LoginActivity extends Activity {

    private static String TAG = "LoginActivity";
    private String nomeUsuario = "";
    private String senhaUsuario = "";
    /*private CallbackManager callbackManager;*/
    private LoginButton loginButton;

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
                    postRequest.put("username", nomeUsuario);
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
                                        Toast.makeText(LoginActivity.this, "Bem vindo " + nomeUsuario, Toast.LENGTH_LONG).show();
                                        Intent cadMed = new Intent(LoginActivity.this, CadMedicinesActivity.class);
                                        cadMed.putExtra("autorizacao", response.toString());


                                        try {
                                            updateFirebaseToken(FirebaseInstanceId.getInstance().getToken(), nomeUsuario, response.getString("Authorization"));
                                        } catch (JSONException e) {
                                            e.printStackTrace();
                                        }
                                        startActivity(cadMed);
                                        try {
                                            updateFirebaseToken(FirebaseInstanceId.getInstance().getToken(), nomeUsuario, response.getString("Authorization"));
                                        } catch (JSONException e) {
                                            e.printStackTrace();
                                        }
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

        loginButton = (LoginButton) findViewById(R.id.login_button);
        loginButton.setReadPermissions("email");
        // If using in a fragment
        /*loginButton.setFragment(this);*/

        CallbackManager callbackManager = CallbackManager.Factory.create();

        // Callback registration
        loginButton.registerCallback(callbackManager, new FacebookCallback<LoginResult>() {
            @Override
            public void onSuccess(LoginResult loginResult) {
                GraphRequest request = GraphRequest.newMeRequest(loginResult.getAccessToken(), new GraphRequest.GraphJSONObjectCallback() {
                    @Override
                    public void onCompleted(JSONObject object, GraphResponse response) {

                        try {
                            String name = object.getString("name");
                            String email = object.getString("email");
                            Toast.makeText(getApplicationContext(), "Name: " + name + " Email: " + email, Toast.LENGTH_LONG).show();
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }

                    }
                });
            }

            @Override
            public void onCancel() {
                // App code
            }

            @Override
            public void onError(FacebookException exception) {
                // App code
            }
        });


        callbackManager = CallbackManager.Factory.create();

        LoginManager.getInstance().registerCallback(callbackManager,
                new FacebookCallback<LoginResult>() {
                    @Override
                    public void onSuccess(LoginResult loginResult) {
                        // App code
                    }

                    @Override
                    public void onCancel() {
                        // App code
                    }

                    @Override
                    public void onError(FacebookException exception) {
                        // App code
                    }
                });

/*        TextView recoverUser = findViewById(R.id.tvRecoverPass);
        recoverUser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent recUser = new Intent(LoginActivity.this, RecoverUserActivity.class);
                startActivity(recUser);
            }
        });*/
    }

    public void updateFirebaseToken(final String token, String username, final String auth) {
        String url = "http://med4u.herokuapp.com/users/updateFirebaseTokenByUsername/" + username;
        RequestQueue queue = Volley.newRequestQueue(LoginActivity.this);

        StringRequest postRequest = new StringRequest(Request.Method.POST, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        Log.d(TAG, "Sucesso ao chamar a API updateFirebaseToken");
                    }
                },

                new Response.ErrorListener()
                {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Log.d(TAG, "Ocorreu um erro ao chamar a API " + error);
                    }
                }
        ) {
            @Override
            protected Map<String, String> getParams() {
                Map<String, String> params = new HashMap<String, String>();
                params.put("token", token);

                return params;
            }

            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                Map<String, String> params = new HashMap<String, String>();
                params.put("Authorization", auth);

                return params;
            }
        };
        queue.add(postRequest);
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

    private String toString(Object palavra) {
        return palavra + "";
    }
}
