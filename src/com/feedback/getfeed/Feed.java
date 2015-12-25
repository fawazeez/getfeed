package com.feedback.getfeed;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

public class Feed extends ParentActivityClass {
	
	private TableLayout feedInput;
	Button addFeedButton;
	Button saveFeedButton;
	Button deleteFeedButton;
	TextView feedHeading;

	public int index;
	public String key;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_feed);
		feedInput = (TableLayout)findViewById(R.id.feedInput);
		
		addFeedButton = (Button)findViewById(R.id.addFeedButton);
		saveFeedButton = (Button)findViewById(R.id.saveFeedbutton);
		deleteFeedButton = (Button)findViewById(R.id.deleteFeedButton);
		Bundle b = getIntent().getExtras();
		name=b.getString("name");
		key=b.getString("key");
		feedHeading= (TextView) findViewById(R.id.feedHeading);
		feedHeading.setText(name);
		index=0;
		addFeedButton.setOnClickListener(feedButtonListener);
		saveFeedButton.setOnClickListener(saveFeedButtonListener);
		deleteFeedButton.setOnClickListener(deleteButtonListener);
		
		
	}
	
	OnClickListener saveFeedButtonListener =new OnClickListener() {
		
		@Override
		public void onClick(View v) {
			// TODO Auto-generated method stub
			
		}
	};
	
	OnClickListener deleteButtonListener = new OnClickListener() {
		
		@Override
		public void onClick(View v) {
			// TODO Auto-generated method stub
			feedInput.removeAllViews();
			index=0;
			
		}
	};

	public OnClickListener feedButtonListener = new OnClickListener() {
		
		@Override
		public void onClick(View v) {
			// TODO Auto-generated method stub
		
			LayoutInflater inflator = (LayoutInflater) getSystemService(LAYOUT_INFLATER_SERVICE);
			View newFeedRow = inflator.inflate(R.layout.feed_input,null );
					Spinner feedType= (Spinner)newFeedRow.findViewById(R.id.queryTypeSpinner);
					TextView sNumber= (TextView)newFeedRow.findViewById(R.id.sNumberTV); 
			//feedQuery.setText("...");
					sNumber.setText(String.valueOf(index+1));
				feedType.setOnItemSelectedListener(feedTypeListener);
				
			feedInput.addView(newFeedRow,index);
			index++;
		}
	};
	
	
	public OnItemSelectedListener feedTypeListener = new OnItemSelectedListener()
	{

		@Override
		public void onItemSelected(AdapterView<?> parent, View view,
				int position, long id) {
			// TODO Auto-generated method stub
		TableRow tRow = (TableRow) view.getParent().getParent().getParent();
		Spinner feedtype =(Spinner) tRow.findViewById(R.id.queryTypeSpinner);
		EditText customET1 = (EditText) tRow.findViewById(R.id.customET1);
		EditText customET2 = (EditText) tRow.findViewById(R.id.customET2);
		EditText customET3 = (EditText) tRow.findViewById(R.id.customET3);
		EditText customET4 = (EditText) tRow.findViewById(R.id.customET4);
		if (feedtype.getSelectedItem().equals("custom3"))
				{
	customET1.setVisibility(View.VISIBLE);
	customET1.requestFocus();	
	customET2.setVisibility(View.VISIBLE);
	customET3.setVisibility(View.VISIBLE);
			
				}
		else if (feedtype.getSelectedItem().equals("custom4"))
		{
			customET1.setVisibility(View.VISIBLE);
			customET2.setVisibility(View.VISIBLE);
			customET3.setVisibility(View.VISIBLE);
			customET4.setVisibility(View.VISIBLE);
		}
		}

		@Override
		public void onNothingSelected(AdapterView<?> parent) {
			// TODO Auto-generated method stub
			
		}
		
	};
	
/*
	private void updateSavedList(String feed) {
		// TODO Auto-generated method stub
		String[] feedsArray = feedEntered.getAll().keySet().toArray(new String[0]);
		//Arrays.sort(feeds,String.CASE_INSENSITIVE_ORDER);
		if(feed != null)
		{
			//insertFeedInScroll(feed,Arrays.binarySearch(feedsArray, feed));
		}
		else {
			for (int i=0;i<feedsArray.length;i++){
			//	insertFeedInScroll(feedsArray[i],i);
			}
		}
		
	} */

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.feed, menu);
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
