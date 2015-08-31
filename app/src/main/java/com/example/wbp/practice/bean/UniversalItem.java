package com.example.wbp.practice.bean;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by new on 15/8/28.
 */
public class UniversalItem {
	private String title;
	private String desc;
	private String time;
	private String phone;

	private final int SIZE = 5;
	private String[] titles = {"美女一只", "太漂亮了", "校园卡", "求助！急急急！！在线等", "求推荐好看的动漫"};
	private String[] descs = {"今天早上捡到美女萌妹子一只，在新食堂888楼", "昨天去了九寨沟，简直美呆了！！%>_<%",
			"捡到校园卡一张，请失主认领！", "请问1+1到底等于几啊", "比如说火影、海贼之类的热血动漫"};
	private String[] times = {"2015/09/02", "2015/09/12", "2015/11/02",
			"2015/10/02", "2015/09/23"};;
	private String[] phones = {"18830242456", "18830234756", "18830242873",
			"188302424XX", "188****2456"};

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getDesc() {
		return desc;
	}

	public void setDesc(String desc) {
		this.desc = desc;
	}

	public String getTime() {
		return time;
	}

	public void setTime(String time) {
		this.time = time;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public List<UniversalItem> getUniversalItems(int number) {

		List<UniversalItem> listDatas = new ArrayList<UniversalItem>();
		int tempNumber = 0;
		for (int i = 0; i < number; i++) {
			tempNumber = i % SIZE;
			UniversalItem item = new UniversalItem();
			item.setTitle(titles[tempNumber]);
			item.setDesc(descs[tempNumber]);
			item.setTime(times[tempNumber]);
			item.setPhone(phones[tempNumber]);
			listDatas.add(item);
		}
		return listDatas;
	}
}
