package ru.fmtk.khlystov.myportfolio;


import android.support.annotation.NonNull;

public interface IConfigValues {

    @NonNull
    String getMyEmail();

    @NonNull
    SocialNetwork getURLTelegram();

    @NonNull
    SocialNetwork getURLGithub();

    @NonNull
    SocialNetwork getURLLinkedin();

    @NonNull
    SocialNetwork getURLStepik();
}
