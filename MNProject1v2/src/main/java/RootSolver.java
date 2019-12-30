public class RootSolver {
    public static double bisection(double xLow, double xHigh,double maxError, Function function){
        double xApproximated = 0;
        double error = 100;
        double xApproximatedPrevious, functionValuexLow, functionValuexHigh, functionValuexApproximated;
        while(error > maxError){
            xApproximatedPrevious = xApproximated;
            xApproximated = (xLow + xHigh) / 2;
            functionValuexLow = function.functionValue(xLow);
            functionValuexHigh = function.functionValue(xHigh);
            functionValuexApproximated = function.functionValue(xApproximated);
            if (functionValuexLow * functionValuexApproximated < 0){
                xHigh = xApproximated;
            }
            if (functionValuexHigh * functionValuexApproximated < 0){
                xLow = xApproximated;
            }
            error = 100*Math.abs(xApproximated - xApproximatedPrevious)/xApproximated;
        }
        return xApproximated;
    }
    public static double falsi(double xLow, double xHigh,double maxError, Function function){
        double xApproximated = 0;
        double error = 100;
        double xApproximatedPrevious, functionValuexLow, functionValuexHigh, functionValuexApproximated;
        while(error > maxError){
            xApproximatedPrevious = xApproximated;
            xApproximated = xHigh - ((function.functionValue(xHigh)*(xLow - xHigh))
                    / (function.functionValue(xLow) - function.functionValue(xHigh)));
            functionValuexLow = function.functionValue(xLow);
            functionValuexHigh = function.functionValue(xHigh);
            functionValuexApproximated = function.functionValue(xApproximated);
            if (functionValuexLow * functionValuexApproximated < 0){
                xHigh = xApproximated;
            }
            if (functionValuexHigh * functionValuexApproximated < 0){
                xLow = xApproximated;
            }
            error = 100*Math.abs(xApproximated - xApproximatedPrevious)/xApproximated;
        }
        return xApproximated;
    }

    public static double fixedPointIteration(double xNValue,double maxError, Function function){
        double xApproximated = 0;
        double error = 100;
        double xApproximatedPrevious;
        while(error > maxError){
            xApproximatedPrevious = xNValue;
            xApproximated = function.functionValue(xNValue) + xNValue;
            xNValue = xApproximated;
            error = 100* Math.abs(xApproximated - xApproximatedPrevious)/xApproximated;
        }
        return xApproximated;
    }
    public static double newtons(double xiValue,double maxError, double eccentricity, Function function){
        double xApproximated = 0;
        double error = 100;
        double xApproximatedPrevious;
        double derivative;
        //derivative of f(E) = M + e* sinE - E is e*cosE-1.
        while(error > maxError){
            xApproximatedPrevious = xiValue;
            derivative = eccentricity * Math.cos(xiValue) - 1;
            xApproximated = xiValue - function.functionValue(xiValue) / derivative ;
            xiValue = xApproximated;
            error = 100* Math.abs(xApproximated - xApproximatedPrevious)/xApproximated;
        }
        return xApproximated;
    }

    public static double secant(double xiValue1, double xiValue2, double maxError, Function function){
        double xApproximated = 0;
        double error = 100;
        double xApproximatedPrevious;
        while(error > maxError){
            xApproximatedPrevious = xiValue2;

            xApproximated = xiValue2 - (function.functionValue(xiValue2)*(xiValue1 -xiValue2))
                    /(function.functionValue(xiValue1) - function.functionValue(xiValue2));
            xiValue1 = xiValue2;
            xiValue2 =xApproximated;
            error = 100* Math.abs(xApproximated - xApproximatedPrevious)/xApproximated;
        }
        return xApproximated;
    }


}
