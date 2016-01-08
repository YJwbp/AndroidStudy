package com.bpwei.examples.activity;

import com.bpwei.examples.R;
import com.bpwei.examples.views.CircularImageView;

import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.ViewById;

/**
 * Created by new on 15/8/12.
 */
@EActivity(R.layout.activity_draw)
public class CircularImageViewActivity extends BaseActivity {
	@ViewById(R.id.circular_image_view)
	CircularImageView circularImageView;
}
