package edu.miracostacollege.cs112.finalexam_entrysoftwarejobs.model;

// TODO: Implement the Serializable interface
// TODO: Implement the Comparable interface (with compareTo() method)
// TODO: Entry software jobs should be compared first by company,
// TODO: then pay, then remote status, finally by requirements

import java.io.Serializable;
import java.text.NumberFormat;
import java.util.Objects;

public abstract class EntrySoftwareJob implements Serializable, Comparable<EntrySoftwareJob> {

    protected String mCompany;
    protected Double mPay;
    protected boolean mRemote;
    protected String mRequirements;
    protected static NumberFormat currency = NumberFormat.getCurrencyInstance();

    public EntrySoftwareJob(String company, Double pay, boolean remote, String requirements) {
        mCompany = company;
        mPay = pay;
        mRemote = remote;
        mRequirements = requirements;
    }

    public String getCompany() {
        return mCompany;
    }

    public void setCompany(String company) {
        mCompany = company;
    }

    public Double getPay() {
        return mPay;
    }

    public void setPay(Double pay) {
        mPay = pay;
    }

    public boolean isRemote() {
        return mRemote;
    }

    public void setRemote(boolean remote) {
        mRemote = remote;
    }

    public String getRequirements() {
        return mRequirements;
    }

    public void setRequirements(String requirements) {
        mRequirements = requirements;
    }

    public static NumberFormat getCurrency() {
        return currency;
    }

    public static void setCurrency(NumberFormat currency) {
        EntrySoftwareJob.currency = currency;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        EntrySoftwareJob that = (EntrySoftwareJob) o;
        return mRemote == that.mRemote && Objects.equals(mCompany, that.mCompany) && Objects.equals(mPay, that.mPay) && Objects.equals(mRequirements, that.mRequirements);
    }

    @Override
    public int hashCode() {
        return Objects.hash(mCompany, mPay, mRemote, mRequirements);
    }

    // TODO: Override the compareTo() method as described:
  @Override
  public int compareTo(EntrySoftwareJob other) {
      // TODO: Entry software jobs should be compared first by company,
      int companyComp = mCompany.compareTo(other.mCompany);
      if (companyComp != 0) return companyComp;

      int payComp = Double.compare(mPay ,other.mPay);
      if (payComp != 0) return payComp;
      // TODO: then pay, then remote status, finally by requirements
      int remoteComp = Boolean.compare(mRemote, other.mRemote);
      if (remoteComp != 0) return remoteComp;

      return mRequirements.compareTo(other.mRequirements);
  }



}
