package com.example.iphone_contects.Database;

import android.database.Cursor;
import androidx.lifecycle.LiveData;
import androidx.room.EntityDeletionOrUpdateAdapter;
import androidx.room.EntityInsertionAdapter;
import androidx.room.RoomDatabase;
import androidx.room.RoomSQLiteQuery;
import androidx.room.SharedSQLiteStatement;
import androidx.room.util.CursorUtil;
import androidx.room.util.DBUtil;
import androidx.sqlite.db.SupportSQLiteStatement;
import java.lang.Class;
import java.lang.Exception;
import java.lang.Override;
import java.lang.String;
import java.lang.SuppressWarnings;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.Callable;

@SuppressWarnings({"unchecked", "deprecation"})
public final class Contact_Dao_Impl implements Contact_Dao {
  private final RoomDatabase __db;

  private final EntityInsertionAdapter<Contact> __insertionAdapterOfContact;

  private final EntityDeletionOrUpdateAdapter<Contact> __deletionAdapterOfContact;

  private final EntityDeletionOrUpdateAdapter<Contact> __updateAdapterOfContact;

  private final SharedSQLiteStatement __preparedStmtOfDeleteAll_contact;

  public Contact_Dao_Impl(RoomDatabase __db) {
    this.__db = __db;
    this.__insertionAdapterOfContact = new EntityInsertionAdapter<Contact>(__db) {
      @Override
      public String createQuery() {
        return "INSERT OR ABORT INTO `contacts_table` (`id`,`first_name`,`lastname`,`number_home`,`number_mobile`,`email`,`address`,`fav`) VALUES (nullif(?, 0),?,?,?,?,?,?,?)";
      }

      @Override
      public void bind(SupportSQLiteStatement stmt, Contact value) {
        stmt.bindLong(1, value.getId());
        if (value.getFirst_name() == null) {
          stmt.bindNull(2);
        } else {
          stmt.bindString(2, value.getFirst_name());
        }
        if (value.getLastname() == null) {
          stmt.bindNull(3);
        } else {
          stmt.bindString(3, value.getLastname());
        }
        if (value.getNumber_home() == null) {
          stmt.bindNull(4);
        } else {
          stmt.bindString(4, value.getNumber_home());
        }
        if (value.getNumber_mobile() == null) {
          stmt.bindNull(5);
        } else {
          stmt.bindString(5, value.getNumber_mobile());
        }
        if (value.getEmail() == null) {
          stmt.bindNull(6);
        } else {
          stmt.bindString(6, value.getEmail());
        }
        if (value.getAddress() == null) {
          stmt.bindNull(7);
        } else {
          stmt.bindString(7, value.getAddress());
        }
        final int _tmp;
        _tmp = value.isFav() ? 1 : 0;
        stmt.bindLong(8, _tmp);
      }
    };
    this.__deletionAdapterOfContact = new EntityDeletionOrUpdateAdapter<Contact>(__db) {
      @Override
      public String createQuery() {
        return "DELETE FROM `contacts_table` WHERE `id` = ?";
      }

      @Override
      public void bind(SupportSQLiteStatement stmt, Contact value) {
        stmt.bindLong(1, value.getId());
      }
    };
    this.__updateAdapterOfContact = new EntityDeletionOrUpdateAdapter<Contact>(__db) {
      @Override
      public String createQuery() {
        return "UPDATE OR ABORT `contacts_table` SET `id` = ?,`first_name` = ?,`lastname` = ?,`number_home` = ?,`number_mobile` = ?,`email` = ?,`address` = ?,`fav` = ? WHERE `id` = ?";
      }

      @Override
      public void bind(SupportSQLiteStatement stmt, Contact value) {
        stmt.bindLong(1, value.getId());
        if (value.getFirst_name() == null) {
          stmt.bindNull(2);
        } else {
          stmt.bindString(2, value.getFirst_name());
        }
        if (value.getLastname() == null) {
          stmt.bindNull(3);
        } else {
          stmt.bindString(3, value.getLastname());
        }
        if (value.getNumber_home() == null) {
          stmt.bindNull(4);
        } else {
          stmt.bindString(4, value.getNumber_home());
        }
        if (value.getNumber_mobile() == null) {
          stmt.bindNull(5);
        } else {
          stmt.bindString(5, value.getNumber_mobile());
        }
        if (value.getEmail() == null) {
          stmt.bindNull(6);
        } else {
          stmt.bindString(6, value.getEmail());
        }
        if (value.getAddress() == null) {
          stmt.bindNull(7);
        } else {
          stmt.bindString(7, value.getAddress());
        }
        final int _tmp;
        _tmp = value.isFav() ? 1 : 0;
        stmt.bindLong(8, _tmp);
        stmt.bindLong(9, value.getId());
      }
    };
    this.__preparedStmtOfDeleteAll_contact = new SharedSQLiteStatement(__db) {
      @Override
      public String createQuery() {
        final String _query = "DELETE FROM contacts_table";
        return _query;
      }
    };
  }

