package com.mesutgolcuk.birislem;

import javax.swing.JFrame;
import javax.swing.JTextField;
import com.jgoodies.forms.layout.FormLayout;
import com.jgoodies.forms.layout.ColumnSpec;
import com.jgoodies.forms.layout.RowSpec;
import com.jgoodies.forms.layout.FormSpecs;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JLabel;
import java.awt.Font;

public class Frame extends JFrame {
	private JTextField textField;  // holds number1
	private JTextField textField_1; // holds number2
	private JTextField textField_2; // holds number3
	private JTextField textField_3; // holds number4
	private JTextField textField_4; // holds number5
	private JTextField textField_5; // holds number6
	private JTextField textField_6; // holds number7
	private JTextField textField_7; // holds result
	private JLabel lblNewLabel;
	private JLabel lblNumber;
	private JLabel lblNumber_1;
	private JLabel lblNumber_2;
	private JLabel lblNumber_3;
	private JLabel lblNmber;
	private JLabel lblWanted;
	String []numbers;
	int result;
	/**
	 * Create the frame.
	 */
	public Frame() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		getContentPane().setLayout(new FormLayout(new ColumnSpec[] {
				FormSpecs.RELATED_GAP_COLSPEC,
				FormSpecs.DEFAULT_COLSPEC,
				FormSpecs.RELATED_GAP_COLSPEC,
				FormSpecs.DEFAULT_COLSPEC,
				FormSpecs.RELATED_GAP_COLSPEC,
				ColumnSpec.decode("default:grow"),},
			new RowSpec[] {
				FormSpecs.RELATED_GAP_ROWSPEC,
				FormSpecs.DEFAULT_ROWSPEC,
				FormSpecs.RELATED_GAP_ROWSPEC,
				FormSpecs.DEFAULT_ROWSPEC,
				FormSpecs.RELATED_GAP_ROWSPEC,
				FormSpecs.DEFAULT_ROWSPEC,
				FormSpecs.RELATED_GAP_ROWSPEC,
				FormSpecs.DEFAULT_ROWSPEC,
				FormSpecs.RELATED_GAP_ROWSPEC,
				FormSpecs.DEFAULT_ROWSPEC,
				FormSpecs.RELATED_GAP_ROWSPEC,
				FormSpecs.DEFAULT_ROWSPEC,
				FormSpecs.RELATED_GAP_ROWSPEC,
				FormSpecs.DEFAULT_ROWSPEC,
				FormSpecs.RELATED_GAP_ROWSPEC,
				FormSpecs.DEFAULT_ROWSPEC,}));
		
		numbers = new String[6];
		
		lblNewLabel = new JLabel("Number1");
		getContentPane().add(lblNewLabel, "2, 2, right, default");
		
		textField = new JTextField();
		textField.setText("1");
		getContentPane().add(textField, "4, 2, fill, default");
		textField.setColumns(10);
		
		lblNumber = new JLabel("Number2");
		getContentPane().add(lblNumber, "2, 4, right, default");
		
		textField_1 = new JTextField();
		textField_1.setText("3");
		getContentPane().add(textField_1, "4, 4, fill, default");
		textField_1.setColumns(10);
		
		lblNumber_1 = new JLabel("Number3");
		getContentPane().add(lblNumber_1, "2, 6, right, default");
		
		textField_2 = new JTextField();
		textField_2.setText("4");
		getContentPane().add(textField_2, "4, 6, fill, default");
		textField_2.setColumns(10);
		
		lblNumber_2 = new JLabel("Number4");
		getContentPane().add(lblNumber_2, "2, 8, right, default");
		
		textField_3 = new JTextField();
		textField_3.setText("7");
		getContentPane().add(textField_3, "4, 8, fill, default");
		textField_3.setColumns(10);
		
		textField_7 = new JTextField();
		textField_7.setFont(new Font("Tahoma", Font.PLAIN, 22));
		getContentPane().add(textField_7, "6, 2, 1, 13, fill, default");
		textField_7.setColumns(10);
		textField_7.setEditable(false);
		
		lblNumber_3 = new JLabel("Number5");
		getContentPane().add(lblNumber_3, "2, 10, right, default");
		
		textField_4 = new JTextField();
		textField_4.setText("8");
		getContentPane().add(textField_4, "4, 10, fill, default");
		textField_4.setColumns(10);
		
		lblNmber = new JLabel("Number6");
		getContentPane().add(lblNmber, "2, 12, right, default");
		
		textField_5 = new JTextField();
		textField_5.setText("9");
		getContentPane().add(textField_5, "4, 12, fill, default");
		textField_5.setColumns(10);
		
		lblWanted = new JLabel("Result");
		getContentPane().add(lblWanted, "2, 14, right, default");
		
		textField_6 = new JTextField();
		textField_6.setText("72");
		getContentPane().add(textField_6, "4, 14, fill, default");
		textField_6.setColumns(10);
		
		JButton btnNewButton = new JButton("Produce Result");
		btnNewButton.addActionListener(new ActionListener() {
			// button clicked
			public void actionPerformed(ActionEvent arg0) {
				// create number array
				numbers[0] = textField.getText();
				numbers[1] = textField_1.getText();
				numbers[2] = textField_2.getText();
				numbers[3] = textField_3.getText();
				numbers[4] = textField_4.getText();
				numbers[5] = textField_5.getText();
				// parse result
				result = Integer.parseInt(textField_6.getText());
				// find steps of reaching result
				String s = GeneticAlgorithm.produceResult(numbers,result);
				// set steps to ui
				textField_7.setText(s);
			}
		});
		getContentPane().add(btnNewButton, "4, 16");
	}

}
