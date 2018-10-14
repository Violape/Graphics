package src;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

public class DrawFill extends JPanel {
	JLabel f_lb_px, f_lb_py;
	JTextField f_tf_px, f_tf_py;
	JRadioButton f_rd_fl, f_rd_sc;
	ButtonGroup f_bg_mode;
	JButton f_bt_st, f_bt_ln, f_bt_cl, f_bt_re, f_bt_draw;
	int x0=0, x1=0, x2=0, y0=0, y1=0, y2=0;
	DrawPanel f_pn_main;
	public DrawFill() {
		String lookAndFeel ="com.sun.java.swing.plaf.windows.WindowsLookAndFeel";
		try { UIManager.setLookAndFeel(lookAndFeel); }
		catch(Exception e) {}
		setLayout(null);
		f_lb_px = new JLabel("横坐标：");
		f_lb_py = new JLabel("纵坐标：");
		f_tf_px = new JTextField();
		f_tf_py = new JTextField();
		f_rd_fl = new JRadioButton("递归");
		f_rd_sc = new JRadioButton("扫描线");
		f_bt_st = new JButton("开始绘制");
		f_bt_ln = new JButton("添加节点");
		f_bt_cl = new JButton("多边形闭合");
		f_bt_re = new JButton("重置");
		f_bt_draw = new JButton("填充");
		f_pn_main = new DrawPanel();
		f_bg_mode = new ButtonGroup();
		f_bg_mode.add(f_rd_fl);
		f_bg_mode.add(f_rd_sc);
		add(f_lb_px);
		add(f_lb_py);
		add(f_tf_px);
		add(f_tf_py);
		add(f_bt_st);
		add(f_bt_ln);
		add(f_bt_cl);
		add(f_bt_re);
		add(f_bt_draw);
		add(f_pn_main);
		add(f_rd_fl);
		add(f_rd_sc);
		f_tf_px.setFont(new Font("Arial", Font.PLAIN, 11));
		f_tf_py.setFont(new Font("Arial", Font.PLAIN, 11));
		f_lb_px.setBounds(50,10,50,20);
		f_lb_py.setBounds(50,35,50,20);
		f_tf_px.setBounds(100,10,35,20);
		f_tf_py.setBounds(100,35,35,20);
		f_bt_st.setBounds(180,8,100,25);
		f_bt_ln.setBounds(180,8,100,25);
		f_bt_cl.setBounds(180,33,100,25);
		f_bt_re.setBounds(180,33,100,25);
		f_bt_draw.setBounds(460,15,80,35);
		f_rd_fl.setBounds(340,10,80,20);
		f_rd_sc.setBounds(340,35,80,20);
		f_pn_main.setBounds(40,60,540,540);
		f_bt_ln.setVisible(false);
		f_bt_re.setVisible(false);
		f_rd_fl.setSelected(false);
		f_rd_sc.setEnabled(false);
		f_bt_st.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				f_bt_ln.setVisible(true);
				f_bt_st.setVisible(false);
				x1 = Integer.valueOf(f_tf_px.getText());
				y1 = Integer.valueOf(f_tf_py.getText());
				drawline(x1,y1,x1,y1);
				x0 = x1;
				y0 = y1;
			}
		});
		f_bt_ln.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				x2 = Integer.valueOf(f_tf_px.getText());
				y2 = Integer.valueOf(f_tf_py.getText());
				drawline(x1,y1,x2,y2);
				x1 = x2;
				y1 = y2;
			}
		});
		f_bt_cl.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				f_bt_ln.setEnabled(false);
				f_bt_re.setVisible(true);
				f_bt_cl.setVisible(false);
				drawline(x1,y1,x0,y0);
			}
		});
		f_bt_re.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				f_bt_ln.setEnabled(true);
				f_bt_cl.setVisible(true);
				f_bt_re.setVisible(false);
				f_bt_ln.setVisible(false);
				f_bt_st.setVisible(true);
				clear();
				x1 = 0;
				x2 = 0;
				y1 = 0;
				y2 = 0;
				f_tf_px.setText("");
				f_tf_py.setText("");
			}
		});
		f_bt_draw.addActionListener(new Fill());
	}
	public void drawline(int x1, int y1, int x2, int y2) {
		int xmin = Integer.valueOf(f_pn_main.l_cb_xmin.getText());
		int ymin = Integer.valueOf(f_pn_main.l_cb_ymin.getText());
		x1 -= xmin;
		x2 -= xmin;
		y1 -= ymin;
		y2 -= ymin;
		if(x1>24 || x1<0 || x2>24 || x2<0 || y1>24 || y2<0 || y1>24 || y2<0) {
			Msgbox msg = new Msgbox("绘图超出边界，请重新绘制！");
			return;
		}
		if(x1 > x2) {
			int temp = x1;
			x1 = x2;
			x2 = temp;
			temp = y1;
			y1 = y2;
			y2 = temp;
		}
		if(x1 == x2) {
			if(y1 < y2) {
				for(int j = y1; j <= y2; j++)
					f_pn_main.paint(x1, 24-j, null);
				return;
			}
			else {
				for(int j = y2; j <= y1; j++)
					f_pn_main.paint(x1, 24-j, null);
				return;
			}
		}
		else {
			int dx = x2 - x1, dy = y2 - y1;
			float k = dy / (float)dx;
			if(k > 1) {
				float x0 = x1;
				for(int j = y1; j <= y2; j++) {
					f_pn_main.paint((int)(x0 + 0.5), 24 - j, null);
					x0 += 1/k;
				}
			}
			else if(k > -1) {
				float y0 = y1;
				for(int i = x1; i <= x2; i++) {
					f_pn_main.paint(i, (int)(24.5 - y0), null);
					y0 += k;
				}
			}
			else {
				float x0 = x1;
				for(int j = y1; j >= y2; j--) {
					f_pn_main.paint((int)(x0 + 0.5), 24 - j, null);
					x0 -= 1/k;
				}
			}
		}
	}
	public void clear() {
		for(int i=0; i<=24; i++)
			for(int j=0; j<=24; j++)
				f_pn_main.d_lb_point[i][j].setIcon(new ImageIcon("D:/Programming Workspace/Graphics/img/pointw.png"));
	}
	private class Fill implements ActionListener{
		public void actionPerformed(ActionEvent e) {
			try {
				Thread.sleep(100);
			}
			catch (InterruptedException e1) {
				e1.printStackTrace();
			}
		}
	}
}