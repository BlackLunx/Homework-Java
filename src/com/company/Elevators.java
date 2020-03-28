package com.company;

import javafx.util.Pair;

import java.util.ArrayList;

public class Elevators {

    static class Elevator extends Thread{
        private int end;
        private int currentFloor;
        private int start;
        private int delta;
        private boolean can;
        @Override
        public void run() {
            can = false;
            delta = currentFloor < start ? 1 : -1;
            while(currentFloor != start) {
                try {
                    sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                currentFloor += delta;
            }
            can = true;
            delta = currentFloor < end ? 1 : -1;
            while(currentFloor != end) {
                try {
                    sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                currentFloor += delta;
            }
        }

        public void setEnd(int end) {
            this.end = end;
        }

        public void setStart(int start) {
            this.start = start;
        }

        private int getEnd() {
            return this.end;
        }

        public boolean Can() {
            return this.can;
        }
        public int getCurrentFloor() {
            return this.currentFloor;
        }

        public int getDelta() {
            return this.delta;
        }
    }

    private ArrayList<Elevator> elevators;
    private ArrayList<Pair<Integer, Pair<Integer, Integer> > > queries;
    private Thread queryParser;
    public Elevators(int quantity) {
        elevators = new ArrayList<>();
        queries = new ArrayList<>();
        for(int i = 0; i < quantity; i++) {
            elevators.add(new Elevator());
        }
        final int[] emptyLoops = {0};
        Thread queryParser = new Thread(new Runnable() {
            @Override
            public void run() {
                while(true) {
                    if(emptyLoops[0] == 1000000) { // ЕСЛИ ПРОСТО ТАК РАБОТАЕМ 1000000 КРУГОВ, ТО ПОРА С ЭТИМ ЗАКАНЧИВАТЬ
                        break;
                    }
                    if(queries.size() == 0){
                        emptyLoops[0]++;
                        continue;
                    }
                    else emptyLoops[0] = 0;
                    ArrayList<Pair<Integer, Pair<Integer, Integer>>> started = new ArrayList<>();
                    for (Pair<Integer, Pair<Integer, Integer>> query : queries) {
                        int best = -1;
                        int len = Integer.MAX_VALUE;
                        for (int j = 0; j < elevators.size(); j++) {
                            Elevator elevator = elevators.get(j);
                            if (elevator.isAlive()) {
                                if (elevator.getDelta() == query.getKey() && elevator.Can()) {
                                    if (query.getKey() < 0) {
                                        if (query.getValue().getKey() < elevator.getCurrentFloor() && query.getValue().getKey() > elevator.getEnd()) {
                                            elevator.setEnd(Math.min(elevator.getEnd(), query.getValue().getValue()));
                                            started.add(query);
                                        }
                                    } else {
                                        if (query.getValue().getKey() > elevator.getCurrentFloor() && query.getValue().getKey() < elevator.getEnd()) {
                                            elevator.setEnd(Math.max(elevator.getEnd(), query.getValue().getValue()));
                                            started.add(query);
                                        }
                                    }
                                }
                            } else {
                                if (Math.abs(elevator.getCurrentFloor() - query.getValue().getKey()) < len) {
                                    best = j;
                                    len = Math.abs(elevator.getCurrentFloor() - query.getValue().getKey());
                                }
                            }
                        }
                        if (best != -1) {
                            startElevator(best, query.getValue().getKey(), query.getValue().getValue());
                            started.add(query);
                        }
                    }
                    queries.removeAll(started);
                }
            }
        });
        queryParser.start();
    }

    private void startElevator(int num, int start, int end) {
        elevators.get(num).setStart(start);
        elevators.get(num).setEnd(end);
        elevators.get(num).start();
    }

    public void request(int delta, int start, int end) {
        int newDelta = start < end ? -1 : 1;
        if(start != end && newDelta == delta)
            queries.add(new Pair<>(delta, new Pair<>(start, end)));
    }

    public void stop() {
        if(queryParser.isAlive())
            queryParser.stop();
    }
}
