package com.etwinkle.solutions.tikiri.helper;

/**
 * Created by kashuni on 4/29/18.
 */

import android.content.Context;
import android.util.Log;

import com.etwinkle.solutions.tikiri.adapter.SQLiteAdapter;
import com.etwinkle.solutions.tikiri.model.Level;
import com.etwinkle.solutions.tikiri.model.Word;

import java.util.ArrayList;

public class SQLiteHelper {
    private static SQLiteAdapter sqLiteAdapter;

    public SQLiteHelper(Context context)
    {
        sqLiteAdapter = new SQLiteAdapter(context);
    }

    public static ArrayList<Level> getLevels() {
        return sqLiteAdapter.getLevels();
    }
    public static ArrayList<Word> getWords(String level) {
        return sqLiteAdapter.getWords(level);
    }

    public static int updateLevel(String level)
    {
       return sqLiteAdapter.updateLevel(level);
    }
//    public static void execute() {
//        sqLiteAdapter.execute();
//    }
}