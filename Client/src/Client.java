
import vigenercipher.vigener;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.InetAddress;
import java.net.Socket;
import javax.swing.JOptionPane;
public class Client extends javax.swing.JFrame {
    private ObjectOutputStream output;
    private ObjectInputStream input;
    private String message="";
    private String serverIP;
    private Socket connection;
    private int port = 6789;
    public Client(String s) {
        initComponents();
        this.setTitle("Client");
        this.setVisible(true);
        status.setVisible(true);
        serverIP = s;
    }
  @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jTextField1 = new javax.swing.JTextField();
        jButton1 = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        chatArea = new javax.swing.JTextArea();
        jLabel2 = new javax.swing.JLabel();
        status = new javax.swing.JLabel();
        jButton2 = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setResizable(false);

        jPanel1.setLayout(null);

        jTextField1.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        jTextField1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField1ActionPerformed(evt);
            }
        });
        jPanel1.add(jTextField1);
        jTextField1.setBounds(20, 270, 270, 40);

        jButton1.setText("Send");
        jButton1.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });
        jPanel1.add(jButton1);
        jButton1.setBounds(290, 270, 100, 20);

        chatArea.setColumns(20);
        chatArea.setRows(5);
        chatArea.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        jScrollPane1.setViewportView(chatArea);

        jPanel1.add(jScrollPane1);
        jScrollPane1.setBounds(10, 30, 370, 210);

        jLabel2.setText("Write your text here");
        jPanel1.add(jLabel2);
        jLabel2.setBounds(20, 250, 150, 20);

        status.setText("Connections");
        jPanel1.add(status);
        status.setBounds(90, 0, 300, 30);

        jButton2.setText("Decrypt ");
        jButton2.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });
        jPanel1.add(jButton2);
        jButton2.setBounds(290, 290, 100, 20);

        jLabel1.setText("Chating");
        jPanel1.add(jLabel1);
        jLabel1.setBounds(10, 10, 90, 16);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, 402, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 373, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );

        setSize(new java.awt.Dimension(418, 381));
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void jTextField1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField1ActionPerformed

        sendMessage(jTextField1.getText());
	jTextField1.setText("");
    }//GEN-LAST:event_jTextField1ActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed

       sendMessage(jTextField1.getText());
	jTextField1.setText("");
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        
                          vigener c = new vigener(message ,"VIGENERECIPHER");
                        c.decrypt(); 
                      chatArea.append("\n Server says: " +(c.decrypt()));
    }//GEN-LAST:event_jButton2ActionPerformed

    
    public void startRunning()
    {
       try
       {
            status.setText("Trying to Connect ");
            try
            {
                connection = new Socket(InetAddress.getByName(serverIP),port);
            }catch(IOException ioEception)
            {
                    JOptionPane.showMessageDialog(null,"Server might be down","Warning",JOptionPane.WARNING_MESSAGE);
            }
            status.setText("Connected to: " + connection.getInetAddress().getHostName());


            output = new ObjectOutputStream(connection.getOutputStream());
            output.flush();
            input = new ObjectInputStream(connection.getInputStream());

            whileChatting();
       }
       catch(IOException ioException)
       {
            ioException.printStackTrace();
       }
    }
    
    private void whileChatting() throws IOException
    {
      jTextField1.setEditable(true);
      do{ 
              try
              {          
                    
                      message = (String) input.readObject();
                       chatArea.append("\n encrypted text from server: " +(message));
                      

              }
              catch(ClassNotFoundException classNotFoundException)
              {
              }
      }while(!message.equals("Client - END"));
    }
  
    private void sendMessage(String message)
    {
        try
        { 
            vigener c = new vigener(message ,"VIGENERECIPHER");

                        c.encrypt();
            output.writeObject( (c.encrypt()));
            output.flush();
            chatArea.append("\n Client: "+message);
                        

        }
        catch(IOException ioException)
        {
            chatArea.append("\n Unable to Send Message");
        }
    }
 
    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTextArea chatArea;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTextField jTextField1;
    private javax.swing.JLabel status;
    // End of variables declaration//GEN-END:variables
}
