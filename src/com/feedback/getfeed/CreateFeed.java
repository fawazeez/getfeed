package com.feedback.getfeed;

import org.json.JSONObject;

import com.feedback.getfeed.MainActivity.AsyncLoadMasterDetails;

import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RadioGroup.OnCheckedChangeListener;
import android.widget.SeekBar;
import android.widget.SeekBar.OnSeekBarChangeListener;

public class CreateFeed extends ParentActivityClass {
	
	
	

	
	EditText feedNameEditText;
	RadioGroup typeRadioGroup;
	public int type=0;
	public String typefeed="Feed";
	RadioButton feedbackRB0;
	RadioButton quizkRB1;
	RadioButton surveyRB2;
	CheckBox paswdreqdCB;
	CheckBox cListAvailableCB;
	
	Button createFeedButton;
	LinearLayout timerView;
	
	
	SeekBar timeSeekBar;
 EditText timeSeek;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_create_feed);
		
		feedNameEntered = getSharedPreferences(FEED_SYMBOL, MODE_PRIVATE);
		feedNameEditText=(EditText) findViewById(R.id.feedNameEditText);
		paswdreqdCB=(CheckBox)findViewById(R.id.paswdreqdCheckBox);
		cListAvailableCB=(CheckBox)findViewById(R.id.cListAvailableCheckBox);
		typeRadioGroup=(RadioGroup)findViewById(R.id.typeradioGroup);
		feedbackRB0=(RadioButton)findViewById(R.id.feedbackRadio0);
		quizkRB1=(RadioButton)findViewById(R.id.quizRadio1);
		surveyRB2=(RadioButton)findViewById(R.id.surveyRadio2);
		timeSeekBar = (SeekBar)findViewById(R.id.timeSeekBar);
		timeSeek=(EditText) findViewById(R.id.timeText);
		timerView=(LinearLayout)findViewById(R.id.timerView);
		timerView.setVisibility(View.INVISIBLE);
		timeSeek.setText("5");
		
		timeSeekBar.setOnSeekBarChangeListener(timeSeekBarLIstener);
		addChangeListenerToRadios();
 
	}
	
	private void addChangeListenerToRadios() {
		// TODO Auto-generated method stub
		typeRadioGroup.setOnCheckedChangeListener(new OnCheckedChangeListener() {
			
			@Override
			public void onCheckedChanged(RadioGroup group, int checkedId) {
				// TODO Auto-generated method stub
				
				InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
				imm.hideSoftInputFromWindow(feedNameEditText.getWindowToken(), 0);
				if(quizkRB1.isChecked())
				{
					type=1;
					
					cListAvailableCB.setVisibility(View.INVISIBLE);
					timerView.setVisibility(View.VISIBLE);
					
				}
				if(feedbackRB0.isChecked())
				{
					type=0;
					timerView.setVisibility(View.INVISIBLE);
					cListAvailableCB.setVisibility(View.VISIBLE);
				}
				if(surveyRB2.isChecked())
				{
					type=2;
					timerView.setVisibility(View.INVISIBLE);
					cListAvailableCB.setVisibility(View.VISIBLE);
				}
			}
		});
	}

	private OnSeekBarChangeListener timeSeekBarLIstener = new OnSeekBarChangeListener ()
	{

		@Override
		public void onProgressChanged(SeekBar seekBar, int progress,
				boolean fromUser) {
			// TODO Auto-generated method stub
			timeSeek.setText(String.valueOf(seekBar.getProgress()) );
			
		}

		@Override
		public void onStartTrackingTouch(SeekBar seekBar) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void onStopTrackingTouch(SeekBar seekBar) {
			// TODO Auto-generated method stub
			
		}
		
	};

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.create_feed, menu);
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
	
	public void createFeedDb(View view){
		
		if(feedNameEditText.getText().length()>0){
			
	 name=feedNameEditText.getText().toString();
	 key= name+"12";
	String passwordreqd=paswdreqdCB.isChecked()?"Y":"N";
	String listavailable=cListAvailableCB.isChecked()?"Y":"N";
	
	Master master=new Master(name, key, type, passwordreqd, listavailable);
	
	new AsyncCreateMaster().execute(master);
	
	Log.d("insert", "inserting .. ");
DatabaseHandler db= new DatabaseHandler(this);
db.addMaster(master);
if (type==0)
{
	Intent intent=new Intent(this,Feed.class);
	intent.putExtra("name", name);
	intent.putExtra("key", key);
	startActivity(intent);
}
else if (type==1)
{
	Intent intent=new Intent(this,Quiz.class);
	intent.putExtra("name", name);
	intent.putExtra("key", key);
	startActivity(intent);
}
else if (type==2)
{
	Intent intent=new Intent(this,Survey.class);
	intent.putExtra("name", name);
	intent.putExtra("key", key);
	startActivity(intent);
}

SharedPreferences.Editor prefEditor = feedNameEntered.edit();
prefEditor.putString("feedkey", key);
prefEditor.commit();

		}
		else
		{
			AlertDialog.Builder builder =  new AlertDialog.Builder(CreateFeed.this);
			builder.setTitle(R.string.app_name);
			builder.setPositiveButton(android.R.string.ok, null);
			builder.setMessage(R.string.name);
			AlertDialog theAlertDialog = builder.create();
			theAlertDialog.show();
			
		}
//feedNameEditText.setText(db.getMasterCount());
	}
	protected class AsyncCreateMaster extends AsyncTask<Master ,Void,Void>
	
	{

		@Override
		protected Void doInBackground(Master... params) {
			RESTAPI api = new RESTAPI();
			try 
			{
				Log.d("tryload", params[0].getKey());
			
				 api.CreateNewMaster(params[0].getName(), params[0].getKey(), params[0].getType(), params[0].getPasswordreqd(), params[0].getListavailable());
			}
		                                                                                                                                                                 
		catch (Exception e)
		{
			Log.d("MasterLoad", params[0].getKey());
		}
			return null;
			}
	
		
	}
	
}
