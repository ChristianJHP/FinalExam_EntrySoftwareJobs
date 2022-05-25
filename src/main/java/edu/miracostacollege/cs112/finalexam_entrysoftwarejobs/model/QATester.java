package edu.miracostacollege.cs112.finalexam_entrysoftwarejobs.model;

import java.io.Serializable;

public class QATester extends EntrySoftwareJob implements Serializable {

    private String mTestAutomation;

    public QATester(String company, Double pay, boolean remote, String requirements, String testAutomation) {
        super(company, pay, remote, requirements);
        mTestAutomation = testAutomation;
    }

    public String getTestAutomation() {
        return mTestAutomation;
    }

    public void setTestAutomation(String testAutomation) {
        mTestAutomation = testAutomation;
    }

    @Override
    public String toString() {
        return "QATester{" +
                "mCompany='" + mCompany + '\'' +
                ", mPay=" + currency.format(mPay) +
                ", mRemote=" + mRemote +
                ", mRequirements='" + mRequirements + '\'' +
                ", mTestAutomation='" + mTestAutomation + '\'' +
                '}';
    }
}
