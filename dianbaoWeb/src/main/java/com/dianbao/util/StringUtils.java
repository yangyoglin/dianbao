package com.dianbao.util;

import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.Set;
import java.util.StringTokenizer;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * <p>
 * Description: Utility class to peform common String manipulation algorithms.
 * </p>
 *
 * @author douglas
 * @version 1.0
 */
public class StringUtils {

    /**
     * Initialization lock for the whole class. Init's only happen once per
     * class load so this shouldn't be a bottleneck.
     */
    private static Object initLock = new Object();

    private static Log log = LogFactory.getLog(StringUtils.class);

    /**
     * Array of numbers and letters of mixed case. Numbers appear in the list
     * twice so that there is a more equal chance that a number will be picked.
     * We can use the array to get a random number or letter by picking a random
     * array index.
     */
    private static char[] numbersAndLetters = null;

    /**
     * Pseudo-random number generator object for use with randomString(). The
     * Random class is not considered to be cryptographically secure, so only
     * use these random Strings for low to medium security applications.
     */
    private static Random randGen = null;

    /**
     * Matching two strings blurring. There is four forms of <code>dec</code>.
     * <P>
     * For example: <blockquote>
     *
     * <pre><code>
     * String dec = &quot;%dec%&quot;;
     * </code>
     * <code>
     * String dec = &quot;dec%&quot;;
     * </code>
     * <code>
     * String dec = &quot;%dec&quot;;
     * </code>
     * <code>
     * String dec = &quot;dec&quot;;
     * </code>
     * </pre>
     *
     * </blockquote>
     *
     * @param src string to be matched
     * @param dec string you want to match
     * @return boolean the match result
     */
    public static boolean blurEquals(String src, String dec) {
        boolean isMatch = false;
        if (dec.startsWith("%") && dec.endsWith("%")) {
            if (src.indexOf(dec.substring(1, dec.length() - 1)) != -1) {
                isMatch = true;
            }
        } else if (dec.startsWith("%")) {
            if (src.endsWith(dec.substring(1))) {
                isMatch = true;
            }
        } else if (dec.endsWith("%")) {
            if (src.startsWith(dec.substring(0, dec.length() - 1))) {
                isMatch = true;
            }
        } else {
            if (src.equals(dec)) {
                isMatch = true;
            }
        }
        return isMatch;
    }

    /**
     * 判断是否是数据
     *
     * @param str
     * @return
     */
    public static boolean isNumeric1(String str) {
        Pattern pattern = Pattern.compile("\\d+[\\.*\\d]?\\d*");
        Matcher isNum = pattern.matcher(str);
        if (!isNum.matches()) {
            return false;
        }
        return true;
    }

    /**
     * Intelligently chops a String at a word boundary (whitespace) that occurs
     * at the specified index in the argument or before. However, if there is a
     * newline character before <code>length</code>, the String will be chopped
     * there. If no newline or whitespace is found in <code>string</code> up to
     * the index <code>length</code>, the String will chopped at
     * <code>length</code>.
     * <p>
     * For example, chopAtWord("This is a nice String", 10) will return "This is
     * a" which is the first word boundary less than or equal to 10 characters
     * into the original String.
     *
     * @param string the String to chop.
     * @param length the index in <code>string</code> to start looking for a
     * whitespace boundary at.
     * @return a substring of <code>string</code> whose length is less than or
     * equal to <code>length</code>, and that is chopped at whitespace.
     */
    public static final String chopAtWord(String string, int length) {
        if (string == null) {
            return string;
        }

        char[] charArray = string.toCharArray();
        int sLength = string.length();
        if (length < sLength) {
            sLength = length;
        }

        // First check if there is a newline character before length; if so,
        // chop word there.
        for (int i = 0; i < sLength - 1; i++) {
            // Windows
            if (charArray[i] == '\r' && charArray[i + 1] == '\n') {
                return string.substring(0, i);
            } // Unix
            else if (charArray[i] == '\n') {
                return string.substring(0, i);
            }
        }
        // Also check boundary case of Unix newline
        if (charArray[sLength - 1] == '\n') {
            return string.substring(0, sLength - 1);
        }

        // Done checking for newline, now see if the total string is less than
        // the specified chop point.
        if (string.length() < length) {
            return string;
        }

        // No newline, so chop at the first whitespace.
        for (int i = length - 1; i > 0; i--) {
            if (charArray[i] == ' ') {
                return string.substring(0, i).trim();
            }
        }

        // Did not find word boundary so return original String chopped at
        // specified length.
        return string.substring(0, length);
    }

