package com.example.zhaoxinwu.eventhelper;

import android.content.Intent;
import android.media.tv.TvView;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.zhaoxinwu.shoppingListDB.DBHandler;
import com.example.zhaoxinwu.shoppingListDB.ShoppingItem;

public class CreateEventActivity extends AppCompatActivity {
    public static final String EXTRA_REPLY = "com.example.android.wordlistsql.REPLY";

    private EditText mEditTextEshiName;
    private EditText mEditTextNumber;
    private EditText mEditTextLocation;
    private EditText mEditTextItemName;

    public void addShoppingItem(View view ) {
        DBHandler dbHandler = new DBHandler(this, null);
        String eshiName = mEditTextEshiName.getText().toString();
        String number = mEditTextNumber.getText().toString();
        String location = mEditTextLocation.getText().toString();
        String itemName = mEditTextItemName.getText().toString();
        ShoppingItem shoppingItem = new ShoppingItem(eshiName, number, location, itemName);
        dbHandler.addHandler(shoppingItem);
        //Reset the GUI
        mEditTextEshiName.setText("");
        mEditTextNumber.setText("");
        mEditTextLocation.setText("");
        mEditTextItemName.setText("");

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_event);
        mEditTextEshiName = findViewById(R.id.input_eshi_name);
        mEditTextNumber = findViewById(R.id.input_number);
        mEditTextLocation = findViewById(R.id.input_location);
        mEditTextItemName = findViewById(R.id.input_item_name);

        final Button submitButton = findViewById(R.id.button_submit);

        submitButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                Intent replyIntent = new Intent();
                if(TextUtils.isEmpty(mEditTextEshiName.getText()) ||
                        TextUtils.isEmpty(mEditTextItemName.getText()) ||
                        TextUtils.isEmpty(mEditTextLocation.getText()) ||
                        TextUtils.isEmpty(mEditTextItemName.getText())) {

                    //setResult(RESULT_CANCELED, replyIntent);
                    Toast toast = Toast.makeText(getApplicationContext(), "有信息未输入，请检查",
                            Toast.LENGTH_SHORT);
                    toast.show();
                }
                else {
                    /*
                    String[] eventArray = new String[4];

                    eventArray[0] = mEditTextEshiName.getText().toString();
                    eventArray[1] = mEditTextNumber.getText().toString();
                    eventArray[2] = mEditTextLocation.getText().toString();
                    eventArray[3] = mEditTextItemName.getText().toString();

                    replyIntent.putExtra(EXTRA_REPLY, eventArray);
                    setResult(RESULT_OK, replyIntent);
                    */
                    addShoppingItem(view);
                    Toast toast = Toast.makeText(getApplicationContext(), "保存成功！现在可以继续输入",
                            Toast.LENGTH_SHORT);
                    toast.show();
                    // Log.d("DBINFO", "SENDING EventInfo: " + [);
                }

            }
        });
    }
}
