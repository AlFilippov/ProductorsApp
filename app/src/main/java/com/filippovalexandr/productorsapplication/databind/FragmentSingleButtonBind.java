package com.filippovalexandr.productorsapplication.databind;

public class FragmentSingleButtonBind {
private String mNameButton;

    public String getNameButton() {
        return mNameButton;
    }

    public void setNameButton(String nameButton) {
        mNameButton = nameButton;
    }

    public FragmentSingleButtonBind(String nameButton) {
        mNameButton = nameButton;
    }
}
