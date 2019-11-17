package general_metanorms_game;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

import agent.Agent;

public class PrintStateOfGame {
	FileWriter fw;
	static PrintWriter pw;
	
	public PrintStateOfGame(int times) {
		try {
			//出力先を作成する
			fw = new FileWriter("D:\\work\\lab\\game\\yazirusi_game"+times+".txt", false);  //※１
			pw = new PrintWriter(new BufferedWriter(fw));
			
		} catch (IOException ex) {
			//例外時処理
			ex.printStackTrace();
		}
	}
	
	public void makeFile(int times) {
		pw.println("Game"+times+"start!!");
		pw.println("----------");
      
		
	}
	
	public void writeLap(int lap,int times) {
		pw.println("lap_"+lap);
		pw.println("----------");

	}
	
	public  void writeAgent(int num,int times,Agent[] agents) {
		pw.println("Agent"+num);
		pw.println("-----");
      
		
	}
	
	public  void writeCoordination(int num,int times,Agent[] agents) {
		pw.println(num+" cooperated.");
      
		//ファイルに書き出す
	}
	
	public  void writeBetrayal(int num,int times,Agent[] agents) {
		pw.println(num+" betrayed!");
      
		//ファイルに書き出す
	}
	
	public  void writeFind(int numi,int numj,int times,Agent[] agents) {
		pw.print("           "+numj+" find "+numi+".");
      
		//ファイルに書き出す

	}
	
	public  void writeNotFind(int numi,int numj,int times,Agent[] agents) {
		pw.println("           "+numj+" not find "+numi+"!");
      
		//ファイルに書き出す

	}
	
	public  void writeComment(int numi,int numj,int times,Agent[] agents) {
		pw.println(numj+" comment "+numi+"!");
      
		//ファイルに書き出す

	}
	
	public  void writeNotComment(int numi,int numj,int times,Agent[] agents) {
		pw.println(numj+" not comment "+numi+".");
      
		//ファイルに書き出す
		
	}
	
	public  void writeMetaComment(int numi,int numj,int times,Agent[] agents) {
		pw.println(numj+" metacomment "+numi+"!");
      
		//ファイルに書き出す
		
	}
	
	public  void writeNotMetaComment(int numi,int numj,int times,Agent[] agents) {
		pw.println(numj+" not metacomment "+numi+".");
      
		//ファイルに書き出す
		//pw.close();
	}
	
	public  void writePunish1(int numi,int numj,int times,Agent[] agents) {
		pw.println(numj+" punish(1) "+numi+"!");
      
		//ファイルに書き出す
		
	}
	
	public  void writeNotPunish1(int numi,int numj,int times,Agent[] agents) {
		pw.println(numj+" not punish(1) "+numi+".");
      
		//ファイルに書き出す
		
	}
	
	public  void writeFind2(int numi,int numj,int times,Agent[] agents) {
		pw.print("                                      "+numj+" find "+numi+".");
      
		//ファイルに書き出す
		
	}
	
	public  void writeNotFind2(int numi,int numj,int times,Agent[] agents) {
		pw.println("                                      "+numj+" not find "+numi+"!");
      
		//ファイルに書き出す
		
	}
	
	public  void writePunish2(int numi,int numj,int times,Agent[] agents) {
		pw.println(numj+" punish(2) "+numi+"!");
      
		//ファイルに書き出す
		
	}
	
	public  void writeNotPunish2(int numi,int numj,int times,Agent[] agents) {
		pw.println(numj+" not punish(2) "+numi+"!");
      
		//ファイルに書き出す
		
	}
	
	public  void writeEnd(int times) {
		pw.println();
      
		//ファイルに書き出す
		
	}
	
	public  void writeLine(int times) {
		pw.println("---------------");
      
		//ファイルに書き出す
		//pw.close();
	}
	
	public void closeFile() {
		pw.close();
	}
	
}
