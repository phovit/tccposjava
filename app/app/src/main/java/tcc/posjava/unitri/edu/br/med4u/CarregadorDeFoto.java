package tcc.posjava.unitri.edu.br.med4u;


import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Matrix;
import android.media.ExifInterface;
import android.util.Log;

import java.io.IOException;


public class CarregadorDeFoto {
    private static String TAG = "CarregadorDeFoto";

        public static Bitmap carrega(String caminhoFoto)  {
            Bitmap bitmap = null;
            Log.d(TAG, "Carregador de foto iniciada");
            Log.d(TAG, "Caminho da foto: " + caminhoFoto);
            Log.d(TAG, "exif: new exif");
            ExifInterface exif = null;
            try {
                Log.d(TAG, "exif: new exif dentro do try");
                exif = new ExifInterface(caminhoFoto);
            } catch (IOException e) {
                Log.d(TAG, "exif: erro");
                e.printStackTrace();
            }
            /*int orientacao = exif.getAttributeInt(ExifInterface.TAG_ORIENTATION, ExifInterface.ORIENTATION_UNDEFINED);*/
            int orientacao = 90;
            Log.d(TAG, "orientacao: " + orientacao);

            switch (orientacao) {
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
