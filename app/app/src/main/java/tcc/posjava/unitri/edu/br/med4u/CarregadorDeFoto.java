package tcc.posjava.unitri.edu.br.med4u;


import android.content.ContentResolver;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Matrix;
import android.media.ExifInterface;
import android.util.Log;

import java.io.IOException;


public class CarregadorDeFoto {
    private static String TAG = "CarregadorDeFoto";

    public static Bitmap carrega(String caminhoFoto){

        int codigoOrientacao = 0;
        Bitmap bitmap = null;
        Log.d(TAG, "Carregador de foto iniciada");
        Log.d(TAG, "Caminho da foto no carregador: " + caminhoFoto);
        Log.d(TAG, "exif: new exif");
            try {
                Log.d(TAG, "enttrou no try");
                
                ExifInterface exif = new ExifInterface(caminhoFoto);

                Log.d(TAG, "codigo orientacao: " + codigoOrientacao);
                codigoOrientacao = exif.getAttributeInt(ExifInterface.TAG_ORIENTATION, -1);
                Log.d(TAG, "codigo orientacao: " + codigoOrientacao);
                Log.d(TAG, "criou exif");
                /*String orientacao = exif.getAttribute(ExifInterface.TAG_ORIENTATION);
                Log.d(TAG, "orientacao: " + orientacao);
                codigoOrientacao = Integer.parseInt(orientacao);
                Log.d(TAG, "orientacao int: " + codigoOrientacao);*/
            }catch (IOException e) {
                e.getMessage();
            }
            switch (codigoOrientacao) {
                case ExifInterface.ORIENTATION_NORMAL:
                    return abreFotoERotaciona(caminhoFoto, 0);
                case ExifInterface.ORIENTATION_ROTATE_90:
                    return abreFotoERotaciona(caminhoFoto, 90);
                case ExifInterface.ORIENTATION_ROTATE_180:
                    return abreFotoERotaciona(caminhoFoto, 180);
                case ExifInterface.ORIENTATION_ROTATE_270:
                    return abreFotoERotaciona(caminhoFoto, 270);
                default:
                    return bitmap;
            }
    }

    private static Bitmap abreFotoERotaciona(String caminhoFoto, int angulo) {
        // Abre o bitmap a partir do caminho da foto
        Bitmap bitmap = BitmapFactory.decodeFile(caminhoFoto);

        // Prepara a operação de rotação com o ângulo escolhido
        Matrix matrix = new Matrix();
        matrix.postRotate(angulo);

        // Cria um novo bitmap a partir do original já com a rotação aplicada
        return Bitmap.createBitmap(bitmap, 0, 0,
        bitmap.getWidth(), bitmap.getHeight(),
        matrix, true);
    }
}
