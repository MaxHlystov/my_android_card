package ru.fmtk.khlystov.myportfolio;

import android.support.annotation.NonNull;

public class ConfigValues implements IConfigValues {

    private static IConfigValues config = null;

    @NonNull
    public static IConfigValues getConfig() {
        if(config == null)
            config = new ConfigValues();
        return config;
    }

    private ConfigValues() {}

    @Override
    public String getMyEmail() {
        return "maxvls@gmail.com";
    }

    @Override
    public SocialNetwork getURLTelegram() {
        return SocialNetwork.TELEGRAM;
    }

    @Override
    public SocialNetwork getURLGithub() {
        return SocialNetwork.GITHUB;
    }

    @Override
    public SocialNetwork getURLLinkedin() { return SocialNetwork.LINKEDIN; }

    @Override
    public SocialNetwork getURLStepik() {
        return SocialNetwork.STEPIK;
    }
}
