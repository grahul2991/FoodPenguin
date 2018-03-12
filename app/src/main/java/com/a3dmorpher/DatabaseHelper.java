package com.a3dmorpher;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.a3dmorpher.POJO.RestaurantModelClass;
import com.a3dmorpher.POJO.User;

import java.util.ArrayList;
import java.util.List;

public class DatabaseHelper extends SQLiteOpenHelper {

    // Database Version
    private static final int DATABASE_VERSION = 1;

    // Database Name
    private static final String DATABASE_NAME = "UserManager.db";

    // User table name
    private static final String TABLE_USER = "user";

    private static final String TABLE_RES_DETAILS = "res_details";

    // User Table Columns names
    private static final String COLUMN_USER_ID = "user_id";
    private static final String COLUMN_USER_FIRST_NAME = "first_name";
    private static final String COLUMN_USER_LAST_NAME = "last_name";
    private static final String COLUMN_USER_EMAIL = "user_email";
    private static final String COLUMN_USER_PASSWORD = "user_password";
    private static final String COLUMN_USER_PROFILE_PIC = "user_profile_pic";
    private static final String COLUMN_USER_PHONE_NUMBER = "user_phone_number";

    //Res_Details Table Columns names
    private static final String COLUMN_RES_IDS = "res_ids";
    private static final String COLUMN_RES_ID = "res_id";
    private static final String COLUMN_RES_NAME = "res_name";
    private static final String COLUMN_RES_LOCATION = "res_location";
    private static final String COLUMN_RES_CUISINES = "res_cuisines";
    private static final String COLUMN_RES_PIC_URL = "res_pic_url";
    private static final String COLUMN_RES_RATING = "res_rating";

    // create table sql query
    private String CREATE_USER_TABLE = "CREATE TABLE " + TABLE_USER + "("
            + COLUMN_USER_ID + " INTEGER PRIMARY KEY AUTOINCREMENT," + COLUMN_USER_FIRST_NAME + " TEXT,"
            + COLUMN_USER_LAST_NAME + " TEXT," + COLUMN_USER_EMAIL + " TEXT,"
            + COLUMN_USER_PASSWORD + " TEXT," + COLUMN_USER_PROFILE_PIC + " TEXT,"
            + COLUMN_USER_PHONE_NUMBER + " TEXT " + ")";

    private String CREATE_RES_DETAILS_TABLE = "CREATE TABLE " + TABLE_RES_DETAILS + "("
            + COLUMN_RES_IDS + " INTEGER PRIMARY KEY AUTOINCREMENT," + COLUMN_RES_ID + " TEXT,"
            + COLUMN_RES_NAME + " TEXT," + COLUMN_RES_LOCATION + " TEXT," + COLUMN_RES_CUISINES +
            " TEXT," + COLUMN_RES_PIC_URL + " TEXT," + COLUMN_RES_RATING + " TEXT " + ")";

    // drop table sql query
    private String DROP_USER_TABLE = "DROP TABLE IF EXISTS " + TABLE_USER;
    private String DROP_RES_DETAILS_TABLE = "DROP TABLE IF EXISTS " + TABLE_RES_DETAILS;

    /**
     * Constructor
     *
     * @param context
     */
    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_USER_TABLE);
        db.execSQL(CREATE_RES_DETAILS_TABLE);
    }


    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        //Drop User Table if exist
        db.execSQL(DROP_USER_TABLE);
        db.execSQL(DROP_RES_DETAILS_TABLE);

        // Create tables again
        onCreate(db);

    }

    /**
     * This method is to create user record
     *
     * @param user
     */
    public void addUser(User user) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COLUMN_USER_FIRST_NAME, user.getFirstName());
        values.put(COLUMN_USER_LAST_NAME, user.getLastName());
        values.put(COLUMN_USER_EMAIL, user.getEmail());
        values.put(COLUMN_USER_PASSWORD, user.getPassword());
        values.put(COLUMN_USER_PROFILE_PIC, user.getProfilePicPath());
        values.put(COLUMN_USER_PHONE_NUMBER, user.getPhoneNumber());

        // Inserting Row
        db.insert(TABLE_USER, null, values);
        db.close();
    }

    public void addResDetails(RestaurantModelClass modelClass){
        SQLiteDatabase db=this.getWritableDatabase();
        ContentValues values=new ContentValues();
        values.put(COLUMN_RES_ID,modelClass.getResID());
        values.put(COLUMN_RES_NAME,modelClass.getResName());
        values.put(COLUMN_RES_LOCATION,modelClass.getResLocation());
        values.put(COLUMN_RES_CUISINES,modelClass.getResCuisines());
        values.put(COLUMN_RES_RATING,modelClass.getResRating());
        values.put(COLUMN_RES_PIC_URL,modelClass.getResPicUrl());

        db.insert(TABLE_RES_DETAILS,null,values);
        db.close();

    }

    public boolean checkResDetails(String resID) {

        // array of columns to fetch
        String[] columns = {
                COLUMN_RES_IDS
        };
        SQLiteDatabase db = this.getReadableDatabase();

        // selection criteria
        String selection = COLUMN_RES_ID + " = ?";

        // selection argument
        String[] selectionArgs = {resID};

        // query user table with condition
        /**
         * Here query function is used to fetch records from user table this function works like we use sql query.
         * SQL query equivalent to this query function is
         * SELECT user_id FROM user WHERE user_email = 'jack@androidtutorialshub.com';
         */
        Cursor cursor = db.query(TABLE_RES_DETAILS, //Table to query
                columns,                    //columns to return
                selection,                  //columns for the WHERE clause
                selectionArgs,              //The values for the WHERE clause
                null,                       //group the rows
                null,                      //filter by row groups
                null);                      //The sort order
        int cursorCount = cursor.getCount();
        cursor.close();
        db.close();

        if (cursorCount > 0) {
            return true;
        }

        return false;
    }


    /**
     * This method is to fetch all user and return the list of user records
     *
     * @return list
     */

    /**
     * This method to update user record
     *
     * @param
     */
