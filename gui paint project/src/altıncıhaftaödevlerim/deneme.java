package altıncıhaftaödevlerim;


import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Scanner;

import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JPanel;





public class deneme extends JFrame{
	private DrawingPanel dp;
	private JPanel jpBottom;
	private JPanel jpBottom1;
	private JPanel jpBottom2;
	private JButton saveButton;	
	private JButton deleteButton;
	private JButton openButton;
	private ArrayList<Point> points;
	private ArrayList<ArrayList<Point>> listOfLists;
	deneme(){
		jpBottom = new JPanel();
		jpBottom1 = new JPanel();
	    jpBottom2 = new JPanel();
		saveButton = new JButton("Save");
		saveButton.addActionListener(new SaveButtonListener());
		jpBottom.add(saveButton);
		deleteButton =new JButton("Delete");
		deleteButton.addActionListener(new DeleteButtonListener());
		jpBottom1.add(deleteButton);
		openButton =new JButton("OpenFile");
		openButton.addActionListener(new OpenButtonListener());
		jpBottom2.add(openButton);
		listOfLists = new ArrayList<ArrayList<Point>>();
		dp = new DrawingPanel();
		dp.addMouseListener(new MyMouseListener());
		dp.addMouseMotionListener(new MyMouseMotionListener());
		this.setLayout(new BorderLayout());
		this.add(dp, BorderLayout.CENTER);
		this.add(jpBottom1, BorderLayout.NORTH);
		this.add(jpBottom1, BorderLayout.EAST);
		this.add(jpBottom, BorderLayout.SOUTH);
		this.add(jpBottom2, BorderLayout.SOUTH);
		this.add(jpBottom, BorderLayout.WEST);
		deleteButton.setPreferredSize(new Dimension(70,30));
		this.setSize(600,400);		
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setVisible(true);
	}
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		deneme t = new deneme();
	}
	//inner class (nested class)
	//JPanel is used for two purposes: (1) a container
	//that contains buttons, textboxes, tables, etc...
	//(2) for drawing 
	class DrawingPanel extends JPanel{
		@Override
		public void paintComponent(Graphics g) {
			super.paintComponent(g);
			//g.drawString("Java String", 250, 200);
			for(int i = 0; i < listOfLists.size(); i++) {
				for(int j = 0; j < listOfLists.get(i).size()-1;j++) {
					g.drawLine(listOfLists.get(i).get(j).x, 
						listOfLists.get(i).get(j).y, 
						listOfLists.get(i).get(j+1).x, 
						listOfLists.get(i).get(j+1).y);
					
				}
			}
			
		}
	}
	class DeleteButtonListener implements ActionListener{
		
		public void actionPerformed(ActionEvent d) {
			
		}
	
	}
	
class OpenButtonListener implements ActionListener{
		
		public void actionPerformed(ActionEvent a) {
			
			JFileChooser z =new JFileChooser();
			z.showSaveDialog(null);
			File fk =z.getSelectedFile();
			
			
			try {
				listOfLists = new ArrayList<ArrayList<Point>>();
				listOfLists.add(new ArrayList());
				Scanner scan=new Scanner(fk);
				while(scan.hasNextLine()) {
					String arve =scan.nextLine();
					String[] ara= arve.split(",");
					listOfLists.get(0).add(new Point(Integer.parseInt(ara [0]),Integer.parseInt(ara[1])));
				}
	          dp.repaint();
			}
	          
		catch(IOException  w){
	            System.out.println("An error occured  while reading ");
	          }
			}
	
}
	class SaveButtonListener implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			JFileChooser jz = new JFileChooser();
			 
			// Open the save dialog
			jz.showSaveDialog(null);
			//after getting the file name save all the contents
			//of listOfLists in the file in such a way that
			//you given the file should be able to create
			//the same drawing on the drawingPanel
			File fc=jz.getSelectedFile();
			try {
				PrintWriter pw=new PrintWriter(fc);
			
				for(int i = 0; i < listOfLists.size(); i++) {
					for(int j = 0; j < listOfLists.get(i).size()-1;j++) {
						pw.printf("%d,%d\n",listOfLists.get(i).get(j).x, 
							listOfLists.get(i).get(j).y);
							
					}
				}
				pw.close();
			}
			
			catch(IOException w) {
				
			}
			}
		
	}
	class MyMouseMotionListener implements MouseMotionListener{

		@Override
		public void mouseDragged(MouseEvent e) {
			// TODO Auto-generated method stub
			/*for(int i = 0; i < points.size();i++)
				System.out.printf("X: %d, Y:%d\n",
					points.get(i).x, points.get(i).y);
			*/
			listOfLists.get(listOfLists.size()-1).add(
				new Point(e.getX(),e.getY()));
			dp.repaint();
			
		}

		@Override
		public void mouseMoved(MouseEvent e) {
			// TODO Auto-generated method stub
			//System.out.printf("x:%d, y:%d\n",e.getX(),e.getY());
		}
		
	}
	class MyMouseListener implements MouseListener{

		@Override
		public void mouseClicked(MouseEvent e) {
			// TODO Auto-generated method stub
			//System.out.printf("x:%d, y:%d\n",e.getX(),e.getY());
			
			//points.add(new Point(e.getX(),e.getY()));
		}

		@Override
		public void mousePressed(MouseEvent e) {
			// TODO Auto-generated method stub
			listOfLists.add(new ArrayList<Point>());
		}

		@Override
		public void mouseReleased(MouseEvent e) {
			// TODO Auto-generated method stub
			//dp.repaint();
		}

		@Override
		public void mouseEntered(MouseEvent e) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void mouseExited(MouseEvent e) {
			// TODO Auto-generated method stub
			
		}
		
	}
}

