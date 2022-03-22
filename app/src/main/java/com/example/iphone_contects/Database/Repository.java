package com.example.iphone_contects.Database;

import android.app.Application;
import android.os.AsyncTask;

import androidx.lifecycle.LiveData;

import java.util.List;

public class Repository {
    private Contact_Dao dao;
    private LiveData<List<Contact>> all_Contact;
    private LiveData<List<Contact>>all_fav;



    Repository(Application application) {
        Database_All database_all = Database_All.getInstance(application);
        dao = database_all.getDao();
        all_Contact = dao.getAll_Contact();
        all_fav= dao.getFavContact(true);

    }


    //Contact
    public void insert_contact(Contact contact) {
        new insert_contact_Async(dao).execute(contact);
    }

    public void delete_contact(Contact contact) {
        new delete_contact_Async(dao).execute(contact);
    }

    public void update_contact(Contact contact) {
        new update_contact_Async(dao).execute(contact);
    }

    public void deleteAll_contact() {
        new delete_All_contact_Async(dao).execute();
    }

    public LiveData<List<Contact>> getAll_Contact() {
        return all_Contact;
    }

    public LiveData<List<Contact>>getFavContact(){
       return all_fav;
    }



    private static class insert_contact_Async extends AsyncTask<Contact, Void, Void> {
        private Contact_Dao dao;

        private insert_contact_Async(Contact_Dao dao) {
            this.dao = dao;
        }

        @Override
        protected Void doInBackground(Contact... contacts) {
            dao.insert_contact(contacts[0]);
            return null;
        }
    }

    private static class update_contact_Async extends AsyncTask<Contact, Void, Void> {
        private Contact_Dao dao;

        private update_contact_Async(Contact_Dao dao) {
            this.dao = dao;
        }

        @Override
        protected Void doInBackground(Contact... contacts) {
            dao.update_contact(contacts[0]);
            return null;
        }
    }

    private static class delete_contact_Async extends AsyncTask<Contact, Void, Void> {
        private Contact_Dao dao;

        private delete_contact_Async(Contact_Dao dao) {
            this.dao = dao;
        }

        @Override
        protected Void doInBackground(Contact... contacts) {
            dao.delete_contact(contacts[0]);
            return null;
        }
    }

    private static class delete_All_contact_Async extends AsyncTask<Void, Void, Void> {
        private Contact_Dao dao;

        private delete_All_contact_Async(Contact_Dao dao) {
            this.dao = dao;
        }

        @Override
        protected Void doInBackground(Void... voids) {
            dao.deleteAll_contact();
            return null;
        }
    }


}