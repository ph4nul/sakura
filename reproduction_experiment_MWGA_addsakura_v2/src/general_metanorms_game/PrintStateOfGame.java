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
			//�o�͐���쐬����
			fw = new FileWriter("D:\\work\\lab\\game\\yazirusi_game"+times+".txt", false);  //���P
			pw = new PrintWriter(new BufferedWriter(fw));
			
		} catch (IOException ex) {
			//��O������
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
      
		//�t�@�C���ɏ����o��
	}
	
	public  void writeBetrayal(int num,int times,Agent[] agents) {
		pw.println(num+" betrayed!");
      
		//�t�@�C���ɏ����o��
	}
	
	public  void writeFind(int numi,int numj,int times,Agent[] agents) {
		pw.print("           "+numj+" find "+numi+".");
      
		//�t�@�C���ɏ����o��

	}
	
	public  void writeNotFind(int numi,int numj,int times,Agent[] agents) {
		pw.println("           "+numj+" not find "+numi+"!");
      
		//�t�@�C���ɏ����o��

	}
	
	public  void writeComment(int numi,int numj,int times,Agent[] agents) {
		pw.println(numj+" comment "+numi+"!");
      
		//�t�@�C���ɏ����o��

	}
	
	public  void writeNotComment(int numi,int numj,int times,Agent[] agents) {
		pw.println(numj+" not comment "+numi+".");
      
		//�t�@�C���ɏ����o��
		
	}
	
	public  void writeMetaComment(int numi,int numj,int times,Agent[] agents) {
		pw.println(numj+" metacomment "+numi+"!");
      
		//�t�@�C���ɏ����o��
		
	}
	
	public  void writeNotMetaComment(int numi,int numj,int times,Agent[] agents) {
		pw.println(numj+" not metacomment "+numi+".");
      
		//�t�@�C���ɏ����o��
		//pw.close();
	}
	
	public  void writePunish1(int numi,int numj,int times,Agent[] agents) {
		pw.println(numj+" punish(1) "+numi+"!");
      
		//�t�@�C���ɏ����o��
		
	}
	
	public  void writeNotPunish1(int numi,int numj,int times,Agent[] agents) {
		pw.println(numj+" not punish(1) "+numi+".");
      
		//�t�@�C���ɏ����o��
		
	}
	
	public  void writeFind2(int numi,int numj,int times,Agent[] agents) {
		pw.print("                                      "+numj+" find "+numi+".");
      
		//�t�@�C���ɏ����o��
		
	}
	
	public  void writeNotFind2(int numi,int numj,int times,Agent[] agents) {
		pw.println("                                      "+numj+" not find "+numi+"!");
      
		//�t�@�C���ɏ����o��
		
	}
	
	public  void writePunish2(int numi,int numj,int times,Agent[] agents) {
		pw.println(numj+" punish(2) "+numi+"!");
      
		//�t�@�C���ɏ����o��
		
	}
	
	public  void writeNotPunish2(int numi,int numj,int times,Agent[] agents) {
		pw.println(numj+" not punish(2) "+numi+"!");
      
		//�t�@�C���ɏ����o��
		
	}
	
	public  void writeEnd(int times) {
		pw.println();
      
		//�t�@�C���ɏ����o��
		
	}
	
	public  void writeLine(int times) {
		pw.println("---------------");
      
		//�t�@�C���ɏ����o��
		//pw.close();
	}
	
	public void closeFile() {
		pw.close();
	}
	
}
