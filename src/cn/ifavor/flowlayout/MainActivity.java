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

		mList = new String[] { "QQ", "��Ƶ", "�ſ�������", "������", "�Ƶ�", "����", "С˵",
				"������", "�ſ�", "����", "WIFI����Կ��", "������", "�������2", "��Ʊ", "��Ϸ",
				"�ܳ�û֮�ܴ����", "��ͼ����", "�����", "������Ϸ", "�ҵ�����", "��Ӱ����", "QQ�ռ�",
				"����", "�����Ϸ", "2048", "��������", "��ֽ", "�����ʦ", "����", "װ���ر�",
				"���춯��", "����", "����", "������", "���ڵ���", "��������Ƶ", "��Ѷ�ֻ��ܼ�", "�ٶȵ�ͼ",
				"�Ա������ʦ", "�ȸ��ͼ", "hao123��������", "����", "youni����", "������-ũ������",
				"֧����Ǯ��" };

		Arrays.sort(mList);
		
		// ʹ�� LinearLayout ������Թ���
		FlowLayout flowLayout = (FlowLayout) findViewById(R.id.fl_layout);
		// ���� FlowLayout �� padding ֵ
		flowLayout.setPadding(20, 20, 20, 20);
		flowLayout.setOrientation(LinearLayout.VERTICAL);

		// �� LinearLayout ��� TextView
		for (int i = 0; i < mList.length; i++) {
			final String content = mList[i];

			TextView textview = new TextView(UiUtils.getContext());
			textview.setText(mList[i]);

			textview.setLayoutParams(new LinearLayout.LayoutParams(
					LinearLayout.LayoutParams.WRAP_CONTENT,
					LinearLayout.LayoutParams.WRAP_CONTENT));

			// ���ñ���
			textview.setBackgroundColor(Color.parseColor("#ffff0000"));

			// ����������ɫ
			textview.setTextColor(Color.WHITE);

			// ��������
			// textview.setTextSize(UiUtils.dip2px(10));

			// �����ڱ߾�
			int padding = UiUtils.dip2px(5);
			textview.setPadding(padding, padding, padding, padding);

			// ����ʱ��ͼƬ
			int backColor = 0xffcecece;
			GradientDrawable pressDrawable = new GradientDrawable();
			pressDrawable.setCornerRadius(UiUtils.dip2px(8));
			pressDrawable.setColor(backColor);

			// ̧��ʱ��ͼƬ
			Random random = new Random();
			int alpha = 254;
			int red = random.nextInt(200) + 22;
			int green = random.nextInt(200) + 22;
			int blue = random.nextInt(200) + 22;
			int rgb = Color.argb(alpha, red, green, blue);
			GradientDrawable normalDrawable = new GradientDrawable();
			normalDrawable.setCornerRadius(UiUtils.dip2px(8));
			normalDrawable.setColor(rgb);

			// ���� selector
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
