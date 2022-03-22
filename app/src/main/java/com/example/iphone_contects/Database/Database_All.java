package com.example.iphone_contects.Database;


import android.content.Context;
import android.os.AsyncTask;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteDatabase;

@Database(entities = {Contact.class}, version = 1)
public abstract class Database_All extends RoomDatabase {
    private static Database_All instance;

    public abstract Contact_Dao getDao();


    public static synchronized Database_All getInstance(Context context) {
        if (instance == null) {
            instance = Room.databaseBuilder(context.getApplicationContext()
                    , Database_All.class, "all_database")
                    .fallbackToDestructiveMigration()
                    .addCallback(contacts_callback)
                    .build();
        }
        return instance;
    }

    private static RoomDatabase.Callback contacts_callback = new Callback() {
        @Override
        public void onCreate(@NonNull SupportSQLiteDatabase db) {
            super.onCreate(db);
            new contactAsync(instance).execute();

        }
    };

    private static class contactAsync extends AsyncTask<Void, Void, Void> {
        private Contact_Dao contactDao;

        private contactAsync(Database_All instance) {
            contactDao = instance.getDao();

        }

        @Override
        protected Void doInBackground(Void... voids) {

            contactDao.insert_contact(new Contact("مصطفى", "نعمه",
                    "07825313141", "null",
                    "mostafa.n3ma@gmail.com"
                    , "Kut/damuk",true
            ));

            contactDao.insert_contact(new Contact("وئام", "فاضل",
                    "07825313141", "null",
                    "jena.mostafa@gmail.com"
                    , "Kut/damuk",true
            ));
            contactDao.insert_contact(new Contact("علي", "خلف",
                    "07801308908", "null",
                    "nema.rashed@gmail.com"
                    , "Kut/damuk",true
            ));

            contactDao.insert_contact(new Contact("مرتضى", "منير",
                    "07825313141", "null",
                    "mostafa.n3ma@gmail.com"
                    , "Kut/damuk",true
            ));

            contactDao.insert_contact(new Contact("jena", "mostafa",
                    "07825313141", "null",
                    "jena.mostafa@gmail.com"
                    , "Kut/damuk",false
            ));
            contactDao.insert_contact(new Contact("نعمه", "رشيد",
                    "07801308908", "null",
                    "nema.rashed@gmail.com"
                    , "Kut/damuk",false
            ));

            contactDao.insert_contact(new Contact("علي", "لامي السايق",
                    "07825313141", "null",
                    "mostafa.n3ma@gmail.com"
                    , "Kut/damuk",false
            ));

            contactDao.insert_contact(new Contact("محمد", "مهدي",
                    "07825313141", "null",
                    "jena.mostafa@gmail.com"
                    , "Kut/damuk",false
            ));
            contactDao.insert_contact(new Contact("علي", "محمود",
                    "07801308908", "null",
                    "nema.rashed@gmail.com"
                    , "Kut/damuk",false
            ));

            contactDao.insert_contact(new Contact("سالم", "عباس",
                    "07825313141", "null",
                    "mostafa.n3ma@gmail.com"
                    , "Kut/damuk",false
            ));

            contactDao.insert_contact(new Contact("مهند", "ناهض",
                    "07825313141", "null",
                    "jena.mostafa@gmail.com"
                    , "Kut/damuk",false
            ));
            contactDao.insert_contact(new Contact("صفاء", "الدين",
                    "07801308908", "null",
                    "nema.rashed@gmail.com"
                    , "Kut/damuk",false
            ));


            contactDao.insert_contact(new Contact("مصطفى", "عرموط",
                    "07825313141", "null",
                    "jena.mostafa@gmail.com"
                    , "Kut/damuk",false
            ));
            contactDao.insert_contact(new Contact("كرار", "موسى",
                    "07801308908", "null",
                    "nema.rashed@gmail.com"
                    , "Kut/damuk",false
            ));

            contactDao.insert_contact(new Contact("سجاد", "اتحاد",
                    "07825313141", "null",
                    "jena.mostafa@gmail.com"
                    , "Kut/damuk",false
            ));
            contactDao.insert_contact(new Contact("عباس", "ابن فرناس",
                    "07801308908", "null",
                    "nema.rashed@gmail.com"
                    , "Kut/damuk",false
            ));

            contactDao.insert_contact(new Contact("فخري", "  ",
                    "07825313141", "null",
                    "jena.mostafa@gmail.com"
                    , "Kut/damuk",false
            ));
            contactDao.insert_contact(new Contact("مطعم", "ابن خرميط",
                    "07801308908", "null",
                    "nema.rashed@gmail.com"
                    , "Kut/damuk",false
            ));

            contactDao.insert_contact(new Contact("مطعم", "الرسول",
                    "07825313141", "null",
                    "jena.mostafa@gmail.com"
                    , "Kut/damuk",false
            ));
            contactDao.insert_contact(new Contact("مطعم", "السدة",
                    "07801308908", "null",
                    "nema.rashed@gmail.com"
                    , "Kut/damuk",false
            ));

            return null;
        }
    }

}
