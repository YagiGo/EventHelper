package com.example.zhaoxinwu.eventhelper;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class CreateEventActivity extends AppCompatActivity {
    public static final String EXTRA_REPLY = "com.example.android.wordlistsql.REPLY";

    private EditText mEditTextEventName;
    private EditText mEditTextZipCode;
    private EditText mEditTextAddress;
    private EditText mEditTextBuilding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_event);
        mEditTextEventName = findViewById(R.id.input_event_name);
        mEditTextZipCode = findViewById(R.id.input_zipcode);
        mEditTextAddress = findViewById(R.id.input_address);
        mEditTextBuilding = findViewById(R.id.input_building_name);

        final Button submitButton = findViewById(R.id.button_submit);

        submitButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                Intent replyIntent = new Intent();
                if(TextUtils.isEmpty(mEditTextEventName.getText())) {
                    setResult(RESULT_CANCELED, replyIntent);
                }
                else {
                    String[] eventArray = new String[4];

                    eventArray[0] = mEditTextEventName.getText().toString();
                    eventArray[1] = mEditTextZipCode.getText().toString();
                    eventArray[2] = mEditTextAddress.getText().toString();
                    eventArray[3] = mEditTextBuilding.getText().toString();

                    replyIntent.putExtra(EXTRA_REPLY, eventArray);
                    setResult(RESULT_OK, replyIntent);
                    Log.d("DBINFO", "SENDING EventInfo: " + eventArray[0]);
                }

                finish();
            }
        });
    }
}
