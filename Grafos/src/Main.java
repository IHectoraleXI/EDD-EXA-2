import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {

    static Scanner scan;
    static int response;
    static List<Node> nodes;

    static int counterNodes = 0;

    static final String NOT_EXIST = "El nodo ingresado no existe, intenta de nuevo";

    public static void main (String[] args) {
        nodes = new ArrayList<>();

        Start();
    }

    private static void Start(){
        System.out.println("Programa 2");
        printMenu();
        response = getResponse();
        while(checkResponse(response)){
            printNodeMatrix();
            printMenu();
            response = getResponse();
        };
    }

    private static void printMenu(){
        System.out.println("Selecciona una opción:");
        System.out.println("0. Agregar nodo");
        System.out.println("1. Agregar vertices");
        System.out.println("2. Borrar nodo");
        System.out.println("3. Borrar vertices");
        System.out.println("4. Adyacentes");
        System.out.println("5. Salir");
    }

    private static int getResponse(){
        scan = new Scanner(System.in);
        return scan.nextInt();
    }

    private static boolean checkResponse(int response){
        switch(response){
            case 0:
                    addNode();
                    return askForMenu();
            case 1:
                    addVertex();
                    return askForMenu();
            case 2:
                    deleteNode();
                    return askForMenu();
            case 3:
                    deleteVertex();
                    return askForMenu();
            case 4:
                    areAdjacent();
                    return askForMenu();
            case 5:
                    return askForExit();
            default:
                return askForExit();
        }
    }

    private static void addNode(){
        nodes.add(new Node(counterNodes));
        counterNodes++;
    }

    private static void deleteNode(){
        System.out.println("Ingresa el nodo a eliminar: ");
        int id = getResponse();
        try{
            if(nodes.contains(getNode(id))) {
                nodes.remove(getNode(id));
                for (Node node : nodes) {
                    if (node.getVertex().contains(getNode(id))) {
                        node.getVertex().remove(getNode(id));
                    }
                }
                System.out.println("El nodo " + id + " ha sido eliminado");
            }else{
                System.out.println(NOT_EXIST);
            }
        }catch(Exception e){
            System.out.println(NOT_EXIST);
        }
    }

    private static void addVertex(){
        System.out.println("Ingresa el primer nodo: ");
        int id1 = getResponse();
        System.out.println("Ingresa el segundo nodo: ");
        int id2 = getResponse();
        try{
            Node node1 = getNode(id1);
            Node node2 = getNode(id2);
            node1.setVertex(node2);
            node2.setVertex(node1);
        } catch (Exception e){
            System.out.println(NOT_EXIST);
        }
    }

    private static void deleteVertex(){
        System.out.println("Ingresa el nodo: ");
        int id = getResponse();
        try{
            Node node = getNode(id);
            System.out.print("Los vertices existentes para el nodo " + id + " son: ");
            for (Node tempNode: node.getVertex()){
                System.out.print("[" + tempNode.getId() + "]");
            }
            System.out.println("");
            System.out.println("Selecciona el vertice que se desea eliminar: ");
            int delNodeId = getResponse();
            node.deleteVertex(getNode(delNodeId));
        }catch(Exception e){
            System.out.println(NOT_EXIST);
        }
    }

    private static void areAdjacent(){
        System.out.println("Ingresa el primer nodo: ");
        int id1 = getResponse();
        System.out.println("Ingresa el segundo nodo: ");
        int id2 = getResponse();

        try{
            Node node1 = getNode(id1);
            Node node2 = getNode(id2);

            if(node1.getVertex().contains(node2) || node2.getVertex().contains(node1)){
                System.out.println("Los nodos son adyacentes");
            } else{
                System.out.println("Los nodos no son adyacentes");
            }
        } catch (Exception e){
            System.out.println("Los nodos no son adyacentes");
        }



    }

    private static boolean askForExit(){
        System.out.println("¿Desea continuar en el programa?");
        System.out.println("1. Si");
        System.out.println("2. No");
        int response = getResponse();
        return response == 1;
    }

    private static boolean askForMenu(){
        System.out.println("¿Desea volver al menú?");
        System.out.println("1. Si");
        System.out.println("2. No");
        int response = getResponse();
        return response == 1;
    }

    private static void printExistedNodes(){
        System.out.print("Nodos existentes: ");
        for (Node node:nodes) {
            System.out.print("[" + node.getId() + "]");
        }
        System.out.println("");
    }

    private static Node getNode(int id){
        for(Node node: nodes) {
            if(node.getId() == id){
                return node;
            }
        }
        return null;
    }

    private static void printNodeMatrix(){
        for (Node node: nodes) {
            for(int i = 0; i < nodes.size(); i++){
                if(node.getVertex().contains(nodes.get(i))){
                    System.out.print("[1]");
                }else{
                    System.out.print("[0]");
                }
            }
            System.out.println("");
        }
    }
}
