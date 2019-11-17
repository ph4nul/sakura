package genetic_algorithm;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

import agent.Agent;

public class PrintGean {
	public static void makeFile(int times,int agent_num) {
		try {
			//出力先を作成する
			FileWriter fw = new FileWriter("D:\\work\\lab\\ga\\evolution"+times+".csv", false);  //※１
			PrintWriter pw = new PrintWriter(new BufferedWriter(fw));
			
			for(int i=0;i<agent_num;i++) {
				pw.print("Agent"+i);
				pw.print(",");
			}
			pw.println();
			
			//ファイルに書き出す
			pw.close();
		} catch (IOException ex) {
			//例外時処理
			ex.printStackTrace();
		}
	}
	
	
	public static void writeGean(int times,int agent_num,Agent[] agents) {
		try {
			//出力先を作成する
			FileWriter fw = new FileWriter("D:\\work\\lab\\ga\\evolution"+times+".csv", true);  //※１
			PrintWriter pw = new PrintWriter(new BufferedWriter(fw));
			
			for(int i=0;i<agent_num;i++) {
				pw.print(agents[i].getAgentValueBL(0)/7);
				pw.print(",");
			}
			pw.println();
			
			for(int i=0;i<agent_num;i++) {
				pw.print(agents[i].getAgentValueBL(1)/7);
				pw.print(",");
			}
			pw.println();
			
			
			//ファイルに書き出す
			pw.close();
		} catch (IOException ex) {
			//例外時処理
			ex.printStackTrace();
		}
	}
}