    /**
     * This method takes a string which may contain HTML tags (ie, &lt;b&gt;,
     * &lt;table&gt;, etc) and converts the '&lt'' and '&gt;' characters to
     * their HTML escape sequences.
     *
     * @param input the text to be converted.
     * @return the input string with the characters '&lt;' and '&gt;' replaced
     * with their HTML escape sequences.
     */
    public static final String escapeHTMLTags(String input) {
        // Check if the string is null or zero length -- if so, return
        // what was sent in.
        if (input == null || input.length() == 0) {
            return input;
        }
        // Use a StringBuffer in lieu of String concatenation -- it is
        // much more efficient this way.
        StringBuffer buf = new StringBuffer(input.length());
        char ch = ' ';
        for (int i = 0; i < input.length(); i++) {
            ch = input.charAt(i);
            // Change if else-if else to switch
            // to reduce CC(Cyclomatic Complexity) in Java Code Conventions
            switch (ch) {
                case '<':
                    buf.append("&lt;");
                    break;
                case '>':
                    buf.append("&gt;");
                    break;
                case '&':
                    buf.append("&amp;");
                    break;
                case '"':
                    buf.append("&quot;");
                    break;
                default:
                    buf.append(ch);
                    break;
            }
        }
        return buf.toString();
    }

    /**
     * Change the coding mode GBK of <code>str</code> to ISO-8859-1
     * <P>
     * This method return empty string when coming string is null or length is 0
     *
     * @param str the coming string which want to change
     * @return return string with coding ISO-8859-1
     */
    public static String GBKtoISO(String str) {
        if (str == null || str.trim().length() == 0) {
            return "";
        }
        try {
            str = new String(str.getBytes("gbk"), "ISO-8859-1");
        } catch (Exception e) {
            log.error("StringUtils.java:GBKtoISO():" + e);
        }
        return str;
    }

    /**
     * Change the coding mode gb2312 of <code>str</code> to ISO-8859-1
     * <P>
     * This method return empty string when coming string is null or length is 0
     *
     * @param str the coming string which want to change
     * @return return string with coding ISO-8859-1
     */
    public static String GBtoISO(String str) {
        if (str == null || str.trim().length() == 0) {
            return "";
        }
        try {
            str = new String(str.getBytes("gb2312"), "ISO-8859-1");
        } catch (Exception e) {
            log.error("StringUtils.java:GBtoISO():" + e);
        }

        return str;
    }

    /**
     * Chop the coming string by <code>substring()</code> with param
     * <code>length</code> If <code>content</code> 's length longer than
     * <code>length</code> return string will end with '...'. Otherwise return
     * <code>content</code>
     * <P>
     * This method return "" when <code>content</code> is null
     *
     * @param content coming string to be chopped
     * @param length the length of coming string want to leave
     * @return String string after chopped,end with ...
     */
    public static String getLimitLengthString(String content, int length) {
        if (content == null) {
            return "";
        }
        if (content.length() > length) {
            content = content.substring(0, length) + "...";
        }
        return content;
    }

    /**
     * Change the coding mode ISO-8859-1 of <code>str</code> to gb2312
     * <P>
     * This method return "" when coming string is null or length is 0
     *
     * @param str the coming string which want to change
     * @return return string with coding gb2312
     */
    public static String ISOtoGB(String str) {
        if (str == null || str.trim().length() == 0) {
            return "";
        }
        try {
            str = new String(str.getBytes("ISO-8859-1"), "gb2312");
        } catch (Exception e) {
            log.error("StringUtils.java:ISOtoGB():" + e);
        }
        return str;
    }

    /**
     * Change the coding mode ISO-8859-1 of <code>str</code> to gbk
     * <P>
     * This method return "" when coming string is null or length is 0
     *
     * @param str the coming string which want to change
     * @return return string with coding gbk
     */
    public static String ISOtoGBK(String str) {
        if (str == null || str.trim().length() == 0) {
            return "";
        }
        try {
            str = new String(str.getBytes("ISO-8859-1"), "GBK");
        } catch (Exception e) {
            log.error("StringUtils.java:ISOtoGB():" + e);
        }
        return str;
    }

