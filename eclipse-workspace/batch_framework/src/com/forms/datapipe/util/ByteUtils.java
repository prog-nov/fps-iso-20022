package com.forms.datapipe.util;

import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 * util for byte
 * </p>
 * 
 * @author cxl
 * 
 */
@SuppressWarnings("unchecked")
public class ByteUtils
{
    /**
     * 
     * @param first
     * @param second
     * @return
     */
    public static byte[] add(byte[] first, byte[] second)
    {
        byte[] sum = new byte[first.length + second.length];
        System.arraycopy(first, 0, sum, 0, first.length);
        System.arraycopy(second, 0, sum, first.length, second.length);
        return sum;
    }

    /**
     * 
     * @param first
     * @param firstStartPos
     * @param firstEndPos
     * @param second
     * @param secondStartPos
     * @param secondEndPos
     * @return
     */
    public static byte[] add(byte[] first, int firstStartPos, int firstEndPos,
        byte[] second, int secondStartPos, int secondEndPos)
    {
        int fNum = firstEndPos - firstStartPos;
        int sNum = secondEndPos - secondStartPos;

        byte[] sum = new byte[fNum + sNum];
        System.arraycopy(first, firstStartPos, sum, 0, fNum);
        System.arraycopy(second, secondStartPos, sum, fNum, sNum);
        return sum;
    }

    /**
     * 
     * @param first
     * @param second
     * @return
     */
    public static byte[] add(byte[] first, byte second)
    {
        return add(first, new byte[] { second });
    }

    /**
     * 
     * @param first
     * @param second
     * @return
     */
    public static byte[] add(byte first, byte[] second)
    {
        return add(new byte[] { first }, second);
    }

    /**
     * 
     * @param c
     * @return
     */
    public static byte toSignedByte(int c)
    {
        if (c > 127)
        {
            return (byte) (c - 256);
        }
        return (byte) c;
    }

    /**
     * 
     * @param b
     * @return
     */
    public static int toUnsignedByte(byte b)
    {
        if (b < 0)
        {
            return b + 256;
        }
        return b;
    }

    /**
     * 
     * @param b
     * @return
     */
    public static String toHexString(byte b)
    {
        String s = Integer.toHexString(toUnsignedByte(b));
        if (s.length() == 1) return "0" + s;
        return s;
    }

    /**
     * 
     * @param bytes
     * @return
     */
    public static String toHexString(byte[] bytes)
    {
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < bytes.length; i++)
        {
            sb.append(toHexString(bytes[i]));
        }
        return sb.toString();
    }

    /**
     * 
     * @param hex
     * @return
     */
    public static byte[] fromHexString(String hex)
    {
        byte[] bytes = new byte[hex.length() / 2];
        for (int i = 0; i < hex.length() / 2; i++)
        {
            bytes[i] = (byte) Integer.parseInt(
                hex.substring(i * 2, (i + 1) * 2), 16);
        }
        return bytes;
    }

    /**
     * 
     * @param data
     * @param delimiter
     * @return
     */
    public static byte[][] split(byte[] data, byte delimiter)
    {
        return split(data, new byte[] { delimiter });
    }

    /**
     * 
     * @param data
     * @param delimiter
     * @return
     */
	public static byte[][] split(byte[] data, byte[] delimiter)
    {
        if (delimiter.length == 0) return new byte[][] { data };

        List result = new ArrayList();
        int index = 0;
        outer: for (int i = 0; i < data.length; i++)
        {
            if (data.length - i < delimiter.length)
            {
                byte[] newBytes = new byte[data.length - index];
                System.arraycopy(data, index, newBytes, 0, newBytes.length);
                result.add(newBytes);
                break;
            }

            if (data.length - i == delimiter.length)
            {
                boolean match = true;
                for (int j = 0; j < delimiter.length; j++)
                {
                    if (data[i + j] != delimiter[j])
                    {
                        match = false;
                        break;
                    }
                }
                if (match)
                {
                    byte[] newBytes = new byte[data.length - index
                        - delimiter.length];
                    System.arraycopy(data, index, newBytes, 0, newBytes.length);
                    result.add(newBytes);
                    result.add(new byte[] {});
                } else
                {
                    byte[] newBytes = new byte[data.length - index];
                    System.arraycopy(data, index, newBytes, 0, newBytes.length);
                    result.add(newBytes);
                }

                break;
            }

            for (int j = 0; j < delimiter.length; j++)
            {
                if (data[i + j] != delimiter[j]) continue outer;
            }

            byte[] newBytes = new byte[i - index];
            System.arraycopy(data, index, newBytes, 0, newBytes.length);
            result.add(newBytes);

            i += delimiter.length;
            index = i;
            i--;
        }
        return (byte[][])result.toArray(new byte[result.size()][]);
    }

    /**
     * 
     * @param b1 --
     *            byte[]
     * @param b2 --
     *            byte[]
     * @return -- boolean
     */
    public static boolean equals(byte[] b1, byte[] b2)
    {
        return toHexString(b1).equals(toHexString(b2));
    }

 /*   public static void main(String[] args)
    {
        byte[] data = { 3, 4, 1, 3, 5, 4, 1, 3, 1 };
        byte[] delimiter = { 5 };
        byte[][] result = split(data, delimiter);

    }*/

}
