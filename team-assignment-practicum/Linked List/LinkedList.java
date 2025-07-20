/* 
 * Author: Aldrin Rayhan Putra (2802601095)
 * Insertion and deletion of a Node in a Linked List
 */

class Node {

    final String fullName;
    int grade;
    Node next;

    public Node(String fullName, int grade) {
        this.fullName = fullName;
        this.grade = grade;
        this.next = null;
    }
}

public class LinkedList {

    Node head = null;
    Node tail = null;
    private int size = 0;

    public void add(String fullName, int grade) {
        Node newNode = new Node(fullName, grade);
        if (head == null) {
            head = newNode;
            tail = newNode;
        } else {
            tail.next = newNode;
            tail = newNode;
        }
        size++;
    }

    public void delete() {
        if (head == null) {
            System.out.println("List is empty");
            return;
        }
        if (head == tail) {
            head = null;
            tail = null;
        } else {
            Node current = head;
            while (current.next != tail) {
                current = current.next;
            }
            current.next = null;
            tail = current;
        }
        size--;
    }

    public void updateGrade(String fullName, int newGrade) {
        Node current = head;
        while (current != null) {
            if (current.fullName.equals(fullName)) {
                current.grade = newGrade;
                return;
            }
            current = current.next;
        }
        System.out.println("Node with full name " + fullName + " not found.");
    }

    public void printList() {
        Node current = head;
        if (current == null) {
            System.out.println("List is empty");
            return;
        }
        while (current != null) {
            System.out.println("Nama: " + current.fullName + ", Nilai: " + current.grade);
            current = current.next;
        }
    }

    public static void main(String[] args) {
        LinkedList list = new LinkedList();

        list.add("Aldrin Rayhan Putra", 85);
        list.add("Feby Tri Wulandari", 90);
        list.add("Dwi Prasetyo", 89);
        list.add("Maharizky Putri Amalia", 92);
        list.add("Jose Agusto Carvalho Widiyanto Dos Santos", 88);

        System.out.println("Initial List:");
        list.printList();

        list.updateGrade("Feby Tri Wulandari", 95);
        System.out.println("\nAfter updating grade:");
        list.printList();

        list.delete();
        System.out.println("\nAfter deleting last node:");
        list.printList();
    }
}
