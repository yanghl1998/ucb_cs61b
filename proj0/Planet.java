public class Planet {
    public double xxPos;
    public double yyPos;
    public double xxVel;
    public double yyVel;
    public double mass;
    public String imgFileName;
    private static double G=6.67e-11;


    /**constructors */
    public Planet(double xP, double yP, double xV, double yV, double m, String img) {
        xxPos=xP;
        yyPos=yP;
        xxVel=xV;
        yyVel=yV;
        mass=m;
        imgFileName=img;

    }

    public Planet(Planet p) {
        xxPos=p.xxPos;
        yyPos=p.yyPos;
        xxVel=p.xxVel;
        yyVel=p.yyVel;
        mass=p.mass;
        imgFileName=p.imgFileName;
    }

    /**calculate the distance */
    public double calcDistance(Planet p) {
        double dx = xxPos-p.xxPos;
        double dy = yyPos-p.yyPos;
        return Math.sqrt(dx*dx+dy*dy);
    }

    /**calculate the force*/
    public double calcForceExertedBy(Planet p) {
        double dis = calcDistance(p);
        return Planet.G*this.mass*p.mass/Math.pow(dis, 2);
    }

    /**calculate the force exerted in the X direction */
    public double calcForceExertedByX(Planet p) {
        // calculate the ratio dx/dis
        double ratioX = (p.xxPos-this.xxPos)/calcDistance(p);
        return calcForceExertedBy(p)*ratioX;
    }

    /**calculate the force exerted in the y direction */
    public double calcForceExertedByY(Planet p) {
        // calculate the ratio dy/dis
        double ratioY = (p.yyPos-this.yyPos)/calcDistance(p);
        return calcForceExertedBy(p)*ratioY;
    }

    /**calculate the force in X direction in the array */
    public double calcNetForceExertedByX(Planet[] ps) {
        double xNetForce = 0;
        for (int i = 0; i < ps.length; i +=1) {
            if(!this.equals(ps[i]))
                xNetForce += calcForceExertedByX(ps[i]);
        }
        return xNetForce;
    }

    /**calculate the force in Y direction in the array */
    public double calcNetForceExertedByY(Planet[] ps) {
        double yNetForce = 0;
        for (int i = 0; i < ps.length; i +=1) {
            if(!this.equals(ps[i]))
                yNetForce += calcForceExertedByY(ps[i]);
        }
        return yNetForce;
    }

    public void update(double t, double fx, double fy) {
        double xxAcc = fx/mass;
        double yyAcc = fy/mass;
        xxVel += xxAcc*t;
        yyVel += yyAcc*t;
        xxPos += xxVel*t;
        yyPos += yyVel*t;

    }

    /** add a new method to draw a planet */
    public void draw() {
        // draw Earth
        String imgPath = "images/" + imgFileName;
        StdDraw.picture(xxPos, yyPos, imgPath);
        //StdDraw.show();
    }

}
