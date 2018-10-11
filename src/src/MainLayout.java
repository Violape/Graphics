package src;
import java.awt.Font;
import java.awt.event.*;
import javax.swing.*;

class MainLayout extends JFrame{
	JButton w_bt_DrawLine;
	JButton w_bt_DrawCircle;
	JButton w_bt_DrawFill;
	JButton w_bt_DrawTrim;
	DrawLine w_pn_DrawLine;
	DrawCircle w_pn_DrawCircle;
	public MainLayout() {
		String lookAndFeel ="com.sun.java.swing.plaf.windows.WindowsLookAndFeel";
		try { UIManager.setLookAndFeel(lookAndFeel); }
		catch(Exception e) {}
		setLayout(null);
		w_bt_DrawLine = new JButton("»æÖÆÖ±Ïß");
		w_bt_DrawCircle = new JButton("»æÖÆÔ²");
		w_bt_DrawFill = new JButton("¶à±ßÐÎÌî³ä");
		w_bt_DrawTrim = new JButton("ÇøÓò²Ã¼ô");
		w_pn_DrawLine = new DrawLine();
		w_pn_DrawCircle = new DrawCircle();
		add(w_bt_DrawLine);
		add(w_bt_DrawCircle);
		add(w_bt_DrawFill);
		add(w_bt_DrawTrim);
		add(w_pn_DrawLine);
		add(w_pn_DrawCircle);
		w_bt_DrawLine.setBounds(30,100,120,50);
		w_bt_DrawCircle.setBounds(30,200,120,50);
		w_bt_DrawFill.setBounds(30,300,120,50);
		w_bt_DrawTrim.setBounds(30,400,120,50);
		w_pn_DrawLine.setBounds(180,0,600,600);
		w_pn_DrawCircle.setBounds(180,0,600,600);
		w_bt_DrawLine.setFont(new Font("Ó×Ô²",Font.BOLD, 16));
		w_bt_DrawCircle.setFont(new Font("Ó×Ô²",Font.BOLD, 16));
		w_bt_DrawFill.setFont(new Font("Ó×Ô²",Font.BOLD, 16));
		w_bt_DrawTrim.setFont(new Font("Ó×Ô²",Font.BOLD, 16));
		w_bt_DrawFill.setEnabled(false);
		w_bt_DrawTrim.setEnabled(false);
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