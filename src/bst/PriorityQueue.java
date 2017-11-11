import java.util.LinkedList;

/**
 * Created by Tomas Kovtun on 1/29/2017.
 */

public class PriorityQueue implements IPriorityQueue
{
    private LinkedList<QueueCell> lLQueue;

    //constructor
    public PriorityQueue()
    {
        lLQueue =new LinkedList<>();
    }

    //checks if the queue is empty
    public boolean isEmpty()
    {
        return (lLQueue.isEmpty());
    }

    //adds an item to the queue
    public void addToPQ(int priority,String data)
    {
        if(priority<1||priority>20) throw new PriorityQueueException("Priority must be a number between 1 and 20");
        QueueCell newCell=new QueueCell(priority,data);
        QueueCell listCell;
        int queueSize=lLQueue.size();
        lLQueue.add(0,newCell);
        for(int i=1;i<=queueSize;i++)
        {
            listCell= lLQueue.get(i);
            if (newCell.getPriority()<=listCell.getPriority())
            {
                lLQueue.set(i,newCell);
                lLQueue.set(i-1,listCell);
            }
        }
    }

    //returns front cell
    public QueueCell front()
    {
        if(lLQueue.isEmpty()) throw new PriorityQueueException("The queue is empty");
        return lLQueue.getFirst();
    }

    //removes front cell
    public boolean deleteFront()
    {
        if(lLQueue.isEmpty())
            return false;
        lLQueue.removeFirst();
        return true;
    }

    //returns priority of the first cell
    public int frontPri()
    {
        if(lLQueue.isEmpty()) throw new PriorityQueueException("The queue is empty");
        return lLQueue.getFirst().getPriority();
    }

    //returns string expression of the queue
    public String toString()
    {
        if(this.isEmpty()) return "<>";
        String result="<";
        for(QueueCell cell:lLQueue)
        {
            result+="\""+cell.getData()+"\":"+cell.getPriority()+",";
        }
        result=result.substring(0,result.length()-1);
        result+=">";
        return result;
    }
}

//Extended Priority queue exception
class PriorityQueueException extends RuntimeException
{
    public PriorityQueueException(String message)
    {
        super(message);
    }
}

//Priority queue interface
interface IPriorityQueue {
    String toString();
    boolean isEmpty();
    QueueCell front();
    boolean deleteFront();
    int frontPri();
}

//unit of priority queue. Holds data and priority number
class QueueCell
{
    private int priority;
    private String data;
    public QueueCell(int priority,String data)
    {
        this.priority=priority;
        this.data=data;
    }

    public int getPriority() {
        return priority;
    }

    public String getData() {
        return data;
    }
}