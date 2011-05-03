package com.dobby.client.ui;

import java.awt.Rectangle;

import javax.swing.JEditorPane;
import javax.swing.JPanel;
import javax.swing.text.Document;

public class TestDisplayPanel extends JPanel {

	private static final long serialVersionUID = 1L;
	private JEditorPane jEditorPane = null;

	/**
	 * This is the default constructor
	 */
	public TestDisplayPanel() {
		super();
		initialize();
	}

	/**
	 * This method initializes this
	 * 
	 * @return void
	 */
	private void initialize() {

		this.setSize(500, 700);
		this.setLayout(null);
		this.add(getJEditorPane());
	}

	/**
	 * This method initializes jEditorPane	
	 * 	
	 * @return javax.swing.JEditorPane	
	 */
	private JEditorPane getJEditorPane() {
		if (jEditorPane == null) {
			jEditorPane = new JEditorPane();
			jEditorPane.setBounds(new Rectangle(5, 5, 490, 690));
		}
		return jEditorPane;
	}

	public Document getDocument() {
		return jEditorPane.getDocument();
	}

	public JEditorPane getjEditorPane() {
		return jEditorPane;
	}

	
	
	
}
