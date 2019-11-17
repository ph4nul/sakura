package network;

import agent.*;
import random.*;

public class CompleteGraph {
	private MakeRnd rnd;
	private Agent[] agents;
	private int n;
	private int n0;
	private int edge;

	public CompleteGraph(Agent[] Agents,int num,int num0,int e,MakeRnd r){
		agents=Agents;
		n=num;
		n0=num0;
		edge=e;
		rnd=r;
	}

	public void CompleteGraphModel() {
		int allofedge=0;

		for(int i=0;i<n0;i++){ //‰Šúó‘Ô
			for(int j=0;j<n0;j++){
				if(i==j) continue;
				agents[i].addAgentNumber(j);
			}
	  }
	  allofedge=(n0-1)*n0;

	  //PrintNetwork.Export(n,agents);
	}
}
