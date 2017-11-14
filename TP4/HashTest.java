import java.security.MessageDigest;
import javax.xml.bind.DatatypeConverter;

public class HashTest{
   public String getSHA256Hash(String data){
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

   public static void main(String[]args){
      String fileName="securityFIle";
      HashTest ht=new HashTest();
      String hash=ht.getSHA256Hash(fileName);
      System.out.println("The hash of your fileName is:"+hash);
      /*String fileName="securityFIle";
      MessageDigest md=MessageDigest.getInstance("SHA-256");
      md.update(fileName.getBytes());

      byte byteData[]=md.digest();
      //convert the byte data to hex format method 1;
      StringBuffer sb=new StringBuffer();
      for(int i=0;i<byteData.length;i++){
         sb.append(Integer.toString((byteData[i] & 0xff)+0x100,16).substring(1));
      }
      System.out.println("From method1,Hex format: "+sb.toString());

      //convert the byte data to hex format method 2;
      StringBuffer hexString=new StringBuffer();
      for(int j=0;j<byteData.length;j++){
         String hex=Integer.toHexString(0xff &byteData[j]);
         if(hex.length()==1) hexString.append('0');
   	   hexString.append(hex);
      }
      System.out.println("From method2,Hex format: "+sb.toString());
      */
   }
}
