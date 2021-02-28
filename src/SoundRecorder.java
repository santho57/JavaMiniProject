import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.filechooser.*;

import java.awt.*;
import java.awt.event.*;

public class SoundRecorder extends JFrame implements ActionListener {
	
	JPanel mainPanel;
	JTextField pathFile;
	int selecti = 0;
	JButton selectPath, startButton, stopButton;
	String folderPath;
	JFileChooser selectFolder;
	
	MySound ms;
	
	public SoundRecorder(){
		mainPanel = new JPanel();
		mainPanel.setLayout(null);
		mainPanel.setBackground(Color.GRAY);
		Border border = BorderFactory.createTitledBorder("JSound REC");
		mainPanel.setBorder(border);
		this.add(mainPanel);
		
		pathFile = new JTextField("Select the Folder");
		pathFile.setBounds(15,80,180,20);
		pathFile.setEditable(false);
		mainPanel.add(pathFile);
		
		selectPath = new JButton("Select");
		selectPath.setBounds(200,80,70,20);
		mainPanel.add(selectPath);
		selectPath.addActionListener(this);
		
		startButton = new JButton("Start");
		startButton.setBounds(82,120,70,20);
		startButton.setBackground(Color.GREEN);
		mainPanel.add(startButton);
		startButton.addActionListener(this);
		
		stopButton = new JButton("Stop");
		stopButton.setBounds(158,120,70,20);
		stopButton.setBackground(Color.RED);
		stopButton.setEnabled(false);
		mainPanel.add(stopButton);
		stopButton.addActionListener(this);
		
		JLabel createrName = new JLabel("Created by SANTHOSH");
		createrName.setBounds(80, 220, 220, 20);
		createrName.setForeground(Color.WHITE);
		mainPanel.add(createrName);
		
		this.setSize(300,300);
		this.setLocation(500,200);
		this.setVisible(true);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		ms = new MySound();
		
	}
	
	public void save(){
		selectFolder = new JFileChooser("d:\\");
		selectFolder.setAcceptAllFileFilterUsed(false);
		selectFolder.setDialogTitle("Save file name");
		FileNameExtensionFilter restrict = new FileNameExtensionFilter("Audio format (*.mp3)","mp3");
		selectFolder.addChoosableFileFilter(restrict);
		selecti = selectFolder.showSaveDialog(null);
		
		try{
			if(selecti==JFileChooser.APPROVE_OPTION){
				folderPath = selectFolder.getSelectedFile().getAbsolutePath();
				pathFile.setText(folderPath+".mp3");
				selecti = 1;
				
				ms.file(folderPath,mainPanel);
			}	
		}
		catch(Exception ex){
		}
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		
		if(e.getSource()==selectPath)
			save();
		else if(e.getSource()==startButton){
			if(selecti>=0){
				ms.start();
				startButton.setEnabled(false);
				stopButton.setEnabled(true);
			}
			else{
				Toolkit.getDefaultToolkit().beep();
				JOptionPane.showMessageDialog(mainPanel,"Please select file name");
			}
		}
		else if(e.getSource()==stopButton){
			ms.stopRec();
			startButton.setEnabled(true);
			stopButton.setEnabled(false);
			selecti = 0;
		}
		
	}

	public static void main(String[] args) {
		
		new SoundRecorder();
		
	}

}
