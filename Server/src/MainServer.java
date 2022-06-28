import vigenercipher.vigener;
import java.io.EOFException;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class MainServer extends javax.swing.JFrame {

    private ObjectOutputStream output;
    private ObjectInputStream input;
    private Socket connection;
    private ServerSocket server;
    private int Clients = 20;
    private int port = 6789;
    private String message="";

  
    public MainServer() {
        
        initComponents();
        this.setTitle("Server");
        this.setVisible(true);
        status.setVisible(true);
    }
    
    public void startRunning()
    {
        try
        {
            server=new ServerSocket(port, Clients);
            while(true)
            {
                try
                {
                    status.setText(" Waiting for Connect ");
                    connection=server.accept();
                    status.setText(" Now Connected to "+connection.getInetAddress().getHostName());


                    output = new ObjectOutputStream(connection.getOutputStream());
                    output.flush();
                    input = new ObjectInputStream(connection.getInputStream());

                    whileChatting();

                }catch(EOFException eofException)
                {
                }
            }
        }
        catch(IOException ioException)
        {
                ioException.printStackTrace();
        }
    }
    
  

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        chatArea = new javax.swing.JTextArea();
        jTextField1 = new javax.swing.JTextField();
        jButton1 = new javax.swing.JButton();
        status = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jButton2 = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel1.setLayout(null);

        chatArea.setColumns(20);
        chatArea.setRows(5);
        chatArea.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        jScrollPane1.setViewportView(chatArea);

        jPanel1.add(jScrollPane1);
        jScrollPane1.setBounds(10, 40, 370, 230);

        jTextField1.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        jTextField1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField1ActionPerformed(evt);
            }
        });
        jPanel1.add(jTextField1);
        jTextField1.setBounds(10, 300, 280, 40);

        jButton1.setText("Send");
        jButton1.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });
        jPanel1.add(jButton1);
        jButton1.setBounds(300, 300, 90, 20);

        status.setText("Connections");
        jPanel1.add(status);
        status.setBounds(100, 10, 270, 30);

        jLabel2.setText("Write your text here");
        jPanel1.add(jLabel2);
        jLabel2.setBounds(20, 280, 150, 20);

        jButton2.setText("decrypt ");
        jButton2.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });
        jPanel1.add(jButton2);
        jButton2.setBounds(300, 320, 90, 20);

        jLabel1.setText("Chating");
        jPanel1.add(jLabel1);
        jLabel1.setBounds(10, 20, 50, 16);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 399, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 2, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 361, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 11, Short.MAX_VALUE))
        );

        setSize(new java.awt.Dimension(417, 380));
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        
        sendMessage(jTextField1.getText());
	jTextField1.setText("");
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jTextField1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField1ActionPerformed
        
        sendMessage(jTextField1.getText());
	jTextField1.setText("");
    }//GEN-LAST:event_jTextField1ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
                           
                       jTextField1.setEditable(true);
                       
                        vigener c = new vigener(message ,"VIGENERECIPHER");
                        c.decrypt(); 
                      chatArea.append("\n Client says: " +(c.decrypt()));
                      
        
    }//GEN-LAST:event_jButton2ActionPerformed
        private void whileChatting() throws IOException
   {  
        jTextField1.setEditable(true);
        do{
                try
                {  
                        message = (String) input.readObject();
                        chatArea.append("\n encrypted text from client+ "+message);
                }catch(ClassNotFoundException classNotFoundException)
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
                          
                        output.writeObject( c.encrypt());

            output.flush();
            chatArea.append("\n Server: "+message);
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
