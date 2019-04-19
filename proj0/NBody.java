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
}