//    public void updateUser(User user) {
//        SQLiteDatabase db = this.getWritableDatabase();
//
//        ContentValues values = new ContentValues();
//        values.put(COLUMN_USER_NAME, user.getName());
//        values.put(COLUMN_USER_EMAIL, user.getEmail());
//        values.put(COLUMN_USER_PASSWORD, user.getPassword());
//
//        // updating row
//        db.update(TABLE_USER, values, COLUMN_USER_ID + " = ?",
//                new String[]{String.valueOf(user.getId())});
//        db.close();
//    }
//
//    /**
//     * This method is to delete user record
//     *
//     * @param user
//     */
//    public void deleteUser(User user) {
//        SQLiteDatabase db = this.getWritableDatabase();
//        // delete user record by id
//        db.delete(TABLE_USER, COLUMN_USER_ID + " = ?",
//                new String[]{String.valueOf(user.getId())});
//        db.close();
//    }
//
//    /**
//     * This method to check user exist or not
//     *
//     * @param email
//     * @return true/false
//     */
    public boolean checkUser(String email) {

        // array of columns to fetch
        String[] columns = {
                COLUMN_USER_ID
        };
        SQLiteDatabase db = this.getReadableDatabase();

        // selection criteria
        String selection = COLUMN_USER_EMAIL + " = ?";

        // selection argument
        String[] selectionArgs = {email};

        // query user table with condition
        /**
         * Here query function is used to fetch records from user table this function works like we use sql query.
         * SQL query equivalent to this query function is
         * SELECT user_id FROM user WHERE user_email = 'jack@androidtutorialshub.com';
         */
        Cursor cursor = db.query(TABLE_USER, //Table to query
                columns,                    //columns to return
                selection,                  //columns for the WHERE clause
                selectionArgs,              //The values for the WHERE clause
                null,                       //group the rows
                null,                      //filter by row groups
                null);                      //The sort order
        int cursorCount = cursor.getCount();
        cursor.close();
        db.close();

        if (cursorCount > 0) {
            return true;
        }

        return false;
    }

    /**
     * This method to check user exist or not
     *
     * @param email
     * @param password
     * @return true/false
     */
    public boolean checkUser(String email, String password) {

        // array of columns to fetch
        String[] columns = {
                COLUMN_USER_ID
        };
        SQLiteDatabase db = this.getReadableDatabase();
        // selection criteria
        String selection = COLUMN_USER_EMAIL + " = ?" + " AND " + COLUMN_USER_PASSWORD + " = ?";

        // selection arguments
        String[] selectionArgs = {email, password};

        // query user table with conditions
        /**
         * Here query function is used to fetch records from user table this function works like we use sql query.
         * SQL query equivalent to this query function is
         * SELECT user_id FROM user WHERE user_email = 'jack@androidtutorialshub.com' AND user_password = 'qwerty';
         */
        Cursor cursor = db.query(TABLE_USER, //Table to query
                columns,                    //columns to return
                selection,                  //columns for the WHERE clause
                selectionArgs,              //The values for the WHERE clause
                null,                       //group the rows
                null,                       //filter by row groups
                null);                      //The sort order

        int cursorCount = cursor.getCount();

        cursor.close();
        db.close();
        if (cursorCount > 0) {
            return true;
        }

        return false;
    }

    public List<User> getAllUser() {
        // array of columns to fetch
        String[] columns = {
                COLUMN_USER_ID,
                COLUMN_USER_EMAIL,
                COLUMN_USER_PASSWORD,
                COLUMN_USER_FIRST_NAME,
                COLUMN_USER_LAST_NAME,
                COLUMN_USER_PHONE_NUMBER,
                COLUMN_USER_PROFILE_PIC
        };
        // sorting orders
        String sortOrder =
                COLUMN_USER_FIRST_NAME + " ASC";
        List<User> userList = new ArrayList<>();

        SQLiteDatabase db = this.getReadableDatabase();

        // query the user table
        /**
         * Here query function is used to fetch records from user table this function works like we use sql query.
         * SQL query equivalent to this query function is
         * SELECT user_id,user_name,user_email,user_password FROM user ORDER BY user_name;
         */
        Cursor cursor = db.query(TABLE_USER, //Table to query
                columns,    //columns to return
                null,        //columns for the WHERE clause
                null,        //The values for the WHERE clause
                null,       //group the rows
                null,       //filter by row groups
                sortOrder); //The sort order


        // Traversing through all rows and adding to list
        if (cursor.moveToFirst()) {
            do {
                User user = new User();
                user.setId(Integer.parseInt(cursor.getString(cursor.getColumnIndex(COLUMN_USER_ID))));
                user.setFirstName(cursor.getString(cursor.getColumnIndex(COLUMN_USER_FIRST_NAME)));
                user.setLastName(cursor.getString(cursor.getColumnIndex(COLUMN_USER_LAST_NAME)));
                user.setEmail(cursor.getString(cursor.getColumnIndex(COLUMN_USER_EMAIL)));
                user.setPassword(cursor.getString(cursor.getColumnIndex(COLUMN_USER_PASSWORD)));
                user.setPhoneNumber(cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_USER_PHONE_NUMBER)));
                user.setProfilePicPath(cursor.getString(cursor.getColumnIndex(COLUMN_USER_PROFILE_PIC)));
                // Adding user record to list
                userList.add(user);
            } while (cursor.moveToNext());
        }
        cursor.close();
        db.close();

        // return user list
        return userList;
    }
}