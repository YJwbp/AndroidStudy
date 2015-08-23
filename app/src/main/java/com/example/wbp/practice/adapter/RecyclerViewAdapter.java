package com.example.wbp.practice.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.wbp.practice.R;
import com.example.wbp.practice.bean.*;
import com.example.wbp.practice.bean.Character;

import org.androidannotations.annotations.EBean;

import java.util.List;

/**
 * Created by new on 15/8/4.
 */
public class RecyclerViewAdapter
		extends
			RecyclerView.Adapter<RecyclerViewAdapter.ViewHolder> {
	private final LayoutInflater mLayoutInflater;
	private final Context mContext;
	private List<Character> characters;

	public RecyclerViewAdapter(Context context, List<Character> characters) {
		this.mContext = context;
		mLayoutInflater = LayoutInflater.from(context);
		this.characters = characters;
	}

	@Override
	public int getItemCount() {
		return characters == null ? 0 : characters.size();
	}

	@Override
	public void onBindViewHolder(ViewHolder holder, int position) {
		holder.textView.setText(characters.get(position).getName());
		holder.imageView.setImageResource(characters.get(position).getImgId());
	}

	@Override
	public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
		return new ViewHolder(mLayoutInflater.inflate(R.layout.card_view,
				parent, false));
	}

	public static class ViewHolder extends RecyclerView.ViewHolder {

		TextView textView;
		ImageView imageView;

		ViewHolder(View v) {
			super(v);
			textView = (TextView) v.findViewById(R.id.text);
			imageView = (ImageView) v.findViewById(R.id.image);

			v.setOnClickListener(new View.OnClickListener() {
				@Override
				public void onClick(View view) {
					Log.d("NormalTextViewHolder", "onClick-->postion:"
							+ getPosition());
				}
			});
		}
	}
}
