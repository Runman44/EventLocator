package nl.mranderson.viewpagerexample;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;

import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Locale;

/**
 * Created by MrAnderson1 on 22/12/15.
 */
public class EventListAdapter extends RecyclerView.Adapter<EventListAdapter.EventListViewHolder> {

    private static Context context;
    private List<Event> eventList;
    private static Event ci;

    public EventListAdapter(List<Event> eventList, Context context) {
        this.context = context;
        this.eventList = eventList;
    }

    @Override
    public int getItemCount() {
        return eventList.size();
    }

    @Override
    public void onBindViewHolder(EventListViewHolder contactViewHolder, int i) {
        ci = eventList.get(i);
        contactViewHolder.currentItem = ci;
        SimpleDateFormat format = new SimpleDateFormat("MM/dd/yyyy HH:mm", Locale.US);

        contactViewHolder.vTitle.setText(ci.getTitle());
        if (ci.getDescription().equals("")) {
            contactViewHolder.vDesc.setVisibility(View.GONE);
        } else {
            contactViewHolder.vDesc.setText(ci.getDescription());
        }
        contactViewHolder.vStart.setText(format.format(ci.getStartDate()) + " - ");
        contactViewHolder.vEnd.setText(format.format(ci.getEndDate()));

        if (ci.getLocation().getLatitude() == 0 && ci.getLocation().getLatitude() == 0) {
            Picasso.with(context)
                    .load(R.drawable.noloc)
                    .resize(150, 150)
                    .centerCrop()
                    .into(contactViewHolder.mMapView);
        } else {
            String maps = "https://maps.googleapis.com/maps/api/staticmap?zoom=15&size=800x600&maptype=normal&markers=color:red%7C" + ci.getLocation().getLatitude() + "," + ci.getLocation().getLongitude() + "&key=AIzaSyAPjP0Rvl2QEzAR3_QGk1TEdOODZFWgLdE";
            Picasso.with(context)
                    .load(maps)
                    .fit()
                    .centerCrop()
                    .into(contactViewHolder.mMapView);
        }
    }

    @Override
    public EventListViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View itemView = LayoutInflater.
                from(viewGroup.getContext()).
                inflate(R.layout.eventview, viewGroup, false);

        return new EventListViewHolder(itemView);
    }

    public static class EventListViewHolder extends RecyclerView.ViewHolder {
        protected View view;
        protected TextView vTitle;
        protected TextView vDesc;
        protected TextView vStart;
        protected TextView vEnd;
        protected ImageView mMapView;
        public Event currentItem;

        public EventListViewHolder(View v) {
            super(v);
            view = v;
            vTitle = (TextView) v.findViewById(R.id.title);
            vDesc = (TextView) v.findViewById(R.id.descript);
            vStart = (TextView) v.findViewById(R.id.startDate);
            vEnd = (TextView) v.findViewById(R.id.endDate);
            mMapView = (ImageView) v.findViewById(R.id.mini_map);

            view.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (currentItem.getLocation().getLatitude() != 0 && currentItem.getLocation().getLatitude() != 0) {
                        String url = "http://maps.google.com/maps?f=d&daddr=" + currentItem.getLocation().getLatitude() + "," + currentItem.getLocation().getLongitude() + "&dirflg=d&layer=t&z=16";
                        Intent intent = new Intent(android.content.Intent.ACTION_VIEW, Uri.parse(url));
                        intent.setClassName("com.google.android.apps.maps", "com.google.android.maps.MapsActivity");
                        context.startActivity(intent);
                    } else {
                        Toast.makeText(context, "No location for this event", Toast.LENGTH_SHORT).show();
                    }
                }
            });
        }
    }
}
