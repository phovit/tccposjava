package tcc.posjava.unitri.edu.br.med4u;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.media.RingtoneManager;
import android.net.Uri;
import android.os.Build;
import android.support.v4.app.NotificationCompat;
import android.support.v4.content.ContextCompat;
import android.util.Log;

import com.google.firebase.messaging.FirebaseMessagingService;
import com.google.firebase.messaging.RemoteMessage;
import com.squareup.picasso.Picasso;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.Map;
import java.util.Random;

public class PushMessagingService extends FirebaseMessagingService {

    static String TAG = PrincipalActivity.class.getName();
    String body, image, title = null;
    Random random = new Random();
    int notifyID = random.nextInt(9999 - 1000) + 1000;
    JSONObject jsonObject;

    String NOTIFICATION_CHANNEL_ID = "CHANNEL_01";
    String NOTIFICATION_CHANNEL_DESCRIPTION = "CHANNEL";


    /**
     * Get the push notification event
     *
     * @param  remoteMessage  Remote message from push notification
     */
    @Override
    public void onMessageReceived(RemoteMessage remoteMessage) {

        if (remoteMessage.getData().size() > 0) {

            jsonObject = parseRemoteMessageToJson(remoteMessage);
            Log.d(TAG, "Push received from " + remoteMessage.getFrom());
            showNotification(jsonObject);
        }
    }

    /**
     * Parse the remote notification to JSON object
     *
     * @param  remoteMessage The push notification message
     * @return jsonObject The JSON object to remoteMessage
     */
    private JSONObject parseRemoteMessageToJson(RemoteMessage remoteMessage) {

        Map<String, String> params = remoteMessage.getData();
        jsonObject = new JSONObject(params);
        Log.d("Notification JSON:", jsonObject.toString());
        return jsonObject;
    }

    /**
     * Show the push notification
     *
     * @param  jsonObject The message title
     */
    private void showNotification(JSONObject jsonObject) {

        Log.d(TAG, "Starting process to showing notification");

        Intent intent = new Intent(this, PrincipalActivity.class);

        try {

            if (!jsonObject.isNull("title")) {

                title = jsonObject.getString("title");
                intent.putExtra("EXTRA_TITLE", title);
            }
            if (!jsonObject.isNull("body")) {

                body = jsonObject.getString("body");
                intent.putExtra("EXTRA_BODY", body);
            }
            if (!jsonObject.isNull("body")) {

                image = jsonObject.getString("image");
                intent.putExtra("EXTRA_IMG", image);
            }


        } catch (JSONException e) {

            Log.e(TAG, "Error getting JSON field \n" +e);
        }

        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);

        PendingIntent pendingIntent =
                PendingIntent.getActivity(this, 0,
                        intent,
                        PendingIntent.FLAG_UPDATE_CURRENT | PendingIntent.FLAG_ONE_SHOT);

        Uri defaultSoundUri = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);

        NotificationCompat.Builder notificationBuilder = new NotificationCompat.Builder(this, NOTIFICATION_CHANNEL_ID);

        NotificationManager notificationManager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);


        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {

            notificationBuilder.setSmallIcon(R.mipmap.ic_launcher);
            notificationBuilder.setColor(ContextCompat.getColor(getApplicationContext(), android.R.color.transparent));

        } else {

            notificationBuilder.setSmallIcon(R.mipmap.ic_launcher);
        }

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {

            NotificationChannel notificationChannel = new NotificationChannel(NOTIFICATION_CHANNEL_ID, "NOTIFICATIONS", NotificationManager.IMPORTANCE_DEFAULT);

            notificationChannel.setDescription(NOTIFICATION_CHANNEL_DESCRIPTION);
            notificationChannel.enableLights(true);
            notificationChannel.setLightColor(Color.WHITE);
            notificationChannel.setVibrationPattern(new long[]{0, 1000, 500, 1000});
            notificationChannel.enableVibration(true);
            notificationManager.createNotificationChannel(notificationChannel);
        }

        notificationBuilder.setContentTitle(title);
        notificationBuilder.setContentText(body);
        notificationBuilder.setAutoCancel(true);
        notificationBuilder.setSound(defaultSoundUri);
        notificationBuilder.setPriority(NotificationCompat.PRIORITY_HIGH);
        notificationBuilder.setVibrate(new long[]{700, 700});
        notificationBuilder.setContentIntent(pendingIntent);



        try {
            Bitmap bitmap = Picasso.with( this ).load( image ).get();
            notificationBuilder.setLargeIcon(bitmap);
            notificationBuilder.setStyle( new NotificationCompat.BigPictureStyle().bigPicture( bitmap ));
        }
        catch (IOException e) {
            Log.e(TAG, "Error getting image from URL " +e);
        }



        notificationManager.notify(notifyID, notificationBuilder.build());
    }
}