    /**
     * Return true if the string is neither null nor empty string
     * <p>
     *
     * @param str The string for checking
     * @return true if the string is neither null nor empty string
     */
    public static boolean isValuedString(String str) {
        return ((str != null) && (!str.equals("")));
    }

    /**
     * Pad <code>body</code> in the left side with <code>c</code> to make sure
     * returning String's length's <code>len</code>
     * <P>
     * If <code>body</code> is <code>null</code> returns String which length's
     * zero.
     * <P>
     * If <code>len</code> larger than <code>body</code>'s length throws
     * <code>IllegalArgumentException</code> exception
     *
     * @param body Coming String want to perform padding
     * @param len Length of return String
     * @param c Padding char
     * @return String which length is <code>len</code> paded with <code>c</code>
     * @exception IllegalArgumentException If <code>len</code> is larger than
     * <code>body</code>'s length
     */
    public static String lpad(String body, int len, char c) {
        if (body == null) {
            return "";
        }
        if (len < body.length()) {
            throw new IllegalArgumentException("Wrong argument!");
        }

        StringBuffer temp = new StringBuffer();
        int l = body.length();
        for (int i = 0; i < len - l; i++) {
            temp.append(c);
        }
        temp.append(body);
        return temp.toString();
    }

    /**
     * Returns a random String of numbers and letters of the specified length.
     * The method uses the Random class that is built-in to Java which is
     * suitable for low to medium grade security uses. This means that the
     * output is only pseudo random, i.e., each number is mathematically
     * generated so is not truly random.
     * <p>
     *
     * For every character in the returned String, there is an equal chance that
     * it will be a letter or number. If a letter, there is an equal chance that
     * it will be lower or upper case.
     * <p>
     *
     * The specified length must be at least one. If not, the method will return
     * null.
     *
     * @param length the desired length of the random String to return.
     * @return a random String of numbers and letters of the specified length.
     */
    public static final String randomString(int length) {
        if (length < 1) {
            return null;
        }
        // Init of pseudo random number generator.
        if (randGen == null) {
            synchronized (initLock) {
                if (randGen == null) {
                    randGen = new Random();
                    // Also initialize the numbersAndLetters array
                    numbersAndLetters = ("0123456789abcdefghijklmnopqrstuvwxyz"
                            + "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZ")
                            .toCharArray();
                }
            }
        }
        // Create a char buffer to put random letters and numbers in.
        char[] randBuffer = new char[length];
        for (int i = 0; i < randBuffer.length; i++) {
            randBuffer[i] = numbersAndLetters[randGen.nextInt(71)];
        }
        return new String(randBuffer);
    }

    /**
     * Replaces all instances of oldString with newString in line.
     * <P>
     * Recommend to use
     * <code>java.lang.String#replace(CharSequence, CharSequence)</code> which
     * since java 1.5
     *
     * @param line the String to search to perform replacements on
     * @param oldString the String that should be replaced by newString
     * @param newString the String that will replace all instances of oldString
     *
     * @return a String will all instances of oldString replaced by newString
     * @deprecated Recommend to use
     * <code>java.lang.String#replace(CharSequence, CharSequence)</code>
     * @see java.lang.String#replace(CharSequence, CharSequence)
     */
    public static final String replace(String line, String oldString,
            String newString) {
        if (line == null) {
            return null;
        }
        if (oldString == null || newString == null) {
            return line;
        }
        int i = 0;
        if ((i = line.indexOf(oldString, i)) >= 0) {
            char[] line2 = line.toCharArray();
            char[] newString2 = newString.toCharArray();
            int oLength = oldString.length();
            StringBuffer buf = new StringBuffer(line2.length);
            buf.append(line2, 0, i).append(newString2);
            i += oLength;
            int j = i;
            while ((i = line.indexOf(oldString, i)) > 0) {
                buf.append(line2, j, i - j).append(newString2);
                i += oLength;
                j = i;
            }
            buf.append(line2, j, line2.length - j);
            return buf.toString();
        }
        return line;
    }

