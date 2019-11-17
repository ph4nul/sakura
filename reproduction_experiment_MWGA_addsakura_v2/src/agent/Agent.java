package agent;

import java.util.ArrayList;
import core.*;
import random.*;

public class Agent {
	private double score=0;
	private int dfcflg=0; //defects flag
	private int dfcdfcflg=0; //defects defects flag
	private int cdnflg=0;	//Coordination flag
	private int lurkerflg=0;
	private int agent_num;
	private int[] BV=new int[6];	//協調する確率と懲罰を与える確率の遺伝子
	private int[] BL=new int[6];	//報酬を与える確率
	private double S=0.0;	//発見率
	private int N_c;
	private int N_cc;
	
	ArrayList<Integer> agList = new ArrayList<Integer>();

	public Agent(int num){
		agent_num=num;
	}
	
/////////////////////////////////////////////
//B,V,L,Sに対する操作
	public void createGene(MakeRnd rnd) {
		for(int i=0;i<3;i++) {
			double bitB = rnd.getRandomNumber();
			if(bitB<0.5) {
				BV[i]=0;
				BL[i]=0;
			}
			else {
				BV[i]=1;
				BL[i]=1;
			}
		}
		for(int i=3;i<6;i++) {
			double bitV = rnd.getRandomNumber();
			double bitL = rnd.getRandomNumber();
			if(bitV<0.5) BV[i]=0;
			else BV[i]=1;
			
			if(bitL<0.5) BL[i]=0;
			else BL[i]=1;
		}
	}
	
	public void showBits(int num) {
		System.out.println(num);
		System.out.print("B=");
		for(int i=0;i<3;i++) {
			System.out.print(BV[i]);
		}
		System.out.println();
		System.out.print("V=");
		for(int i=3;i<6;i++) {
			System.out.print(BV[i]);
		}
		System.out.println();
		System.out.print("L=");
		for(int i=3;i<6;i++) {
			System.out.print(BL[i]);
		}
		System.out.println();
		System.out.println("==========");
	}
	
	public void setAgentValueBV(int num,int bit) {
		BV[bit]=num;
	}
	
	public void setAgentValueBL(int num,int bit) {
		BL[bit]=num;
	}
	
	public double getAgentValueBV(int flg) { //flg=0ならB,flg=1ならV
		double ans=0.0;
		if(flg==0) {
			for(int i=0;i<3;i++) {
				ans+=(BV[i]*Math.pow(2, i));
		 }
		 return ans;
		}
		else {
			for(int i=3;i<6;i++) {
				ans+=(BV[i]*Math.pow(2, (i-3)));
		 }
		 return ans;
		}
	}
	
	public double getAgentValueBL(int flg) { //flg=0ならB,flg=1ならL
		double ans=0.0;
		if(flg==0) {
			for(int i=0;i<3;i++) {
				ans+=(BL[i]*Math.pow(2, i));
		 }
		 return ans;
		}
		else {
			for(int i=3;i<6;i++) {
				ans+=(BL[i]*Math.pow(2, (i-3)));
		 }
		 return ans;
		}
	}
	
	public int getBV(int i) {
		return BV[i];
	}
	
	public int getBL(int i) {
		return BL[i];
	}
	
	public int[] getBLall() {
		return BL;
	}
	
	public void printBV() {
		for(int i=0;i<6;i++) {
			System.out.print(BV[i]);
		}
		System.out.println();
	}
	
	public void printBL() {
		for(int i=0;i<6;i++) {
			System.out.print(BL[i]);
		}
		System.out.println();
	}
	
	public void setS(MakeRnd rnd) {
		S=rnd.getRandomNumber();
	}
	public void setSmax() {
		S=0.2;
	}
	
	public double getS() {
		return S;
	}
	
/////////////////////////////////////////////

/////////////////////////////////////////////
//N_cに対する操作
public void resetN_c() {
N_c=0;
}

public void addN_c() {
N_c+=1;
}

public void resetN_cc() {
N_cc=0;
}

public void addN_cc() {
N_cc+=1;
}

public int getN_c() {
return N_c;
}

public int getN_cc() {
return N_cc;
}


/////////////////////////////////////////////
	
/////////////////////////////////////////////
//agListに対する操作
	public void addAgentNumber(int num) {
		agList.add(num);
	}
	
	public int getAgentNumber(int num) {
		return agList.get(num);
	}
	
	public int getListSize() {
		return agList.size();
	}
	
/////////////////////////////////////////////

/////////////////////////////////////////////
//スコアに対する操作
	public void addScore(double sc){
	  score+=sc;
	}

	public double getScore(){
	  return score;
	}
	
	public void resetScore() {
		score=0;
	}
/////////////////////////////////////////////

/////////////////////////////////////////////
//裏切りフラグ、協調フラグに対する操作
	public void setlurkerflg(){
		  lurkerflg=1;
		}

		public void resetlurkerflg(){
		  lurkerflg=0;
		}

		public int getlurkerflg(){
		  return lurkerflg;
		}
	
	public void setdfcflg(){
	  dfcflg=1;
	}

	public void resetdfcflg(){
	  dfcflg=0;
	}

	public int getdfcflg(){
	  return dfcflg;
	}
	
	public void setdfcdfcflg(){
		 dfcdfcflg=1;
	}

	public void resetdfcdfcflg(){
	  dfcdfcflg=0;
	}

	public int getdfcdfcflg(){
	  return dfcdfcflg;
	}
	
	
/////////////////////////////////////////////
}