package src;

import java.awt.Font;

import javax.swing.*;

public class DrawFill extends JPanel {
	JLabel f_lb_sp, f_lb_fp, f_lb_px, f_lb_py;
	JTextField f_tf_spx, f_tf_spy, f_tf_fpx, f_tf_fpy, f_tf_px, f_tf_py;
	JRadioButton f_rd_fl, f_rd_sc;
	ButtonGroup f_bg_mode;
	JButton f_bt_ln, f_bt_pt[][], f_bt_draw;
	DrawPanel f_pn_main;
	public DrawFill() {
		String lookAndFeel ="com.sun.java.swing.plaf.windows.WindowsLookAndFeel";
		try { UIManager.setLookAndFeel(lookAndFeel); }
		catch(Exception e) {}
		setLayout(null);
		f_lb_sp = new JLabel("起点：");
		f_lb_fp = new JLabel("终点：");
		f_lb_px = new JLabel("横坐标：");
		f_lb_py = new JLabel("纵坐标：");
		f_tf_spx = new JTextField();
		f_tf_spy = new JTextField();
		f_tf_fpx = new JTextField();
		f_tf_fpy = new JTextField();
		f_tf_px = new JTextField();
		f_tf_py = new JTextField();
		f_rd_fl = new JRadioButton("递归");
		f_rd_sc = new JRadioButton("扫描线");
		f_bt_ln = new JButton("直线");
		f_bt_pt = new JButton[3][3];
		f_bt_draw = new JButton("填充");
		f_pn_main = new DrawPanel();
		for(int i=0; i<3; i++)
			for(int j=0; j<3; j++) {
				f_bt_pt[i][j] = new JButton("");
				add(f_bt_pt[i][j]);
				f_bt_pt[i][j].setBounds(315+20*i,3+20*j,15,15);
				if(i!=1||j!=1) {
					f_bt_pt[i][j].setEnabled(false);
				}
			}
		f_bg_mode = new ButtonGroup();
		f_bg_mode.add(f_rd_fl);
		f_bg_mode.add(f_rd_sc);
		add(f_lb_sp);
		add(f_lb_fp);
		add(f_lb_px);
		add(f_lb_py);
		add(f_tf_spx);
		add(f_tf_spy);
		add(f_tf_fpx);
		add(f_tf_fpy);
		add(f_tf_px);
		add(f_tf_py);
		add(f_bt_ln);
		add(f_bt_draw);
		add(f_pn_main);
		add(f_rd_fl);
		add(f_rd_sc);
		f_tf_spx.setFont(new Font("Helvetica", Font.PLAIN, 11));
		f_tf_spy.setFont(new Font("Helvetica", Font.PLAIN, 11));
		f_tf_fpx.setFont(new Font("Helvetica", Font.PLAIN, 11));
		f_tf_fpy.setFont(new Font("Helvetica", Font.PLAIN, 11));
		f_tf_px.setFont(new Font("Helvetica", Font.PLAIN, 11));
		f_tf_py.setFont(new Font("Helvetica", Font.PLAIN, 11));
		f_lb_sp.setBounds(20,10,40,20);
		f_lb_fp.setBounds(20,35,40,20);
		f_lb_px.setBounds(220,10,50,20);
		f_lb_py.setBounds(220,35,50,20);
		f_tf_spx.setBounds(60,10,35,20);
		f_tf_spy.setBounds(100,10,35,20);
		f_tf_fpx.setBounds(60,35,35,20);
		f_tf_fpy.setBounds(100,35,35,20);
		f_tf_px.setBounds(270,10,35,20);
		f_tf_py.setBounds(270,35,35,20);
		f_bt_ln.setBounds(145,20,60,25);
		f_bt_draw.setBounds(460,15,80,35);
		f_rd_fl.setBounds(380,10,80,20);
		f_rd_sc.setBounds(380,35,80,20);
		f_pn_main.setBounds(40,60,540,540);
		f_rd_fl.setSelected(false);
		f_rd_sc.setEnabled(false);
	}
}
