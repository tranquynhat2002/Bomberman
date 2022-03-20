package uet.oop.bomberman.game;

import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;
import javafx.stage.Stage;
import javafx.scene.layout.Pane;
import uet.oop.bomberman.entities.character.Bomber;
import uet.oop.bomberman.graphics.Sprite;
import javafx.scene.canvas.Canvas;
import uet.oop.bomberman.map.LevelMap;
import javafx.scene.effect.DropShadow;
import javafx.scene.effect.ColorAdjust;
import uet.oop.bomberman.sound.Sound;

import java.io.InputStream;

import static uet.oop.bomberman.game.GamePlay.*;

public class GameBomberman extends Application {
    public static final int WIDTH = 31;
    public static final int HEIGHT = 13;

    public static GraphicsContext graphicsContext;
    public static Canvas canvas;
    private GamePlay gamePlay = new GamePlay();
    private LevelMap levelMap = new LevelMap(gamePlay);

    public int numLevel = 1;
    public int timeSub = 1;

    public int timeAppear = 5;
    public static void main(String[] args) {
        Application.launch(GameBomberman.class);
    }

    @Override
    public void start(Stage stage) {
        // Tao Canvas
        Sound.playGame("menu");
        canvas = new Canvas();
        Pane paneStatus = new Pane();
        paneStatus.setStyle("-fx-background-color: black");
        //set width and height of canvas
        canvas.setHeight(Sprite.SCALED_SIZE * HEIGHT);
        canvas.setWidth(Sprite.SCALED_SIZE * WIDTH);

        graphicsContext = canvas.getGraphicsContext2D();

        // set layout for canvas.
        canvas.setLayoutX((1000 / 2 - Sprite.SCALED_SIZE * WIDTH / 2));
        canvas.setLayoutY(50);

        // Tao root container
        paneStatus.getChildren().add(canvas);

        //THANH CONG CU TREN DAU
        //score.
        Label scoreLabel = new Label(String.format("Score: %d", gamePlay.scoreGame.getScoreTotal()));
        scoreLabel.setTextFill(Color.WHITE);
        scoreLabel.setLayoutX(550);
        scoreLabel.setLayoutY(10);
        paneStatus.getChildren().add(scoreLabel);

        //time.
        Label timeLabel = new Label(String.format("Time: %d", timeLimit));
        timeLabel.setTextFill(Color.WHITE);
        timeLabel.setLayoutX(300);
        timeLabel.setLayoutY(10);
        paneStatus.getChildren().add(timeLabel);

        //level.
        Label levelLabel = new Label(String.format("Level : %d", numLevel));
        levelLabel.setTextFill(Color.WHITE);
        levelLabel.setLayoutX(50);
        levelLabel.setLayoutY(10);
        paneStatus.getChildren().add(levelLabel);

        //lives
        Label liveLabel = new Label(String.format("Live : %d", lives));
        liveLabel.setTextFill(Color.WHITE);
        liveLabel.setLayoutX(850);
        liveLabel.setLayoutY(10);
        paneStatus.getChildren().add(liveLabel);
        Scene sceneGame = new Scene(paneStatus, Sprite.SCALED_SIZE * WIDTH + 2, Sprite.SCALED_SIZE * HEIGHT + 50);

        DropShadow dropShadow = new DropShadow();
        ColorAdjust colorAdjust = new ColorAdjust();
        colorAdjust.setBrightness(0.4);
        //SCENE MENU
        // tao scene Start.
        Group root = new Group();
        root.getChildren().addAll(new Rectangle(Sprite.SCALED_SIZE * WIDTH, Sprite.SCALED_SIZE * HEIGHT, Color.BLACK));
        Class<?> clazz = this.getClass();

        InputStream input = clazz.getResourceAsStream("/textures/start2.png");
        Image image = new Image(input,Sprite.SCALED_SIZE * WIDTH, Sprite.SCALED_SIZE * HEIGHT,false,true);
        ImageView imageView = new ImageView(image);
        imageView.setLayoutX(0);
        imageView.setLayoutY(0);
        root.getChildren().addAll(imageView);

        // button chon choi binh thuong.
        Button play = new Button("Play");
        play.setStyle("-fx-background-color: #000000, linear-gradient(#7ebcea, #2f4b8f),linear-gradient(#426ab7, #263e75),linear-gradient(#395cab, #223768);"
                + "-fx-background-insets: 0,1,2,3;" + "-fx-background-radius: 3,2,2,2;" + " -fx-padding: 8 155 8 155;"
                + "-fx-text-fill: white; -fx-font-size: 18px; -fx-font-weight: bold");
        play.setTranslateX(320);
        play.setTranslateY(180);
        root.getChildren().add(play);
        play.addEventHandler(MouseEvent.MOUSE_ENTERED, new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                play.setEffect(dropShadow);
                play.setEffect(colorAdjust);
            }
        });
        play.addEventHandler(MouseEvent.MOUSE_EXITED, new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                play.setEffect(null);
            }
        });

        //button chon instruction
        Button instruction = new Button("Instruction");
        instruction.setStyle("-fx-background-color: #000000, linear-gradient(#7ebcea, #2f4b8f),linear-gradient(#426ab7, #263e75),linear-gradient(#395cab, #223768);"
                + "-fx-background-insets: 0,1,2,3;" + "-fx-background-radius: 3,2,2,2;" + " -fx-padding: 8 127 8 127;"
                + "-fx-text-fill: white; -fx-font-size: 18px; -fx-font-weight: bold");
        instruction.setLayoutX(320);
        instruction.setLayoutY(240);
        root.getChildren().add(instruction);
        instruction.addEventHandler(MouseEvent.MOUSE_ENTERED, new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                instruction.setEffect(dropShadow);
                instruction.setEffect(colorAdjust);
            }
        });
        instruction.addEventHandler(MouseEvent.MOUSE_EXITED, new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                instruction.setEffect(null);
            }
        });

        // button chon thoat
        Button exit = new Button("Exit");
        exit.setStyle("-fx-background-color: #000000, linear-gradient(#7ebcea, #2f4b8f),linear-gradient(#426ab7, #263e75),linear-gradient(#395cab, #223768);"
                + "-fx-background-insets: 0,1,2,3;" + "-fx-background-radius: 3,2,2,2;" + " -fx-padding: 8 157 8 157;"
                + "-fx-text-fill: white; -fx-font-size: 18px; -fx-font-weight: bold");
        exit.setLayoutX(320);
        exit.setLayoutY(300);
        root.getChildren().add(exit);
        exit.addEventHandler(MouseEvent.MOUSE_ENTERED, new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                exit.setEffect(dropShadow);
                exit.setEffect(colorAdjust);
            }
        });
        exit.addEventHandler(MouseEvent.MOUSE_EXITED, new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                exit.setEffect(null);
            }
        });

        Scene sceneIntro = new Scene(root, Sprite.SCALED_SIZE * WIDTH, Sprite.SCALED_SIZE * HEIGHT);
        stage.setScene(sceneIntro);
        stage.show();

        //SCENE INSTRUCTION 1
        Pane paneIns = new Pane();

        InputStream ins1 = clazz.getResourceAsStream("/textures/ins1.png");
        Image insImage = new Image(ins1,Sprite.SCALED_SIZE * WIDTH, Sprite.SCALED_SIZE * HEIGHT,false,true);
        ImageView insImageView = new ImageView(insImage);
        insImageView.setLayoutX(0);
        insImageView.setLayoutY(0);
        paneIns.getChildren().addAll(insImageView);

        Button next = new Button();
        next.setStyle("-fx-background-color: black");
        next.setTranslateX(850);
        next.setTranslateY(30);
        InputStream mt = clazz.getResourceAsStream("/textures/muiten.png");
        Image mten = new Image(mt, 50, 41, false, true);
        ImageView muiten = new ImageView(mten);
        next.setGraphic(muiten);
        paneIns.getChildren().add(next);
        InputStream mt0 = clazz.getResourceAsStream("/textures/muiten.png");
        Image mten0 = new Image(mt0, 59, 50, false, true);
        ImageView muiten0 = new ImageView(mten0);
        next.addEventHandler(MouseEvent.MOUSE_ENTERED, new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                next.setGraphic(muiten0);
            }
        });
        next.addEventHandler(MouseEvent.MOUSE_EXITED, new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                next.setGraphic(muiten);
            }
        });

        //SCENE INSTRUCTION 2
        Pane paneIns2 = new Pane();
        InputStream ins2 = clazz.getResourceAsStream("/textures/ins2.png");
        Image insImage2 = new Image(ins2,Sprite.SCALED_SIZE * WIDTH, Sprite.SCALED_SIZE * HEIGHT,false,true);
        ImageView insImageView2 = new ImageView(insImage2);
        insImageView2.setLayoutX(0);
        insImageView2.setLayoutY(0);
        paneIns2.getChildren().addAll(insImageView2);

        Button pre = new Button();
        pre.setStyle("-fx-background-color: black");
        pre.setTranslateX(720);
        pre.setTranslateY(360);
        InputStream mt1 = clazz.getResourceAsStream("/textures/muiten1.png");
        Image mten1 = new Image(mt1, 50, 41, false, true);
        ImageView muiten1 = new ImageView(mten1);
        pre.setGraphic(muiten1);
        paneIns2.getChildren().add(pre);
        InputStream mt11 = clazz.getResourceAsStream("/textures/muiten1.png");
        Image mten11 = new Image(mt11, 59, 50, false, true);
        ImageView muiten11 = new ImageView(mten11);
        pre.addEventHandler(MouseEvent.MOUSE_ENTERED, new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                pre.setGraphic(muiten11);
            }
        });
        pre.addEventHandler(MouseEvent.MOUSE_EXITED, new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                pre.setGraphic(muiten1);
            }
        });

        Button exitIns = new Button();
        exitIns.setStyle("-fx-background-color: black");
        exitIns.setTranslateX(900);
        exitIns.setTranslateY(360);
        InputStream exitIn = clazz.getResourceAsStream("/textures/exit.png");
        Image exitImage = new Image(exitIn, 41, 41, false, true);
        ImageView exitInsView = new ImageView(exitImage);
        exitIns.setGraphic(exitInsView);
        paneIns2.getChildren().add(exitIns);
        exitIns.setOnMouseClicked(mouseEvent -> {
            System.exit(0);
        });
        InputStream exitIn1 = clazz.getResourceAsStream("/textures/exit.png");
        Image exitImage1 = new Image(exitIn1, 50, 50, false, true);
        ImageView exitInsView1 = new ImageView(exitImage1);
        exitIns.addEventHandler(MouseEvent.MOUSE_ENTERED, new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                exitIns.setGraphic(exitInsView1);
            }
        });
        exitIns.addEventHandler(MouseEvent.MOUSE_EXITED, new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                exitIns.setGraphic(exitInsView);
            }
        });

        Button homeIns = new Button();
        homeIns.setStyle("-fx-background-color: black");
        homeIns.setTranslateX(815);
        homeIns.setTranslateY(360);
        InputStream homeInsIn = clazz.getResourceAsStream("/textures/home.png");
        Image homeInsIm = new Image(homeInsIn, 41, 41, false, true);
        ImageView homeInsView = new ImageView(homeInsIm);
        homeIns.setGraphic(homeInsView);
        paneIns2.getChildren().add(homeIns);
        InputStream homeInsIn1 = clazz.getResourceAsStream("/textures/home.png");
        Image homeInsIm1 = new Image(homeInsIn1, 50, 50, false, true);
        ImageView homeInsView1 = new ImageView(homeInsIm1);
        homeIns.setOnMouseClicked(mouseEvent -> {
            stage.setScene(sceneIntro);
        });
        homeIns.addEventHandler(MouseEvent.MOUSE_ENTERED, new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                homeIns.setGraphic(homeInsView1);
            }
        });
        homeIns.addEventHandler(MouseEvent.MOUSE_EXITED, new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                homeIns.setGraphic(homeInsView);
            }
        });

        Scene sceneIns1 = new Scene(paneIns, Sprite.SCALED_SIZE * WIDTH, Sprite.SCALED_SIZE * HEIGHT);
        pre.setOnMouseClicked(mouseEvent -> {
            stage.setScene(sceneIns1);
        });
        Scene sceneIns2 = new Scene(paneIns2, Sprite.SCALED_SIZE * WIDTH, Sprite.SCALED_SIZE * HEIGHT);
        next.setOnMouseClicked(mouseEvent -> {
            stage.setScene(sceneIns2);
        });

        //SCENE GAME OVER
        Pane paneGameOver = new Pane();
        InputStream gameOverIn = clazz.getResourceAsStream("/textures/gameover.png");

        Image gameOverIm = new Image(gameOverIn,Sprite.SCALED_SIZE * WIDTH, Sprite.SCALED_SIZE * HEIGHT,false,true);
        ImageView gameOverView = new ImageView(gameOverIm);
        gameOverView.setLayoutX(0);
        gameOverView.setLayoutY(0);
        paneGameOver.getChildren().addAll(gameOverView);

        Button exitGameOver = new Button();
        exitGameOver.setStyle("-fx-background-color: black");
        exitGameOver.setTranslateX(630);
        exitGameOver.setTranslateY(300);
        InputStream exitOver = clazz.getResourceAsStream("/textures/exit.png");
        Image exitIm = new Image(exitOver, 55, 55, false, true);
        ImageView exitOverView = new ImageView(exitIm);
        exitGameOver.setGraphic(exitOverView);
        paneGameOver.getChildren().add(exitGameOver);
        exitGameOver.setOnMouseClicked(mouseEvent -> {
            System.exit(0);
        });
        InputStream exitOver1 = clazz.getResourceAsStream("/textures/exit.png");
        Image exitIm1 = new Image(exitOver1, 64, 64, false, true);
        ImageView exitOverView1 = new ImageView(exitIm1);

        exitGameOver.addEventHandler(MouseEvent.MOUSE_ENTERED, new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                exitGameOver.setGraphic(exitOverView1);
            }
        });
        exitGameOver.addEventHandler(MouseEvent.MOUSE_EXITED, new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                exitGameOver.setGraphic(exitOverView);
            }
        });

        Button newGameOver = new Button();
        newGameOver.setStyle("-fx-background-color: black");
        newGameOver.setTranslateX(330);
        newGameOver.setTranslateY(300);
        InputStream newGameIn = clazz.getResourceAsStream("/textures/newgame.png");
        Image newGameIm = new Image(newGameIn, 57, 57, false, true);
        ImageView newGameView = new ImageView(newGameIm);
        newGameOver.setGraphic(newGameView);
        paneGameOver.getChildren().add(newGameOver);
        newGameOver.setOnMouseClicked(mouseEvent -> {
            gamePlay.resetGame();
            numLevel = 1;
            gamePlay.scoreGame.setScoreTotal(0);
            gamePlay.scoreGame.setScoreLevel(0);
            lives = 3;
            levelLabel.setText(String.format("Level : %d", numLevel));
            scoreLabel.setText(String.format("Score: %d", gamePlay.scoreGame.getScoreTotal()));
            liveLabel.setText(String.format("Live : %d", lives));
            levelMap.createMap(1);
            stage.setScene(sceneGame);
        });
        InputStream newGameIn1 = clazz.getResourceAsStream("/textures/newgame.png");
        Image newGameIm1 = new Image(newGameIn1, 66, 66, false, true);
        ImageView newGameView1 = new ImageView(newGameIm1);

        newGameOver.addEventHandler(MouseEvent.MOUSE_ENTERED, new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                newGameOver.setGraphic(newGameView1);
            }
        });
        newGameOver.addEventHandler(MouseEvent.MOUSE_EXITED, new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                newGameOver.setGraphic(newGameView);
            }
        });

        Label markTotal = new Label();
        markTotal.setFont(Font.font("Arial", FontWeight.BOLD, 40));
        markTotal.setTextFill(Color.WHITE);
        markTotal.setTextAlignment(TextAlignment.CENTER);
        markTotal.setLayoutX(380);
        markTotal.setLayoutY(200);
        paneGameOver.getChildren().addAll(markTotal);

        Label notification = new Label();
        notification.setFont(Font.font("Arial", FontWeight.BOLD, 45));
        notification.setTextFill(Color.RED);
        notification.setTextAlignment(TextAlignment.CENTER);
        notification.setLayoutX(400);
        notification.setLayoutY(140);
        paneGameOver.getChildren().addAll(notification);

        Scene sceneOver =  new Scene(paneGameOver);

        //SCENE WIN
        Pane paneWin = new Pane();
        InputStream winIn = clazz.getResourceAsStream("/textures/win.png");

        Image winIm = new Image(winIn,Sprite.SCALED_SIZE * WIDTH, Sprite.SCALED_SIZE * HEIGHT,false,true);
        ImageView winView = new ImageView(winIm);
        paneWin.setLayoutX(0);
        paneWin.setLayoutY(0);
        paneWin.getChildren().addAll(winView);

        Button exitWin = new Button();
        exitWin.setStyle("-fx-background-color: light green");
        exitWin.setTranslateX(820);
        exitWin.setTranslateY(330);
        InputStream exitWinIn = clazz.getResourceAsStream("/textures/exit.png");
        Image exitWinIm = new Image(exitWinIn, 55, 55, false, true);
        ImageView exitWinView = new ImageView(exitWinIm);
        exitWin.setGraphic(exitWinView);
        paneWin.getChildren().add(exitWin);
        exitWin.setOnMouseClicked(mouseEvent -> {
            System.exit(0);
        });
        InputStream exitWinIn1 = clazz.getResourceAsStream("/textures/exit.png");
        Image exitWinIm1 = new Image(exitWinIn1, 64, 64, false, true);
        ImageView exitWinView1 = new ImageView(exitWinIm1);

        exitWin.addEventHandler(MouseEvent.MOUSE_ENTERED, new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                exitWin.setGraphic(exitWinView1);
            }
        });
        exitWin.addEventHandler(MouseEvent.MOUSE_EXITED, new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                exitWin.setGraphic(exitWinView);
            }
        });
        Label markWin = new Label();
        markWin.setFont(Font.font("Arial", FontWeight.BOLD, 50));
        markWin.setTextFill(Color.BROWN);
        markWin.setTextAlignment(TextAlignment.CENTER);
        markWin.setLayoutX(350);
        markWin.setLayoutY(230);
        paneWin.getChildren().addAll(markWin);

        Scene sceneWin = new Scene(paneWin);


        //CLICK CHƠI
        play.setOnMouseClicked(mouseEvent -> {
            stage.setScene(sceneGame);
            stage.show();
            levelMap.createMap(numLevel);

            AnimationTimer timer = new AnimationTimer() {
                public void handle(long l) {
                    gamePlay.render();
                    gamePlay.update();
                    scoreLabel.setText(String.format("Score: %d", gamePlay.scoreGame.getScoreTotal()));

                    if (timeSub > 60) {
                        timeSub = 0;
                        timeLimit--;
                    } else {
                        timeSub++;
                    }

                    if (timeLimit == 0) {
                        markTotal.setText("Score : " + gamePlay.scoreGame.getScoreTotal());
                        notification.setText("Time over!");
                        stage.setScene(sceneOver);
                    }

                    timeLabel.setText(String.format("Time: %d", timeLimit));

                    if (checkChangeLevel && lives >= 0) {
                        gamePlay.scoreGame.setScoreLevel(0);
                        numLevel = numLevel + 1;
                        if (numLevel <= 2) {
                            gamePlay.resetGame();
                            levelMap.createMap(numLevel);
                            levelLabel.setText(String.format("Level: %d", numLevel));
                            checkChangeLevel = false;
                        } else {
                            markWin.setText("Score : " + gamePlay.scoreGame.getScoreTotal());
                            stage.setScene(sceneWin);
                        }
                    }

                    if (gamePlay.bomberman.get(0).isCheckRemoved()) {
                        lives--;
                        if (lives > 0) {
                            liveLabel.setText(String.format("Live: %d", lives));
                            bomberman.clear();
                            Bomber newBomber = new Bomber(gamePlay.startBomberX, gamePlay.startBomberY, gamePlay);
                            bomberman.add(newBomber);
                            timeLimit = 200;
                        } else {
                            markTotal.setText("Score : " + gamePlay.scoreGame.getScoreTotal());
                            notification.setText("You died!");
                            stage.setScene(sceneOver);
                        }
                    }
                }
            };
            timer.start();
            sceneGame.setOnKeyPressed(new EventHandler<KeyEvent>() {
                @Override
                public void handle(KeyEvent event) {
                    gamePlay.bomberman.get(bomberman.size() - 1).keyBoard(event);
                }
            });
            Sound.playLoop("play");
        });

        instruction.setOnMouseClicked(mouseEvent -> {
            stage.setScene(sceneIns1);
        });
        exit.setOnMouseClicked(mouseEvent -> {
            System.exit(0);
        });
    }
}


