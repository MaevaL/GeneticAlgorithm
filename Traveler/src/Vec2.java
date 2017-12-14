
public class Vec2 {

	private double x;
	private double y;
	
	public Vec2(double x, double y) {
		this.x = x;
		this.y = y;
	}
	
	public double getX() {
		return x;
	}
	

	public void setX(double x) {
		this.x = x;
	}

	public double getY() {
		return y;
	}

	public void setY(double y) {
		this.y = y;
	}


	
	public void setCoordinate(double x, double y) {
		this.x = x;
		this.y = y;
	}
	
	public Vec2 sum(Vec2 vec) {
		Vec2 vecSum = new Vec2(x+vec.x, y+vec.y);
		
		return vecSum;
		
	}
	
	public Vec2 substract(Vec2 vec) {
		Vec2 vecSubstract = new Vec2(x-vec.x, y-vec.y);
		
		return vecSubstract;

	}
	public Vec2 multiplyByVec(Vec2 vec) {
		Vec2 multipliedVec = new Vec2(0,0);
		multipliedVec.x = x * vec.x + x * vec.y;
		multipliedVec.y = y * vec.x + y * vec.y;
		
		return multipliedVec;

	}
	
	public Vec2 multiplyByNumber(double number) {
		Vec2 multipliedVec = new Vec2(x*number,y*number);
		
		return multipliedVec;

	}
	public Vec2 divide(Vec2 vec) {
		Vec2 dividedVec = new Vec2(0,0);
		dividedVec.x = x / vec.x + x / vec.y ;
		dividedVec.y = y / vec.x + y / vec.y ;
		
		return dividedVec;

	}
	
	public double norme() {
		return Math.sqrt(x*x + y*y);

	}
	
	public float getDistance(Vec2 v2) {
		
		return (float) Math.abs(Math.sqrt(Math.pow(v2.x - x, 2) + Math.pow(v2.y - y, 2)));
		
	}
	
	public double squareNorme(){
		double square = x*x + y*y;
		
		return square;
	}
	
	public Vec2 normalized() {
		Vec2 normalizedVect = new Vec2(x/norme(), y/norme());
		
		return normalizedVect;

	}
	
	public double scalarProduct(Vec2 vec) {
		return x*vec.x + y*vec.y;
	}
	
	@Override
	public boolean equals(Object vec) {
		Vec2 Vec2 = (Vec2) vec;
		if(x != Vec2.x || y != Vec2.y) {
			return false;
		}
		return true;
	}
	public void showVec() {
		System.out.println("<" + x + "," + y + ">");
	}
	
	public String toString() {
		return new String("< " + x + " , " + y + ">");
	}


}
