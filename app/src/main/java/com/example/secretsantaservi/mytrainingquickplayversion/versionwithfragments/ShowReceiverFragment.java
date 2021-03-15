package com.example.secretsantaservi.mytrainingquickplayversion.versionwithfragments;

import android.content.Context;
import android.os.Bundle;
import android.widget.*;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.example.secretsantaservi.R;

import static com.example.secretsantaservi.activities.MainSelectVersionActivity.quickGame;

public class ShowReceiverFragment extends Fragment {

    private Button buttonBack;
    private QuickPlayInterface activity;
    private int indexSanta;

    public static ShowReceiverFragment newInstance(int indexSanta) {
        Bundle bundle = new Bundle();
        bundle.putInt("indexSanta", indexSanta);
        ShowReceiverFragment fragment = new ShowReceiverFragment();
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        indexSanta = getArguments().getInt("indexSanta", 0);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_show_receiver, container, false);

        buttonBack = rootView.findViewById(R.id.buttonBack);
        buttonBack.setOnClickListener(onClickListener);

        String str1 = getResources().getString(R.string.title_santa_nd_receiver);
        indexSanta = requireArguments().getInt("indexSanta", 0);
        String str = String.format(str1, quickGame.getNameByIndex(indexSanta), quickGame.getNameReceiverBySantaIndex(indexSanta));
        TextView textView = rootView.findViewById(R.id.textViewShowReceiver);
        textView.setText(str);

        return rootView;
    }

    private final View.OnClickListener onClickListener = new View.OnClickListener() {

        @Override
        public void onClick(View v) {
            if (v.getId() == buttonBack.getId()) {
                activity.removeFragmentShowReceiver();
            }
        }
    };


    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);

        activity = (QuickPlayInterface) context;
    }
}