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

public class NamesFragment extends Fragment {
    private Button buttonAddName;
    private Button buttonContinue;
    private ListView listViewAllParticipants;
    private ArrayAdapter<String> adapter;
    private EditText editText;

    private QuickPlayInterface activity;

    private boolean allParticipantsAdded = false;

    private NamesViewModel mViewModel;

    public static NamesFragment newInstance() {
        return new NamesFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_names, container, false);

        buttonAddName = rootView.findViewById(R.id.buttonAddName);
        buttonAddName.setOnClickListener(onClickListener);
        buttonContinue = rootView.findViewById(R.id.buttonBack);
        buttonContinue.setOnClickListener(onClickListener);

        editText = rootView.findViewById(R.id.editTextPersonName);

        listViewAllParticipants = rootView.findViewById(R.id.listViewNames);

        adapter = new ArrayAdapter<>(getActivity(), android.R.layout.simple_list_item_1, quickGame.getParticipantsNames());
        listViewAllParticipants.setAdapter(adapter);

        return rootView;
    }

    private final View.OnClickListener onClickListener = new View.OnClickListener() {

        @Override
        public void onClick(View v) {
            if (v.getId() == buttonAddName.getId()) {
                String name = editText.getText().toString();
                if (name.length() != 0) {
                    adapter.add(name);
                    editText.setText("");
                    adapter.notifyDataSetChanged();
                    quickGame.addNewPersonByName(name);
                } else {
                    showToastIncorrectInfo();
                }
            }
            if (v.getId() == buttonContinue.getId()) {
                activity.goToSelectNamesForSetParams();
            }
        }
    };

    public void showToastIncorrectInfo() {
        Toast.makeText(getActivity(), getResources().getString(R.string.title_incorrect_info), Toast.LENGTH_LONG).show();
    }

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