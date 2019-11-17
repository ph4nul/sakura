package simulation;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

import agent.Agent;

public class PrintWorldScore {
	FileWriter fw;
	static PrintWriter pw;
	int world;
	
	public PrintWorldScore(int times,int w) {
		world=w;
		try {
			//o—Íæ‚ğì¬‚·‚é
			fw = new FileWriter("D:\\work\\lab\\game\\score\\score_"+times+"_d.csv", false);  //¦‚P
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
	
	
	public void writeScore(int times,int num,Agent[][] agents) {

		for(int i=0;i<world;i++) {
			pw.print("world"+i);
			pw.print(",");
			for(int j=0;j<num;j++) {
				pw.print(agents[i][j].getScore());
				pw.print(",");
			}
			pw.println();
		}
		
      
	
	}
	
	public void closeFile() {
		pw.close();
	}
}
