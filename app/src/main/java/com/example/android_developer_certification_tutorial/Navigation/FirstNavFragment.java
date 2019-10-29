package com.example.android_developer_certification_tutorial.Navigation;

//import android.content.Context;
//import android.net.Uri;
//import android.os.Bundle;
//
//import androidx.fragment.app.Fragment;
//
//import android.view.LayoutInflater;
//import android.view.View;
//import android.view.ViewGroup;


///**
// * A simple {@link Fragment} subclass.
// * Activities that contain this fragment must implement the
// * {@link FirstNavFragment.OnFragmentInteractionListener} interface
// * to handle interaction events.
// * Use the {@link FirstNavFragment#newInstance} factory method to
// * create an instance of this fragment.
// */
//public class FirstNavFragment extends Fragment {
//    // TODO: Rename parameter arguments, choose names that match
//    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
//    private static final String ARG_PARAM1 = "param1";
//    private static final String ARG_PARAM2 = "param2";
//
//    // TODO: Rename and change types of parameters
//    private String mParam1;
//    private String mParam2;
//
//    private OnFragmentInteractionListener mListener;
//
//    public FirstNavFragment() {
//        // Required empty public constructor
//    }
//
//    /**
//     * Use this factory method to create a new instance of
//     * this fragment using the provided parameters.
//     *
//     * @param param1 Parameter 1.
//     * @param param2 Parameter 2.
//     * @return A new instance of fragment FirstNavFragment.
//     */
//    // TODO: Rename and change types and number of parameters
//    public static FirstNavFragment newInstance(String param1, String param2) {
//        FirstNavFragment fragment = new FirstNavFragment();
//        Bundle args = new Bundle();
//        args.putString( ARG_PARAM1, param1 );
//        args.putString( ARG_PARAM2, param2 );
//        fragment.setArguments( args );
//        return fragment;
//    }
//
//    @Override
//    public void onCreate(Bundle savedInstanceState) {
//        super.onCreate( savedInstanceState );
//        if (getArguments() != null) {
//            mParam1 = getArguments().getString( ARG_PARAM1 );
//            mParam2 = getArguments().getString( ARG_PARAM2 );
//        }
//    }
//
//    @Override
//    public View onCreateView(LayoutInflater inflater, ViewGroup container,
//                             Bundle savedInstanceState) {
//        // Inflate the layout for this fragment
//        return inflater.inflate( R.layout.fragment_first_nav, container, false );
//    }
//
//    // TODO: Rename method, update argument and hook method into UI event
//    public void onButtonPressed(Uri uri) {
//        if (mListener != null) {
//            mListener.onFragmentInteraction( uri );
//        }
//    }
//
//    @Override
//    public void onAttach(Context context) {
//        super.onAttach( context );
//        if (context instanceof OnFragmentInteractionListener) {
//            mListener = (OnFragmentInteractionListener) context;
//        } else {
//            throw new RuntimeException( context.toString()
//                    + " must implement OnFragmentInteractionListener" );
//        }
//    }
//
//    @Override
//    public void onDetach() {
//        super.onDetach();
//        mListener = null;
//    }
//
//    /**
//     * This interface must be implemented by activities that contain this
//     * fragment to allow an interaction in this fragment to be communicated
//     * to the activity and potentially other fragments contained in that
//     * activity.
//     * <p>
//     * See the Android Training lesson <a href=
//     * "http://developer.android.com/training/basics/fragments/communicating.html"
//     * >Communicating with Other Fragments</a> for more information.
//     */
//    public interface OnFragmentInteractionListener {
//        // TODO: Update argument type and name
//        void onFragmentInteraction(Uri uri);
//    }
//}

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.NavDirections;
import androidx.navigation.Navigation;

import com.example.android_developer_certification_tutorial.R;

public class FirstNavFragment extends Fragment {

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate( R.layout.fragment_first_nav, container, false);
    }


    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        //Create args for 2nd fragment programatically
        final NavController navController = Navigation.findNavController(getActivity(), R.id.nav_host_fragment);
        final Bundle bundle = new Bundle();
        bundle.putBoolean("test_boolean", true);

//        navController.navigate(R.id.action_firstNavFragment_to_secondNavFragment, bundle);

        //onClick
        View.OnClickListener Listener = Navigation.createNavigateOnClickListener(R.id.action_firstNavFragment_to_secondNavFragment,bundle);
        view.setOnClickListener(Listener);

        Toast.makeText(getActivity().getApplicationContext(), "Bundle args " + FirstNavFragmentArgs.fromBundle(getArguments()).getInputToFirstNavFrag(), Toast.LENGTH_SHORT).show();


    }


}

