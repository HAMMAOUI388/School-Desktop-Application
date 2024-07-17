package alltheInterfaces;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MainInterface {

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            createAndShowMainInterface();
        });
    }

    private static void createAndShowMainInterface() {
        JFrame frame = new JFrame("System de gestion de L'ecole Superieure de Technologie ");
        frame.setSize(2000, 1500);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        // Load background image
        try {
            ImagePanel imagePanel = new ImagePanel(new ImageIcon("/Users/macbook/Downloads/WhatsApp Image 2023-12-30 at 23.58.24.jpeg").getImage());
            frame.setContentPane(imagePanel);
        } catch (Exception e) {
            e.printStackTrace();
        }
        

        // Set layout to null for absolute positioning
        frame.setLayout(null);




        frame.setLayout(new BorderLayout(10, 10));

        JPanel panel = new JPanel();
        panel.setOpaque(false); // Make the panel transparent

        JButton consultNotesButton = new JButton("Consulter les Notes");
        JButton consultEmploiDuTempsButton = new JButton("Consulter les Emplois du Temps");
        JButton consultExamsButton = new JButton("Consulter les Examen");
        JButton consultFilierButton = new JButton("Consulter les filiers");
        JButton consultCourButton = new JButton("Consulter les cours");
        JButton consultEseignButton = new JButton("Inscription des enseignants");
        JButton consultEtudiantButton = new JButton("Inscription des etudiants");
        
        // Set button positions
        consultNotesButton.setBounds(50, 50, 200, 30);
        consultEmploiDuTempsButton.setBounds(50, 100, 200, 30);
        consultExamsButton.setBounds(50, 150, 200, 30);
        consultEtudiantButton.setBounds(50, 200, 200, 30);
        consultEseignButton.setBounds(50, 250, 200, 30);
        consultCourButton.setBounds(50, 300, 200, 30);
        consultFilierButton.setBounds(50, 350, 200, 30);

        panel.add(consultNotesButton);
        panel.add(consultEmploiDuTempsButton);
        panel.add(consultExamsButton);
        panel.add(consultEtudiantButton);
        panel.add(consultEseignButton);
        panel.add(consultCourButton);
        panel.add(consultFilierButton);

        consultNotesButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Open the interface for consulting notes
               note.Main.main(null);
            }
        });

        consultEmploiDuTempsButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Open the interface for consulting emploi du temps
               emploiDuTemp.Main.main(null);
            }
        });

        consultExamsButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Open the interface for consulting exams
                examen.Main.main(null);
            }
        });

        consultEtudiantButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Open the interface for consulting exams
                exercise.Main.main(null);
            }
        });
        consultEseignButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Open the interface for consulting exams
                enseignant.Main.main(null);
            }
        });
        consultCourButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Open the interface for consulting exams
               cour.Main.main(null);
            }
        });
        consultFilierButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                filiere.Main.main(null);
            }
        });

        frame.getContentPane().add(panel);
        frame.setVisible(true);
        frame.setOpacity(0.9f);


    }

    // Custom JPanel to display background image
    private static class ImagePanel extends JPanel {
        private static final long serialVersionUID = 1L;
		private final Image image;

        public ImagePanel(Image image) {
            this.image = image;
        }

        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);
            g.drawImage(image, 0, 0, getWidth(), getHeight(), this);        }
    }
}
