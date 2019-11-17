package genetic_algorithm;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

import agent.Agent;

public class PrintGean {
	public static void makeFile(int times,int agent_num) {
		try {
			//�o�͐���쐬����
			FileWriter fw = new FileWriter("D:\\work\\lab\\ga\\evolution"+times+".csv", false);  //���P
			PrintWriter pw = new PrintWriter(new BufferedWriter(fw));
			
			for(int i=0;i<agent_num;i++) {
				pw.print("Agent"+i);
				pw.print(",");
			}
			pw.println();
			
			//�t�@�C���ɏ����o��
			pw.close();
		} catch (IOException ex) {
			//��O������
			ex.printStackTrace();
		}
	}
	
	
	public static void writeGean(int times,int agent_num,Agent[] agents) {
		try {
			//�o�͐���쐬����
			FileWriter fw = new FileWriter("D:\\work\\lab\\ga\\evolution"+times+".csv", true);  //���P
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
			
			
			//�t�@�C���ɏ����o��
			pw.close();
		} catch (IOException ex) {
			//��O������
			ex.printStackTrace();
		}
	}
}
