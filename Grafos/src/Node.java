import java.util.ArrayList;
import java.util.List;

public class Node {
    private int id;
    private List<Node> vertex;

    public Node(int id){
        this.id = id;
        vertex = new ArrayList<>();
        System.out.println("Se creó el nodo " + id);
    }

    public Node(){

    }

    public void setId(int id){
        this.id = id;
    }

    public int getId(){
        return id;
    }

    public void setVertex(Node node){
        if(!vertex.contains(node) && (node.getId() != id)){
            vertex.add(node);
            System.out.println("El nodo " + id + " tiene un nuevo vertice con el nodo " + node.getId());
        }
    }

    public void setVertex(Node[] nodes){
        for (Node node: nodes) {
            if(!vertex.contains(node)){
                vertex.add(node);
            }
        }
    }

    public List<Node> getVertex(){
        return vertex;
    }

    public void deleteVertex(Node node){
        vertex.remove(node);
    }
}
