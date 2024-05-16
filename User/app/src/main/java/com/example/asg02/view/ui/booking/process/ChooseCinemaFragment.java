package com.example.asg02.view.ui.booking.process;

import android.annotation.SuppressLint;
import android.app.DatePickerDialog;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.LinearSnapHelper;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.SnapHelper;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.DatePicker;
import android.widget.TextView;
import android.widget.Toast;

import com.example.asg02.R;
import com.example.asg02.controller.cinema.GetCinemaController;
import com.example.asg02.controller.show.GetShowController;
import com.example.asg02.databinding.FragmentChooseCinemaBinding;
import com.example.asg02.model.Cinema;
import com.example.asg02.model.Manager;
import com.example.asg02.model.Movie;
import com.example.asg02.model.Show;
import com.example.asg02.view.RegisterActivity;
import com.example.asg02.view.Utils;
import com.example.asg02.view.ui.booking.process.adapter.CinemaAdapter;
import com.rezwan.rcalenderlib.callbacks.YearRangeListener;
import com.rezwan.rcalenderlib.models.RCalendar;
import com.rezwan.rcalenderlib.views.YearRangeCalendarView;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class ChooseCinemaFragment extends Fragment {
    private static final String ARG_PARAM1 = "manager";
    private Manager manager;
    private static final String ARG_PARAM2 = "movie";
    private Movie movie;
    private FragmentChooseCinemaBinding binding;
    private GetCinemaController getCinemaController;
    private GetShowController getShowController;
    private List<Cinema> cinemas = new ArrayList<>();
    private RecyclerView recyclerView;
    private CinemaAdapter cinemaAdapter;
    private TextView chooseDate;
    @SuppressLint("NotifyDataSetChanged")
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = FragmentChooseCinemaBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        getCinemaController = new GetCinemaController();
        getShowController = new GetShowController();

        chooseDate = binding.chooseDate;
        LocalDate currentDate = LocalDate.now();
        chooseDate.setText(currentDate.format(DateTimeFormatter.ofPattern("dd/MM/yyyy")));

        recyclerView = binding.recyclerview;

        manager = (Manager) getArguments().getSerializable(ARG_PARAM1);
        movie = (Movie) getArguments().getSerializable(ARG_PARAM2);

        binding.poster.setImageBitmap(Utils.decodeBitmap(movie.getPoster()));

        chooseDate.setOnClickListener(v -> {
            int iDate[] = new int[3];
            String sDate[] = chooseDate.getText().toString().split("/");
            for (int i = 0; i < sDate.length; i++) {
                iDate[i] = Integer.parseInt(sDate[i]);
            }
            new DatePickerDialog(getContext(),
                    new DatePickerDialog.OnDateSetListener() {
                        @Override
                        public void onDateSet(DatePicker datePicker, int i, int i1, int i2) {
                            chooseDate.setText(String.format("%02d/%02d/%04d", i2, i1 + 1, i));
                            changeDate(chooseDate.getText().toString());
                        }
                    }, iDate[2], iDate[1] - 1, iDate[0]).show();
        });

        changeDate(chooseDate.getText().toString());

        return root;
    }

    private void changeDate(String date) {
        cinemaAdapter = new CinemaAdapter(cinemas, movie.getId(), date);
        recyclerView.setAdapter(cinemaAdapter);

        cinemaAdapter.setOnItemClickListener((cinemaPos, showPos) -> {
            Cinema cinema = cinemas.get(cinemaPos);
            List<Show> shows = cinemaAdapter.getShowAdapters().get(cinemaPos).getShowList();
            Show show = shows.get(showPos);

            Bundle bundle = new Bundle();
            bundle.putSerializable("manager", manager);
            bundle.putSerializable("movie", movie);
            bundle.putSerializable("cinema", cinema);
            bundle.putSerializable("show", show);
            NavController controller = Navigation.findNavController(getActivity(), R.id.nav_host_fragment_content_main);
            controller.navigate(R.id.action_nav_choose_cinema_to_nav_choose_seat, bundle);
        });
        getCinemaController.getAllCinemas(manager.getEmail()).thenAccept(cinemas -> {
            if (cinemas != null) {
                this.cinemas.clear();
                this.cinemas.addAll(cinemas);
                cinemaAdapter.notifyDataSetChanged();
            }
        });

    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        binding = null;
    }
}