package com.example.asg02.view;

import android.app.Dialog;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.graphics.Rect;
import android.graphics.drawable.ColorDrawable;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import com.example.asg02.R;
import com.google.zxing.WriterException;

import java.io.ByteArrayOutputStream;
import java.util.Base64;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.EncodeHintType;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.WriterException;
import com.google.zxing.common.BitMatrix;

public class Utils {
    public static TextWatcher afterEditTextChanged(EditText editText, View.OnClickListener listener) {
        return new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                listener.onClick(editText);
            }
        };
    }
    public static Dialog createAskingDialog(String message, Context context, View.OnClickListener onConfirmListener) {
        // create dialog
        Dialog dialog = new Dialog(context);
        dialog.setCancelable(false);
        dialog.setContentView(R.layout.dialog_asking_for_user_opinions);
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));

        TextView content = dialog.findViewById(R.id.content);
        content.setText(message);

        Button cancel = dialog.findViewById(R.id.cancel_action);
        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });

        Button continue_ = dialog.findViewById(R.id.continue_action);
        continue_.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onConfirmListener.onClick(v);
                dialog.dismiss();
            }
        });

        return dialog;
    }

    public static String convertIntTimeToString (int durationMins) {
        int hours = durationMins / 60;
        int mins = durationMins % 60;
        String hourString = (hours == 0) ? "" : hours + " giờ ";
        String minuteString = (mins == 0) ? "" : mins + " phút";
        return hourString + minuteString;
    }

    public static String encodeImage(Bitmap bitmap) {
        int previewWidth = 400;
        int previewHeight = bitmap.getHeight() * previewWidth / bitmap.getWidth();
        Bitmap previewBitmap = Bitmap.createScaledBitmap(bitmap, previewWidth, previewHeight, false);
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        previewBitmap.compress(Bitmap.CompressFormat.JPEG, 100, byteArrayOutputStream);
        byte[] bytes = byteArrayOutputStream.toByteArray();
        return Base64.getEncoder().encodeToString(bytes);
    }

    public static Bitmap decodeBitmap(String uri) {
        byte[] bytes = Base64.getDecoder().decode(uri);
        return BitmapFactory.decodeByteArray(bytes, 0, bytes.length);
    }

    public static Bitmap cropToCircle(Bitmap bitmap) {
        int width = bitmap.getWidth();
        int height = bitmap.getHeight();
        int radius = Math.min(width, height) / 2;

        Bitmap output = Bitmap.createBitmap(width, height, Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(output);
        Paint paint = new Paint();
        Rect rect = new Rect(0, 0, width, height);

        paint.setAntiAlias(true);
        canvas.drawARGB(0, 0, 0, 0);
        canvas.drawCircle(width / 2f, height / 2f, radius, paint);
        paint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.SRC_IN));
        canvas.drawBitmap(bitmap, rect, rect, paint);

        return output;
    }

    public static Bitmap cropToCircleWithBorder(Bitmap bitmap, int borderWidth, int borderColor) {
        int width = bitmap.getWidth();
        int height = bitmap.getHeight();
        int radius = Math.min(width, height) / 2;

        // Tạo một bitmap mới để chứa hình tròn và viền
        Bitmap output = Bitmap.createBitmap(width + borderWidth * 2, height + borderWidth * 2, Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(output);

        // Tạo một bitmap mới để chứa ảnh đã crop thành hình tròn
        Bitmap croppedBitmap = Bitmap.createBitmap(width, height, Bitmap.Config.ARGB_8888);
        Canvas croppedCanvas = new Canvas(croppedBitmap);

        // Crop ảnh thành hình tròn
        Paint paint = new Paint();
        paint.setAntiAlias(true);
        croppedCanvas.drawCircle(width / 2f, height / 2f, radius, paint);
        paint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.SRC_IN));
        croppedCanvas.drawBitmap(bitmap, 0, 0, paint);

        // Vẽ hình tròn ngoài với màu của viền
        Paint borderPaint = new Paint();
        borderPaint.setAntiAlias(true);
        borderPaint.setColor(borderColor);
        canvas.drawCircle((width + borderWidth * 2) / 2f, (height + borderWidth * 2) / 2f, radius + borderWidth, borderPaint);

        // Vẽ ảnh đã crop vào trong hình tròn
        canvas.drawBitmap(croppedBitmap, borderWidth, borderWidth, null);

        return output;
    }

    public static String createEmbedLinkFromYoutube(String youtubeLink) {
        String id = "https://www.youtube.com/embed/" + youtubeLink.substring(youtubeLink.indexOf("watch?v=") + 8);
        return "<iframe width=\"100%\" height=\"100%\" src=\"" + id + "\" title=\"YouTube video player\" frameborder=\"0\" allow=\"accelerometer; autoplay; clipboard-write; encrypted-media; gyroscope; picture-in-picture; web-share\" referrerpolicy=\"strict-origin-when-cross-origin\" allowfullscreen></iframe>";
    }

    public static Bitmap generateBarcode(String data, int width, int height) {
        Bitmap bitmap = Bitmap.createBitmap(width, height, Bitmap.Config.RGB_565);;
        if (data != null) {
            MultiFormatWriter mwriter = new MultiFormatWriter();

            try {
                BitMatrix bitMatrix = mwriter.encode(data, BarcodeFormat.CODE_128, width, height);
                for (int x = 0; x < width; x++) {
                    for (int y = 0; y < height; y++) {
                        bitmap.setPixel(x, y, bitMatrix.get(x, y) ? Color.BLACK : Color.WHITE);
                    }
                }
            } catch (WriterException e) {
                e.printStackTrace();
            }
        }
        return bitmap;
    }
}
