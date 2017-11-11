/**
 * Created by Tomas Kovtun on 1/30/2017.
 */
public class TestPriorityQueue {
    public static void main(String [] args)
    {
        PriorityQueue queue=new PriorityQueue();
        System.out.println("Queue is empty : "+queue.isEmpty());
        queue.addToPQ(3,"third");
        System.out.println("Data: third, priority: 3 //ADDED");
        queue.addToPQ(2,"second");
        System.out.println("Data: second, priority: 2 //ADDED");
        queue.addToPQ(1,"first");
        System.out.println("Data: first, priority: 1 //ADDED");
        queue.addToPQ(20,"twenty");
        System.out.println("Data: twenty, priority: 20 //ADDED");
        System.out.println("Current queue : \n"+queue.toString());
        System.out.println("Queue is empty : "+queue.isEmpty());

        System.out.print("Delete front of the queue: ");
        try
        {
            queue.deleteFront();
        }
        catch (PriorityQueueException err)
        {
            System.out.println(err.getMessage());
        }
        System.out.println("Current queue : \n"+queue.toString());

        System.out.print("Get data of the first node: ");
        try
        {
            System.out.println(queue.front().getData());
        }
        catch (PriorityQueueException err)
        {
            System.out.println(err.getMessage());
        }
        System.out.println("Current queue : \n"+queue.toString());

        System.out.print("Get priority of the first node: ");
        try
        {
            System.out.println(queue.frontPri());
        }
        catch (PriorityQueueException err)
        {
            System.out.println(err.getMessage());
        }
        System.out.println("Current queue : \n"+queue.toString());
    }
}
