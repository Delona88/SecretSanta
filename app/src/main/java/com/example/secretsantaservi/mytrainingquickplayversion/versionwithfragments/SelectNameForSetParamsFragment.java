package com.example.secretsantaservi.mytrainingquickplayversion.versionwithfragments;

import android.content.Context;
import android.util.Log;
import android.widget.*;
import androidx.lifecycle.ViewModelProvider;
import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.example.secretsantaservi.R;
import com.example.secretsantaservi.mytrainingquickplayversion.model.BadConditionsException;

import static com.example.secretsantaservi.activities.MainSelectVersionActivity.quickGame;


public class SelectNameForSetParamsFragment extends Fragment {

    private Button buttonContinue;
    private ListView listViewAllParticipants;
    private ArrayAdapter<String> adapter;

    private QuickPlayInterface activity;

    private NamesViewModel mViewModel;

    public static SelectNameForSetParamsFragment newInstance() {
        return new SelectNameForSetParamsFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_select_name_for_set_params, container, false);

        buttonContinue = rootView.findViewById(R.id.buttonBack);
        buttonContinue.setOnClickListener(onClickListener);

        listViewAllParticipants = rootView.findViewById(R.id.listViewNames);

        adapter = new ArrayAdapter<>(getActivity(), android.R.layout.simple_list_item_1, quickGame.getParticipantsNames());
        listViewAllParticipants.setAdapter(adapter);
        listViewAllParticipants.setOnItemClickListener(onItemClickListener);

        return rootView;
    }

    @Override
    public void onStart() {
        super.onStart();
        adapter = new ArrayAdapter<>(getActivity(), android.R.layout.simple_list_item_1, quickGame.getParticipantsNames());
        listViewAllParticipants.setAdapter(adapter);
        listViewAllParticipants.setOnItemClickListener(onItemClickListener);

    }
    @Override
    public void onResume() {
        super.onResume();
        adapter = new ArrayAdapter<>(getActivity(), android.R.layout.simple_list_item_1, quickGame.getParticipantsNames());
        listViewAllParticipants.setAdapter(adapter);
        listViewAllParticipants.setOnItemClickListener(onItemClickListener);

    }

    private final View.OnClickListener onClickListener = new View.OnClickListener() {

        @Override
        public void onClick(View v) {

            if (v.getId() == buttonContinue.getId()) {
                try {
                    quickGame.fillReceivers();
                    Log.d("fillReceivers","Receivers" + quickGame.toString());
                    activity.goToSelectNameForShowReceiver();
                } catch (BadConditionsException e) {
                    e.printStackTrace();
                    showToastIncorrectInfo();
                }
            }
        }
    };

    private final AdapterView.OnItemClickListener onItemClickListener = new AdapterView.OnItemClickListener() {
        @Override
        public void onItemClick(AdapterView<?> parent, View itemClicked, int position, long id) {
            try {
                activity.goToSetParams(position);
            } catch (NumberFormatException | NullPointerException ex) {
                ex.printStackTrace();
            }
        }
    };

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);

        activity = (QuickPlayInterface) context;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    public void showToastIncorrectInfo() {
        Toast.makeText(getActivity(), getResources().getString(R.string.title_bad_conditions), Toast.LENGTH_LONG).show();
    }
}