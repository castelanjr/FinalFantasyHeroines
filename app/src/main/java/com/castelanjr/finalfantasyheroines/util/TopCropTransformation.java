package com.castelanjr.finalfantasyheroines.util;

import android.graphics.Bitmap;
import android.graphics.Matrix;
import android.widget.ImageView;

import com.squareup.picasso.Transformation;

/**
 * Created by angelocastelanjr on 5/31/15.
 */
public class TopCropTransformation implements Transformation {

    private ImageView target;

    public TopCropTransformation(ImageView target) {
        this.target = target;
    }

    @Override
    public Bitmap transform(Bitmap result) {
        int inWidth = result.getWidth();
        int inHeight = result.getHeight();
        float widthRatio = target.getWidth() / (float) inWidth;
        float heightRatio = target.getHeight() / (float) inHeight;
        int drawWidth = inWidth;
        int drawHeight = inHeight;
        float scale;
        int drawX = 0;
        Matrix matrix = new Matrix();
        if (widthRatio > heightRatio) {
            scale = widthRatio;
            drawHeight = (int) Math.ceil(inHeight * (heightRatio / widthRatio));
        } else {
            scale = heightRatio;
            int newSize = (int) Math.ceil(inWidth * (widthRatio / heightRatio));
            drawX = (inWidth - newSize) / 2;
            drawWidth = newSize;
        }
        matrix.preScale(scale, scale);

        if (drawWidth <= 0) {
            return result;
        }

        Bitmap newResult = Bitmap.createBitmap(result, drawX, 0, drawWidth, drawHeight, matrix, true);
        if (newResult != result) {
            result.recycle();
            result = newResult;
        }

        return result;
    }

    @Override
    public String key() {
        return "" + target.getWidth() + target.getHeight();
    }

}
