package org.ex;

public class Flight {
    /**
     * 航线： 欧美 非欧美 国内
     */
    private String destination;

    /**
     * 商务，经济
     */
    private String flightClass;

    /**
     * 飞行时长
     */
    private int time;

    public Flight(String destination, String flightClass, int time) {
        this.destination = destination;
        this.flightClass = flightClass;
        this.time = time;
    }

    public boolean haveFood(){
        if(destination.equals("国内")){
            if(flightClass.equals("商务")){
                return true;
            }else{
                return time > 2;
            }
        }else{
            return true;
        }
    }

    public boolean watchMovie(){
        if(destination.equals("国内")){
            return false;
        }else{
            if(destination.equals("欧美")){
                return true;
            }else{
                if(flightClass.equals("商务")){
                    return true;
                }
            }
        }
        return false;
    }

    public String getDestination() {
        return destination;
    }

    public void setDestination(String destination) {
        this.destination = destination;
    }

    public String getFlightClass() {
        return flightClass;
    }

    public void setFlightClass(String flightClass) {
        this.flightClass = flightClass;
    }

    public int getTime() {
        return time;
    }

    public void setTime(int time) {
        this.time = time;
    }
}
