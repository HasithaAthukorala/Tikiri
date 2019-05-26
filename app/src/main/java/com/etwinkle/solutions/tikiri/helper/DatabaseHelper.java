//package com.etwinkle.solutions.tikiri.helper;
//
//import android.content.Context;
//import android.database.sqlite.SQLiteDatabase;
//import android.database.sqlite.SQLiteOpenHelper;
//
//public class DatabaseHandler extends SQLiteOpenHelper {
//    public static int DATABASE_VERSION = 1;
//    public static final String DATABASE_NAME = "TIKIRI";
//
//    public static final String tblLetters_Name = "letters", tblLetters_col1 = "letterID", tblLetters_col2 = "type", tblLetters_col3 = "letter";
//
//    public static final String tblWords_Name = "words", tblWords_col1 = "wordID", tblWords_col2 = "letter1_ID", tblWords_col3 = "letter2_ID", tblWords_col4 = "letter3_ID";
//
//    public static String tblLevels_Name = "levels",tblLevels_col1 = "levelID", tblLevels_col2 = "word1_ID", tblLevels_col3 = "word2ID";
//
//    //constructor
//    public DatabaseHandler(Context context){
//        super(context, DATABASE_NAME, null, DATABASE_VERSION);
//        SQLiteDatabase db=this.getWritableDatabase();
//    }
//
//    @Override
//    public void onCreate(SQLiteDatabase db){
//        db.execSQL("CREATE TABLE "+tblLetters_Name+"("+tblLetters_col1+" INTEGER PRIMARY KEY AUTOINCREMENT, "+tblLetters_col2+" ENUM (\"vowel\", \"consonant\", \"composite\"), "+tblLetters_col3+" TEXT))");
//        db.execSQL("CREATE TABLE "+tblWords_Name+"("+tblWords_col1+" INTEGER PRIMARY KEY AUTOINCREMENT, "+tblWords_col2+" INTEGER NOT NULL, "+tblWords_col3+" INTEGER NOT NULL, "
//                +tblWords_col4+" INTEGER, CONSTRAINT letter FOREIGN KEY ("+tblWords_col2+", "+tblWords_col3+", "+tblWords_col4+") REFERENCES "+tblLetters_Name+"("+tblLetters_col1+"))");
//        db.execSQL("CREATE TABLE "+tblLevels_Name+"("+tblLevels_col1+" INTEGER PRIMARY KEY AUTOINCREMENT, "+tblLevels_col2+" INTEGER, "+tblLevels_col3+" INTEGER, "+ "FOREIGN KEY ("+tblLevels_col2+") REFERENCES "+tblWords_Name+"("+tblWords_col1+"), FOREIGN KEY ("+tblLevels_col3+") REFERENCES "+tblWords_Name+"("+tblWords_col1+"))")
//        insertData(db);
//    }
//
//    @Override
//    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
//        db.execSQL("DROP TABLE IF EXISTS "+tblLetters_Name);
//        db.execSQL("DROP TABLE IF EXISTS "+tblWords_Name);
//        db.execSQL("DROP TABLE IF EXISTS "+tblLevels_Name);
//        onCreate(db);
//    }
//
//    public void insertData(SQLiteDatabase db){
//        db.execSQL("INSERT INTO "+tblLetters_Name+" VALUES (001, consonant, \"ම\"), (002, consonant, \"ල\"), (003, vowel, \"ඌ\"), (004, composite, \"රා\")";
//        db.execSQL("INSERT INTO "+tblWords_Name+" VALUES (001, 001, 002), (002, 003, 004)");
//        db.execSQL("INSERT INTO "+tblLevels_Name+"VALUES (001, 001, null), (002, 002, null)");
//       /* db.execSQL("INSERT INTO "+tblConsonants_Name+" VALUES (001, \"ක\"), (002, \"ච\"), (003, \"ට\"), (004, \"ත\"), (005, \"ප\"), (006, \"ග\"), (007, \"ජ\"), " +
//                "(008, \"ඩ\"), (009, \"ද\"), (010, \"බ\"), (011, \"ය\"), (012, \"ර\"), (013, \"ල\"), (014, \"ව\"), (015, \"ස\"), (016, \"න\"), (017, \"ම\"), " +
//                "(018, \"ළ\"), (019, \"ණ\")");
//        db.execSQL("INSERT INTO "+tblVowels_Name+" VALUES (101, \"අ\"), (102, \"ආ\",), (103, \"ඇ\"), (104, \"ඈ\"), (105, \"ඉ\"), (106, \"ඊ\"), (107, \"උ\"), " +
//                "(108, \"ඌ\"), (109, \"එ\"), (110, \"ඒ\"), (111, \"ඔ\"), (112, \"ඕ\")");
//        db.execSQL("INSERT INTO "+tblComposites_Name+" VALUES (1001, \"රා\", 012, 102), (1002, \"ටු\", 003, 107), (1003, \"වා\", 014, 102), (1004, \"පු\", 005, 107), " +
//                "(1005, \"ටා\", 003, 102), (1006, \"රි\", 012, 105), (1007, \"යා\", 011, 102)");
//        db.execSQL("INSERT INTO "+tbl2LtrWords_Name+" VALUES (2001, \"මල\", 017,013), (2002, \"ඇස\", 104, 015), (2003, \"ඉර\", 105, 012), (2004, \"ඌරා\"), " +
//                "(2005, \"ගස\")");
//        db.execSQL("INSERT INTO "+tbl3LtrWords_Name+" VALUES (3001, \"ඔටුවා\", 111, 1002, 1003), (3002, \"කපුටා\", 001,1004,1005), (3003, \"නරියා\", 016, 1006, 1007)");
//*/
//    }
//
//}