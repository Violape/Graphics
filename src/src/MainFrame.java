package src;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;

public class MainFrame {
	public static void main(String[] args) {
		MainLayout window = new MainLayout();
		window.setBounds(280,60,800,600);
		window.setTitle("计算机图形学实验设计");
	}
}

class MainLayout extends JFrame{
	JButton w_bt_DrawLine;
	JButton w_bt_DrawCircle;
	JButton w_bt_DrawPolygen;
	JButton w_bt_DrawFill;
	DrawLine w_pn_DrawLine;
	DrawCircle w_pn_DrawCircle;
	public MainLayout() {
		String lookAndFeel ="com.sun.java.swing.plaf.windows.WindowsLookAndFeel";
		try { UIManager.setLookAndFeel(lookAndFeel); }
		catch(Exception e) {}
		setLayout(null);
		w_bt_DrawLine = new JButton("绘制直线");
		w_bt_DrawCircle = new JButton("绘制圆");
		w_bt_DrawPolygen = new JButton("绘制多边形");
		w_bt_DrawFill = new JButton("区域填充");
		w_pn_DrawLine = new DrawLine();
		w_pn_DrawCircle = new DrawCircle();
		add(w_bt_DrawLine);
		add(w_bt_DrawCircle);
		add(w_bt_DrawPolygen);
		add(w_bt_DrawFill);
		add(w_pn_DrawLine);
		add(w_pn_DrawCircle);
		w_bt_DrawLine.setBounds(30,100,120,50);
		w_bt_DrawCircle.setBounds(30,200,120,50);
		w_bt_DrawPolygen.setBounds(30,300,120,50);
		w_bt_DrawFill.setBounds(30,400,120,50);
		w_pn_DrawLine.setBounds(180,0,600,600);
		w_pn_DrawCircle.setBounds(180,0,600,600);
		w_bt_DrawLine.setFont(new Font("幼圆",Font.BOLD, 16));
		w_bt_DrawCircle.setFont(new Font("幼圆",Font.BOLD, 16));
		w_bt_DrawPolygen.setFont(new Font("幼圆",Font.BOLD, 16));
		w_bt_DrawFill.setFont(new Font("幼圆",Font.BOLD, 16));
		w_bt_DrawPolygen.setEnabled(false);
		w_bt_DrawFill.setEnabled(false);
		setVisible(true);
		w_pn_DrawLine.setVisible(false);
		w_pn_DrawCircle.setVisible(false);
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		w_bt_DrawLine.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
					w_pn_DrawLine.setVisible(true);
					w_pn_DrawCircle.setVisible(false);
				}
			});
		w_bt_DrawCircle.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
					w_pn_DrawCircle.setVisible(true);
					w_pn_DrawLine.setVisible(false);
				}
			});
	}
}

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
						if(r < 0) {
							msg = new Msgbox("半径小于0，输入数据非法！");
							return;
						}
						if(cx+r>24||cx-r<0||cy+r>24||cy-r<0) {
							msg = new Msgbox("绘图超出边界，请重新绘制！");
							return;
						}
						if(c_rd_mp.isSelected()) {
							; //圆的中点画线算法
						}
						else if(c_rd_br.isSelected()) {
							; //圆的Bresham算法
						}
						else {
							msg = new Msgbox("请选择绘制方法！");
						}
					}
					else if(c_rd_to.isSelected()) {
						int rx = Integer.valueOf(c_tf_rx.getText());
						int ry = Integer.valueOf(c_tf_ry.getText());
						if(rx < 0) {
							msg = new Msgbox("半径小于0，输入数据非法！");
							return;
						}
						if(ry < 0) {
							msg = new Msgbox("半径小于0，输入数据非法！");
							return;
						}
						if(cx+rx>24||cx-rx<0||cy+ry>24||cy-ry<0) {
							msg = new Msgbox("绘图超出边界，请重新绘制！");
							return;
						}
						; //椭圆的中点画线算法
					}
					else {
						msg = new Msgbox("请选择绘制图形类型！");
					}
				}
			}
		});
	}
}

