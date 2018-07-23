package tcc.posjava.unitri.edu.br.med4u;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

public class LoginActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

    }

    public void newUser() {
        TextView newuser = findViewById(R.id.tvNewUserLogin);
    }



}
