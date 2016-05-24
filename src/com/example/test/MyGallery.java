package com.example.test;

import java.util.Timer;
import java.util.TimerTask;

import android.content.Context;
import android.os.Handler;
import android.os.Message;
import android.util.AttributeSet;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.widget.Gallery;

@SuppressWarnings("deprecation")
public class MyGallery extends Gallery {
	private static final int timeAnimation = 1;
	private final Handler mHandler = new Handler(){

		@Override
		public void handleMessage(Message msg) {
			super.handleMessage(msg);
			switch (msg.what) {
			case timeAnimation:
				int position = getSelectedItemPosition();
				if(position >= (getCount() - 1)){
					onKeyDown(KeyEvent.KEYCODE_DPAD_LEFT, null);
				}else{
					onKeyDown(KeyEvent.KEYCODE_DPAD_RIGHT, null);
				}
				break;

			default:
				break;
			}
		}
	};
	
	private final Timer timer = new Timer();
	private final TimerTask task = new TimerTask() {
		
		@Override
		public void run() {
			mHandler.sendEmptyMessage(timeAnimation);
		}
	};
	
	public MyGallery(Context context) {
		super(context);
		timer.schedule(task, 3000, 3000);
	}

	public MyGallery(Context context, AttributeSet attrs) {
		super(context, attrs);
		timer.schedule(task, 3000, 3000);
	}

	public MyGallery(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
		timer.schedule(task, 3000, 3000);
	}

	public boolean onFling(MotionEvent event1,MotionEvent event2,float f1, float f2){
		int keyCode;
		if(event2.getX() > event1.getX()){
			keyCode = KeyEvent.KEYCODE_DPAD_LEFT;
		}else{
			keyCode = KeyEvent.KEYCODE_DPAD_RIGHT;
		}
		onKeyDown(keyCode, null);
		return true;
	}
	
	public void destroy(){
		timer.cancel();
	}
}
