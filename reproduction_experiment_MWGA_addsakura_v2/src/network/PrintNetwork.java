package network;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

import agent.*;

public class PrintNetwork {
	public static void Export(int n,Agent[] agents) {
		try {
			//出力先を作成する
			FileWriter fw = new FileWriter("D:\\work\\lab\\network\\matrix.csv", false);  //※１
			FileWriter fw2 = new FileWriter("D:\\work\\lab\\network\\network.csv", false);  //※１
			FileWriter fw3 = new FileWriter("D:\\work\\lab\\network\\table.csv", false);  //※１
			PrintWriter pw = new PrintWriter(new BufferedWriter(fw));
			PrintWriter pw2 = new PrintWriter(new BufferedWriter(fw2));
			PrintWriter pw3 = new PrintWriter(new BufferedWriter(fw3));

			int[][] matrix=new int[n][n];
            
            for(int i=0;i<n;i++) {
            	for(int j=0;j<n;j++) {
            		matrix[i][j]=0;
            	}
            }
            
            for(int i=0;i<n;i++) {
            	for(int j=0;j<agents[i].getListSize();j++) {
                	int tmp=agents[i].getAgentNumber(j);
                	matrix[i][tmp]=1;
                }
            }
			
			//ヘッダー書き込み
            pw.print(",");
            for(int i=0;i<n;i++) {
            	pw.print("Agent"+i);
            	pw.print(",");
            }
            pw.print("sum");
            pw.println();
        
			//値書き込み
            for(int i=0;i<n;i++) {
            	pw.print(i);
            	pw.print(",");
            	for(int j=0;j<n;j++) {
            		pw.print(matrix[i][j]);
            		pw.print(",");
            	}
            	pw.print(agents[i].getListSize());
            	pw.println();
            }
        
			//ファイルに書き出す
			pw.close();

			//終了メッセージを画面に出力する
			System.out.println("matrixの出力が完了しました。");
			
			//ヘッダー書き込み
            pw2.print("edge");
            pw2.print(",");
            pw2.print("From");
            pw2.print(",");
            pw2.print("To");
            pw2.println();
            
            //値書き込み
            int flg=0;
            for(int i=0;i<n;i++) {
            	for(int j=0;j<agents[i].getListSize();j++) {
            		if(i<agents[i].getAgentNumber(j)) {
            			pw2.print(flg);
                        pw2.print(",");
                        flg++;
                        pw2.print("Agent"+i);
                        pw2.print(",");
                        pw2.print("Agent"+agents[i].getAgentNumber(j));
                        pw2.println();
            		}
            	}
            }
            
            //ファイルに書き出す
			pw2.close();
			
			//終了メッセージを画面に出力する
			System.out.println("networkの出力が完了しました。");
			
			//ヘッダー書き込み
            pw3.print("Agent");
            pw3.print(",");
            pw3.print("sum");
            pw3.println();
            
            //値書き込み
            for(int i=0;i<n;i++) {
            	pw3.print("Agent"+i);
            	pw3.print(",");
            	pw3.print(agents[i].getListSize());
            	pw3.println();
            }
            
            //ファイルに書き出す
			pw3.close();
			
			//終了メッセージを画面に出力する
			System.out.println("tableの出力が完了しました。");


		} catch (IOException ex) {
			//例外時処理
			ex.printStackTrace();
		}
	}
}