    /**
     * Replaces all instances of oldString with newString in line.
     * <P>
     * The count Integer is updated with number of replaces.
     *
     * @param line the String to search to perform replacements on
     * @param oldString the String that should be replaced by newString
     * @param newString the String that will replace all instances of oldString
     * @param count number of replaces
     * @return a String will all instances of oldString replaced by newString
     */
    public static final String replace(String line, String oldString,
            String newString, int[] count) {
        if (line == null) {
            return null;
        }
        if (oldString == null || newString == null) {
            return line;
        }
        int i = 0;
        if ((i = line.indexOf(oldString, i)) >= 0) {
            int counter = 0;
            counter++;
            char[] line2 = line.toCharArray();
            char[] newString2 = newString.toCharArray();
            int oLength = oldString.length();
            StringBuffer buf = new StringBuffer(line2.length);
            buf.append(line2, 0, i).append(newString2);
            i += oLength;
            int j = i;
            while ((i = line.indexOf(oldString, i)) > 0) {
                counter++;
                buf.append(line2, j, i - j).append(newString2);
                i += oLength;
                j = i;
            }
            buf.append(line2, j, line2.length - j);
            count[0] = counter;
            return buf.toString();
        }
        return line;
    }

    /**
     * Replaces all strings of <code>from</code> with <code>to</code> in src.
     * <P>
     * Recommend to use <code>java.lang.String#replaceAll(String, String)</code>
     * which since java 1.4
     *
     * @param src the String to search to perform replacements on
     * @param from the String that should be replaced by to
     * @param to the String that will replace all strings of from
     * @return String a String will all strings of from replaced by to
     * @deprecated Recommend to use
     * <code>java.lang.String#replaceAll(String, String)</code>
     * @see java.lang.String#replaceAll(String, String)
     */
    public static String replaceAll(String src, String from, String to) {
        if (src == null || src.length() < 1) {
            return src;
        }
        if (from == null || to == null || from.equals(to)) {
            return src;
        }

        int tmpIndex = -1;
        while ((tmpIndex = src.indexOf(from)) != -1) {
            src = src.substring(0, tmpIndex) + to
                    + src.substring(tmpIndex + from.length());
        }
        return src;
    }

    /**
     * Replaces all instances of oldString with newString in line with the added
     * feature that matches of newString in oldString ignore case.
     *
     * @param line the String to search to perform replacements on
     * @param oldString the String that should be replaced by newString
     * @param newString the String that will replace all instances of oldString
     *
     * @return a String will all instances of oldString replaced by newString
     */
    public static final String replaceIgnoreCase(String line, String oldString,
            String newString) {
        if (line == null) {
            return null;
        }
        if (oldString == null || newString == null) {
            return line;
        }
        String lcLine = line.toLowerCase();
        String lcOldString = oldString.toLowerCase();
        int i = 0;
        if ((i = lcLine.indexOf(lcOldString, i)) >= 0) {
            char[] line2 = line.toCharArray();
            char[] newString2 = newString.toCharArray();
            int oLength = oldString.length();
            StringBuffer buf = new StringBuffer(line2.length);
            buf.append(line2, 0, i).append(newString2);
            i += oLength;
            int j = i;
            while ((i = lcLine.indexOf(lcOldString, i)) > 0) {
                buf.append(line2, j, i - j).append(newString2);
                i += oLength;
                j = i;
            }
            buf.append(line2, j, line2.length - j);
            return buf.toString();
        }
        return line;
    }

    /**
     * Pad <code>body</code> in the right side with <code>c</code> to make sure
     * returning String's length's <code>len</code>
     * <P>
     * If <code>body</code> is <code>null</code> returns String which length's
     * zero.
     * <P>
     * If <code>len</code> larger than <code>body</code>'s length throws
     * <code>IllegalArgumentException</code> exception
     *
     * @param body Coming String want to perform padding
     * @param len Length of return String
     * @param c Padding char
     * @return String which length is <code>len</code> paded with <code>c</code>
     * @exception IllegalArgumentException If <code>len</code> is larger than
     * <code>body</code>'s length
     */
    public static String rpad(String body, int len, char c) {
        if (body == null) {
            return "";
        }
        if (len < body.length()) {
            throw new IllegalArgumentException("Wrong argument!");
        }

        StringBuffer temp = new StringBuffer();
        temp.append(body);
        for (int i = 0; i < len - body.length(); i++) {
            temp.append(c);
        }
        return temp.toString();
    }

