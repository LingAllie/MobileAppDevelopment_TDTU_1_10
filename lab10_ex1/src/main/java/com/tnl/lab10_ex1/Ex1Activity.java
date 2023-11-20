package com.tnl.lab10_ex1;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.content.ContentResolver;
import android.content.Intent;
import android.Manifest;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.provider.ContactsContract.Contacts;
import android.util.Log;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class Ex1Activity extends AppCompatActivity implements ContactAdapter.ContactListener {

    private ContactAdapter adapter;
    private RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ex1);

        recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this, RecyclerView.VERTICAL, true));//ghi dai true
        recyclerView.addItemDecoration(new DividerItemDecoration(this, RecyclerView.VERTICAL));

        adapter = new ContactAdapter(this);
        adapter.setListener(this);
        recyclerView.setAdapter(adapter);

        if(hasContactPermission()){
            readContact();
        } else requestContactPermissions();

    }

    private boolean hasContactPermission() {
        return ContextCompat.checkSelfPermission(this, Manifest.permission.READ_CONTACTS) == PackageManager.PERMISSION_GRANTED;
    }

    private void requestContactPermissions() {
        ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.READ_CONTACTS}, 100);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if(grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED){
            readContact();
        } else {
            Toast.makeText(this, "No permission", Toast.LENGTH_SHORT).show();
        }
    }

    private void readContact(){
        Uri link = Contacts.CONTENT_URI;
        String COL_NAME = Contacts.DISPLAY_NAME_PRIMARY;
        String COL_HAS_NUMBER = Contacts.HAS_PHONE_NUMBER;
        String COL_LOOKUP = Contacts.LOOKUP_KEY;
        String COL_IMAGE = Contacts.PHOTO_URI;
        String COL_THUMBNAIL = Contacts.PHOTO_THUMBNAIL_URI;

        ContentResolver resolver = getContentResolver();
        Cursor cursor = resolver
                .query(link, new String[]{COL_NAME, COL_HAS_NUMBER, COL_LOOKUP, COL_IMAGE, COL_THUMBNAIL},
                        null, null, null);

        List<ContactUser> users = new ArrayList<>();

        while (cursor.moveToNext()){
            @SuppressLint("Range") String name = cursor.getString(cursor.getColumnIndex(COL_NAME));
            @SuppressLint("Range") int hasNumber = cursor.getInt(cursor.getColumnIndex(COL_HAS_NUMBER));
            @SuppressLint("Range") String key = cursor.getString(cursor.getColumnIndex(COL_LOOKUP));
            @SuppressLint("Range") String image = cursor.getString(cursor.getColumnIndex(COL_IMAGE));
            @SuppressLint("Range") String thumbnail = cursor.getString(cursor.getColumnIndex(COL_THUMBNAIL));

            int nameIndex = cursor.getColumnIndex(COL_NAME);
            if (nameIndex != -1) {
                name = cursor.getString(nameIndex);
                // Tiếp tục xử lý dữ liệu
            } else {
                Log.e("TAG", "Column COL_NAME not found");
            }


            ContactUser user = new ContactUser();
            user.setName(name);
            user.setImage(Uri.parse(image));
            user.setThumbnails(Uri.parse(thumbnail));

            Uri detailUri = ContactsContract.Data.CONTENT_URI;
            String COL_DATA = ContactsContract.Data.DATA1;
            String COL_TYPE = ContactsContract.Data.MIMETYPE;

            Cursor c2 = getContentResolver().query(detailUri,
                    new String[]{COL_DATA, COL_TYPE}, ContactsContract.Data.LOOKUP_KEY + " = ?",
                    new String[]{key}, null);

            while (c2.moveToNext()){
                @SuppressLint("Range") String data = c2.getString(c2.getColumnIndex(COL_DATA));
                @SuppressLint("Range") String type = c2.getString(c2.getColumnIndex(COL_TYPE));

                if(data != null && type.equals(ContactsContract.CommonDataKinds.Phone.CONTENT_ITEM_TYPE)){
                    user.addPhone(data);
                }
                else if(data != null && type.equals(ContactsContract.CommonDataKinds.Note.CONTENT_ITEM_TYPE)){
                    user.setNote(data);
                }
                else if(data != null && type.equals(ContactsContract.CommonDataKinds.StructuredPostal.CONTENT_ITEM_TYPE)){
                    user.setAddress(data);
                }
                else if(data != null && type.equals(ContactsContract.CommonDataKinds.Email.CONTENT_ITEM_TYPE)){
                    user.setEmail(data);
                }
                else if(data != null && type.equals(ContactsContract.CommonDataKinds.Website.CONTENT_ITEM_TYPE)){
                    user.setWebsite(data);
                }
            }

            users.add(user);

        }
        cursor.close();
        adapter.addAll(users);
    }

    @Override
    public void onClick(int position) {
        Intent intent = new Intent(this, ContactActivity.class);
        intent.putExtra("data", adapter.getContact(position));

        startActivity(intent);
    }
}