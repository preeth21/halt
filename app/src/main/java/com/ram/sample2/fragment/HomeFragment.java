package com.ram.sample2.fragment;

import android.os.Bundle;
//import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.ram.sample2.R;
import com.ram.sample2.adapter.RecyclerHomeAdapter;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link HomeFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class HomeFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    FirebaseDatabase database = FirebaseDatabase.getInstance("https://halt-2108-default-rtdb.firebaseio.com/");
    DatabaseReference myRef = database.getReference();

    public  FirebaseAuth auth;
    public FirebaseUser user;
    public TextView name,reg,ins,sem,prog,gen,dob;
    public RecyclerView.LayoutManager layoutManager;
    public RecyclerView recyclerView;
    public RecyclerHomeAdapter recyclerHomeAdapter;
View view;

    public HomeFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment HomeFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static HomeFragment newInstance(String param1, String param2) {
        HomeFragment fragment = new HomeFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        //View v=new View(container.getContext());
        view=inflater.inflate(R.layout.fragment_home, container, false);
//        name=view.findViewById(R.id.txtName);
//        reg=view.findViewById(R.id.txtReg);
//        ins=view.findViewById(R.id.txtInst);
//        sem=view.findViewById(R.id.txtSem);
//        prog=view.findViewById(R.id.txtProgram);
//        gen=view.findViewById(R.id.txtGen);
//        dob=view.findViewById(R.id.txtDob);
        auth = FirebaseAuth.getInstance();
        user = auth.getCurrentUser();
        recyclerView=view.findViewById(R.id.recyclerHome);
        layoutManager= new LinearLayoutManager(getContext());
        recyclerView.addItemDecoration(new DividerItemDecoration(getContext(),LinearLayoutManager.VERTICAL));

        myRef.child("users").child(user.getUid()).get().addOnCompleteListener(new OnCompleteListener<DataSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DataSnapshot> task) {
                if (!task.isSuccessful()) {
                    Log.e("firebase", "Error getting data", task.getException());
                }
                else {
                    HashMap<String,String> mp= (HashMap<String, String>) task.getResult().getValue();
//                    Iterator<Map.Entry<String, String>> itr = mp.entrySet().iterator();
//                    while(itr.hasNext()) {
//                        System.out.println("hitonyz " + mp);
//                        Map.Entry<String, String> entry = itr.next();
//                        System.out.println("Key = " + entry.getKey() +
//                                ", Value = " + entry.getValue());
//                    }
                    recyclerHomeAdapter=new RecyclerHomeAdapter(mp);
                    recyclerView.setAdapter(recyclerHomeAdapter);
                    recyclerView.setLayoutManager(layoutManager);
//                    Log.d("firebase", String.valueOf(task.getResult().getValue()));
//                    name.setText(mp.get("name"));
//                    reg.setText(mp.get("reg"));
//                    ins.setText(mp.get("ins"));
//                    sem.setText(mp.get("sem"));
//                    prog.setText(mp.get("program"));
//                    gen.setText(mp.get("gender"));
//                    dob.setText(mp.get("dob"));

                    Log.d("firebase", mp.get("name"));
                }
            }
        });

        return view;

    }
}