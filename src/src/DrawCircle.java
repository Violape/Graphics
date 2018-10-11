package src;

import java.awt.Font;
import java.awt.event.*;
import javax.swing.*;

class DrawCircle extends JPanel{
	JLabel c_lb_cx, c_lb_cy, c_lb_rx, c_lb_ry;
	JTextField c_tf_cx, c_tf_cy, c_tf_rx, c_tf_ry;
	JRadioButton c_rd_tc, c_rd_to, c_rd_mp, c_rd_br;
	ButtonGroup c_bg_shap, c_bg_mode;
	JButton c_bt_draw;
	DrawPanel c_pn_main;
	public DrawCircle() {
		String lookAndFeel ="com.sun.java.swing.plaf.windows.WindowsLookAndFeel";
		try { UIManager.setLookAndFeel(lookAndFeel); }
		catch(Exception e) {}
		setLayout(null);
		c_lb_cx = new JLabel("圆心横坐标");
		c_lb_cy = new JLabel("圆心纵坐标");
		c_lb_rx = new JLabel("x轴方向半径");
		c_lb_ry = new JLabel("y轴方向半径");
		c_tf_cx = new JTextField();
		c_tf_cy = new JTextField();
		c_tf_rx = new JTextField();
		c_tf_ry = new JTextField();
		c_pn_main = new DrawPanel();
		c_bt_draw = new JButton("绘制");
		c_rd_tc = new JRadioButton("正圆");
		c_rd_to = new JRadioButton("椭圆");
		c_rd_mp = new JRadioButton("Midpoint");
		c_rd_br = new JRadioButton("Bresham");
		c_bg_shap = new ButtonGroup();
		c_bg_shap.add(c_rd_tc);
		c_bg_shap.add(c_rd_to);
		c_bg_mode = new ButtonGroup();
		c_bg_mode.add(c_rd_mp);
		c_bg_mode.add(c_rd_br);
		add(c_lb_cx);
		add(c_lb_cy);
		add(c_lb_rx);
		add(c_lb_ry);
		add(c_tf_cx);
		add(c_tf_cy);
		add(c_tf_rx);
		add(c_tf_ry);
		add(c_rd_tc);
		add(c_rd_to);
		add(c_rd_mp);
		add(c_rd_br);
		add(c_bt_draw);
		add(c_pn_main);
		c_rd_mp.setFont(new Font("Arial", Font.PLAIN, 12));
		c_rd_br.setFont(new Font("Arial", Font.PLAIN, 12));
		c_tf_cx.setFont(new Font("Arial", Font.PLAIN, 12));
		c_tf_cy.setFont(new Font("Arial", Font.PLAIN, 12));
		c_tf_rx.setFont(new Font("Arial", Font.PLAIN, 12));
		c_tf_ry.setFont(new Font("Arial", Font.PLAIN, 12));
		c_lb_cx.setBounds(50,10,100,20);
		c_lb_cy.setBounds(50,35,100,20);
		c_lb_rx.setBounds(190,10,100,20);
		c_lb_ry.setBounds(190,35,100,20);
		c_tf_cx.setBounds(120,10,50,20);
		c_tf_cy.setBounds(120,35,50,20);
		c_tf_rx.setBounds(260,10,50,20);
		c_tf_ry.setBounds(260,35,50,20);
		c_bt_draw.setBounds(460,15,80,35);
		c_rd_tc.setBounds(320,10,50,20);
		c_rd_to.setBounds(320,32,50,20);
		c_rd_mp.setBounds(370,10,80,20);
		c_rd_br.setBounds(370,32,80,20);
		c_pn_main.setBounds(40,60,540,540);
		c_rd_tc.setSelected(true);
		c_rd_mp.setSelected(true);
		c_tf_ry.setText("");
		c_tf_ry.setEnabled(false);
		c_tf_cx.setText("12");
		c_tf_cy.setText("12");
		c_tf_rx.setText("8");
		c_rd_tc.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				c_rd_mp.setEnabled(true);
				c_rd_br.setEnabled(true);
				c_tf_ry.setText("");
				c_tf_ry.setEnabled(false);
			}
		});
		c_rd_to.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				c_rd_mp.setSelected(true);
				c_rd_br.setSelected(false);
				c_rd_mp.setEnabled(false);
				c_rd_br.setEnabled(false);
				c_tf_ry.setEnabled(true);
			}
		});
		c_rd_mp.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				c_rd_to.setEnabled(true);
			}
		});
		c_rd_br.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				c_rd_tc.setSelected(true);
				c_rd_to.setEnabled(false);
			}
		});
		c_bt_draw.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Msgbox msg;
				if(c_tf_cx.getText().equals("")) {
					msg = new Msgbox("请输入圆心坐标与半径！");
				}
				else if(c_tf_cy.getText().equals("")) {
					msg = new Msgbox("请输入圆心坐标与半径！");
				}
				else if(c_tf_rx.getText().equals("")) {
					msg = new Msgbox("请输入圆心坐标与半径！");
				}
				else {
					for(int i=0;i<25;i++) {
						for(int j=0;j<25;j++) {
							c_pn_main.d_lb_point[i][j].setIcon(new ImageIcon("D:/Programming Workspace/Graphics/img/pointw.png"));
						}
					}
					int xmin = Integer.valueOf(c_pn_main.l_cb_xmin.getText());
					int ymin = Integer.valueOf(c_pn_main.l_cb_ymin.getText());
					int cx = Integer.valueOf(c_tf_cx.getText())-xmin;
					int cy = Integer.valueOf(c_tf_cy.getText())-ymin;
					if(c_rd_tc.isSelected()) {
						int r = Integer.valueOf(c_tf_rx.getText());
						if(r <= 0) {
							msg = new Msgbox("半径小于0，输入数据非法！");
							return;
						}
						if(cx+r>24||cx-r<0||cy+r>24||cy-r<0) {
							msg = new Msgbox("绘图超出边界，请重新绘制！");
							return;
						}
						if(c_rd_mp.isSelected()) {
							int x = 0, y = r, d = 1 - r;
							String text = "Order "+String.valueOf(x)+", d = "+String.valueOf(d);
							c_pn_main.paint(cx+x, 24-cy-y, text);
							c_pn_main.paint(cx+x, 24-cy+y, text);
							c_pn_main.paint(cx+y, 24-cy-x, text);
							c_pn_main.paint(cx-y, 24-cy-x, text);
							while(x <= y) {
								if(d < 0) 
									d += 2 * x + 3;
								else {
									d += 2 * (x - y) + 5;
									y--;
								}
								x++;
								text = "Order "+String.valueOf(x)+", d = "+String.valueOf(d);
								c_pn_main.paint(cx+x, 24-cy-y, text);
								c_pn_main.paint(cx+x, 24-cy+y, text);
								c_pn_main.paint(cx-x, 24-cy-y, text);
								c_pn_main.paint(cx-x, 24-cy+y, text);
								c_pn_main.paint(cx+y, 24-cy-x, text);
								c_pn_main.paint(cx+y, 24-cy+x, text);
								c_pn_main.paint(cx-y, 24-cy-x, text);
								c_pn_main.paint(cx-y, 24-cy+x, text);
							}
						}
						else if(c_rd_br.isSelected()) {
							int x = 0, y = r, d = 2 - 2 * r, limit = 0, cnt = 0;
							int d1 = 0, d2 = 0, dr;
							while(y >= limit) {
								String text = "Order "+String.valueOf(cnt)+", d = "+String.valueOf(d)+", d1 = "+String.valueOf(d1)+", d2 = "+String.valueOf(d2);
								c_pn_main.paint(cx+x, 24-cy-y, text);
								c_pn_main.paint(cx+x, 24-cy+y, text);
								c_pn_main.paint(cx-x, 24-cy-y, text);
								c_pn_main.paint(cx-x, 24-cy+y, text);
								if(d < 0) {
									d1 = 2 * (d + y) - 1;
									if(d1 <= 0) dr = 1;
									else dr = 2;
								}
								else if(d > 0) {
									d2 = 2 * (d - x) - 1;
									if(d2 < 0) dr = 2;
									else dr = 3;
								}
								else
									dr = 2;
								switch(dr) {
								case 1: x++;
										d += 2 * x + 1;
										break;
								case 2: x++;
										y--;
										d += 2 * (x - y + 1);
										break;
								case 3: y--;
										d += 1 - 2 * y;
										break;
								}
								cnt++;
							}
						}
						else {
							msg = new Msgbox("请选择绘制方法！");
						}
					}
					else if(c_rd_to.isSelected()) {
						int rx = Integer.valueOf(c_tf_rx.getText());
						int ry = Integer.valueOf(c_tf_ry.getText());
						if(rx <= 0) {
							msg = new Msgbox("半径小于0，输入数据非法！");
							return;
						}
						if(ry <= 0) {
							msg = new Msgbox("半径小于0，输入数据非法！");
							return;
						}
						if(cx+rx>24||cx-rx<0||cy+ry>24||cy-ry<0) {
							msg = new Msgbox("绘图超出边界，请重新绘制！");
							return;
						}
						boolean flag = true;
						if(rx < ry) {
							int temp = rx;
							rx = ry;
							ry = temp;
							flag = false;
						}
						int x = (int)(rx+0.5), y = 0, tx = x, cnt = 0;
						int d1 = 2*ry*ry*x*(x-1) + ry*ry/2 + 2*rx*rx*(1-ry*ry);
						while(2*ry*ry*tx > 2*rx*rx*y) {
							String text = "Order "+ String.valueOf(cnt)+", d1 = "+String.valueOf(d1);
							if(flag) {
								c_pn_main.paint(cx+x, 24-cy-y, text);
								c_pn_main.paint(cx+x, 24-cy+y, text);
								c_pn_main.paint(cx-x, 24-cy-y, text);
								c_pn_main.paint(cx-x, 24-cy+y, text);
							}
							else {
								c_pn_main.paint(cx+y, 24-cy-x, text);
								c_pn_main.paint(cx+y, 24-cy+x, text);
								c_pn_main.paint(cx-y, 24-cy-x, text);
								c_pn_main.paint(cx-y, 24-cy+x, text);
							}
							if(d1 < 0) {
								y++;
								d1 += 4*rx*rx*y + 2*rx*rx;
								tx = x - 1;
							}
							else {
								x--;
								y++;
								d1 -= 4*ry*ry*x - 4*rx*rx*y - 2*rx*rx;
								tx = x;
							}
							cnt++;
						}
						int d2 = 2*ry*ry*(x*x+1) - 4*ry*ry*x + 2*rx*rx*(y*y+y-ry*ry) + rx*rx/2;
						while(x >= 0) {
							String text = "Order "+ String.valueOf(cnt)+", d2 = "+String.valueOf(d2);
							if(flag) {
								c_pn_main.paint(cx+x, 24-cy-y, text);
								c_pn_main.paint(cx+x, 24-cy+y, text);
								c_pn_main.paint(cx-x, 24-cy-y, text);
								c_pn_main.paint(cx-x, 24-cy+y, text);
							}
							else {
								c_pn_main.paint(cx+y, 24-cy-x, text);
								c_pn_main.paint(cx+y, 24-cy+x, text);
								c_pn_main.paint(cx-y, 24-cy-x, text);
								c_pn_main.paint(cx-y, 24-cy+x, text);
							}
							if(d2 < 0) {
								x--;
								y++;
								d2 += 4*rx*rx*y - 4*ry*ry*x + 2*ry*ry;
							}
							else {
								x--;
								d2 -= 4*ry*ry*x - 2*ry*ry;
							}
							cnt++;
						}
					}
					else {
						msg = new Msgbox("请选择绘制图形类型！");
					}
				}
			}
		});
	}
}