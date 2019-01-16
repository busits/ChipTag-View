package bits.views.chip.tag;

import android.graphics.Color;

public class TagModel {

    public long id;
    public String name;
    private int background_color;
    private boolean closable;
    private int text_color;

    public TagModel(long id, String name) {
        this.id = id;
        this.name = name;
        this.closable=true;
    }

    public boolean isClosable() {
        return closable;
    }

    public int getBackground_color() {
        if(background_color==0)
            return Color.parseColor("#006DF0");
        return background_color;
    }

    public int getText_color() {
        if(text_color==0)
            return Color.parseColor("#FFFFFF");
        return text_color;
    }

    public void setBackground_color(int background_color) {
        this.background_color = background_color;
    }

    public void setClosable(boolean closable) {
        this.closable = closable;
    }

    public void setText_color(int text_color) {
        this.text_color = text_color;
    }


}
