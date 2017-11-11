import java.util.NoSuchElementException;

public class BST
{
  private BTNode<Integer> root;

  //private variables
  private static int size=0;
  private static int counter;
  private static int num;
  private static boolean firstRotation;
  private static int index;
  private static int atIndex;

  public BST()
  { root = null;
  }

  public boolean find(Integer i)
  { BTNode<Integer> n = root;
    boolean found = false;

    while (n!=null && !found)
    { int comp = i.compareTo(n.data);
      if (comp==0)
        found = true;
      else if (comp<0)
        n = n.left;
      else
        n = n.right;
	}

    return found;
  }

  public boolean insert(Integer i)
  { BTNode<Integer> parent = root, child = root;
    boolean goneLeft = false;

    while (child!=null && i.compareTo(child.data)!=0)
    { parent = child;
      if (i.compareTo(child.data)<0)
	  { child = child.left;
	    goneLeft = true;
	  }
	  else
	  { child = child.right;
	    goneLeft = false;
      }
	}

    if (child!=null)
      return false;  // number already present
    else
    { BTNode<Integer> leaf = new BTNode<Integer>(i);
      if (parent==null) // tree was empty
        root = leaf;
      else if (goneLeft)
        parent.left = leaf;
      else
        parent.right = leaf;
      return true;
    }
  }
  public int greater(int n)
  {
    num=n;
    counter=0;
    firstRotation=true;
    scanPreOrder(this.root);
    return counter;
  }

  // recursive method running through the tree
  // and counts occurrences of greater numbers
  private static void scanPreOrder(BTNode<Integer> node)
  {
      if(node!=null)
      {
        firstRotation=false;
          if(node.data>num)
          {
            counter++;
          }
          size++;
          scanPreOrder(node.left);
          scanPreOrder(node.right );
      }
      else if(firstRotation){
        counter =0;
      }
  }

  public int nth(int n)
  {
    if(size()<n+1|| n<0) throw new NoSuchElementException();
    num=n;
    counter =0;
    firstRotation=true;
    index=n;
      indexScanPreOrder(root);
      return atIndex;
  }

  //recursive function which goes through
  //left children of the tree first
  //counting each node. When number of nodes reaches
  //index value - the data is saved to a static variable
  //called atIndex
  private static void indexScanPreOrder(BTNode<Integer> node)
  {
    if(counter<=index)
    {
      if(node!=null)
      {
        firstRotation=false;
        atIndex=node.data;
        indexScanPreOrder(node.left);
        counter++;
        indexScanPreOrder(node.right);
      }
      else if(firstRotation){
        counter =0;
      }
    }
  }

  private int size()
  {
    size=0;
    scanPreOrder(root);
    return size;
  }

}

class BTNode<T>
{
  T data;
  BTNode<T> left, right;

  BTNode(T o)
  { data = o; left = right = null;
  }
}
