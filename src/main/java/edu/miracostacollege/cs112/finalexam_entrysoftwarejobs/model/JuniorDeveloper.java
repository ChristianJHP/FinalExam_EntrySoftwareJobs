package edu.miracostacollege.cs112.finalexam_entrysoftwarejobs.model;

import java.io.Serializable;

public class JuniorDeveloper extends EntrySoftwareJob implements Serializable {

    private String mLanguages;

    public JuniorDeveloper(String company, Double pay, boolean remote, String requirements, String languages) {
        super(company, pay, remote, requirements);
        mLanguages = languages;
    }

    public String getLanguages() {
        return mLanguages;
    }

    public void setLanguages(String languages) {
        mLanguages = languages;
    }

    @Override
    public String toString() {
        return "JuniorDeveloper{" +
                "mCompany='" + mCompany + '\'' +
                ", mPay=" + currency.format(mPay) +
                ", mRemote=" + mRemote +
                ", mRequirements='" + mRequirements + '\'' +
                ", mLanguages='" + mLanguages + '\'' +
                '}';
    }
}