  @Override
  public void insert_contact(final Contact contact) {
    __db.assertNotSuspendingTransaction();
    __db.beginTransaction();
    try {
      __insertionAdapterOfContact.insert(contact);
      __db.setTransactionSuccessful();
    } finally {
      __db.endTransaction();
    }
  }

  @Override
  public void delete_contact(final Contact contact) {
    __db.assertNotSuspendingTransaction();
    __db.beginTransaction();
    try {
      __deletionAdapterOfContact.handle(contact);
      __db.setTransactionSuccessful();
    } finally {
      __db.endTransaction();
    }
  }

  @Override
  public void update_contact(final Contact contact) {
    __db.assertNotSuspendingTransaction();
    __db.beginTransaction();
    try {
      __updateAdapterOfContact.handle(contact);
      __db.setTransactionSuccessful();
    } finally {
      __db.endTransaction();
    }
  }

  @Override
  public void deleteAll_contact() {
    __db.assertNotSuspendingTransaction();
    final SupportSQLiteStatement _stmt = __preparedStmtOfDeleteAll_contact.acquire();
    __db.beginTransaction();
    try {
      _stmt.executeUpdateDelete();
      __db.setTransactionSuccessful();
    } finally {
      __db.endTransaction();
      __preparedStmtOfDeleteAll_contact.release(_stmt);
    }
  }

