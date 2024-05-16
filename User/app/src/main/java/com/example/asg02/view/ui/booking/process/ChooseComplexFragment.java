package com.example.asg02.view.ui.booking.process;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridView;

import com.example.asg02.R;
import com.example.asg02.controller.account.GetAllManagerController;
import com.example.asg02.databinding.FragmentChooseComplexBinding;
import com.example.asg02.model.Account;
import com.example.asg02.model.Manager;
import com.example.asg02.model.Movie;
import com.example.asg02.view.ui.booking.process.adapter.GridViewAdapter;

import java.util.ArrayList;
import java.util.List;

public class ChooseComplexFragment extends Fragment {

    private FragmentChooseComplexBinding binding;
    private GridViewAdapter adapter;
    private List<Manager> managers = new ArrayList<>();
    private GridView gridView;
    private Movie movie;
    private static final String ARG_PARAM = "movie";

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentChooseComplexBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        movie = (Movie) getArguments().getSerializable(ARG_PARAM);

        gridView = binding.gridView;

        new GetAllManagerController().getAllManagers().thenAccept(accounts -> {
            if (accounts != null) {
                managers.clear();

                for (Account a : accounts) {
                    Manager m = (Manager) a;
                    managers.add(m);
                }

                adapter = new GridViewAdapter(getContext(), managers);
                gridView.setAdapter(adapter);
                gridView.setOnItemClickListener((parent, view, position, id) -> {
                    Manager manager = managers.get(position);
                    // do something with manager
                    NavController navController = Navigation.findNavController(getActivity(), R.id.nav_host_fragment_content_main);
                    Bundle bundle = new Bundle();
                    bundle.putSerializable("manager", manager);
                    bundle.putSerializable("movie", movie);
                    navController.navigate(R.id.action_nav_choose_complex_to_nav_choose_cinema, bundle);
                });
            } else {
//                Log.e("Manager", "null");
            }
        });
        return root;

    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        binding = null;
    }
}