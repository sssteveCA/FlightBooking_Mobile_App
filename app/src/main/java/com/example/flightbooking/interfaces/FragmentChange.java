package com.example.flightbooking.interfaces;

import androidx.fragment.app.Fragment;

/**
 * Actions to do when a fragment is replaced
 */
public interface FragmentChange {
    /**
     * Replace a fragment
     * @param oldFragment the fragment to be replaced
     * @param newFragment the new fragment inserted
     * @param success true if  action return a success response
     * @param data optional data that can pass to next fragment
     */
    public void onFragmentChange(Fragment oldFragment, Fragment newFragment, boolean success, Object data);
}
