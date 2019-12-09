public class NBody{
	public static double readRadius(String filename){
		In in = new In(filename);
		int nunm = in.readInt();
		double radius = in.readDouble();
		return radius;
	}

	public static Body[] readBodies(String filename){
		In in = new In(filename);
		int num = in.readInt();
		double radius = in.readDouble();
		Body[] allBodies = new Body[num];
		/*double xxpos;
		double yypos;
		double xxvel;
		double yyvel;
		double mass;
		String img;*/
		int i = 0;
		while(i<num){
			double xxpos = in.readDouble();
			double yypos = in.readDouble();
			double xxvel = in.readDouble();
			double yyvel = in.readDouble();
			double mass = in.readDouble();
			String img = in.readString();
			allBodies[i] = new Body(xxpos,yypos,xxvel,yyvel,mass,img);
			i += 1;
		}
		return allBodies;
	}
	
	public static void main(String[] args){
		double T = Double.parseDouble(args[0]);
		double dt = Double.parseDouble(args[1]);
		String filename = args[2];
		double radius = readRadius(filename);
		Body[] universe = readBodies(filename);

		double time = 0;

		StdDraw.enableDoubleBuffering();
		StdDraw.setScale(-radius,radius);
	
		StdDraw.clear();
		StdDraw.picture(0,0,"images/starfield.jpg");
	
		for(Body b:universe){
			b.draw();
		}
	
		StdDraw.show();
		StdDraw.pause(10);

		while(time<T){
			double[] xForces = new double[universe.length];
			double[] yForces = new double[universe.length];
			
			for(int i = 0;i<universe.length;i++){
				xForces[i] = universe[i].calcNetForceExertedByX(universe);
				yForces[i] = universe[i].calcNetForceExertedByY(universe);
			}

			for(int i = 0;i<universe.length;i++){
				universe[i].update(dt,xForces[i],yForces[i]);
			}
			
			StdDraw.clear();
			StdDraw.picture(0,0,"images/starfield.jpg");
		
			for(Body b:universe){
				b.draw();
			}
		
			StdDraw.show();
			StdDraw.pause(10);
			
			time += dt;
		}
		
		StdOut.printf("%d\n", universe.length);
		StdOut.printf("%.2e\n", radius);
		for (int i = 0; i < universe.length; i++) {
	    		StdOut.printf("%11.4e %11.4e %11.4e %11.4e %11.4e %12s\n",
                  	universe[i].xxPos, universe[i].yyPos, universe[i].xxVel,
                  	universe[i].yyVel, universe[i].mass, universe[i].imgFileName);   
		}
	}
}
