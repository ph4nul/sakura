package general_metanorms_game;

import agent.Agent;
import random.*;
import simulation.Sync;

public class MetaReward_d extends Thread{

	int n;	//�G�[�W�F���g��
	double F;	//�G�[�W�F���gi�����؂�������i�̓��闘��
	double M;	//�c��̃G�[�W�F���g�����闘��
	double C;	//j��i�̗��؂�𔭌����������Ƃ���i�̓��闘��
	double R;	//j��i�̗��؂�𔭌����������Ƃ���j�̓��闘��
	double CC;	//����ɁAj��i�̗��؂�𔭌����Aj��i�𔱂��Ȃ��������Ƃ�k��j�𔱂����Ƃ���j�̓��闘��
	double RR;	//����ɁAj��i�̗��؂�𔭌����Aj��i�𔱂��Ȃ��������Ƃ�k��j�𔱂����Ƃ���k�̓��闘��
	private Agent[] agents;
	MakeRnd rnd;
	int times;
	int my_world;
	Sync sync;
	
	public MetaReward_d(Sync sy,int num,double f,double m,double c,double r,double cc,double rr,Agent[] a,MakeRnd rn,int t,int m_w) {
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
	
	private void checkCoordination(int times,Agent[] agents,MakeRnd rnd,PrintScoreOfGame_d ps) {
		for(int i=0;i<n;i++) {
			//pg.writeAgent(i,times, agents);
			double S=agents[i].getS();
			//System.out.println(agents[i].getS());
			if(S<agents[i].getAgentValueBL(0)/7.0) {	//����	
				/*System.out.println(i);
				for(int j=0;j<agents[i].getListSize();j++) {
					if(j==i) System.out.print("["+agents[i].getAgentNumber(j)+"] ");
				    else System.out.print(agents[i].getAgentNumber(j)+" ");
					//if(j==i) agents[i].addScore(F);
					//else agents[agents[i].getAgentNumber(j)].addScore(M);
				}
				System.out.println();*/
				//pg.writeCoordination(i,times, agents);
				
				agents[i].addScore(F);
				for(int j=0;j<agents[i].getListSize();j++) {
					agents[agents[i].getAgentNumber(j)].addScore(M);
				}
				CheckOthers1(times,"Phase2",i,S,agents,rnd);
			}
			else {	//���؂�
				//pg.writeBetrayal(i,times, agents);
			}
			//pg.writeEnd(times);
			//ps.writeScore(i,times,n,agents);
		}
		//pg.writeLine(times);
	}
	
	private void CheckOthers1(int times,String name,int i,double S,Agent[] agents,MakeRnd rnd) {
		for(int j=0;j<agents[i].getListSize();j++) {
			double rnd1=rnd.getRandomNumber();
			double rnd2=rnd.getRandomNumber();
			
			if(i==agents[i].getAgentNumber(j)) {
				continue;
			}
			
			if(rnd1<S) {	//���e�ɋC�Â���
				//pg.writeFind(i,agents[i].getAgentNumber(j),times,agents);
				if(rnd2<(agents[agents[i].getAgentNumber(j)].getAgentValueBL(1)/7.0)) {	//�R�����g����
					agents[agents[i].getAgentNumber(j)].addScore(C);
					agents[i].addScore(R);
					//pg.writeComment(i,agents[i].getAgentNumber(j),times,agents);
					CheckOthers2(times,"Phase3",i,agents[i].getAgentNumber(j),S,agents,rnd);
					
				}
				else {	//�R�����g���Ȃ�
					//pg.writeNotComment(i,agents[i].getAgentNumber(j),times,agents);
				}
			}
			else {	//�C�Â��Ȃ�����
				//pg.writeNotFind(i,agents[i].getAgentNumber(j),times,agents);
			}
		}
	}
	
	private void CheckOthers2(int times,String name,int i,int j,double S,Agent[] agents,MakeRnd rnd) {
		
		double rnd2=rnd.getRandomNumber();
			
		//pg.writeFind2(j,agents[j].getAgentNumber(k),times,agents);
		if(rnd2<(agents[i].getAgentValueBL(1)/7.0)) {	
			agents[i].addScore(CC);
			agents[j].addScore(RR);
			//pg.writeMetaComment(j,agents[j].getAgentNumber(k),times,agents);
		}
		else {	//�w�E���Ȃ�
			//pg.writeNotMetaComment(j,agents[j].getAgentNumber(k),times,agents);
		}
			
	}
	
	public void game() {
		
		//PrintStateOfGame pg=new PrintStateOfGame(times);
		PrintScoreOfGame_d ps=new PrintScoreOfGame_d(times,my_world);
		
			//pg.makeFile(times);
		//if(my_world==1) ps.makeFile(times,n);
		
		
		for(int i=0;i<4;i++) {
			//System.out.println("phase"+i);
			for(int j=0;j<n;j++) {	//�������Z�b�g
				agents[j].setS(rnd);	
				//System.out.println(agents[j].getS());
			}
			//System.out.println("--------------------");
			
			//pg.writeLap(i,times);
			//if(my_world==1) ps.writeLap(i,times);
			
			checkCoordination(times,agents,rnd,ps);
		}
		//pg.closeFile();
		//if(my_world==1) ps.closeFile();
	}
	
	public void run() {
		
		game();
		//System.out.println("�q�X���b�h:"+this.getName());
		sync.delSync();
	}
	
}
