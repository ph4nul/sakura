package general_metanorms_game;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

import agent.*;

public class PrintScoreOfGame {
	
	FileWriter fw;
	static PrintWriter pw;
	
	public PrintScoreOfGame(int times) {
		try {
			//出力先を作成する
			fw = new FileWriter("D:\\work\\lab\\game\\score\\score_"+times+".csv", false);  //※１
			pw = new PrintWriter(new BufferedWriter(fw));
			
		} catch (IOException ex) {
			//例外時処理
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
