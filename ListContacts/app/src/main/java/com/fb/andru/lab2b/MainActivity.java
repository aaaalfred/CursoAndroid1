package com.fb.andru.lab2b;

import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.AdapterView;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private ListView mContactList;
    private CheckBox mCheckBox;
    private boolean mShowStarred;
    private TextView selectedText;
    private TextView totalcontact;
    /** Called when the activity is first created. */

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mContactList = (ListView) findViewById(R.id.contactsListView);
        mCheckBox = (CheckBox) findViewById(R.id.showStarred);
        selectedText = (TextView) findViewById(R.id.selectedContact);
        totalcontact = (TextView) findViewById(R.id.totalcontact);
        mShowStarred = false;
        mCheckBox.setOnCheckedChangeListener(new CheckChangedListener());
        mContactList.setOnItemClickListener(new ClickListener());
        populateContactList();
    }

    public void populateContactList() {
        Uri uri = ContactsContract.Contacts.CONTENT_URI;
        String[] projection = new String[] {
                ContactsContract.Contacts._ID,
                ContactsContract.Contacts.DISPLAY_NAME,
        };
        String[] selectionArgs = null;
        String sortOrder = ContactsContract.Contacts.DISPLAY_NAME +" COLLATE LOCALIZED ASC";
        String selection = mShowStarred? ContactsContract.Contacts.STARRED +" ='1'" : null;
        Cursor c = getContentResolver().query(uri, projection, selection, selectionArgs,sortOrder);
        totalcontact.setText("" + c.getCount());

        String[] fields = new String[] {ContactsContract.Data.DISPLAY_NAME};
        SimpleCursorAdapter adapter =
                new SimpleCursorAdapter(this, R.layout.contactitem,c, fields, new int[] {R.id.contactItem}, 0);
        mContactList.setAdapter(adapter);
    }

    private class ClickListener implements AdapterView.OnItemClickListener {
        @Override
        public void onItemClick(AdapterView<?> arg0, View textView, int pos, long arg3) {
            if(textView instanceof TextView)selectedText.setText(((TextView) textView).getText());

        }
    }
    private class CheckChangedListener implements CompoundButton.OnCheckedChangeListener {
        @Override
        public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
            mShowStarred = isChecked;
            selectedText.setText("");
            populateContactList();
        }
    }


}
