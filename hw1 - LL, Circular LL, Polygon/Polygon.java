/*
 * Name:
 * Student ID:
 */

public class Polygon
{
    private CircularLinkedList<Point> boundary;

    /*
     * Constructor for our Polygon
     *
     * Initialize the instance variables to their default values
     */
    public Polygon()
    {
        boundary = new CircularLinkedList<Point>();
    }

    /*
     * addPoint
     *
     * Insert a new point to the end of the polygon boundary
     *
     * p - Point; the point to be added to the polygon
     *
     * Note: By the "end" of the polygon boundary we mean the tail of the
     *       underlying circular linked list
     */
    public void addPoint(Point p)
    {
        boundary.insertAtTail(p);
    }

    /*
     * getSize
     *
     * Return the number of points on the polygon boundary
     */
    public int getSize()
    {
        return boundary.getSize();
    }

    /*
     * pointInPolygon
     *
     * Return true if the point is inside of the polygon and false otherwise
     *
     * p - Point; the point to be tested
     *
     * Note: You should use the "Ray-Casting" algorithm described in the
     *       handout.
     *
     * Note: Your ray should have slope 0, meaning it extends to infinity
     *       directly to the right of the point (see edge case #4)
     *
     * Note: You DO NOT have to consider the following edge cases:
     *          1) There are two points in the polygon with the same x and y
     *          2) The test point lies on a straight line with two adjacent
     *             points on the boundary, or lies exactly on the boundary
     *          3) The test point has the same x and y as a point already in the
     *             polygon
     *          4) The ray intersecting the boundary at a vertex (where two
     *             edges meet).
     *                 i) This is the reason for the slope 0 ray
     */
    public boolean pointInPolygon(Point p)
    {
        int r, s, intersections = 0;
        double b, x1, x2, y1, y2, rayX = p.getX(), rayY = p.getY(), possibleX, slope;

        for (int i = 0; i < boundary.getSize(); i++){
            r = i;
            s = i + 1;
            if (i == boundary.getSize() - 1){b = 0;}
            x1 = boundary.getElementAtIndex(r).getData().getX();
            y1 = boundary.getElementAtIndex(r).getData().getY();
            x2 = boundary.getElementAtIndex(s).getData().getX();
            y2 = boundary.getElementAtIndex(s).getData().getY();
            
            if (!((rayY > y1 && rayY > y2) || (rayY < y1 && rayY < y2))){
                if (x1 == x2 && rayX < x1){
                    intersections++;
                } else {
                    slope = (y2 - y1)/(x2 - x1);
                    b = y1 - (slope * x1);
                    possibleX = (rayY - b)/slope;
                    if (rayX < possibleX){
                        intersections++;
                    }
                }
            }
        }
        return (intersections % 2 == 1);
    }
}

/*
 * Point class
 *
 * This class describes a basic 2-d point object
 *
 * You should not edit anything below this line but please note exactly what
 * is implemented so that you can use it in your linked list code
 */

class Point
{
    private double x,y;
    public Point(double x, double y)
    {
        this.x = x;
        this.y = y;
    }

    //You probably won't have to use this but we have implemented it for you
    //in case you want to test your code
    @Override public boolean equals(Object obj)
    {
        if(this == obj)
            return true;
        if(!(obj instanceof Point))
            return false;
        else
        {
            Point obj_cast = (Point)obj;
            return this.x == obj_cast.x && this.getY() == obj_cast.getY();
        }
    }

    public double getX()
    {
        return x;
    }

    public double getY()
    {
        return y;
    }
}
