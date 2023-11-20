package com.tnl.lab10_ex1;

import android.net.Uri;
import android.os.Parcel;
import android.os.Parcelable;

import androidx.annotation.NonNull;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class ContactUser implements Parcelable {

    private String name;
    private List<String> phone;
    private String address;
    private String website;
    private String note;
    private String email;
    private Uri thumbnails;
    private Uri image;

    public ContactUser(){
        this.phone = new ArrayList<>();
    }
    protected ContactUser(Parcel in) {
        name = in.readString();
        phone = in.createStringArrayList();
        address = in.readString();
        website = in.readString();

    }

    public static final Creator<ContactUser> CREATOR = new Creator<ContactUser>() {
        @Override
        public ContactUser createFromParcel(Parcel in) {
            return new ContactUser(in);
        }

        @Override
        public ContactUser[] newArray(int size) {
            return new ContactUser[size];
        }
    };


    @Override
    public void writeToParcel(@NonNull Parcel dest, int flags) {
        dest.writeString(name);
        dest.writeStringList(phone);
        dest.writeString(address);
        dest.writeString(website);
        dest.writeString(note);
        dest.writeString(email);
        dest.writeParcelable(thumbnails, flags);
        dest.writeParcelable(image, flags);
    }

    public int describeContents(){
        return 0;
    }

    public void setName(String name) {
        this.name = name;
    }
    public String getName() {
        return name;
    }
    public String getAddress() {
        return address;
    }
    public void setAddress(String address) {
        this.address = address;
    }
    public void setPhone(List<String> phone) {
        this.phone = phone;
    }
    public List<String> getPhone() {
        if(phone == null && phone.size() == 0){
            return Collections.singletonList("");
        }
        StringBuilder builder = new StringBuilder();
        for(int i = 0; i < phone.size();i++){
            if(i > 0){
                builder.append("\n");
            }
            builder.append(phone.get(i));
        }
        return Collections.singletonList(builder.toString());
    }
    public void setEmail(String email) {
        this.email = email;
    }
    public String getEmail() {
        return email;
    }
    public String getNote() {
        return note;
    }
    public void setNote(String note) {
        this.note = note;
    }
    public Uri getImage() {
        return image;
    }
    public String getWebsite() {
        return website;
    }
    public void setImage(Uri image) {
        this.image = image;
    }
    public void setWebsite(String website) {
        this.website = website;
    }
    public Uri getThumbnails() {
        return thumbnails;
    }
    public void setThumbnails(Uri thumbnails) {
        this.thumbnails = thumbnails;
    }

    public void addPhone(String data) {
    }
}
