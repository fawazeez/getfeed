package com.feedback.getfeed;
import java.util.ArrayList;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.util.Log;

public class JsonParser {

	public JsonParser()
	{
		super();
	}
	public ArrayList<Master> parseMaterList(JSONObject object)
	{
		ArrayList<Master> arrayList = new ArrayList<Master>();
		
		try
		{
			JSONArray jsonArray =object.getJSONArray("Value");
			JSONObject jsonobj=null;
			for(int i=0;i<jsonArray.length();i++)
			{
			jsonobj=jsonArray.getJSONObject(i);
			arrayList.add(new Master(jsonobj.getString("name"),jsonobj.getString("keyid"),jsonobj.getInt("type"),jsonobj.getString("passwordreqd"),jsonobj.getString("listavailable")));
			}
		}
		catch (JSONException e) {
Log.d("ParseMaster", e.getMessage());
		}
		return arrayList;
		
	}
	
	public Master parseMater(JSONObject object)
	{
		Master master= new Master();
		
		try
		{
			if (object.getJSONArray("Value").length() > 0)
			{
				
			JSONObject jsonobj =object.getJSONArray("Value").getJSONObject(0);
			 master.setName(jsonobj.getString("name"));
			 master.setKey(jsonobj.getString("keyid"));
			 master.setType(jsonobj.getInt("type"));
			 master.setListavailable(jsonobj.getString("passwordreqd"));
			 master.setPasswordreqd(jsonobj.getString("listavailable"));

			}		 			
		}
		catch (JSONException e) {
Log.d("ParseMaster", e.getMessage());
		}
		return master;
		
	}
	
}
