package chapter11;

import java.util.ArrayList;

public class BSTMap<K extends Comparable<K>,V> implements Map<K,V> {
    private class Node{
        public K key;
        public V value;
        public Node left,right;

        public Node(K key,V value){
            this.key = key;
            this.value = value;
            left=null;
            right=null;
        }
    }

    private Node root;
    private int size;

    public BSTMap(){
        root = null;
        size = 0;
    }

    @Override
    public int getSize(){
        return size;
    }

    @Override
    public boolean isEmpty(){
        return size == 0;
    }

    @Override
    public void add(K key,V value){
        root = add(root,key,value);
    }
    // 向以node为根的二分搜索树中插入key:value，递归算法
    // 返回插入新节点后二分搜索树的根
    public Node add(Node node,K key,V value){
        if(node == null){
            size++;
            return new Node(key,value);
        }

        if(key.compareTo(node.key)<0){
            node.left = add(node.left,key,value);
        }
        else if(key.compareTo(node.key)>0){
            node.right = add(node.right,key,value);
        }
        else{
            node.value = value;
        }
        return node;
    }

    private Node getNode(Node node,K key){
        if(node == null){
            return null;
        }
        if(key.compareTo(node.key) == 0){
            return node;
        }
        else if(key.compareTo(node.key)<0){
            return getNode(node.left,key);
        }
        else{
            return getNode(node.right,key);
        }
    }

    @Override
    public boolean contains(K key){
        return getNode(root,key) != null;
    }

    @Override
    public V get(K key){
        Node node = getNode(root,key);
        return node==null ? null : node.value;
    }

    @Override
    public void set(K key,V value){
        Node node = getNode(root,key);
        if(node == null){
            throw new IllegalArgumentException(key + "doesn't exist");
        }else{
            node.value = value;
        }
    }

    private Node minimum(Node node){
        if(node.left == null){
            return node;
        }
        return minimum(node.left);
    }

    private Node removeMin(Node node){
        if(node.left == null){
            Node rightNode = node.right;
            node.right = null;
            size--;
            return rightNode;
        }
        node.left = removeMin(node.left);
        return node;
    }

    @Override
    public V remove(K key){
        Node node = getNode(root,key);
        if(node != null){
            remove(root,key);
            return node.value;
        }
        return null;
    }

    private Node remove(Node node,K key){
        if(key.compareTo(node.key)<0){
            return remove(node.left,key);
        }
        else if(key.compareTo(node.key)>0){
            return remove(node.right,key);
        }
        else{
            if(node.left == null){
                Node rightNode = node.right;
                node.right =null;
                size--;
                return rightNode;
            }
            else if(node.right == null){
                Node leftNode = node.left;
                node.left = null;
                size--;
                return leftNode;
            }
            else{
                Node successor = minimum(node.right);
                successor.right = removeMin(node);
                successor.left = node.left;
                node.left = node.right = null;
                return successor;
            }
        }
    }

    public static void main(String[] args) {
        System.out.println("pride-and-prejudice:");
        ArrayList<String> words = new ArrayList<>();
        if(FileOperation.readFile("G:\\javastudy\\idea\\workspace\\code\\Algorithm\\src\\pride-and-prejudice.txt",words)){
            System.out.println("Total Words: " + words.size());

            BSTMap<String,Integer> map = new BSTMap<>();
            for(String word:words){
                if(map.contains(word)){
                    map.set(word,map.get(word) + 1);
                }else{
                    map.add(word,1);
                }
            }
            System.out.println("Total Different Words: " + map.getSize());
            System.out.println("Frequency Of Pride: " + map.get("pride"));
            System.out.println("Frequency Of Prejudice: " + map.get("prejudice"));
        }

    }
}
