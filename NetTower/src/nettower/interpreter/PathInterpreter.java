/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package nettower.interpreter;

import java.awt.Point;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;

/**
 *
 * @author David Moran Diaz
 */
public class PathInterpreter {
    File archivo;
    FileReader fr;
    BufferedReader br;
    String MAP = "^\\s*(MAP)$";
    String AMAP = "\\s*(MAP)$";
    String EMAP = "\\s*(/MAP)$";
    String PATH = "\\s*(PATH)$";
    String EPATH = "\\s*(/PATH)";
    String COOR = "\\s*[0-9]+,[0-9]+$";
    // Estados
    int SMAP = 0;
    int SPATH = 1;
    int SCOOR = 2;
    int SEPATH = 2;
    int SEMAP = 2;
    // Varios
    int OK = 1;
    int FAIL = 2;

    public PathInterpreter() {}

    public static void main(String[] args) {
        PathInterpreter p = new PathInterpreter();

        p.parseMap("entrada.txt");
    }

    public ArrayList<ArrayList> parseMap(String fileName) {

        ArrayList<ArrayList> pathsList = null;
        ArrayList<Point.Double> path = null;
        int status;
        String lin;
        String[] puntos;
        Point.Double p;

        if (openFile(fileName) == FAIL) {
            return null;
        }

        while ((lin = readLine()) != null) {

            if (lin.matches(MAP)) {
                status = SMAP;
                pathsList = new ArrayList();
            } else if (lin.matches(PATH)) {
                status = SPATH;
                path = new ArrayList();
            } else if (lin.matches(COOR)) {
                status = SCOOR;
                
                lin = lin.replaceAll("\\s*", "");
                puntos = lin.split(",");
                p = new Point.Double();
                p.x = Integer.parseInt(puntos[0]);
                p.y = Integer.parseInt(puntos[1]);
                
                p.x = p.x * 32 + 16;
                p.y = p.y * 32 + 16;
                
                // Ponemos el punto al final de la lista
                path.add(path.size(), p);
            } else if (lin.matches(EPATH)) {
                status = SEPATH;
                
                // Ponemos el camino al final de la lista
                pathsList.add(pathsList.size(), path);
            } else if (lin.matches(EMAP)) {
                status = SEMAP;
            }
        }
        
        closeFile();
        
        return pathsList;
    }

    private int openFile(String fileName) {
        int ret = FAIL;
        try {
            // Apertura del fichero y creacion de BufferedReader para poder
            // hacer una lectura comoda (disponer del metodo readLine()).
            this.archivo = new File(fileName);
            this.fr = new FileReader(archivo);
            this.br = new BufferedReader(fr);

            ret = OK;
        } catch (Exception e) {
            e.printStackTrace();
        }

        return ret;
    }

    private String readLine() {
        String linea = null;
        try {
            // Lectura del fichero
            linea = br.readLine();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return linea;
    }

    private void closeFile() {
        try {
            fr.close();
        } catch (Exception e2) {
            e2.printStackTrace();
        }
    }
}
