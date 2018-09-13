package tcc.posjava.unitri.edu.br.med4u;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

public class LoginActivity extends AppCompatActivity {

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
    }
}
