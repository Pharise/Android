package com.example.sportcity;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

public class DatabaseAdapter {
    private DatabaseHelper dbHelper;
    private SQLiteDatabase database;

    public DatabaseAdapter(Context context){
        dbHelper = new DatabaseHelper(context.getApplicationContext());
    }

    public DatabaseAdapter open(){
        database = dbHelper.getWritableDatabase();
        return this;
    }

    public void close(){
        dbHelper.close();
    }

    public List<Sport> getSports() {
        List<Sport> sports = new ArrayList<>();
        String[] columns = new String[] {DatabaseHelper.TABLE1_COLUMN1,
                DatabaseHelper.TABLE1_COLUMN2, DatabaseHelper.TABLE1_COLUMN3};
        Cursor cursor = database.query(DatabaseHelper.TABLE1, columns, null,
                null, null, null, null);
        while (cursor.moveToNext()){
            sports.add(new Sport(cursor.getInt(0), cursor.getString(1), cursor.getString(2)));
        }
        cursor.close();
        return sports;
    }

    public List<Field> getFields() {
        List<Field> fields = new ArrayList<>();
        String[] columns = new String[] {DatabaseHelper.TABLE2_COLUMN1, DatabaseHelper.TABLE2_COLUMN2,
                DatabaseHelper.TABLE2_COLUMN3, DatabaseHelper.TABLE2_COLUMN4, DatabaseHelper.TABLE2_COLUMN5,
                DatabaseHelper.TABLE2_COLUMN6, DatabaseHelper.TABLE2_COLUMN7, DatabaseHelper.TABLE2_COLUMN8,
                DatabaseHelper.TABLE2_COLUMN9, DatabaseHelper.TABLE2_COLUMN10, DatabaseHelper.TABLE2_COLUMN11,
                DatabaseHelper.TABLE2_COLUMN12};
        Cursor cursor = database.query(DatabaseHelper.TABLE2, columns, null,
                null, null, null, null);
        while (cursor.moveToNext()){
            fields.add(new Field(cursor.getInt(0), cursor.getString(1), cursor.getString(2),
                    cursor.getString(3), cursor.getString(4), cursor.getString(5), cursor.getString(6),
                    cursor.getInt(7), cursor.getInt(8), cursor.getString(9), cursor.getDouble(10),
                    cursor.getDouble(11)));
        }
        cursor.close();
        return fields;
    }

    public List<Integer> getFavIds() {
        List<Integer> favIds = new ArrayList<>();
        String[] columns = new String[] {DatabaseHelper.TABLE3_COLUMN1, DatabaseHelper.TABLE3_COLUMN2};
        Cursor cursor = database.query(DatabaseHelper.TABLE3, columns, null,
                null, null, null, null);
        while (cursor.moveToNext()){
            favIds.add(cursor.getInt(1));
        }
        cursor.close();
        return favIds;
    }

    public long addToFavorites(int id){
        ContentValues cv = new ContentValues();
        cv.put(DatabaseHelper.TABLE3_COLUMN2, id);
        return  database.insert(DatabaseHelper.TABLE3, null, cv);
    }

    public long changeFavStatus(int id, int status){

        String whereClause = DatabaseHelper.TABLE2_COLUMN1 + "=" + id;
        ContentValues cv = new ContentValues();
        cv.put(DatabaseHelper.TABLE2_COLUMN9, status);
        return database.update(DatabaseHelper.TABLE2, cv, whereClause, null);
    }

    public long deleteFromFavorites(int id){

        String whereClause = "field_id = ?";
        String[] whereArgs = new String[]{String.valueOf(id)};
        return database.delete(DatabaseHelper.TABLE3, whereClause, whereArgs);
    }
}
