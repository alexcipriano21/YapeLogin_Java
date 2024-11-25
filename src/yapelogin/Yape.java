package yapelogin;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Collections;

public class Yape extends JFrame implements ActionListener {
    private final JPasswordField passwordField;
    private final StringBuilder inputPassword;
    private final String correctPassword = "123456";
    private JLabel placeholderLabel;
    private final JLabel forgotPasswordLabel;

    private int intentosFallidos = 0;

    public Yape() {
        setTitle("Login-Yape");
        setSize(385, 730);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());
        getRootPane().setWindowDecorationStyle(JRootPane.NONE);

        inputPassword = new StringBuilder();

        JPanel panelSuperior = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                Graphics2D g2d = (Graphics2D) g;
                GradientPaint gradiente = new GradientPaint(0, 0, new Color(0x76268C), getWidth(), getHeight(), new Color(0x5D1C77));
                g2d.setPaint(gradiente);
                g2d.fillRect(0, 0, getWidth(), getHeight());
            }
        };
        panelSuperior.setLayout(new GridBagLayout());
        panelSuperior.setPreferredSize(new Dimension(385, 365));

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.insets = new Insets(20, 0, 20, 0);
        gbc.anchor = GridBagConstraints.CENTER;

        JLabel imageLabel = new JLabel();
        ImageIcon icon = new ImageIcon(getClass().getResource("/img/QR.png"));
        Image img = icon.getImage().getScaledInstance(190, 190, Image.SCALE_SMOOTH);
        icon = new ImageIcon(img);
        imageLabel.setIcon(icon);
        imageLabel.setHorizontalAlignment(SwingConstants.CENTER);

        JPanel imagePanel = new JPanel();
        imagePanel.setLayout(new BorderLayout());
        imagePanel.add(imageLabel, BorderLayout.CENTER);
        imagePanel.setPreferredSize(new Dimension(190, 190));
        imagePanel.setBorder(null);

        panelSuperior.add(imagePanel, gbc);

        add(panelSuperior, BorderLayout.NORTH);

        JPanel tecladoPanel = new JPanel(new BorderLayout());
        tecladoPanel.setBackground(Color.WHITE);

        JPanel passwordPanel = new JPanel();
        passwordPanel.setLayout(new OverlayLayout(passwordPanel));
        passwordPanel.setPreferredSize(new Dimension(300, 60));
        passwordPanel.setBackground(Color.WHITE);
        passwordPanel.setBorder(null);

        passwordField = new JPasswordField(10);
        passwordField.setFont(new Font("Arial", Font.BOLD, 24));
        passwordField.setHorizontalAlignment(JPasswordField.CENTER);
        passwordField.setEchoChar('•');
        passwordField.setOpaque(false);
        passwordField.setBorder(BorderFactory.createEmptyBorder());
        passwordPanel.add(passwordField);

        placeholderLabel = new JLabel("Ingresa tu clave");
        placeholderLabel.setFont(new Font("Arial", Font.BOLD, 24));
        placeholderLabel.setForeground(new Color(128, 0, 128));
        placeholderLabel.setHorizontalAlignment(SwingConstants.CENTER);
        placeholderLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        placeholderLabel.setVisible(true);
        passwordPanel.add(placeholderLabel);

        tecladoPanel.add(passwordPanel, BorderLayout.NORTH);

        JPanel botonesPanel = new JPanel(new GridLayout(4, 3, 10, 10));
        botonesPanel.setBackground(Color.WHITE);
        botonesPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        String[] botonesFijos = {"", "Borrar"};
        ArrayList<String> numeros = new ArrayList<>();
        for (int i = 0; i <= 9; i++) {
            numeros.add(String.valueOf(i));
        }
        Collections.shuffle(numeros);

        int indexNumeros = 0;
        for (int i = 0; i < 12; i++) {
            JButton boton;
            if (i == 9) {
                boton = new JButton(botonesFijos[0]);
                boton.setEnabled(false);
                boton.setBackground(Color.WHITE);
                boton.setBorder(null);
            } else if (i == 11) {
                boton = new JButton();
                boton.setBackground(Color.WHITE);
                boton.setFocusPainted(false);
                boton.setPreferredSize(new Dimension(50, 50));
                boton.setBorder(null);

                ImageIcon borrarIcon = new ImageIcon(getClass().getResource("/img/borrar.png"));
                Image borrarImg = borrarIcon.getImage().getScaledInstance(30, 20, Image.SCALE_SMOOTH);
                borrarIcon = new ImageIcon(borrarImg);
                boton.setIcon(borrarIcon);
            } else {
                boton = new JButton(numeros.get(indexNumeros++));
                boton.setFont(new Font("Arial", Font.BOLD, 14));
                boton.setBackground(new Color(240, 240, 240));
                boton.setFocusPainted(false);
                boton.setPreferredSize(new Dimension(50, 50));
                boton.setBorder(null);
            }
            boton.addActionListener(this);
            botonesPanel.add(boton);
        }

        tecladoPanel.add(botonesPanel, BorderLayout.CENTER);

        add(tecladoPanel, BorderLayout.CENTER);

        JPanel borrarPanel = new JPanel();
        borrarPanel.setLayout(new FlowLayout(FlowLayout.LEFT, 10, 0));

        JLabel imageLabel2 = new JLabel();
        ImageIcon icon2 = new ImageIcon(getClass().getResource("/img/QR.png"));
        Image img2 = icon2.getImage().getScaledInstance(50, 50, Image.SCALE_SMOOTH);
        icon2 = new ImageIcon(img2);
        imageLabel2.setIcon(icon2);

        JButton botonBorrar = new JButton();
        botonBorrar.setBackground(Color.WHITE);
        botonBorrar.setFocusPainted(false);
        botonBorrar.setPreferredSize(new Dimension(50, 50));
        botonBorrar.setBorder(null);

        ImageIcon borrarIcon = new ImageIcon(getClass().getResource("/img/borrar.png"));
        Image borrarImg = borrarIcon.getImage().getScaledInstance(30, 20, Image.SCALE_SMOOTH);
        borrarIcon = new ImageIcon(borrarImg);
        botonBorrar.setIcon(borrarIcon);

        borrarPanel.add(imageLabel2);
        borrarPanel.add(botonBorrar);

        add(borrarPanel, BorderLayout.SOUTH);

        forgotPasswordLabel = new JLabel("¿OLVIDASTE TU CLAVE?");
        forgotPasswordLabel.setFont(new Font("Arial", Font.BOLD, 16));
        forgotPasswordLabel.setForeground(new Color(56, 194, 177));
        forgotPasswordLabel.setHorizontalAlignment(SwingConstants.CENTER);
        forgotPasswordLabel.setAlignmentX(Component.CENTER_ALIGNMENT);

        JPanel bottomPanel = new JPanel(new BorderLayout());
        bottomPanel.setBackground(Color.WHITE);
        bottomPanel.add(forgotPasswordLabel, BorderLayout.SOUTH);

        add(bottomPanel, BorderLayout.SOUTH);

        setLocationRelativeTo(null);
        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String comando = e.getActionCommand();

        if (e.getSource() instanceof JButton) {
            JButton boton = (JButton) e.getSource();

            if (boton.getIcon() != null) {
                if (inputPassword.length() > 0) {
                    inputPassword.deleteCharAt(inputPassword.length() - 1);
                    passwordField.setText(inputPassword.toString());

                    if (inputPassword.length() == 0) {
                        placeholderLabel.setVisible(true);
                    }
                }
            } else {
                if (inputPassword.length() < 6) {
                    inputPassword.append(comando);
                    passwordField.setText(inputPassword.toString());

                    if (inputPassword.length() == 1) {
                        placeholderLabel.setVisible(false);
                    }
                }

                if (inputPassword.length() == 6) {
                    verificarClave();
                }
            }
        }
    }

    private void verificarClave() {
        if (inputPassword.toString().equals(correctPassword)) {
            JOptionPane.showMessageDialog(this, "¡Has ingresado correctamente!");
        } else {
            intentosFallidos++;
            JOptionPane.showMessageDialog(this, "Clave incorrecta, intento " + intentosFallidos);

            if (intentosFallidos == 3) {
                JOptionPane.showMessageDialog(this, "Despues de 3 intentos incorrectos el acceso se bloqueara. En caso no recuerdes tu clave, cambiala");
                dispose();
            } else {
                inputPassword.setLength(0);
                passwordField.setText("");
            }
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new Yape());
    }
}
