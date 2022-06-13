import java.util.HashMap;
import java.util.Objects;

import javax.swing.JTextArea;
import javax.swing.JTextField;


public class MT {


	int pasos=0;
	char vacio;
	String cadena;
	int pocicion;
	String siguienteNodo;
	String[] nodosDeAceptacion;
	HashMap<String, Nodo> nodos;
	

	public MT() {//solo para hacer pruebas rápidas
		
		this.vacio = 'b';
		this.cadena = "";
		this.pocicion = 0;
		this.siguienteNodo = "q0";
		this.nodosDeAceptacion = new String[]{ "q7" };

		this.nodos = new HashMap<>();
		this.nodos.put("q0", new Nodo(new String[] { "0q1xD", "yq5yD" }));
		this.nodos.put("q1", new Nodo(new String[] { "0q10D", "1q2yD", "yq1yD" }));
		this.nodos.put("q2", new Nodo(new String[] { "0q3zD", "1q21D", "zq2zD" }));
		this.nodos.put("q3", new Nodo(new String[] { "0q30D", "bq4bI" }));
		this.nodos.put("q4", new Nodo(new String[] { "0q40I", "1q41I", "xq0xD", "yq4yI", "zq4zI" }));
		this.nodos.put("q5", new Nodo(new String[] { "yq5yD", "zq6zD" }));
		this.nodos.put("q6", new Nodo(new String[] { "zq6zD", "bq7bP" }));

	}
				//En el contructor se recibe lo necesario para el funcionamiento de la MT
				// donde se pide la cadena a analizar su pocicion, los nodos necesarios y los estados finales e iniciales
	public MT(char vacio, String cadena, int pocicion, String siguienteNodo, String nodosDeAceptacion, String conjuntoDeNodos) {
		this.vacio = vacio;
		this.cadena = cadena;
		this.pocicion = pocicion;
		this.siguienteNodo = siguienteNodo;
		
		this.nodosDeAceptacion = nodosDeAceptacion.split(",");

		this.nodos = new HashMap<>();
		String[] nodosIndividuales = conjuntoDeNodos.split("\n");
		for (String nodoIndividual:nodosIndividuales) {
			nodoIndividual = nodoIndividual.replace("\r", "");
			String[] fragmentoDeNodo = nodoIndividual.split(",");
			
			String[] instrucciones = new String[fragmentoDeNodo.length-1];
			for(int i=0;i<instrucciones.length;i+=1) {
				instrucciones[i]=fragmentoDeNodo[i+1];
			}
			
			this.nodos.put( fragmentoDeNodo[0], new Nodo(instrucciones) );
		}
		
	}

	class Nodo {
		String[] setDeInstrucciones;

		public Nodo(String[] setDeInstrucciones) {
			this.setDeInstrucciones = setDeInstrucciones;
		}

		public String ejecutarInstruccion(JTextArea caja) {

			for(int i=0;i<setDeInstrucciones.length;i+=1) {
				if(cadena.equals("")) {
					return null;
				}
				if(cadena.charAt(pocicion)==setDeInstrucciones[i].charAt(0)) { // recorre buscando una instrucción que sirva para cuando un determinado caracter
					StringBuilder temporal = new StringBuilder(cadena);			//Remplazamos el caracter
					temporal.setCharAt(pocicion, setDeInstrucciones[i].charAt(setDeInstrucciones[i].length()-2));	//se reemplaza el caracter en x pocicion
					cadena = temporal.toString();	// se vuelve a convertir a String
					
					String output1=setDeInstrucciones[i].charAt(0) + " se ignora\n==========="+"\n";
					String output2="Cambiando "+ setDeInstrucciones[i].charAt(0) + " por "+setDeInstrucciones[i].charAt(setDeInstrucciones[i].length() - 2)+"\n==========="+"\n";
					if(setDeInstrucciones[i].charAt(0)==setDeInstrucciones[i].charAt(setDeInstrucciones[i].length() - 2)) {
						caja.append(output1);
					}else {
						caja.append(output2);
					}//feedback
					
					switch(setDeInstrucciones[i].charAt(setDeInstrucciones[i].length()-1)) {	//Este switch ve hacia que lado se movera Derecha o Iquierda 
					case 'D':	//si es derecha, aumenta el índice en 1
						pocicion+=1;
						if(pocicion==cadena.length()) { //si la pocicion es igual a la longitud de la cadena, le concatena un blanco al final de la cadena
							cadena=cadena+vacio;
						}
						break;
					case 'I':	//si es izquierda, disminuye el índice en 1
						pocicion-=1;
						if(pocicion==-1) { //si la pocicion es  -1, agrega un  blanco al inicio de la cadena y restablece el la pocicion
							cadena=vacio+cadena;
							pocicion=0;
						}
						break;
					default:break;
					}
					return setDeInstrucciones[i].substring(1, setDeInstrucciones[i].length()-2); //retorna un String que viene siendo el siguiente nodo que dicta la instrucción
				}
			}
			return null;
		}

	}

	public boolean analizarCadena(String cad,JTextArea caja_mov,JTextField resultado) {
		this.cadena = cad;
		boolean terminado = false;
		//Aquí se agregan los movimientos a la caja de texto en la GUI dependiendo que este pasando
		while (!terminado) {
			pasos+=1;
			caja_mov.append("Cadena: "+ cadena+"\n");
			caja_mov.append("Estado actual: "+ siguienteNodo + ", Posicion actual: "+pocicion+"\n");

			Nodo nodoActual = nodos.get(siguienteNodo);
			siguienteNodo = nodoActual.ejecutarInstruccion(caja_mov);
			if (siguienteNodo == null) {
				caja_mov.append("Cadena " + cadena+" rechazada!\n");
				caja_mov.append("Completado en " + pasos + " pasos\n");
				return false;
			}

			for (int i = 0; i < nodosDeAceptacion.length; i += 1) {
				terminado = (Objects.equals(nodosDeAceptacion[i], siguienteNodo));
			}
			if (terminado) {
				caja_mov.append("Cadena " + cadena+" aceptada!\n");
				caja_mov.append("Completado en " + pasos + " pasos\n");
				resultado.setText(cadena);
				return true;
			}

		}
		
		caja_mov.append("Cadena " + cadena+" rechazada!\n");
		caja_mov.append("Completado en " + pasos + " pasos\n");
		return false;
	}

}
