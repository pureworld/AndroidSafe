package org.pw.safe;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

public class BootReceiver extends BroadcastReceiver {

	@Override
	public void onReceive(Context context, Intent intent) {
		Intent service = new Intent(context,MyService.class);
		service.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
		context.startService(service);
	}

}
