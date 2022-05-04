package chapter6.leetcode;

public class LinkedList3<E> {
    private class Node{
        public E e;
        public Node next;

        public Node(E e,Node next){
            this.e = e;
            this.next = next;
        }

        public Node(E e){
            this(e,null);
        }

        public Node(){
            this(null,null);
        }

        @Override
        public String toString(){
            return e.toString();
        }
    }

    private Node dummyHead;
    private int size;

    public LinkedList3(){
        dummyHead = new Node(null,null);
        size = 0;
    }

    public int getSize(){
        return size;
    }

    public boolean isEmpty(){
        return size == 0;
    }

    //在index位置添加元素e
    public Node add(Node node,int index,E e){
        if(index == 0){
            return new Node(e,node);
        }
        node.next = add(node,index-1,e);
        return node;
    }

    //在链表头添加元素e
    public Node addFirst(Node node,E e){
        return add(node,0,e);
    }

    //在链表尾添加元素e
    public Node addLast(Node node,E e){
        return add(node,size,e);
    }

    //获取链表index位置的元素e
    public E get(int index){
        if(index<0 || index>size){
            throw new IllegalArgumentException("Add failed.Illegal index");
        }

        Node cur = dummyHead.next;
        for(int i=0;i<index;i++){
            cur = cur.next;
        }
        return cur.e;
    }

    public E getFirst(){
        return get(0);
    }

    public E getLast(){
        return get(size-1);
    }

    //修改链表index位置元素为e
    public void set(int index,E e){
        if(index<0 || index>size){
            throw new IllegalArgumentException("Add failed.Illegal index");
        }

        Node cur = dummyHead.next;
        for(int i=0;i<index;i++){
            cur = cur.next;
        }
        cur.e = e;
    }

    public void setFirst(E e){
        set(0,e);
    }

    public void setLast(E e){
        set(size-1,e);
    }

    //查找链表中是否有元素e
    public boolean contains(E e){
        Node cur = dummyHead.next;
        while(cur != null){
            if(cur.e.equals(e)){
                return true;
            }else{
                cur = cur.next;
            }
        }
        return false;
    }

    //删除index位置的节点
    public E remove(int index){
        if(index<0 || index>size){
            throw new IllegalArgumentException("Add failed.Illegal index");
        }
        Node pre = dummyHead;
        for(int i=0;i<index;i++){
            pre = pre.next;
        }
        Node delNode = pre.next;
        pre.next = delNode.next;
        delNode.next = null;
        size--;
        return delNode.e;
    }

    public E removeFirst(){
        return remove(0);
    }

    public E removeLast(){
        return remove(size-1);
    }

    @Override
    public String toString(){
        StringBuilder res = new StringBuilder();

        // while循环
//        Node cur = dummyHead.next;
//        while(cur != null){
//            res.append(cur + "->");
//            cur = cur.next;
//        }

        // for 循环
        for(Node cur = dummyHead.next;cur != null;cur = cur.next){
            res.append(cur + "->");
        }
        res.append("NULL");
        return res.toString();
    }
}
