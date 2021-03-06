package com.example.swli.myapplication20150519.activity.bottombar;

import android.app.Activity;
import android.net.Uri;
import android.os.Bundle;
import android.app.Fragment;
import android.os.Environment;
import android.util.DisplayMetrics;
import android.view.Display;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.example.swli.myapplication20150519.R;
import com.example.swli.myapplication20150519.activity.BackupFilePickDialog;
import com.example.swli.myapplication20150519.activity.DaYunPickDialog;
import com.example.swli.myapplication20150519.common.MyApplication;
import com.example.swli.myapplication20150519.data.handler.MemberDataHandler;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link BottomBarFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link BottomBarFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class BottomBarFragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private OnFragmentInteractionListener mListener;

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment BottomBarFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static BottomBarFragment newInstance(String param1, String param2) {
        BottomBarFragment fragment = new BottomBarFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    public BottomBarFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    private Button btnImportDialog;
    String path = Environment.getExternalStorageDirectory() +"/"+
            MyApplication.getInstance().getResources().getString(R.string.externalSavingFolder)+"/";

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_bottom_bar, container, false);

        btnImportDialog = (Button) view.findViewById(R.id.btnImportDialog);

        btnImportDialog.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                BackupFilePickDialog backupFilePickDialog = new BackupFilePickDialog(getActivity());
                backupFilePickDialog.setCallBack(new BackupFilePickDialog.CallBack() {
                    @Override
                    public void invoke() {
                        if (mListener != null) {
                            mListener.onFragmentInteraction(null);
                        }
                    }
                });
                backupFilePickDialog.pickDialog();
            }
        });

        // Inflate the layout for this fragment
        return view;
    }

    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        try {
            mListener = (OnFragmentInteractionListener) activity;
        } catch (ClassCastException e) {
            throw new ClassCastException(activity.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }
    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p/>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        public void onFragmentInteraction(Uri uri);
    }

}