    /**
     * Accourding to <code>regex</code> to split <code>strSrc</code>
     * <P>
     * For example, The <code>strSrc</code> "boo:and:foo",
     * <P>
     * Regex Result : { "boo", "and", "foo" } o { "b", "", ":and:f" }
     * <P>
     * Recommend to use <code>java.lang.String#split(String)</code>
     *
     * @param strSrc String
     * @param regex String
     * @return String[] The array of strings computed by splitting this string
     * around matches of the given regular expression
     * @deprecated Recommend to use <code>java.lang.String#split(String)</code>
     * @see java.lang.String#split(String)
     */
    public static String[] tokenizeByRegex(String strSrc, String regex) {
        String[] strRst = null;
        if (isValuedString(strSrc) && isValuedString(regex)) {
            strRst = strSrc.split(regex);
        }
        return strRst;
    }

    /**
     * Converts a line of text into an array of lower case words. Words are
     * delimited by the following characters: , .\r\n:/\+
     * <p>
     * In the future, this method should be changed to use a
     * BreakIterator.wordInstance(). That class offers much more fexibility.
     * <p>
     * This method return <code>null</code> when <code>test</code> is null and
     * <code>new String[]{""}</code> when length is 0
     *
     * @param text a String of text to convert into an array of words
     * @return text broken up into an array of words.
     */
    public static final String[] toLowerCaseWordArray(String text) {
        if (text == null) {
            return null;
        }
        if (text.length() == 0) {
            return new String[]{""};
        }
        StringTokenizer tokens = new StringTokenizer(text, " ,\r\n.:/\\+");
        String[] words = new String[tokens.countTokens()];
        for (int i = 0; i < words.length; i++) {
            words[i] = tokens.nextToken().toLowerCase();
        }
        return words;
    }

    /**
     * Convert the first letter of coming String to be a capital
     *
     * @param src a String with the first char to be converted into capital
     * @return String with the first letter be a capital
     */
    public static String toUpperFisrtChar(String src) {
        if (src == null || src.length() < 1) {
            return "";
        }

        String first = src.substring(0, 1);
        return first.toUpperCase() + src.substring(1, src.length());
    }

    public static List splitToVecString(String in, String delimiter) {
        List q = new ArrayList();

        if (in == null || in.length() == 0) {
            return q;
        }

        int pos = 0;
        pos = in.indexOf(delimiter);
        String val = null;
        while (pos >= 0) {
            if (pos == 0) {
                val = "";
            }
            if (pos > 0) {
                val = in.substring(0, pos);
            }
            q.add(val);
            in = in.substring(pos + delimiter.length(), in.length());
            pos = in.indexOf(delimiter);
        }
        if (in.length() > 0) {
            q.add(in);
        }
        return q;
    }

    /**
     * ����һ�������������жϸ÷������Ƿ�����ַ�
     *
     * @param str �ַ�
     * @param searchChars Ҫ���ҵ��ַ�
     * @return boolean true: ��; false: ����
     */
    public static boolean containsAny(String str, String searchChars) {

        if (str.length() != str.replace(searchChars, "").length()) {
            return true;
        }
        return false;
    }

    /**
     * private constructor for this class prevent <code>StringUtils</code> to be
     * instanced
     *
     */
    private StringUtils() {

    }

    /**
     * 判断字符串是否为空
     *
     * @param string 字符串
     * @return 返回布尔值，true表示字符串为null或空字符串
     */
    public static boolean isBlank(final String string) {
        return string == null || string.trim().length() <= 0;
    }

    /**
     * 判断字符是否为空
     *
     * @param character 字符
     * @return 返回布尔值，true表示字符为null或空字符
     */
    public static boolean isBlank(final Character character) {
        return character == null || Character.isWhitespace(character.charValue());
    }

    /**
     * 判断字符是否为空
     *
     * @param character 字符
     * @return 返回布尔值，true表示字符为null或空字符
     */
    public static boolean isBlank(final StringBuilder stringBuilder) {
        return stringBuilder == null || stringBuilder.length() <= 0;
    }

    /**
     * 判断字符是否为空
     *
     * @param character 字符
     * @return 返回布尔值，true表示字符为null或空字符
     */
    public static boolean isBlank(final StringBuffer stringBuffer) {
        return stringBuffer == null || stringBuffer.length() <= 0;
    }

