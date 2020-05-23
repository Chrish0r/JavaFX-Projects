package version1;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.event.EventHandler;
import javafx.geometry.Rectangle2D;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.effect.Reflection;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.stage.Screen;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.IOException;


public class Main extends Application {
    final String[] icons = {
            "version1/Images/firefox.png",
            "version1/Images/eclipse.png",
            "version1/Images/intelli.png",
            "version1/Images/textedit.png"
    };

    Rectangle2D screenSize = Screen.getPrimary().getVisualBounds();
    
    @Override
    public void start(Stage primaryStage) throws Exception {
        // Container
        Group root = new Group();

        // Dock
        ImageView imageViewDock = new ImageView(new Image("version1/Images/dock.png"));
        imageViewDock.setTranslateX(12);
        imageViewDock.setTranslateY(100);

        root.getChildren().add(imageViewDock);

        // adding images
        for(int i = 0; i < icons.length; i++) {
            final ImageView icon = new ImageView(new Image(icons[i]));
            icon.setTranslateX(90 + 80 * i);;
            icon.setTranslateY(100);
            icon.setScaleX(0.8);
            icon.setScaleY(0.8);
            icon.setEffect(new Reflection());
            zoomIn(icon);
            zoomOut(icon);
            clickOnIcon(i, icon);

            root.getChildren().add(icon);
        }

       // EventHandling and Listeners
          // Exit
        imageViewDock.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                if(event.getButton().equals(MouseButton.PRIMARY) && event.getClickCount() >= 2) {
                    Platform.exit(); // recommended to close JavaFX-applications with this command (smoother shut down), since System.exit(0= shuts down the whole JVM.
                }
            }
        });

        // Scene
        Scene scene = new Scene(root, 500, 200);
        scene.setFill(Color.TRANSPARENT);
        primaryStage.initStyle(StageStyle.TRANSPARENT);
        primaryStage.setX(screenSize.getWidth()/2 - 280);
        primaryStage.setY(screenSize.getHeight() - 200);
        primaryStage.setScene(scene);
        primaryStage.setResizable(false);
        primaryStage.show();
    }

    // Zoom in/out
    public void zoomIn(ImageView icon) {
        icon.setOnMouseEntered(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                icon.setScaleX(1.0);
                icon.setScaleY(1.0);
            }
        });
    }

    public void zoomOut(ImageView icon) {
        icon.setOnMouseExited(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                icon.setScaleX(0.8);
                icon.setScaleY(0.8);
            }
        });
    }

    // click on icon -> open program
    public void clickOnIcon(int index, ImageView icon) {
        icon.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                switch(index) {
                    case 0:
                        System.out.println("Firefox");
                        String path = "C:\\Program Files\\Mozilla Firefox\\firefox.exe";
                        startProgram(path);
                        break;
                    case 1:
                        System.out.println("Eclipse");
                        path = "C:\\Users\\User\\eclipse\\java-2019-06\\eclipse\\eclipse.exe";
                     //   path = "C:\\ProgramData\\Microsoft\\Windows\\Start Menu\\Programs\\JetBrains";
                        startProgram(path);
                        break;
                    case 2:
                        System.out.println("Intelli");
                        path = "C:\\Program Files\\JetBrains\\IntelliJ IDEA 2019.3.2\\bin\\idea64.exe";
                        startProgram(path);
                        break;
                    case 3:
                        System.out.println("Editor");
                        path = "C:\\Windows\\System32\\notepad.exe";
                        startProgram(path);
                        break;
                    default:
                        break;
                }
            }
        });
    }

    public void startProgram(String path) {
        String cmd = path;
        try {
            Process process = Runtime.getRuntime().exec(cmd);
            try {
                Thread.sleep(5000);
            } catch (Exception e) {
                e.printStackTrace();
            }
            System.out.println("Programm Ende.");
          //  process.destroy(); // nach 5 Sekunden soll der Prozess beendet werden, damit keine weiteren Ressourcen mehr verbraucht werden
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        launch(args);
    }
}
