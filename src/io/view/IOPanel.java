package io.view;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SpringLayout;

import io.controller.IOController;
import io.model.Game;

/**
 * Panel for the IO project. 
 * @author kell7444
 *
 */
public class IOPanel extends JPanel
{
	private IOController baseController;
	private JButton saveButton;
	private JButton loadButton;
	private JTextField titleField;
	private JTextField rankingField;
	private JTextArea rulesArea;
	private JLabel rulesLabel;
	private JLabel rankingLabel;
	private JLabel titleLabel;
	private SpringLayout baseLayout;
	private JLabel gameCountLabel;
	
	/**
	 * Constructor for the project. Uses a IO Controller to link to the MVC paradigm
	 * @param baseController The reference to the controller for the project.
	 */
	public IOPanel(IOController baseController)
	{
		this.baseController = baseController;
		saveButton = new JButton("save the game");
		loadButton = new JButton("load the game");
		titleField = new JTextField(15);
		titleLabel = new JLabel("Game Title");
		rankingField = new JTextField(5);
		rankingLabel = new JLabel("Game Ranking");
		rulesArea = new JTextArea(5, 20);
		rulesLabel = new JLabel("Game Rules");
		gameCountLabel = new JLabel("Current game count:");
		baseLayout = new SpringLayout();
		baseLayout.putConstraint(SpringLayout.WEST, gameCountLabel, 0, SpringLayout.WEST, rankingLabel);
		baseLayout.putConstraint(SpringLayout.SOUTH, gameCountLabel, -27, SpringLayout.NORTH, saveButton);
		baseLayout.putConstraint(SpringLayout.WEST, rankingField, 41, SpringLayout.EAST, rankingLabel);
		baseLayout.putConstraint(SpringLayout.NORTH, rulesArea, -5, SpringLayout.NORTH, rulesLabel);
		baseLayout.putConstraint(SpringLayout.WEST, rulesArea, 0, SpringLayout.WEST, rankingField);
		baseLayout.putConstraint(SpringLayout.NORTH, rulesLabel, 26, SpringLayout.SOUTH, rankingLabel);
		baseLayout.putConstraint(SpringLayout.WEST, rulesLabel, 0, SpringLayout.WEST, rankingLabel);
		baseLayout.putConstraint(SpringLayout.NORTH, rankingField, -3, SpringLayout.NORTH, rankingLabel);
		baseLayout.putConstraint(SpringLayout.NORTH, titleField, -3, SpringLayout.NORTH, titleLabel);
		baseLayout.putConstraint(SpringLayout.WEST, titleField, 58, SpringLayout.EAST, titleLabel);
		baseLayout.putConstraint(SpringLayout.NORTH, rankingLabel, 24, SpringLayout.SOUTH, titleLabel);
		baseLayout.putConstraint(SpringLayout.WEST, rankingLabel, 10, SpringLayout.WEST, this);
		baseLayout.putConstraint(SpringLayout.WEST, titleLabel, 10, SpringLayout.WEST, this);
		baseLayout.putConstraint(SpringLayout.NORTH, titleLabel, 23, SpringLayout.NORTH, this);
	
		setupPanel();
		setupLayout();
		setupListeners();
		
	}
	
	/**
	 * Helper method to add the components to the panel, including defining the background color. 
	 */
	private void setupPanel()
	{
		this.setLayout(baseLayout);
		this.setBackground(new Color(255, 182, 193));
		this.add(rankingField);
		this.add(rankingLabel);
		this.add(rulesArea);
		this.add(rulesLabel);
		this.add(saveButton);
		this.add(titleField);
		this.add(titleLabel);
		this.add(loadButton);
		this.add(gameCountLabel);
		
	}
	
	/**
	 * Helper method to set up the visual layout (location on the panel) of the panel. 
	 */
	private void setupLayout()
	 {
		baseLayout.putConstraint(SpringLayout.NORTH, saveButton, 248, SpringLayout.NORTH, this);
		baseLayout.putConstraint(SpringLayout.WEST, saveButton, 85, SpringLayout.WEST, this);
		baseLayout.putConstraint(SpringLayout.NORTH, loadButton, 0, SpringLayout.NORTH, saveButton);
		baseLayout.putConstraint(SpringLayout.WEST, loadButton, 16, SpringLayout.EAST, saveButton);
		
	 }
	
	/**
	 * Method that defines what the save button is to do; it makes it save when you click it
	 */
	private void setupListeners()
	{
		saveButton.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent click)
			{
				Game tempGame = baseController.makeGameFromInput(titleField.getText(), rankingField.getText(), rulesArea.getText());
				if (tempGame !=null)
				{
					baseController.saveGameInformation(tempGame);
					gameCountLabel.setText("Current game count: " + baseController.getProjectGames().size());
				}
				else
				{
					JOptionPane.showMessageDialog(null, "Try again with a valid number");
				}
			}
		});
		
		/**
		 * Method that defines what the load button does; it retrieves the information from the saved file
		 */
		loadButton.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent click)
			{
				Game tempGame = baseController.readGameInformation();
				if(tempGame != null)
				{
					titleField.setText(tempGame.getGameTitle());
					rankingField.setText(Integer.toString(tempGame.getFunRanking()));
					String tempRules = "";
					for(String currentRule : tempGame.getGameRules())
					{
						tempRules += currentRule + "\n";
					}
					rulesArea.setText(tempRules);
					
				}
				else
				{
					JOptionPane.showMessageDialog(null, "Check the save file to make sure it is in order.");
				}
			}
		});
	}

}
