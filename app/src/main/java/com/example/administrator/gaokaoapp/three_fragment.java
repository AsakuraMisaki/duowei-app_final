package com.example.administrator.gaokaoapp;

import android.app.Fragment;
import android.content.Intent;
import android.support.annotation.Nullable;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.Toast;

public class three_fragment extends Fragment implements View.OnClickListener{

    private ImageButton[] allImageButton = new ImageButton[5];
    private Button admitLineEntrance;
    Intent startTest = null;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.three_fragment,container,false);
        return view;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated( savedInstanceState );
        initView();
    }

    private void initView() {
        allImageButton[0] = getActivity().findViewById(R.id.zhili_bt);
        allImageButton[1] = getActivity().findViewById(R.id.xingqu_bt);
        allImageButton[2] = getActivity().findViewById(R.id.qizhi_bt);
        allImageButton[3] = getActivity().findViewById(R.id.renge);
        allImageButton[4] = getActivity().findViewById(R.id.shehui_bt);
        admitLineEntrance = getActivity().findViewById( R.id.submit_per_entrance );
        admitLineEntrance.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent admitLine = new Intent( getActivity(),AdmitLine.class );
                startActivity( admitLine );
            }
        } );
        for(int i=0; i<allImageButton.length; i++){
            allImageButton[i].setOnClickListener(this);
        }
    }

    @Override
    public void onClick(View v){
        startTest = new Intent(getActivity(), AllTestStart.class);
        switch (v.getId()){
            case R.id.zhili_bt:{
                startTest.putExtra("question-class", "bpm");
                break;
            }
            case R.id.qizhi_bt: {
                startTest.putExtra("question-class", "temper-type");
                break;
            }
            case R.id.shehui_bt: {
                startTest.putExtra("question-class", "society-test");
                break;
            }
            case R.id.renge: {
                startTest.putExtra("question-class", "16pf");
                break;
            }
            case R.id.xingqu_bt: {
                startTest.putExtra("question-class", "sds");
                break;
            }
            default: break;
        }
        startActivity(startTest);
    }
}
