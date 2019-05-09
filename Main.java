import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.geom.*;

public class Main extends JFrame 
{
    
	
	
    JMenuBar mainMenuBar = new JMenuBar();
    JMenu fileMenu = new JMenu("File");
    JMenuItem newMenuItem = new JMenuItem("New");
    JMenuItem exitMenuItem = new JMenuItem("Exit");
    JMenu penMenu = new JMenu("Pen");
    JMenuItem tinyMenuItem = new JMenuItem("Tiny");
    JMenuItem smallMenuItem = new JMenuItem("Small");
    JMenuItem medMenuItem = new JMenuItem("Medium");
    JMenuItem largeMenuItem = new JMenuItem("Large");
    JMenuItem giantMenuItem = new JMenuItem("Giant");
    JMenu backMenu = new JMenu("Background");
    JMenuItem defaultMenuItem = new JMenuItem("Default");
    JMenuItem greyMenuItem = new JMenuItem("Gray");
    JMenuItem whiteMenuItem = new JMenuItem("White");
    JMenuItem greenMenuItem = new JMenuItem("Green");
    JMenuItem blueMenuItem = new JMenuItem("Blue");
    JMenu modeMenu = new JMenu("Mode");
    JMenuItem darkMenuItem = new JMenuItem("Dark");
    JMenuItem lightMenuItem = new JMenuItem("Light");
    JMenuItem deMenuItem = new JMenuItem("Default");
    JPanel drawPanel = new JPanel();
    JLabel leftColorLabel = new JLabel();
    JLabel rightColorLabel = new JLabel();
    JPanel colorPanel = new JPanel();
    JLabel[] colorLabel = new JLabel[14];
    Graphics2D g2D;
    double xPrevious, yPrevious;
    Color drawColor, leftColor, rightColor;
    
    
    
    public static void main(String args[]) 
    {
       
        new Main().setVisible(true);
    }
    
    
    