    /**
     * 判断对象是否为空
     *
     * @param object 对象
     * @return 返回布尔值，true表示对象为null
     */
    public static boolean isBlank(final Object object) {
        return object == null;
    }

    /**
     * 判断数组是否为空
     *
     * @param objects 数组
     * @return 返回布尔值，true表示数组为null或空数组
     */
    public static boolean isBlank(final Object[] objects) {
        return objects == null || objects.length <= 0;
    }

    /**
     * 判断集合是否为空
     *
     * @param collection 集合对象
     * @return 返回布尔值，true表示集合为null或空集合
     */
    public static boolean isBlank(final Collection<?> collection) {
        return collection == null || collection.isEmpty();
    }

    /**
     * 判断散列是否为空
     *
     * @param set 散列
     * @return 返回布尔值，true表示散列为null或空散列
     */
    public static boolean isBlank(final Set<?> set) {
        return set == null || set.isEmpty();
    }

    /**
     * 判断键值对是否为空
     *
     * @param map 键值对
     * @return 返回布尔值，true表示键值对为null或空键值对
     */
    public static boolean isBlank(final Map<?, ?> map) {
        return map == null || map.isEmpty();
    }

    /**
     * 判断键值对是否为空
     *
     * @param map 键值对
     * @return 返回布尔值，false表示键值对为null或空键值对
     */
    public static boolean isNotEmpty(String str) {
        if (str == null || ("".equals(str))) {
            return false;
        }
        return true;
    }

    /**
     * 判断list是否为空
     *
     * @Title: isListNull
     * @Description: TODO()
     * @param @param list
     * @param @return
     * @author maweijie
     * @throws
     */
    public static boolean isListNotNull(List list) {
        return list != null && list.size() > 0;
    }

    /**
     * Description:去除字符串中的空格、回车、换行符、制表符 Date:2015-2-10
     *
     * @author swp
     * @param str
     * @return
     * @return String
     */
    public static String replaceBlank(String str) {
        String dest = "";
        if (str != null) {
            Pattern p = Pattern.compile("\\s*|\t|\r|\n");
            Matcher m = p.matcher(str);
            dest = m.replaceAll("");
        }
        return dest;
    }

    public static String getCnASCII(String cnStr) {
        StringBuffer strBuf = new StringBuffer();
        byte[] bGBK = cnStr.getBytes();
        for (int i = 0; i < bGBK.length; i++) {
            // System.out.println(Integer.toHexString(bGBK[i]&0xff));
            strBuf.append(Integer.toHexString(bGBK[i] & 0xff));
        }
        return strBuf.toString();
    }

    /**
     * 对多个String 进行判断是否null or ""
     *
     * @Title: isNotEmptyList
     * @Description: TODO()
     * @param @param strs
     * @param @return
     * @author maweijie
     * @throws
     */
    public static boolean isNotEmptyStrs(String... strs) {
        if (strs != null && strs.length > 0) {
            for (String str : strs) {
                if (!StringUtils.isNotEmpty(str)) {
                    return false;
                }
            }
            return true;
        }
        return false;
    }

    /**
     * @Title: limitStrLength
     * @Description: 返回限定长度的字符串
     * @param @param str
     * @param @param length
     * @param @return
     * @return String 返回类型
     * @author zrc
     * @throws
     */
    public static String limitStrLength(String str, int length) throws Exception {
        if (StringUtils.isBlank(str) || length < 0) {
            return null;
        }
        if (str.length() <= length) {
            return str;
        } else {
            return str.substring(0, length) + "...";
        }

    }

    /**
     * @Title: changeMoneyUnit
     * @Description: TODO 修改金额单位为万元，保留一位小数，并且四舍五入
     * @param @param account
     * @param @return
     * @return String 返回类型
     * @author zrc
     * @throws
     */
    public static String changeMoneyUnit(String account) {

        Pattern pattern = Pattern.compile("[0-9]{1,}");
        Matcher matcher = pattern.matcher((CharSequence) account);
        boolean result = matcher.matches();
        if (!result) {
            return account;
        }

        BigDecimal decimal = new BigDecimal(account);
        BigDecimal temp = new BigDecimal("10000");
        if (decimal.compareTo(temp) == -1) {
            return account + "元";
        } else {
            return decimal.divide(temp).setScale(1, BigDecimal.ROUND_HALF_UP).toString() + "万元";
        }

    }

