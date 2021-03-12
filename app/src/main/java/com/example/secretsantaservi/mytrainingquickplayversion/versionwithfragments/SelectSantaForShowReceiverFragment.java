package com.example.secretsantaservi.mytrainingquickplayversion.versionwithfragments;

import android.content.Context;
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

import static com.example.secretsantaservi.mytrainingquickplayversion.versionwithfragments.QuickPlayActivity.quickGame;

public class SelectSantaForShowReceiverFragment extends Fragment {

    private Button buttonContinue;
    private ListView listViewAllParticipants;
    private ArrayAdapter<String> adapter;

    private QuickPlayInterface activity;

    private NamesViewModel mViewModel;

    public static NamesFragment newInstance() {
        return new NamesFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_select_santa_for_show_receiver, container, false);

        buttonContinue = rootView.findViewById(R.id.buttonBack);
        buttonContinue.setOnClickListener(onClickListener);

        listViewAllParticipants = rootView.findViewById(R.id.listViewNames);

        adapter = new ArrayAdapter<>(getActivity(), android.R.layout.simple_list_item_1, quickGame.getParticipantsNames());
        listViewAllParticipants.setAdapter(adapter);
        listViewAllParticipants.setOnItemClickListener(onItemClickListener);

        return rootView;
    }

    private final View.OnClickListener onClickListener = new View.OnClickListener() {

        @Override
        public void onClick(View v) {

            if (v.getId() == buttonContinue.getId()) {
                activity.finish();
            }
        }
    };

    private final AdapterView.OnItemClickListener onItemClickListener = new AdapterView.OnItemClickListener() {
        @Override
        public void onItemClick(AdapterView<?> parent, View itemClicked, int position, long id) {
            try {
                activity.goToShowReceivers(position);
            } catch (NumberFormatException | NullPointerException ex) {
                ex.printStackTrace();
            }
        }
    };


    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = new ViewModelProvider(this).get(NamesViewModel.class);
        // TODO: Use the ViewModel
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);

        activity = (QuickPlayInterface) context;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }


}