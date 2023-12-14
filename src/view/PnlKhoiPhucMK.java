package view;

import java.awt.EventQueue;
import java.awt.Font;
import java.awt.HeadlessException;
import java.security.cert.X509Certificate;
import java.util.Properties;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import javax.net.ssl.SSLContext;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.ImageIcon;
import java.awt.Color;

public class PnlKhoiPhucMK extends JFrame {

    private JPanel contentPane;
    private JTextField textField;
    private JTextField textEmail;

    /**
     * Launch the application.
     */
    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    PnlKhoiPhucMK frame = new PnlKhoiPhucMK();
                    frame.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    /**
     * Create the frame.
     */
    public PnlKhoiPhucMK() {
        setLocationRelativeTo(null);
//        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setDefaultCloseOperation(1);
        setBounds(100, 100, 450, 300);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

        setContentPane(contentPane);
        contentPane.setLayout(null);

        JLabel lblNewLabel = new JLabel("Khôi phục mật khẩu");
        lblNewLabel.setForeground(new Color(255, 255, 255));
        lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 17));
        lblNewLabel.setBounds(163, 11, 155, 29);
        contentPane.add(lblNewLabel);

        JLabel lblNhpTiKhon = new JLabel("Nhập tài khoản:");
        lblNhpTiKhon.setForeground(new Color(255, 255, 255));
        lblNhpTiKhon.setFont(new Font("Tahoma", Font.PLAIN, 17));
        lblNhpTiKhon.setBounds(30, 51, 123, 29);
        contentPane.add(lblNhpTiKhon);

        JLabel lblNhpEmail = new JLabel("Nhập email:");
        lblNhpEmail.setForeground(new Color(255, 255, 255));
        lblNhpEmail.setFont(new Font("Tahoma", Font.PLAIN, 17));
        lblNhpEmail.setBounds(30, 104, 93, 29);
        contentPane.add(lblNhpEmail);

        textField = new JTextField();
        textField.setBounds(163, 51, 251, 27);
        contentPane.add(textField);
        textField.setColumns(10);

        textEmail = new JTextField();
        textEmail.setColumns(10);
        textEmail.setBounds(163, 106, 251, 27);
        contentPane.add(textEmail);

        JButton btnNewButton = new JButton("Xác nhận");
        btnNewButton.addMouseListener(new MouseAdapter() {
        	@Override
        	public void mouseClicked(MouseEvent e) {
        		// TODO add your handling code here:
                String email = textEmail.getText();
                sendEmail(email);
        	}
        });
        btnNewButton.setFont(new Font("Tahoma", Font.PLAIN, 15));
        btnNewButton.setBounds(163, 163, 116, 29);
        contentPane.add(btnNewButton);
        
        JLabel lblNewLabel_1 = new JLabel("New label");
        lblNewLabel_1.setIcon(new ImageIcon(PnlKhoiPhucMK.class.getResource("/view/icon/anhnengdchinh.jpg")));
        lblNewLabel_1.setBounds(0, 0, 436, 263);
        contentPane.add(lblNewLabel_1);
    }

    public void sendEmail(String toEmail) {
        Properties props = new Properties();
        final String EMAIL_USERNAME = "khoinguyeniuh@gmail.com"; // Replace with your email
        final String EMAIL_PASSWORD = "cjcf uclv eyig akeq"; // Replace with your password

        props.put("mail.smtp.host", "smtp.gmail.com");
        props.put("mail.smtp.socketFactory.port", "587");
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.port", "587");
        props.put("mail.smtp.starttls.enable", "true");

        // Create a TrustManager temporarily
        TrustManager[] trustAllCerts = new TrustManager[]{
                new X509TrustManager() {
                    public X509Certificate[] getAcceptedIssuers() {
                        return null;
                    }

                    public void checkClientTrusted(
                            X509Certificate[] certs, String authType) {
                    }

                    public void checkServerTrusted(
                            X509Certificate[] certs, String authType) {
                    }
                }
        };

        // Set TrustManager into SSLContext
        try {
            SSLContext sc = SSLContext.getInstance("SSL");
            sc.init(null, trustAllCerts, new java.security.SecureRandom());

            // Use SSLContext in Session
            props.put("mail.smtp.ssl.socketFactory", sc.getSocketFactory());
        } catch (Exception e) {
            e.printStackTrace();
        }

        Session session = Session.getDefaultInstance(props,
                new javax.mail.Authenticator() {
                    protected PasswordAuthentication getPasswordAuthentication() {
                        return new PasswordAuthentication(EMAIL_USERNAME, EMAIL_PASSWORD);
                    }
                }
        );

        try {
            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress(EMAIL_USERNAME));
            message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(toEmail));
            message.setSubject("Khôi phục mật khẩu");

            // EMAIL CONTENT
            Multipart multiPart = new MimeMultipart();
            MimeBodyPart messageBodyPart1 = new MimeBodyPart();
            messageBodyPart1.setText("Mật khẩu mới của bạn là: abcABC123");

            multiPart.addBodyPart(messageBodyPart1);

            // Set email content as a combination of body parts
            message.setContent(multiPart);

            Transport.send(message);

            // Uncomment the following lines if you have a JTextField named txt_username
            // TaiKhoan tk = tk_dao.getTK_Theousername(txt_username.getText());
            // if(tk != null) {
            //     tk_dao.CapNhatTK(tk.getMaTK(), "1111");
            // }

            JOptionPane.showMessageDialog(null, "Gửi email thành công");
        } catch (MessagingException | HeadlessException e) {
            System.out.println(e);
            JOptionPane.showMessageDialog(null, e);
        }
    }
}