package com.sample;

import org.kie.api.KieServices;
import org.kie.api.runtime.KieContainer;
import org.kie.api.runtime.KieSession;

import javax.swing.*;

import java.awt.Image;
import java.io.InputStream;

public class Main {

    
    public static void main(String[] args) throws Exception {

        

        KieServices ks = KieServices.Factory.get();
        KieContainer kContainer = ks.getKieClasspathContainer();
        KieSession kSession = kContainer.newKieSession("ksession-rules");

        kSession.setGlobal("gui", new Main());
        

        kSession.fireAllRules();
    }

    // Called from Drools
    public boolean ask(String text) {
        //String text = questionMap.get(questionCode);
        int result = JOptionPane.showConfirmDialog(
                null,
                text,
                text,
                JOptionPane.YES_NO_OPTION
        );
        return (result == JOptionPane.YES_OPTION);
    }

    // Called from Drools
    public void showDrinks(String[][] dataList, String title, String folder) {
        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        panel.add(new JLabel(title));
        panel.add(new JLabel(" "));
        
        for (String[] data : dataList) {
            String displayName = data[0];
            String imageFile = data[1];

            panel.add(new JLabel(displayName));

            if (imageFile != null && !imageFile.isEmpty()) {
                try (InputStream is = getClass().getResourceAsStream(folder + imageFile)) {
                    if (is != null) {
                        ImageIcon icon = new ImageIcon(is.readAllBytes());
                        Image scaled = icon.getImage().getScaledInstance(128, 128, Image.SCALE_SMOOTH);
                        panel.add(new JLabel(new ImageIcon(scaled)));
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            panel.add(Box.createVerticalStrut(10));
        }

        JOptionPane.showMessageDialog(
                null,
                panel,
                title,
                JOptionPane.INFORMATION_MESSAGE
        );
    }
}