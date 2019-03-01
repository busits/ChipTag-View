package bits.views.chip.tag;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Typeface;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.TextView;
import android.widget.ViewFlipper;

import com.nex3z.flowlayout.FlowLayout;

import java.util.List;

public class ChipTagView extends FrameLayout {


    TextView hintTxt;
    ViewFlipper viewFlipper;
    FlowLayout flowLayout;
    ChipListener chipListener;


    public void setChipListener(ChipListener chipListener) {
        this.chipListener = chipListener;
    }

    public interface ChipListener {
        public void onPostCreate(Chip chip, TagModel tag);
    }


    public ChipTagView(@NonNull Context context) {
        super(context);
        init(null);
    }

    public ChipTagView(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init(attrs);
    }

    void init(AttributeSet attrs) {
        inflate(getContext(), R.layout.chip_tag_view, this);
        hintTxt = findViewById(R.id.hint_textview);
        viewFlipper = findViewById(R.id.chip_flipper);
        flowLayout = findViewById(R.id.chip_tag_flow_layout);

        if (attrs != null) {
            TypedArray ta = getContext().getTheme().obtainStyledAttributes(attrs, R.styleable.ChipTagView, 0, 0);
            hintTxt.setText(ta.getString(R.styleable.ChipTagView_chip_tag_view_hint));
            ta.recycle();
        }
    }

    public void addTag(final TagModel tag) {
        if (getTag(tag.id) == null) {
            View chipLayout = LayoutInflater.from(getContext()).inflate(R.layout.chip_tag_view_chip, null);
            final Chip chip = chipLayout.findViewById(R.id.chip_view_chip_id);
            chip.setHasIcon(tag.hasIcon);
            chip.setChipText(tag.name);
            chipLayout.setTag(tag);
            chip.changeBackgroundColor(tag.getBackground_color());
            chip.setClosable(tag.isClosable());
            chip.setOnCloseClickListener(new OnCloseClickListener() {
                @Override
                public void onCloseClick(View v) {
                    remove(tag.id);
                }
            });
            chip.post(new Runnable() {
                @Override
                public void run() {
                    chip.getChipTextView().setTextColor(tag.getText_color());
                    chip.getChipTextView().setTypeface(Typeface.createFromAsset(getContext().getAssets(), "chip_fonts/Lato/Lato-Bold.ttf"));
                    if (chipListener != null)
                        chipListener.onPostCreate(chip, tag);
                }
            });

            flowLayout.addView(chipLayout);
            invalidateHint();
        }
    }

    private void invalidateHint() {
        if (flowLayout.getChildCount() == 0)
            viewFlipper.setDisplayedChild(0);
         else
            viewFlipper.setDisplayedChild(1);
    }


    public void addTags(List<TagModel> tags) {
        for (TagModel tag : tags)
            addTag(tag);
    }

    public void remove(long id) {
        for (int i = 0; i < flowLayout.getChildCount(); i++) {
            TagModel tagModel = (TagModel) flowLayout.getChildAt(i).getTag();
            if (tagModel.id == id) {
                flowLayout.removeViewAt(i);
                invalidateHint();
            }
        }
    }

    public TagModel getTag(long id) {
        for (int i = 0; i < flowLayout.getChildCount(); i++) {
            TagModel tagModel = (TagModel) flowLayout.getChildAt(i).getTag();
            if (tagModel.id == id)
                return tagModel;
        }
        return null;
    }

    public Long[] getTagIds() {
        Long[] ids = new Long[flowLayout.getChildCount()];
        for (int i = 0; i < flowLayout.getChildCount(); i++) {
            TagModel tagModel = (TagModel) flowLayout.getChildAt(i).getTag();
            ids[i] = tagModel.id;
        }
        return ids;
    }

    public TagModel[] getTags() {
        TagModel[] tags = new TagModel[flowLayout.getChildCount()];
        for (int i = 0; i < flowLayout.getChildCount(); i++) {
            tags[i] = (TagModel) flowLayout.getChildAt(i).getTag();
        }
        return tags;
    }
}
