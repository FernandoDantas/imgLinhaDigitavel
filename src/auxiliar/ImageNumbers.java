package auxiliar;

import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.channels.FileChannel;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServlet;

import auxiliar.exceptions.ConvertToImageException;

public class ImageNumbers extends HttpServlet {
	private static final long serialVersionUID = 1L;

	// private static final String FIXED_PATH_TEMP = "temp";
	
	private static final String FIXED_PATH = "images" + "/" + "numeros" + "/";
	private static final String _0 = FIXED_PATH + "0.png";
	private static final String _1 = FIXED_PATH + "1.png";
	private static final String _2 = FIXED_PATH + "2.png";
	private static final String _3 = FIXED_PATH + "3.png";
	private static final String _4 = FIXED_PATH + "4.png";
	private static final String _5 = FIXED_PATH + "5.png";
	private static final String _6 = FIXED_PATH + "6.png";
	private static final String _7 = FIXED_PATH + "7.png";
	private static final String _8 = FIXED_PATH + "8.png";
	private static final String _9 = FIXED_PATH + "9.png";
	private static final String _BARRA = FIXED_PATH + "barra.png";
	private static final String _PONTO = FIXED_PATH + "ponto.png";
	private static final String _VIRGULA = FIXED_PATH + "virgula.png";
	private static final String _ESPACO = FIXED_PATH + "espaco.png";

	private static ArrayList<String> imagePaths = null;	
	
	public String rootPath = "C:\\Users\\boleto verde 02\\workspace\\imgLinhaDigitavel\\WebContent\\linhaDigitavel";   //"../temp"; 	
	public String rootPathSaida =  "C:\\Users\\boleto verde 02\\workspace\\imgLinhaDigitavel\\WebContent\\saida"; 						//"/millennium4/WebContent/saida";
	
	//public String rootPath = "linhaDigitavel"; 
	//public String rootPathSaida =  "saida_ld"; 						
	
	private String arg = null;
	private String imageName = null;

	BufferedImage argBufferedImages[] = null;

	public static void main(String[] args) {
		try {
			ImageNumbers in = new ImageNumbers("23791820000000045003211090000006577500253820");

		} catch (ConvertToImageException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public ImageNumbers(String arg) throws ConvertToImageException {

		System.out.println("Caminho principal  " + rootPath);		
		System.out.println("Caminho Saida  " + rootPathSaida);
		
		this.arg = arg.replace("&nbsp;", " ");

		if (imagePaths == null) {
			imagePaths = new ArrayList<String>();
		}

		this.generateImage();

	}

	private void generateImage() throws ConvertToImageException {

		char argChar[] = this.arg.toCharArray();

		String argPaths[] = new String[argChar.length];

		File argFiles[] = new File[argChar.length];

		BufferedImage argBufferedImages[] = new BufferedImage[argChar.length];

		int heightImg = 0;
		int widthImg = 0;
		try {
			for (int i = 0; i < argChar.length; i++) {
				argPaths[i] = this.getArgCharPath(argChar[i]);
				argFiles[i] = new File(argPaths[i]);

				argBufferedImages[i] = ImageIO.read(argFiles[i]); //

				if (i == 0) {
					heightImg = argBufferedImages[i].getHeight();
				}

				widthImg += argBufferedImages[i].getWidth() + (heightImg / 3);

			}

			BufferedImage img = new BufferedImage(widthImg, // Final image will have width and height as
					heightImg, // addition of widths and heights of the images we already have
					BufferedImage.TYPE_INT_ARGB);

			boolean imageDrawn = false;
			int positionX = 0;
			for (int i = 0; i < argBufferedImages.length; i++) {

				imageDrawn = img.createGraphics().drawImage(argBufferedImages[i], positionX, 0, null); // 0, 0 are the x
																										// and y
																										// positions

				positionX += argBufferedImages[i].getWidth() + (heightImg / 3);

				if (!imageDrawn) {
					throw new ConvertToImageException("Sua imagem não foi gerada com sucesso");
				}

			}

			if (this.rootPath == null) {
				throw new ConvertToImageException("Sua imagem não foi gerada com sucesso");
			}

			String finalImagePath = this.rootPath;

			if (!this.rootPath.endsWith("/" + "")) {
				finalImagePath += "/";
			}

			Date data = new Date(System.currentTimeMillis());
			this.imageName = data.getTime() + ".png";

			File finalImage = new File(finalImagePath + this.imageName);

			//boolean finalImageDrawing = 
			
		   ImageIO.write(img, "png", finalImage);

			//if (!finalImageDrawing) {
			//	this.imageName = null;
			//	throw new ConvertToImageException("Sua imagem não foi gerada com sucesso");
			//}

			imagePaths.add(finalImagePath + this.imageName);			
			
			File file = new File (finalImagePath + this.imageName); 
			System.out.println(file.getAbsolutePath());
			File fileSaida = new File (this.rootPathSaida + File.separator);   
			fileSaida.createNewFile();   
			 
			FileInputStream in = new FileInputStream(file);   
			FileOutputStream out = new FileOutputStream(fileSaida+ File.separator + file.getName());   
			byte[] buf = new byte[1024];     
			int len;     
			while ((len = in.read(buf)) > 0) {     
				out.write(buf, 0, len);     
			}
			out.flush();
			
			in.close();   
			out.close();			

			getImageURL();

		} catch (IOException e) {
			System.out.println("Sua imagem não foi gerada com sucesso");
			e.printStackTrace();
		}
	}

	// Copia file
	public void copiar(File fonte) {
		try {
			FileChannel in = new FileInputStream(fonte).getChannel();
			FileChannel out = new FileOutputStream(rootPathSaida).getChannel();
			out.transferFrom(in, 0, in.size());

			in.close();
			out.close();

		} catch (IOException ex) {
			ex.printStackTrace();
		}
	}

	private String getArgCharPath(char c) throws ConvertToImageException {
		if (this.rootPath == null) {
			throw new ConvertToImageException("Sua imagem não foi gerada com sucesso");
		}

		String result = this.rootPath;

		if (!this.rootPath.endsWith("/")) {
			result += "/";
		}

		switch (c) {

		case '0':
			return result + ImageNumbers._0;
		case '1':
			return result + ImageNumbers._1;
		case '2':
			return result + ImageNumbers._2;
		case '3':
			return result + ImageNumbers._3;
		case '4':
			return result + ImageNumbers._4;
		case '5':
			return result + ImageNumbers._5;
		case '6':
			return result + ImageNumbers._6;
		case '7':
			return result + ImageNumbers._7;
		case '8':
			return result + ImageNumbers._8;
		case '9':
			return result + ImageNumbers._9;
		case '/':
			return result + ImageNumbers._BARRA;
		case '.':
			return result + ImageNumbers._PONTO;
		case ',':
			return result + ImageNumbers._VIRGULA;
		case ' ':
			return result + ImageNumbers._ESPACO;

		default:
			throw new ConvertToImageException("Literal Inválido");
		}

	}

	public String getImageURL() throws ConvertToImageException {
		if (this.imageName == null) {
			throw new ConvertToImageException("Sua imagem não foi gerada com sucesso");
		}

		String result = this.imageName;
		System.out.println(result);
		return result;
	}

	public static void deletarImagens() {
		String path = null;
		File f = null;
		for (Iterator<String> iterator = imagePaths.iterator(); iterator.hasNext();) {
			path = iterator.next();
			f = new File(path);
			f.deleteOnExit();
			if (f.exists()) {
				f.delete();
			}
			if (f.exists()) {
				f.delete();
			}

		}
	}

}