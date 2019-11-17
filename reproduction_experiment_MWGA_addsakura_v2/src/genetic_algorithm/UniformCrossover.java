package genetic_algorithm;

import agent.Agent;
import random.*;
import java.lang.Double;
import java.util.LinkedHashMap;
import java.util.Map;



public class UniformCrossover {
	int n;	//agent number
	
	private double[] weight;
	private int[][] tmp_child;	//できた子供を格納しておく
	//Agent[] agents;
	private Agent[] agents;
	private Agent[][] agents_d;
	private int world;
	
	public UniformCrossover(int num,int w,Agent[] a,Agent[][] a_d) {
		n=num;
		weight=new double[w+1];
		tmp_child=new int[w+1][6];
		agents=a;
		agents_d=a_d;
		world=w;
	}
	
	public void calculateWeight(int i) {
		double v_min=10000000;
		//System.out.println(agents[i].getListSize());
		
		if(v_min>agents[i].getScore()) {
			v_min=agents[i].getScore();
		}
		
		for(int j=0;j<world;j++) {
			if(v_min>agents_d[j][i].getScore()) {
				v_min=agents_d[j][i].getScore();
			}
		}
		//System.out.println("Agent"+i+" "+v_min);
		
		for(int x=0;x<world+1;x++) {	//重み初期化
			weight[x]=0;
		}
			
		double v_child=0.0;
		double v_parent=0.0;
		v_child=Math.pow((agents[i].getScore()-v_min), 2);	//重みの計算（エージェントi）
		
		for(int j=0;j<world;j++) {
			v_parent+=Math.pow((agents_d[j][i].getScore()-v_min), 2);
		}
		v_parent+=Math.pow((agents[i].getScore()-v_min), 2);
			//System.out.println(i+" "+v_child+" "+v_parent+" "+v_child/v_parent);
			
		if(Double.compare(v_parent, 0)==0) {
			weight[0]=(double)1/n;
		}
		else {
			weight[0]=v_child/v_parent;
		}
		
		
		for(int j=0;j<world;j++) {
			v_child=Math.pow((agents_d[j][i].getScore()-v_min), 2);
			if(Double.compare(v_parent, 0)==0) {
				weight[j+1]=(double)1/n;
			}
			else {
				weight[j+1]=v_child/v_parent;
			}
			
		}
		
		/*if(i==0) {
			for(int j=0;j<world+1;j++) {
				System.out.println(j+" "+weight[j]);
			}
		}*/
		
		
		//System.out.println(weight);
	}
	

	public int[] weightedLottery(int agent_num,MakeRnd rnd) {
		int[] parent=new int[2];
		for(int i=0;i<2;i++) {
			AgentWeight[] probability=new AgentWeight[world+1];
			AgentWeight[] new_weight=new AgentWeight[world+1];
			for(int j=0;j<world+1;j++) {
				probability[j]=new AgentWeight();
				new_weight[j]=new AgentWeight();
			}

			double total_weight=0.0;
			int cnt=0;
			for(int j=0;j<world+1;j++) {
				if(weight[j]==0) {
					continue;
				}
				total_weight+=weight[j];
				new_weight[cnt].weight=weight[j];
				new_weight[cnt].agent_num=j;
				cnt++;
			}
			//System.out.println(total_weight);
			for(int j=0;j<cnt;j++) {
				if(j==0) {
					probability[j].weight=new_weight[j].weight/total_weight;
					probability[j].agent_num=new_weight[j].agent_num;
				}
				else{
					probability[j].weight=probability[j-1].weight+new_weight[j].weight/total_weight;
					probability[j].agent_num=new_weight[j].agent_num;
				}
			}
			
			/*System.out.println(agent_num);
			for(int j=0;j<cnt;j++) {
				System.out.println(probability[j].agent_num+"->"+probability[j].weight);
			}
			System.out.println("----------");*/
			
			/*for(int j=0;j<cnt;j++) {
				System.out.print(probability[j].agent_num+"___"+probability[j].weight+"   ");
			}
			System.out.println();*/
			
			double r=rnd.getRandomNumber();
			//System.out.println(r);
			for(int j=0;j<cnt;j++){

	        	if(r<probability[j].weight){
	        		parent[i]=probability[j].agent_num;
	        		weight[probability[j].agent_num]=0;
	        		break;
	        	}
	        }
		}
		
		return parent;
	}
	
