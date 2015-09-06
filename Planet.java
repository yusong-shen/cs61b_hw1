/** 
 * Planet Constructor
 */
public class Planet {
	public double x;
	public double y;
	public double xVelocity;
	public double yVelocity;
	public double mass;
	public String img;
	public double xNetForce;
	public double yNetForce;
	public double dt;
	public double xAccel;
	public double yAccel;

	public Planet(double x, double y, double xVel,
	double yVel, double mass, String img){
		this.x = x;
		this.y = y;
		this.xVelocity = xVel;
		this.yVelocity = yVel;
		this.mass = mass;
		this.img = img;
	}

	public double calcDistance( Planet p){
		double dist;
		double dx;
		double dy;
		dx = p.x - this.x;
		dy = p.y - this.y;
		dist = Math.pow((dx*dx + dy*dy),0.5);
		return dist;

	}

	public double calcPairwiseForce(Planet p){
		double f;
		double g;
		double dist;
		g = 6.67e-11;
		dist = this.calcDistance(p);
		f = g * this.mass * p.mass / (dist * dist);
		return f;
	}

	public double calcPairwiseForceX(Planet p){
		double dx;
		double fx;
		dx = p.x - this.x;
		fx = this.calcPairwiseForce(p) * dx / this.calcDistance(p);
		return fx;		
	}

	public double calcPairwiseForceY(Planet p){
		double dy;
		double fy;
		dy = p.y - this.y;
		fy = this.calcPairwiseForce(p) * dy / this.calcDistance(p);
		return fy;		
	}

	public void setNetForce(Planet[] planets){
		double xNF = 0.0;
		double yNF = 0.0;
		for (int i=0; i<planets.length; i++){
			xNF += this.calcPairwiseForceX(planets[i]);
			yNF += this.calcPairwiseForceY(planets[i]);
		}
		this.xNetForce = xNF;
		this.yNetForce = yNF;
		// is that right ?
		this.xAccel = this.xNetForce / this.mass;
		this.yAccel = this.yNetForce / this.mass;		
	}
	/** 
	* draw the planet to the x,y coordinate specified
	*/

	public void draw(){
		StdDraw.picture(this.x, this.y, "images/"+this.img);
	}

	/** 
	* update the positions and velocities of the planets
	* every discrete interval dt
	*/
	public void update(double dt){

		this.xVelocity += dt * this.xAccel;
		this.yVelocity += dt * this.yAccel;
		this.x += dt * this.xVelocity;
		this.y += dt * this.yVelocity;


	}

}
