package com.example.secretsantaservi.androidrepository;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import com.google.gson.Gson;

import java.util.HashMap;

import repository.RepositoryInterface;

public class RepositorySQLite<K, V> implements RepositoryInterface<K, V> {
    private SQLiteDatabase db;
    private Class keyClass;
    private Class valueClass;

    private static final String TABLE = "repo_table";
    private static final String COLUMN_KEY = "id";
    private static final String COLUMN_VALUE = "value";

    public RepositorySQLite(Context context, String dbFileName, Class keyClass, Class valueClass) { //объекты хранятся в JSON, класс нужен, чтобы распарсить JSON
        this.keyClass = keyClass;
        this.valueClass = valueClass;
        this.db = context.openOrCreateDatabase(dbFileName, Context.MODE_PRIVATE, null);
        this.db.execSQL("CREATE TABLE IF NOT EXISTS " +
                TABLE + " (_id INTEGER PRIMARY KEY AUTOINCREMENT, " +
                COLUMN_KEY + " TEXT, " +
                COLUMN_VALUE + " TEXT)");
    }

    @Override
    public void add(K id, V object) {
        ContentValues values = new ContentValues();
        Gson gson = new Gson();
        String jsonId = gson.toJson(id);
        String jsonValue = gson.toJson(object);
/*        Cursor cursor = db.query(TABLE, new String[]{COLUMN_VALUE}, COLUMN_KEY + " LIKE ?", new String[]{jsonId}, null, null, null);
        if (cursor.moveToNext()) {
            db.delete(TABLE, COLUMN_KEY + " LIKE ?", new String[]{jsonId});
        }
        cursor.close();*/
        values.put(COLUMN_KEY, gson.toJson(id));
        values.put(COLUMN_VALUE, gson.toJson(object));
        db.insert(TABLE, null, values);
    }

    @Override
    public V getById(K id) {
        Gson gson = new Gson();
        String jsonId = gson.toJson(id);
        Cursor cursor = db.query(TABLE, new String[]{COLUMN_VALUE},
                COLUMN_KEY + " LIKE ?", new String[]{jsonId}, null, null, null);
        if (cursor.moveToNext()) {
            String jsonValue = cursor.getString(cursor.getColumnIndex(COLUMN_VALUE));
            cursor.close();
            return (V) gson.fromJson(jsonValue, valueClass);
        }
        cursor.close();
        return null;
    }

    @Override
    public void deleteById(K id) {
        Gson gson = new Gson();
        String jsonId = gson.toJson(id);
        db.delete(TABLE, COLUMN_KEY + " LIKE ?", new String[]{jsonId});
    }

    @Override
    public void update(K id, V object) {
        ContentValues values = new ContentValues();
        Gson gson = new Gson();
        String jsonId = gson.toJson(id);
        String jsonValue = gson.toJson(object);
        Cursor cursor = db.query(TABLE, new String[]{COLUMN_VALUE},
                COLUMN_KEY + " LIKE ?", new String[]{jsonId}, null, null, null);
        if (cursor.moveToNext()) {
            db.delete(TABLE, COLUMN_KEY + " LIKE ?", new String[]{jsonId});
        }
        cursor.close();
        values.put(COLUMN_KEY, gson.toJson(id));
        values.put(COLUMN_VALUE, gson.toJson(object));
        db.insert(TABLE, null, values);
    }

    @Override
    public HashMap<K, V> getAll() {
        HashMap<K, V> hashMap = new HashMap<>();
        Cursor cursor = db.query(TABLE, new String[]{COLUMN_KEY, COLUMN_VALUE}, null, null, null, null, null);
        String jsonObject;
        String jsonKey;
        Gson gson = new Gson();
        while (cursor.moveToNext()) {
            jsonObject = cursor.getString(cursor.getColumnIndex(COLUMN_VALUE));
            jsonKey = cursor.getString(cursor.getColumnIndex(COLUMN_KEY));
            hashMap.put((K) gson.fromJson(jsonKey, keyClass), (V) gson.fromJson(jsonObject, valueClass));
        }
        cursor.close();
        return hashMap;
    }
}
