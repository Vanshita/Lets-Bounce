package bouncer.common;
import java.lang.*;

import android.util.Log;

public class Timer extends Thread {

	int oneSecond=1000;
	int value=0; 
	static String TAG="Timer";
	
	@Override
	public void run() {
		for(;;){
			
			value++;
			//Thread.currentThread();
			try {
				Thread.sleep(1000);
			
			//	Log.d(TAG, " " + value);
			} catch (InterruptedException e) {
				System.out.println("timer interrupted");
				//value=0;
				
				
				e.printStackTrace();
				
			}
		}
		
	}
	
	public int getValue(){
		return value;
	}

}
