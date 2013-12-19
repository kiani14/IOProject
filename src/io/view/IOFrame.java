package io.view;

import javax.swing.JFrame;

import io.controller.IOController;

/**
 * Frame for the IO Project
 * 
 * @author kell7444
 * @Version 1.1 16/12/13 Created setup method and constructor. Added a title to
 *          the window.
 * 
 */
public class IOFrame extends JFrame
{

	/**
	 * IOPanel for the frame.
	 */
	private IOPanel basePanel;

	/**
	 * Constructor for the IOFrame
	 * @param baseController IOController passed in for MVC relationship
	 */
	public IOFrame(IOController baseController)
	{
		basePanel = new IOPanel(baseController);
		setupFrame();
	}

	/**
	 * Sets up the frame size and loads the content panel.
	 */
	private void setupFrame()
	{
		this.setContentPane(basePanel);
		this.setTitle("IO Project");
		this.setSize(400, 400);
		this.setVisible(true);
	}

}