    public Main()
    {
        
        setTitle("William Kellermann CSP");
        setResizable(false);
        addWindowListener(new WindowAdapter()
        {
            public void windowClosing(WindowEvent e)
            {
                exitForm(e);
            }
        });
        getContentPane().setLayout(new GridBagLayout());
        Color backgroundColor = new Color(122,50,150);
        getContentPane().setBackground(backgroundColor);
        
        setJMenuBar(mainMenuBar);
        fileMenu.setMnemonic('F');
        Color menubarColor = new Color(100,100,100);
        mainMenuBar.setBackground(menubarColor);
        colorPanel.setBackground(menubarColor);
        mainMenuBar.add(fileMenu);
        mainMenuBar.add(penMenu);
        mainMenuBar.add(backMenu);
        mainMenuBar.add(modeMenu);
        penMenu.add(tinyMenuItem);
        penMenu.add(smallMenuItem);
        penMenu.add(medMenuItem);
        penMenu.add(largeMenuItem);
        penMenu.add(giantMenuItem);
        backMenu.add(defaultMenuItem);
        backMenu.add(greyMenuItem);
        backMenu.add(whiteMenuItem);
        backMenu.add(greenMenuItem);
        backMenu.add(blueMenuItem);
        fileMenu.add(newMenuItem);
        fileMenu.addSeparator();
        fileMenu.add(exitMenuItem);
        modeMenu.add(lightMenuItem);
        modeMenu.add(darkMenuItem);
        modeMenu.add(deMenuItem);
        
        drawPanel.setPreferredSize(new Dimension(1000, 1000));
        drawPanel.setBackground(Color.BLACK);
        GridBagConstraints gridConstraints = new GridBagConstraints();
        gridConstraints.gridx = 0;
        gridConstraints.gridy = 0;
        gridConstraints.gridheight = 2;
        gridConstraints.insets = new Insets(10,10,10,10);
        getContentPane().add(drawPanel, gridConstraints);
        
        leftColorLabel.setPreferredSize(new Dimension(40,40));
        leftColorLabel.setOpaque(true);
        gridConstraints = new GridBagConstraints();
        gridConstraints.gridx = 1;
        gridConstraints.gridy = 0;
        gridConstraints.anchor = GridBagConstraints.NORTH;
        gridConstraints.insets = new Insets(10,5,10,10);
        getContentPane().add(leftColorLabel, gridConstraints);
        rightColorLabel.setPreferredSize(new Dimension(40,40));
        rightColorLabel.setOpaque(true);
        gridConstraints = new GridBagConstraints();
        gridConstraints.gridx = 2;
        gridConstraints.gridy = 0;
        gridConstraints.anchor = GridBagConstraints.NORTH;
        gridConstraints.insets = new Insets(10,5,10,10);
        getContentPane().add(rightColorLabel, gridConstraints);
        
        colorPanel.setPreferredSize(new Dimension(80,260));
        colorPanel.setBorder(BorderFactory.createTitledBorder("Colors"));
        colorPanel.setForeground(Color.WHITE);
        gridConstraints = new GridBagConstraints();
        gridConstraints.gridx = 1;
        gridConstraints.gridy = 1;
        gridConstraints.gridwidth = 2;
        gridConstraints.anchor = GridBagConstraints.NORTH;
        gridConstraints.insets = new Insets(10,10,10,10);
        getContentPane().add(colorPanel, gridConstraints);
        
        colorPanel.setLayout(new GridBagLayout());
        int j = 0;
        for (int i = 0; i < 14; i++)
        {
            colorLabel[i] = new JLabel();
            colorLabel[i].setPreferredSize(new Dimension(30,30));
            colorLabel[i].setOpaque(true);
            gridConstraints = new GridBagConstraints();
            gridConstraints.gridx = j;
            gridConstraints.gridy = i - j * 7;
            colorPanel.add(colorLabel[i], gridConstraints);
            if(i==6)
            {
                j++;
            }
            colorLabel[i].addMouseListener(new MouseAdapter()
            {
                public void mousePressed(MouseEvent e)
                {
                    colorMousePressed(e);
                }
            });
        }
        colorLabel[0].setBackground(Color.RED);
        colorLabel[1].setBackground(Color.ORANGE);
        colorLabel[2].setBackground(Color.YELLOW);
        colorLabel[3].setBackground(Color.GREEN);
        colorLabel[4].setBackground(Color.BLUE);
        colorLabel[5].setBackground(Color.MAGENTA);
        colorLabel[6].setBackground(Color.GRAY);
        colorLabel[7].setBackground(Color.BLACK);
        colorLabel[8].setBackground(Color.PINK);
        colorLabel[9].setBackground(Color.CYAN);
        colorLabel[10].setBackground(Color.LIGHT_GRAY);
        colorLabel[11].setBackground(Color.DARK_GRAY);
        colorLabel[12].setBackground(Color.WHITE);
        Color C = new Color(122,50,150);
        colorLabel[13].setBackground(C);
        
        leftColor = colorLabel[0].getBackground();
        leftColorLabel.setBackground(leftColor);
        rightColor = colorLabel[12].getBackground();
        rightColorLabel.setBackground(rightColor);
        
        
        newMenuItem.addActionListener(new ActionListener()
        {
        	public void actionPerformed (ActionEvent e) {
        		newMenuItemActionPerformed(e);
        	}
        });
        
        tinyMenuItem.addActionListener(new ActionListener()
        {
        	public void actionPerformed (ActionEvent e) {
        		tinyMenuItemActionPerformed(e);
        	}
        });
        
        smallMenuItem.addActionListener(new ActionListener()
        {
        	public void actionPerformed (ActionEvent e) {
        		smallMenuItemActionPerformed(e);
        	}
        });
        
        medMenuItem.addActionListener(new ActionListener()
        {
        	public void actionPerformed (ActionEvent e) {
        		medMenuItemActionPerformed(e);
        	}
        });
        
        largeMenuItem.addActionListener(new ActionListener()
        {
        	public void actionPerformed (ActionEvent e) {
        		largeMenuItemActionPerformed(e);
        	}
        });
        
        giantMenuItem.addActionListener(new ActionListener()
        {
        	public void actionPerformed (ActionEvent e) {
        		giantMenuItemActionPerformed(e);
        	}
        });
        
        defaultMenuItem.addActionListener(new ActionListener()
        {
        	public void actionPerformed (ActionEvent e) {
        		defaultMenuItemActionPerformed(e);
        	}
        });
        
        greyMenuItem.addActionListener(new ActionListener()
        {
        	public void actionPerformed (ActionEvent e) {
        		greyMenuItemActionPerformed(e);
        	}
        });
        
        whiteMenuItem.addActionListener(new ActionListener()
        {
        	public void actionPerformed (ActionEvent e) {
        		whiteMenuItemActionPerformed(e);
        	}
        });
        
        darkMenuItem.addActionListener(new ActionListener()
        {
        	public void actionPerformed (ActionEvent e) {
        		darkMenuItemActionPerformed(e);
        	}
        });
        
        lightMenuItem.addActionListener(new ActionListener()
        {
        	public void actionPerformed (ActionEvent e) {
        		lightMenuItemActionPerformed(e);
        	}
        });
        
        deMenuItem.addActionListener(new ActionListener()
        {
        	public void actionPerformed (ActionEvent e) {
        		deMenuItemActionPerformed(e);
        	}
        });
        
        greenMenuItem.addActionListener(new ActionListener()
        {
        	public void actionPerformed (ActionEvent e) {
        	greenMenuItemActionPerformed(e);
        	}
        });
        
        blueMenuItem.addActionListener(new ActionListener()
        {
        	public void actionPerformed (ActionEvent e) {
        		blueMenuItemActionPerformed(e);
        	}
        });
        
        exitMenuItem.addActionListener(new ActionListener()
        {
        	public void actionPerformed (ActionEvent e) {
        		exitMenuItemActionPerformed(e);
        	}
        });
        
        drawPanel.addMouseListener(new MouseAdapter() {
        	public void mousePressed (MouseEvent e) {
        		drawPanelMousePressed(e);
        	}
        });
        drawPanel.addMouseMotionListener(new MouseMotionAdapter() {
        	public void mouseDragged (MouseEvent e) {
        		drawPanelMouseDragged(e);
        	}

        });
        drawPanel.addMouseListener(new MouseAdapter() {
        	public void mouseReleased (MouseEvent e) {
        		drawPanelMouseReleased(e);
        	}
        });
        
        
       
        
        pack();
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        setBounds((int) (0.5 * (screenSize.width - getWidth())), (int) (0.5 * (screenSize.height - getHeight())),getWidth(),getHeight());
        g2D = (Graphics2D) drawPanel.getGraphics();
        
        
    }
    
    
    private void drawPanelMousePressed(MouseEvent e) {
		if (e.getButton() == MouseEvent.BUTTON1 || 
				e.getButton() == MouseEvent.BUTTON3) {
			xPrevious = e.getX();
			yPrevious = e.getY();
			if (e.getButton() == MouseEvent.BUTTON1) {
				drawColor = leftColor;
			}
			else {
				drawColor = rightColor;
			}
		}
	}
    
