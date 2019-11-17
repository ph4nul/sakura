package simulation;

import agent.*;
import core.*;
import general_metanorms_game.*;
import network.*;
import random.*;
import genetic_algorithm.*;
import java.io.File;
import java.io.FileReader;
import java.io.BufferedReader;
import java.io.IOException;
import java.util.Objects;

public class Simulation {
	public static void start(int seed_num,int n,int n0,int edge) {
		//Sfmt rnd= new Sfmt(seed_num);
		MakeRnd rnd=new MakeRnd(seed_num);
		int times=2000;	//シミュレーション回数
		int world=29;
		int num_sakura=1;
		double[][] aveBL=new double[n][2];
		Agent[] agents=new Agent[n+num_sakura];
		
		Agent[][] agents_d=new Agent[world][n+num_sakura];
		
		for(int i=0;i<n;i++) {
			agents[i]=new Agent(i);
		}
		
		for(int i=0;i<world;i++) {
			for(int j=0;j<n;j++) {
				agents_d[i][j]=new Agent(i);
			}
		}
		
		//Create Agents
		try {
			for(int i=0;i<n;i++) {
				File f = new File("D:\\work\\dataset2\\geanfinal\\gean-"+i+".csv");
				BufferedReader br = new BufferedReader(new FileReader(f));
				String[][] data = new String[30][6];
			    String line = br.readLine();
			    for (int row = 0; line != null; row++) {
			        data[row] = line.split(",", 0);
			        line = br.readLine();
			    }
			    br.close();
			    
			    for(int row = 0; row < world+1; row++) {
			    	  if(row==0) {
			    		  for(int col = 0; col < 6; col++) {
			    			  if(Objects.equals(data[row][col],"1")) agents[i].setAgentValueBL(1, col);
			    			  else agents[i].setAgentValueBL(0,col);
			    		  }
			    	  }
			    	  else {
			    		  for(int col = 0; col < 6; col++) {
			    			  if(Objects.equals(data[row][col],"1")) agents_d[row-1][i].setAgentValueBL(1, col);
			    			  else agents_d[row-1][i].setAgentValueBL(0,col);
			    		  }
			    	  }
			    } 
			}
		 } catch (IOException e) {
		      System.out.println(e);
		 }
		
		try {
			File f = new File("D:\\work\\dataset2\\network\\matrix.csv");
			BufferedReader br = new BufferedReader(new FileReader(f));
		 
			String[][] data2 = new String[1001][1002];
			String line = br.readLine();
			for (int row = 0; line != null; row++) {
				data2[row] = line.split(",", 0);
				line = br.readLine();
			}
			br.close();
		 
			// CSVから読み込んだ配列の中身を表示
			/*for(int row = 0; row < 1001; row++) {
				for(int col = 0; col < 1002; col++) {
					System.out.print(data2[row][col]);
				}
				System.out.println();
			} */
			
			for(int i=0;i<n;i++) {
				for(int j=1;j<1001;j++) {
					if(Objects.equals(data2[i+1][j],"1")) {
						for(int k=0;k<world+1;k++) {
							if(k==0) agents[i].addAgentNumber(j-1);
							else agents_d[k-1][i].addAgentNumber(j-1);
						}
					}
				}
			}
		} catch (IOException e) {
			System.out.println(e);
		}
		
		/*for(int i=0;i<n;i++) {
			for(int j=0;j<agents[i].getListSize();j++) {
				System.out.print(agents[i].getAgentNumber(j)+",");
			}
			System.out.println();
		}*/
		
		System.out.println("Making network completed.");
		System.out.println("\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\");
		
		//サクラをネットワークにつなぐ
		for(int i=0;i<num_sakura;i++) {
			agents[n+i]=new Agent(n+i);
			for(int j=0;j<3;j++) {
				agents[n+i].setAgentValueBL(0, j);
			}
			for(int j=3;j<6;j++) {
				agents[n+i].setAgentValueBL(1, j);
			}
			agents[n+i].setSmax();
					
			for(int j=0;j<world;j++) {
				agents_d[j][n+i]=new Agent(n+i);
				for(int k=0;k<3;k++) {
					agents_d[j][n+i].setAgentValueBL(0, k);
				}
				for(int k=3;k<6;k++) {
					agents_d[j][n+i].setAgentValueBL(1, k);
				}
				agents_d[j][n+i].setSmax();
			}			
		}
				
		/*for(int i=0;i<n+num_sakura;i++) {
			for(int j=0;j<6;j++) {
				System.out.print(agents_d[0][i].getBL(j));
			}
			System.out.println();
		}*/
				
		//フリーライダー識別＋エッジ追加
		for(int i=0;i<n;i++) {
			double sumb=0;
			double suml=0;
			for(int j=0;j<world+1;j++) {
				if(j==0) {
					sumb+=agents[i].getAgentValueBL(0)/7;
					suml+=agents[i].getAgentValueBL(1)/7;
				}
				else {
					sumb+=agents_d[j-1][i].getAgentValueBL(0)/7;
					suml+=agents_d[j-1][i].getAgentValueBL(1)/7;
				}
			}
			sumb/=(world+1);
			suml/=(world+1);
			//if(sumb<0.4&&suml<0.4) System.out.println(i+" is free rider");
			if(sumb<0.1&&suml<0.1) {
				agents[i].setlurkerflg();
				for(int j=0;j<world;j++) {
					agents_d[j][i].setlurkerflg();
				}
				for(int j=0;j<num_sakura;j++) {
					agents[i].addAgentNumber(n+j);
					for(int k=0;k<world;k++) {
						agents_d[k][i].addAgentNumber(n+j);
					}
				}
			}
		}
				
		for(int i=0;i<n;i++) {
			for(int j=0;j<agents[i].getListSize();j++) {
				System.out.print(agents_d[0][i].getAgentNumber(j)+",");
			}
			System.out.println();
		}
		
		
		CalculationBVL.SetHeader();
		//MetaPunishment punishment =new MetaPunishment(n,3.0,-1.0,-9.0,-2.0,-9.0,-2.0); 
		dMRG reward =new dMRG(n,-3.0,1.0,-2.0,9.0,-2.0,9.0); 
		dMRG_d[] reward_d;
		reward_d=new dMRG_d[world];
		MakeRnd[] r;
		r=new MakeRnd[world];
		Sync sync = new Sync();
		
		double[] sumB=new double[n];
		double[] sumL=new double[n];
		
		double tmpB=0;
		double tmpL=0;
		
		for(int i=0;i<n;i++) {
			sumB[i]=0;
			sumL[i]=0;
		}
		
		for(int i=0;i<times;i++) {
			
			//punishment.game(i,agents,rnd);
			reward.game(i,agents,rnd);
			
			for(int j=0;j<world;j++) {
				r[j]=new MakeRnd(j+10);
				reward_d[j]=new dMRG_d(sync,n,-3.0,1.0,-2.0,9.0,-2.0,9.0,agents_d[j],r[j],i,j);
				reward_d[j].start();
			}
			
			sync.waitSync();
			
			PrintWorldScore ps=new PrintWorldScore(i,world);
			ps.makeFile(times,n);
			ps.writeScore(times, n, agents_d);
			ps.closeFile();
			
			//UniformCrossover uc=new UniformCrossover(n,world,agents,agents_d);
			UniformCrossover_v2 uc=new UniformCrossover_v2(n,world,agents,agents_d);
			//UniformCrossoverReward ucr=new UniformCrossoverReward(n);
			uc.ga(i,rnd);
			//ucr.ga(i,agents,rnd);
			
			
			//CalculationBVL.Export(agents,n,i);

			for(int j=0;j<n;j++) {
				agents[j].resetScore();
				//agents[j].printBL();
				for(int k=0;k<world;k++) {
					agents_d[k][j].resetScore();
				}
			}
			
			for(int j=0;j<n;j++) {
				double tmpb=0.0;
				double tmpl=0.0;
				tmpb+=agents[j].getAgentValueBL(0)/7;
				tmpl+=agents[j].getAgentValueBL(1)/7;
				for(int w=0;w<world;w++) {
					tmpb+=agents_d[w][j].getAgentValueBL(0)/7;
					tmpl+=agents_d[w][j].getAgentValueBL(1)/7;
				}
				tmpb/=(world+1);
				tmpl/=(world+1);
				aveBL[j][0]=tmpb;
				aveBL[j][1]=tmpl;
				PrintGean.Export(tmpb,tmpl, n, i,j);
			}
			//PrintGean.Export(agents, n, i);
			
			if(i>=1500) {
				for(int j=0;j<n;j++) {
					tmpB=0;
					tmpL=0;
					tmpB+=agents[j].getAgentValueBL(0)/7;
					tmpL+=agents[j].getAgentValueBL(1)/7;
					for(int w=0;w<world;w++) {
						tmpB+=agents_d[w][j].getAgentValueBL(0)/7;
						tmpL+=agents_d[w][j].getAgentValueBL(1)/7;
					}
					tmpB/=(world+1);
					tmpL/=(world+1);
					sumB[j]+=tmpB;
					sumL[j]+=tmpL;
				}
			}
			
			System.out.println("finish step"+i);
		}
		
		for(int i=0;i<n;i++) {
			PrintGean.ExportFinal(agents, agents_d, i, world);
		}
		
		PrintGean.ExportAverage(agents, n, sumB, sumL, times);
	}
	
}
