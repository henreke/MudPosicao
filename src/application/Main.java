package application;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;

import javafx.application.Application;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;


public class Main extends Application {

	private Stage primaryStage;
	private BorderPane rootLayout;

	public ArrayList<posicoes> posicao = new ArrayList<posicoes>();
	private posicoes posicao_corrente;
	private int id_corrente = 0;

	@FXML
	private ImageView imgView4;

	@FXML
	private Label lab;

	@Override
	public void start(Stage primaryStage) {
		try {
			this.primaryStage = primaryStage;
			this.primaryStage.setTitle("Posição");

			initRootLayout();
			//BufferedImage imagem = ImageIO.read(Main.class.getClassLoader().getResourceAsStream("DF.png"));
			//Image imagem2 = SwingFXUtils.toFXImage(imagem, null);
			Image imagem2 = new Image("DLD.png");
			System.out.print(imagem2.getWidth());
			System.out.print(imagem2.getHeight());
			//File file = new File("C:\\Users\\kbqk\\git\\MudPosicao\\bin\\DF.png");
			//Image imagem = new Image(file.toURI().toString());
			//imgView.setImage(new Image("DLD.png"));
			ImageView imgView2 =  new ImageView(imagem2);

			//System.out.println(lab.getText());

		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	public void initRootLayout() {
        try {
            // Carrega o root layout do arquivo fxml.
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(Main.class.getResource("MainLayout.fxml"));
            rootLayout = (BorderPane) loader.load();

            // Mostra a scene (cena) contendo o root layout.
            Scene scene = new Scene(rootLayout);
            primaryStage.setScene(scene);
            primaryStage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
	public Timer timerUpdate;
	@FXML
	private void teste(){
		posicao.add(new posicoes(new Image("DLD.png"),0,posicao.size()));
		posicao.add(new posicoes(new Image("DF.png"),0,posicao.size()));
		posicao.add(new posicoes(new Image("DLE.png"),0,posicao.size()));
		posicao_corrente = posicao.get(id_corrente);
		posicao_corrente.hora_executada = System.currentTimeMillis();
		id_corrente = (id_corrente+1) % posicao.size();
		imgView4.setImage(posicao_corrente.imagem);
		timerUpdate = new Timer();
		timerUpdate.scheduleAtFixedRate(new RelogioUpdate(), 2000, 1000);

	}
	public static void main(String[] args) {
		launch(args);
	}


	class posicoes{
		public Image imagem;
		public long hora_executada;
		public int id;

		public posicoes(Image imagem, long hora_executada, int id){
			this.imagem = imagem;
			this.hora_executada = hora_executada;
			this.id = id;

		}
	}

	class RelogioUpdate extends TimerTask{


		@Override
		public void run() {
			// TODO Auto-generated method stub
			try {

				if (System.currentTimeMillis() - posicao_corrente.hora_executada > 60000){
					posicao_corrente = posicao.get(id_corrente);
					posicao_corrente.hora_executada = System.currentTimeMillis();
					id_corrente = (id_corrente+1) % posicao.size();
					imgView4.setImage(posicao_corrente.imagem);
				}
				else
				{
					long passado = posicao_corrente.hora_executada - System.currentTimeMillis();
					System.out.print("Se passaram ");
					System.out.println(passado);
				}
			}
			catch (Exception e) {
				// TODO: handle exception
			}
		}


	}
}
