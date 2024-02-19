package com.example.sportcity;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class DatabaseHelper extends SQLiteOpenHelper {
    private static final String DATABASE_NAME = "sports_fields.db";
    private static final int SCHEMA = 1;

    public static final String TABLE1 = "sports";
    public static final String TABLE1_COLUMN1 = "_id";
    public static final String TABLE1_COLUMN2 = "title";
    public static final String TABLE1_COLUMN3 = "image";

    public static final String TABLE2 = "fields";
    public static final String TABLE2_COLUMN1 = "_id";
    public static final String TABLE2_COLUMN2 = "title";
    public static final String TABLE2_COLUMN3 = "address";
    public static final String TABLE2_COLUMN4 = "opening_hours";
    public static final String TABLE2_COLUMN5 = "phone";
    public static final String TABLE2_COLUMN6 = "type";
    public static final String TABLE2_COLUMN7 = "cost";
    public static final String TABLE2_COLUMN8 = "sport_id";
    public static final String TABLE2_COLUMN9 = "fav_status";
    public static final String TABLE2_COLUMN10 = "image";
    public static final String TABLE2_COLUMN11 = "latitude";
    public static final String TABLE2_COLUMN12 = "longitude";

    public static final String TABLE3 = "fav_fields";
    public static final String TABLE3_COLUMN1 = "_id";
    public static final String TABLE3_COLUMN2 = "field_id";

    public DatabaseHelper(@Nullable Context context) {
        super(context, DATABASE_NAME, null, SCHEMA);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        db.execSQL("CREATE TABLE " + TABLE1 + " (" + TABLE1_COLUMN1 + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                + TABLE1_COLUMN2 + " TEXT, " + TABLE1_COLUMN3 + " TEXT);");

        db.execSQL("CREATE TABLE " + TABLE2 + " (" + TABLE2_COLUMN1 + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                + TABLE2_COLUMN2 + " TEXT, " + TABLE2_COLUMN3 + " TEXT, " + TABLE2_COLUMN4 + " TEXT, "
                + TABLE2_COLUMN5 + " TEXT, " + TABLE2_COLUMN6 + " TEXT, " + TABLE2_COLUMN7 + " TEXT, "
                + TABLE2_COLUMN8 + " INTEGER, " + TABLE2_COLUMN9 + " INTEGER, " + TABLE2_COLUMN10 + " TEXT, " +
                TABLE2_COLUMN11 + " REAL, " + TABLE2_COLUMN12 + " REAL, " +
                "FOREIGN KEY (" + TABLE2_COLUMN8 + ") REFERENCES " + TABLE1 + "(" + TABLE1_COLUMN1 + "));");

        db.execSQL("CREATE TABLE " + TABLE3 + " (" + TABLE3_COLUMN1 + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                + TABLE3_COLUMN2 + " INTEGER, FOREIGN KEY (" + TABLE3_COLUMN2 + ") REFERENCES "
                + TABLE2 + "(" + TABLE2_COLUMN1 + "));");

        initData(db);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE1);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE2);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE3);
        onCreate(db);
    }

    private void initData(SQLiteDatabase db) {
        db.execSQL("INSERT INTO " + TABLE1 + " (" + TABLE1_COLUMN2
                + ", " + TABLE1_COLUMN3 + ") VALUES ('Лыжи', 'skiing.png');");
        db.execSQL("INSERT INTO " + TABLE1 + " (" + TABLE1_COLUMN2
                + ", " + TABLE1_COLUMN3 + ") VALUES ('Хоккей', 'hockey.png');");
        db.execSQL("INSERT INTO " + TABLE1 + " (" + TABLE1_COLUMN2
                + ", " + TABLE1_COLUMN3 + ") VALUES ('Воркаут', 'workout.png');");
        db.execSQL("INSERT INTO " + TABLE1 + " (" + TABLE1_COLUMN2
                + ", " + TABLE1_COLUMN3 + ") VALUES ('Баскетбол', 'basketball.png');");
        db.execSQL("INSERT INTO " + TABLE1 + " (" + TABLE1_COLUMN2
                + ", " + TABLE1_COLUMN3 + ") VALUES ('Футбол', 'football.png');");
        db.execSQL("INSERT INTO " + TABLE1 + " (" + TABLE1_COLUMN2
                + ", " + TABLE1_COLUMN3 + ") VALUES ('Плавание', 'swimming.png');");

        db.execSQL("INSERT INTO " + TABLE2 + " (" + TABLE2_COLUMN2 + ", " + TABLE2_COLUMN3 + ", "
                + TABLE2_COLUMN4 + ", " + TABLE2_COLUMN5 + ", " + TABLE2_COLUMN6 + ", "
                + TABLE2_COLUMN7 + ", " + TABLE2_COLUMN8 + ", " + TABLE2_COLUMN9 + ", " + TABLE2_COLUMN10 + ", "
                + TABLE2_COLUMN11 + ", " + TABLE2_COLUMN12 + ") " +
                "VALUES ('Футбольное поле СФУ', 'Свободный пр., 78Г', 'Круглосуточно', '8 (391) 244-86-25'," +
                " 'Открытый', 'Вход свободный', 5, 0, 'football_1.jpg', 56.006583, 92.769240);");
        db.execSQL("INSERT INTO " + TABLE2 + " (" + TABLE2_COLUMN2 + ", " + TABLE2_COLUMN3 + ", "
                + TABLE2_COLUMN4 + ", " + TABLE2_COLUMN5 + ", " + TABLE2_COLUMN6 + ", "
                + TABLE2_COLUMN7 + ", " + TABLE2_COLUMN8 + ", " + TABLE2_COLUMN9 + ", " + TABLE2_COLUMN10 + ", "
                + TABLE2_COLUMN11 + ", " + TABLE2_COLUMN12 + ") " +
                "VALUES ('Футбол - Арена Енисей', 'Новгородская ул., 11', '08:00-23:00', '8 (391) 222-82-92'," +
                " 'Закрытый', '1500р/час', 5, 0, 'football_2.jpg', 56.058068, 92.983194);");
        db.execSQL("INSERT INTO " + TABLE2 + " (" + TABLE2_COLUMN2 + ", " + TABLE2_COLUMN3 + ", "
                + TABLE2_COLUMN4 + ", " + TABLE2_COLUMN5 + ", " + TABLE2_COLUMN6 + ", "
                + TABLE2_COLUMN7 + ", " + TABLE2_COLUMN8 + ", " + TABLE2_COLUMN9 + ", " + TABLE2_COLUMN10 + ", "
                + TABLE2_COLUMN11 + ", " + TABLE2_COLUMN12 + ") " +
                "VALUES ('Площадка для воркаута', 'Советский район', 'Круглосуточно', '-'," +
                " 'Открытый', 'Вход свободный', 3, 0, 'workout_2.jpg', 56.050497, 92.959000);");
        db.execSQL("INSERT INTO " + TABLE2 + " (" + TABLE2_COLUMN2 + ", " + TABLE2_COLUMN3 + ", "
                + TABLE2_COLUMN4 + ", " + TABLE2_COLUMN5 + ", " + TABLE2_COLUMN6 + ", "
                + TABLE2_COLUMN7 + ", " + TABLE2_COLUMN8 + ", " + TABLE2_COLUMN9 + ", " + TABLE2_COLUMN10 + ", "
                + TABLE2_COLUMN11 + ", " + TABLE2_COLUMN12 + ") " +
                "VALUES ('Площадка на набережной', 'ул. Дубровинского', 'Круглосуточно', '-'," +
                " 'Открытый', 'Вход свободный', 3, 0, 'workout_1.jpg', 56.004366, 92.851757);");
        db.execSQL("INSERT INTO " + TABLE2 + " (" + TABLE2_COLUMN2 + ", " + TABLE2_COLUMN3 + ", "
                + TABLE2_COLUMN4 + ", " + TABLE2_COLUMN5 + ", " + TABLE2_COLUMN6 + ", "
                + TABLE2_COLUMN7 + ", " + TABLE2_COLUMN8 + ", " + TABLE2_COLUMN9 + ", " + TABLE2_COLUMN10 + ", "
                + TABLE2_COLUMN11 + ", " + TABLE2_COLUMN12 + ") " +
                "VALUES ('Баскетбольная площадка', 'Остров Татышева', 'Круглосуточно', '-'," +
                " 'Открытый', 'Вход свободный', 4, 0, 'basketball_1.jpg', 56.027684, 92.939828);");
        db.execSQL("INSERT INTO " + TABLE2 + " (" + TABLE2_COLUMN2 + ", " + TABLE2_COLUMN3 + ", "
                + TABLE2_COLUMN4 + ", " + TABLE2_COLUMN5 + ", " + TABLE2_COLUMN6 + ", "
                + TABLE2_COLUMN7 + ", " + TABLE2_COLUMN8 + ", " + TABLE2_COLUMN9 + ", " + TABLE2_COLUMN10 + ", "
                + TABLE2_COLUMN11 + ", " + TABLE2_COLUMN12 + ") " +
                "VALUES ('Красный Яр', 'Маерчака, 57', '10:00-23:00', '8 (999) 441-55-51'," +
                " 'Закрытый', '700р/час', 4, 0, 'basketball_2.jpg', 56.032808, 92.831236);");
        db.execSQL("INSERT INTO " + TABLE2 + " (" + TABLE2_COLUMN2 + ", " + TABLE2_COLUMN3 + ", "
                + TABLE2_COLUMN4 + ", " + TABLE2_COLUMN5 + ", " + TABLE2_COLUMN6 + ", "
                + TABLE2_COLUMN7 + ", " + TABLE2_COLUMN8 + ", " + TABLE2_COLUMN9 + ", " + TABLE2_COLUMN10 + ", "
                + TABLE2_COLUMN11 + ", " + TABLE2_COLUMN12 + ") " +
                "VALUES ('Хоккейная коробка', 'Мате Залки, 4а', 'Круглосуточно', '-'," +
                " 'Открытый', 'Вход свободный', 2, 0, 'hockey_1.jpg', 56.067642, 92.941144);");
        db.execSQL("INSERT INTO " + TABLE2 + " (" + TABLE2_COLUMN2 + ", " + TABLE2_COLUMN3 + ", "
                + TABLE2_COLUMN4 + ", " + TABLE2_COLUMN5 + ", " + TABLE2_COLUMN6 + ", "
                + TABLE2_COLUMN7 + ", " + TABLE2_COLUMN8 + ", " + TABLE2_COLUMN9 + ", " + TABLE2_COLUMN10 + ", "
                + TABLE2_COLUMN11 + ", " + TABLE2_COLUMN12 + ") " +
                "VALUES ('Спартаковец', 'ул. Республики, 43а', 'Круглосуточно', '-'," +
                " 'Открытый', 'Вход свободный', 2, 0, 'hockey_2.jpg', 56.015607, 92.849181);");
        db.execSQL("INSERT INTO " + TABLE2 + " (" + TABLE2_COLUMN2 + ", " + TABLE2_COLUMN3 + ", "
                + TABLE2_COLUMN4 + ", " + TABLE2_COLUMN5 + ", " + TABLE2_COLUMN6 + ", "
                + TABLE2_COLUMN7 + ", " + TABLE2_COLUMN8 + ", " + TABLE2_COLUMN9 + ", " + TABLE2_COLUMN10 + ", "
                + TABLE2_COLUMN11 + ", " + TABLE2_COLUMN12 + ") " +
                "VALUES ('Лыжная база - Берёзка', 'ул. Саянская, 7Ж', '09:00-17:00', '8 (391) 295–09–31'," +
                " 'Открытый', '100р/час', 1, 0, 'skiing_1.jpg', 55.966338, 92.848227);");
        db.execSQL("INSERT INTO " + TABLE2 + " (" + TABLE2_COLUMN2 + ", " + TABLE2_COLUMN3 + ", "
                + TABLE2_COLUMN4 + ", " + TABLE2_COLUMN5 + ", " + TABLE2_COLUMN6 + ", "
                + TABLE2_COLUMN7 + ", " + TABLE2_COLUMN8 + ", " + TABLE2_COLUMN9 + ", " + TABLE2_COLUMN10 + ", "
                + TABLE2_COLUMN11 + ", " + TABLE2_COLUMN12 + ") " +
                "VALUES ('МФК Радуга', 'ул. Е. Стасовой, 69Л', '08:00-22:00', '8 (391) 222-11-55'," +
                " 'Открытый', '200р/час', 1, 0, 'skiing_2.jpg', 56.008846, 92.723993);");
        db.execSQL("INSERT INTO " + TABLE2 + " (" + TABLE2_COLUMN2 + ", " + TABLE2_COLUMN3 + ", "
                + TABLE2_COLUMN4 + ", " + TABLE2_COLUMN5 + ", " + TABLE2_COLUMN6 + ", "
                + TABLE2_COLUMN7 + ", " + TABLE2_COLUMN8 + ", " + TABLE2_COLUMN9 + ", " + TABLE2_COLUMN10 + ", "
                + TABLE2_COLUMN11 + ", " + TABLE2_COLUMN12 + ") " +
                "VALUES ('Сибиряк', 'ул. Ладо Кецховели, 62', '07:00-23:00', '8 (391) 223-86-01'," +
                " 'Закрытый', '200р/час', 6, 0, 'swimming_1.jpg', 56.020915, 92.813997);");
        db.execSQL("INSERT INTO " + TABLE2 + " (" + TABLE2_COLUMN2 + ", " + TABLE2_COLUMN3 + ", "
                + TABLE2_COLUMN4 + ", " + TABLE2_COLUMN5 + ", " + TABLE2_COLUMN6 + ", "
                + TABLE2_COLUMN7 + ", " + TABLE2_COLUMN8 + ", " + TABLE2_COLUMN9 + ", " + TABLE2_COLUMN10 + ", "
                + TABLE2_COLUMN11 + ", " + TABLE2_COLUMN12 + ") " +
                "VALUES ('Клуб Сибирь', 'ул. Карамзина, 26', '08:30-21:30', '8 (391) 271‒40‒08'," +
                " 'Закрытый', '150р/час', 6, 0, 'swimming_2.jpg', 55.988897, 92.84980);");
    }
}
