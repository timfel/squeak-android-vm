package org.squeak.android;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.view.View;
import android.view.MotionEvent;
import android.view.KeyEvent;

import org.squeak.android.SqueakVM;

public class SqueakView extends View {
	SqueakVM vm;
	int bits[];
	int width;
	int height;
	int depth;
	int dirty[];
	Paint paint;

	public SqueakView(Context context) {
		super(context);
		width = 800;
		height = 600;
		depth = 32;
		bits = new int[800*600];
    	dirty = new int[4];
    	paint = new Paint();
	}

	public boolean onKeyEvent(KeyEvent event) {
		System.out.println("Key Event: " + event);
		return true;
	}

	public boolean onTouchEvent(MotionEvent event) {
		int buttons = 0;
		int modifiers = 0;

		System.out.println("MotionEvent: " + event);
		switch(event.getAction()) {
			case MotionEvent.ACTION_DOWN: 
				buttons = 4;
				break;
			case MotionEvent.ACTION_MOVE: 
				buttons = 4;
				break;
			case MotionEvent.ACTION_UP: 
				buttons = 0;
				break;
			default:
				System.out.println("Unsupported motion action: " + event.getAction());
				return false;
		}
		vm.sendEvent(1 /* EventTypeMouse */, 0 /* timestamp */, 
					(int)event.getX(), (int)event.getY(), 
					buttons, modifiers, 0, 0);
		vm.interpret();
		invalidate();
		return true;
	}

	/**
     * Render me
     * 
     * @see android.view.View#onDraw(android.graphics.Canvas)
     */
    @Override
    protected void onDraw(Canvas canvas) {
    	vm.updateDisplay(bits, width, height, depth, dirty);
        super.onDraw(canvas);
        canvas.drawColor(-1);
    	canvas.drawBitmap(bits, 0, width, 0, 0, width, height, false, paint);
    }
}
