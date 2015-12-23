package nl.mranderson.viewpagerexample;

import android.content.Context;
import android.database.Cursor;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.provider.CalendarContract;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
 * Created by MrAnderson1 on 22/12/15.
 */
public class Utility {

    public static List<Event> readCalendarEvent(Context context) {
        String[] projection = new String[]{CalendarContract.Events.CALENDAR_ID, CalendarContract.Events.TITLE, CalendarContract.Events.DESCRIPTION, CalendarContract.Events.DTSTART, CalendarContract.Events.DTEND, CalendarContract.Events.EVENT_LOCATION, CalendarContract.Events.ALL_DAY};

        Calendar startTime = Calendar.getInstance();
        String selection = "(( " + CalendarContract.Events.DTSTART + " >= " + startTime.getTimeInMillis() + " ) AND ( " + CalendarContract.Events.ALL_DAY + " = 0))";


        Cursor cursor = context.getContentResolver().query(CalendarContract.Events.CONTENT_URI, projection, selection, null, CalendarContract.Events.DTSTART + " ASC");

        cursor.moveToFirst();

        // fetching calendars id
        List<Event> list = new ArrayList<>();
        Geocoder geo = new Geocoder(context);

        for (int i = 0; i < cursor.getCount(); i++) {
            Location loc = new Location("event" + i);
            Event event = new Event();
            try {
                List<Address> adres = geo.getFromLocationName(cursor.getString(5), 1);
                loc.setLatitude(adres.get(0).getLatitude());
                loc.setLongitude(adres.get(0).getLongitude());
            } catch (Exception e) {
                loc.setLatitude(0);
                loc.setLongitude(0);
            }
            event.setTitle(cursor.getString(1));
            event.setEndDate(new Date(Long.parseLong(cursor.getString(3))));
            event.setStartDate(new Date(Long.parseLong(cursor.getString(4))));
            event.setDescription(cursor.getString(2));

            event.setLocation(loc);
            list.add(event);

            cursor.moveToNext();

        }
        return list;
    }


}