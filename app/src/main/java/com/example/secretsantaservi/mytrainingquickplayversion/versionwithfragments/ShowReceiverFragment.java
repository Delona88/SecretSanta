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

import static com.example.secretsantaservi.mytrainingquickplayversion.versionwithfragments.QuickPlayActivity.quickGame;

public class ShowReceiverFragment extends Fragment {

    private Button buttonBack;
    private TextView textView;

    private QuickPlayInterface activity;
    private int indexSanta;

    public ShowReceiverFragment() {

    }

    public static SetParamsFragment newInstance(String param1, String param2) {

        return new SetParamsFragment();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_show_receiver, container, false);

        buttonBack = rootView.findViewById(R.id.buttonBack);
        buttonBack.setOnClickListener(onClickListener);

        String str1 = getResources().getString(R.string.title_santa_nd_receiver);
        indexSanta = requireArguments().getInt("indexSanta",0);
        String str = String.format(str1, quickGame.getNameByIndex(indexSanta),quickGame.getNameReceiverBySantaIndex(indexSanta));
        textView = rootView.findViewById(R.id.textViewShowReceiver);
        textView.setText(str);


        return rootView;
    }

    private final View.OnClickListener onClickListener = new View.OnClickListener() {

        @Override
        public void onClick(View v) {
            if (v.getId() == buttonBack.getId()) {
                activity.popBackStack();
            }
        }
    };


    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);

        activity = (QuickPlayInterface) context;
    }
}