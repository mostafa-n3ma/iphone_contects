package com.example.iphone_contects.Database;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import java.util.List;

public class ContactViewModel extends AndroidViewModel {
    private Repository repository;
    private LiveData<List<Contact>> all_Contact;
    private LiveData<List<Contact>> allfav;

    public ContactViewModel(@NonNull Application application) {
        super(application);
        repository = new Repository(application);
        all_Contact = repository.getAll_Contact();
        allfav= repository.getFavContact();
    }

    //Contact
    public void insert_contact(Contact contact) {
        repository.insert_contact(contact);
    }

    public void delete_contact(Contact contact) {
        repository.delete_contact(contact);
    }

    public void update_contact(Contact contact) {
        repository.update_contact(contact);
    }

    public void deleteAll_contact() {
        repository.deleteAll_contact();
    }

    public LiveData<List<Contact>> getAll_Contact() {
        return all_Contact;
    }

    public LiveData<List<Contact>> getFavContact() {
        return allfav;
    }


}
