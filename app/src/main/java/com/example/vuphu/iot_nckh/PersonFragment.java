package com.example.vuphu.iot_nckh;

import android.content.Context;
import android.content.Intent;
import android.content.res.TypedArray;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.Arrays;
import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;


public class PersonFragment extends Fragment {

    private RecyclerView list_profile;
    private List<String> txt_profile;
    private TypedArray img_profile;
    private List<String> txt_des;
    private Context context;

    private TextView name, email;

    private CircleImageView avatar;


    public PersonFragment() {

    }

    public static PersonFragment newInstance() {
        PersonFragment fragment = new PersonFragment();
        Bundle args = new Bundle();

        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view= inflater.inflate(R.layout.fragment_person, container, false);
        list_profile = view.findViewById(R.id.list_profile);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity());
        list_profile.setLayoutManager(linearLayoutManager);
        list_profile.setHasFixedSize(true);
        txt_profile = Arrays.asList(getResources().getStringArray(R.array.list_profile_txt));
        txt_des = Arrays.asList(getResources().getStringArray(R.array.list_profile_des));
        img_profile =getResources().obtainTypedArray(R.array.list_profile_img);
        adapter adapter = new adapter(txt_profile,img_profile,txt_des,getActivity());
        list_profile.setAdapter(adapter);

        name = view.findViewById(R.id.profile_name);
        email = view.findViewById(R.id.profile_email);
        avatar = view.findViewById(R.id.profile_avatar);
        return view;
    }

    private static  class viewHolder extends RecyclerView.ViewHolder{

        ImageView img;
        TextView text, des;
        public viewHolder(View itemView) {
            super(itemView);
            img = itemView.findViewById(R.id.img_prof);
            text = itemView.findViewById(R.id.txt_prof);
            des = itemView.findViewById(R.id.txt_descrip);
        }

    }

    private class adapter extends RecyclerView.Adapter<viewHolder> {

        List<String> txt_profile;
        TypedArray img_profile;
        List<String> txt_des;
        Context context;

        public adapter(List<String> txt_profile, TypedArray img_profile, List<String> txt_des, Context context) {
            this.txt_profile = txt_profile;
            this.img_profile = img_profile;
            this.txt_des = txt_des;
            this.context = context;
        }

        @Override
        public viewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(context).inflate(R.layout.item_profile, parent, false);
            return new viewHolder(view);
        }

        @Override
        public void onBindViewHolder(viewHolder holder, int position) {

            holder.img.setImageResource(img_profile.getResourceId(position, 0));
            holder.text.setText(txt_profile.get(position));
            holder.des.setText(txt_des.get(position));

        }

        @Override
        public int getItemCount() {
            return txt_profile.size();
        }

    }

}
