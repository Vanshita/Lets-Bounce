package bouncer.logic;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.RadioButton;
import android.annotation.SuppressLint;
import android.annotation.TargetApi;
import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;


public class Menu extends Activity {
	
	private static String PREFS_NAME="bouncer_game";
	private static String ON_OFF_KEY="on_off";
	private static String TAG;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);
        TAG=Menu.class.getCanonicalName();
        
        
        checkDefaultRadioButton();
        
        
    }
    
    @SuppressLint("NewApi")
	@TargetApi(9)
	public void checkDefaultRadioButton(){
    	
    	String onOff = LoadPreferences(ON_OFF_KEY);
    	if(onOff.contains("on") || onOff.isEmpty() || onOff.contains("empty")){
    	RadioButton on=(RadioButton) findViewById(R.id.on);
        on.setChecked(true);
        RadioButton off=(RadioButton) findViewById(R.id.off);
        off.setChecked(false);
        
    	}else if(onOff.contains("off")){
    		RadioButton on=(RadioButton) findViewById(R.id.on);
            on.setChecked(false);
            RadioButton off=(RadioButton) findViewById(R.id.off);
            off.setChecked(true);
    	}
    	
    }

    
    
    public void turnSoundOffOn(View view){
    	  // Is the button now checked?
        boolean checked = ((RadioButton) view).isChecked();
        
        // Check which radio button was clicked
        switch(view.getId()) {
            case R.id.on:
                if (checked){
                	SavePreferences(ON_OFF_KEY,"on");
                	}
                    
                break;
            case R.id.off:
                if (checked)
                	SavePreferences(ON_OFF_KEY,"off");
                break;
        }
    	
    }
    
    
   public void backToGame(View view){
	   Intent intent = new Intent(this, Bouncer.class);
	   startActivity(intent);
	  
   }
   
   @SuppressLint("WorldReadableFiles")
  	private String LoadPreferences(String key){
  	   	   
  	        String defaultString = "empty";  
  	        String location ="";
  	        SharedPreferences sharedPreferences = getSharedPreferences(PREFS_NAME, MODE_WORLD_READABLE);
  	        location =  sharedPreferences.getString( key, defaultString );
  	        System.out.println("loadRestore key = " + location);
  	        	
  	        return location;
  	   
  	       }
    
    
    private void SavePreferences(String key, String value){

         SharedPreferences sharedPreferences = getSharedPreferences(PREFS_NAME, MODE_WORLD_READABLE);
         SharedPreferences.Editor editor = sharedPreferences.edit();
        
         editor.putString(key , value);
         Log.d(TAG,"saved " + value);
         
         editor.commit();
     
        }
    
  public void goToTitleScreen(View view){
    	
        Intent intent = new Intent(this,TitleScreen.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
  	   	startActivity(intent);
     }
  
  public void exit(View view){
  	Intent intent = new Intent(this,TitleScreen.class);
      intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
	   	startActivity(intent);
  	backgroundApp();
  }


    public void onDestroy(View view)
    {

        System.exit(0);

    }
  public void backgroundApp(){
      
  	this.moveTaskToBack(true);
  	
  }
}