	private void drawPanelMouseDragged(MouseEvent e) {
		System.out.println("dragged is running");
		Line2D.Double myLine = new Line2D.Double(xPrevious, yPrevious, 
				e.getX(), e.getY());
		g2D.setPaint(drawColor);
		g2D.draw(myLine);
		xPrevious = e.getX();
		yPrevious = e.getY();
		System.out.println(xPrevious + "," + yPrevious);
	}
	
	private void drawPanelMouseReleased(MouseEvent e) {
		if (e.getButton() == MouseEvent.BUTTON1 || 
				e.getButton() == MouseEvent.BUTTON3) {
			Line2D.Double myLine = new Line2D.Double(xPrevious, yPrevious, 
					e.getX(), e.getY());
			g2D.setPaint(drawColor);
			g2D.draw(myLine);
		}
	}

	private void colorMousePressed(MouseEvent e)
    {
        Component clickedColor = e.getComponent();
        Toolkit.getDefaultToolkit().beep();
        if (e.getButton() == MouseEvent.BUTTON1)
        {
            leftColor = clickedColor.getBackground();
            leftColorLabel.setBackground(leftColor);
        }else if (e.getButton() == MouseEvent.BUTTON3)
        {
            rightColor = clickedColor.getBackground();
            rightColorLabel.setBackground(rightColor);
        }
    }
    
    private void newMenuItemActionPerformed (ActionEvent e) {
    	int response;
    	response = JOptionPane.showConfirmDialog(null, "Are you sure you want to "
    			+ "start a new drawing?", "New Drawing", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
    	if (response == JOptionPane.YES_OPTION) {
    		g2D.setPaint(drawPanel.getBackground());
    		g2D.fill(new Rectangle2D.Double(0, 0, drawPanel.getWidth(), drawPanel.getHeight()));
    	}
    }
    
    private void exitMenuItemActionPerformed (ActionEvent e) {
    	int response;
    	response = JOptionPane.showConfirmDialog(null, "Are you sure you want to "
    			+ "exit the BlackBoard program?", "Exit Program", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
    	if (response == JOptionPane.NO_OPTION) {
    		return;
    	}
    	else {
    		exitForm(null);
    	}
    	
    }
    
    private void tinyMenuItemActionPerformed (ActionEvent e) {
    	g2D.setStroke(new BasicStroke(0.5F));
    }
    
    private void giantMenuItemActionPerformed (ActionEvent e) {
    	g2D.setStroke(new BasicStroke(45F));
    }
    
    private void smallMenuItemActionPerformed (ActionEvent e) {
    	g2D.setStroke(new BasicStroke(5F));
    }
    
    private void medMenuItemActionPerformed (ActionEvent e) {
    	g2D.setStroke(new BasicStroke(15F));
    }
    
    private void largeMenuItemActionPerformed (ActionEvent e) {
    	g2D.setStroke(new BasicStroke(25F));
    }
    
    private void defaultMenuItemActionPerformed (ActionEvent e) {
    	drawPanel.setBackground(Color.BLACK);
    }

	private void greyMenuItemActionPerformed (ActionEvent e) {
		drawPanel.setBackground(Color.GRAY);
	}

	private void whiteMenuItemActionPerformed (ActionEvent e) {
		drawPanel.setBackground(Color.WHITE);
	}

	private void greenMenuItemActionPerformed (ActionEvent e) {
		drawPanel.setBackground(Color.GREEN);
	}

	private void blueMenuItemActionPerformed (ActionEvent e) {
		drawPanel.setBackground(Color.BLUE);
	}
    
	
	private void deMenuItemActionPerformed (ActionEvent e) {
    	Color backgroundColor = new Color(122,50,150);
        getContentPane().setBackground(backgroundColor);
    }
	
	
	private void lightMenuItemActionPerformed (ActionEvent e) {
		Color backgroundColor = new Color(255,255,255);
        getContentPane().setBackground(backgroundColor);
    }
	
	
	private void darkMenuItemActionPerformed (ActionEvent e) {
		Color backgroundColor = new Color(0,0,0);
        getContentPane().setBackground(backgroundColor);
    }
	
	
	
	
    private void exitForm(WindowEvent e)
    {
        g2D.dispose();
        System.exit(0);
    }
}








