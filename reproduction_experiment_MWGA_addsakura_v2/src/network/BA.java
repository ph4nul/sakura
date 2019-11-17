package network;

import agent.*;
import random.*;

public class BA {
	private MakeRnd rnd;
	private Agent[] agents;
	private Agent[][] agents_d;
	private int n;
	private int n0;
	private int edge;
	private int world;
	
	public BA(Agent[] Agents,Agent[][] Agents_d,int num,int num0,int e,int w,MakeRnd r){
		agents=Agents;
		agents_d=Agents_d;
		n=num;
		n0=num0;
		edge=e;
		world=w;
		rnd=r;
	}
	
	public void BAmodel() {
		int allofedge=0;
		
		for(int i=0;i<n0;i++){ //初期状態
			for(int j=0;j<n0;j++){
				if(i==j) continue;
				agents[i].addAgentNumber(j);
				for(int k=0;k<world;k++) {
					agents_d[k][i].addAgentNumber(j);
				}
			}
	    }
	    allofedge=(n0-1)*n0;
	    
	    for(int i=n0;i<n;i++){ //成長
	    	double[] weight=new double[i];
	        double totalweight=0;
	        double[] probability=new double[i];
	        for(int j=0;j<i;j++){// 重み
	        	weight[j]=(double)agents[j].getListSize()/(double)allofedge;
	        	totalweight+=weight[j];
	        }

	        for(int j=0;j<edge;j++){ //抽選回数edge回繰り返し
	        	for(int k=0;k<i;k++){// 重みの正規化
	        		if(k==0){
	        			probability[k]=weight[k]/totalweight;
	        		}
	        		else{
	        			probability[k]=probability[k-1]+weight[k]/totalweight;
	        		}
	        	}
	            
	            //double rnd = Math.random();
	        	double lottery=rnd.getRandomNumber();
	            for(int k=0;k<i;k++){
	            	if(lottery<probability[k]){//決定
	            		totalweight-=weight[k];
	            		weight[k]=0;
	            		agents[i].addAgentNumber(k);
	            		agents[k].addAgentNumber(i);
	            		for(int l=0;l<world;l++) {
	            			agents_d[l][i].addAgentNumber(k);
		            		agents_d[l][k].addAgentNumber(i);
	            		}
	            		allofedge+=2;
	            		break;
	            	}
	            }
	            //出力
	            /*for(int k=0;k<i;k++) {
	            	System.out.println(k+":"+weight[k]);
	            }
	            System.out.println("==========");*/
	          }
	          //出力
	         /* System.out.println("origin");
	          for(int j=0;j<=i;j++) {
	          	System.out.print(j+":");
	          	for(int k=0;k<agents[j].getListSize();k++) {
	          		System.out.print(agents[j].getAgentNumber(k)+" ");
	          	}
	          	System.out.println("");
	          }
	          System.out.println("--------------------");
	          
	          for(int x=0;x<world;x++) {
	        	  System.out.println("world "+x);
	        	  for(int j=0;j<=i;j++) {
		          	System.out.print(j+":");
		          	for(int k=0;k<agents[j].getListSize();k++) {
		          		System.out.print(agents[j].getAgentNumber(k)+" ");
		          	}
		          	System.out.println("");
		          }
		          System.out.println("--------------------");
	          }*/
	         
	    	}	
	    PrintNetwork.Export(n,agents);   
	}
}
