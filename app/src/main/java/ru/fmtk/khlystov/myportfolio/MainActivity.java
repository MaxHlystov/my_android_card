package ru.fmtk.khlystov.myportfolio;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;
import android.support.annotation.NonNull;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        addTextView(this, findViewById(R.id.main),
                getString(R.string.copyright));

        ImageView img_telegram = findViewById(R.id.telega);
        ImageView img_github = findViewById(R.id.github);
        ImageView img_linkedin = findViewById(R.id.linkedin);
        TextView tv_msg = findViewById(R.id.message);
        TextView tv_send_mail = findViewById(R.id.send_message);
        TextView tv_stepik = findViewById(R.id.key_info2);

        img_telegram.setOnClickListener((View img) -> {
            openURL(getString(R.string.url_telegram));
        });

        img_github.setOnClickListener((View img) -> {
            openURL(getString(R.string.url_github));
        });

        img_linkedin.setOnClickListener((View img) -> {
            openURL(getString(R.string.url_linkedin));
        });

        tv_stepik.setOnClickListener((View tv) -> {
            openURL(getString(R.string.stepik));
        });

        tv_send_mail.setOnClickListener((View tv) -> {
            Intent i = getMailIntent(getString(R.string.my_email), getString(R.string.greeting), tv_msg.getText().toString());
            if (i.resolveActivity(getPackageManager()) != null) {
                startActivity(i);
            } else {
                Toast.makeText(this, getString(R.string.NoEmail), Toast.LENGTH_LONG).show();
            }

        });
    }

    private TextView addTextView(@NonNull Activity activity,
                                 @NonNull RelativeLayout parent,
                                 @NonNull CharSequence text) {
        int margin = getResources().getDimensionPixelOffset(R.dimen.half_margin);
        TextView tv = new TextView(activity);
        tv.setText(text);
        RelativeLayout.LayoutParams layoutParams = new RelativeLayout.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT, // width
                ViewGroup.LayoutParams.WRAP_CONTENT); // height
        layoutParams.addRule(RelativeLayout.ALIGN_PARENT_RIGHT);
        layoutParams.addRule(RelativeLayout.ALIGN_PARENT_BOTTOM);
        layoutParams.setMargins(margin, margin, margin, margin);
        tv.setLayoutParams(layoutParams);
        parent.addView(tv);
        return tv;
    }

    public void openURL(@NonNull String url) {
        Intent intent = new Intent();
        intent.setAction(Intent.ACTION_VIEW);
        intent.addCategory(Intent.CATEGORY_BROWSABLE);
        intent.setData(Uri.parse(url));
        startActivity(intent);
    }

    private Intent getMailIntent(@NonNull String to,
                                 @NonNull String subject,
                                 @NonNull String msg) {
        Intent i = new Intent(Intent.ACTION_SENDTO);
        String mailto = "mailto:" + to + "?subject=" + subject + "&body=" + msg;
        i.setData(Uri.parse(mailto));
        return i;
    }

}
