package cn.ifavor.flowlayout;

import java.util.Arrays;
import java.util.Random;

import android.app.Activity;
import android.graphics.Color;
import android.graphics.drawable.GradientDrawable;
import android.graphics.drawable.StateListDrawable;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;
import cn.ifavor.flowlayout.utils.ToastUtils;
import cn.ifavor.flowlayout.utils.UiUtils;
import cn.ifavor.flowlayout.view.FlowLayout;

public class MainActivity extends Activity {
	private String[] mList;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		mList = new String[] { "QQ", "视频", "放开那三国", "电子书", "酒店", "单机", "小说",
				"斗地主", "优酷", "网游", "WIFI万能钥匙", "播放器", "捕鱼达人2", "机票", "游戏",
				"熊出没之熊大快跑", "美图秀秀", "浏览器", "单机游戏", "我的世界", "电影电视", "QQ空间",
				"旅游", "免费游戏", "2048", "刀塔传奇", "壁纸", "节奏大师", "锁屏", "装机必备",
				"天天动听", "备份", "网盘", "海淘网", "大众点评", "爱奇艺视频", "腾讯手机管家", "百度地图",
				"猎豹清理大师", "谷歌地图", "hao123上网导航", "京东", "youni有你", "万年历-农历黄历",
				"支付宝钱包" };

		Arrays.sort(mList);
		
		// 使得 LinearLayout 界面可以滚动
		FlowLayout flowLayout = (FlowLayout) findViewById(R.id.fl_layout);
		// 设置 FlowLayout 的 padding 值
		flowLayout.setPadding(20, 20, 20, 20);
		flowLayout.setOrientation(LinearLayout.VERTICAL);

		// 在 LinearLayout 添加 TextView
		for (int i = 0; i < mList.length; i++) {
			final String content = mList[i];

			TextView textview = new TextView(UiUtils.getContext());
			textview.setText(mList[i]);

			textview.setLayoutParams(new LinearLayout.LayoutParams(
					LinearLayout.LayoutParams.WRAP_CONTENT,
					LinearLayout.LayoutParams.WRAP_CONTENT));

			// 设置背景
			textview.setBackgroundColor(Color.parseColor("#ffff0000"));

			// 设置字体颜色
			textview.setTextColor(Color.WHITE);

			// 设置字体
			// textview.setTextSize(UiUtils.dip2px(10));

			// 设置内边距
			int padding = UiUtils.dip2px(5);
			textview.setPadding(padding, padding, padding, padding);

			// 按下时的图片
			int backColor = 0xffcecece;
			GradientDrawable pressDrawable = new GradientDrawable();
			pressDrawable.setCornerRadius(UiUtils.dip2px(8));
			pressDrawable.setColor(backColor);

			// 抬起时的图片
			Random random = new Random();
			int alpha = 254;
			int red = random.nextInt(200) + 22;
			int green = random.nextInt(200) + 22;
			int blue = random.nextInt(200) + 22;
			int rgb = Color.argb(alpha, red, green, blue);
			GradientDrawable normalDrawable = new GradientDrawable();
			normalDrawable.setCornerRadius(UiUtils.dip2px(8));
			normalDrawable.setColor(rgb);

			// 设置 selector
			StateListDrawable stateListDrawable = new StateListDrawable();
			stateListDrawable.addState(
					new int[] { android.R.attr.state_pressed }, pressDrawable);
			stateListDrawable.addState(new int[] {}, normalDrawable);

			textview.setBackgroundDrawable(stateListDrawable);
			textview.setGravity(Gravity.CENTER);
			textview.setOnClickListener(new OnClickListener() {
				
				@Override
				public void onClick(View v) {
					ToastUtils.show(content);
				}
			});
			flowLayout.addView(textview);
		}
	}
}
