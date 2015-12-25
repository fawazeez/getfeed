package com.feedback.getfeed;

public class Master {
	String _name;
	String _key;
	Integer _type;
	String _passwordreqd;
	String _listavailable;

	public Master()
	{
		
	}
	
	
	public Master(String name,String key,	Integer type,String passwordreqd,String listavailable)
	{
		 this._name      =name;           
		 this._key=key;
		 this._type=type;
		 this._passwordreqd=passwordreqd;
		 this._listavailable=listavailable;

		
	}
	
	public String getName()
	{
		return _name;
		
	}
	
	
	public void setName(String name)
	{
		this._name=name;
	}
	
	
	public String getKey()
	{
		return _key;
		
	}
	
	
	public void setKey(String key)
	{
		this._key=key;
	}
	
	
	public Integer getType()
	{
		return _type;
		
	}
	
	
	public void setType(Integer type)
	{
		this._type=type;
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
