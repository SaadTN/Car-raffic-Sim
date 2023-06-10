package proj364;


import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.control.ToggleButton;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;

import java.util.ArrayList;
import java.util.Random;

public class MainFX extends Application {
    /**
     * The current phase(default is 1)
     */
    private int phase=1;
    /**
     * Group obj that contains all the javafx elements
     */
    private final Group root = new Group();
    /**
     * An array that contains the x&y coordinates of the current driver
     */
    private double [] xyPos;
    /**
     * Magnitude of the roundabout circle on the screen
     */
    private final double MAGNITUDE = Coordinate.circleRadius;
    /**
     * Number of the drivers in the simulation
     */
    private int NUMB_OF_DRIVERS = 2;
    /**
     * Arraylist that stores all the current drivers in the simulation
     */
    private final ArrayList<CarDriver> lane = new ArrayList<>();
    /**
     * A random starting point for the lane of drivers
     */
    private double startingAng;
    /**
     * An arraylist that stores the images for the drivers
     */
    private ImageView[] carImgArray = new ImageView[NUMB_OF_DRIVERS];
    /**
     * x center for the application window
     */
    private final double X_CENTER = MAGNITUDE *1.5;
    /**
     * y center for the application window
     */
    private final double Y_CENTER = MAGNITUDE *1.5;
    /**
     * An array for the images of cars
     */
    private final Image[] carImgDirArray = {new Image("file:demo1/src/main/java/proj364/1.png"), new Image("file:demo1/src/main/java/proj364/2.png"),
            new Image("file:demo1/src/main/java/proj364/3.png"), new Image("file:demo1/src/main/java/proj364/4.png"),
            new Image("file:demo1/src/main/java/proj364/5.png")} ;
    /**
     * time between every loop iteration in nanoseconds
     */
    private double timeVar;
    /**
     * the time that has passed since the start of the simulation
     */
    private double timePassed;
    /**
     * the average stopping time percentage for all drivers
     */
    private double stoppingPercentage;
    /**
     * the percent of improvement between two simulations
     */
    private double improvementPerc;

    /**
     * Stores the stoppingPercentage of the last simulation
     */
    private double lastStoppingPerc;
    /**
     * the current selected driver to display its info
     */
    private CarDriver selectedDriver;

