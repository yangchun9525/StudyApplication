package com.example.administrator.on_off_button;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.view.View;

public class RoundRectView extends View {

	private Bitmap bitmap;
	private Bitmap mOut;
	private Paint mPaint;
	public RoundRectView(Context context, AttributeSet attrs, int defStyleAttr) {
		super(context, attrs, defStyleAttr);
		init();
	}

	public RoundRectView(Context context, AttributeSet attrs) {
		super(context, attrs);
		init();
	}

	public RoundRectView(Context context) {
		super(context);
		init();
	}
	private void init() {
		setLayerType(LAYER_TYPE_SOFTWARE, null);//禁止使用硬件加速
		bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.test);
		mOut = Bitmap.createBitmap(bitmap.getWidth(), bitmap.getHeight(), Bitmap.Config.ARGB_8888);
		Canvas canvas = new Canvas(mOut);
		mPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
		RectF rectF = new RectF(0, 0, bitmap.getWidth(), bitmap.getHeight());
		canvas.drawRoundRect(rectF, 135, 90, mPaint);
		mPaint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.SRC_IN));
		canvas.drawBitmap(bitmap, 0, 0, mPaint);
		mPaint.setXfermode(null);
	}
	@Override
	protected void onDraw(Canvas canvas) {
		canvas.drawBitmap(mOut, 0, 0, null);
//		RectF rectF = new RectF(0, 0, getWidth(), getHeight());
//		mPaint.setXfermode(new PorterDuffXfermode(Mode.SRC_IN));
//		canvas.drawBitmap(bitmap, null, rectF, mPaint);
	}
}
