package com.example.wbp.practice;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import org.androidannotations.annotations.EBean;
import org.androidannotations.annotations.ViewById;

/**
 * Created by new on 15/8/4.
 */
@EBean
public class NormalRecyclerViewAdapter extends RecyclerView.Adapter<NormalRecyclerViewAdapter.NormalTextViewHolder>{
	private final LayoutInflater mLayoutInflater;
	private final Context mContext;
	private String[] mTitles;

	public NormalRecyclerViewAdapter(Context context) {
		mTitles = context.getResources().getStringArray(R.array.titles);
		mContext = context;
		mLayoutInflater = LayoutInflater.from(context);
	}

	@Override
	public int getItemCount() {
		return mTitles == null ? 0 : mTitles.length;
	}

	@Override
	public void onBindViewHolder(NormalTextViewHolder holder, int position) {
		holder.textView.setText(mTitles[position]);
	}

	@Override
	public NormalTextViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
		return new NormalTextViewHolder(mLayoutInflater.inflate(R.layout.item_text, parent,false));
	}

	public static class NormalTextViewHolder extends RecyclerView.ViewHolder{

		TextView textView;

		NormalTextViewHolder(View v)
		{
			super(v);
			textView = (TextView)v.findViewById(R.id.text);

			v.setOnClickListener(new View.OnClickListener() {
				@Override
				public void onClick(View view) {
					Log.d("NormalTextViewHolder","onClick-->postion:"+getPosition());
				}
			});
		}
	}
}
