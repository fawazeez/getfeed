package com.feedback.getfeed;

public class User {
	
	String _username;
	String _mailid;
	String _passwordreqd;
	String _listavailable;

	public User()
	{
		
	}
	
	
	public User(String name,String key,	Integer type,String passwordreqd,String listavailable)
	{
		 this._username      =name;           
		 this._mailid=key;
		 this._passwordreqd=passwordreqd;
		 this._listavailable=listavailable;

		
	}
	
	public String getName()
	{
		return _username;
		
	}
	
	
	public void setName(String name)
	{
		this._username=name;
	}
	
	
	public String getKey()
	{
		return _mailid;
		
	}
	
	
	public void setKey(String key)
	{
		this._mailid=key;
	}
	
	
	
	public String getPasswordreqd()
	{
		return _passwordreqd;
		
	}
	
	
	public void setPasswordreqd(String passwordreqd)
	{
		this._passwordreqd=passwordreqd;
	}
	
	
	public String getListavailable()
	{
		return _listavailable;
		
	}
	
	
	public void setListavailable(String listavailable)
	{
		this._listavailable=listavailable;
	}



}
