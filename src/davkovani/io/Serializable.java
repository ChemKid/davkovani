/**************************************************************************************************
  davkovani/io/Serializable.java
  @author: Jan Chara
  date:
  description: 
**************************************************************************************************/

package davkovani.io;

import java.io.DataInputStream;
import java.io.DataOutputStream;

/**
 * rozhraní pro sjednocení tříd Form a Canvas
 * @author honza
 */
public interface Serializable
{
/**************************************************************************************************
 * variables declaration
**************************************************************************************************/
    
/**************************************************************************************************
 * overriding of parent or overloaded methods
**************************************************************************************************/

/**************************************************************************************************
 * own methods:
**************************************************************************************************/
    
    public void fromByteArray(byte data[]) throws Throwable;

    public byte[] toByteArray() throws Throwable;

    public void fromDataStream(DataInputStream din) throws Throwable;

    public void toDataStream(DataOutputStream dout) throws Throwable;
    
/**************************************************************************************************
 * interface implemented methods:
**************************************************************************************************/
}
