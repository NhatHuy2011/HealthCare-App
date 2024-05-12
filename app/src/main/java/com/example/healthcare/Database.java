package com.example.healthcare;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import java.util.ArrayList;

public class Database extends SQLiteOpenHelper {

    public Database(@Nullable Context context, @Nullable String name, @Nullable SQLiteDatabase.CursorFactory factory, int version) {
        super(context, "HealthCare.db", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String qry1 ="create table users(username TEXT , email, password)";
        db.execSQL(qry1);

        String qry2 = "create table cart(username TEXT , product TEXT, price float, otype TEXT)";
        db.execSQL(qry2);

        String qry3 = "create table orders(username TEXT, fullname TEXT, address TEXT, contact TEXT, date TEXT, time TEXT, price float, otype TEXT)";
        db.execSQL(qry3);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    public void register(String username, String email, String password){
        SQLiteDatabase db =this.getWritableDatabase();
        ContentValues contentValues =new ContentValues();
        contentValues.put("username", username);
        contentValues.put("email", email);
        contentValues.put("password", password);
        db.insert("users", null, contentValues);
        db.close();
    }

    public int login(String username, String password)
    {
        int result = 0;
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor c = db.rawQuery("select * from users where username=? and password=?", new String[]{username, password});
        if(c.moveToFirst()){
            result = 1;
        }
        return result;
    }

    public User getUserFromDatabase() {
        SQLiteDatabase db = this.getReadableDatabase();
        String[] projection = {
                "username",
                "email",
                "password"
        };
        Cursor cursor = db.query("users", projection, null, null, null, null, null);
        cursor.moveToFirst();
        String username = cursor.getString(cursor.getColumnIndexOrThrow("username"));
        String email = cursor.getString(cursor.getColumnIndexOrThrow("email"));
        String password = cursor.getString(cursor.getColumnIndexOrThrow("password"));
        cursor.close();
        return new User(username, email, password);
    }

    public void updateUser(String username, String newEmail, String newPassword) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("email", newEmail);
        values.put("password", newPassword);
        String selection = "username = ?";
        String[] selectionArgs = { username };
        int count = db.update(
                "users",
                values,
                selection,
                selectionArgs);
        db.close();
    }

    public void addCart(String username, String product, float price, String otype)
    {
        ContentValues cv = new ContentValues();
        cv.put("username", username);
        cv.put("product", product);
        cv.put("price", price);
        cv.put("otype", otype);
        SQLiteDatabase db = getWritableDatabase();
        db.insert("cart", null, cv);
        db.close();
    }
    public int checkCart(String username, String product){
        int result = 0;
        String str[] = new String[2];
        str[0] = username;
        str[1] = product;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor c = db.rawQuery("select * from cart where username = ? and product = ?", str);
        if(c.moveToFirst()){
            result = 1;
        }
        db.close();
        return result;
    }

    public ArrayList getCartData(String username, String otype)
    {
        ArrayList<String> arr = new ArrayList<>();
        SQLiteDatabase db = getReadableDatabase();
        String str[] = new String[2];
        str[0] = username;
        str[1] = otype;
        Cursor c = db.rawQuery("select * from cart where username = ? and otype = ?", str);
        if(c.moveToFirst()){
            do{
                String product=c.getString(1);
                String price = c.getString(2);
                arr.add(product + " VND" + price);
            }while (c.moveToNext());
        }
        db.close();
        return arr;
    }
    public void addOrder(String username, String fullname, String address, String contact, String date, String time, float price, String otype)
    {
        ContentValues cv = new ContentValues();
        cv.put("username", username);
        cv.put("fullname", fullname);
        cv.put("address", address);
        cv.put("contact", contact);
        cv.put("date", date);
        cv.put("time", time);
        cv.put("price", price);
        cv.put("otype", otype);
        SQLiteDatabase db = getWritableDatabase();
        db.insert("orders", null, cv);
        db.close();
    }
    public void removeCart(String username, String otype){
        String str[] = new String[2];
        str[0] = username;
        str[1] = otype;
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete("cart", "username = ? and otype = ?", str);
        db.close();
    }

    public void removeOrder(String fullname, String address, String contact, String date, String time, float price){
        SQLiteDatabase db = this.getWritableDatabase();
        String whereClause = "fullname = ? AND address = ? AND contact = ? AND date = ? AND time = ? AND price = ?";
        String[] whereArgs = {fullname, address, contact, date, time, String.valueOf(price)};
        db.delete("orders", whereClause, whereArgs);
        db.close();
    }


    public ArrayList getOrderData(String username)
    {
        ArrayList<String> arr = new ArrayList<>();
        SQLiteDatabase db = getReadableDatabase();
        String str[] = new String[1];
        str[0] = username;
        Cursor c = db.rawQuery("select * from orders where username = ?", str);
        if(c.moveToFirst()){
            do{
                arr.add(c.getString(1) + "$" + c.getString(2) + "$" + c.getString(3) + "$" +c.getString(4) + "$" + c.getString(5) + "$" + c.getString(6) + "$" + c.getString(7));
            }while (c.moveToNext());
        }
        db.close();
        return arr;
    }
    public int checkAppointmentExist(String username,
                                     String fullname,
                                     String address,
                                     String contact,
                                     String date,
                                     String time)
    {
        int result = 0;
        String str[]=new String[6];
        str[0] =username;
        str[1] =fullname;
        str[2] =address;
        str[3] =contact;
        str[4] =date;
        str[5] =time;
        SQLiteDatabase db = getReadableDatabase();
        Cursor c =db.rawQuery("select * from orders where username = ?" +
                "and fullname = ?" +
                "and address = ?" +
                "and contact = ?" +
                "and date = ?" +
                "and time = ?", str);
        return result;
    }
}
