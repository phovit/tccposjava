
import android.util.Log;

import com.google.firebase.iid.FirebaseInstanceId;
import com.google.firebase.iid.FirebaseInstanceIdService;

import tcc.posjava.unitri.edu.br.med4u.BuildConfig;
import tcc.posjava.unitri.edu.br.med4u.PrincipalActivity;

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