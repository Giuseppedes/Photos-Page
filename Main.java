import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;

import javax.imageio.ImageIO;
import javax.swing.JFileChooser;
import javax.swing.JFrame;


public class Main {

	//img ad alta risoluzione
	public static final String ROOT1 = "../foto-img/SPECIFIC_DIRECTORY/orig/";
	//img a bassa risoluzione
	public static final String ROOT2 = "../foto-img/SPECIFIC_DIRECTORY/small/";
	//path del file chooser
	public static final String PATH = System.getProperty("user.home") + "/Git/Sito-Euterpe/html/foto-img";

	public static void main(String[] args) throws FileNotFoundException {

		//Seleziona tutti i file da inserire nella pagina

		JFileChooser fileChooser = new JFileChooser();
		fileChooser.setMultiSelectionEnabled(true);
		fileChooser.setCurrentDirectory(new File(PATH));
		int result = fileChooser.showOpenDialog(new JFrame());
		if (result == JFileChooser.APPROVE_OPTION) {

            PrintWriter writer = new PrintWriter("output.html");

			File[] selectedFiles = fileChooser.getSelectedFiles();

			writer.println("<div class=\"container\">");

			int i = 0;

			for (i=0; i<selectedFiles.length ; i++){

				//solo il nome del file, senza tutto il percorso
				String nomeFile= selectedFiles[i].getPath().substring(selectedFiles[i].getPath().lastIndexOf("/")+1);

				//serve per prendere larghezza e altezza dell'img
				BufferedImage img = null;
				try {
				    img = ImageIO.read(new File(selectedFiles[i].getPath()));
				} catch (IOException e) {
				}

				if (i%3 == 0) {
					writer.println("<div class=\"row\">");
				}

				writer.println("<div class=\"col-sm-4\">");

				//crea nuova finestra delle dimensioni specificate
				writer.println("<a onclick=\"window.open('"+ ROOT1 + nomeFile +"','name','width="+img.getWidth()+",height="+img.getHeight()+"')\">");

				//crea nuova tab o finestra
				//writer.print("<a target=\"_blank\" href =\"" + ROOT1 + nomeFile + "\">");

				writer.println("<img src=\"" + ROOT2 + nomeFile + "\" />\n</a>");

				writer.println("</div>");

				if (i%3 == 2) {
					writer.println("</div>");
					writer.println("<p> </p>");
				}

			}

			if (i%3 != 0) {
				writer.println("</div>");
				writer.println("<p> </p>");
			}

			writer.println("</div>");

            writer.close();

		}

		System.out.println("DONE!!");

		System.exit(0);

	}


}
