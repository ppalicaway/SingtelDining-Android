package com.singtel.ilovedeals.db;

import java.util.ArrayList;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.singtel.ilovedeals.info.MerchantInfo;
import com.singtel.ilovedeals.util.Constants;

public class DBManager {
	
	private Context context;
	private String dbName;
	private SQLiteDatabase myDB = null;
	
	private final String TB_MERCHANTAUTOID = "MerchantAutoId";
	private final String TB_MERCHANTID = "MerchantId";
	private final String TB_MERCHANTNAME = "MerchantName";
	private final String TB_MERCHANTADDRESS = "MerchantAddress";

	public DBManager(Context context, String dbName) {
		this.context = context;
		this.dbName = dbName;
		
		verify();
		try {
			myDB = context.openOrCreateDatabase(dbName, Context.MODE_PRIVATE, null);
		}catch(Exception ex){
			ex.printStackTrace();
		}
	}

	private void verify() {
		createDatabase();
		createTables();
		close();
	}

	public void close() {
		myDB.close();
	}

	private void createTables() {
		try {
			 myDB.execSQL("CREATE TABLE IF NOT EXISTS "+ 
					 	  Constants.TABLE_MERCHANT +
					      " ("+					      
					      TB_MERCHANTAUTOID + "	 	INTEGER PRIMARY KEY, " +
					      TB_MERCHANTID + "  		INTEGER, " +
					      TB_MERCHANTNAME + "  		VARCHAR(255), " +
					      TB_MERCHANTADDRESS + "	VARCHAR(255)  " +					      
			 			  " );");
		}catch(Exception ex){
			ex.printStackTrace();
		}
	}

	private void createDatabase() {
		try {
			myDB = context.openOrCreateDatabase(dbName, Context.MODE_PRIVATE, null);
		}catch(Exception ex){
			ex.printStackTrace();
		}
	}
	
	public void insertMerchant(MerchantInfo merchant) {
		try {
			myDB.execSQL("INSERT INTO " 
					+ Constants.TABLE_MERCHANT + 
					" (" + TB_MERCHANTID + ", " + TB_MERCHANTNAME +
					", " + TB_MERCHANTADDRESS + ")"  + 
                    " VALUES (	" +
                    	  "'" + merchant.getId() + "', " +
                    	  "'" + merchant.getRestaurantName() + "', " +
                    	  "'" + merchant.getAddress() + "'" +
                    " );");
		}catch(Exception ex){
			ex.printStackTrace();
		}
	}
	
	public void deleteMerchant(MerchantInfo merchant) {
		try {
			myDB.execSQL("DELETE FROM  " 
					+ Constants.TABLE_MERCHANT + 
					" WHERE " + TB_MERCHANTID +" = " + merchant.getId() + ";"); 
		}catch(Exception ex){
			ex.printStackTrace();
		}
	}
	
	public ArrayList<MerchantInfo> getMerchantList() {
		ArrayList<MerchantInfo> result = null;
		try {
			Cursor c = myDB.rawQuery("SELECT * FROM " + Constants.TABLE_MERCHANT +
					" limit " + Constants.ITEMS_PER_PAGE + ";", 
                    null);

			c.moveToFirst();

			if (c != null) {
				do {
					int merchantId = c.getInt(c.getColumnIndex(TB_MERCHANTID));
					String merchantName = c.getString(c.getColumnIndex(TB_MERCHANTNAME));
					String merchantAddress = c.getString(c.getColumnIndex(TB_MERCHANTADDRESS));
														
					if(result == null) {
						result = new ArrayList<MerchantInfo>();
					}
					
					MerchantInfo mInfo = new MerchantInfo();
					mInfo.setId(merchantId);
					mInfo.setRestaurantName(merchantName);
					mInfo.setAddress(merchantAddress);
					
					result.add(mInfo);
				} while (c.moveToNext());
			}			
			if(c != null)	
				c.close();
		}catch(Exception ex){
			ex.printStackTrace();
		}
		return result;
	}
	
	public boolean isMerchantExist(MerchantInfo mInfo) {
		boolean result = false;
		Cursor c = null;
		try {
			c = myDB.rawQuery("SELECT * FROM " + Constants.TABLE_MERCHANT +
					" WHERE " + TB_MERCHANTID + "=" + mInfo.getId() + ";", 
                    null);

			c.moveToFirst();

			if (c.getCount() > 0) {
				result = true;
			}
		}catch(Exception ex){
			ex.printStackTrace();
		}
		finally {
			c.close();
		}		
		return result;
	}
}