    /**
     * @Title: changeMoneyDecimal
     * @Description: TODO 保留金额的小数位，进行四舍五入
     * @param @param account
     * @param @param Decimal
     * @param @return
     * @return String 返回类型
     * @author zrc
     * @throws
     */
    public static String changeMoneyDecimal(String account, int Decimal) {
        if (StringUtils.isBlank(account) || !StringUtils.isNumeric(account)) {
            return null;
        }
        BigDecimal decimal = new BigDecimal(account);
        return decimal.setScale(Decimal, BigDecimal.ROUND_HALF_UP).toString();

    }

    /**
     * 用于搜索框电话数字判断
     *
     * @Title: isPhoneSearch
     * @Description: TODO()
     * @param @param input
     * @param @return
     * @author maweijie
     * @throws
     */
    public static boolean isPhoneSearch(String input) {
        if (input.matches("[0-9]*")) {
            return true;
        }
        return false;
    }

    /**
     * @Title: isNumeric
     * @Description: TODO判断字符串是否为数字(正负、小数)
     * @param @param str
     * @param @return
     * @return boolean 返回类型
     * @author zrc
     * @throws
     */
    public static boolean isNumeric(String str) {
        if (StringUtils.isBlank(str)) {
            return false;
        }
        return str.matches("-?[0-9]+.*[0-9]*");
    }

    /**
     * @Title: fuzzyMobilePhone
     * @Description: TODO 手机号码前三后四格式化:135****4568
     * @param @param phoneStr
     * @param @return
     * @return String 返回类型
     * @author zrc
     * @throws
     */
    public static String fuzzyMobilePhone(String phoneStr) {
        if (StringUtils.isBlank(phoneStr) && phoneStr.length() != 11) {
            return phoneStr;
        }
        return phoneStr.substring(0, 3) + "****" + phoneStr.substring(7);
    }

