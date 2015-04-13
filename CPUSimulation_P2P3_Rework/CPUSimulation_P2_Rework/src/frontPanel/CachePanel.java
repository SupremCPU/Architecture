package frontPanel;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GridLayout;
import java.awt.Stroke;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;

import javax.swing.JCheckBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.TitledBorder;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import companent.CPU;
import companent.Cache;
import caculation.Util;





public class CachePanel extends JPanel{

	/**
	 * @author you zhao
	 */
	private static final long serialVersionUID = 3969040766682417743L;
	public static final int CACHE_CAP = 0x8;
	public static final int CACHE_SIZE =0x10;

	
	private Color defaultColor = Color.gray;
	private Color newColor = Color.magenta;
	private int blockWidth = 176/CACHE_CAP;
	private int blockHeight = 208/CACHE_SIZE;
	private int initX =70;
	private int initY =70;
	private int colorLevel = 2;
	private int levitationX,levitationY =-1;
	
	private JCheckBox cacheAct;
	private JLabel chooseCell;
	private String hintMessage = "Show value if stay";
	private JLabel hitOrMiss;
	

	private Color getColorLevel(int _level){
		if(_level == Cache.R_NOT_UESED)
			return defaultColor;
		if(_level == Cache.R_JUST_NEW)
			return newColor;
		return new Color(10,40+_level*colorLevel,220-_level*colorLevel);
	}
	
	private void getLevitationCell(int _levitationX,int _levitationY){
		
		levitationX = (_levitationX-initX)/blockWidth;
		if(_levitationX<=initX||levitationX>=CACHE_CAP)
			levitationX = -1;
			
		levitationY = (_levitationY-initY)/blockHeight;
		if(_levitationY<=initY||levitationY>=CACHE_SIZE)
			levitationY = -1;
		
	}
	public CachePanel(){
		super(new GridLayout(),true);
		this.setBorder(new TitledBorder("Cache"));
		
		this.setLayout(null);
		
		this.cacheAct = new JCheckBox("Enable Cache");
		this.cacheAct.setSelected(true);
		this.cacheAct.setBounds(10, 20, 150, 20);
		
		//cacheAct.setEnabled(true);
		this.add(cacheAct);
		
		hitOrMiss = new JLabel("HitMiss");
		hitOrMiss.setFont(new Font("Helvetica", Font.BOLD, 14));
		//hitOrMiss.setFont(getFont().deriveFont(10f));
		this.add(hitOrMiss);
		hitOrMiss.setBounds(20, 36, 250, 20);
		
		chooseCell = new JLabel(hintMessage);
		chooseCell.setFont(new Font("Helvetica", Font.BOLD, 14));
		//chooseCell.setFont(getFont().deriveFont(10f));
		this.add(chooseCell);
		chooseCell.setBounds(20, 50, 250, 20);
		
		//this.setSize(200, 300);
		
		
		
		this.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		cacheAct.addChangeListener(
				new ChangeListener(){

					@Override
					public void stateChanged(ChangeEvent e) {
						//CPU.getInstance().setCahceEnable(cacheAct.isSelected());
					}
					
				});
		
		this.addMouseMotionListener(
				new MouseMotionListener() {
					
					@Override
					public void mouseMoved(MouseEvent e) {
						// TODO Auto-generated method stub
						getLevitationCell(e.getX(),e.getY());
						if(levitationX>=0&&levitationY>=0){
							setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
						}else{
							setCursor(Cursor.getDefaultCursor());
						}
						update();
					}
					
					@Override
					public void mouseDragged(MouseEvent e) {
						// TODO Auto-generated method stub
						
					}
				});
	}
	
	
	
	@Override
	public void paint(Graphics graph) {
		// TODO Auto-generated method stub
		super.paint(graph);
		
		int[][]records = Cache.getInstance().getCacheRecord();
		
		for(int i=0;i<CACHE_SIZE;i++){
			
			graph.setColor(Color.black);
			graph.setFont(getFont().deriveFont(9.5f));
			graph.drawString("Line "+i, 20, initY+(i+1)*blockHeight-2);
			
			for(int j=0;j<CACHE_CAP;j++){
				graph.setColor(getColorLevel(records[i][j]));
				graph.fillRect(initX+j*blockWidth, initY+i*blockHeight, blockWidth-1, blockHeight-1);
			}
		}
		
		Graphics2D g2d=(Graphics2D)graph;
		Stroke stroke=new BasicStroke(2.0f);
		g2d.setStroke(stroke);
			
		/*
		 * set hovered block selected
		 */
		g2d.setColor(Color.red);
		if(initX>=0&&initY>=0){
			g2d.drawRect(initX+levitationX*blockWidth-1, initY+levitationY*blockHeight-1, blockWidth, blockHeight);
		}
		//System.out.println("test1");
		//g.drawLine(0, 0, 100, 100);
	}
	


	public void update(){
		this.repaint();
		if(levitationX>=0&&levitationY>=0){
			chooseCell.setText("L"+levitationY+"W"+levitationX+":"+
					Util.getBinaryStringFromBits(Cache.getInstance().getWordByLineAndOffset(levitationY, levitationX).getData()));
		}
		else{
			chooseCell.setText(hintMessage);
		}
		hitOrMiss.setText(Cache.getInstance().getRateInfo());
		
	}
	

		
	}


