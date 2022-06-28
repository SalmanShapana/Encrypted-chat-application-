package vigenercipher;
public class vigener {
private String message;
  private String key;
  char ch;
public vigener(String text, String key) {
  this.message = text;
 this.key = key;
}
 public  String encrypt( )
    {
        String encryptedText = "";
        message = message.toUpperCase();
        for (int i = 0, j = 0; i < message.length(); i++)
        {
            char c = message.charAt(i);
            if (c < 'A' || c > 'Z')
                continue;
            encryptedText += (char) ((c + key.charAt(j) - 2 * 'A') % 26 + 'A');
            j = ++j % key.length();
        }
        return encryptedText;
    }

 public  String decrypt()
    {
        String decryptedText = "";
        message = message.toUpperCase();
        for (int i = 0, j = 0; i < message.length(); i++)
        {
            char c = message.charAt(i);
            if (c < 'A' || c > 'Z')
                continue;
            decryptedText += (char) ((c - key.charAt(j) + 26) % 26 + 'A');
            j = ++j % key.length();
        }
                         return decryptedText ;
    }
}
