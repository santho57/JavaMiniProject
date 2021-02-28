import java.io.*;
import java.lang.*;
import javax.swing.*;
import javax.sound.sampled.*;

public class MySound extends Thread {
	File fileName;
	AudioFileFormat.Type fileType;
	TargetDataLine line;
	JPanel mainPanel;
	
	public void file(String ww, JPanel mainPanel){
		fileName = new File(ww+".mp3");
		mainPanel = mainPanel;
	}
	
	public void run(){
		try{
			fileType = AudioFileFormat.Type.WAVE;
			AudioFormat format = new AudioFormat(16000,8,2,true,true);
			DataLine.Info info = new DataLine.Info(TargetDataLine.class,format);
			
			if(!AudioSystem.isLineSupported(info)){
				JOptionPane.showMessageDialog(mainPanel, "Line not supported");
			}
			line = (TargetDataLine)AudioSystem.getLine(info);
			line.open(format);
			line.start();
			AudioInputStream ais = new AudioInputStream(line);
			AudioSystem.write(ais, fileType, fileName);
		}
		catch(LineUnavailableException ex){}
		catch(IOException ex){}
		
	}
	
	public void stopRec(){
		line.stop();
		line.close();
	}
		

}
