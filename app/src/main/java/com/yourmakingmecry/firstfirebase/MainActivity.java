package com.yourmakingmecry.firstfirebase;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import com.google.android.gms.tasks.Continuation;
import com.google.android.gms.tasks.Task;
import com.google.firebase.functions.FirebaseFunctions;
import com.google.firebase.functions.HttpsCallableResult;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.Console;
import java.util.HashMap;
import java.util.Map;

public class MainActivity extends AppCompatActivity {
    TextView statusText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        statusText = findViewById(R.id.statusText);
        addMessage("petrolguy");
        //String filtered = String.valueOf(dataReturned);
        //Log.d("horses","Value of returned data:" + filtered);
        /*FirebaseFunctions.getInstance() // Optional region: .getInstance("europe-west1")
                .getHttpsCallable("createUser")
                .call("hi");*/

    }
    private void addMessage(String userId) {
        // Create the arguments to the callable function.
        Map<String, Object> data = new HashMap<>();
        data.put("userId", userId);
        FirebaseFunctions.getInstance()
                .getHttpsCallable("getTheLastNamePlease")
                .call(data)
                .continueWith(new Continuation<HttpsCallableResult, String>() {
                    @Override
                    public String then(@NonNull Task<HttpsCallableResult> task) throws Exception {
                        // This continuation runs on either success or failure, but if the task
                        // has failed then getResult() will throw an Exception which will be
                        // propagated down.
                        /*String result = (String) task.getResult().getData();
                        Log.d("dude","result:" + result);
                        return result;*/
                        Log.d("dude","Here -2");
                        HashMap result = (HashMap) task.getResult().getData();
                        Log.d("dude","Here -1");
                        Log.d("dude","result value:" + result);
                        JSONObject res = new JSONObject(result);
                        Log.d("dude","Here");
                        String message = res.getString("lastName");
                        Log.d("dude","message:"+message);
                        return null;
                    }
                });
    }
}
