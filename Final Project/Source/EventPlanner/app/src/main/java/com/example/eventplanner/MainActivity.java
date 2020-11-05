package com.example.eventplanner;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.os.Build;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import androidx.appcompat.widget.Toolbar;//makes toolbar function correctly

public class MainActivity extends AppCompatActivity {

    private Toolbar toolbar; //used to access toolbar created in activity_main
    private ListView lists; //used to access list from activity_main

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP) //allows viewCreator to work
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //This section will first create the cards,toolbar, then list will be created upon start up
        viewCreator();
        create_toolbar();
        listCreator();
    }

    /**
     * Function will create the toolbar for the user to see
     */
    private void create_toolbar() {
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("Event Planner");//this will set the title of our application

    }

    /**
     * Function will act as template to create the cards for user interface
     */
    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)//allows toolbar to work
    private void viewCreator() {
        toolbar = (Toolbar) findViewById(R.id.toolbar_main);
        lists = (ListView) findViewById(R.id.list);

    }

    /**
     * Function will create the list to be viewed
     */
    private void listCreator() {
        String[] title = getResources().getStringArray(R.array.Home);
        String[] description = getResources().getStringArray(R.array.Description);

        Adapter adapter = new Adapter(this, title, description);
        lists.setAdapter(adapter);

    }

    /**
     * Adapter used for list view. Layout of the listview
     */
    public class Adapter extends BaseAdapter {

        private Context myContext; //access to application-specific resources
        private LayoutInflater inflater; //define the row layout. Loads different layouts into views
        private TextView title, description; //used for each card creation
        private String[] titleA, descriptA; //used to save info
        private ImageView images; //images used here

        /**
         * Constructor is created here. Used to help pupulate listview. Will be called on start up
         *
         * @param c
         * @param title
         * @param descript
         */
        public Adapter(Context c, String[] title, String[] descript) {
            myContext = c;
            titleA = title;
            descriptA = descript;
            inflater = LayoutInflater.from(c);

        }

        /**
         * This section will return title's length
         *
         * @return titleA.length
         */
        @Override
        public int getCount() {
            return titleA.length;//returns array length
        }

        /**
         * This section will return the title's postion
         *
         * @param position
         * @return titleA[postion]
         */
        @Override
        public Object getItem(int position) {
            return titleA[position];
        }

        /**
         * This section will return the position
         *
         * @param position
         * @return position
         */
        @Override
        public long getItemId(int position) {
            return position;
        }

        /**
         * This section will set each different view. Helps swap between the 2 main activities
         *
         * @param position
         * @param convertView
         * @param parent
         * @return
         */
        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            //if the view is empty, access the card layout in activity_main_2
            if (convertView == null) {
                convertView = inflater.inflate(R.layout.acitivity_main_2, null);
            }
            //set the title to be equal to the title in the activity_main_2
            title = (TextView) convertView.findViewById(R.id.cardTitle);
            //set the description to be equal to the description in activity_main_2
            description = (TextView) convertView.findViewById(R.id.cardDescription);
            //set the image equal to in activity_main_2
            images = (ImageView) convertView.findViewById(R.id.mainImage);
            title.setText(titleA[position]);

            //--------------------------------------------------------------------------------ERROR HERE FIX ME -------------------------------->
            //description.setText(descriptA[position]);

            //This section will set the image for each title created in strings.xml
            if (titleA[position].equalsIgnoreCase("Weekly View")) {
                images.setImageResource(R.drawable.weekly);
            } else if (titleA[position].equalsIgnoreCase("Calendar View")) {
                images.setImageResource(R.drawable.calendar);
            } else if (titleA[position].equalsIgnoreCase("University Bookstore")) {
                images.setImageResource(R.drawable.bookstore);
            } else if (titleA[position].equalsIgnoreCase("University Email")) {
                images.setImageResource(R.drawable.email);
            } else if (titleA[position].equalsIgnoreCase("Educational Resources")) {
                images.setImageResource(R.drawable.resources);
            } else if (titleA[position].equalsIgnoreCase("Mindfulness")) {
                images.setImageResource(R.drawable.mindfulness);
            } else {
                images.setImageResource(R.drawable.entertainment);
            }

            return convertView;
        }
    }
}