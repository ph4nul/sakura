package general_metanorms_game;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

import agent.*;

public class PrintScoreOfGame_d {
	
	FileWriter fw;
	static PrintWriter pw;
	
	public PrintScoreOfGame_d(int times,int world) {
		try {
			//o—Íæ‚ğì¬‚·‚é
			fw = new FileWriter("D:\\work\\lab\\game\\score\\score_"+times+"_d_"+world+".csv", false);  //¦‚P
			pw = new PrintWriter(new BufferedWriter(fw));
			
		} catch (IOException ex) {
			//—áŠOˆ—
			ex.printStackTrace();
		}
	}
	
	public void makeFile(int times,int num) {
		pw.print(",");
		for(int i=0;i<num;i++) {
			pw.print("Agent_"+i);
			pw.print(",");
		}
		pw.println();
  
	}
	
	public void writeLap(int lap,int times) {
		pw.println("lap_"+lap);
		
		

	}
	
	public void writeScore(int phase,int times,int num,Agent[] agents) {
		pw.print(phase);
		pw.print(",");
		for(int i=0;i<num;i++) {
			pw.print(agents[i].getScore());
			pw.print(",");
		}
		pw.println();
      

	}
	
	public void closeFile() {
		pw.close();
	}
	
}
