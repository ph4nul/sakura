package simulation;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

import agent.*;

public class PrintGean {
	
	public static void Export(double B,double L,int n,int times,int Agent_num) {
		try {
			//�o�͐���쐬����
			FileWriter fw;
			if(times==0) {
				fw = new FileWriter("D:\\work\\lab\\gean\\gean-"+Agent_num+".csv", false);  //���P
			}
			else {
				fw = new FileWriter("D:\\work\\lab\\gean\\gean-"+Agent_num+".csv", true);  //���P
			}
			
			PrintWriter pw = new PrintWriter(new BufferedWriter(fw));
        
			if(times==0) {
				pw.print("B");
				pw.print(",");
				pw.print("L");
				pw.println();
			}
			
			pw.print(B);
			pw.print(",");
			pw.print(L);
			pw.println();


			//�t�@�C���ɏ����o��
			pw.close();

			//�I�����b�Z�[�W����ʂɏo�͂���
			//System.out.println(name+"_"+times+"_"+steps+"("+num+"�^�[����)�̏o�͂��������܂����B");

		} catch (IOException ex) {
			//��O������
			ex.printStackTrace();
		}
	}
	
	public static void ExportAll(Agent[][] agents,int n,int world,int times) {
		try {
			//�o�͐���쐬����
			FileWriter fw = new FileWriter("D:\\work\\lab\\gean\\gean-"+times+"-all.csv", false);  //���P
			PrintWriter pw = new PrintWriter(new BufferedWriter(fw));
        
			//�w�b�_�[��������
			pw.print("world");
			pw.print(",");
			for(int i=0;i<n;i++) {
				pw.print("Agent"+i);
				pw.print(",");
			}
			pw.println();
			
			for(int i=0;i<world;i++) {
				pw.print(i);
				pw.println();
				
				pw.print("B");
				pw.print(",");
				for(int j=0;j<n;j++) {
					pw.print(agents[i][j].getAgentValueBL(0)/7);
					pw.print(",");
				}
				pw.println();
				
				pw.print("L");
				pw.print(",");
				for(int j=0;j<n;j++) {
					pw.print(agents[i][j].getAgentValueBL(1)/7);
					pw.print(",");
				}
				pw.println();
				
			}

			//�t�@�C���ɏ����o��
			pw.close();

			//�I�����b�Z�[�W����ʂɏo�͂���
			//System.out.println(name+"_"+times+"_"+steps+"("+num+"�^�[����)�̏o�͂��������܂����B");

		} catch (IOException ex) {
			//��O������
			ex.printStackTrace();
		}
	}
	
	public static void ExportAverage(Agent[] agents,int n,double[] b,double[] l,int times) {
		try {
			//�o�͐���쐬����
			FileWriter fw = new FileWriter("D:\\work\\lab\\gean\\gean-average.csv", false);  //���P
			PrintWriter pw = new PrintWriter(new BufferedWriter(fw));
        
			//�w�b�_�[��������
			pw.print("");
			pw.print(",");
			pw.print("B");
			pw.print(",");
			pw.print("L");
			pw.println();
        
			
			
			for(int i=0;i<n;i++) {
				pw.print(i);
				pw.print(",");
				pw.print(b[i]/500);
				pw.print(",");
				pw.print(l[i]/500);
				pw.println();
			}

			//�t�@�C���ɏ����o��
			pw.close();

			//�I�����b�Z�[�W����ʂɏo�͂���
			//System.out.println(name+"_"+times+"_"+steps+"("+num+"�^�[����)�̏o�͂��������܂����B");

		} catch (IOException ex) {
			//��O������
			ex.printStackTrace();
		}
	}
	
	public static void ExportFinal(Agent[] agents,Agent[][] agents_d,int Agent_num,int world) {
		try {
			//�o�͐���쐬����
			
			
			FileWriter fw = new FileWriter("D:\\work\\lab\\geanfinal\\gean-"+Agent_num+".csv", false);  //���P
			PrintWriter pw = new PrintWriter(new BufferedWriter(fw));
			
			
		
			
			for(int i=0;i<world+1;i++) {
				if(i==0) {
					for(int j=0;j<6;j++) {
						pw.print(agents[Agent_num].getBL(j));
						pw.print(",");
					}
					pw.println();
				}
				else {
					
					for(int j=0;j<6;j++) {
						pw.print(agents_d[i-1][Agent_num].getBL(j));
						pw.print(",");
					}
					pw.println();
				}
				

			}
			

			//�t�@�C���ɏ����o��
			pw.close();

			//�I�����b�Z�[�W����ʂɏo�͂���
			//System.out.println(name+"_"+times+"_"+steps+"("+num+"�^�[����)�̏o�͂��������܂����B");

		} catch (IOException ex) {
			//��O������
			ex.printStackTrace();
		}
	}
	
}
