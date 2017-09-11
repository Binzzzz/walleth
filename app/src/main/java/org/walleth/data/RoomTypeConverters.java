package org.walleth.data;

import android.arch.persistence.room.TypeConverter;
import java.util.Date;
import org.kethereum.model.Address;

public class RoomTypeConverters {

    @TypeConverter
    public static Address fromTimestamp(String value) {
        return value == null ? null : new Address(value);
    }

    @TypeConverter
    public static String dateToTimestamp(Address address) {
        return address == null ? null : address.getHex();
    }

    @TypeConverter
    public static Date fromTimestamp(Long value) {
        return value == null ? null : new Date(value);
    }

    @TypeConverter
    public static Long dateToTimestamp(Date date) {
        return date == null ? null : date.getTime();
    }
}