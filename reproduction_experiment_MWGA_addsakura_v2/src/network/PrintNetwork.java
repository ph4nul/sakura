package network;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

import agent.*;

public class PrintNetwork {
	public static void Export(int n,Agent[] agents) {
		try {
			//�o�͐���쐬����
			FileWriter fw = new FileWriter("D:\\work\\lab\\network\\matrix.csv", false);  //���P
			FileWriter fw2 = new FileWriter("D:\\work\\lab\\network\\network.csv", false);  //���P
			FileWriter fw3 = new FileWriter("D:\\work\\lab\\network\\table.csv", false);  //���P
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
			
			//�w�b�_�[��������
            pw.print(",");
            for(int i=0;i<n;i++) {
            	pw.print("Agent"+i);
            	pw.print(",");
            }
            pw.print("sum");
            pw.println();
        
			//�l��������
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
        
			//�t�@�C���ɏ����o��
			pw.close();

			//�I�����b�Z�[�W����ʂɏo�͂���
			System.out.println("matrix�̏o�͂��������܂����B");
			
			//�w�b�_�[��������
            pw2.print("edge");
            pw2.print(",");
            pw2.print("From");
            pw2.print(",");
            pw2.print("To");
            pw2.println();
            
            //�l��������
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
            
            //�t�@�C���ɏ����o��
			pw2.close();
			
			//�I�����b�Z�[�W����ʂɏo�͂���
			System.out.println("network�̏o�͂��������܂����B");
			
			//�w�b�_�[��������
            pw3.print("Agent");
            pw3.print(",");
            pw3.print("sum");
            pw3.println();
            
            //�l��������
            for(int i=0;i<n;i++) {
            	pw3.print("Agent"+i);
            	pw3.print(",");
            	pw3.print(agents[i].getListSize());
            	pw3.println();
            }
            
            //�t�@�C���ɏ����o��
			pw3.close();
			
			//�I�����b�Z�[�W����ʂɏo�͂���
			System.out.println("table�̏o�͂��������܂����B");


		} catch (IOException ex) {
			//��O������
			ex.printStackTrace();
		}
	}
}