    /**
     * Overrides the start method from the Application class
     */
    @Override
    public void start(Stage stage) {

        // Background image settings
        Image image = new Image("file:demo1/src/main/java/proj364/Background.png");
        ImageView backgroundImg = new ImageView(image);
        backgroundImg.setFitHeight(Y_CENTER*2);
        backgroundImg.setFitWidth(X_CENTER*2+300);

        // Add a new car
        Button addCarsBtn = new Button("AddCar");
        addCarsBtn.setPrefWidth(70);
        addCarsBtn.setPrefHeight(40);
        addCarsBtn.setLayoutX(975);
        addCarsBtn.setLayoutY(440);

        // Remove a car
        Button deleteCarBtn = new Button("DeleteCar");
        deleteCarBtn.setPrefWidth(70);
        deleteCarBtn.setPrefHeight(40);
        deleteCarBtn.setLayoutX(1055);
        deleteCarBtn.setLayoutY(440);
        deleteCarBtn.setDisable(true);

        // Restart button settings
        Button restartBtn = new Button("Restart");
        restartBtn.setPrefWidth(70);
        restartBtn.setPrefHeight(40);
        restartBtn.setLayoutX(1055);
        restartBtn.setLayoutY(80);

        // Start Button settings
        ToggleButton startBtn = new ToggleButton("Start");
        startBtn.setPrefWidth(70);
        startBtn.setPrefHeight(40);
        startBtn.setLayoutX(975);
        startBtn.setLayoutY(80);

        // PhaseSwitch toggle Button
        ToggleButton phaseSwitch = new ToggleButton("Phase2");
        phaseSwitch.setPrefWidth(150);
        phaseSwitch.setPrefHeight(40);
        phaseSwitch.setLayoutX(975);
        phaseSwitch.setLayoutY(30);

        //Label that displays the passed time
        Label timeLbl = new Label("0");
        timeLbl.setLayoutX(940);
        timeLbl.setLayoutY(150);

        //Slider to control the speed of the simulation
        Slider timeSlider = new Slider(1 ,10 ,1);
        timeSlider.setLayoutX(975);
        timeSlider.setLayoutY(150);

        //Label that display the time that passed since the start
        Label passedTimeLbl = new Label("Time Passed: ");
        passedTimeLbl.setLayoutX(975);
        passedTimeLbl.setLayoutY(200);

        //Label that display the selected car id
        Label carIdLbl = new Label("Car ID:  ");
        carIdLbl.setLayoutX(975);
        carIdLbl.setLayoutY(250);

        //Label that display the selected car driver type
        Label carTypeLbl = new Label("Type Of Car:  ");
        carTypeLbl.setLayoutX(975);
        carTypeLbl.setLayoutY(300);

        //Label that display the selected car velocity
        Label carVelocityLbl = new Label("Velocity:  ");
        carVelocityLbl.setLayoutX(975);
        carVelocityLbl.setLayoutY(350);

        //Label that display the selected car detection
        Label carDetectionLbl = new Label("Detection:  ");
        carDetectionLbl.setLayoutX(975);
        carDetectionLbl.setLayoutY(400);

        //Label that display the stopping percentage
        Label stopPercentageLbl = new Label("Stopping percent: %");
        stopPercentageLbl.setLayoutX(975);
        stopPercentageLbl.setLayoutY(500);

        Label lastStopPercLbl = new Label("Last percent: %");
        lastStopPercLbl.setLayoutX(975);
        lastStopPercLbl.setLayoutY(550);
        lastStopPercLbl.setDisable(true);

        Label improvementPercLbl = new Label("Improved percent %");
        improvementPercLbl.setLayoutX(975);
        improvementPercLbl.setLayoutY(600);
        improvementPercLbl.setDisable(true);

        //Rectangle on the selected car
        Rectangle selectBorder = new Rectangle(40,40,Color.TRANSPARENT);
        selectBorder.setStrokeWidth(2);
        selectBorder.setStroke(Color.DARKBLUE);
        selectBorder.setVisible(false);

        //adding all the elements to the root
        root.getChildren().addAll(backgroundImg, timeLbl, passedTimeLbl, timeSlider, addCarsBtn, deleteCarBtn,
                startBtn, restartBtn, carIdLbl, carTypeLbl, carDetectionLbl, carVelocityLbl, stopPercentageLbl,
                lastStopPercLbl, improvementPercLbl, selectBorder, phaseSwitch);
        //the initial start of the simulating
        startSim();

        Scene scene = new Scene(root,X_CENTER*2+300, Y_CENTER*2);
        stage.setTitle("SnakeBreaking");
        //The main loop of the simulation and the time between every iteration is determined by the timeVar
        AnimationTimer timer = new AnimationTimer() {
            private long lastUpdate = 0;
            @Override
            public void handle(long now) {
                timeVar = 100000000/timeSlider.getValue();
                String newText =""+(int)timeSlider.getValue()+"X";
                timeLbl.setText(newText);
                if (now - lastUpdate >= timeVar) {
                    timePassed += 0.1;
                    int counter = 0;
                    int nextCar = 1;

                    //loops over every carDriver obj in lane to update its values
                    for (CarDriver carDriver : lane) {
                        //calls the move method of every driver to update its position
                        carDriver.Move();

                        //stores the X&Y values
                        xyPos = carDriver.getVehicleXY();

                        //stores the angle value
                        double ang = carDriver.getVehicleAngle();

                        //sets the position and angle of the car image
                        double carImageSize = carImgArray[counter].getFitWidth();
                        carImgArray[counter].setX(xyPos[0] + X_CENTER - carImageSize/2);
                        carImgArray[counter].setY(xyPos[1] + Y_CENTER - carImageSize/2);
                        carImgArray[counter].setRotate(ang-180);
                        carImgArray[counter].setVisible(true);

                        //updates the detection of the driver
                        carDriver.setDetection(lane.get(nextCar));

                        //update the behavior of the driver based on the new detection value
                        carDriver.makeDecision();


                        if(carDriver.getVelocity() == 0){

                            // value is 0.1 is because the program loops every 1/10th of a second
                            carDriver.setStoppingTime(0.1);
                        }

                        //increment
                        counter +=1;
                        nextCar +=1;

                        //reset the value to not get out of bound exceptions
                        if (nextCar == NUMB_OF_DRIVERS) {
                            nextCar = 0;
                        }

                        //calculate the average stopping time for the drivers
                        averageStoppingTime(lane);

//                        improvementPerc = Math.round((-stoppingPercentage+lastStoppingPerc) * 100)/100.0;
                        improvementPerc = Math.round(((lastStoppingPerc-stoppingPercentage)/lastStoppingPerc)*100);

                        //updates the labels to display info
                        passedTimeLbl.setText(String.format("Time passed: "+ (int)timePassed)+" s");
                        double roundedStoppingPercentage = Math.round(stoppingPercentage * 100)/100.0;
                        stopPercentageLbl.setText(String.format("Stopping percent: %s %% " , roundedStoppingPercentage));
                        improvementPercLbl.setText(String.format("Stopping time decreases by : %s %% " , improvementPerc));

                        if (selectedDriver != null) {
                            Vehicle selectedVehicle = selectedDriver.getVehicle();
                            Coordinate selectedVehicleCoordinate = selectedVehicle.getVehicleCoordinate();
                            double newX = selectedVehicleCoordinate.getX() + X_CENTER - 20;
                            double newY = selectedVehicleCoordinate.getY() + Y_CENTER-20;
                            double newAngle = selectedVehicleCoordinate.getAngle();
                            int newId = selectedDriver.getid();
                            double newDetection = selectedDriver.getDetection();
                            double newVelocity = selectedDriver.getVelocity();
                            String newName = selectedDriver.getClassName();
                            selectBorder.setX(newX);
                            selectBorder.setY(newY);
                            selectBorder.setRotate(newAngle);
                            carIdLbl.setText(String.format("Car ID: " + newId));
                            carTypeLbl.setText(String.format("Type Of Car: " + newName));
                            carDetectionLbl.setText(String.format("Detection: " + (int) newDetection)+ "m");
                            carVelocityLbl.setText(String.format("Velocity: " + (int) newVelocity) + "m/s");
                        }
                    }

                    //update the timestamp to the last time of irritation
                    lastUpdate = now;

                    // loop for displaying the info of the selected driver
                    for(int i = 0; i< carImgArray.length; i++) {
                        final CarDriver j = lane.get(i);
                        carImgArray[i].setOnMouseClicked((MouseEvent e) -> {

                            changeSelectedDriver(j);
                            carIdLbl.setText(String.format("Car ID: " + selectedDriver.getid()));
                            carTypeLbl.setText(String.format("Type Of Car: " + selectedDriver.getClassName()));
                            carDetectionLbl.setText(String.format("Detection: " + Double.toString((int) selectedDriver.getDetection())+ "m"));
                            carVelocityLbl.setText(String.format("Velocity: " + Double.toString((int) selectedDriver.getVelocity()) + "m/s"));
                            Vehicle selectedVehicle = selectedDriver.getVehicle();
                            Coordinate selectedVehicleCoordinate = selectedVehicle.getVehicleCoordinate();
                            selectBorder.setVisible(true);
                            selectBorder.setX(selectedVehicleCoordinate.getX() + X_CENTER - 20);
                            selectBorder.setY(selectedVehicleCoordinate.getY() + Y_CENTER-20);
                            selectBorder.setRotate(selectedVehicleCoordinate.getAngle());

                        });
                    }
                }

            }
        };

        stage.setScene(scene);
        stage.setResizable(false);
        stage.show();

        //sets the functionality of the start button
        startBtn.setOnAction(event -> {
            if(startBtn.isSelected()){
                timer.start();
                startBtn.setText("Stop");


            } else {
                timer.stop();
                startBtn.setText("Start");
            }
        });

        //sets the functionality of the phase switch button
        phaseSwitch.setOnAction(actionEvent -> {
            if(phaseSwitch.isSelected()){
                phaseSwitch.setText("Phase 1");
                phase = 2;
                restartBtn.fire();
            }else{
                phaseSwitch.setText("Phase 2");
                phase =1;
                restartBtn.fire();
            }
        });

        //sets the functionality of the start button
        restartBtn.setOnAction(actionEvent -> {
            lastStoppingPerc = Math.round(stoppingPercentage * 100)/100.0;

            lastStopPercLbl.setDisable(false);
            improvementPercLbl.setDisable(false);
            clear();
            startSim();
            selectBorder.setVisible(false);
            selectedDriver = null;
            carIdLbl.setText("Car ID: " );
            carTypeLbl.setText("Type Of Car: ");
            carDetectionLbl.setText("Detection: ");
            carVelocityLbl.setText("Velocity: ");
            lastStopPercLbl.setText(String.format("Previous Stopping percent: %s %% " , lastStoppingPerc));
        });
        //sets the functionality of the add cars button
        addCarsBtn.setOnAction(actionEvent -> {
            NUMB_OF_DRIVERS +=1;
            if (NUMB_OF_DRIVERS >=15)
                addCarsBtn.setDisable(true);
            if (NUMB_OF_DRIVERS >= 3)
                deleteCarBtn.setDisable(false);
            clear();
            startSim();
            selectBorder.setVisible(false);
            selectedDriver = null;
            carIdLbl.setText("Car ID: " );
            carTypeLbl.setText("Type Of Car: ");
            carDetectionLbl.setText("Detection: ");
            carVelocityLbl.setText("Velocity: ");

        });

        //sets the functionality of the delete cars button
        deleteCarBtn.setOnAction(actionEvent -> {
            NUMB_OF_DRIVERS -=1;
            if (NUMB_OF_DRIVERS <15)
                addCarsBtn.setDisable(false);
            if (NUMB_OF_DRIVERS <= 2)
                deleteCarBtn.setDisable(true);
            clear();
            startSim();
            selectBorder.setVisible(false);
            selectedDriver = null;
            carIdLbl.setText("Car ID: " );
            carTypeLbl.setText("Type Of Car: ");
            carDetectionLbl.setText("Detection: ");
            carVelocityLbl.setText("Velocity: ");

        });
    }
    /**
     * change the selected Driver
     */
    public void changeSelectedDriver(CarDriver newDriver){
        selectedDriver = newDriver;

    }

