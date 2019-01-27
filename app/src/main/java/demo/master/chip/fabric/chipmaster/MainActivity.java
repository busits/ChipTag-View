package demo.master.chip.fabric.chipmaster;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;

import bits.views.chip.tag.Chip;
import bits.views.chip.tag.ChipTagView;
import bits.views.chip.tag.TagModel;

public class MainActivity extends AppCompatActivity {

    ChipTagView chipTagView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        chipTagView = findViewById(R.id.chipView);
        chipTagView.addTag(new TagModel("",2, "Ashish Sahu",false));
        chipTagView.addTag(new TagModel("",3, "Johnathan Lee",true));
        chipTagView.addTag(new TagModel("",4, "Satyam",false));
        chipTagView.addTag(new TagModel("",5, "Abhishek Sahu",false));
        chipTagView.addTag(new TagModel("",6, "Shweta",false));
        chipTagView.setChipListener(new ChipTagView.ChipListener() {
            @Override
            public void onPostCreate(Chip chip, TagModel tag) {
                if (tag.id==3) {
                    chip.getChipImageView().setImageResource(R.drawable.ic_close);
                }
            }
        });
    }

    public BitmapDrawable loadBitmapFromView() {
        View v = LayoutInflater.from(this).inflate(R.layout.tin, null);
        Bitmap b = Bitmap.createBitmap(100, 100, Bitmap.Config.ARGB_8888);
        Canvas c = new Canvas(b);
        v.layout(v.getLeft(), v.getTop(), v.getRight(), v.getBottom());
        v.draw(c);
        return new BitmapDrawable(getResources(), b);
    }
}