    /**
     * @Title: Html2Text
     * @Description: TODO 去掉字符串中的html标签
     * @param inputString
     * @return String 返回类型
     * @author zrc
     * @throws
     */
    public static String Html2Text(String inputString) {
        String htmlStr = inputString; //含html标签的字符串
        String textStr = "";
        java.util.regex.Pattern p_script;
        java.util.regex.Matcher m_script;
        java.util.regex.Pattern p_style;
        java.util.regex.Matcher m_style;
        java.util.regex.Pattern p_html;
        java.util.regex.Matcher m_html;

        try {
            String regEx_script = "<[\\s]*?script[^>]*?>[\\s\\S]*?<[\\s]*?\\/[\\s]*?script[\\s]*?>"; //定义script的正则表达式{或<script[^>]*?>[\\s\\S]*?<\\/script> }
            String regEx_style = "<[\\s]*?style[^>]*?>[\\s\\S]*?<[\\s]*?\\/[\\s]*?style[\\s]*?>"; //定义style的正则表达式{或<style[^>]*?>[\\s\\S]*?<\\/style> }
            String regEx_html = "<[^>]+>"; //定义HTML标签的正则表达式

            p_script = Pattern.compile(regEx_script, Pattern.CASE_INSENSITIVE);
            m_script = p_script.matcher(htmlStr);
            htmlStr = m_script.replaceAll(""); //过滤script标签

            p_style = Pattern.compile(regEx_style, Pattern.CASE_INSENSITIVE);
            m_style = p_style.matcher(htmlStr);
            htmlStr = m_style.replaceAll(""); //过滤style标签

            p_html = Pattern.compile(regEx_html, Pattern.CASE_INSENSITIVE);
            m_html = p_html.matcher(htmlStr);
            htmlStr = m_html.replaceAll(""); //过滤html标签

            htmlStr = htmlStr.replaceAll("&nbsp;", "");
            textStr = htmlStr;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return textStr;//返回文本字符串
    }

    /**
     * @Title: nameAddSirORMiss
     * @Description: TODO 名字加 先生 或者女士 0为男，1为女
     * @param @param name
     * @param @param sex
     * @param @return
     * @return String 返回类型
     * @author zrc
     * @throws
     */
    public static String nameAddSirORMiss(String name, String sex) {
        if (StringUtils.isBlank(name) || StringUtils.isBlank(sex)) {
            return name;
        }
        if ("0".equals(sex)) {//女
            if (name.indexOf("女士") < 0) {
                name = name + "女士";
            }
        } else if ("1".equals(sex)) {
            if (name.indexOf("先生") < 0) {
                name = name + "先生";
            }
        }
        return name;
    }

    /**
     * 用来处理M3推送判断
     *
     * @Title: IsEmptyT , 不针对判断 ""
     * @Description: TODO()
     * @param @param t
     * @param @return
     * @author maweijie
     * @throws
     */
    public static <T> boolean isNotEmptyT(T... t) {

        if (t != null && t.length > 0) {
//			System.out.println(t.length);
            return true;
        }
        return false;
    }

    /**
     *
     * @Title: mobileModel
     * @Description: TODO(判断号码是否为中间四个0)
     * @param @param mobile
     * @param @return
     * @return boolean true 是四个0 否则不是
     * @author sunmingan
     * @throws
     */
    public static boolean mobileModel(String mobile) {
        if (StringUtils.isNotEmpty(mobile)) {
            String regex = "^1[3,4,5,7,8]{1}[0-9]{1}[0]{4}[0-9]{4}$";
            boolean flg = Pattern.matches(regex, mobile);
            return flg;
        }
        return false;
    }

    public static boolean isMobile(String mobile) {
        if (StringUtils.isNotEmpty(mobile)) {
            String regex = "^((17[0-9])|(14[0-9])|(13[0-9])|(15[^4,\\D])|(18[0,5-9]))\\d{8}$";
            boolean flg = Pattern.matches(regex, mobile);
            return flg;
        }
        return false;
    }

    public static String stringtostrs(String str) {
        StringBuffer buffer = new StringBuffer();
        if (StringUtils.isNotEmpty(str)) {
            String[] strs = str.split(",");
            for (String s : strs) {
                buffer.append("'" + s + "',");
            }
        }
        if (buffer != null) {
            return buffer.toString().substring(0, buffer.toString().length() - 1);
        }
        return "";
    }

    public static String getRemoteHost(javax.servlet.http.HttpServletRequest request) {
        String ip = request.getHeader("x-forwarded-for");
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("Proxy-Client-IP");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("WL-Proxy-Client-IP");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getRemoteAddr();
        }
        return ip.equals("0:0:0:0:0:0:0:1") ? "127.0.0.1" : ip;
    }


    public static <T> List<List<T>> listSplit(List<T> resList, int count) {

        if (resList == null || count < 1)
            return null;
        List<List<T>> ret = new ArrayList<List<T>>();
        int size = resList.size();
        if (size <= count) { // 数据量不足count指定的大小
            ret.add(resList);
        } else {
            int pre = size / count;
            int last = size % count;
            // 前面pre个集合，每个大小都是count个元素
            for (int i = 0; i < pre; i++) {
                List<T> itemList = new ArrayList<T>();
                for (int j = 0; j < count; j++) {
                    itemList.add(resList.get(i * count + j));
                }
                ret.add(itemList);
            }
            // last的进行处理
            if (last > 0) {
                List<T> itemList = new ArrayList<T>();
                for (int i = 0; i < last; i++) {
                    itemList.add(resList.get(pre * count + i));
                }
                ret.add(itemList);
            }
        }
        return ret;

    }
    
    /**
     * 格式化二手房放盘时间
     * @param publishTime
     * @return
     */
    public static String formatHousePubTime(Date publishTime) {
    	if(publishTime == null)
    		return "";
    	SimpleDateFormat sdf2 = new SimpleDateFormat("yyyy-MM-dd");
    	try {
			publishTime = sdf2.parse(sdf2.format(publishTime));
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        SimpleDateFormat sdf = new SimpleDateFormat("M月d日");
        long now = System.currentTimeMillis();
        if(publishTime != null) {
            long diffDays = ((now - publishTime.getTime())/1000/60/60/24);
            if(diffDays == 0) {
            	return "今日放盘";
            } else if(diffDays == 1) {
            	return "昨日放盘";
            } else {
            	return sdf.format(publishTime) + "放盘";
            }
        }
        return "";
    }
}
