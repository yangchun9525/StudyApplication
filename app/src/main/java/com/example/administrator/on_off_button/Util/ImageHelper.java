package com.example.administrator.on_off_button.Util;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.ColorMatrix;
import android.graphics.ColorMatrixColorFilter;
import android.graphics.Paint;

/**
 * Created by Administrator on 2016/2/24.
 */
public class ImageHelper {
    /**三个参数分别为色相，饱和度，亮度
     * 色相为蓝色更蓝，红色更红
     * 饱和度，颜色的纯度，从0（灰）到100%（饱和）
     * 亮度：颜色的相对明暗程度
     * */
    public static Bitmap handleImageEffect(Bitmap bm,float hue,float saturation,float lum){
        Bitmap bmp = Bitmap.createBitmap(bm.getWidth(),bm.getHeight(), Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(bmp);
        Paint paint = new Paint(Paint.ANTI_ALIAS_FLAG);
        //设置色相
        ColorMatrix hueMatrix = new ColorMatrix();
        //0</code> correspond to a rotation around the RED color
        hueMatrix.setRotate(0, hue);
        hueMatrix.setRotate(1, hue);
        hueMatrix.setRotate(2, hue);
        //设置饱和度
        ColorMatrix saturationMatrix = new ColorMatrix();
        saturationMatrix.setSaturation(saturation);
        //设置亮度
        ColorMatrix lumMatrix = new ColorMatrix();
        lumMatrix.setScale(lum,lum,lum,1);
        //执行
        ColorMatrix imageMatrix = new ColorMatrix();
        imageMatrix.postConcat(hueMatrix);
        imageMatrix.postConcat(saturationMatrix);
        imageMatrix.postConcat(lumMatrix);

        paint.setColorFilter(new ColorMatrixColorFilter(imageMatrix));
        canvas.drawBitmap(bm,0,0,paint);
        return bmp;
    }
    /**
     * 用于创建底片效果特效
     */
    public static Bitmap handleImageNegative(Bitmap bm) {
        //bm.getWidth 返回的就是bm的像素点
        int width = bm.getWidth();
        int height = bm.getHeight();
        int color;
        //用于保存当前像素点的r,g,b,a分量
        int r, g, b, a;

        Bitmap bmp = Bitmap.createBitmap(width, height
                , Bitmap.Config.ARGB_8888);
        //保存像素点数组到图像
        int[] oldPx = new int[width * height];
        int[] newPx = new int[width * height];
        bm.getPixels(oldPx, 0, width, 0, 0, width, height);

        for (int i = 0; i < width * height; i++) {
            color = oldPx[i];
            r = Color.red(color);
            g = Color.green(color);
            b = Color.blue(color);
            a = Color.alpha(color);

            r = 255 - r;
            g = 255 - g;
            b = 255 - b;

            if (r > 255) {
                r = 255;
            } else if (r < 0) {
                r = 0;
            }
            if (g > 255) {
                g = 255;
            } else if (g < 0) {
                g = 0;
            }
            if (b > 255) {
                b = 255;
            } else if (b < 0) {
                b = 0;
            }
            newPx[i] = Color.argb(a, r, g, b);
        }
        bmp.setPixels(newPx, 0, width, 0, 0, width, height);
        return bmp;
    }
    /**
     * 用于创建老照片效果特效
     */
    public static Bitmap handleImagePixelsOldPhoto(Bitmap bm) {
        Bitmap bmp = Bitmap.createBitmap(bm.getWidth(), bm.getHeight(),
                Bitmap.Config.ARGB_8888);
        int width = bm.getWidth();
        int height = bm.getHeight();
        int color = 0;
        int r, g, b, a, r1, g1, b1;

        int[] oldPx = new int[width * height];
        int[] newPx = new int[width * height];

        bm.getPixels(oldPx, 0, bm.getWidth(), 0, 0, width, height);
        for (int i = 0; i < width * height; i++) {
            color = oldPx[i];
            a = Color.alpha(color);
            r = Color.red(color);
            g = Color.green(color);
            b = Color.blue(color);

            r1 = (int) (0.393 * r + 0.769 * g + 0.189 * b);
            g1 = (int) (0.349 * r + 0.686 * g + 0.168 * b);
            b1 = (int) (0.272 * r + 0.534 * g + 0.131 * b);

            if (r1 > 255) {
                r1 = 255;
            }
            if (g1 > 255) {
                g1 = 255;
            }
            if (b1 > 255) {
                b1 = 255;
            }

            newPx[i] = Color.argb(a, r1, g1, b1);
        }
        bmp.setPixels(newPx, 0, width, 0, 0, width, height);
        return bmp;
    }
    /**
     * 用于创建浮雕效果特效
     */
    public static Bitmap handleImagePixelsRelief(Bitmap bm) {
        Bitmap bmp = Bitmap.createBitmap(bm.getWidth(), bm.getHeight(),
                Bitmap.Config.ARGB_8888);
        int width = bm.getWidth();
        int height = bm.getHeight();
        int color = 0, colorBefore = 0;
        int a, r, g, b;
        int r1, g1, b1;

        int[] oldPx = new int[width * height];
        int[] newPx = new int[width * height];

        bm.getPixels(oldPx, 0, bm.getWidth(), 0, 0, width, height);
        for (int i = 1; i < width * height; i++) {
            colorBefore = oldPx[i - 1];
            a = Color.alpha(colorBefore);
            r = Color.red(colorBefore);
            g = Color.green(colorBefore);
            b = Color.blue(colorBefore);

            color = oldPx[i];
            r1 = Color.red(color);
            g1 = Color.green(color);
            b1 = Color.blue(color);

            r = (r - r1 + 127);
            g = (g - g1 + 127);
            b = (b - b1 + 127);
            if (r > 255) {
                r = 255;
            }
            if (g > 255) {
                g = 255;
            }
            if (b > 255) {
                b = 255;
            }
            newPx[i] = Color.argb(a, r, g, b);
        }
        bmp.setPixels(newPx, 0, width, 0, 0, width, height);
        return bmp;
    }
}
