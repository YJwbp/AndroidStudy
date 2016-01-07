package com.example.wbp.practice.activity;

import android.app.Activity;
import android.graphics.drawable.Drawable;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.style.ImageSpan;
import android.widget.EditText;

import com.example.wbp.practice.R;
import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.ViewById;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by new on 15/8/31.
 */
@EActivity(R.layout.activity_text_style)
public class TextStyleActivity extends BaseActivity {
	@ViewById(R.id.et_emoji)
	EditText etEmoji;

	SpannableString str = new SpannableString("哈哈 。，：[sl]");

	@AfterViews
	void afterViews() {
		Pattern pattern = Pattern.compile("\\[[a-zA-Z_0-9_.-]+\\]");
		Matcher matcher = pattern.matcher(str);
		while (matcher.find()) {
			Drawable drawable = getResources().getDrawable(R.drawable.img_text);
			if (drawable == null) {
				continue;
			}

			drawable.setBounds(0, 0, 50, 50);
			str.setSpan(new ImageSpan(drawable), matcher.start(),
					matcher.end(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);

			etEmoji.setText(str);
		}
	}
}