	public void evolution(int i,int w,int[] bitmask,int[] num_of_parents,Agent[] agents,MakeRnd rnd) {	
		double r1 = rnd.getRandomNumber();
		if(r1<0.5) {
			for(int j=0;j<6;j++) {	//懲罰ゲーム用の子供生成
				if(bitmask[j]==1) {
					if(num_of_parents[0]==0) tmp_child[w][j]=agents[i].getBL(j);
					else tmp_child[w][j]=agents_d[num_of_parents[0]-1][i].getBL(j);
				}
				else {
					if(num_of_parents[1]==0) tmp_child[w][j]=agents[i].getBL(j);
					else tmp_child[w][j]=agents_d[num_of_parents[1]-1][i].getBL(j);
				}
				double r = rnd.getRandomNumber();
				if(r<0.005) {
					//System.out.println("mutation!!!");
					if(tmp_child[w][j]==1) tmp_child[w][j]=0;
					else tmp_child[w][j]=1;
				}
			}
		}
		else {
			for(int j=0;j<6;j++) {	//懲罰ゲーム用の子供生成
				if(bitmask[j]==1) {
					if(num_of_parents[1]==0) tmp_child[w][j]=agents[i].getBL(j);
					else tmp_child[w][j]=agents_d[num_of_parents[1]-1][i].getBL(j);
				}
				else {
					if(num_of_parents[0]==0) tmp_child[w][j]=agents[i].getBL(j);
					else tmp_child[w][j]=agents_d[num_of_parents[0]-1][i].getBL(j);
				}
				double r = rnd.getRandomNumber();
				if(r<0.005) {
					//System.out.println("mutation!!!");
					if(tmp_child[w][j]==1) tmp_child[w][j]=0;
					else tmp_child[w][j]=1;
				}
			}
		}
		
		
	}
	
	public void ga(int times,MakeRnd rnd) {
		//PrintGean.makeFile(times,n);
		int[] bitmask=new int[6];	//ビットマスク
		int[] num_of_parents=new int[2];	//選択した親の番号を格納
		
		for(int i=0;i<n;i++) {
			for(int w=0;w<world+1;w++) {
				//PrintEvolution.makeFile(i,times);
				for(int j=0;j<6;j++) {	//マスクのビット列生成
					double bit = rnd.getRandomNumber();
				
					if(bit<0.5) bitmask[j]=0;
					else bitmask[j]=1;
				}
				calculateWeight(i);
			
				//PrintEvolution.writeWeight(times,i,n,weight,agents);
			
				num_of_parents=weightedLottery(i,rnd);
			
				//if(i==1) System.out.println(num_of_parents[0]+" "+num_of_parents[1]);
			
				evolution(i,w,bitmask,num_of_parents,agents,rnd);
			
				//PrintEvolution.writeEvolution(times,i,num_of_parents,tmp_child,agents);
			}
		
			for(int x=0;x<world+1;x++) {	//進化を反映
				if(x==0) {
					for(int j=0;j<3;j++) {	//懲罰ゲーム用の子供生成
						agents[i].setAgentValueBL(tmp_child[x][j],j);
					}
					for(int j=3;j<6;j++) {	//懲罰ゲーム用の子供生成(続き)
						agents[i].setAgentValueBL(tmp_child[x][j],j);
					}
				}
				else {
					for(int j=0;j<3;j++) {	//懲罰ゲーム用の子供生成
						agents_d[x-1][i].setAgentValueBL(tmp_child[x][j],j);
					}
					for(int j=3;j<6;j++) {	//懲罰ゲーム用の子供生成(続き)
						agents_d[x-1][i].setAgentValueBL(tmp_child[x][j],j);
					}
				}
			}
		}
	}
}
