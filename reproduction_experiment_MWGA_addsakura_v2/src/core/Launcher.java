package core;

import simulation.*;

public class Launcher {

	public static void main(String[] args) {
		int n=1000;	//�G�[�W�F���g��;
		int n0=10;	//�����G�[�W�F���g��
		int edge=10;	//�G�[�W�F���g������̃G�b�a��
		long startTime=System.currentTimeMillis();
		Simulation.start(Seed._seeds[0],n,n0,edge);
		long endTime = System.currentTimeMillis();
		System.out.println("time�F" + ((endTime - startTime)/1000) + " s");
		System.out.println("MWGA_sakur_v2");
	}
}
