package com.carsonlius.thinkinjava.enums;

public class Light {
    Signal signal = Signal.GREEN;

    @Override
    public String toString() {
        return "Light is " + signal;
    }

    private void change() throws Exception {
        switch (signal) {
            case RED:
                signal = Signal.GREEN;
                break;
            case GREEN:
                signal = Signal.YELLOW;
                break;
            case YELLOW:
                signal = Signal.RED;
                return ;
            default:
                throw new Exception("not match case");

        }
    }

    public static void main(String[] args) throws Exception {
        Light light = new Light();
        for (int i = 0; i < 5; i++) {
            System.out.println(light);
            light.change();

        }
    }




}
