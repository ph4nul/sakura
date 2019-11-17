package general_metanorms_game;

import agent.Agent;
import random.*;
import simulation.Sync;

public class dMRG_d extends Thread{

	int n;	//エージェント数
	double F;	//エージェントiが裏切った時のiの得る利得
	double M;	//残りのエージェントが得る利得
	double C;	//jがiの裏切りを発見し罰したときのiの得る利得
	double R;	//jがiの裏切りを発見し罰したときのjの得る利得
	double CC;	//さらに、jがiの裏切りを発見し、jがiを罰しなかったことをkがjを罰したときのjの得る利得
	double RR;	//さらに、jがiの裏切りを発見し、jがiを罰しなかったことをkがjを罰したときのkの得る利得
	private Agent[] agents;
	MakeRnd rnd;
	int times;
	int my_world;
	Sync sync;
	
	public dMRG_d(Sync sy,int num,double f,double m,double c,double r,double cc,double rr,Agent[] a,MakeRnd rn,int t,int m_w) {
		n=num;
		F=f;
		M=m;
		C=c;
		R=r;
		CC=cc;
		RR=rr;
		agents=a;
		rnd=rn;
		times=t;
		my_world=m_w;
		sync=sy;
		sync.addSync();
	}

	private void checkCoordination(int times,Agent[] agents,MakeRnd rnd) {
		for(int i=0;i<n;i++) {
			for(int j=0;j<n;j++) {
				agents[j].resetN_c();
				agents[j].resetN_cc();
			}
			//pg.writeAgent(i,times, agents);
			double S=agents[i].getS();
			//System.out.println(agents[i].getS());
			if(S>=1-agents[i].getAgentValueBL(0)/7.0) {	//協調
				//pg.writeCoordination(i,times, agents);
				agents[i].addScore(F);
				for(int j=0;j<agents[i].getListSize();j++) {
					agents[agents[i].getAgentNumber(j)].addScore(M);
				}
				CheckOthers1(times,"Phase2",i,agents,rnd);
			}
			else {	//裏切り
				//pg.writeBetrayal(i,times, agents);
			}
			//pg.writeEnd(times);
			for(int j=0;j<n;j++) {
				if(j==i) {
					agents[i].addScore(R*Math.log(agents[i].getN_c()+1));
				}
				else {
					agents[j].addScore(RR*Math.log(agents[j].getN_c()+1));
				}
			}
			//ps.writeScore(i,times,n,agents);
			
			/*System.out.println(i);
			for(int j=0;j<n;j++) {
				System.out.println("Agent"+j+" Nc="+agents[j].getN_c()+" Ncc="+agents[j].getN_cc());
			}*/
			
		}
		//pg.writeLine(times);
	}

	private void CheckOthers1(int times,String name,int i,Agent[] agents,MakeRnd rnd) {
		for(int j=0;j<agents[i].getListSize();j++) {
			double rnd1=rnd.getRandomNumber();
			double rnd2=rnd.getRandomNumber();

			if(i==agents[i].getAgentNumber(j)) {
				continue;
			}
			double S=agents[agents[i].getAgentNumber(j)].getS();
			if(rnd1<S) {	//投稿に気づいた
				//pg.writeFind(i,agents[i].getAgentNumber(j),times,agents);
				if(rnd2<(agents[agents[i].getAgentNumber(j)].getAgentValueBL(1)/7.0)) {	//コメントする
					//if(agents[i].getAgentNumber(j)>=n) System.out.println("BOT!");
					
					agents[agents[i].getAgentNumber(j)].addScore(C);
					agents[i].addN_c();
					//pg.writeComment(i,agents[i].getAgentNumber(j),times,agents);
					CheckOthers2(times,"Phase3",i,agents[i].getAgentNumber(j),agents,rnd);

				}
				else {	//コメントしない
					//pg.writeNotComment(i,agents[i].getAgentNumber(j),times,agents);
				}
			}
			else {	//気づかなかった
				//pg.writeNotFind(i,agents[i].getAgentNumber(j),times,agents);
			}
		}
	}

	private void CheckOthers2(int times,String name,int i,int j,Agent[] agents,MakeRnd rnd) {
		for(int k=0;k<agents[j].getListSize();k++) {
			double rnd1=rnd.getRandomNumber();
			double rnd2=rnd.getRandomNumber();

			if(j==agents[j].getAgentNumber(k)) {
				continue;
			}
			double S=agents[agents[j].getAgentNumber(k)].getS();
			if(rnd1<S) {	//コメントに気づいた
				//pg.writeFind2(j,agents[j].getAgentNumber(k),times,agents);
				if(rnd2<(agents[agents[j].getAgentNumber(k)].getAgentValueBL(1)/7.0)) {	//裏切りに対する指摘
					if(agents[j].getAgentNumber(k)==i) agents[i].addN_cc();
					else agents[agents[j].getAgentNumber(k)].addScore(CC);
					agents[j].addN_c();
					//pg.writeMetaComment(j,agents[j].getAgentNumber(k),times,agents);
				}
				else {	//指摘しない
					//pg.writeNotMetaComment(j,agents[j].getAgentNumber(k),times,agents);
				}
			}
			else {
				//pg.writeNotFind2(j,agents[j].getAgentNumber(k),times,agents);
			}
		}
	}

	public void game() {
		//PrintStateOfGame pg=new PrintStateOfGame(times);
		//PrintScoreOfGame ps=new PrintScoreOfGame(times);
		//pg.makeFile(times);
		//ps.makeFile(times,n);

		
		
		for(int i=0;i<4;i++) {
			//System.out.println("phase"+i);
			for(int j=0;j<n;j++) {	//発見率セット
				agents[j].setS(rnd);
				//System.out.println(agents[j].getS());
				
			}
			//System.out.println("--------------------");
			
			//pg.writeLap(i,times);
			//ps.writeLap(i,times);

			checkCoordination(times,agents,rnd);
			
			
			
		}
		//pg.closeFile();
		//ps.closeFile();
	}
	
	public void run() {
		
		game();
		//System.out.println("子スレッド:"+this.getName());
		sync.delSync();
	}

}
