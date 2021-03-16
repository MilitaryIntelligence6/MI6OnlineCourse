package cn.misection.miscourse.constant.global;

import java.util.EnumSet;
import java.util.HashMap;
import java.util.Map;

import cn.misection.miscourse.R;

/**
 * @author Military Intelligence 6 root
 * @version 1.0.0
 * @ClassName EnumExerciseImageView
 * @Description TODO
 * @CreateTime 2021年03月12日 09:52:00
 */
public enum EnumExercise
{
    /**
     * 选项;
     */
    CHOICE_A("a", R.id.a_image_view, R.id.a_text_view, R.drawable.a_exercise),

    CHOICE_B("b", R.id.b_image_view, R.id.b_text_view, R.drawable.b_exercise),

    CHOICE_C("c", R.id.c_image_view, R.id.c_text_view, R.drawable.c_exercise),

    CHOICE_D("d", R.id.d_image_view, R.id.d_text_view, R.drawable.d_exercise),
    ;

    private String lowerCase;

    private int imageView;

    private int textView;

    private int imageResource;

    EnumExercise(String lowerCase, int imageView, int textView, int imageResource)
    {
        this.lowerCase = lowerCase;
        this.imageView = imageView;
        this.textView = textView;
        this.imageResource = imageResource;
    }

    public String getLowerCase()
    {
        return lowerCase;
    }

    public void setLowerCase(String lowerCase)
    {
        this.lowerCase = lowerCase;
    }

    public int getImageView()
    {
        return imageView;
    }

    public void setImageView(int imageView)
    {
        this.imageView = imageView;
    }

    public int getTextView()
    {
        return textView;
    }

    public void setTextView(int textView)
    {
        this.textView = textView;
    }

    public int getImageResource()
    {
        return imageResource;
    }

    public void setImageResource(int imageResource)
    {
        this.imageResource = imageResource;
    }

    private static final Map<String, EnumExercise> lowerCaseLookup = new HashMap<>();

    static
    {
        for (EnumExercise enumExercise : EnumSet.allOf(EnumExercise.class))
        {
            lowerCaseLookup.put(enumExercise.lowerCase, enumExercise);
        }
    }

    public static EnumExercise valueOf(int ordinal)
    {
        if (ordinal < 0 || ordinal > values().length - 1)
        {
            throw new IndexOutOfBoundsException("Invalid ordinal");
        }
        return EnumExercise.values()[ordinal];
    }

    public static int count()
    {
        return values().length;
    }

    public static EnumExercise selectEnumByLowerCase(String lowerCase)
    {
        return lowerCaseLookup.get(lowerCase);
    }

    public static boolean containsChoice(String lowerCase)
    {
        return lowerCaseLookup.containsKey(lowerCase);
    }
}
