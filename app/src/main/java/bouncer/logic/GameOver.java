package bouncer.logic;

import android.os.Bundle;
import android.annotation.SuppressLint;
import android.annotation.TargetApi;
import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.view.Menu;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

public class GameOver extends Activity {
	
	String result;
	private static String USER_NAME="userName";
	private static String PREFS_NAME="bouncer_game";

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game_over);
      
        Intent intent = getIntent();
        result=intent.getStringExtra("result");
       
        TextView et = (TextView) findViewById(R.id.points);
        et.setText(result);
        
     
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.activity_game_over, menu);
        return true;
    }
    
   
    public void goToTitleScreen(View view){
       Intent intent = new Intent(this,TitleScreen.class);
       intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
 	   startActivity(intent);
 	   
 	   
    }
    public void backgroundApp(){
        
    	this.moveTaskToBack(true);
    	
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
    public void onDestroy(View view)
    {
        finish();
        System.exit(0);

    }
}
