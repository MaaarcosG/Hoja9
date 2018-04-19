
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.StringTokenizer;

import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;

/**
 * @author Marcos Gutierrez				17909
 * @author Raul Monzon					17014
 * Clase principal
 */
public class Main {
	public static void main(String[] args) throws IOException {
		Scanner teclado = new Scanner(System.in);
		/*ArrayList para las palabras del diccionario*/
		ArrayList<String> diccionario = new ArrayList<String>();
		/*Asociamos la clase BinaryTree con un atributo*/
		BinaryTree<String, String> palabra = new BinaryTree<String,String>();
		/*Atributos para los datos*/
		String traducido = "";
		String dirrección="";
		
		System.out.println("Seleccione el archivo que tenga el diccionario");
		teclado.nextLine();
		
		/*Escogemos el archivo con el diccionario*/
		JFileChooser chooser = new JFileChooser();
		chooser.setCurrentDirectory(new java.io.File("./src"));
		chooser.setDialogTitle("Seleccione el archivo que desea");
		chooser.setFileFilter(new FileNameExtensionFilter("DIC (.dic", "dic"));

		int valor = chooser.showOpenDialog(null);
		if(valor == JFileChooser.APPROVE_OPTION) {
			try {
				/*Leemos el archivo de texto*/
				FileInputStream stream = new FileInputStream(chooser.getSelectedFile().getAbsolutePath());
				BufferedReader reader = new BufferedReader(new InputStreamReader(stream));
				
				String linea;
				while((linea = reader.readLine()) != null) {
					/*Agregamos la línea al diccionario*/
					diccionario.add(linea);
				}
				
				/*Separamos las lineas del archivo para lograr hacer la asociacion*/
				for(int i = 0; i<diccionario.size(); i++) {
					String datos = diccionario.get(i).substring(i, diccionario.get(i).length() - 1);
					String[] unicornio = datos.split("");
					palabra.insert(unicornio[0], unicornio[1]);
				}
			} catch(Exception e) {
				System.err.println("El .txt esta vacio");
			}
		}
		
		
		
		//Seleccionamos archivo que deseemos traducir
		System.out.println("Seleccione el archivo que desea traducir");
        teclado.nextLine();
        JFileChooser chooser2 = new JFileChooser(System.getProperty("java.class.path"));
        chooser2.setDialogTitle("Seleccione su archivo");
        chooser2.setFileFilter(new FileNameExtensionFilter("Text  (.txt)", "txt"));
        int returnVal2 = chooser2.showOpenDialog(null);
        if(returnVal2 == JFileChooser.APPROVE_OPTION) {
            try {
                Scanner inputFile2 = new Scanner(new File(chooser2.getSelectedFile().getAbsolutePath()));
                dirrección = chooser2.getSelectedFile().getAbsolutePath();
            }
            catch (FileNotFoundException e) {
                System.out.println("No ha seleccionado ningun archivo");

            }
        }
        try
        {
            BufferedReader informacion = new BufferedReader(new FileReader(dirrección));  // Abre documento para su lectura
            String rawText = informacion.readLine();  // Lee la unica linea y la guarda como string
            /*en caso de punto al final*/
            rawText = rawText.substring(0, rawText.length()-1);
            String textoATraducir = rawText.toLowerCase();
            informacion.close();  // Se finaliza el lector
            System.out.println("El texto Originas es: \n" + textoATraducir);
            String wIngles, wEspanol;	
            StringTokenizer st = new StringTokenizer (textoATraducir);

            /* traduce las palabras*/
            while (st.hasMoreTokens())
            {
                wIngles = st.nextToken();
                wEspanol = palabra.find(wIngles);
                if (wEspanol != null){
                	traducido = (traducido +" "+ wEspanol);
                }
                else{
                	traducido = (traducido + " *"+wIngles+"*");
                }

            }
            System.out.println("Traducción: \n");
            traducido = traducido + ".";
            System.out.println(traducido);
        }
        catch(FileNotFoundException e)
        {
            System.out.println("El archivo que ha seleccionado no es valido");
        }
        System.out.println("\nLas palbras entre astericos, son las que no estan en el diccionario");
        System.out.println("Palabras dentro del diccionario utlizado (inOrder): \n");
        palabra.display(palabra.root);


		
	}

}
