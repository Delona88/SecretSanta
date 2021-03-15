package com.example.secretsantaservi.mytrainingquickplayversion.versionwithfragments;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.util.SparseBooleanArray;
import android.widget.*;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.example.secretsantaservi.R;
import com.example.secretsantaservi.mytrainingquickplayversion.model.PersonForQuickGame;

import java.util.ArrayList;

import static com.example.secretsantaservi.activities.MainSelectVersionActivity.quickGame;
import static com.example.secretsantaservi.mytrainingquickplayversion.numbersofparicipantsMVP.NumberOfParticipantsActivity.game;

public class SetParamsFragment extends Fragment {

    private Button buttonBack;
    private TextView textView;
    private ListView listViewAllParticipants;
    private ArrayAdapter<String> adapter;

    private QuickPlayInterface activity;
    private int indexSanta;
    private ArrayList<String> receiverNames;

    public static SetParamsFragment newInstance(int indexSanta) {
        Bundle bundle = new Bundle();
        bundle.putInt("indexSanta", indexSanta);
        SetParamsFragment fragment = new SetParamsFragment();
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
        View rootView = inflater.inflate(R.layout.fragment_set_params, container, false);

        buttonBack = rootView.findViewById(R.id.buttonBack);
        buttonBack.setOnClickListener(onClickListener);

        String str1 = getResources().getString(R.string.title_choose_naughtylist_for_santa);
        indexSanta = requireArguments().getInt("indexSanta", 0);
        String str = String.format(str1, quickGame.getNameByIndex(indexSanta));
        textView = rootView.findViewById(R.id.textViewShowReceiver);
        textView.setText(str);

        listViewAllParticipants = rootView.findViewById(R.id.listViewNames);
        receiverNames = quickGame.getParticipantsNamesWithoutSanta(indexSanta);
        adapter = new ArrayAdapter<>(getActivity(), android.R.layout.simple_list_item_multiple_choice, receiverNames);
        listViewAllParticipants.setChoiceMode(ListView.CHOICE_MODE_MULTIPLE);

        listViewAllParticipants.setAdapter(adapter);

        return rootView;
    }

    private final View.OnClickListener onClickListener = new View.OnClickListener() {

        @Override
        public void onClick(View v) {
            if (v.getId() == buttonBack.getId()) {
                quickGame.getPersonByIndex(indexSanta).setNaughtyList(new ArrayList<>());
                addChosenInSantaNaughtyList();
                activity.removeFragmentSetParams();
            }
        }
    };

    private void addChosenInSantaNaughtyList() {
        SparseBooleanArray chosen = listViewAllParticipants.getCheckedItemPositions();
        for (int i = 0; i < chosen.size(); i++) {
            if (chosen.valueAt(i)) {
                PersonForQuickGame person = quickGame.getPersonByName(receiverNames.get(chosen.keyAt(i)));
                quickGame.getPersonByIndex(indexSanta).addInNaughtyList(person);


                Log.d("fillNaughtyList", "NaughtyList ------>" + person.toString());
                Log.d("fillNaughtyList", "NaughtyList ------>" + quickGame.getPersonByIndex(indexSanta).getNaughtyList().toString());
            }
        }
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);

        activity = (QuickPlayInterface) context;
    }
}