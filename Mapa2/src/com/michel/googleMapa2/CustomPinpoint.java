package com.michel.googleMapa2;

import java.util.ArrayList;

import android.content.Context;
import android.graphics.drawable.Drawable;

import com.google.android.maps.ItemizedOverlay;
import com.google.android.maps.OverlayItem;

public class CustomPinpoint extends ItemizedOverlay<OverlayItem>{

	private ArrayList<OverlayItem> pinpoints = new ArrayList<OverlayItem>();
	private Context c; 
	
	
	
	
	public CustomPinpoint(Drawable defaultMarker)//Constructor 1
	{
		super(boundCenter(defaultMarker));//Bound center of the pinpoint
		// TODO Auto-generated constructor stub
	
	
	}

	
	public CustomPinpoint(Drawable m, Context context)// Constructor 2
	{
		// TODO Auto-generated constructor stub
	this(m);
	c = context;
	
	}
	
	
	@Override
	protected OverlayItem createItem(int i) {
		// TODO Auto-generated method stub
	
		return pinpoints.get(i);
	
	
	}

	@Override
	public int size() {
		// TODO Auto-generated method stub
		return pinpoints.size();
	}

	
	public void insertPinpoint(OverlayItem item){
		pinpoints.add(item);
		this.populate();				
	}
	
	/*
	public void deletePinpoint(OverlayItem item){
		pinpoints.remove(item);			
		
	}
	*/
}
