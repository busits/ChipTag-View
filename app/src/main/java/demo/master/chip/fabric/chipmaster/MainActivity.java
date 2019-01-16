package demo.master.chip.fabric.chipmaster;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.drawable.BitmapDrawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;

import com.applandeo.materialcalendarview.CalendarUtils;
import com.applandeo.materialcalendarview.CalendarView;
import com.applandeo.materialcalendarview.EventDay;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import bits.views.chip.tag.ChipTagView;
import bits.views.chip.tag.TagModel;

public class MainActivity extends AppCompatActivity {

    ChipTagView chipTagView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
//        chipTagView = findViewById(R.id.contentchip);
//        chipTagView.addTag(new TagModel(2,"Ashish Sahu"));
//        chipTagView.addTag(new TagModel(3,"Johnathan Lee"));
//        chipTagView.addTag(new TagModel(4,"Satyam"));
//        chipTagView.addTag(new TagModel(5,"Abhishek Sahu"));
//        chipTagView.addTag(new TagModel(6,"Shweta"));

        List<EventDay> events = new ArrayList<>();

        Calendar calendar = Calendar.getInstance();
        events.add(new EventDay(calendar, CalendarUtils.getDrawableText(this,"M", null,R.color.defaultColor, 50)));

        CalendarView calendarView = findViewById(R.id.calendarView);
        calendarView.setEvents(events);


    }

    public  BitmapDrawable loadBitmapFromView() {
        View v= LayoutInflater.from(this).inflate(R.layout.tin,null);
        Bitmap b = Bitmap.createBitmap( 100,100, Bitmap.Config.ARGB_8888);
        Canvas c = new Canvas(b);
        v.layout(v.getLeft(), v.getTop(), v.getRight(), v.getBottom());
        v.draw(c);
        return new BitmapDrawable(getResources(), b);
    }
}
