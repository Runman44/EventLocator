package nl.mranderson.viewpagerexample;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.provider.CalendarContract;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

/**
 * Created by MrAnderson1 on 22/12/15.
 */
public class TwoFragment extends Fragment {

    private FloatingActionButton mAddView;

    public TwoFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.activity_main, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        setRetainInstance(true);
        RecyclerView recList = (RecyclerView) getView().findViewById(R.id.event_list);
        LinearLayoutManager llm = new LinearLayoutManager(getActivity());
        llm.setOrientation(LinearLayoutManager.VERTICAL);
        recList.setLayoutManager(llm);

        if (ContextCompat.checkSelfPermission(getActivity(),
                Manifest.permission.READ_CALENDAR) != PackageManager.PERMISSION_GRANTED) {

            ActivityCompat.requestPermissions(getActivity(),
                    new String[]{Manifest.permission.READ_CALENDAR},
                    1);

        } else {
            List<Event> list = Utility.readCalendarEvent(getActivity());
            recList.setAdapter(new EventListAdapter(list, getActivity()));
        }

        mAddView = (FloatingActionButton) getView().findViewById(R.id.add_button);
        mAddView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_EDIT).setData(CalendarContract.Events.CONTENT_URI);
                startActivity(intent);
            }
        });
    }
}
