package ru.fmtk.khlystov.myportfolio;

import android.app.Activity;
import android.content.Intent;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Button;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        createCopyright();

        ImageView imgTelegram = findViewById(R.id.telega);
        ImageView imgGithub = findViewById(R.id.github);
        ImageView imgLinkedin = findViewById(R.id.linkedin);
        TextView tvMsg = findViewById(R.id.message);
        Button btnSendMail = findViewById(R.id.btn_send_message);
        ImagedTextView tvStepik = findViewById(R.id.key_info2);

        IConfigValues config = ConfigValues.getConfig();

        imgTelegram.setOnClickListener((View view) ->
                socialLinkOnClickHandler(view, config.getURLTelegram()));

        imgGithub.setOnClickListener((View view) ->
                socialLinkOnClickHandler(view, config.getURLGithub()));

        imgLinkedin.setOnClickListener((View view) ->
                socialLinkOnClickHandler(view, config.getURLLinkedin()));

        tvStepik.setOnClickListener((View view) ->
                socialLinkOnClickHandler(view, config.getURLStepik()));

        btnSendMail.setOnClickListener((View view) -> {
            Intent intent = MailIntent.get(config.getMyEmail(),
                    getString(R.string.greeting),
                    tvMsg.getText().toString());
            showIntent(view, intent, getString(R.string.no_email));
        });
    }

    private void createCopyright() {
        RelativeLayout.LayoutParams layoutParams = getDefaultRLayoutParams();
        layoutParams.addRule(RelativeLayout.ALIGN_BOTTOM);
        layoutParams.addRule(RelativeLayout.BELOW, R.id.social);
        addTextView(this, findViewById(R.id.main),
                getString(R.string.copyright), layoutParams);
    }

    private void addTextView(@NonNull Activity activity,
                             @NonNull RelativeLayout parent,
                             @NonNull CharSequence text,
                             @NonNull RelativeLayout.LayoutParams layoutParams) {
        TextView tv = new TextView(activity);
        tv.setText(text);
        tv.setLayoutParams(layoutParams);
        parent.addView(tv);
    }

    @NonNull
    private RelativeLayout.LayoutParams getDefaultRLayoutParams() {
        int margin = getResources().getDimensionPixelOffset(R.dimen.half_margin);
        RelativeLayout.LayoutParams layoutParams = new RelativeLayout.LayoutParams(
                ViewGroup.LayoutParams.WRAP_CONTENT, // width
                ViewGroup.LayoutParams.WRAP_CONTENT); // height
        layoutParams.addRule(RelativeLayout.ALIGN_PARENT_RIGHT);
        layoutParams.setMargins(margin, margin, margin, margin);
        return layoutParams;
    }

    private void socialLinkOnClickHandler(@NonNull View parent, @NonNull SocialNetwork link) {
        showIntent(parent,
                BrowserIntent.get(link.getUrl()),
                getString(R.string.no_browser_error));
    }

    private void showIntent(@NonNull View parent,
                            @NonNull Intent intent,
                            @NonNull String errorMessage) {
        if (intent.resolveActivity(getPackageManager()) != null) {
            startActivity(intent);
        } else {
            Snackbar.make(parent, errorMessage, Snackbar.LENGTH_LONG).show();
        }
    }
}
