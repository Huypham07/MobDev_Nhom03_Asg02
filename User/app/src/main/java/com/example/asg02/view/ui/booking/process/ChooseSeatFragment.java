package com.example.asg02.view.ui.booking.process;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.asg02.R;
import com.example.asg02.controller.seat.GetSeatController;
import com.example.asg02.databinding.FragmentChooseSeatBinding;
import com.example.asg02.model.Seat;
import com.google.android.material.snackbar.Snackbar;

import java.util.ArrayList;
import java.util.List;

public class ChooseSeatFragment extends Fragment implements View.OnClickListener {
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private FragmentChooseSeatBinding binding;
    private int row;
    private int col;
    private List<Integer> selectedSeat = new ArrayList<>();
    List<TextView> seatViews = new ArrayList<>();
    List<Seat> seatList;
    int seatSize = 100;
    int seatGaping = 10;

    ViewGroup viewGroup;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentChooseSeatBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        //============== TEST UI =====================
        row = 10;
        col = 20;
        seatList = new ArrayList<>();
        for (int i = 0; i < row * col; i++) {
            String sRow = String.valueOf((char) ('A' + i / col));
            Seat seat = new Seat(sRow, i % col + 1, 0, 1);
            if (i % 3 == 0) {
                seat.setStatus(Seat.BOOKED);
            }
            if ((i / col) >= row / 2) {
                seat.setPrice(100);
            } else {
                seat.setPrice(75);
            }
            seatList.add(seat);
        }
        //============ END TEST UI ===================

        GetSeatController controller = new GetSeatController();
        controller.sortListofSeat(seatList);

        viewGroup = binding.layoutSeat;

        LinearLayout layoutSeat = new LinearLayout(getContext());
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        layoutSeat.setOrientation(LinearLayout.VERTICAL);
        layoutSeat.setLayoutParams(params);
        layoutSeat.setPadding(8 * seatGaping, 8 * seatGaping, 8 * seatGaping, 8 * seatGaping);
        viewGroup.addView(layoutSeat);

        LinearLayout layout;

        int count = 0;

        for (int i = 0; i < row; i++) {
            layout = new LinearLayout(getContext());
            layout.setOrientation(LinearLayout.HORIZONTAL);
            layoutSeat.addView(layout);

            for (int j = 0; j < col; j++) {
                Seat s = seatList.get(count);
                TextView seat = new TextView(getContext());
                LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(seatSize, seatSize);
                layoutParams.setMargins(seatGaping, seatGaping, seatGaping, seatGaping);
                seat.setLayoutParams(layoutParams);
                seat.setPadding(seatGaping, 0, seatGaping, 2 * seatGaping);
                seat.setGravity(Gravity.CENTER);
                seat.setId(s.getId());
                if (s.getPrice() > 75){
                    seat.setHint("VIP");
                } else {
                    seat.setHint("Normal");
                }
                seat.setText("" + s.getSeatRow().toUpperCase() + s.getSeatNumber());
                seat.setTag(s.getStatus());
                switch (s.getStatus()) {
                    case Seat.AVAILABLE:
                        if (s.getPrice() > 75) {
                            seat.setBackgroundResource(R.drawable.ic_seats_available_vip);
                        } else {
                            seat.setBackgroundResource(R.drawable.ic_seats_available);
                        }
                        seat.setTextColor(getResources().getColor(R.color.black));
                        break;
                    case Seat.BOOKED:
                        seat.setBackgroundResource(R.drawable.ic_seats_booked);
                        seat.setTextColor(getResources().getColor(R.color.white));
                        break;
                }

                layout.addView(seat);
                seatViews.add(seat);
                seat.setOnClickListener(this);
                count++;
            }
        }

        return root;
    }

    @Override
    public void onClick(View v) {
        if ((int) v.getTag() == Seat.AVAILABLE) {
            if (selectedSeat.contains(v.getId())) {
                selectedSeat.remove(Integer.valueOf(v.getId()));
                if (((TextView) v).getHint().equals("VIP")) {
                    v.setBackgroundResource(R.drawable.ic_seats_available_vip);
                } else {
                    v.setBackgroundResource(R.drawable.ic_seats_available);
                }
                ((TextView) v).setTextColor(getResources().getColor(R.color.black));
            } else {
                selectedSeat.add(v.getId());
                v.setBackgroundResource(R.drawable.ic_seats_selected);
                ((TextView) v).setTextColor(getResources().getColor(R.color.white));
            }
        } else if ((int) v.getTag() == Seat.BOOKED) {
            Snackbar.make(v, "Ghế đã được đặt, vui lòng chọn ghế khác", Snackbar.LENGTH_LONG).show();
        }
    }


}