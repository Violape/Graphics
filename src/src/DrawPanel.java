package src;

import java.awt.Font;
import java.awt.event.*;
import javax.swing.*;

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
