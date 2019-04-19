public class Body{
	public double xxPos;
	public double yyPos;
	public double xxVel;
	public double yyVel;
	public double mass;
	public String imgFileName;

	public static final double G = 6.67e-11;
	
	public Body(double xP,double yP,double xV,
			double yV,double m,String img){
		xxPos = xP;
		yyPos = yP;
		xxVel = xV;
		yyVel = yV;
		mass = m;
		imgFileName = img;
	}

	public Body(Body b){
	
		xxPos = b.xxPos;
		yyPos = b.yyPos;
		xxVel = b.xxVel;
		yyVel = b.yyVel;
		mass = b.mass;
		imgFileName = b.imgFileName;
	}

	public double calcDistance(Body b){
		double dx = this.xxPos - b.xxPos;
		double dy = this.yyPos - b.yyPos;
		double distance;
		distance = Math.sqrt(dx*dx + dy*dy);
		return distance;
	}

	public double calcForceExertedBy(Body b){
		double distance = this.calcDistance(b);
		double force;
		force = G*mass*b.mass/(distance*distance);
		return force;
	}

	public double calcForceExertedByX(Body b){
		double dx = b.xxPos - this.xxPos;
		double distance  = this.calcDistance(b);
		double forceX = this.calcForceExertedBy(b)*dx/distance;
		return forceX;
	}

	public double calcForceExertedByY(Body b){
		double dy = b.yyPos - this.yyPos;
		double distance  = this.calcDistance(b);
		double forceY = this.calcForceExertedBy(b)*dy/distance;
		return forceY;
	}

	public double calcNetForceExertedByX(Body[] allBodys){
		double forceX;
		double netForceX = 0.0;
		for(Body b:allBodys){
			if(!this.equals(b)){
				forceX = this.calcForceExertedByX(b);
				netForceX += forceX;
			}
		}
		return netForceX;
	}

	public double calcNetForceExertedByY(Body[] allBodys){
		double forceY;
		double netForceY = 0.0;
		for(Body b:allBodys){
			if(!this.equals(b)){
				forceY = this.calcForceExertedByY(b);
				netForceY += forceY;
			}
		}
		return netForceY;
	}
	
	public void update(double dt,double fX,double fY){
		double aX = fX/this.mass;
		double aY = fY/this.mass;
		this.xxVel += dt*aX;
		this.yyVel += dt*aY;
		this.xxPos += dt*this.xxVel;
		this.yyPos += dt*this.yyVel;
	}

}
