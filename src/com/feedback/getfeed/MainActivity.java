package com.feedback.getfeed;

import java.util.ArrayList;

import org.json.JSONObject;

import android.app.AlertDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;


public class MainActivity extends ParentActivityClass {

	 ArrayAdapter<String> adpter;
	 ArrayList<String> data;
	 Button goButton;
	 EditText codeText;
	 
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
       codeText= (EditText) findViewById(R.id.codeText);
       goButton = (Button)findViewById(R.id.goButton);
       feedNameEntered = getSharedPreferences(FEED_SYMBOL, MODE_PRIVATE);
      
       codeText.setText(feedNameEntered.getString("feedkey", "123456"));
        
       goButton.setOnClickListener(goButtonListener);
    }

    
    
private OnClickListener goButtonListener = new OnClickListener() {
	
	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		if (codeText.getText().length() > 0 )
		new AsyncLoadMasterDetails().execute(codeText.getText().toString());
			else
			{
				AlertDialog.Builder builder =  new AlertDialog.Builder(MainActivity.this);
				builder.setTitle(R.string.app_name);
				builder.setPositiveButton(android.R.string.ok, null);
				builder.setMessage(R.string.entercode);
				AlertDialog theAlertDialog = builder.create();
				theAlertDialog.show();
				
			}
		
	}
};

protected class AsyncLoadMasterDetails extends AsyncTask<String ,Void, Master>
{

	@Override
	protected Master doInBackground(String... params) {
		// TODO Auto-generated method stub
		Master master = null;
		RESTAPI api = new RESTAPI();
		try 
		{
			
			Log.d("AsyncLoad", params[0]);
			JSONObject jsonobj = api.GetMasterDetaiks(params[0]);		
			//JSONObject jsonobj = api.CreateNewMaster("ASAA", "keyid", 1, "Y", "N");
			JsonParser parser =new JsonParser();
			master = parser.parseMater(jsonobj);
	
		}
		catch(Exception e)
		{
			return null;
			
			//Log.d("AsyncLoad", e.getMessage());
		}
		return master;
	}
	@Override
	protected void onPostExecute(Master result) {
		//Log.d("postexecute",result.getName());
	
			name=result.getName();
			key=result.getKey();
			if (name != key)
	codeText.setText(name+key);
			else
				codeText.setText("");
	}

	
	
}

public void createFeed(View view)
{
	Intent intent=new Intent(this,CreateFeed.class);
	startActivity(intent);
}
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        if (id == R.id.feedHeading) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
