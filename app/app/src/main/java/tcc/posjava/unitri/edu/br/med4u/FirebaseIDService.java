package tcc.posjava.unitri.edu.br.med4u;

import android.util.Log;

import com.google.firebase.iid.FirebaseInstanceId;
import com.google.firebase.iid.FirebaseInstanceIdService;

public class FirebaseIDService extends FirebaseInstanceIdService {

    static String TAG = PrincipalActivity.class.getName();

    @Override
    public void onTokenRefresh() {
        // Get updated InstanceID token.
        String refreshedToken = FirebaseInstanceId.getInstance().getToken();

        if(BuildConfig.DEBUG) {

            Log.d(TAG, "Refreshed token: " + refreshedToken);
        }
    }
}