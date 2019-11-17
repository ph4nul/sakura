package random;

public class MakeRnd {
	private Sfmt rnd;
	public MakeRnd(int seed_num){
		rnd= new Sfmt(seed_num);
	}
	
	public double getRandomNumber() {
		double x=rnd.NextUnif(); 
		return x;
	}
	
}
