package com.etwinkle.solutions.tikiri.adapter;

/**
 * Created by kashuni on 4/29/18.
 */

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import com.etwinkle.solutions.tikiri.model.Level;
import com.etwinkle.solutions.tikiri.model.Word;

import java.util.ArrayList;

public class SQLiteAdapter {
    myDbHelper myhelper;
    public SQLiteAdapter(Context context)
    {
        myhelper = new myDbHelper(context);
    }

//    public void insertData(ProductObject productObject)
//    {
////        Log.d("Here is the testing db",productObject.getColor());
//        try {
//            SQLiteDatabase dbb = myhelper.getWritableDatabase();
//            ContentValues contentValues = new ContentValues();
//            contentValues.put(myDbHelper.ID, productObject.getId());
//            contentValues.put(myDbHelper.CUSTOMER_ID, productObject.getCustomer_id());
//            contentValues.put(myDbHelper.CATEGORIES_ID, productObject.getCategories_id());
//            contentValues.put(myDbHelper.NAME, productObject.getNames());
//            contentValues.put(myDbHelper.DESCRIPTION, productObject.getDescription());
//            contentValues.put(myDbHelper.IMAGE, productObject.getImages());
//            contentValues.put(myDbHelper.OBJ, productObject.getObj());
//            contentValues.put(myDbHelper.COLOR, productObject.getColor());
//            contentValues.put(myDbHelper.SIZE, productObject.getSize());
//            contentValues.put(myDbHelper.PRIZE, productObject.getPrice());
//            contentValues.put(myDbHelper.QUANTITY, productObject.getQuantity());
//            contentValues.put(myDbHelper.STATUS, productObject.getStatus());
//            contentValues.put(myDbHelper.CREATED_AT, productObject.getCreated_at());
//            contentValues.put(myDbHelper.FAVOURITE, productObject.getFavorite());
//            contentValues.put(myDbHelper.COUPAN, productObject.getCoupon_type());
//            contentValues.put(myDbHelper.DISCOUNT, productObject.getDiscount());
//            contentValues.put(myDbHelper.ENDING_DATE, productObject.getEnding_date());
//            contentValues.put(myDbHelper.DISCOUNT_VALUE, productObject.getDiscount_value());
//            long id = dbb.insert(myDbHelper.TABLE_NAME, null, contentValues);
//        }catch (NullPointerException k){
//
//        }
//
//    }
//
    public ArrayList<Level> getLevels() {
        try {
            ArrayList<Level> items = new ArrayList<>();
            SQLiteDatabase db = myhelper.getWritableDatabase();
            String[] columns = {myDbHelper.LEVEL_ID, myDbHelper.LEVEL_STATUS};
            Cursor cursor = db.query(myDbHelper.LEVEL_TABLE_NAME, columns, null, null, null, null, null);
            StringBuffer buffer = new StringBuffer();
            while (cursor.moveToNext()) {
                int cid = cursor.getInt(cursor.getColumnIndex(myDbHelper.LEVEL_ID));
                int levelStatus = cursor.getInt(cursor.getColumnIndex(myDbHelper.LEVEL_STATUS));
                Log.d("levelllllleeee",String.valueOf(cid));
                Level level = new Level(cid,levelStatus);
                items.add(level);
//            String  password =cursor.getString(cursor.getColumnIndex(myDbHelper.MyPASSWORD));
                //buffer.append(cid + "   " + name + "   " + " \n" + date + "   " + " \n" + tags + "   " + " \n" + cover + "   " + " \n");
            }
            return items;
        }catch (SQLException s){
            ArrayList<Level> items = new ArrayList<>();
            return items;
        }
    }
    public ArrayList<Word> getWords(String level) {
        try {
            ArrayList<Word> items = new ArrayList<>();
            SQLiteDatabase db = myhelper.getWritableDatabase();
            String sql="SELECT * FROM "+ myDbHelper.WORD_TABLE_NAME+" WHERE "+ myDbHelper.LEVEL+" ="+level;
            Cursor cursor = db.rawQuery(sql,null);
            StringBuffer buffer = new StringBuffer();
            while (cursor.moveToNext()) {
                int id = cursor.getInt(cursor.getColumnIndex(myDbHelper.ID));
                int levelstr = cursor.getInt(cursor.getColumnIndex(myDbHelper.LEVEL));
                String word = cursor.getString(cursor.getColumnIndex(myDbHelper.WORD));
                String list = cursor.getString(cursor.getColumnIndex(myDbHelper.LIST));
                String image = cursor.getString(cursor.getColumnIndex(myDbHelper.IMAGE));
                Word word1 = new Word(id,levelstr,word,list,image);
                items.add(word1);
//            String  password =cursor.getString(cursor.getColumnIndex(myDbHelper.MyPASSWORD));
                //buffer.append(cid + "   " + name + "   " + " \n" + date + "   " + " \n" + tags + "   " + " \n" + cover + "   " + " \n");
            }
            return items;
        }catch (SQLException s){
            ArrayList<Word> items = new ArrayList<>();
            return items;
        }
    }

//    public void execute(){
//        Log.d("levelll","2");
//        SQLiteDatabase db = myhelper.getWritableDatabase();
//        for(int i = 1;i<11;i++){
//            Log.d("levellll","INSERT INTO "+myDbHelper.LEVEL_TABLE_NAME+" VALUES (i, 0);");
//            db.execSQL("INSERT INTO "+myDbHelper.LEVEL_TABLE_NAME+" VALUES ("+String.valueOf(i)+", 0);");
//            Log.d("levellll","INSERT INTO "+myDbHelper.LEVEL_TABLE_NAME+" VALUES (i, 0);");
//        }
//        db.execSQL("INSERT INTO "+myDbHelper.WORD_TABLE_NAME+" VALUES (1,1,'මල','ම,ල','http://images.clipartpanda.com/flower-clip-art-21168-blue-flower-svg.svg');");
//        db.execSQL("INSERT INTO "+myDbHelper.WORD_TABLE_NAME+" VALUES (2,2,'ඌරා','ඌ,රා','http://images.clipartpanda.com/flower-clip-art-21168-blue-flower-svg.svg');");
//    }
//
//    public  int delete(String uname)
//    {
//        SQLiteDatabase db = myhelper.getWritableDatabase();
//        String[] whereArgs ={uname};
//
//        int count =db.delete(myDbHelper.TABLE_NAME , myDbHelper.ID+" = ?",whereArgs);
//        return  count;
//    }
//
    public int updateLevel(String level)
    {
        SQLiteDatabase db = myhelper.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(myDbHelper.LEVEL_STATUS,1);
        String[] whereArgs= {level};
        int count =db.update(myDbHelper.LEVEL_TABLE_NAME,contentValues, myDbHelper.LEVEL_ID+" = ?",whereArgs );
        return count;
    }

