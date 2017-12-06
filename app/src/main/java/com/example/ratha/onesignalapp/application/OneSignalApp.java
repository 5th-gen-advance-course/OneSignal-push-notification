package com.example.ratha.onesignalapp.application;

import android.app.Application;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import com.example.ratha.onesignalapp.NotificationDetailActivity;
import com.example.ratha.onesignalapp.entity.User;
import com.onesignal.OSNotificationAction;
import com.onesignal.OSNotificationOpenResult;
import com.onesignal.OneSignal;

import org.json.JSONObject;

/**
 * Created by ratha on 12/4/2017.
 */

public class OneSignalApp extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        OneSignal.startInit(this)
                .inFocusDisplaying(OneSignal.OSInFocusDisplayOption.Notification)
                .unsubscribeWhenNotificationsAreDisabled(true)
                .setNotificationOpenedHandler(new ExampleNotificationOpenedHandler())
                .init();
    }

    class ExampleNotificationOpenedHandler implements OneSignal.NotificationOpenedHandler {
        // This fires when a notification is opened by tapping on it.
        @Override
        public void notificationOpened(OSNotificationOpenResult result) {
            OSNotificationAction.ActionType actionType = result.action.type;
            JSONObject data = result.notification.payload.additionalData;
            String name=null,email=null;
            User user =new User();
            if (data != null) {

                name = data.optString("name");
                email=data.optString("email");
                if (null!=name) user.setName(name);
                if (null!=email) user.setEmail(email);

            }

            if (actionType == OSNotificationAction.ActionType.ActionTaken)
                Log.i("OneSignalExample", "Button pressed with id: " + result.action.actionID);

            // The following can be used to open an Activity of your choice.
            // Replace - getApplicationContext() - with any Android Context.
            Intent intent = new Intent(getApplicationContext(), NotificationDetailActivity.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT | Intent.FLAG_ACTIVITY_NEW_TASK);
            Bundle bundle=new Bundle();
            bundle.putParcelable("USER",user);
            intent.putExtras(bundle);
            startActivity(intent);

            // Add the following to your AndroidManifest.xml to prevent the launching of your main Activity
            //   if you are calling startActivity above.
     /*
        <application ...>
          <meta-data android:name="com.onesignal.NotificationOpened.DEFAULT" android:value="DISABLE" />
        </application>
     */
        }
    }
}
