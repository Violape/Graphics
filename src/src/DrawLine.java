package src;

import java.awt.Font;
import java.awt.event.*;
import javax.swing.*;

class DrawLine extends JPanel {
	JLabel l_lb_x1, l_lb_x2, l_lb_y1, l_lb_y2;
	JTextField l_cb_x1,l_cb_x2,l_cb_y1,l_cb_y2;
	JRadioButton l_rd_bre, l_rd_dda, l_rd_mp;
	ButtonGroup l_bg_mode;
	JButton l_bt_draw;
	DrawPanel l_pn_main;
	public DrawLine() {
		String lookAndFeel ="com.sun.java.swing.plaf.windows.WindowsLookAndFeel";
		try { UIManager.setLookAndFeel(lookAndFeel); }
		catch(Exception e) {}
		setLayout(null);
		l_lb_x1 = new JLabel("起始位置横坐标");
		l_lb_x2 = new JLabel("结束位置横坐标");
		l_lb_y1 = new JLabel("起始位置纵坐标");
		l_lb_y2 = new JLabel("结束位置纵坐标");
		l_cb_x1 = new JTextField();
		l_cb_x2 = new JTextField();
		l_cb_y1 = new JTextField();
		l_cb_y2 = new JTextField();
		l_pn_main = new DrawPanel();
		l_bt_draw = new JButton("绘制");
		l_rd_bre = new JRadioButton("Bresham");
		l_rd_dda = new JRadioButton("DDA");
		l_rd_mp = new JRadioButton("Midpoint");
		l_bg_mode = new ButtonGroup();
		l_bg_mode.add(l_rd_bre);
		l_bg_mode.add(l_rd_dda);
		l_bg_mode.add(l_rd_mp);
		add(l_lb_x1);
		add(l_lb_x2);
		add(l_lb_y1);
		add(l_lb_y2);
		add(l_cb_x1);
		add(l_cb_x2);
		add(l_cb_y1);
		add(l_cb_y2);
		add(l_rd_bre);
		add(l_rd_dda);
		add(l_rd_mp);
		add(l_pn_main);
		add(l_bt_draw);
		l_rd_dda.setFont(new Font("Arial", Font.PLAIN, 12));
		l_rd_bre.setFont(new Font("Arial", Font.PLAIN, 12));
		l_rd_mp.setFont(new Font("Arial", Font.PLAIN, 12));
		l_cb_x1.setFont(new Font("Arial", Font.PLAIN, 12));
		l_cb_x2.setFont(new Font("Arial", Font.PLAIN, 12));
		l_cb_y1.setFont(new Font("Arial", Font.PLAIN, 12));
		l_cb_y2.setFont(new Font("Arial", Font.PLAIN, 12));
		l_lb_x1.setBounds(50,10,100,20);
		l_lb_x2.setBounds(200,10,100,20);
		l_lb_y1.setBounds(50,35,100,20);
		l_lb_y2.setBounds(200,35,100,20);
		l_cb_x1.setBounds(140,10,50,20);
		l_cb_x2.setBounds(290,10,50,20);
		l_cb_y1.setBounds(140,35,50,20);
		l_cb_y2.setBounds(290,35,50,20);
		l_bt_draw.setBounds(460,15,80,35);
		l_rd_dda.setBounds(360,0,100,20);
		l_rd_bre.setBounds(360,20,100,20);
		l_rd_mp.setBounds(360,40,100,20);
		l_pn_main.setBounds(40,60,540,540);
		l_bt_draw.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Msgbox msg;
				if(l_cb_x1.getText().equals("")) {
					msg = new Msgbox("请输入起始点与终点坐标！");
				}
				else if(l_cb_x2.getText().equals("")) {
					msg = new Msgbox("请输入起始点与终点坐标！");
				}
				else if(l_cb_y1.getText().equals("")) {
					msg = new Msgbox("请输入起始点与终点坐标！");
				}
				else if(l_cb_y2.getText().equals("")) {
					msg = new Msgbox("请输入起始点与终点坐标！");
				}
				else if(l_pn_main.l_cb_xmin.getText().equals("")) {
					msg = new Msgbox("请设置坐标系显示范围！");
				}
				else if(l_pn_main.l_cb_xmax.getText().equals("")) {
					msg = new Msgbox("请设置坐标系显示范围！");
				}
				else if(l_pn_main.l_cb_ymin.getText().equals("")) {
					msg = new Msgbox("请设置坐标系显示范围！");
				}
				else if(l_pn_main.l_cb_ymax.getText().equals("")) {
					msg = new Msgbox("请设置坐标系显示范围！");
				}
				else {
					for(int i=0;i<25;i++) {
						for(int j=0;j<25;j++) {
							l_pn_main.d_lb_point[i][j].setIcon(new ImageIcon("D:/Programming Workspace/Graphics/img/pointw.png"));
						}
					}
					int xmin = Integer.valueOf(l_pn_main.l_cb_xmin.getText());
					int ymin = Integer.valueOf(l_pn_main.l_cb_ymin.getText());
					int x1 = Integer.valueOf(l_cb_x1.getText())-xmin;
					int x2 = Integer.valueOf(l_cb_x2.getText())-xmin;
					int y1 = Integer.valueOf(l_cb_y1.getText())-ymin;
					int y2 = Integer.valueOf(l_cb_y2.getText())-ymin;
					if(x1>24 || x1<0 || x2>24 || x2<0 || y1>24 || y2<0 || y1>24 || y2<0) {
						msg = new Msgbox("绘图超出边界，请重新绘制！");
						return;
					}
					if(x1 > x2) {
						int tempx = x1, tempy = y1;
						x1 = x2;
						y1 = y2;
						x2 = tempx;
						y2 = tempy;
					}
					if(l_rd_dda.isSelected()) {
						int i;
						float dx,dy,temp,k;
						if(x1 == x2) {
							if(y1 > y2) {
								for(int j = y2; j <= y1; j++) {
									String text = "Order "+String.valueOf(j-y2);
									l_pn_main.paint(x1,24-j, text);
								}
							}
							else {
								for(int j = y1; j <= y2; j++) {
									String text = "Order "+String.valueOf(j-y1);
									l_pn_main.paint(x1,24-j,text);
								}
							}
						}
						if(x1 < x2){
							dx = x2 - x1;
							dy = y2 - y1;
							int flag = 0;
							if(dy < 0) {
								dy = -dy;
								flag = 1;
							}
							if(dx >= dy) {
								k = dy / dx;
								temp = y1;
								for(i = x1; i <= x2; i++) {
									String text = "Order "+String.valueOf(i-x1)+", temp = "+String.format("%.2f",temp);
									l_pn_main.paint(i,(int)(24.5-temp),text);
									if(flag==1)
										temp = temp - k;
									else
										temp = temp + k;
								}
							}
							else {
								k = dx / dy;
								if(y1 < y2) {
									temp = x1;
									for(i = y1 ; i <= y2; i++) {
										String text = "Order "+String.valueOf(i-y1)+", temp = "+String.format("%.2f",temp);
										l_pn_main.paint((int)(temp+0.5),(24-i),text);
										temp = temp + k;
									}
								}
								else {
									temp = x2;
									for(i = y2 ; i <= y1; i++) {
										String text = "Order "+String.valueOf(i-y2)+", temp = "+String.format("%.2f",temp);
										l_pn_main.paint((int)(temp+0.5),(24-i),text);
										temp = temp - k;
									}
								}
							}
						}
					}
					else if(l_rd_bre.isSelected()) {
						int x=x1, y=y1;
						float dx=x2-x1, dy=y2-y1;
						if(x1==x2) {
							if(y1<y2) {
								while(y<=y2) {
									String text = "Order "+String.valueOf(y-y1);
									l_pn_main.paint(x1,24-y,text);
									y++;
								}
							}
							else {
								y = y2;
								while(y<=y1) {
									String text = "Order "+String.valueOf(y-y2);
									l_pn_main.paint(x1,24-y,text);
									y++;
								}
							}
						}
						else if(dy>0) {
							if(dy<=dx) {
								float k=dy/dx, bre=-0.5f;
								for(int i=0;i<=dx;i++) {
									bre+=k;
									String text = "Order "+String.valueOf(i)+", e = "+String.format("%.2f", bre);
									l_pn_main.paint(x,24-y,text);
									x++;
									if(bre>=0) {
										y++;
										bre-=1;
									}
								}
							}
							else {
								float k=dx/dy, bre=-0.5f;
								for(int i=0; i<dy; i++) {
									bre+=k;
									String text = "Order "+String.valueOf(i)+", e = "+String.format("%.2f", bre);
									l_pn_main.paint(x,24-y,text);
									y++;
									if(bre>=0) {
										x++;
										bre-=1;
									}
								}
							}
						}
						else {
							if(dx+dy>0) {
								float k=dy/dx, bre=0.5f;
								for(int i=0;i<=dx;i++) {
									bre+=k;
									String text = "Order "+String.valueOf(i)+", e = "+String.format("%.2f", -bre);
									l_pn_main.paint(x,24-y,text);
									x++;
									if(bre<0) {
										y--;
										bre+=1;
									}
								}
							}
							else {
								float k=dx/dy, bre=0.5f;
								for(int i=0; i<=-dy; i++) {
									bre+=k;
									String text = "Order "+String.valueOf(i)+", e = "+String.format("%.2f", -bre);
									l_pn_main.paint(x,24-y,text);
									y--;
									if(bre<0) {
										x++;
										bre+=1;
									}
								}
							}
						}
					}
					else if(l_rd_mp.isSelected()) {
						int a=y1-y2, b=x2-x1;
						if(b==0) {
							if(y1<y2) {
								int y = y1;
								while(y<=y2) {
									String text="Order "+String.valueOf(y-y1);
									l_pn_main.paint(x1,24-y,text);
									y++;
								}
							}
							else {
								int y = y2;
								while(y<=y1) {
									String text="Order "+String.valueOf(y-y2);
									l_pn_main.paint(x1,24-y,text);
									y++;
								}
							}
						}
						else if(a<=0){
							int x=x1, y=y1;
							if(a+b>=0) {
								int d=2*a+b, d1=2*a, d2=2*a+2*b;
								String text="Order "+String.valueOf(x-x1)+", d = "+String.valueOf(-d);
								l_pn_main.paint(x,24-y,text);
								while(x<x2) {
									text="Order "+String.valueOf(x-x1)+", d = "+String.valueOf(-d);
									if(d<0) {
										x++;
										y++;
										d+=d2;
									}
									else {
										x++;
										d+=d1;
									}
									l_pn_main.paint(x,24-y,text);
								}
							}
							else {
								int d=2*b+a, d1=2*b, d2=2*a+2*b;
								String text="Order "+String.valueOf(y-y1)+", d = "+String.valueOf(d);
								l_pn_main.paint(x,24-y,text);
								while(y<y2) {
									text="Order "+String.valueOf(y-y1)+", d = "+String.valueOf(d);
									if(d>0) {
										x++;
										y++;
										d+=d2;
									}
									else
									{
										y++;
										d+=d1;
									}
									l_pn_main.paint(x,24-y,text);
								}
							}
						}
						else {
							int x=x1, y=y1;
							if(a<b) {
								int d=2*a-b, d1=2*a, d2=2*a-2*b;
								String text="Order "+String.valueOf(x-x1)+", d = "+String.valueOf(d);
								l_pn_main.paint(x,24-y,text);
								while(x<x2) {
									text="Order "+String.valueOf(x-x1)+", d = "+String.valueOf(d);
									if(d>0) {
										x++;
										y--;
										d+=d2;
									}
									else {
										x++;
										d+=d1;
									}
									l_pn_main.paint(x,24-y,text);
								}
							}
							else {
								int d=a-2*b, d1=-2*b, d2=2*a-2*b;
								String text="Order "+String.valueOf(y1-y)+", d = "+String.valueOf(d);
								l_pn_main.paint(x,24-y,text);
								while(y>y2) {
									text="Order "+String.valueOf(y1-y)+", d = "+String.valueOf(d);
									if(d<0) {
										y--;
										x++;
										d+=d2;
									}
									else {
										y--;
										d+=d1;
									}
									l_pn_main.paint(x,24-y,text);
								}
							}
						}
					}
					else {
						msg = new Msgbox("请选择绘图方法！");
					}
				}
			}
		});
	}
}