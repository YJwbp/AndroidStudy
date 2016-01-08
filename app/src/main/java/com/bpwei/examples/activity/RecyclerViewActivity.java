package com.bpwei.examples.activity;

import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.bpwei.examples.R;
import com.bpwei.examples.adapter.RecyclerViewAdapter;
import com.bpwei.examples.bean.Character;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.ViewById;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by new on 15/8/24.
 */
@EActivity(R.layout.recycler_view_activity)
public class RecyclerViewActivity extends BaseActivity {
	@ViewById(R.id.list)
	RecyclerView recyclerView;

	RecyclerView.Adapter adapter;
	private List<Character> characters = new ArrayList<Character>();
	private String[] names = {"艾露莎&杰拉尔", "艾露莎", "四人组", "⊙﹏⊙b汗", "梅比斯", "纳兹",
			"艾露莎&纳兹"};
	private int[] pics = {R.drawable.couple, R.drawable.drown_deep,
			R.drawable.four, R.drawable.han, R.drawable.master,
			R.drawable.nazi, R.drawable.nazi_and_ailusa};

	@AfterViews
	void afterViews() {
		recyclerView.setHasFixedSize(true);
		recyclerView.setItemAnimator(new DefaultItemAnimator());
		recyclerView.setLayoutManager(new LinearLayoutManager(this));
		for (int i = 0; i < names.length; i++) {
			characters.add(new Character(names[i], pics[i]));
		}
		adapter = new RecyclerViewAdapter(this, characters);
		recyclerView.setAdapter(adapter);
	}

}
