package com.example.flightbooking.interfaces;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

/**
 * Actions to do when a fragment is replaced
 */
public interface FragmentChange {
    /**
     * Replace a fragment
     * @param oldFragmentLabel the label of the fragment to be replaced
     * @param newFragmentLabel the label of the fragment to be inserted
     * @param success true if  action return a success response
     * @param data optional data that can pass to next fragment
     */
    public void fragmentChange(String oldFragmentLabel, String newFragmentLabel, boolean success, Bundle data);
}
