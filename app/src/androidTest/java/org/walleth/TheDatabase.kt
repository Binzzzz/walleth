package org.walleth

import android.arch.persistence.room.Room
import android.support.test.InstrumentationRegistry
import com.google.common.truth.Truth.assertThat
import org.junit.Before
import org.junit.Test
import org.walleth.data.AppDatabase
import org.walleth.data.addressbook.AddressBookEntry
import org.walleth.testdata.DEFAULT_TEST_ADDRESS
import org.walleth.testdata.DEFAULT_TEST_ADDRESS2
import org.walleth.testdata.DEFAULT_TEST_ADDRESS3

class TheDatabase {

    lateinit var database : AppDatabase

    @Before
    fun createDb() {
        val context = InstrumentationRegistry.getTargetContext()
        database = Room.inMemoryDatabaseBuilder(context, AppDatabase::class.java).build()
    }


    @Test
    fun isEmptyInitially() {
        assertThat(database.addressBook.all().size).isEqualTo(0)
    }

    @Test
    fun weCanInsertTwo() {
        database.addressBook.upsert(AddressBookEntry(DEFAULT_TEST_ADDRESS,"nameprobe"))
        database.addressBook.upsert(AddressBookEntry(DEFAULT_TEST_ADDRESS2,"2nameprobe2"))
        assertThat(database.addressBook.all().size).isEqualTo(2)
    }


    @Test
    fun queryWorks() {
        database.addressBook.upsert(AddressBookEntry(DEFAULT_TEST_ADDRESS,"nameprobe"))
        database.addressBook.upsert(AddressBookEntry(DEFAULT_TEST_ADDRESS2,"2nameprobe2"))
        assertThat(database.addressBook.byAddress(DEFAULT_TEST_ADDRESS2)?.name).isEqualTo("2nameprobe2")
    }

    @Test
    fun updateWorks() {
        database.addressBook.upsert(AddressBookEntry(DEFAULT_TEST_ADDRESS,"nameprobe"))
        database.addressBook.upsert(AddressBookEntry(DEFAULT_TEST_ADDRESS,"nameprobe_after_upsert"))
        assertThat(database.addressBook.byAddress(DEFAULT_TEST_ADDRESS)?.name).isEqualTo("nameprobe_after_upsert")
    }


    @Test
    fun findsWhereNotificationIsWanted() {
        database.addressBook.upsert(AddressBookEntry(DEFAULT_TEST_ADDRESS,"notificationNotWanted1",isNotificationWanted = false))
        val entry = AddressBookEntry(DEFAULT_TEST_ADDRESS2, "notificationWanted", isNotificationWanted = true)
        database.addressBook.upsert(entry)
        database.addressBook.upsert(AddressBookEntry(DEFAULT_TEST_ADDRESS3,"notificationNotWanted2",isNotificationWanted = false))

        assertThat(database.addressBook.allThatWantNotifications().first().name).isEqualTo("notificationWanted")
    }


    @Test
    fun queryInEmptyReturnsNull() {
        assertThat(database.addressBook.byAddress(DEFAULT_TEST_ADDRESS)).isNull()
    }



}