  @Override
  public LiveData<List<Contact>> getAll_Contact() {
    final String _sql = "SELECT * FROM contacts_table ORDER BY id DESC";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 0);
    return __db.getInvalidationTracker().createLiveData(new String[]{"contacts_table"}, false, new Callable<List<Contact>>() {
      @Override
      public List<Contact> call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
        try {
          final int _cursorIndexOfId = CursorUtil.getColumnIndexOrThrow(_cursor, "id");
          final int _cursorIndexOfFirstName = CursorUtil.getColumnIndexOrThrow(_cursor, "first_name");
          final int _cursorIndexOfLastname = CursorUtil.getColumnIndexOrThrow(_cursor, "lastname");
          final int _cursorIndexOfNumberHome = CursorUtil.getColumnIndexOrThrow(_cursor, "number_home");
          final int _cursorIndexOfNumberMobile = CursorUtil.getColumnIndexOrThrow(_cursor, "number_mobile");
          final int _cursorIndexOfEmail = CursorUtil.getColumnIndexOrThrow(_cursor, "email");
          final int _cursorIndexOfAddress = CursorUtil.getColumnIndexOrThrow(_cursor, "address");
          final int _cursorIndexOfFav = CursorUtil.getColumnIndexOrThrow(_cursor, "fav");
          final List<Contact> _result = new ArrayList<Contact>(_cursor.getCount());
          while(_cursor.moveToNext()) {
            final Contact _item;
            _item = new Contact();
            final int _tmpId;
            _tmpId = _cursor.getInt(_cursorIndexOfId);
            _item.setId(_tmpId);
            final String _tmpFirst_name;
            if (_cursor.isNull(_cursorIndexOfFirstName)) {
              _tmpFirst_name = null;
            } else {
              _tmpFirst_name = _cursor.getString(_cursorIndexOfFirstName);
            }
            _item.setFirst_name(_tmpFirst_name);
            final String _tmpLastname;
            if (_cursor.isNull(_cursorIndexOfLastname)) {
              _tmpLastname = null;
            } else {
              _tmpLastname = _cursor.getString(_cursorIndexOfLastname);
            }
            _item.setLastname(_tmpLastname);
            final String _tmpNumber_home;
            if (_cursor.isNull(_cursorIndexOfNumberHome)) {
              _tmpNumber_home = null;
            } else {
              _tmpNumber_home = _cursor.getString(_cursorIndexOfNumberHome);
            }
            _item.setNumber_home(_tmpNumber_home);
            final String _tmpNumber_mobile;
            if (_cursor.isNull(_cursorIndexOfNumberMobile)) {
              _tmpNumber_mobile = null;
            } else {
              _tmpNumber_mobile = _cursor.getString(_cursorIndexOfNumberMobile);
            }
            _item.setNumber_mobile(_tmpNumber_mobile);
            final String _tmpEmail;
            if (_cursor.isNull(_cursorIndexOfEmail)) {
              _tmpEmail = null;
            } else {
              _tmpEmail = _cursor.getString(_cursorIndexOfEmail);
            }
            _item.setEmail(_tmpEmail);
            final String _tmpAddress;
            if (_cursor.isNull(_cursorIndexOfAddress)) {
              _tmpAddress = null;
            } else {
              _tmpAddress = _cursor.getString(_cursorIndexOfAddress);
            }
            _item.setAddress(_tmpAddress);
            final boolean _tmpFav;
            final int _tmp;
            _tmp = _cursor.getInt(_cursorIndexOfFav);
            _tmpFav = _tmp != 0;
            _item.setFav(_tmpFav);
            _result.add(_item);
          }
          return _result;
        } finally {
          _cursor.close();
        }
      }

      @Override
      protected void finalize() {
        _statement.release();
      }
    });
  }

  @Override
  public LiveData<List<Contact>> getFavContact(final boolean fav) {
    final String _sql = "SELECT * FROM contacts_table WHERE fav LIKE ? ";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 1);
    int _argIndex = 1;
    final int _tmp;
    _tmp = fav ? 1 : 0;
    _statement.bindLong(_argIndex, _tmp);
    return __db.getInvalidationTracker().createLiveData(new String[]{"contacts_table"}, false, new Callable<List<Contact>>() {
      @Override
      public List<Contact> call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
        try {
          final int _cursorIndexOfId = CursorUtil.getColumnIndexOrThrow(_cursor, "id");
          final int _cursorIndexOfFirstName = CursorUtil.getColumnIndexOrThrow(_cursor, "first_name");
          final int _cursorIndexOfLastname = CursorUtil.getColumnIndexOrThrow(_cursor, "lastname");
          final int _cursorIndexOfNumberHome = CursorUtil.getColumnIndexOrThrow(_cursor, "number_home");
          final int _cursorIndexOfNumberMobile = CursorUtil.getColumnIndexOrThrow(_cursor, "number_mobile");
          final int _cursorIndexOfEmail = CursorUtil.getColumnIndexOrThrow(_cursor, "email");
          final int _cursorIndexOfAddress = CursorUtil.getColumnIndexOrThrow(_cursor, "address");
          final int _cursorIndexOfFav = CursorUtil.getColumnIndexOrThrow(_cursor, "fav");
          final List<Contact> _result = new ArrayList<Contact>(_cursor.getCount());
          while(_cursor.moveToNext()) {
            final Contact _item;
            _item = new Contact();
            final int _tmpId;
            _tmpId = _cursor.getInt(_cursorIndexOfId);
            _item.setId(_tmpId);
            final String _tmpFirst_name;
            if (_cursor.isNull(_cursorIndexOfFirstName)) {
              _tmpFirst_name = null;
            } else {
              _tmpFirst_name = _cursor.getString(_cursorIndexOfFirstName);
            }
            _item.setFirst_name(_tmpFirst_name);
            final String _tmpLastname;
            if (_cursor.isNull(_cursorIndexOfLastname)) {
              _tmpLastname = null;
            } else {
              _tmpLastname = _cursor.getString(_cursorIndexOfLastname);
            }
            _item.setLastname(_tmpLastname);
            final String _tmpNumber_home;
            if (_cursor.isNull(_cursorIndexOfNumberHome)) {
              _tmpNumber_home = null;
            } else {
              _tmpNumber_home = _cursor.getString(_cursorIndexOfNumberHome);
            }
            _item.setNumber_home(_tmpNumber_home);
            final String _tmpNumber_mobile;
            if (_cursor.isNull(_cursorIndexOfNumberMobile)) {
              _tmpNumber_mobile = null;
            } else {
              _tmpNumber_mobile = _cursor.getString(_cursorIndexOfNumberMobile);
            }
            _item.setNumber_mobile(_tmpNumber_mobile);
            final String _tmpEmail;
            if (_cursor.isNull(_cursorIndexOfEmail)) {
              _tmpEmail = null;
            } else {
              _tmpEmail = _cursor.getString(_cursorIndexOfEmail);
            }
            _item.setEmail(_tmpEmail);
            final String _tmpAddress;
            if (_cursor.isNull(_cursorIndexOfAddress)) {
              _tmpAddress = null;
            } else {
              _tmpAddress = _cursor.getString(_cursorIndexOfAddress);
            }
            _item.setAddress(_tmpAddress);
            final boolean _tmpFav;
            final int _tmp_1;
            _tmp_1 = _cursor.getInt(_cursorIndexOfFav);
            _tmpFav = _tmp_1 != 0;
            _item.setFav(_tmpFav);
            _result.add(_item);
          }
          return _result;
        } finally {
          _cursor.close();
        }
      }

      @Override
      protected void finalize() {
        _statement.release();
      }
    });
  }

  public static List<Class<?>> getRequiredConverters() {
    return Collections.emptyList();
  }
}
