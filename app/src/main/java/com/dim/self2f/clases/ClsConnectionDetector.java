package com.dim.self2f.clases;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

public class ClsConnectionDetector {
	private Context _context;
	public ClsConnectionDetector(Context context){
		this._context = context;
	}
	public boolean isConnectingToInternet(){
		ConnectivityManager connectivity = (ConnectivityManager)_context.getSystemService(Context.CONNECTIVITY_SERVICE);
		if (connectivity != null) {
			NetworkInfo[] netInfo = connectivity.getAllNetworkInfo();
			if (netInfo != null) {
				for (int i = 0; i < netInfo.length; i++) {
					if (netInfo[i].getState() == NetworkInfo.State.CONNECTED) {
						return true;
					}
				}
			}
		}
		return false;	
	}

}
