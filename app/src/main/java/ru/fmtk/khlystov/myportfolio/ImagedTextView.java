package ru.fmtk.khlystov.myportfolio;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

public class ImagedTextView extends LinearLayout {

    @Nullable
    private String text = "";
    @Nullable
    private Drawable image = null;
    private int imageToTextMargin = 0;
    private final Context context;

    public ImagedTextView(@NonNull Context context) {
        super(context);
        this.context = context;
        init();
    }

    public ImagedTextView(@NonNull Context context, @NonNull AttributeSet attrs) {
        super(context, attrs);
        this.context = context;
        text = "";
        TypedArray typedArray = context.getTheme().obtainStyledAttributes(
                attrs,
                R.styleable.ImagedText,
                0, 0);

        try {
            image = typedArray.getDrawable(R.styleable.ImagedText_src);
            imageToTextMargin = typedArray.getDimensionPixelOffset(R.styleable.ImagedText_imageToTextMargin, 0);
            text = typedArray.getString(R.styleable.ImagedText_text);
        } finally {
            typedArray.recycle();
        }
        init();
    }

    private void init() {
        View rootView = inflate(context, R.layout.layout_imaged_text, this);
        TextView textView = rootView.findViewById(R.id.layout_imaged_text__text);
        textView.setText(text);
        if (text == null || text.isEmpty()) {
            textView.setVisibility(INVISIBLE);
        } else {
            textView.setVisibility(VISIBLE);
        }

        ImageView imageView = rootView.findViewById(R.id.layout_imaged_text__image);
        if (imageView.getLayoutParams() instanceof ViewGroup.MarginLayoutParams) {
            ViewGroup.MarginLayoutParams marginLayoutParams =
                    (ViewGroup.MarginLayoutParams) imageView.getLayoutParams();
            marginLayoutParams.setMarginEnd(imageToTextMargin);
            imageView.requestLayout();
        }
        imageView.setImageDrawable(image);
        if (image == null) {
            imageView.setVisibility(GONE);
        } else {
            imageView.setVisibility(VISIBLE);
        }
    }
}
