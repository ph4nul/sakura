package simulation;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

import agent.*;

public class CalculationBVL {
	public static void SetHeader() {
		try {
			//出力先を作成する
			FileWriter fw = new FileWriter("D:\\work\\lab\\simulation\\BVLaverage.csv", false);  //※１
			PrintWriter pw = new PrintWriter(new BufferedWriter(fw));
        
			//ヘッダー書き込み
			pw.print("Turn");
			pw.print(",");
			pw.print("B(BV)");
			pw.print(",");
			pw.print("V");
			pw.print(",");
			pw.print("B(BL)");
			pw.print(",");
			pw.print("L");
			pw.println();
        
			//ファイルに書き出す
			pw.close();

			

		} catch (IOException ex) {
			//例外時処理
			ex.printStackTrace();
		}
	}
	
	public static void Export(Agent[] agents,int n,int times) {
		try {
			//出力先を作成する
			FileWriter fw = new FileWriter("D:\\work\\lab\\simulation\\BVLaverage.csv", true);  //※１
			PrintWriter pw = new PrintWriter(new BufferedWriter(fw));
        
			//ヘッダー書き込み
			
        
        
			//値書き込み
			pw.print(times);
			pw.print(",");
			
			double tmpB1=0;
			for(int i=0;i<n;i++) {
				tmpB1+=(agents[i].getAgentValueBV(0)/7);
			}
			pw.print(tmpB1/n);
			pw.print(",");
			
			double tmpV=0;
			for(int i=0;i<n;i++) {
				tmpV+=(agents[i].getAgentValueBV(1)/7);
			}
			pw.print(tmpV/n);
			pw.print(",");
			
			double tmpB2=0;
			for(int i=0;i<n;i++) {
				tmpB2+=(agents[i].getAgentValueBL(0)/7);
			}
			pw.print(tmpB2/n);
			pw.print(",");
			
			double tmpL=0;
			for(int i=0;i<n;i++) {
				tmpL+=(agents[i].getAgentValueBL(1)/7);
			}
			pw.print(tmpL/n);
			pw.println();
			
        
			//ファイルに書き出す
			pw.close();

			//終了メッセージを画面に出力する
			//System.out.println(name+"_"+times+"_"+steps+"("+num+"ターン目)の出力が完了しました。");

		} catch (IOException ex) {
			//例外時処理
			ex.printStackTrace();
		}
	}
}
