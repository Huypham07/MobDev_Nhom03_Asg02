package com.example.asg02.view.ui.booking.process;

import android.annotation.SuppressLint;
import android.app.DatePickerDialog;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.DatePicker;
import android.widget.TextView;

import com.example.asg02.R;
import com.example.asg02.controller.cinema.GetCinemaController;
import com.example.asg02.controller.show.GetShowController;
import com.example.asg02.databinding.FragmentSelectCinemaBinding;
import com.example.asg02.model.Cinema;
import com.example.asg02.model.Manager;
import com.example.asg02.model.Movie;
import com.example.asg02.model.Show;
import com.example.asg02.utils.DateTimeUtils;
import com.example.asg02.utils.ImageUtils;
import com.example.asg02.view.ui.booking.process.adapter.CinemaAdapter;
import com.example.asg02.vm.BookingViewModel;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class SelectCinemaFragment extends Fragment {
    private BookingViewModel bookingViewModel;
    private FragmentSelectCinemaBinding binding;
    private Movie movie;
    private Manager manager;
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
        bookingViewModel = new ViewModelProvider(requireActivity()).get(BookingViewModel.class);
        // Inflate the layout for this fragment
        binding = FragmentSelectCinemaBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        recyclerView = binding.recyclerview;

        chooseDate = binding.chooseDate;
        chooseDate.setText(DateTimeUtils.getToday());

        getCinemaController = new GetCinemaController();
        getShowController = new GetShowController();

        bookingViewModel.isSelectCinemaAndShowReady().observe(
                getViewLifecycleOwner(),
                selectCinemaAndShowReady -> {
                    if (selectCinemaAndShowReady) {
                        this.movie = bookingViewModel.getMovie().getValue();
                        this.manager = bookingViewModel.getManager().getValue();
                        updateUI(chooseDate.getText().toString());
                    }
                }
        );

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
                            updateUI(chooseDate.getText().toString());
                        }
                    }, iDate[2], iDate[1] - 1, iDate[0]).show();
        });

        return root;

    }


    private void updateUI(String date) {
        if (manager == null || movie == null && date.isEmpty()) {
            return;
        }

        binding.poster.setImageBitmap(ImageUtils.decodeBitmap(movie.getPoster()));

        cinemaAdapter = new CinemaAdapter(cinemas, movie.getId(), date);
        recyclerView.setAdapter(cinemaAdapter);

        cinemaAdapter.setOnItemClickListener((cinemaPos, showPos) -> {
            Cinema cinema = cinemas.get(cinemaPos);
            List<Show> shows = cinemaAdapter.getShowAdapters().get(cinemaPos).getShowList();
            Show show = shows.get(showPos);
            bookingViewModel.setCinema(cinema);
            bookingViewModel.setShow(show);
            NavController controller = Navigation.findNavController(getActivity(), R.id.nav_host_fragment_content_main);
            controller.navigate(R.id.action_nav_select_cinema_to_nav_select_seat);
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