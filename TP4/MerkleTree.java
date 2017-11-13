
/**
*This class constructs a Merkle tree;
*/

public class MerkelTree{
   protected byte[][] tree;
   protected int height; // Root is level 0
   protected int numLeaves;
   protected int totalNodes;
   protected boolean exists;
   protected HashTable hashAlgorithm;
   protected List<byte[]> recentLeaves;

//Default constructor
public MerkelTree(HashType type){
      hashAlgorithm=true;
      recentLeaves=new ArrayList<byte[]>();
      tree=null;
      exists=false;
   }

public List<MerklePathStep> findPath(byte[] startingHash){
     List<MerklePathStep> merklePath = new ArrayList<MerklePathStep> ();
     if(existsAsLeaf(startingHash)){
      int currentIndex = findLeafIndex(startingHash);
      while(currentIndex != 0){
       int siblingIndex = getSibling(currentIndex);
       byte[] sibling = tree[siblingIndex];
       boolean siblingOnLeft = isLeftNode(siblingIndex);
       // not nodeOnLeft because sibling is on other side
       merklePath.add(new MerklePathStep(siblingOnLeft, sibling));
       currentIndex = getParent(currentIndex);
      }
     }

     return merklePath;
    }

 protected int getParent(int index) {
     return (index-1)/2;
 }

 protected int getLeftChild(int index) {
     return 2*(index) + 1;
 }

 protected int getRightChild(int index) {
     return 2*(index+1);
 }

 protected boolean isLeftNode(int index) {
  return (index % 2) != 0;
 }
}