    /**
     * creates new driver objects and their images
     */
    public void startSim(){

        //random value for the starting pos
        startingAng = Math.random() * 360;

        //loop to create all the drivers and there images
        for(int i = 0; i< NUMB_OF_DRIVERS; i++){
            lane.add(RandomPicking(phase));
            CarDriver driver =lane.get(i);
            driver.Move(startingAng);
            startingAng +=10;
            int randomIndex = (int)(Math.random()* carImgDirArray.length);
            ImageView obj = new ImageView(carImgDirArray[randomIndex]);
            obj.setVisible(false);
            obj.setFitHeight(35);
            obj.setFitWidth(35);
            obj.setPickOnBounds(true);
            carImgArray[i] = obj;

        }
        root.getChildren().addAll(carImgArray);
    }

    /**
     * removes all previous drivers and their images
     */
    public void clear(){
        lane.clear();
        timePassed = 0;
        startingAng = 0;

        //remove every car image from the GUI
        for (ImageView carImg: carImgArray){
            carImg.imageProperty().set(null);
             root.getChildren().remove(carImg);
        }

        root.getChildren().removeAll(carImgArray);
        carImgArray = new ImageView[NUMB_OF_DRIVERS];

        CarDriver.setIdCounter(0);
    }

    /**
     * randomly chooses a driver based on the current phase
     * @param phase Which phase to get drivers from
     * @return  random CarDriver object
     */
    public  CarDriver RandomPicking(int phase) {
        Random rand = new Random();
        CarDriver theChosenDriver;
        int randDriverIndex = 1 + rand.nextInt(100);
        if (phase == 1) {

            if (randDriverIndex <= 40) {
                theChosenDriver = new RegularDriverP1();
            } else if (randDriverIndex <= 70) {
                theChosenDriver = new CarefulDriverP1();
            } else {
                theChosenDriver = new RecklessDriverP1();
            }

        }
        else {
            if (randDriverIndex <= 40) {
                theChosenDriver = new RegularDriverP2();
            } else if (randDriverIndex <= 70) {
                theChosenDriver = new CarefulDriverP2();
            } else {
                theChosenDriver = new RecklessDriverP2();
            }
        }
        return theChosenDriver;
    }

    /**
     * Calculate percentage of the average stopping time
     *
     * @param lane The arrayList of carDrivers
     */
    public void averageStoppingTime(ArrayList<CarDriver> lane){
        double avgTime;
        double timeSum = 0;
        for(CarDriver car:lane){
            timeSum  += car.getStoppingTime();
        }
        avgTime = timeSum/ NUMB_OF_DRIVERS;
        stoppingPercentage = avgTime/timePassed *100 ;
    }


    /**
     * main method
     * @param args
     */
    // Main method
    public static void main(String[] args) {
        launch();
    }
}