package tr.edu.istiklal;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class HesapMakinesi {
    private JPanel anaPanel;
    private JTextField txtEkran;
    private JButton btn3, btn4, btn5, btn6, btn7, btn8, btn9, btn1, btn2, btn0;
    private JButton btnEsittir, btnTopla, btnCikar, btnCarp, btnBol, btnC, btnSil, btnVirgul;
    private JLabel lblGecmis;

    private double ilkSayi = 0;
    private String operator = "";
    private boolean islemSecildi = false;
    private String tumGecmis = "";

    HesapIslemleri hesaplayici = new HesapIslemleri();

    public HesapMakinesi() {
        stilUygula();

        ActionListener rakamDinleyici = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JButton tiklananButon = (JButton) e.getSource();
                if (islemSecildi) {
                    txtEkran.setText("");
                    islemSecildi = false;
                }
                txtEkran.setText(txtEkran.getText() + tiklananButon.getText());
            }
        };

        JButton[] rakamlar = {btn0, btn1, btn2, btn3, btn4, btn5, btn6, btn7, btn8, btn9};
        for (JButton btn : rakamlar) btn.addActionListener(rakamDinleyici);

        ActionListener islemDinleyici = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (txtEkran.getText().isEmpty()) return;
                JButton tiklanan = (JButton) e.getSource();
                double suAnki = Double.parseDouble(txtEkran.getText());
                if (!operator.isEmpty()) {
                    ilkSayi = hesaplayici.islemYap(ilkSayi, suAnki, operator);
                    txtEkran.setText(String.valueOf(ilkSayi));
                } else {
                    ilkSayi = suAnki;
                }
                operator = tiklanan.getText();
                islemSecildi = true;
                tumGecmis = ilkSayi + " " + operator + " ";
                lblGecmis.setText(tumGecmis);
            }
        };

        btnTopla.addActionListener(islemDinleyici);
        btnCikar.addActionListener(islemDinleyici);
        btnCarp.addActionListener(islemDinleyici);
        btnBol.addActionListener(islemDinleyici);

        btnEsittir.addActionListener(e -> {
            if (txtEkran.getText().isEmpty() || operator.isEmpty()) return;
            double ikinci = Double.parseDouble(txtEkran.getText());
            double sonuc = hesaplayici.islemYap(ilkSayi, ikinci, operator);
            lblGecmis.setText(tumGecmis + ikinci + " =");
            txtEkran.setText(String.valueOf(sonuc));
            operator = "";
            islemSecildi = true;
        });

        btnSil.addActionListener(e -> {
            String metin = txtEkran.getText();
            if (!metin.isEmpty()) txtEkran.setText(metin.substring(0, metin.length() - 1));
        });

        btnVirgul.addActionListener(e -> {
            if (!txtEkran.getText().contains(".")) {
                txtEkran.setText(txtEkran.getText().isEmpty() ? "0." : txtEkran.getText() + ".");
            }
        });

        btnC.addActionListener(e -> {
            txtEkran.setText("");
            lblGecmis.setText("");
            ilkSayi = 0;
            operator = "";
            tumGecmis = "";
        });
    }

    private void stilUygula() {
        Color koyuMavi = new Color(10, 25, 47);
        Color acikMavi = new Color(100, 255, 218);
        Color butonBg = new Color(17, 34, 64);

        anaPanel.setBackground(koyuMavi);
        txtEkran.setBackground(koyuMavi);
        txtEkran.setForeground(acikMavi);
        txtEkran.setBorder(null);
        txtEkran.setFont(new Font("Consolas", Font.BOLD, 42));
        txtEkran.setHorizontalAlignment(JTextField.RIGHT);

        lblGecmis.setForeground(new Color(136, 146, 176));
        lblGecmis.setHorizontalAlignment(SwingConstants.RIGHT);
        lblGecmis.setFont(new Font("Consolas", Font.PLAIN, 15));

        JButton[] hepsi = {btn0, btn1, btn2, btn3, btn4, btn5, btn6, btn7, btn8, btn9,
                btnTopla, btnCikar, btnCarp, btnBol, btnEsittir, btnC, btnSil, btnVirgul};

        for (JButton b : hepsi) {
            b.setBackground(butonBg);
            b.setForeground(Color.WHITE);
            b.setFont(new Font("Segoe UI", Font.BOLD, 20));
            b.setFocusPainted(false);
            b.setBorder(BorderFactory.createLineBorder(koyuMavi, 1));
            b.setMargin(new Insets(0, 0, 0, 0));

            if (b.getText().matches("[+\\-*/=C]")) {
                b.setForeground(acikMavi);
            }
        }
    }

    public JPanel getAnaPanel() {
        return anaPanel;
    }
}