package br.com.silver.findmovie;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by silver on 30/03/16.
 */
public class HomeFragment extends Fragment {

    @Override
    public void onCreate(Bundle savedInstance) {
        super.onCreate(savedInstance);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstance) {

        View layout = inflater.inflate(R.layout.fragment_home, container, false);

        return layout;
    }
}
