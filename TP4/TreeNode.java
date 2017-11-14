import java.security.MessageDigest;
import javax.xml.bind.DatatypeConverter;
/**
*This class constructs a Merkle tree;
*/

public class TreeNode{
   public long maxId;
   public byte[] hash;
   public boolean isleaf;
   public TreeNode leftNode,rightNode,parentNode;

   //Default constructor
   public TreeNode(byte[]data){

   }
   //hash the file;
   public String getHashed(String file){
      String result=null;
      try{
      MessageDigest digest=MessageDigest.getInstance("SHA-256");
      byte[] hash=digest.digest(data.getBytes("UTF-8"));
      return DatatypeConverter.printHexBinary(hash);         //make it printable;
      }catch(Exception e){
         e.printStackTrace();
      }
      return result;
   }

   public int getParent(int index) {
        return (index-1)/2;
   }

   public int getLeftChild(int index) {
        return 2*(index) + 1;
   }

   public int getRightChild(int index) {
        return 2*(index+1);
   }
}
