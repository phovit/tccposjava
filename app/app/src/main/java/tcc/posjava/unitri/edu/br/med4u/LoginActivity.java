package tcc.posjava.unitri.edu.br.med4u;

import android.app.DialogFragment;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.app.AlertDialog;
import android.content.DialogInterface;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class LoginActivity extends AppCompatActivity {

    private static String TAG = "LoginActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        TextView newUser = findViewById(R.id.tvNewUserLogin);
        newUser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent iNewUser = new Intent(LoginActivity.this, NewUserContinueActivity.class);
                startActivity(iNewUser);
            }
        });

        Button login = findViewById(R.id.btLogin);
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String url = "http://med4u.herokuapp.com/login";
                RequestQueue queue = Volley.newRequestQueue(LoginActivity.this);
                JSONObject postRequest = new JSONObject();
                try {
                    postRequest.put("username", "admin");
                    postRequest.put("password", "admin");

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
                                Log.d(TAG, "API Response: " + response.toString());
                                String message = response.optString("message");
                                showDialog("Informação", message);
                            }
                        },

                        /* Callback chamado em caso de erro */

                        new Response.ErrorListener() {
                            @Override
                            public void onErrorResponse(VolleyError error) {

                                Log.d(TAG, "Ocorreu um erro ao chamar a API " + error);
                            }
                        }) {
                    @Override
                    protected Map<String, String> getParams() throws AuthFailureError {
                        Map<String, String> params = new HashMap<String, String>();
                        //add params <key,value>
                        return params;
                    }

                    @Override
                    public Map<String, String> getHeaders() throws AuthFailureError {

                        Map<String, String> headers = new HashMap<String, String>();
                        String auth = "Basic MzgxNTc5ZmEtZDI0MC00Mzg3LTkyNTMtZWY2YjgwYTdhMWEwOmM4NDM4M2Y0LTJiMDgtNGJiYy04MjQwLWI0YjQ5YTFlYWQzZQ==";
                        headers.put("Authorization", auth);
                        return headers;
                    }
                };

                queue.add(jsonObjReq);
            }
        });
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
}