class DrawPanel extends JPanel{
	JLabel d_lb_gridh[], d_lb_gridv[], d_lb_point[][];
	JLabel l_cb_xaxis[], l_cb_yaxis[];
	JTextField l_cb_xmin,l_cb_xmax,l_cb_ymin,l_cb_ymax;
	public DrawPanel(){
		String lookAndFeel ="com.sun.java.swing.plaf.windows.WindowsLookAndFeel";
		try { UIManager.setLookAndFeel(lookAndFeel); }
		catch(Exception e) {}
		setLayout(null);
		l_cb_xmin = new JTextField();
		l_cb_xmax = new JTextField();
		l_cb_ymin = new JTextField();
		l_cb_ymax = new JTextField();
		l_cb_xaxis = new JLabel[5];
		l_cb_yaxis = new JLabel[5];
		d_lb_gridh = new JLabel[25];
		d_lb_gridv = new JLabel[25];
		d_lb_point = new JLabel[25][25];
		for(int i=0; i<5; i++) {
			l_cb_xaxis[i] = new JLabel();
			l_cb_yaxis[i] = new JLabel();
		}
		for(int i=0; i<25; i++) {
			for(int j=0; j<25;j++) {
				d_lb_point[i][j] = new JLabel();
				d_lb_point[i][j].setIcon(new ImageIcon("D:/Programming Workspace/Graphics/img/pointw.png"));
				add(d_lb_point[i][j]);
				d_lb_point[i][j].setBounds(i*20-6,j*20-7,14,14);
			}
		}
		for(int i=0; i<25; i++) {
			d_lb_gridh[i] = new JLabel();
			d_lb_gridh[i].setIcon(new ImageIcon("D:/Programming Workspace/Graphics/img/thicklineh.png"));
			add(d_lb_gridh[i]);
			d_lb_gridh[i].setBounds(0,i*20,481,1);
			d_lb_gridv[i] = new JLabel();
			d_lb_gridv[i].setIcon(new ImageIcon("D:/Programming Workspace/Graphics/img/thicklinev.png"));
			add(d_lb_gridv[i]);
			d_lb_gridv[i].setBounds(i*20,0,1,481);
		}
		add(l_cb_xmin);
		add(l_cb_xmax);
		add(l_cb_ymin);
		add(l_cb_ymax);
		for(int i=0;i<5;i++) {
			add(l_cb_xaxis[i]);
			add(l_cb_yaxis[i]);
		}
		l_cb_xmin.setHorizontalAlignment(SwingConstants.CENTER);
		l_cb_xmax.setHorizontalAlignment(SwingConstants.CENTER);
		l_cb_ymin.setHorizontalAlignment(SwingConstants.CENTER);
		l_cb_ymax.setHorizontalAlignment(SwingConstants.CENTER);
		l_cb_xmin.setFont(new Font("Arial", Font.PLAIN, 12));
		l_cb_xmax.setFont(new Font("Arial", Font.PLAIN, 12));
		l_cb_ymin.setFont(new Font("Arial", Font.PLAIN, 12));
		l_cb_ymax.setFont(new Font("Arial", Font.PLAIN, 12));
		l_cb_xmin.setText("0");
		l_cb_xmax.setText("24");
		l_cb_ymin.setText("0");
		l_cb_ymax.setText("24");
		l_cb_xmax.setEditable(false);
		l_cb_ymax.setEditable(false);
		setaxis();
		l_cb_xmin.setBounds(0,490,40,20);
		l_cb_xmax.setBounds(460,490,40,20);
		l_cb_ymin.setBounds(495,465,40,20);
		l_cb_ymax.setBounds(495,0,40,20);
		for(int i=0;i<5;i++) {
			l_cb_xaxis[i].setBounds(80*i+75,490,40,20);
			l_cb_yaxis[i].setBounds(495,390-80*i,40,20);
			l_cb_xaxis[i].setFont(new Font("Arial", Font.PLAIN, 12));
			l_cb_yaxis[i].setFont(new Font("Arial", Font.PLAIN, 12));
		}
		l_cb_xmin.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setaxis();
			}
		});
		l_cb_ymin.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setaxis();
			}
		});
	}
	void setaxis(){
		l_cb_xmax.setText(String.valueOf(Integer.valueOf(l_cb_xmin.getText())+24));
		l_cb_ymax.setText(String.valueOf(Integer.valueOf(l_cb_ymin.getText())+24));
		int xmin = Integer.valueOf(l_cb_xmin.getText());
		int ymin = Integer.valueOf(l_cb_ymin.getText());
		for(int i=0; i<5; i++) {
			l_cb_xaxis[i].setText(String.valueOf(xmin+i*4+4));
			l_cb_yaxis[i].setText(String.valueOf(ymin+i*4+4));
		}
	}
	void paint(int x, int y, String text){
		d_lb_point[x][y].setIcon(new ImageIcon("D:/Programming Workspace/Graphics/img/pointb.png"));
		d_lb_point[x][y].setToolTipText(text);
	}
}
