package core;

import simulation.*;

public class Launcher {

	public static void main(String[] args) {
		int n=1000;	//エージェント数;
		int n0=10;	//初期エージェント数
		int edge=10;	//エージェントあたりのエッヂ数
		long startTime=System.currentTimeMillis();
		Simulation.start(Seed._seeds[0],n,n0,edge);
		long endTime = System.currentTimeMillis();
		System.out.println("time：" + ((endTime - startTime)/1000) + " s");
		System.out.println("MWGA_sakur_v2");
	}
}
