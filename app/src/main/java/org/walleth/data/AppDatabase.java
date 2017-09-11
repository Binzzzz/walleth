package org.walleth.data;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.RoomDatabase;
import android.arch.persistence.room.TypeConverters;
import org.walleth.data.addressbook.AddressBookDAO;
import org.walleth.data.addressbook.AddressBookEntry;

@Database(entities = {AddressBookEntry.class}, version = 1)
@TypeConverters({RoomTypeConverters.class})
public abstract class AppDatabase extends RoomDatabase {
    public abstract AddressBookDAO getAddressBook();
}