    static class myDbHelper extends SQLiteOpenHelper
    {
        private static final String DATABASE_NAME = "etwinkledatabase";    // Database Name
        private static final String LEVEL_TABLE_NAME = "level_table";   // Table Name
        private static final String WORD_TABLE_NAME = "word_table";   // Table Name
        private static final int DATABASE_Version = 4;    // Database Version
        private static final String LEVEL_ID="level_id";     // Column I (Primary Key)
        private static final String ID="id";     // Column I (Primary Key)
        private static final String LEVEL_STATUS = "status";//Column II
        private static final String WORD = "word";
        private static final String LEVEL = "level";
        private static final String LIST = "list";
        private static final String IMAGE = "image";
        //private static final String CONTENT= "Password";    // Column III
        public static final String CREATE_WORD_TABLE = "CREATE TABLE "+WORD_TABLE_NAME+" ("+ID+" INTEGER PRIMARY KEY AUTOINCREMENT, "+LEVEL+" INTEGER, "+WORD+" TEXT, "+LIST+" TEXT, "+IMAGE+" TEXT);";
        public static final String CREATE_LEVEL_TABLE = "CREATE TABLE "+LEVEL_TABLE_NAME+" ("+LEVEL_ID+" INTEGER PRIMARY KEY AUTOINCREMENT, "+LEVEL_STATUS+" INTEGER);";
        private static final String DROP_TABLE ="DROP TABLE IF EXISTS "+WORD_TABLE_NAME;
        private static final String DROP_TABLE_2 ="DROP TABLE IF EXISTS "+LEVEL_TABLE_NAME;
        private Context context;

        public myDbHelper(Context context) {
            super(context, DATABASE_NAME, null, DATABASE_Version);
            this.context=context;
            SQLiteDatabase db = getWritableDatabase();
            onCreate(db);
        }

        public void onCreate(SQLiteDatabase db) {
            String[] statements = new String[]{CREATE_LEVEL_TABLE, CREATE_WORD_TABLE};
            try {
                Log.d("levelll","1");
                for(String sql : statements){
                    db.execSQL(sql);
                }
                Log.d("levelll","2");
                for(int i = 1;i<11;i++){
                    Log.d("levellll","INSERT INTO "+LEVEL_TABLE_NAME+" VALUES (i, 0);");
                    db.execSQL("INSERT INTO "+LEVEL_TABLE_NAME+" VALUES ("+String.valueOf(i)+", '0');");
                    Log.d("levellll","INSERT INTO "+LEVEL_TABLE_NAME+" VALUES (i, 0);");
                }
                db.execSQL("INSERT INTO "+WORD_TABLE_NAME+" VALUES (1,1,'මල','ම,ල','http://clipart-library.com/images/8TzraLKgc.png');");
                db.execSQL("INSERT INTO "+WORD_TABLE_NAME+" VALUES (2,2,'ඌරා','ඌ,රා','http://www.sclance.com/pngs/cartoon-pig-png/cartoon_pig_png_228196.png');");
            } catch (Exception e) {
                //Message.message(context,""+e);
            }
        }

        @Override
        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
            try {
                //Message.message(context,"OnUpgrade");
                db.execSQL(DROP_TABLE);
                db.execSQL(DROP_TABLE_2);
                onCreate(db);
            }catch (Exception e) {
                //Message.message(context,""+e);
            }
        }
    }
}