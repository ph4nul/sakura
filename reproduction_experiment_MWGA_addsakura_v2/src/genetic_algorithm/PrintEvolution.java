package genetic_algorithm;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

import agent.Agent;

public class PrintEvolution {
	public static void makeFile(int i,int times) {
		try {
			//出力先を作成する
			FileWriter fw = new FileWriter("D:\\work\\lab\\ga\\evolution"+times+"-"+i+".txt", false);  //※１
			PrintWriter pw = new PrintWriter(new BufferedWriter(fw));
			
			pw.println("Game"+times+"evolution");
			pw.println("----------");
        
			//ファイルに書き出す
			pw.close();
		} catch (IOException ex) {
			//例外時処理
			ex.printStackTrace();
		}
	}
	
	public static void writeWeight(int times,int agent_num,int n,double[] probability,Agent[] agents) {
		try {
			//出力先を作成する
			FileWriter fw = new FileWriter("D:\\work\\lab\\ga\\evolution"+times+"-"+agent_num+".txt", true);  //※１
			PrintWriter pw = new PrintWriter(new BufferedWriter(fw));
			
			pw.println("Weight");
			
			/*for(int i=0;i<agents[agent_num].getListSize();i++) {
				 pw.println("agent"+agents[agent_num].getAgentNumber(i)+"->"+probability[i]);
			}*/
			
			for(int i=0;i<n;i++) {
				 pw.println("agent"+i+"->"+probability[i]);
			}
			
			pw.println("----------");
			//ファイルに書き出す
			pw.close();
		} catch (IOException ex) {
			//例外時処理
			ex.printStackTrace();
		}
	}
	
	public static void writeProbability(int times,int agent_num,int n,double[] probability,Agent[] agents) {
		try {
			//出力先を作成する
			FileWriter fw = new FileWriter("D:\\work\\lab\\ga\\evolution"+times+"-"+agent_num+".txt", true);  //※１
			PrintWriter pw = new PrintWriter(new BufferedWriter(fw));
			
			pw.println("Probability");
			
			/*for(int i=0;i<agents[agent_num].getListSize();i++) {
				 pw.println("agent"+agents[agent_num].getAgentNumber(i)+"->"+probability[i]);
			}*/
			
			for(int i=0;i<n;i++) {
				 pw.println("agent"+i+"->"+probability[i]);
			}
			
			pw.println("----------");
			//ファイルに書き出す
			pw.close();
		} catch (IOException ex) {
			//例外時処理
			ex.printStackTrace();
		}
	}
	
	public static void writeEvolution(int times,int agent_num,int[] parents,int[][] tmp_child,Agent[] agents) {
		try {
			//出力先を作成する
			FileWriter fw = new FileWriter("D:\\work\\lab\\ga\\evolution"+times+"-"+agent_num+".txt", true);  //※１
			PrintWriter pw = new PrintWriter(new BufferedWriter(fw));
			
			//エージェントiは親にAとBを選んで、111111に進化した。

			pw.println("Agent"+agent_num+" chose "+parents[0]+" and "+parents[1]);
			for(int i=0;i<6;i++) {
				pw.print(agents[agent_num].getBV(i));
			}
			pw.println();
			
			for(int i=0;i<6;i++) {
				pw.print(tmp_child[agent_num][i]);
			}
			pw.println();
			
			pw.println("----------");
			
        
			//ファイルに書き出す
			pw.close();
		} catch (IOException ex) {
			//例外時処理
			ex.printStackTrace();
		}
	}
}
