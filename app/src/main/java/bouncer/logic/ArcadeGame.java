package bouncer.logic;

import java.util.Timer;
import java.util.TimerTask;

import bouncer.common.AudioClip;


import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.AttributeSet;
import android.widget.LinearLayout;

public abstract class ArcadeGame extends LinearLayout
{
	// App context
	private Context mContex;
	
	// Update timer used to invalidate the view
	private Timer mUpdateTimer;
	
	// Timer period
	private long mPeriod = 1000;

	public ArcadeGame(Context context) {
		super(context);
		mContex = context;
	}
	public ArcadeGame(Context context, AttributeSet attrs) {
		super(context, attrs);
		mContex = context;
	}

	@Override
	protected void onLayout(boolean changed, int l, int t, int r, int b) {
		super.onLayout(changed, l, t, r, b);
		try {

			initialize();
			startUpdateTimer();
		} 
		catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void setUpdatePeriod(long period) {
		mPeriod = period;
	}

	protected void startUpdateTimer() {
		mUpdateTimer = new Timer();
		mUpdateTimer.schedule(new UpdateTask(), 0, mPeriod);
	}

	protected void stopUpdateTimer() {
		if (mUpdateTimer != null) {
			mUpdateTimer.cancel();
		}
	}

	public Context getContex() {
		return mContex;
	}

	protected Bitmap getImage(int id) {
		return BitmapFactory.decodeResource(mContex.getResources(), id);
	}

	protected AudioClip getAudioClip(int id) {
		return new AudioClip(mContex, id);
	}

	abstract protected void updatePhysics();

	abstract protected void initialize();
	
	abstract protected boolean gameOver();
	
	abstract protected long getScore();

	private class UpdateTask extends TimerTask {

		@Override
		public void run() {
			updatePhysics();
			postInvalidate();
		}

	}

	public void halt() {
		stopUpdateTimer();
	}

	public void resume() {
		initialize();
		startUpdateTimer();
	}
	
	
}
