public class NBody {
    /** This class has NO constructors*/
    public static double readRadius(String file) {
        In in = new In(file);
        in.readInt();
        return in.readDouble();
    }

    public static Planet[] readPlanets(String file) {
        In in = new In(file);
        int planetNumber = in.readInt();
        in.readDouble();
        Planet[] p = new Planet[planetNumber];
        for (int i = 0; i < planetNumber; i++) {
            double xp = in.readDouble();
            double yp = in.readDouble();
            double xv = in.readDouble();
            double yv = in.readDouble();
            double m = in.readDouble();
            String img = in.readString();
            p[i] = new Planet(xp, yp, xv, yv, m, img);
        }
        return p;
    }

    public static void main(String[] args) {
        // collect all needed inputs
        double T = Double.parseDouble(args[0]);
        double dt = Double.parseDouble(args[1]);
        String filename = args[2];
        double radius = readRadius(filename);
        Planet[] planets = readPlanets(filename);
        // drawing the background
        String bgdImg = "images/starfield.jpg";
        StdDraw.setScale(-radius, radius);
        StdDraw.clear();
        StdDraw.picture(0, 0, bgdImg);
        StdDraw.show();
        // drawing the planets using Planet.draw() method
        for (int i = 0; i < planets.length; i++) {
            planets[i].draw();
        }
        StdDraw.pause(100);

        // create an animation
        StdDraw.enableDoubleBuffering();
        double t = 0;
        for (; t < T; t+=dt) {
            // each loop, do the following
            double[] xForces=new double[planets.length];
            double[] yForces=new double[planets.length];
            for (int i = 0; i < planets.length; i++) {
                xForces[i] = planets[i].calcNetForceExertedByX(planets);
                yForces[i] = planets[i].calcNetForceExertedByY(planets);
            }
            for (int i = 0; i < planets.length; i++) {
                planets[i].update(dt, xForces[i], yForces[i]);
            }
            StdDraw.picture(0,0, bgdImg);
            for (int i = 0; i < planets.length; i++) {
                planets[i].draw();
            }
            StdDraw.show();
            StdDraw.pause(10);
        }
        StdOut.printf("%d\n", planets.length);
        StdOut.printf("%.2e\n", radius);
        for (int i = 0; i < planets.length; i++) {
            StdOut.printf("%11.4e %11.4e %11.4e %11.4e %11.4e %12s\n",
                    planets[i].xxPos, planets[i].yyPos, planets[i].xxVel,
                    planets[i].yyVel, planets[i].mass, planets[i].imgFileName);
        }
    }
}
