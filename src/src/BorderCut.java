package src;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.*;
import javax.swing.*;

public class BorderCut extends JPanel{
	JLabel b_lb_lx1, b_lb_ly1, b_lb_lx2, b_lb_ly2, b_lb_cx1, b_lb_cx2, b_lb_cy1, b_lb_cy2;
	JTextField b_tf_lx1, b_tf_ly1, b_tf_lx2, b_tf_ly2, b_tf_cx1, b_tf_cx2, b_tf_cy1, b_tf_cy2;
	JButton b_bt_draw;
	DrawPanel b_pn_main;
	public BorderCut() {
		String lookAndFeel ="com.sun.java.swing.plaf.windows.WindowsLookAndFeel";
		try { UIManager.setLookAndFeel(lookAndFeel); }
		catch(Exception e) {}
		setLayout(null);
		b_lb_lx1 = new JLabel("起点x：");
		b_lb_ly1 = new JLabel("起点y：");
		b_lb_lx2 = new JLabel("终点x：");
		b_lb_ly2 = new JLabel("终点y：");
		b_lb_cx1 = new JLabel("左边界：");
		b_lb_cx2 = new JLabel("右边界：");
		b_lb_cy1 = new JLabel("上边界：");
		b_lb_cy2 = new JLabel("下边界：");
		b_tf_lx1 = new JTextField("");
		b_tf_ly1 = new JTextField("");
		b_tf_lx2 = new JTextField("");
		b_tf_ly2 = new JTextField("");
		b_tf_cx1 = new JTextField("0");
		b_tf_cx2 = new JTextField("24");
		b_tf_cy1 = new JTextField("24");
		b_tf_cy2 = new JTextField("0");
		b_bt_draw = new JButton("绘制");
		b_pn_main = new DrawPanel();
		add(b_lb_lx1);
		add(b_lb_ly1);
		add(b_lb_lx2);
		add(b_lb_ly2);
		add(b_lb_cx1);
		add(b_lb_cx2);
		add(b_lb_cy1);
		add(b_lb_cy2);
		add(b_tf_lx1);
		add(b_tf_ly1);
		add(b_tf_lx2);
		add(b_tf_ly2);
		add(b_tf_cx1);
		add(b_tf_cx2);
		add(b_tf_cy1);
		add(b_tf_cy2);
		add(b_bt_draw);
		add(b_pn_main);
		b_tf_lx1.setFont(new Font("Arial", Font.PLAIN, 11));
		b_tf_ly1.setFont(new Font("Arial", Font.PLAIN, 11));
		b_tf_lx2.setFont(new Font("Arial", Font.PLAIN, 11));
		b_tf_ly2.setFont(new Font("Arial", Font.PLAIN, 11));
		b_tf_cx1.setFont(new Font("Arial", Font.PLAIN, 11));
		b_tf_cx2.setFont(new Font("Arial", Font.PLAIN, 11));
		b_tf_cy1.setFont(new Font("Arial", Font.PLAIN, 11));
		b_tf_cy2.setFont(new Font("Arial", Font.PLAIN, 11));
		b_lb_lx1.setBounds(50,10,50,20);
		b_lb_ly1.setBounds(50,35,50,20);
		b_lb_lx2.setBounds(150,10,50,20);
		b_lb_ly2.setBounds(150,35,50,20);
		b_lb_cx1.setBounds(250,10,50,20);
		b_lb_cx2.setBounds(250,35,50,20);
		b_lb_cy1.setBounds(350,10,50,20);
		b_lb_cy2.setBounds(350,35,50,20);
		b_tf_lx1.setBounds(100,10,35,20);
		b_tf_ly1.setBounds(100,35,35,20);
		b_tf_lx2.setBounds(200,10,35,20);
		b_tf_ly2.setBounds(200,35,35,20);
		b_tf_cx1.setBounds(300,10,35,20);
		b_tf_cx2.setBounds(300,35,35,20);
		b_tf_cy1.setBounds(400,10,35,20);
		b_tf_cy2.setBounds(400,35,35,20);
		b_bt_draw.setBounds(460,15,80,35);
		b_pn_main.setBounds(40,60,540,540);
		b_bt_draw.addActionListener(new Draw());
	}
	private class Draw implements ActionListener{
		int x1, x2, y1, y2, xl, xr, yt, yb;
		public void actionPerformed(ActionEvent e) {
			new Thread(new Runnable() {
				@Override
				public void run() {
					for(int i=0; i<=24; i++)
						for(int j=0; j<=24; j++)
							b_pn_main.d_lb_point[i][j].setIcon(new ImageIcon("D:/Programming Workspace/Graphics/img/pointw.png"));
					x1 = Integer.valueOf(b_tf_lx1.getText());
					x2 = Integer.valueOf(b_tf_lx2.getText());
					y1 = Integer.valueOf(b_tf_ly1.getText());
					y2 = Integer.valueOf(b_tf_ly2.getText());
					xl = Integer.valueOf(b_tf_cx1.getText());
					xr = Integer.valueOf(b_tf_cx2.getText());
					yt = Integer.valueOf(b_tf_cy1.getText());
					yb = Integer.valueOf(b_tf_cy2.getText());
					Msgbox msg;
					if(xl < 0 || xr > 24 || xl > xr) {
						msg = new Msgbox("左右边界不合法！");
						return;
					}
					else if(yb < 0 || yt > 24 || yt < yb) {
						msg = new Msgbox("上下边界不合法！");
						return;
					}
					for(int i=xl; i<=xr; i++)
						for(int j=yb; j<=yt; j++)
							b_pn_main.d_lb_point[i][24-j].setIcon(new ImageIcon("D:/Programming Workspace/Graphics/img/pointy.png"));
					int code1 = encode(x1, y1);
					int code2 = encode(x2, y2);
					int code = 0;
					float x = 0, y = 0;
					while(code1 != 0 || code2 != 0) {
						if((code1 & code2) != 0)
							return;
						if(code1 != 0)
							code = code1;
						else
							code = code2;
						if((1 & code) != 0) {
							x = xl;
							y = y1 + (y2 - y1) * (xl - x1) / (float)(x2 - x1);
						}
						else if((2 & code) != 0) {
							x = xr;
							y = y1 + (y2 - y1) * (xr - x1) / (float)(x2 - x1);
						}
						else if((4 & code) != 0) {
							y = yb;
							x = x1 + (x2 - x1) * (yb - y1) / (float)(y2 - y1);
						}
						else if((8 & code) != 0) {
							y = yt;
							x = x1 + (x2 - x1) * (yt - y1) / (float)(y2 - y1);
						}
						if(code == code1) {
							code1 = encode(x, y);
							x1 = (int)(x + 0.5);
							y1 = (int)(y + 0.5);
							b_tf_lx1.setText(String.format("%.2f", x));
							b_tf_ly1.setText(String.format("%.2f", y));
							if(x1 == xl || x1 == xr) {
								b_tf_lx1.setBackground(Color.RED);
								b_tf_lx1.setForeground(Color.WHITE);
							}
							else {
								b_tf_lx1.setBackground(Color.YELLOW);
							}
							if(y1 == yt || y1 == yb) {
								b_tf_ly1.setBackground(Color.RED);
								b_tf_ly1.setForeground(Color.WHITE);
							}
							else {
								b_tf_ly1.setBackground(Color.YELLOW);
							}
						}
						else {
							code2 = encode(x, y);
							x2 = (int)(x + 0.5);
							y2 = (int)(y + 0.5);
							b_tf_lx2.setText(String.format("%.2f", x));
							b_tf_ly2.setText(String.format("%.2f", y));
							if(x2 == xl || x2 == xr) {
								b_tf_lx2.setBackground(Color.RED);
								b_tf_lx2.setForeground(Color.WHITE);
							}
							else {
								b_tf_lx2.setBackground(Color.YELLOW);
							}
							if(y2 == yt || y2 == yb) {
								b_tf_ly2.setBackground(Color.RED);
								b_tf_ly2.setForeground(Color.WHITE);
							}
							else {
								b_tf_ly2.setBackground(Color.YELLOW);
							}
						}
						try {
							Thread.sleep(1000);
						}
						catch (InterruptedException e1) {
							e1.printStackTrace();
						}
						b_tf_lx1.setBackground(Color.WHITE);
						b_tf_lx2.setBackground(Color.WHITE);
						b_tf_ly1.setBackground(Color.WHITE);
						b_tf_ly2.setBackground(Color.WHITE);
						b_tf_lx1.setForeground(Color.BLACK);
						b_tf_lx2.setForeground(Color.BLACK);
						b_tf_ly1.setForeground(Color.BLACK);
						b_tf_ly2.setForeground(Color.BLACK);
					}
					drawline(x1, y1, x2, y2);
					try {
						Thread.sleep(1000);
					}
					catch (InterruptedException e1) {
						e1.printStackTrace();
					}
					b_tf_lx1.setText(String.format("%d", x1));
					b_tf_ly1.setText(String.format("%d", y1));
					b_tf_lx2.setText(String.format("%d", x2));
					b_tf_ly2.setText(String.format("%d", y2));
				}
			}).start();
		}
		public void drawline(int x1, int y1, int x2, int y2) {
			int xmin = Integer.valueOf(b_pn_main.l_cb_xmin.getText());
			int ymin = Integer.valueOf(b_pn_main.l_cb_ymin.getText());
			x1 -= xmin;
			x2 -= xmin;
			y1 -= ymin;
			y2 -= ymin;
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
					for(int j = y1; j <= y2; j++) {
						b_pn_main.paint(x1, 24-j, null);
					}
					return;
				}
				else {
					for(int j = y2; j <= y1; j++) {
						b_pn_main.paint(x1, 24-j, null);
					}
					return;
				}
			}
			else {
				int dx = x2 - x1, dy = y2 - y1;
				float k = dy / (float)dx;
				if(k > 1) {
					float x0 = x1;
					for(int j = y1; j <= y2; j++) {
						b_pn_main.paint((int)(x0 + 0.5), 24 - j, null);
						x0 += 1/k;
					}
				}
				else if(k > -1) {
					float y0 = y1;
					for(int i = x1; i <= x2; i++) {
						b_pn_main.paint(i, (int)(24.5 - y0), null);
						y0 += k;
					}
				}
				else {
					float x0 = x1;
					for(int j = y1; j >= y2; j--) {
						b_pn_main.paint((int)(x0 + 0.5), 24 - j, null);
						x0 -= 1/k;
					}
				}
			}
		}
		public int encode(float x, float y) {
			int c = 0;
			if(x < xl) c |= 1;
			if(x > xr) c |= 2;
			if(y < yb) c |= 4;
			if(y > yt) c |= 8;
			return c;
		}
	}
}
